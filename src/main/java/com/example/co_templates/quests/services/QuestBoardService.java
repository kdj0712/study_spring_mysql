package com.example.co_templates.quests.services;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.co_templates.daos.ShareDao;
import com.example.co_templates.utils.Commons;
import com.example.co_templates.utils.Paginations;
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

    public ArrayList<HashMap<String, Object>> selectMany(HashMap<String,Object> dataMap){
        String sqlMapId = "board.selectBySearch";
        Object list = shareDao.getList(sqlMapId, dataMap);
        return (ArrayList<HashMap<String, Object>>) list;
    }
    public Object selectTotal(Map dataMap) {
        String sqlMapId = "board.selectTotal";
        Object result = shareDao.getOne(sqlMapId, dataMap);
        return result;
    }
    public Object deleteWithIn(Map dataMap){
        String sqlMapID = "board.deletewithin";
        Object count = shareDao.delete(sqlMapID, dataMap);
        return count;
    }

    public Map selectSearchWithPagination(Map dataMap) {
        String deleteIdsStr = (String) dataMap.get("deleteIds");
        if (deleteIdsStr != null && !deleteIdsStr.isEmpty()) {
            // deleteIds를 List<String> 타입으로 안전하게 캐스팅
            List<String> deleteIds = Arrays.asList(deleteIdsStr.split(","));
    
            
            for (String pkBoards : deleteIds) {
                // VISITORS 테이블에서 해당 PK_BOARDS를 사용하는 레코드가 있는지 확인
                int count = (Integer) shareDao.getOne("board.checkVisitorExists", pkBoards);
                String sqlMapId;
                if (count > 0) {
                    // VISITORS와 BOARDS에서 PK_BOARDS를 사용하는 레코드 삭제
                    sqlMapId = "board.deleteVisitorAndBoard";
                } else {
                    // BOARDS에서만 PK_BOARDS를 사용하는 레코드 삭제
                    sqlMapId = "board.deleteBoardOnly";
                }
                // 삭제 작업 수행
                shareDao.delete(sqlMapId, pkBoards);
            }
        }  
        // 페이지 형성 위한 계산
        int totalCount = (int) this.selectTotal(dataMap);
        int currentPage = 1;
        if(dataMap.get("currentPage") != null) {
            currentPage = Integer.parseInt((String)dataMap.get("currentPage"));    // from client in param
        }
        Paginations paginations = new Paginations(totalCount, currentPage);
        HashMap result = new HashMap<>();
        result.put("paginations", paginations); // 페이지에 대한 정보
        // page record 수
        String sqlMapId = "board.selectSearchWithPagination";
        dataMap.put("nextPage", paginations.getNextPage());
        dataMap.put("previousPage", paginations.getPreviousPage());
        dataMap.put("pageScale", paginations.getPageScale());
        dataMap.put("pageBegin", paginations.getPageBegin());
        result.put("resultList", shareDao.getList(sqlMapId, dataMap)); // 표현된 레코드 정보
        return result;
    }

    public HashMap<String, Object> selectOne(HashMap<String,Object> dataMap,String PK_BOARDS){
        String sqlMapId = "board.selectByUID";
        dataMap.put("PK_BOARDS",PK_BOARDS);
        Object one = shareDao.getOne(sqlMapId, dataMap);
        return (HashMap<String, Object>)one;
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
    

    public Object deleteMultipleBoards(HashMap<String, Object> dataMap, List<String> deleteIds) {
        // 여러 PK_BOARDS 값에 대한 삭제 로직을 수행할 SQL 매퍼 ID 지정
        String sqlMapId = "board.deleteMultipleBoards";
        // 삭제할 PK_BOARDS 값들을 dataMap에 추가
        dataMap.put("deleteIds", deleteIds);
        
        // 삭제 작업 수행
        Object deleteResult = shareDao.delete(sqlMapId, dataMap);
        return deleteResult;
    }
    
    public void callDao(HashMap<String,Object> dataMap){
        // this.selectMany(dataMap);
        // this.selectOne(dataMap);
        // this.insert(dataMap);
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