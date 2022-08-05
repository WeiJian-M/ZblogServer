package com.mwj.web;

import com.mwj.dao.BlogDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addABlogServlet")
public class AddABlogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("hello add");
        String userId = request.getParameter("userId");
        String blogContent = request.getParameter("blogContent");
        String blogTime = request.getParameter("blogTime");

//        System.out.println(userId);
//        System.out.println(blogContent);
//        System.out.println(blogTime);

        BlogDao blogDao = new BlogDao();
        Boolean isSendTrue = blogDao.addABlog(userId, blogContent, blogTime);
        if(isSendTrue){
            response.setHeader("isSend", "1");
        }else{
            response.setHeader("isSend", "0");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
