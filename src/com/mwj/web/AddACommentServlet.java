package com.mwj.web;

import com.mwj.dao.CommentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addACommentServlet")
public class AddACommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("hello AddComment");

        String userId = request.getParameter("userId");
        String blogId = request.getParameter("blogId");
        String commentContent = request.getParameter("commentContent");
        String commentTime = request.getParameter("commentTime");

        CommentDao commentDao = new CommentDao();
        Boolean isSendCommentSuccess = commentDao.addAComment(userId, blogId, commentContent, commentTime);
        if(isSendCommentSuccess){
            response.setHeader("isSendCommentSuccess", "1");
        }else {
            response.setHeader("isSendCommentSuccess", "0");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
