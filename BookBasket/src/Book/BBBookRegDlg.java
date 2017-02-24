package Book;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import Data.BBMainData;
import javax.swing.*;

import Data.BBBookData;
import Main.BBMain;
/*
 * 책등록 화면
 * 필요한 항목: 도서명, 저자, 출판사, 구입날짜, 책상태, 등록&취소버튼
 */
public class BBBookRegDlg  extends JPanel {
	BBMain main;
	JTextField textF[];
	ButtonGroup bg;
	JRadioButton sang, jung, ha;
	PreparedStatement pstmt;
	ResultSet rs;
	public BBBookRegDlg(BBMain m) {
		main = m;

		setLayout(null);
		setUIFont(new javax.swing.plaf.FontUIResource("고딕", Font.PLAIN, 12));
		// 라디오버튼 생성
		bg = new ButtonGroup();
		sang = new JRadioButton("상");
		jung = new JRadioButton("중");
		ha = new JRadioButton("하");
		// 라벨 생성
		JLabel Reg_label[] = new JLabel[3];
		 Reg_label[0] = new JLabel("도서명");
		 Reg_label[1] = new JLabel("저  자");
		 Reg_label[2] = new JLabel("책상태");
		// 텍스트필드 생성
		textF = new JTextField[2]; 		
		textF[0] = new JTextField(20);
		textF[1] = new JTextField(20);

		//라디오버튼 묶기
		bg.add(sang);
		bg.add(jung);
		bg.add(ha);
		
		JPanel p0 = new JPanel();
		p0.add(sang);
		p0.add(jung);
		p0.add(ha);

		//라벨 묶기
		JPanel p1 = new JPanel(new GridLayout(3,1,10,10));
		p1.add(Reg_label[0]);
		p1.add(Reg_label[1]);
		p1.add(Reg_label[2]);
		p1.setBounds(130, 120, 100, 150);
		
		//텍스트필드 묶기
		JPanel p3 = new JPanel(new GridLayout(3,1,10,10));
		p3.add(textF[0]);
		p3.add(textF[1]);
		p3.add(p0);
		p3.setBounds(230, 120, 200, 150);
		
		// 버튼 묶기
		JButton registerB = new JButton("등록");
		registerB.addActionListener(new BtnEvent());
		JButton cancelB = new JButton("취소");
		
		JPanel p4 = new JPanel(new GridLayout(1,2,30,10));
		p4.add(registerB);
		p4.add(cancelB);
		p4.setBounds(190,350,200,30);
		
		// 타이틀 설정
		JLabel title = new JLabel("* 내가 보유한 책 등록하기");
		title.setBounds(130, 20, 200, 50);

		add(title);
		add(p1);
		add(p3);
		add(p4);
		
//		setSize(600,570);
//		setVisible(false);
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
	// '등록' 버튼 이벤트 클래스 
	class BtnEvent implements ActionListener {
		public void actionPerformed(ActionEvent e){
			// 사용자가 입력한 텍스트 알아내기
			String Bname = textF[0].getText();
			String Bwriter = textF[1].getText();
			String Bid = main.id;
			System.out.println(Bid);
			System.out.println(Bname+" "+Bwriter+" ");
			
			// 라디오버튼 값 알아내기
			String status = "";
			if(sang.isSelected() == true) {
				status = "상";
			}
			else if(jung.isSelected() == true) {
				status = "중";
			}
			else if(ha.isSelected() == true) {
				status = "하";
			}
			System.out.println(status);
			
			//텍스트 널값 체크
			if(Bname==null || Bname.length()==0){
				JOptionPane.showMessageDialog(null, "도서명을 입력하세요.");
			}
			else if(Bwriter==null || Bwriter.length()==0){
				JOptionPane.showMessageDialog(null, "저자명을 입력하세요.");
			}
			else if(status==null || status.length()==0){
				JOptionPane.showMessageDialog(null, "책상태를 선택하세요.");
			}
			// 서버에 보내기 위한 데이터 묶기
			BBMainData mainD = new BBMainData();
			BBBookData DataB = new BBBookData();
			mainD.protocol = 1202;
			DataB.bb_name = Bname;
			DataB.bb_writer = Bwriter;
			DataB.bb_ownerid = Bid;
			DataB.bb_staus = status;
			mainD.bookData = DataB;
			System.out.println("서버로 보내는 데이터 : " + DataB);
			
			// 서버에 보내기
			try {
				main.oout.writeObject(mainD);
				
			} catch (Exception e2) {}
			
			textF[0].setText("");
			textF[1].setText("");
			
			main.bookSearchMain.searchAllProc(); // 등록하자마 
			
		}
		}
//	public static void main(String[] args) {
//		new BBBookRegDlg();
//	}
}
