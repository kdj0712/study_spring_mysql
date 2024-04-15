package com.example.co_templates.quests.services;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.co_templates.daos.ShareDao;
import com.example.co_templates.utils.Commons;
@Service
public class QuestVisitorsService {
    @Autowired
    ShareDao shareDao;
    private Commons commons;
    private String pk_visitors;
    @Autowired
    public void setCommons(Commons commons) {
        this.commons = commons;
    }
    private QuestBoardService questBoardService = new QuestBoardService();
    @Autowired
    public void AnotherService(QuestBoardService questBoardService) {
        this.questBoardService = questBoardService;
    }
    public Object selectMany(HashMap<String,Object> dataMap){
        String sqlMapId = "visitor.selectBySearch";
        Object list = shareDao.getList(sqlMapId, dataMap);
        return list;
    }
    public Object selectOne(HashMap<String,Object> dataMap){
        String sqlMapId = "visitor.selectByUID";
        dataMap.put("PK_VISITORS","visitor-103");
        Object one = shareDao.getOne(sqlMapId, dataMap);
        return one;
    }
    public Object insert(HashMap<String,Object> dataMap){
        pk_visitors = commons.getUniqueSequence();
        String sqlMapId = "visitor.insert";
        // (`PK_VISITORS`, `WRITER_ID`, `PK_BOARDS`) 
        dataMap.put("PK_VISITORS", pk_visitors);
        Object insert = shareDao.insert(sqlMapId, dataMap);
        return insert;
    }
    public Object update(HashMap<String,Object> dataMap){
        String sqlMapId = "visitor.update";
        String pk_boards = questBoardService.getPkBoard();
        dataMap.put("WRITER_ID", "user-16");
        dataMap.put("PK_BOARDS", pk_boards);
        Object update = shareDao.update(sqlMapId, dataMap);
        return update;
    }
    public Object delete(HashMap<String,Object> dataMap,String PK_VISITORS){
        String sqlMapId = "visitor.delete";
        dataMap.put("PK_VISITORS", PK_VISITORS);
        Object delete = shareDao.delete(sqlMapId, dataMap);
        return delete;
    }
    // public void callDao(HashMap<String,Object> dataMap){
    //     this.selectMany(dataMap);
    //     this.selectOne(dataMap);
    //     this.insert(dataMap);
    //     this.update(dataMap);
    //     this.delete(dataMap);
    //     return ; 
    // }
}