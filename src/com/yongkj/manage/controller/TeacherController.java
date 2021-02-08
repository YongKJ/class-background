package com.yongkj.manage.controller;

import com.yongkj.manage.eneity.Teacher;
import com.yongkj.manage.service.TeacherService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeacherController extends HttpServlet {

    private BasicController basicController;
    private TeacherService teacherService;

    public TeacherController() {
        basicController = new BasicController();
        teacherService = new TeacherService();
    }

    public void getTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");

        List<Teacher> teachersList = teacherService.getTeacher();
        json.put("count", teachersList.size());

        String p = request.getParameter("page");
        String limit = request.getParameter("limit");
        if(p != null && limit != null && !p.equals("") && !limit.equals("")) {
            int page = Integer.valueOf(p);
            int pageSize = Integer.valueOf(limit);
            int start = -1;
            int end = -1;

            int listSum = teachersList.size();
            start = (page - 1) * pageSize;
            end = start + pageSize <= listSum ? start + pageSize : listSum;

            List<Teacher> teachersListPage = new ArrayList<>();
            for(int i = start; i < end; i++) {
                teachersListPage.add(teachersList.get(i));
            }
            json.put("data", teachersListPage);
        }else{
            json.put("data", teachersList);
        }
        basicController.writeJson(json.toString(), response);
    }

    public void addTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");
        String active = request.getParameter("active");
        if(name != null && password != null && phone != null && sex != null && active != null) {
            if(!name.equals("") && !password.equals("") && !phone.equals("") && !sex.equals("") && !active.equals("")) {
                String md5Password = basicController.md5(password);
                Teacher teacher = new Teacher();
                teacher.setName(name);
                teacher.setPassword(md5Password);
                teacher.setPhone(phone);
                teacher.setSex(Integer.valueOf(sex));
                teacher.setActive(Integer.valueOf(active));
                teacherService.addTeacher(teacher);
            }
        }

        getTeacher(request, response);
    }

    public void modTeacherInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");
        String active = request.getParameter("active");
        if(id != null && name != null  && phone != null && sex != null && active != null) {
            if(!id.equals("") && !name.equals("") && !phone.equals("") && !sex.equals("") && !active.equals("")) {
                Teacher teacher = new Teacher();
                teacher.setId(Integer.valueOf(id));
                teacher.setName(name);
                teacher.setPhone(phone);
                teacher.setSex(Integer.valueOf(sex));
                teacher.setActive(Integer.valueOf(active));
                teacherService.modTeacherInfo(teacher);
            }
        }

        getTeacher(request, response);
    }

    public void modTeacherPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        if(id != null && password != null) {
            if(!id.equals("") && !password.equals("")) {
                String md5Password = basicController.md5(password);
                Teacher teacher = new Teacher();
                teacher.setId(Integer.valueOf(id));
                teacher.setPassword(md5Password);
                teacherService.modTeacherPassword(teacher);
            }
        }

        getTeacher(request, response);
    }

    public void delTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        if(id != null && !id.equals("")) {
            Teacher teacher = new Teacher();
            teacher.setId(Integer.valueOf(id));
            teacherService.delTeacher(teacher);
        }

        getTeacher(request, response);
    }

    public void judge(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");

        String operate = request.getParameter("operate");
        if(operate != null) {
            if(operate.equals("get")) {
                getTeacher(request, response);
            }else if(operate.equals("add")){
                addTeacher(request, response);
            }else if(operate.equals("modInfo")){
                modTeacherInfo(request, response);
            }else if(operate.equals("modPassword")){
                modTeacherPassword(request, response);
            }else if(operate.equals("del")){
                delTeacher(request, response);
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
