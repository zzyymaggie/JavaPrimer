package xyz.zzyymaggie.mybatis.dao.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.zzyymaggie.mybatis.model.Style;

public class StyleDao {
    public List<Style> getAll(Connection conn, int maxCount) {
        List<Style> styleList = new ArrayList<Style>();
        String sql = "select * from style_year_season where ROWNUM <= ?";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            pstmt.setInt(1, maxCount);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                long styleColorId = rs.getLong("style_color_id");
                int year = rs.getInt("year");
                int month = rs.getInt("month");
                Style style = new Style();
                style.setStyleColorId(styleColorId);
                style.setYear(year);
                style.setMonth(month);
                styleList.add(style);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return styleList;
    }
    
    public List<Style> batchGetAll(Connection conn, int maxCount) {
        List<Style> styleList = new ArrayList<Style>();
        String sql = "select * from style_year_season where ROWNUM <= ?";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            pstmt.setInt(1, maxCount);
            pstmt.setFetchSize(1000);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                long styleColorId = rs.getLong("style_color_id");
                int year = rs.getInt("year");
                int month = rs.getInt("month");
                Style style = new Style();
                style.setStyleColorId(styleColorId);
                style.setYear(year);
                style.setMonth(month);
                styleList.add(style);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return styleList;
    }
}
