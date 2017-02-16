package Rental;

/*
 * 	신청 받은 책 목록 화면   
 */
import javax.swing.*;  
import javax.swing.table.*;
import java.awt.*;

public class BBToRental extends JPanel {
	BBRentalMain main;
	
	JTable table;
	
	public BBToRental(BBRentalMain m) {
		main = m;
		
		table = new JTable(new DefaultTableModel(new Object[]{"NO", "책이름","신청자","예약현황","요청일"},100));
		JLabel panelLabel = new JLabel("    신청받은 책");
		JScrollPane tPane = new JScrollPane(table);
		
		// 테이블 생성, 모델 생성, 객체배열({목록},행숫자) 생성,  제목인 라벨 생성.
		// 생성 된 테이블 JScrollPane 으로 입력. 
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(140);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(75);
		table.getColumnModel().getColumn(4).setPreferredWidth(75);

		
		// 테이블 내용 자동너비조절 OFF, 컬럼마다 임의로 너비지정.
		
		
		JRadioButton beingReturned 		= new JRadioButton("반납 중");
		JRadioButton available			= new JRadioButton("대출가능");

		JRadioButton standBy			= new JRadioButton("대기");
		JRadioButton reserveStandBy		= new JRadioButton("예약중");
		
		ButtonGroup btnGroup		= new ButtonGroup();
		JButton modifyBtn		= new JButton("수정하기");
		JButton refreshBtn 		= new JButton("새로고침");
		
		btnGroup.add(beingReturned);
		btnGroup.add(available);
		btnGroup.add(standBy);
		btnGroup.add(reserveStandBy);
		
		JPanel radioPanel = new JPanel(new GridLayout(5,1));
		
		radioPanel.add(available);
		radioPanel.add(standBy);
		radioPanel.add(reserveStandBy);
		radioPanel.add(beingReturned);
		radioPanel.add(modifyBtn);
		
		JPanel btnPanel1 = new JPanel(new BorderLayout());
		
		//btnPanel1.add(modifyBtn);
		
		JPanel btnPanel2 = new JPanel(new BorderLayout());
		
		btnPanel2.add(refreshBtn);
		
		JPanel controlPanel = new JPanel(new BorderLayout());
		//라디오버튼 생성 및 그룹화.

		controlPanel.add(radioPanel,"North");
		controlPanel.add(btnPanel1,"Center");		
		controlPanel.add(btnPanel2, "South");
		
//		reserved.setText("예약 중");
//		reserved.setVerticalTextPosition(JRadioButton.BOTTOM);
//		reserved.setHorizontalTextPosition(JRadioButton.CENTER);
		
		// 버튼 및 버튼 패널 생성. 위는 라디오버튼 글자 방향설정 코드.

	//	buttonPanel.setLayout(null);
	//	reserved.setBounds(10, 0, 90, 15);
	//	checkedOut.setBounds(10, 20, 90, 15);
	//	available.setBounds(10, 40, 90, 15); 
		
		// 버튼패널 레이아웃 null 설정 후 임의로 라디오버튼 위치설정
		
	//	modifyBtn.setBounds(5, 100, 90, 25);
	//	refreshBtn.setBounds(5, 420, 90, 25);
		
		// 위와 동일작업 수행.
		
		
		// 버튼패널에 입력
		
	//	this.setLayout(null);
	//	panelLabel.setBounds(0, 0, 600, 25);
	//	buttonPanel.setBounds(0,50,105,500);
	//	tPane.setBounds(100,25,400,480);
		
		// 다른 패널에서 레이아웃이 있는 상태에서 setLayOut null설정불가. 다른 패널들도 모두 임의로 설정.
		
			
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(470, 490));
		
		mainPanel.add(panelLabel,"North");
		mainPanel.add(tPane,"Center");
		mainPanel.add(controlPanel,"West");
		
		add(mainPanel,"Center");
		
//		this.setSize(600,570);
//		this.setVisible(true);
		
	}
/*	public static void main(String[] args) {
		new BBToRental();
		
	}*/
	
}
