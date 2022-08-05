package com.mwj.web;

import com.mwj.dao.BlogDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteABlogServlet")
public class DeleteABlogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hello deleteABlog");
        request.setCharacterEncoding("utf-8");

        String aBlogId = request.getParameter("aBlogId");

        BlogDao blogDao = new BlogDao();
        Boolean aBoolean = blogDao.deleteABlog(aBlogId);

        if(aBoolean){
            response.setHeader("isDeleteSuccess", "1");
        }else {
            response.setHeader("isDeleteSuccess", "0");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
