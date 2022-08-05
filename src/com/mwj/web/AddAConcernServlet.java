package com.mwj.web;

import com.mwj.dao.RelationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addAConcernServlet")
public class AddAConcernServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("hello addA");

        String myId = request.getParameter("myId");
        String wantId = request.getParameter("wantId");

        System.out.println(myId);
        System.out.println(wantId);

        RelationDao relationDao = new RelationDao();
        int i = relationDao.addAConcern(myId, wantId);
        if(i == 1){
            response.setHeader("isAddSuccess", "success");
        }else{
            response.setHeader("isAddSuccess", "fail");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
