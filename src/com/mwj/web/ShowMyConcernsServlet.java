package com.mwj.web;

import com.alibaba.druid.support.json.JSONUtils;
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

@WebServlet("/showMyConcernsServlet")
public class ShowMyConcernsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hello myConcerns");
        request.setCharacterEncoding("utf-8");

        String userId = request.getParameter("userId");

        RelationDao relationDao = new RelationDao();
        List<Map<String, Object>> concernsMapList = relationDao.showMyConcerns(userId);
        String JSONstr = JSONUtils.toJSONString(concernsMapList); //此行转换
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
