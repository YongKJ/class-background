package com.yongkj.manage.service;

import com.yongkj.manage.dao.GradeDao;
import com.yongkj.manage.eneity.Grade;

import java.util.List;

public class GradeService {

    private GradeDao gradeDao;

    public GradeService() {
        gradeDao = new GradeDao();
    }

    public List<Grade> getGrade() throws Exception {
        return gradeDao.getGrade();
    }

    public void addGrade(Grade grade) throws Exception {
        gradeDao.addGrade(grade);
    }

    public void modGrade(Grade grade) throws Exception {
        gradeDao.modGrade(grade);
    }

    public void delGrade(Grade grade) throws Exception {
        gradeDao.delGrade(grade);
    }
    
}
