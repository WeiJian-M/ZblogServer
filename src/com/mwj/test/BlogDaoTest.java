package com.mwj.test;

import com.alibaba.druid.support.json.JSONUtils;
import com.mwj.dao.BlogDao;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class BlogDaoTest {

    @Test
    public void showMainBlogsTest(){
        String idList = "1";
        BlogDao blogDao = new BlogDao();
        List<Map<String, Object>> maps = blogDao.showMainBlogs(idList);
        String str = JSONUtils.toJSONString(maps); //此行转换
        System.out.println(maps.size());
        System.out.println(maps);
        System.out.println(str);
    }

    @Test
    public void addABlogTest(){
        String userId = "1";
        String blogContent = "My Blog22222222222";
        String blogTime = "2020-12-26 19:54:09";

        BlogDao blogDao = new BlogDao();
        Boolean aBoolean = blogDao.addABlog(userId, blogContent, blogTime);
        System.out.println(aBoolean);
    }

    @Test
    public void deleteABlogTest(){
        String aBlogId = "32";
        BlogDao blogDao = new BlogDao();
        Boolean aBoolean = blogDao.deleteABlog(aBlogId);
        System.out.println(aBoolean);
    }
}
