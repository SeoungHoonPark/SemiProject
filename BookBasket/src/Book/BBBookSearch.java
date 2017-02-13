package Book;
/*
 * 	책검색 화면
 */
import	javax.swing.*;
import	javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import Main.*;
public class BBBookSearch extends JPanel {
	BBMain main;
	
	JButton rsvtB, bEditB, bDeleteB, psblSrchB, ttlSrchB, myBookB, bNameSrchB;
	JTable bSrchTable;
	public BBBookSearch(BBMain m) {
//		main = m ;
		
		setLayout(new BorderLayout());
		
		JPanel sideP = new JPanel();	// 왼쪽 버튼이 들어갈 패널
		JPanel sideBtnP = new JPanel(new GridLayout(3,1));
		sideP.setPreferredSize(new Dimension(90,570));
		sideBtnP.setPreferredSize(new Dimension(85,90));	// 왼쪽 버튼을 넣을 패널 
		JPanel centerMainP = new JPanel(new BorderLayout());	// 왼쪽 전체 패널
		JPanel centerBtnP = new JPanel();	// 테이블 밑에 들어갈 패널 
		JPanel sideBlankP = new JPanel();	// 디자인을 위한 빈 패널
		sideBlankP.setPreferredSize(new Dimension(5, 570));
		
		// 버튼을 만들어준다.
		rsvtB = new JButton("대출 예약");
		bEditB = new JButton("책 수정");
		bDeleteB = new JButton("책 삭제");
		psblSrchB = new JButton("대출가능 검색");
		ttlSrchB = new JButton("전체가능 검색");
		myBookB = new JButton("내책 검색");
		bNameSrchB = new JButton("책이름 검색");
		
		// 검색화면 JTable 을 만들어주자.
		String[] fldName = {"no", "저 자", "소유자", "책상태", "예약 현황", "등 록 일"};
		DefaultTableModel tmodel = new DefaultTableModel(fldName, 0);
		bSrchTable = new JTable(tmodel);
		JScrollPane tableScrlP = new JScrollPane(bSrchTable);	// 스크롤패인에 넣어준다.
//		bSrchTable.setDragEnabled(false);
//		bSrchTable.setEnabled(true);
//		bSrchTable.getTableHeader().setAlignmentY(SwingConstants.CENTER);
		// 컬럼 사이즈 조정...
		bSrchTable.getColumn("no").setPreferredWidth(3);
		bSrchTable.getColumn("저 자").setPreferredWidth(20);
		bSrchTable.getColumn("소유자").setPreferredWidth(20);
//		bSrchTable.getColumn("책상태").setPreferredWidth(30);
//		bSrchTable.getColumn("예약 현황").setPreferredWidth(15);
//		bSrchTable.getColumn("등 록 일").setPreferredWidth(50);
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		celAlignCenter.setBackground(Color.decode("#2cb1ff"));
//		celAlignCenter.setBorder(new LineBorder(Color.BLACK));
		bSrchTable.getTableHeader().setDefaultRenderer(celAlignCenter);
		bSrchTable.setGridColor(Color.gray);
//		TableColumnModel tblClmModel = bSrchTable.getColumnModel();
//		for (int i = 0 ; i < tblClmModel.getColumnCount(); i++) {
//			tblClmModel.getColumn(i).setCellRenderer(celAlignCenter);
//		}
		String[] st1 = {"01", "홍길동", "박아지", "중상", "가능", "2017/02/12"};
//		bSrchTable.(st1, 1);
		
		
		sideBtnP.add(rsvtB);
		sideBtnP.add(bEditB);
		sideBtnP.add(bDeleteB);
		
		sideP.add(sideBtnP);
		
		centerBtnP.add(psblSrchB);
		centerBtnP.add(ttlSrchB);
		centerBtnP.add(myBookB);
		centerBtnP.add(bNameSrchB);
		
		centerMainP.add(sideBlankP, "East");
		centerMainP.add(tableScrlP, "Center");
		centerMainP.add(centerBtnP, "South");
		
		add(sideP, "West");
		add(centerMainP, "Center");
		
//		JFrame f = new JFrame();
//		f.add(this);
//		f.setSize(600, 570);
//		f.setVisible(true);
//		f.pack();
	}
	
//	public static void main(String[] args){
//		new BBBookSearch();
//	}

}
