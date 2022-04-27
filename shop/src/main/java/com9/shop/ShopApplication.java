package com9.shop;

import com9.shop.common.CsvParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

//@EnableBatchProcessing
@SpringBootApplication
@Slf4j
public class ShopApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(ShopApplication.class, args);
		//csv parse & insert db
		CsvParser csvParser = new CsvParser();
		csvParser.parser();
	}
}
