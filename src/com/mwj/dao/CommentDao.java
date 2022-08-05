package com.mwj.dao;

import com.mwj.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class CommentDao {

    // 声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate((JDBCUtils.getDataSource()));

    /**
     *
     * @param blogId 需要获得评论的微博 Id
     * @return 返回查询结果
     */
    public List<Map<String, Object>> showComment(String blogId){
        try {
            String sql = "select user.userName, comment.commentTime, comment.commentContent from user, comment where user.userId = comment.userId and comment.blogId = ?";
            List<Map<String, Object>> commentListMaps = template.queryForList(sql, blogId);
            return commentListMaps;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param userId 发布评论的用户 Id
     * @param blogId 往哪个微博上发布评论
     * @param commentContent 评论内容
     * @param commentTime 发布评论的时间
     * @return
     */
    public Boolean addAComment(String userId, String blogId, String commentContent, String commentTime){
        try {
            String sql = "insert into comment values(null, ?, ?, ?, ?)";
            int updateNum = template.update(sql, userId, blogId, commentContent, commentTime);
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
