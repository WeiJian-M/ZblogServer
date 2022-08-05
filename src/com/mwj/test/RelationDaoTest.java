package com.mwj.test;

import com.alibaba.druid.support.json.JSONUtils;
import com.mwj.dao.RelationDao;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class RelationDaoTest {

    @Test
    public void getConcernListStrTest(){

        String userId = "14";

        RelationDao relationDao = new RelationDao();

        String concernListStr = relationDao.getConcernListStr(userId);

        System.out.println(concernListStr);
    }

    @Test
    public void addAConcernTest(){
        String myId = "1";
        String wantId = "7";

        RelationDao relationDao = new RelationDao();
        int i = relationDao.addAConcern(myId, wantId);

        System.out.println(i);

    }

    @Test
    public void showMyConcernsTest(){
        String userId = "1";
        RelationDao relationDao = new RelationDao();
        List<Map<String, Object>> maps = relationDao.showMyConcerns(userId);
        String str = JSONUtils.toJSONString(maps); //此行转换
        System.out.println(maps.size());
        System.out.println(maps);
        System.out.println(str);
    }

    @Test
    public void deleteAConcernTest(){
        String meId = "1";
        String heId = "6";
        RelationDao relationDao = new RelationDao();
        Boolean aBoolean = relationDao.deleteAConcern(meId, heId);
        System.out.println(aBoolean);
    }
}
