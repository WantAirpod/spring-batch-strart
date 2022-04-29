package com9.shop.domain.entity.ItemDAO;

import com9.shop.domain.entity.Mapper.ItemRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ItemDAOSpring extends JdbcDaoSupport {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setSuperDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    //SQL 명령
    private final String BOARD_INSERT = "insert into Item(item_id, name, price, stock) values(?,?,?,?)";
    private final String BOARD_UPDATE = "update Item set stcok=? where item_id=?";
    private final String BOARD_GET = "select * from Item where item_id=?";
    private final String BOARD_LIST = "select * from Item ";


    // 글 수정
    public void updateBoard(ItemVO vo) {
        System.out.println("===> Spring JDBC로 updateBoard() 기능 처리");
        getJdbcTemplate().update(BOARD_UPDATE, vo.getStock(), vo.getItemId());
    }
    // 글 상세 조회
    public ItemVO getBoard(ItemVO vo) {
        System.out.println("===> Spring JDBC로 getBoard() 기능 처리");
        Object[] args = { vo.getItemId() };
        return getJdbcTemplate().queryForObject(BOARD_GET, args, new ItemRowMapper());
    }

    // 글 목록 조회
    public List<ItemVO> getBoardList(ItemVO vo) {
        System.out.println("===> Spring JDBC로 getBoardList() 기능 처리");
        return getJdbcTemplate().query(BOARD_LIST, new ItemRowMapper());
    }
    // CRUD 기능의 메소드 구현
    // 글 등록
    public void insertBoard(ItemVO vo) {
        System.out.println("===> Spring JDBC로 insertBoard() 기능 처리");
        getJdbcTemplate().update(BOARD_INSERT, vo.getItemId(), vo.getName(), vo.getPrice(), vo.getStock());
    }
}
