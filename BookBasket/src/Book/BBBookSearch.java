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
	BBModifyDlg bookMdfDlg;
	BBRentalDlg rentalDlg;
	
	JButton rsvtB, bEditB, bDeleteB, psblSrchB, ttlSrchB, myBookB, bNameSrchB;
	JTable bSrchTable;
	public BBBookSearch(BBMain m) {
		main = m ;
		
		setLayout(new BorderLayout());
		
		JPanel sideP = new JPanel();	// 왼쪽 버튼이 들어갈 패널
		JPanel sideBtnP = new JPanel(new GridLayout(3,1));
		sideP.setPreferredSize(new Dimension(110,570));
		sideBtnP.setPreferredSize(new Dimension(95,90));	// 왼쪽 버튼을 넣을 패널 
		JPanel centerMainP = new JPanel(new BorderLayout());	// 왼쪽 전체 패널
		JPanel centerBtnP = new JPanel();	// 테이블 밑에 들어갈 패널 
		JPanel sideBlankP = new JPanel();	// 디자인을 위한 빈 패널
		sideBlankP.setPreferredSize(new Dimension(5, 570));
		
		// 버튼을 만들어준다.
		rsvtB = new JButton("대출예약");
		bEditB = new JButton("책 수정");
		bDeleteB = new JButton("책 삭제");
		psblSrchB = new JButton("대출가능 검색");
		ttlSrchB = new JButton("전체 검색");
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
		
		/*
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		celAlignCenter.setBackground(Color.decode("#2cb1ff"));
		bSrchTable.getTableHeader().setDefaultRenderer(celAlignCenter);
		bSrchTable.setGridColor(Color.gray);
		*/
		
		
		sideBtnP.add(rsvtB);
		sideBtnP.add(bEditB);
		sideBtnP.add(bDeleteB);
		
		sideP.add(sideBtnP);
		
		centerBtnP.add(ttlSrchB);
		centerBtnP.add(psblSrchB);
		centerBtnP.add(bNameSrchB);
		centerBtnP.add(myBookB);
		
		BookSrchEvent evt = new BookSrchEvent();
		rsvtB.addActionListener(evt);
		bEditB.addActionListener(evt);
		bDeleteB.addActionListener(evt);
		psblSrchB.addActionListener(evt);
		ttlSrchB.addActionListener(evt);
		myBookB.addActionListener(evt);
		bNameSrchB.addActionListener(evt);
		
		centerMainP.add(sideBlankP, "East");
		centerMainP.add(tableScrlP, "Center");
		centerMainP.add(centerBtnP, "South");
		
		add(sideP, "West");
		add(centerMainP, "Center");
		
	}
	
//	public static void main(String[] args){
//		new BBBookSearch();
//	}
	
	public void deleteProc(){
		
	}
	
	class BookSrchEvent implements ActionListener {
		public void actionPerformed(ActionEvent e){
			String comm = (String) e.getActionCommand();

			if ( comm.equals("대출예약")){
				rentalDlg = new BBRentalDlg(BBBookSearch.this);
			}
			else if ( comm.equals("책 수정")){
				bookMdfDlg = new BBModifyDlg(BBBookSearch.this);
			}
			else if ( comm.equals("책 삭제")){
				deleteProc();
			}
			else if ( comm.equals("대출가능 검색")){
				
			}
			else if ( comm.equals("전체가능 검색")){
				
			}
			else if ( comm.equals("내책 검색")){
				
			}
			else if ( comm.equals("책이름 검색")){
				
			}
			
		}
	}
}
