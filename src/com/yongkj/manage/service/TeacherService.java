package com.yongkj.manage.service;

import com.yongkj.manage.dao.TeacherDao;
import com.yongkj.manage.eneity.Teacher;

import java.util.List;

public class TeacherService {

    private TeacherDao teacherDao;

    public TeacherService() {
        teacherDao = new TeacherDao();
    }

    public List<Teacher> getTeacher() throws Exception {
        return teacherDao.getTeacher();
    }

    public void addTeacher(Teacher teacher) throws Exception {
        teacherDao.addTeacher(teacher);
    }

    public void modTeacherInfo(Teacher teacher) throws Exception {
        teacherDao.modTeacherInfo(teacher);
    }

    public void modTeacherPassword(Teacher teacher) throws Exception {
        teacherDao.modTeacherPassword(teacher);
    }

    public void delTeacher(Teacher teacher) throws Exception {
        teacherDao.delTeacher(teacher);
    }

}
