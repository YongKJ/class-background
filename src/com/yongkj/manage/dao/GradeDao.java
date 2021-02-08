package com.yongkj.manage.dao;

import com.yongkj.manage.eneity.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GradeDao {

    private Connection conn;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public GradeDao() {
        conn = null;
        st = null;
        ps = null;
        rs = null;
    }

    public List<Grade> getGrade() throws Exception {
        List<Grade> gradesList = new ArrayList<>();
        sql = "select * from t_chinasofti_class";
        conn = C3P0Utils.getConnection();
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        while (rs.next()){
            Grade grade = new Grade();
            grade.setId(rs.getInt(1));
            grade.setCreate_time(rs.getString(2));
            grade.setStart_time(rs.getString(3));
            grade.setEnd_time(rs.getString(4));
            grade.setName(rs.getString(5));
            grade.setT_id(rs.getInt(6));
            grade.setStatus(rs.getInt(7));
            gradesList.add(grade);
        }
        C3P0Utils.closeAll(conn, st, rs);
        return gradesList;
    }

    public void addGrade(Grade grade) throws Exception {
        sql = "insert into t_chinasofti_class (create_time, start_time, end_time, name, t_id, status) values (?, ?, ?, ?, ?, ?)";
        conn = C3P0Utils.getConnection();
        ps  = conn.prepareStatement(sql);
        String timeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ps.setString(1, timeStr);
        ps.setString(2, grade.getStart_time());
        ps.setString(3, grade.getEnd_time());
        ps.setString(4, grade.getName());
        ps.setInt(5, grade.getT_id());
        ps.setInt(6, grade.getStatus());
        ps.executeUpdate();
        C3P0Utils.closeAll(conn, ps);
    }

    public void modGrade(Grade grade) throws Exception {
        sql = "update t_chinasofti_class set start_time = ?, end_time = ?, name = ?, t_id = ?, status = ? where id = ?";
        conn = C3P0Utils.getConnection();
        ps  = conn.prepareStatement(sql);
        ps.setString(1, grade.getStart_time());
        ps.setString(2, grade.getEnd_time());
        ps.setString(3, grade.getName());
        ps.setInt(4, grade.getT_id());
        ps.setInt(5, grade.getStatus());
        ps.setInt(6, grade.getId());
        ps.executeUpdate();
        C3P0Utils.closeAll(conn, ps);
    }

    public void delGrade(Grade grade) throws Exception {
        sql = "delete from t_chinasofti_class where id = ?";
        conn = C3P0Utils.getConnection();
        ps  = conn.prepareStatement(sql);
        ps.setInt(1, grade.getId());
        ps.executeUpdate();
        C3P0Utils.closeAll(conn, ps);
    }
    
}
