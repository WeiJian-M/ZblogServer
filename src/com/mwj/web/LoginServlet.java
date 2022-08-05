package com.mwj.web;

import com.mwj.dao.UserDao;
import com.mwj.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        System.out.println("hello zblog");//测试
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 创建该用户的User类并传入属性
        User loginUser = new User();
        loginUser.setUserName(username);
        loginUser.setUserPassword(password);

        // 创建用户操作类对用户进行操作
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        if(user != null){
            response.setHeader("msg", "" + user.getUserId());
        }else {
            System.out.println("kkkkkkk");//测试
            response.setHeader("msg", "0");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
