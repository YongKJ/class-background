package com.yongkj.manage.controller;

import com.yongkj.manage.eneity.Grade;
import com.yongkj.manage.eneity.Teacher;
import com.yongkj.manage.service.GradeService;
import com.yongkj.manage.service.TeacherService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GradeController extends HttpServlet {

    private BasicController basicController;
    private GradeService gradeService;
    private TeacherService teacherService;

    public GradeController() {
        basicController = new BasicController();
        gradeService = new GradeService();
        teacherService = new TeacherService();
    }

    public void getGrade(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");

        List<Grade> gradesList = gradeService.getGrade();
        List<Teacher> teachersList = teacherService.getTeacher();
        for(int i = 0; i < gradesList.size(); i++) {
            for(int j = 0; j < teachersList.size(); j++) {
                if(gradesList.get(i).getT_id() == teachersList.get(j).getId()) {
                    gradesList.get(i).setTeacherName(teachersList.get(j).getName());
                    break;
                }
            }
        }
        json.put("count", gradesList.size());

        String p = request.getParameter("page");
        String limit = request.getParameter("limit");
        if(p != null && limit != null && !p.equals("") && !limit.equals("")) {
            int page = Integer.valueOf(p);
            int pageSize = Integer.valueOf(limit);
            int start = -1;
            int end = -1;

            int listSum = gradesList.size();
            start = (page - 1) * pageSize;
            end = start + pageSize <= listSum ? start + pageSize : listSum;

            List<Grade> gradesListPage = new ArrayList<>();
            for(int i = start; i < end; i++) {
                gradesListPage.add(gradesList.get(i));
            }
            json.put("data", gradesListPage);
        }else{
            json.put("data", gradesList);
        }

        basicController.writeJson(json.toString(), response);
    }

    public void addGrade(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String start_time = request.getParameter("start_time");
        String end_time = request.getParameter("end_time");
        String name = request.getParameter("name");
        String t_id = request.getParameter("t_id");
        String status = request.getParameter("status");
        if(start_time != null && end_time != null && name != null && t_id != null && status != null) {
            if(!start_time.equals("") && !end_time.equals("") && !name.equals("") && !t_id.equals("") && !status.equals("")) {
                Grade grade = new Grade();
                grade.setStart_time(start_time);
                grade.setEnd_time(end_time);
                grade.setName(name);
                grade.setT_id(Integer.valueOf(t_id));
                grade.setStatus(Integer.valueOf(status));
                gradeService.addGrade(grade);
            }
        }

        getGrade(request, response);
    }

    public void modGrade(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String start_time = request.getParameter("start_time");
        String end_time = request.getParameter("end_time");
        String name = request.getParameter("name");
        String t_id = request.getParameter("t_id");
        String status = request.getParameter("status");
        if(id != null && start_time != null && end_time != null && name != null && t_id != null && status != null) {
            if(!id.equals("") &&!start_time.equals("") && !end_time.equals("") && !name.equals("") && !t_id.equals("") && !status.equals("")) {
                Grade grade = new Grade();
                grade.setId(Integer.valueOf(id));
                grade.setStart_time(start_time);
                grade.setEnd_time(end_time);
                grade.setName(name);
                grade.setT_id(Integer.valueOf(t_id));
                grade.setStatus(Integer.valueOf(status));
                gradeService.modGrade(grade);
            }
        }

        getGrade(request, response);
    }

    public void delGrade(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        if(id != null && !id.equals("")) {
            Grade grade = new Grade();
            grade.setId(Integer.valueOf(id));
            gradeService.delGrade(grade);
        }

        getGrade(request, response);
    }

    public void judge(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");

        String operate = request.getParameter("operate");
        if(operate != null) {
            if(operate.equals("get")) {
                getGrade(request, response);
            }else if(operate.equals("add")){
                addGrade(request, response);
            }else if(operate.equals("mod")){
                modGrade(request, response);
            }else if(operate.equals("del")){
                delGrade(request, response);
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            judge(request, response);
        }catch(Exception e){}
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
}
