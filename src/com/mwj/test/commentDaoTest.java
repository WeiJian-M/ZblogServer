package com.mwj.test;

import com.mwj.dao.CommentDao;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class commentDaoTest {

    @Test
    public void showCommentTest(){
        String blogId = "2";
        CommentDao commentDao = new CommentDao();
        List<Map<String, Object>> commentListMaps = commentDao.showComment(blogId);

        System.out.println(commentListMaps);
        System.out.println(commentListMaps.size());

    }

    @Test
    public void addAComment(){
        String userId = "1";
        String blogId = "2";
        String commentContent = "手动发布评论";
        String commentTime = "2020-12-27 20:41:42";

        CommentDao commentDao = new CommentDao();
        Boolean aBoolean = commentDao.addAComment(userId, blogId, commentContent, commentTime);

        System.out.println(aBoolean);
    }
}
