package com.mwj.web;

import com.mwj.dao.UserDao;
import com.mwj.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registServlet")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hello register");//测试

        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 创建该用户的User类并传入属性
        User registUser = new User();
        registUser.setUserName(username);
        registUser.setUserPassword(password);

        // 创建用户操作类对用户进行操作
        UserDao userDao = new UserDao();
        Integer msgRegister = userDao.regist(registUser);
        response.setHeader("msgRegister", ""+msgRegister);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
