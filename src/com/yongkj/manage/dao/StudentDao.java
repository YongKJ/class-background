package com.yongkj.manage.dao;

import com.yongkj.manage.eneity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private Connection conn;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public StudentDao() {
        conn = null;
        st = null;
        ps = null;
        rs = null;
    }

    public List<Student> getStudent() throws Exception {
        List<Student> studentsList = new ArrayList<>();
        sql = "select * from t_chinasofti_student";
        conn = C3P0Utils.getConnection();
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        while (rs.next()){
            Student student = new Student();
            student.setId(rs.getInt(1));
            student.setCreate_time(rs.getString(2));
            student.setUpdate_time(rs.getString(3));
            student.setName(rs.getString(4));
            student.setPassword(rs.getString(5));
            student.setSchool(rs.getString(6));
            student.setSubject(rs.getString(7));
            student.setEducation(rs.getString(8));
            student.setPhone(rs.getString(9));
            student.setSex(rs.getInt(10));
            student.setStatus(rs.getInt(11));
            student.setClass_id(rs.getInt(12));
            studentsList.add(student);
        }
        C3P0Utils.closeAll(conn, st, rs);
        return studentsList;
    }

    public void addStudent(Student student) throws Exception {
        sql = "insert into t_chinasofti_student (create_time, name, password, school, subject, education, phone, sex, status, class_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        conn = C3P0Utils.getConnection();
        ps  = conn.prepareStatement(sql);
        String timeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ps.setString(1, timeStr);
        ps.setString(2, student.getName());
        ps.setString(3, student.getPassword());
        ps.setString(4, student.getSchool());
        ps.setString(5, student.getSubject());
        ps.setString(6, student.getEducation());
        ps.setString(7, student.getPhone());
        ps.setInt(8, student.getSex());
        ps.setInt(9, student.getStatus());
        ps.setInt(10, student.getClass_id());
        ps.executeUpdate();
        C3P0Utils.closeAll(conn, ps);
    }

    public void modStudentInfo(Student student) throws Exception {
        sql = "update t_chinasofti_student set update_time = ?, name = ?, school = ?, subject = ?, education = ?, phone = ?, sex = ?, status = ?, class_id = ? where id = ?";
        conn = C3P0Utils.getConnection();
        ps  = conn.prepareStatement(sql);
        String timeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ps.setString(1, timeStr);
        ps.setString(2, student.getName());
        ps.setString(3, student.getSchool());
        ps.setString(4, student.getSubject());
        ps.setString(5, student.getEducation());
        ps.setString(6, student.getPhone());
        ps.setInt(7, student.getSex());
        ps.setInt(8, student.getStatus());
        ps.setInt(9, student.getClass_id());
        ps.setInt(10, student.getId());
        ps.executeUpdate();
        C3P0Utils.closeAll(conn, ps);
    }

    public void modStudentPassword(Student student) throws Exception {
        sql = "update t_chinasofti_student set update_time = ?, password = ? where id = ?";
        conn = C3P0Utils.getConnection();
        ps  = conn.prepareStatement(sql);
        String timeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ps.setString(1, timeStr);
        ps.setString(2, student.getPassword());
        ps.setInt(3, student.getId());
        ps.executeUpdate();
        C3P0Utils.closeAll(conn, ps);
    }

    public void delStudent(Student student) throws Exception {
        sql = "delete from t_chinasofti_student where id = ?";
        conn = C3P0Utils.getConnection();
        ps  = conn.prepareStatement(sql);
        ps.setInt(1, student.getId());
        ps.executeUpdate();
        C3P0Utils.closeAll(conn, ps);
    }
    
}
