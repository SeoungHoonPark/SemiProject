package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Data.BBMainData;
import Data.BBMemberData;
import Data.BBMsgData;

public class DataAccessObject {
	public BBJDBC db;
	public Connection con;
	public Statement stmt;
	public PreparedStatement selectLoginS, selectFromListMsgS, selectToListMsgS;
	
	public DataAccessObject() {
		db = new BBJDBC();
		con = db.getCon();
		stmt = db.getStmt(con	);
		
		preStatement();	// pre... 스테이트먼트를 지정해준다
	}
	
	void preStatement(){
		selectLoginS	= db.getPstmt(con, BBSql.getSQL(BBSql.selectLogin));
		selectFromListMsgS = db.getPstmt(con, BBSql.getSQL(BBSql.selectFromListMsg));
		selectToListMsgS = db.getPstmt(con, BBSql.getSQL(BBSql.selectToListMst));
	}
	
	/*
	 * 받은 쪽지 리스트 검색
	 */
	public BBMainData getMsgFromSelect(BBMsgData data){
		ResultSet rs = null;
		BBMainData returnData = new BBMsgData();
		
		try{
			selectFromListMsgS.setString(1, data.sendId);
			rs = selectFromListMsgS.executeQuery();
			
			ArrayList list = new ArrayList();
			
			if(rs.next()){
				BBMsgData temp = new BBMsgData();
				temp.no = rs.getInt("MS_NO");
				temp.sendId = rs.getString("MS_SENDID");
				temp.sendId = rs.getString("MS_RECEIVEID");
				temp.bookNo = rs.getInt("MS_BNO");
				temp.msDate = rs.getString("MS_DATE");
				temp.msTxt = rs.getString("MS_TEXT");
				temp.check = rs.getString("MS_CHECK");	
				list.add(temp);
			}else{
				returnData.isSuccess = false;
			}
			returnData.msgFromList = list;				// 받은 쪽지 처리를 위한 리스트 변수에 담는다.
			returnData.isSuccess = true;
			
		}catch (Exception e) {
			System.out.println("받은 쪽지 리스트 처리 에러 = " + e);
		}finally {
			db.close(rs);
		}
		return returnData;
	}
	
	/*
	 * 전달한 쪽지 리스트 검색
	 */
	public BBMainData getMsgToSelect(BBMsgData data){
		ResultSet rs = null;
		BBMainData returnData = new BBMsgData();
		
		try{
			selectFromListMsgS.setString(1, data.sendId);
			rs = selectFromListMsgS.executeQuery();
			
			ArrayList list = new ArrayList();
			
			if(rs.next()){
				BBMsgData temp = new BBMsgData();
				temp.no = rs.getInt("MS_NO");
				temp.sendId = rs.getString("MS_SENDID");
				temp.sendId = rs.getString("MS_RECEIVEID");
				temp.bookNo = rs.getInt("MS_BNO");
				temp.msDate = rs.getString("MS_DATE");
				temp.msTxt = rs.getString("MS_TEXT");
				temp.check = rs.getString("MS_CHECK");	
				list.add(temp);
			}else{
				returnData.isSuccess = false;
			}
			returnData.msgFromList = list;				// 받은 쪽지 처리를 위한 리스트 변수에 담는다.
			returnData.isSuccess = true;
			
		}catch (Exception e) {
			System.out.println("받은 쪽지 리스트 처리 에러 = " + e);
		}finally {
			db.close(rs);
		}
		return returnData;
	}
	
	/*
	 * 로그인 처리
	 */
	public BBMainData getSelectLogin(BBMemberData data){
		ResultSet rs = null;
		BBMainData returnData = new BBMainData();
		
		try{
			selectLoginS.setString(1, data.id);
			selectLoginS.setString(2, data.pw);
			rs = selectLoginS.executeQuery();
			
			if(rs.next()){
				BBMemberData temp = new BBMemberData();
				temp.no = rs.getInt("BM_NO");
				temp.id = rs.getString("BM_ID");
				temp.name = rs.getString("BM_NAME");
				temp.phone = rs.getString("BM_PHONE");
				returnData.isSuccess = true;
				returnData.memberData = temp;
			}else{
				returnData.isSuccess = false;
			}
		}catch (Exception e) {
			System.out.println("로그인 처리 에러 = " + e);
		}finally {
			db.close(rs);
		}
		return returnData;
	}
	
}
