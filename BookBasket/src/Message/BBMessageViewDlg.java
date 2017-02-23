package Message;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.*;
/*
 * 	메시지 내용 확인 화면
 * 
 * 예약 신청하기 화면 (예송씨 ppt 5p) 화면과 비슷하거나 동일하게 구성
 */
public class BBMessageViewDlg  extends JFrame {
	BBMessageMain main;
	
	JTextField filed, subject;
	JTextArea area;
	
//	public BBMessageViewDlg(){};
	public BBMessageViewDlg(BBMessageMain m) {
		main = m;
/*	}
	
	public BBMessageViewDlg() {*/
		// 메시지 확인(dialog 화면) 좌측 화면 구성
		JLabel filedLabel= new JLabel( main.whose + " : ");
		JLabel rentSubjectLabel= new JLabel("예약 희망 도서 : ");
		JLabel areaLabel= new JLabel("", JLabel.CENTER);
		areaLabel.setAlignmentX(BOTTOM_ALIGNMENT);
		areaLabel.setPreferredSize(new Dimension(100, 5));
		
		filed = new JTextField();
		filed.setEnabled(false);
		subject = new JTextField();
		subject.setEnabled(false);
		area = new JTextArea();
		area.setEditable(false);
		JScrollPane sp = new JScrollPane(area);
		TitledBorder msgBorder = new TitledBorder(null,"메세지 내용",TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
		sp.setBorder(msgBorder);
//		sp.setBorder(new TitledBorder("메세지 내용"));

		JPanel p1 = new JPanel(new BorderLayout(0,2));
		p1.add("West", filedLabel);
		p1.add("Center", filed);
		
		JPanel p2 =new JPanel(new BorderLayout(0, 2));
		p2.add("West", rentSubjectLabel);
		p2.add("Center", subject);
		
		JPanel p3 = new JPanel(new BorderLayout(0, 2));
		p3.add("North", p1);
		p3.add("South", p2);
		
		JPanel p5 = new JPanel(new BorderLayout());
		JPanel blank1 = new JPanel();
		blank1.setPreferredSize(new Dimension(10, 15));
		JPanel blank2 = new JPanel();
		blank2.setPreferredSize(new Dimension(10, 15));
		JPanel blankT = new JPanel();
		blankT.setPreferredSize(new Dimension(400, 7));
		
		p5.add(blank1, "West");
		p5.add(blank2, "East");
		p5.add(blankT, "North");
		p5.add(p3, "Center");
		
		JPanel p4 = new JPanel(new BorderLayout(0, 2));
		p4.add("North", areaLabel);
		p4.add("Center", sp);
		
		this.add("North", p5);
		this.add("Center", p4);
		
		this.setSize(400, 300);
		this.setVisible(true);
		
		Dimension frameSize = getSize();
		Dimension winSize = Toolkit.getDefaultToolkit().getScreenSize();
		int locW = (winSize.width - frameSize.width)/2;
		int locH = (winSize.height - frameSize.height)/2;
		setLocation(locW, locH);
	}
	
	public void dataSetting(){
		// 이쪽에서 data셋팅
	}
	
//	public static void main(String[] args) {
//		new BBMessageViewDlg();
//	}
}
