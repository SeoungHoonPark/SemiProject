package Book;
import javax.swing.*;
import Book.BBBookSearch;
import Book.BBModifyDlg.BtnEvent;
import Data.BBBookData;
import Data.BBMsgData;
import Data.BBMainData;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
/*
 * 	책 대여 예약 화면
 *  필요한 항목: 책주인, 신청도서, 저자, 메시지, 보내기버튼
 */
public class BBRentalDlg extends JFrame {
	BBBookSearch main;
	JTextField BownerF, BnameF, BwriterF;
	JTextArea memoText;
	String Bid = main.main.data.memberData.id; // 로그인id 전역변수
	String Bo; // 책소유자id 전역변수
	PreparedStatement pstmt;
	ResultSet rs;
	
	public BBRentalDlg(BBBookSearch m) {
		main = m;
		Dimension frameSize = getSize();
		Dimension winSize = Toolkit.getDefaultToolkit().getScreenSize();
		int locW = (winSize.width - frameSize.width)/2;
		int locH = (winSize.height - frameSize.height)/2;
		setLocation(locW, locH);
		
		setLayout(null);
		setTitle("예약 메시지 보내기");
		// 폰트 설정
		setUIFont(new javax.swing.plaf.FontUIResource("고딕", Font.PLAIN, 12));
		// 라벨 생성
		JLabel Bowner_la = new JLabel("책주인");
		JLabel Bname_la = new JLabel("신청도서");
		JLabel Bwriter_la = new JLabel("저 자");
		JLabel Bmemo_la = new JLabel("메시지");
		// 텍스트필드 생성 및 disabled 설정
		BownerF = new JTextField(5);
		BownerF.setEnabled(false);
		BnameF = new JTextField(10);
		BnameF.setEnabled(false);
		BwriterF = new JTextField(10);
		BwriterF.setEnabled(false);
		memoText = new JTextArea(235,120);
		// 자동 줄바꿈
		memoText.setLineWrap(true);
		// 스크롤바 생성 및 설정
		JScrollPane sp = new JScrollPane(memoText);
		sp.setPreferredSize(new Dimension(220,95));
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		// 라벨 묶기
		JPanel p1 = new JPanel(new GridLayout(3,1,10,10));
		p1.add(Bowner_la);
		p1.add(Bname_la);
		p1.add(Bwriter_la);
		p1.setBounds(50, 20, 200, 100);
		
		//텍스트필드 묶기
		JPanel p2 = new JPanel(new GridLayout(3,1,10,10));
		p2.add(BownerF);
		p2.add(BnameF);
		p2.add(BwriterF);
		p2.setBounds(120, 20,150,100);
		
		//메시지입력칸
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p3.add(Bmemo_la);
		p3.add(sp);
		p3.setBounds(45, 130, 235, 120);
		
		// 버튼 생성
		JButton SendB = new JButton("신청하기"); 
		SendB.addActionListener(new BtnEvent());
		SendB.setBounds(115,280,100,30);
		
		add(p1);
		add(p2);
		add(p3);
		add(SendB);
		setSize(350,380);
		setVisible(true);
		
		setText();
	}
	void setText(){
		// 책리스트 테이블에서 선택된 마우스 row 알아내기
			int row = main.BookRowNo;
			if(row == -1){
				return;
			}
			System.out.println("마우스클릭 row:"+row);
			// 테이블의 선택된 내용 스트링으로 알아낸 뒤 
			String Bn = (String)main.bSrchTable.getValueAt(row, 1);
			String Bw = (String)main.bSrchTable.getValueAt(row, 2);
			Bo = (String)main.bSrchTable.getValueAt(row, 3);
			// 텍스트 필드에 출력
			BnameF.setText(Bn);
			BwriterF.setText(Bw);
			BownerF.setText(Bo);

	}
	// 전체 폰트 설정
	public static void setUIFont(javax.swing.plaf.FontUIResource f) {
	    java.util.Enumeration keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	        Object key = keys.nextElement();
	        Object value = UIManager.get(key);
	        if (value instanceof javax.swing.plaf.FontUIResource)
	            UIManager.put(key, f);
	    }
	}
	// 신청하기 버튼 클릭 이벤트
	class BtnEvent implements ActionListener {
		public void actionPerformed(ActionEvent e){
			// 사용자가 입력한 메시지 텍스트 알아내기
			String text = memoText.getText();
			System.out.println("메시지 내용:"+text);
			// 로그인한 아이디 알아내고
			//Bid = main.main.id; // 로그인 아이디(쪽지 보내는 사람)
			// 로그인한 아이디, 받는 아이디, 문자내용을 메인데이터에 보내기
			
			// 서버에 보내기 위한 데이터 묶기
			BBMainData mainD = new BBMainData();
			BBMsgData DataM = new BBMsgData();
			mainD.protocol = 1403;
			DataM.sendId = Bid;
			DataM.receiveId = Bo;
			DataM.msTxt = text;
			mainD.msgData = DataM;
			
//			// 서버에 보내기
			try {
				main.main.oout.writeObject(mainD);
			} 
			catch (Exception e2) {}
			
			memoText.setText("");
			}
		}
	
//	public static void main(String[] args) {
//		new BBRentalDlg();
//	}
}
