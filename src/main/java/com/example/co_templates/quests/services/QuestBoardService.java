package com.example.co_templates.quests.services;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.co_templates.daos.ShareDao;
import com.example.co_templates.utils.Commons;
@Service
public class QuestBoardService {
    @Autowired
    ShareDao shareDao;
    private Commons commons;
    private String pk_boards;
    @Autowired
    public void setCommons(Commons commons) {
        this.commons = commons;
    }

    public Object selectMany(HashMap<String,Object> dataMap){
        String sqlMapId = "board.selectBySearch";
        Object list = shareDao.getList(sqlMapId, dataMap);
        return list;
    }

    public Object selectOne(HashMap<String,Object> dataMap){
        String sqlMapId = "board.selectByUID";
        dataMap.put("PK_BOARDS","news-004");
        Object one = shareDao.getOne(sqlMapId, dataMap);
        return one;
    }
    public Object insert(HashMap<String,Object> dataMap){
        pk_boards = commons.getUniqueSequence();
        String sqlMapId = "board.insert";
        // (`PK_BOARDS`, `TITLE`, `CONTENTS`, `WRITER_ID`, `CREATE_DATE`, `PARENT_BOARDS`)
        dataMap.put("PK_BOARDS", pk_boards);
        dataMap.put("PARENT_BOARDS", "");
        Object insert = shareDao.insert(sqlMapId, dataMap);
        return insert ;
    }
    public Object update(HashMap<String,Object> dataMap,String PK_BOARDS){
        String sqlMapId = "board.update";
        dataMap.put("PK_BOARDS", PK_BOARDS);
        Object update = shareDao.update(sqlMapId, dataMap);
        return update;
    }
    
    public Object delete(HashMap<String,Object> dataMap,String PK_BOARDS){
        String sqlMapId;
        // VISITORS 테이블에서 해당 PK_BOARDS를 사용하는 레코드가 있는지 확인
        sqlMapId = "board.checkVisitorExists";
        dataMap.put("PK_BOARDS", PK_BOARDS);
        int count = (Integer) shareDao.getOne(sqlMapId, dataMap);
        // 조건에 따라 적절한 SQL 매퍼 실행
        if (count > 0) {
            // VISITORS와 BOARDS에서 PK_BOARDS를 사용하는 레코드 삭제
            sqlMapId = "board.deleteVisitorAndBoard";
        } else {
            // BOARDS에서만 PK_BOARDS를 사용하는 레코드 삭제
            sqlMapId = "board.deleteBoardOnly";
        }
        // 삭제 작업 수행
        Object delete = shareDao.delete(sqlMapId, dataMap);
        return delete;
    }
    
    public void callDao(HashMap<String,Object> dataMap){
        this.selectMany(dataMap);
        this.selectOne(dataMap);
        this.insert(dataMap);
        // this.update(dataMap);
        // this.delete(dataMap);
        return ; 
    }
    public String getPkBoard(){
        return pk_boards;
    }
    public Object getList(HashMap<String, Object> dataMap) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getList'");
    }
}