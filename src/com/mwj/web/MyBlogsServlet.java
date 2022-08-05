package com.mwj.web;

import com.alibaba.druid.support.json.JSONUtils;
import com.mwj.dao.BlogDao;
import com.mwj.dao.RelationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/myBlogsServlet")
public class MyBlogsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hello myblogs");
        request.setCharacterEncoding("utf-8");
        String userId = request.getParameter("userId");
        System.out.println("userId"+ userId);

        // 利用userIdListStr获取blog列表数据
        BlogDao blogDao = new BlogDao();
        List<Map<String, Object>> blogMaps = blogDao.showMainBlogs(userId);

        // 转换为JSON数据
        String JSONstr = JSONUtils.toJSONString(blogMaps);
        System.out.println(blogMaps);
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
