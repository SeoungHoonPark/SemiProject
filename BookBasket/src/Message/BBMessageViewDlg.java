package Message;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/*
 * 	메시지 내용 확인 화면
 * 
 * 예약 신청하기 화면 (예송씨 ppt 5p) 화면과 비슷하거나 동일하게 구성
 */
public class BBMessageViewDlg  extends JFrame {
	BBMessageMain main;
	
	JTextField filed, subject;
	JTextArea area;
	
	public BBMessageViewDlg(BBMessageMain m) {
		main = m;
	}
	
	public BBMessageViewDlg() {
		// 메시지 확인(dialog 화면) 좌측 화면 구성
		JLabel filedLabel= new JLabel("보낸사람");
		JLabel rentSubjectLabel= new JLabel("예약희망 도서");
		JLabel areaLabel= new JLabel("내용");
		
		filed = new JTextField();
		filed.setEnabled(false);
		subject = new JTextField();
		subject.setEnabled(false);
		area = new JTextArea();
		area.setEditable(false);
		JScrollPane sp = new JScrollPane(area);

		JPanel p1 = new JPanel(new BorderLayout(0,2));
		p1.add("West", filedLabel);
		p1.add("Center", filed);
		
		JPanel p2 =new JPanel(new BorderLayout(0, 2));
		p2.add("West", rentSubjectLabel);
		p2.add("Center", subject);
		
		JPanel p3 = new JPanel(new BorderLayout(0, 2));
		p3.add("North", p1);
		p3.add("South", p2);
		
		JPanel p4 = new JPanel(new BorderLayout(0, 2));
		p4.add("West", areaLabel);
		p4.add("Center", sp);
		
		this.add("North", p3);
		this.add("Center", p4);
		
		this.setSize(400, 300);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new BBMessageViewDlg();
	}
}
