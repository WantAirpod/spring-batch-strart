package com9.shop.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class CsvParser  {
    private static String targetFilePath ;

    //csv path
    public static void parser() throws IOException {
        final DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        ClassPathResource resource = new ClassPathResource("/csv/items_.csv");
        targetFilePath = resource.getFile().getPath();
        final String jdbcURL = "jdbc:h2:mem:test";
        final String username = "sa";
        final String password = "";

        final int batchSize = 2_000; //bulk insert시 커밋 갯수

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);

            //String sql = "insert into item(id,name,price,stock) " + "VALUES ('id', 'name', 'price', 'stock')";
            String sql = "insert into item(id,name,price,stock) " + "VALUES ( ? , ? , ? , ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            int columnSize = 4; //CSV 데이터 필드 컬럼 갯수
            List<CSVRecord> records = getCsvRecords();


            for (int row = 0; row < records.size(); row++) {
                CSVRecord data = records.get(row);
                for (int fieldIndex = 0; fieldIndex < columnSize; fieldIndex++) {
                    statement.setString(fieldIndex + 1, data.get(fieldIndex));
                }
                statement.addBatch();
                if (row % batchSize == 0) {
                    statement.executeBatch();
                    System.out.println(String.format("statement.executeBatch ing row ==> %s", row));
                    connection.commit(); //DB서버 부하분산을 원하는 대용량 처리시 중간중간 커밋
                    sleep(1); //부하 분산
                }
            }
            //남아있는 데이터 처리
            System.out.println("나머지 데이터도 executeBatch ");
            statement.executeBatch();
            connection.commit();
            connection.close();
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sleep(long millis) {
        try { Thread.sleep(millis);
        } catch (InterruptedException e) { e.printStackTrace();
        }
    }

    private static List<CSVRecord> getCsvRecords() throws IOException {
        File targetFile = new File(targetFilePath);
        int sampleDataRow = 0; //샘플 데이터 row번호
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(targetFile))) {
            CSVParser parser = CSVFormat.EXCEL.withFirstRecordAsHeader().withQuote('"').parse(bufferedReader);
            //엑셀타입 & 쌍따옴표 escape처리
            List<CSVRecord> records = parser.getRecords();
            log.debug("\nCSV 헤더\n\t{}\n데이터 샘플\n\t{}\n", parser.getHeaderMap(), records.get(sampleDataRow));
            log.info("\n\t헤더 필드 갯수 :{}\n\t데이터 갯수 :{}\n\t{}번째 row의 데이터 필드 갯수:{}\n\n", parser.getHeaderMap().size(), records.size(), sampleDataRow, records.get(sampleDataRow).size());
            return records; }
    }
}
