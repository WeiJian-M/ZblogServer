package com.mwj.web;

import com.mwj.dao.RelationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteAConcernServlet")
public class DeleteAConcernServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hello dConcern");
        request.setCharacterEncoding("utf-8");

        String meId = request.getParameter("meId");
        String heId = request.getParameter("heId");

        RelationDao relationDao = new RelationDao();
        Boolean isDelete = relationDao.deleteAConcern(meId, heId);

        if(isDelete){
            response.setHeader("isDelete", "1");
        }else{
            response.setHeader("isDelete", "0");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
