package com.yongkj.manage.dao;

import com.yongkj.manage.eneity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao {

    private Connection conn;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public TeacherDao() {
        conn = null;
        st = null;
        ps = null;
        rs = null;
    }

    public List<Teacher> getTeacher() throws Exception {
        List<Teacher> teachersList = new ArrayList<>();
        sql = "select * from t_chinasofti_teacher";
        conn = C3P0Utils.getConnection();
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        while (rs.next()){
            Teacher teacher = new Teacher();
            teacher.setId(rs.getInt(1));
            teacher.setCreate_time(rs.getString(2));
            teacher.setUpdate_time(rs.getString(3));
            teacher.setName(rs.getString(4));
            teacher.setPassword(rs.getString(5));
            teacher.setPhone(rs.getString(6));
            teacher.setSex(rs.getInt(7));
            teacher.setActive(rs.getInt(8));
            teachersList.add(teacher);
        }
        C3P0Utils.closeAll(conn, st, rs);
        return teachersList;
    }

    public void addTeacher(Teacher teacher) throws Exception {
        sql = "insert into t_chinasofti_teacher (create_time, name, password, phone, sex, active) values (?, ?, ?, ?, ?, ?)";
        conn = C3P0Utils.getConnection();
        ps  = conn.prepareStatement(sql);
        String timeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ps.setString(1, timeStr);
        ps.setString(2, teacher.getName());
        ps.setString(3, teacher.getPassword());
        ps.setString(4, teacher.getPhone());
        ps.setInt(5, teacher.getSex());
        ps.setInt(6, teacher.getActive());
        ps.executeUpdate();
        C3P0Utils.closeAll(conn, ps);
    }

    public void modTeacherInfo(Teacher teacher) throws Exception {
        sql = "update t_chinasofti_teacher set update_time = ?, name = ?, phone = ?, sex = ?, active = ? where id = ?";
        conn = C3P0Utils.getConnection();
        ps  = conn.prepareStatement(sql);
        String timeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ps.setString(1, timeStr);
        ps.setString(2, teacher.getName());
        ps.setString(3, teacher.getPhone());
        ps.setInt(4, teacher.getSex());
        ps.setInt(5, teacher.getActive());
        ps.setInt(6, teacher.getId());
        ps.executeUpdate();
        C3P0Utils.closeAll(conn, ps);
    }

    public void modTeacherPassword(Teacher teacher) throws Exception {
        sql = "update t_chinasofti_teacher set  update_time = ?, password = ? where id = ?";
        conn = C3P0Utils.getConnection();
        ps  = conn.prepareStatement(sql);
        String timeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ps.setString(1, timeStr);
        ps.setString(2, teacher.getPassword());
        ps.setInt(3, teacher.getId());
        ps.executeUpdate();
        C3P0Utils.closeAll(conn, ps);
    }

    public void delTeacher(Teacher teacher) throws Exception {
        sql = "delete from t_chinasofti_teacher where id = ?";
        conn = C3P0Utils.getConnection();
        ps  = conn.prepareStatement(sql);
        ps.setInt(1, teacher.getId());
        ps.executeUpdate();
        C3P0Utils.closeAll(conn, ps);
    }

}
