package Message;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
/*
 * 	메시지 메인 화면 구성
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Data.BBMainData;
import Data.BBMsgData;
import Main.BBMain;

public class BBMessageMain  extends JFrame {
	public BBMain main;
	public BBMessageViewDlg msgView;
	
	JPanel fromPanel;		// 받은 쪽지함
	JPanel toPanel; 			// 보낸 쪽지함
	JButton reflashBtn; 		// 새로고침 버튼
	JButton MessageBtn; 	// 메시지폼 호출 버튼
	JTable fromTable, toTable;						// 받은 탭쪽 테이블, 보낸 탭쪽 테이블
	DefaultTableModel fromModel, toModel;	// 받은 탭쪽 모델, 보낸 탭쪽 모델
	String whose;			// 어떤탭을 눌렀냐에 따라 팝업창에 보낸 사람/ 받은 사람 으로 구분하기위해...				
	// BookBasket메인과 상호참조를 위한 생성자
	public BBMessageMain(BBMain bbMain){
		main = bbMain;
		/*new BBMessageMain();*/
	/*}*/
	
	// 전체적인 틀(layout)을 그려주는 생성자 
	/*public BBMessageMain() {*/		

		
		this.setTitle(main.name + " 님의 메세지함");
		// 메시지 프레임창을 종료하기 위한 함수
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				/*BBMessageMain.this.*/dispose();
			}
		});
		
		// 전체 탭 구성 영역
		JTabbedPane tPane = new JTabbedPane(2);	// 탭  메인 패널
		
		fromPanel = new JPanel(new  BorderLayout());
		toPanel = new JPanel(new BorderLayout());
		
		JPanel p1 = new JPanel();
		
		reflashBtn = new JButton("새로고침");
		//MessageBtn = new JButton("메시지 쓰기");
		p1.add(reflashBtn);
		//p1.add(MessageBtn);	
		reflashBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DisplayModel1();
				DisplayModel2();
			}
		});
		//받은 쪽지함 테이블
		String[] fromTitle = {"번호", "보낸 사람", "내용", "날짜"};
		fromModel = new DefaultTableModel(fromTitle, 0);
		fromTable = new JTable( fromModel);
		fromTable.addMouseListener(new TableEvent());
		JScrollPane fromSp = new JScrollPane(fromTable);
		
		fromTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		fromTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		fromTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		fromTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		
		fromPanel.add(fromSp);
		
		//보낸 쪽지함 테이블
		String[] toTitle = {"번호", "받은 사람", "내용", "날짜"};
		
		toModel = new DefaultTableModel(toTitle, 0);
		toTable = new JTable(toModel);
		toTable.addMouseListener(new TableEvent());
		JScrollPane toSp = new JScrollPane(toTable);

		toTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		toTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		toTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		toTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		
		toPanel.add(toSp);
		
		tPane.add("받은 쪽지함", fromPanel);					// 탭중 1 .. 받은 쪽지함 리스트를 볼 수 있음
		tPane.add("보낸 쪽지함", toPanel);						// 탭중 2 .. 보낸 쪽지함 리스트를 볼 수 있음
		
		this.add("Center", tPane);
		this.add("South", p1);		
		this.setSize(600, 570);
		this.setResizable(false);
		this.setVisible(true);
		
		// 실행창 위치 설정...
				Dimension frameSize = this.getSize();
				Dimension winSize = Toolkit.getDefaultToolkit().getScreenSize();
				int locW = (winSize.width - frameSize.width)/2;
				int locH = (winSize.height - frameSize.height)/2;
				setLocation(locW, locH);
	
				System.out.println(frameSize.width+ " , " + frameSize.height);
	}
	
	int no;
	class TableEvent extends MouseAdapter{
		public void mousePressed(MouseEvent e){
			//System.out.println("e.getSource() " +e.getID());
			BBMainData data = new BBMainData();
			data.protocol = 1402;
			if(e.getID() == 501){
				whose = "쪽지 보낸 사람";
				int row = fromTable.getSelectedRow();
				if(row == -1){
					return;
				}
				no = (int) fromTable.getValueAt(row, 0);	// 받은 쪽지 탭쪽 선택한 줄의 일련번호를 얻어온다.
				
			}else{
				whose = "쪽지 받은 사람";
				int row = toTable.getSelectedRow();
				if(row == -1){
					return;
				}
				no = (int) toTable.getValueAt(row, 0);	// 보낸 쪽지 탭쪽 선택한 줄의 일련번호를 얻어온다.
			}
			data.msgData.no = no;
			try {
				main.oout.writeObject(data);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			// row를 선택하면 쪽지 확인 창이 띄워진다.
			msgView = new BBMessageViewDlg(BBMessageMain.this);
		}
	}
	
	void removeTable(){
		int frows = fromTable.getRowCount();
//		System.out.println("11111111111111111111111111 55555555555555556666666666 " + frows);
		for(int i = 0; i < frows; i++){
			fromModel.removeRow(0);
		}
//		System.out.println("11111111111111111111111111 7777777777777 8888888888888");
		int trows = toTable.getRowCount();
//		System.out.println("11111111111111111111111111 999999999999900000000000000");
		for(int i = 0; i < trows; i++){
			toModel.removeRow(0);
		}
	}
	
	/* ###########################################
	 * ######### 리시버로 부터 넘어온 데이터를 model에 출력하는 함수  #######
	 * ###########################################
	 */
	public void DisplayModel1(){
		removeTable();	// row가 쌓이는 것을 방지하기 위함
		
		Iterator	iter = main.data.msgFromList.iterator();
		while(iter.hasNext()){
			System.out.println("FromList === " +  main.data.msgFromList.toString());
			BBMsgData temp = (BBMsgData) iter.next();

				Object[] o = new Object[4];
				o[0] = temp.no;
				o[1] = temp.sendId;
				o[2] = temp.msTxt;
				o[3] = temp.msDate;
			fromModel.addRow(o);
		}
	}
	
	public void DisplayModel2(){
		Iterator	iter2 = main.data.msgToList.iterator();		
		while(iter2.hasNext()){
			System.out.println("ToList === " +  main.data.msgToList.toString());
			BBMsgData temp = (BBMsgData) iter2.next();
			
				Object[] o = new Object[4];
				o[0] = temp.no;
				o[1] = temp.receiveId;
				o[2] = temp.msTxt;
				o[3] = temp.msDate;
			toModel.addRow(o);
		}
	}
}
