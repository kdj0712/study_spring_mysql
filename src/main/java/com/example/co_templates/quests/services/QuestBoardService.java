package com.example.co_templates.quests.services;

import java.util.HashMap;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.co_templates.daos.ShareDao;
import com.example.co_templates.utils.Commons;

@Service
public class QuestBoardService {
    @Autowired
    ShareDao shareDao;

    @Autowired
    Commons commons;
    String pk_boards = commons.getUniqueSequence();

    public Object selectMany(HashMap<String,Object> dataMap){
        String sqlMapId = "QuestBoard.selectBySearch";
        Object list = shareDao.getList(sqlMapId, dataMap);
        return list;
    }
    public Object selectOne(HashMap<String,Object> dataMap){
        String sqlMapId = "QuestBoard.selectByUID";
        dataMap.put("PK_BOARDS","news-004");
        Object one = shareDao.getOne(sqlMapId, dataMap);
        return one;
    }
    public Object insert(HashMap<String,Object> dataMap){
        String sqlMapId = "QuestBoard.insert";
        // (`PK_BOARDS`, `TITLE`, `CONTENTS`, `WRITER_ID`, `CREATE_DATE`, `PARENT_BOARDS`)
        dataMap.put("PK_BOARDS", pk_boards);
        dataMap.put("TITLE", "시제품-1");
        dataMap.put("CONTENTS", "내용없음");
        dataMap.put("WRITER_ID", "김덕재");
        dataMap.put("PARENT_BOARDS", "문학");
        Object insert = shareDao.getList(sqlMapId, dataMap);
        return insert;
    }
    public Object update(HashMap<String,Object> dataMap){
        String sqlMapId = "QuestBoard.update";
        dataMap.put("PK_BOARDS", pk_boards);
        dataMap.put("TITLE", "테스트데이터");
        Object update = shareDao.getList(sqlMapId, dataMap);
        return update;
    }
    public Object delete(HashMap<String,Object> dataMap){
        String sqlMapId = "QuestBoard.delete";
        dataMap.put("PK_BOARDS", pk_boards);
        Object delete = shareDao.getList(sqlMapId, dataMap);
        return delete;
    }
    public void callDao(HashMap<String,Object> dataMap){
        this.selectMany(dataMap);
        this.selectOne(dataMap);
        this.insert(dataMap);
        this.update(dataMap);
        this.delete(dataMap);
        return ; 

    }


    
}
