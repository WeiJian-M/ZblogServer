package com.mwj.web;

import com.alibaba.druid.support.json.JSONUtils;
import com.mwj.dao.CommentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/showCommentServlet")
public class ShowCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("hello comment");

        String blogId = request.getParameter("blogId");
        System.out.println(blogId);

        CommentDao commentDao = new CommentDao();
        List<Map<String, Object>> commentListMaps = commentDao.showComment(blogId);

        // 转换为JSON数据
        String JSONstr = JSONUtils.toJSONString(commentListMaps);
        System.out.println(commentListMaps);
        System.out.println(JSONstr);

        /**
         * 返回给客户端JSON数据
         */
        response.setContentType("text/html;charset=UTF-8");
        //禁用缓存，确保网页信息是最新数据
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", -10);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(JSONstr);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(out != null){
                out.close();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
