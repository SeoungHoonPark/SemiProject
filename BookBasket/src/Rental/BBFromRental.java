package Rental;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

/*
 * 신청한 책 목록을 화면   
 */
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Data.BBMainData;

public class BBFromRental extends JPanel{
	public BBRentalMain main;
	
	public JTable table;
	public DefaultTableModel tmodel;
	public BBFromRental(BBRentalMain m) {
		
		main = m;

		String[] fldName = {"NO", "책이름","글쓴이","책주인","예약현황","요청일"};
		tmodel = new DefaultTableModel(fldName, 0);
		table = new JTable(tmodel);
		
//		JTable table = new JTable(new DefaultTableModel(new Object[]
//				{"NO", "책이름","글쓴이","책주인","예약현황","요청일"},100));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(140);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(75);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		
		// 테이블 생성, 모델 생성, 객체배열({목록},행숫자) 생성,  제목인 라벨 생성.
		// 생성 된 테이블 JScrollPane 으로 입력. 
		
		JLabel panelLabel = new JLabel("대여신청 도서목록",JLabel.LEFT);
		JScrollPane tPane = new JScrollPane(table);

		
		JPanel centerPanel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel(new BorderLayout());
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(470, 490));
		
		topPanel.add(panelLabel);
		centerPanel.add(tPane);

		mainPanel.add(topPanel, "North");
		mainPanel.add(centerPanel, "Center");
		//mainPanel.add(topPanel,"North");
		//mainPanel.add(centerPanel, "Center");
		
		add(mainPanel, "Center");
		
	
		//	this.setSize(600, 570);
		//	this.setVisible(true);
		
		
		
	}
	
/*	public static void main(String[] args) {
		new BBFromRental();
	}*/
	
}
