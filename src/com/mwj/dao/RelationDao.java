package com.mwj.dao;

import com.mwj.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class RelationDao {

    // 声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate((JDBCUtils.getDataSource()));

    /**
     *
     * @param userId 用户 Id 的字符串表示形式
     * @return 返回用户的关注者列表，以及该用户自己
     */
    public String getConcernListStr(String userId){
        StringBuilder builder = new StringBuilder();
        try{
            // 编写sql语句
            String sql = "select heId from relation where meId = ?";
            // 2. 调用query方法
            List<Map<String, Object>> blogMapsList = template.queryForList(sql, userId);

            for (int i = 0; i < blogMapsList.size()-1; i++) {
                Map<String, Object> item = blogMapsList.get(i);
                String heId = item.get("heId") + ",";
                builder.append(heId);
            }
            builder.append(blogMapsList.get(blogMapsList.size()-1).get("heId"));

            return builder.toString();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param myId 我的 Id
     * @param wantId 想要关注的用户的 Id
     * @return 返回关注结果，非零：关注成功；0、-1：关注失败；
     */
    public int addAConcern(String myId, String wantId){
        try{
            // sql语句
            String sql1 = "insert into relation values(?, ?)";
            String sql2 = "select * from relation where meId = ? and heId = ?";
            // 插入一条记录
            List<Map<String, Object>> maps = template.queryForList(sql2, myId, wantId);
            if(maps.size() > 0){
                return 2;
            }
            int update = template.update(sql1, myId, wantId);
            return update;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }


    /**
     *
     * @param userId 我的用户Id
     * @return 返回我的关注列表
     */
    public List<Map<String, Object>> showMyConcerns(String userId){
        try{
            String sql = "select relation.meId, relation.heId, user.userName from relation, user where relation.meId = " + userId + " and relation.heId = user.userId and user.userId != " + userId;

            List<Map<String, Object>> myConcernsList = template.queryForList(sql);

            return  myConcernsList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param meId 我的 Id
     * @param heId 想要取消关注的用户 Id
     * @return
     */
    public Boolean deleteAConcern(String meId, String heId){
        try {
            String sql = "delete from relation where meId = ? and heId = ?";
            int updateNum = template.update(sql, meId, heId);
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
