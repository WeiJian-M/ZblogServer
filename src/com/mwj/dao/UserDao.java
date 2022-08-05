package com.mwj.dao;


import com.mwj.domain.User;
import com.mwj.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/*
操作数据库中user表的类
 */
public class UserDao {
    // 声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate((JDBCUtils.getDataSource()));
    /**
     * 登录方法
     * @param loginUser 只有用户名和密码
     * @return user包含用户全部数据
     */
    public User login(User loginUser){

        try{
            // 1. 编写sql语句
            String sql = "select * from user where userName = ? and userPassword = ?";
            // 2. 调用query方法
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUserName(), loginUser.getUserPassword());
            return user;
        }catch (Exception e){
//            e.printStackTrace();
            return null;
        }
    }

    /**
     * 注册方法
     * @param registUser 只有用户名和密码
     * @return user包含用户表全部数据
     */
    public Integer regist(User registUser){

        // sql查询语句，用于验证用户名是否重复
        String sql1 = "select * from user where userName = ?";
        //sql插入语句，用于注册用户信息，添加数据
        String sql2 = "insert into user values(?, ?, ?)";
        // sql插入语句，用于添加用户关系
        String sql3 = "insert into relation values(?, ?)";

        try {
            // 利用下面这条代码进行验证，如果查询得到，证明用户存在，返回-1
            User user1 = template.queryForObject(sql1,
                    new BeanPropertyRowMapper<User>(User.class),
                    registUser.getUserName());
            System.out.println("Tag");
            return -1;//执行到这一步未报错，说明用户名已存在，返回-1
        }catch (Exception e){
//            e.printStackTrace();
            template.update(sql2, null, registUser.getUserName(), registUser.getUserPassword());
            try{
                // 再次查询
                User user2 = template.queryForObject(sql1,
                        new BeanPropertyRowMapper<User>(User.class),
                        registUser.getUserName());
                template.update(sql3, user2.getUserId(), user2.getUserId());
                return user2.getUserId(); // 注册成功，返回id，为正整数
            }catch (Exception ei){
                return 0; // 注册失败，返回0
            }
        }
    }

    /**
     *
     * @param myId 我的 Id
     * @param userNameItem 想要查询的用户名字段
     * @return 返回一个List<Map<String, Object>>, 便于封装成JSON返回给客户端
     */
    public List<Map<String, Object>> searchUsers(String myId, String userNameItem){
        try{
            // 1. 编写sql语句
            // String sql = "select * from blog where userId in (" + userIdListStr + ")";
            String sql = "select * from user where userName like '%"+ userNameItem +"%' and userId in (SELECT distinct meId FROM relation where meId not in (select heId from relation where meId = "+ myId +"))";
            // 2. 调用query方法
            List<Map<String, Object>> userMapsList = template.queryForList(sql);
            return userMapsList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

