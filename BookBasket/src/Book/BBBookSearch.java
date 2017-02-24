package Book;
import java.awt.BorderLayout; 
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * 	책검색 화면
 */
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Data.BBBookData;
import Data.BBMainData;
import Data.BBMemberData;
import Main.BBMain;
import SQL.MyJDBC;


public class BBBookSearch extends JPanel {
	public BBMain main;
	BBModifyDlg bookMdfDlg;
	BBRentalDlg rentalDlg;
	
	JButton rsvtB, bEditB, bDeleteB, psblSrchB, ttlSrchB, myBookB, bNameSrchB;
	public JTable bSrchTable;
	public DefaultTableModel tmodel;
	JPanel centerMainP;
	Integer BookRowNo;
	
	String bnameSearch = "";
	String idSearch	   = "";
	
	//-- 테스트용
	MyJDBC		db;
	ResultSet			rs = null;
	Connection 			conn = null;
	PreparedStatement 	pstmtTotalSearch, test;
	Statement			stmt = null;

	public BBBookSearch(BBMain m) {
		main = m ;
		
		setLayout(new BorderLayout());
		
		JPanel sideP = new JPanel();	// 왼쪽 버튼이 들어갈 패널
		JPanel sideBtnP = new JPanel(new GridLayout(3,1));
		sideP.setPreferredSize(new Dimension(110,570));
		sideBtnP.setPreferredSize(new Dimension(95,90));	// 왼쪽 버튼을 넣을 패널 
		centerMainP = new JPanel(new BorderLayout());	// 왼쪽 전체 패널
		JPanel centerBtnP = new JPanel();	// 테이블 밑에 들어갈 패널 
		JPanel sideBlankP = new JPanel();	// 디자인을 위한 빈 패널
		sideBlankP.setPreferredSize(new Dimension(5, 570));
		
		// 버튼을 만들어준다.
		rsvtB = new JButton("대출예약");
		bEditB = new JButton("책 수정");
		bDeleteB = new JButton("책 삭제");
		psblSrchB = new JButton("대출가능 검색");
		ttlSrchB = new JButton("전체 검색");
		myBookB = new JButton("내책 검색");
		bNameSrchB = new JButton("책이름 검색");
		
		// 검색화면 JTable 을 만들어주자.
//		String[] fldName = {"no", "저 자", "소유자", "책상태", "예약 현황", "등 록 일"};
		String[] fldName = {"no", "책이름", "저자", "책주인", "책상태", "예약현황", "등록일"};
		
		tmodel = new DefaultTableModel(fldName, 0);
		
		bSrchTable = new JTable(tmodel);
		JScrollPane tableScrlP = new JScrollPane(bSrchTable);	// 스크롤패인에 넣어준다.
//		bSrchTable.setDragEnabled(false);
//		bSrchTable.setEnabled(true);
//		bSrchTable.getTableHeader().setAlignmentY(SwingConstants.CENTER);
		// 컬럼 사이즈 조정...
		bSrchTable.getColumn("no").setPreferredWidth(45);
//		bSrchTable.getColumn("책이름").setPreferredWidth(20);
//		bSrchTable.getColumn("저자").setPreferredWidth(20);
		bSrchTable.getColumn("책주인").setPreferredWidth(55);
		bSrchTable.getColumn("책상태").setPreferredWidth(45);
//		bSrchTable.getColumn("예약현황").setPreferredWidth(15);
//		bSrchTable.getColumn("등록일").setPreferredWidth(50);
		
		/*
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		celAlignCenter.setBackground(Color.decode("#2cb1ff"));
		bSrchTable.getTableHeader().setDefaultRenderer(celAlignCenter);
		bSrchTable.setGridColor(Color.gray);
		*/
		
		
		sideBtnP.add(rsvtB);
		sideBtnP.add(bEditB);
		sideBtnP.add(bDeleteB);
		
		sideP.add(sideBtnP);
		
		centerBtnP.add(ttlSrchB);
		centerBtnP.add(psblSrchB);
		centerBtnP.add(bNameSrchB);
		centerBtnP.add(myBookB);
		
		BookSrchEvent evt = new BookSrchEvent();
		rsvtB.addActionListener(evt);
		bEditB.addActionListener(evt);
		bDeleteB.addActionListener(evt);
		psblSrchB.addActionListener(evt);
		ttlSrchB.addActionListener(evt);
		myBookB.addActionListener(evt);
		bNameSrchB.addActionListener(evt);
		
		centerMainP.add(sideBlankP, "East");
		centerMainP.add(tableScrlP, "Center");
		centerMainP.add(centerBtnP, "South");
		
		add(sideP, "West");
		add(centerMainP, "Center");
		
	
	}
	
	
//	public static void main(String[] args){
//		new BBBookSearch();
//	}
	
	public void deleteProc(){
		BookRowNo = bSrchTable.getSelectedRow();
//		if(BookRowNo == -1){
//			return;
//		}
		System.out.println("마우스클릭 row:"+BookRowNo);
		int Bno = (Integer)bSrchTable.getValueAt(BookRowNo, 0);
		System.out.println("삭제할 책번호 :"+Bno);
		
		BBMainData mainD = new BBMainData();
		BBBookData DataB = new BBBookData();
		mainD.protocol = 1204;
		DataB.bb_no = Bno;
		mainD.bookData = DataB;
		// 서버에 보내기
		try {
			main.oout.writeObject(mainD);
		} 
		catch (Exception e2) {}
		
		main.bookSearchMain.searchAllProc();
		
	}
	
	public void searchAllProc(){
		BBMainData Data = new BBMainData();
		
		Data.protocol = 1201;
		System.out.println("전체검색 요청프로토콜 : "+Data.protocol);
		
		try {
			main.oout.writeObject(Data);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void availableSearch(){
		BBMainData Data = new BBMainData();
		
		Data.protocol = 1205;
		System.out.println("대출가능검색 요청프로토콜 : "+Data.protocol);
		
		try{
			main.oout.writeObject(Data);
		}catch(IOException e1){
			e1.printStackTrace();
		}
	}
	
	public void myBookSearch(){
		BBMainData Data = new BBMainData();
		BBMemberData MemberData = new BBMemberData();
		
		String id = MemberData.id;
		//String id2 = Data.memberData.id;
		System.out.println(id + " ");
		//System.out.println(id2 + " ");
		
		System.out.println("아이디 값 받아오기 멤버"+idSearch);
		
		
		Data.protocol = 1206;
		System.out.println("내책검색 요청프로토콜 : "+Data.protocol);
		
		try{
			main.oout.writeObject(Data);
		}catch(IOException e1){
			e1.printStackTrace();
		}
	}
	
	public void BooknameSearch(){
		BBMainData Data = new BBMainData();
		BBBookData BookData = new BBBookData();
		
		BookData.bb_name = bnameSearch;
		
		
		Data.protocol = 1207;
		Data.bookData = BookData;
		System.out.println("내책검색 요청프로토콜 : "+Data.protocol);
//		Data.BookD.bb_name = bnameSearch;
		
		try{
			main.oout.writeObject(Data);
		}catch(IOException e1){
			e1.printStackTrace();
		}
		System.out.println("책이름 입력 값: " + bnameSearch);
		
	}
	
	
	class BookSrchEvent implements ActionListener {
		public void actionPerformed(ActionEvent e){
			String comm = (String) e.getActionCommand();
			BookRowNo = bSrchTable.getSelectedRow();
			
			if(BookRowNo == -1){
				return;
			}
			
			if ( comm.equals("대출예약")){
				rentalDlg = new BBRentalDlg(BBBookSearch.this);
				
			}
			else if ( comm.equals("책 수정")){
				bookMdfDlg = new BBModifyDlg(BBBookSearch.this);
				
			}
			else if ( comm.equals("책 삭제")){
				deleteProc();
			}
			else if ( comm.equals("대출가능 검색")){
				availableSearch();
			}
			else if ( comm.equals("전체 검색")){		
				searchAllProc();
				
			}
			else if ( comm.equals("내책 검색")){
				myBookSearch();
			}
			else if ( comm.equals("책이름 검색")){


				Icon img = null; // 이미지 아이콘 만들기...
				try {
		            img = new ImageIcon("./src/Data/icon_livro.png");
		        } catch(Exception me) {
		            me.printStackTrace();
		        }
				bnameSearch = JOptionPane.showInputDialog(main, "책 이름을 입력하세요!", "책 이름 검색", JOptionPane.QUESTION_MESSAGE);
				BooknameSearch();
			}
			
		}
	}
}
