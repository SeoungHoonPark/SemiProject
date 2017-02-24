package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Data.BBBookData;
import Data.BBMainData;
import Data.BBMemberData;
import Data.BBMsgData;

public class DataAccessObject {
	public BBJDBC db;
	public Connection con;
	public Statement stmt;
	public PreparedStatement selectLoginS, selectFromListMsgS, selectToListMsgS, 
							pstmt, pstmt2, selectDetailtMsgS;
	String SendId;
	
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
		selectDetailtMsgS = db.getPstmt(con, BBSql.getSQL(BBSql.selectDetailtMsg));
	}
	
	/*
	 *  등록된 책 정보 저장하기(예송)
	 */
	public boolean bookRegister(BBBookData data){
		boolean is = true;
		System.out.println("등록된 책정보: "+data.bb_name+data.bb_writer+
							data.bb_staus+data.bb_ownerid);
		String sendId = data.bb_ownerid;
		System.out.println("등록한 사람: "+sendId);
		try {
			String sql = "insert into b_book(bb_no, bb_name, bb_writer, bb_status, bb_ownerId, bb_date, bb_rcode, bb_visibleYN) values((SELECT NVL(MAX(bb_no), 0)+1 from b_book),?,?,?,?,sysdate,'10','Y')";
			pstmt = db.getPstmt(con, sql);
			pstmt.setString(1, data.bb_name);
			pstmt.setString(2, data.bb_writer);
			pstmt.setString(3, data.bb_staus);
			pstmt.setString(4, data.bb_ownerid);
			pstmt.executeQuery();
			is = true;
			} 
		catch (Exception e2) {
			is = false;
			System.out.println("등록 명령문 에러"+e2);
		}
		finally{
			db.close(pstmt);
		}
		return is;
	}
	/*
	 *  책수정하기 데이터 저장하기(예송)
	 */
	public boolean bookModify(BBBookData data){
		boolean is = true;
		Integer Bno = (int)data.bb_no; 
		System.out.println("수정된 책정보: "+data.bb_name+data.bb_writer+data.bb_staus);
		try {
			String sql = "update b_book set bb_name=?, bb_writer=?, bb_status=? where bb_no =?";
			pstmt = db.getPstmt(con, sql);
			pstmt.setString(1, data.bb_name);
			pstmt.setString(2, data.bb_writer);
			pstmt.setString(3, data.bb_staus);
			pstmt.setInt(4, Bno);
			pstmt.executeQuery();
			is = true;
			} 
		catch (Exception e2) {
			is = false;
			System.out.println("수정 명령문 에러"+e2);
		}
		finally{
				db.close(pstmt);
		}
		return is;
	}
	/*
	 *  책 삭제하기(예송)
	 */
	public boolean bookDelete(BBBookData data){
		boolean is = true;
		Integer Bno = (int)data.bb_no;
		System.out.println("넘어온 책번호(삭제): "+Bno);
		try {
			String sql = "update b_book set bb_visibleYN='N' where bb_no =?";
			pstmt = db.getPstmt(con, sql);
			pstmt.setInt(1, Bno);
			pstmt.executeQuery();
			is = true;
			} 
		catch (Exception e2) {
			is = false;
			System.out.println("수정 명령문 에러"+e2);
		}
		finally{
				db.close(pstmt);
		}
		return is;
	}
	
	/*
	 *  예약신청 메시지 보내기(예송)
	 */
	public boolean MessageInsertUser(BBMsgData data, BBBookData data1){
		boolean is = true;
		String sendId = data.sendId;
		System.out.println("보내는 사람: "+sendId);
		Integer Bno = (int)data1.bb_no;
		System.out.println("넘어온 책번호(예약): "+Bno);
		System.out.println("쪽지정보: "+ sendId+"님이 "+data.receiveId+"님에게 "+ data.msTxt);
		try {
			String sql = "insert into b_msg(ms_no, ms_sendId, ms_receiveId, ms_date, ms_text, ms_check) values((SELECT NVL(MAX(ms_no), 0)+1 from b_msg),?,?,sysdate,?,'N')";
			pstmt = db.getPstmt(con, sql);
			pstmt.setString(1, sendId);
			pstmt.setString(2, data.receiveId);
			pstmt.setString(3, data.msTxt);
			pstmt.executeQuery();
			
			// 메시지 보내면 예약상태코드 변경(10->20)
			String sql2= "update b_book set bb_rcode='20' where bb_no=?";
			pstmt2 = db.getPstmt(con, sql2);
			pstmt2.setInt(1, Bno);
			pstmt2.executeQuery();
			is = true;
			} 
		catch (Exception e2) {
			is = false;
			System.out.println("예약신청 명령문 에러: "+e2);
		}
		finally{
				db.close(pstmt2);
				db.close(pstmt);
		}
		return is;
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
			BBMsgData temp = null;
			if(rs.next()){
				temp = new BBMsgData();
// BBSql.selectFromListMsg ==> SELECT MS_NO, MS_SENDID, MS_DATE, MS_TEXT FROM B_MSG WHERE MS_RECEIVEID=?
				temp.no = rs.getInt("MS_NO");
				temp.sendId = rs.getString("MS_SENDID");
				//temp.bookNo = rs.getInt("MS_BNO");
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
			BBMsgData temp = null;
			if(rs.next()){
				temp = new BBMsgData();
// BBSql.selectToListMsg = SELECT MS_NO, MS_RECEIVEID, MS_BNO, MS_DATE, MS_TEXT FROM B_MSG WHERE MS_SENDID=?
				temp.no = rs.getInt("MS_NO");
				temp.receiveId = rs.getString("MS_RECEIVEID");
//				temp.bookNo = rs.getInt("MS_BNO");
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
	 * 쪽지 상세보기
	 */	
	public BBMainData getMsgDetail(int no){
		ResultSet rs = null;
		BBMainData returnData = new BBMsgData();
		returnData.isSuccess = false;
		try{
			selectDetailtMsgS.setInt(1, no);
			rs = selectDetailtMsgS.executeQuery();
			BBMsgData temp = new BBMsgData();
// BBSql.selectDetailtMsgS = SELECT MS_NO, MS_SENDID, MS_RECEIVEID, MS_BNO, MS_DATE, MS_TEXT FROM B_MSG WHERE MS_NO=?
			temp.no = rs.getInt("MS_NO");			
			temp.sendId = rs.getString("MS_SENDID");
			temp.receiveId= rs.getString("MS_RECEIVEID");
//			temp.bookNo = rs.getInt("MS_BNO");
			temp.msDate = rs.getString("MS_DATE");
			temp.msTxt = rs.getString("MS_TEXT");
			
			returnData.isSuccess = true;
			returnData.msgData = temp;
			
		}catch (Exception e) {
			returnData.isSuccess = false;
			System.out.println("쪽지 상세보기 처리 에러 = " + e);
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
