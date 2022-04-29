package com9.shop.domain.entity.Mapper;

import com9.shop.domain.entity.ItemDAO.ItemVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<ItemVO> {

    public ItemVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemVO item = new ItemVO();
        item.setItemId(rs.getLong("1"));
        return item;
    }

}
