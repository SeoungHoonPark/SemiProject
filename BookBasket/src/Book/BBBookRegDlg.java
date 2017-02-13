package Book;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Main.BBMain;
/*
 * 책등록 화면
 * 필요한 항목: 도서명, 저자, 출판사, 구입날짜, 책상태, 등록&취소버튼
 */
public class BBBookRegDlg /* extends JDialog */ extends JFrame {
	BBMain main;
	public BBBookRegDlg(BBMain m) {
		main = m;
		JFrame f = new JFrame();
		f.getContentPane().setBackground(Color.WHITE);
		f.setLayout(null);
		setUIFont(new javax.swing.plaf.FontUIResource("고딕", Font.PLAIN, 12));
		// 라디오버튼 생성
		ButtonGroup bg = new ButtonGroup();
		JRadioButton sang = new JRadioButton("상");
		sang.setBackground(Color.WHITE);
		JRadioButton jung = new JRadioButton("중");
		jung.setBackground(Color.WHITE);
		JRadioButton ha = new JRadioButton("하");
		ha.setBackground(Color.WHITE);
		// 라벨 생성
		JLabel Reg_label[] = new JLabel[7];
		 Reg_label[0] = new JLabel("도서명");
		 Reg_label[1] = new JLabel("저  자");
		 Reg_label[2] = new JLabel("출판사");
		 Reg_label[3] = new JLabel("구입날짜");
		 Reg_label[4] = new JLabel("년");
		 Reg_label[5] = new JLabel("월");
		 Reg_label[6] = new JLabel("책상태");
		// 텍스트필드 생성
		JTextField textF[] = new JTextField[5]; 		
		textF[0] = new JTextField(20);
		textF[1] = new JTextField(20);
		textF[2] = new JTextField(20);
		textF[3] = new JTextField(4);
		textF[4] = new JTextField(2);
		
		//라디오버튼 묶기
		bg.add(sang);
		bg.add(jung);
		bg.add(ha);
		
		JPanel p0 = new JPanel();
		p0.add(sang);
		p0.add(jung);
		p0.add(ha);
		p0.setBackground(Color.WHITE);

		// 구입년도 칸 합치기
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p2.add(textF[3]); // 연도 입력칸
		p2.add(Reg_label[4]); // "년"
		p2.add(textF[4]); // 월 입력칸
		p2.add(Reg_label[5]); // "월"
		p2.setBackground(Color.WHITE);
		
		//라벨 묶기
		JPanel p1 = new JPanel(new GridLayout(5,1,10,10));
		p1.add(Reg_label[0]);
		p1.add(Reg_label[1]);
		p1.add(Reg_label[2]);
		p1.add(Reg_label[3]);
		p1.add(Reg_label[6]);
		p1.setBounds(130, 80, 100, 200);
		p1.setBackground(Color.WHITE);
		
		//텍스트필드 묶기
		JPanel p3 = new JPanel(new GridLayout(5,1,10,10));
		p3.add(textF[0]);
		p3.add(textF[1]);
		p3.add(textF[2]);
		p3.add(p2);
		p3.add(p0);
		p3.setBounds(230, 80, 200, 200);
		p3.setBackground(Color.WHITE);
		
		// 버튼 묶기
		JButton registerB = new JButton("등록");
		registerB.setBackground(new Color(051, 204, 204));
		registerB.setBorderPainted(false);
		registerB.setForeground(Color.WHITE);
		JButton cancelB = new JButton("취소");
		cancelB.setBackground(new Color(051, 204, 204));
		cancelB.setBorderPainted(false);
		cancelB.setForeground(Color.WHITE);
		
		JPanel p4 = new JPanel(new GridLayout(1,2,30,10));
		p4.add(registerB);
		p4.add(cancelB);
		p4.setBounds(190,350,200,30);
		p4.setBackground(Color.WHITE);
		
		JLabel title = new JLabel("* 내가 보유한 책 등록하기");
		title.setBounds(130, 20, 200, 50);
		
		f.add(title);
		f.add(p1);
		f.add(p3);
		f.add(p4);
		f.setSize(600,570);
		f.setVisible(true);
		
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
//		new BBBookRegDlg();
//	}
}
