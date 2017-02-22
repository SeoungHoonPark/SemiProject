package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

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
	public BBMainData getMsgListSelect(String id){
		ResultSet rs = null;
		BBMainData returnData = new BBMsgData();
		
		try{
			System.out.println("아이디값 : " + id);
			selectFromListMsgS.setString(1, id);
			rs = selectFromListMsgS.executeQuery();
			
			ArrayList list = new ArrayList();
			
			if(rs.next()){
				BBMsgData temp = new BBMsgData();
// BBSql.selectFromListMsg ==> SELECT MS_NO, MS_SENDID, MS_BNO, MS_DATE, MS_TEXT FROM B_MSG WHERE MS_RECEIVEID=?
				temp.no = rs.getInt("MS_NO");
				temp.sendId = rs.getString("MS_SENDID");
				temp.bookNo = rs.getInt("MS_BNO");
				temp.msDate = rs.getString("MS_DATE");
				temp.msTxt = rs.getString("MS_TEXT");
				System.out.println("=================dao단 받은 쪽지 처리에서 rs로 받을 값을 출력 :" + temp.toString());
				list.add(temp);
			}else{
				returnData.isSuccess = false;
			}
			
			returnData.msgFromList = list;				// 받은 쪽지 처리를 위한 리스트 변수에 담는다.	
			
		}catch (Exception e) {
			System.out.println("받은 쪽지 리스트 처리 에러 = " + e);
		}
		
		// 이쪽에서는 전달한 쪽지 처리
		try{
			selectToListMsgS.setString(1, id);
			rs = selectToListMsgS.executeQuery();
			
			ArrayList list = new ArrayList();
			
			if(rs.next()){
				BBMsgData temp = new BBMsgData();
// BBSql.selectToListMsg = SELECT MS_NO, MS_RECEIVEID, MS_BNO, MS_DATE, MS_TEXT FROM B_MSG WHERE MS_SENDID=?
				temp.no = rs.getInt("MS_NO");
				temp.receiveId = rs.getString("MS_RECEIVEID");
				temp.bookNo = rs.getInt("MS_BNO");
				temp.msDate =  rs.getString("MS_DATE");
				temp.msTxt = rs.getString("MS_TEXT");
				System.out.println("=================dao단 전달한 쪽지 처리에서 rs로 받을 값을 출력 :" + temp.toString());
				list.add(temp);				
			}else{
				returnData.isSuccess = false;
			}
			
			returnData.msgToList = list;				// 보낸 쪽지 처리를 위한 리스트 변수에 담는다.
			returnData.isSuccess = true;
		
		}catch (Exception e) {
			System.out.println("전달한 쪽지 리스트 처리 에러 = " + e);
		}finally {
			db.close(rs);
		}
		return returnData;
	}
	
	/*
	 * 전달한 쪽지 리스트 검색
	 */
//	public BBMainData getMsgToSelect(String id){
//		ResultSet rs = null;
//		BBMainData returnData = new BBMsgData();
//		
//		try{
//			selectToListMsgS.setString(1, id);
//			rs = selectToListMsgS.executeQuery();
//			
//			ArrayList list = new ArrayList();
//			
//			if(rs.next()){
//				BBMsgData temp = new BBMsgData();
//				//return Data = SELECT MS_NO, MS_RECEIVEID, MS_BNO, MS_DATE, MS_TEXT FROM B_MSG WHERE MS_SENDID=?
//				temp.no = rs.getInt("MS_NO");
//				temp.receiveId = rs.getString("MS_RECEIVEID");
//				temp.bookNo = rs.getInt("MS_BNO");
//				temp.msDate =  rs.getString("MS_DATE");
//				temp.msTxt = rs.getString("MS_TEXT");
//				
//				System.out.println("=================dao단에서 rs로 받을 값을 출력 :" + temp.toString());
//				list.add(temp);
//			}else{
//				returnData.isSuccess = false;
//			}
//			returnData.msgToList = list;				// 보낸 쪽지 처리를 위한 리스트 변수에 담는다.
//			returnData.isSuccess = true;
//			
//		}catch (Exception e) {
//			System.out.println("보낸 쪽지 리스트 처리 에러 = " + e);
//		}finally {
//			db.close(rs);
//		}
//		return returnData;
//	}
	
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
				returnData.protocol = 2101;
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
