package com.mwj.dao;

import com.mwj.domain.User;
import com.mwj.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class BlogDao {

    // 声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate((JDBCUtils.getDataSource()));

    /**
     * @param userIdListStr 一个字符串，包含需要查询的用户ID，类似于 "1,2,3"
     * @return 返回一个List<Map<String, Object>>, 便于封装成JSON返回给客户端
     */
    public List<Map<String, Object>> showMainBlogs(String userIdListStr){
        try{
            // 1. 编写sql语句
            // String sql = "select * from blog where userId in (" + userIdListStr + ")";
            String sql = "select blog.blogId, blog.userId, user.userName, blog.blogContent, blog.blogTime from blog, user where blog.userId in ("+userIdListStr+") and blog.userId = user.userId";
            // 2. 调用query方法
            List<Map<String, Object>> blogMapsList = template.queryForList(sql);
            return blogMapsList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param userId 用户 Id
     * @param bolgContent 微博内容
     * @param blogTime 微博时间
     * @return 是否发布成功
     */
    public Boolean addABlog(String userId, String bolgContent, String blogTime){
        try {
            String sql = "insert into blog values(?, ?, ?, ?)";
            int updateNum = template.update(sql, null, userId, bolgContent, blogTime);
//            System.out.println(updateNum);
            if(updateNum > 0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param aBlogId 需要删除的微博 Id
     * @return 是否删除成功
     *
     */
    public Boolean deleteABlog(String aBlogId){
        try{
            String sql = "delete from blog where blogId = ?";
            int updateNum = template.update(sql, aBlogId);
            if(updateNum > 0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
