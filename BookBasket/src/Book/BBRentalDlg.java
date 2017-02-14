package Book;
import javax.swing.*;
import Book.BBBookSearch;
import java.awt.*;
import java.util.*;
/*
 * 	책 대여 예약 화면
 *  필요한 항목: 책주인, 신청도서, 저자, 메시지, 보내기버튼
 */
public class BBRentalDlg extends JFrame {
	BBBookSearch main;
	
	public BBRentalDlg(BBBookSearch m) {
		main = m;
		
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
		JTextField BownerF = new JTextField(5);
		BownerF.setEnabled(false);
		JTextField BnameF = new JTextField(10);
		BnameF.setEnabled(false);
		JTextField BwriterF = new JTextField(10);
		BwriterF.setEnabled(false);
		JTextArea memoText = new JTextArea(235,120);
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
		SendB.setBounds(115,280,100,30);
		
		add(p1);
		add(p2);
		add(p3);
		add(SendB);
		setSize(350,380);
		setVisible(true);
		
		// 실행창 위치 설정...
		Dimension frameSize = getSize();
		Dimension winSize = Toolkit.getDefaultToolkit().getScreenSize();
		int locW = (winSize.width - frameSize.width)/2;
		int locH = (winSize.height - frameSize.height)/2;
		setLocation(locW, locH);
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
//	public static void main(String[] args) {
//		new BBRentalDlg();
//	}
}
