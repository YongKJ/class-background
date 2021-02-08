package com.yongkj.manage.controller;

import com.yongkj.manage.eneity.Grade;
import com.yongkj.manage.eneity.Student;
import com.yongkj.manage.service.GradeService;
import com.yongkj.manage.service.StudentService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentController extends HttpServlet {

    private BasicController basicController;
    private StudentService studentService;
    private GradeService gradeService;

    public StudentController() {
        basicController = new BasicController();
        studentService = new StudentService();
        gradeService = new GradeService();
    }

    public void getStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");

        List<Student> studentsList = studentService.getStudent();
        List<Grade> gradesList = gradeService.getGrade();
        for(int i = 0; i < studentsList.size(); i++) {
            for(int j = 0; j < gradesList.size(); j++) {
                if(studentsList.get(i).getClass_id() == gradesList.get(j).getId()) {
                    studentsList.get(i).setClassName(gradesList.get(j).getName());
                    break;
                }
            }
        }
        json.put("count", studentsList.size());

        String p = request.getParameter("page");
        String limit = request.getParameter("limit");
        if(p != null && limit != null && !p.equals("") && !limit.equals("")) {
            int page = Integer.valueOf(p);
            int pageSize = Integer.valueOf(limit);
            int start = -1;
            int end = -1;

            int listSum = studentsList.size();
            start = (page - 1) * pageSize;
            end = start + pageSize <= listSum ? start + pageSize : listSum;

            List<Student> studentsListPage = new ArrayList<>();
            for(int i = start; i < end; i++) {
                studentsListPage.add(studentsList.get(i));
            }
            json.put("data", studentsListPage);
        }else{
            json.put("data", studentsList);
        }

        basicController.writeJson(json.toString(), response);
    }

    public void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String school = request.getParameter("school");
        String subject = request.getParameter("subject");
        String education = request.getParameter("education");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");
        String status = request.getParameter("status");
        String class_id = request.getParameter("class_id");
        if(name != null && password != null && school != null && subject != null && education != null && phone != null && sex != null && status != null && class_id != null) {
            if(!name.equals("") && !password.equals("") && !school.equals("") && !subject.equals("") && !education.equals("") && !phone.equals("") && !sex.equals("") && !status.equals("") && !class_id.equals("")) {
                String md5Password = basicController.md5(password);
                Student student = new Student();
                student.setName(name);
                student.setPassword(md5Password);
                student.setSchool(school);
                student.setSubject(subject);
                student.setEducation(education);
                student.setPhone(phone);
                student.setSex(Integer.valueOf(sex));
                student.setStatus(Integer.valueOf(status));
                student.setClass_id(Integer.valueOf(class_id));
                studentService.addStudent(student);
            }
        }

        getStudent(request, response);
    }

    public void modStudentInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String school = request.getParameter("school");
        String subject = request.getParameter("subject");
        String education = request.getParameter("education");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");
        String status = request.getParameter("status");
        String class_id = request.getParameter("class_id");
        if(id != null && name != null && school != null && subject != null && education != null && phone != null && sex != null && status != null && class_id != null) {
            if(!id.equals("") && !name.equals("") && !school.equals("") && !subject.equals("") && !education.equals("")  && !phone.equals("") && !sex.equals("") && !status.equals("") && !class_id.equals("")) {
                Student student = new Student();
                student.setId(Integer.valueOf(id));
                student.setName(name);
                student.setSchool(school);
                student.setSubject(subject);
                student.setEducation(education);
                student.setPhone(phone);
                student.setSex(Integer.valueOf(sex));
                student.setStatus(Integer.valueOf(status));
                student.setClass_id(Integer.valueOf(class_id));
                studentService.modStudentInfo(student);
            }
        }

        getStudent(request, response);
    }

    public void modStudentPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        if(id != null && password != null) {
            if(!id.equals("") && !password.equals("")) {
                String md5Password = basicController.md5(password);
                Student student = new Student();
                student.setId(Integer.valueOf(id));
                student.setPassword(md5Password);
                studentService.modStudentPassword(student);
            }
        }

        getStudent(request, response);
    }

    public void delStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        if(id != null && !id.equals("")) {
            Student student = new Student();
            student.setId(Integer.valueOf(id));
            studentService.delStudent(student);
        }

        getStudent(request, response);
    }

    public void judge(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");

        String operate = request.getParameter("operate");
        if(operate != null) {
            if(operate.equals("get")) {
                getStudent(request, response);
            }else if(operate.equals("add")){
                addStudent(request, response);
            }else if(operate.equals("modInfo")){
                modStudentInfo(request, response);
            }else if(operate.equals("modPassword")){
                modStudentPassword(request, response);
            }else if(operate.equals("del")){
                delStudent(request, response);
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
