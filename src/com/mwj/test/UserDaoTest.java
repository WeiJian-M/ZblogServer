package com.mwj.test;

import com.alibaba.druid.support.json.JSONUtils;
import com.mwj.dao.UserDao;
import com.mwj.domain.User;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class UserDaoTest {

    @Test
    public void testLogin(){
        User loginuser = new User();

        loginuser.setUserName("admin");
        loginuser.setUserPassword("000000");


        UserDao dao = new UserDao();
        User user = dao.login(loginuser);

        System.out.println(user);
    }

    // 测试注册
    @Test
    public void testRegist(){
        User registUser = new User();

        registUser.setUserName("test");
        registUser.setUserPassword("000000");

        UserDao dao = new UserDao();
        Integer regist = dao.regist(registUser);
        System.out.println(regist);
    }

    @Test
    public void searchUsersTest(){
        UserDao userDao = new UserDao();
        String myId = "1";
        String userNameItem = "b";
        List<Map<String, Object>> maps = userDao.searchUsers(myId, userNameItem);
        String str = JSONUtils.toJSONString(maps); //此行转换
        System.out.println(maps);
        System.out.println(maps.size());
        System.out.println(str);
    }
}
