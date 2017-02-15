package Message;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

import Main.BBMain;

public class BBMessageMain  extends JFrame {
	BBMain main;
	BBMessageViewDlg msgView;
	
	JPanel fromPanel;		// 받은 쪽지함
	JPanel toPanel; 			// 보낸 쪽지함
	JButton reflashBtn; 		// 새로고침 버튼
	JButton MessageBtn; 	// 메시지폼 호출 버튼
	JTable fromTable, toTable;						// 받은 탭쪽 테이블, 보낸 탭쪽 테이블
	DefaultTableModel fromModel, toModel;	// 받은 탭쪽 모델, 보낸 탭쪽 모델
	
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

//	public static void main(String[] args) {
//		new BBMessageMain();
//	}
	
	class TableEvent extends MouseAdapter{
		public void mousePressed(MouseEvent e){
			// row를 선택하면 쪽지 확인 창이 띄워진다.
			msgView = new BBMessageViewDlg(BBMessageMain.this);
		}
	}
}
