package com.yongkj.manage.service;

import com.yongkj.manage.dao.StudentDao;
import com.yongkj.manage.eneity.Student;

import java.util.List;

public class StudentService {

    private StudentDao studentDao;

    public StudentService() {
        studentDao = new StudentDao();
    }

    public List<Student> getStudent() throws Exception {
        return studentDao.getStudent();
    }

    public void addStudent(Student student) throws Exception {
        studentDao.addStudent(student);
    }

    public void modStudentInfo(Student student) throws Exception {
        studentDao.modStudentInfo(student);
    }

    public void modStudentPassword(Student student) throws Exception {
        studentDao.modStudentPassword(student);
    }

    public void delStudent(Student student) throws Exception {
        studentDao.delStudent(student);
    }
    
}
