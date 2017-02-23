package Book;
import Book.BBBookSearch;
import Book.BBBookRegDlg.BtnEvent;
import Data.BBBookData;
import Data.BBMainData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/*
 * 	책정보 수정 화면
 *  필요한 항목: 도서명, 저자, 출판사, 구입날짜, 책상태, 수정버튼
 */
public class BBModifyDlg extends JFrame  {
	BBBookSearch main;
	JTextField BnameF, BwriterF, BcomF, ByearF, BmonthF;
	ButtonGroup bg;
	JRadioButton sang, jung, ha;
	PreparedStatement pstmt;
	ResultSet rs;
	Integer Bno; // 책리스트에서의 책일련번호
	public BBModifyDlg(BBBookSearch m) {
		main = m;
		
		Dimension frameSize = getSize();
		Dimension winSize = Toolkit.getDefaultToolkit().getScreenSize();
		int locW = (winSize.width - frameSize.width)/2;
		int locH = (winSize.height - frameSize.height)/2;
		setLocation(locW, locH);
		
		setTitle("등록한 책 수정");
		setLayout(null);
		// 폰트 설정
		setUIFont(new javax.swing.plaf.FontUIResource("고딕", Font.PLAIN, 12));
		// 라디오버튼
		bg = new ButtonGroup();
		sang = new JRadioButton("상");
		jung = new JRadioButton("중");
		ha = new JRadioButton("하");
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
		BnameF = new JTextField(20);
		BwriterF = new JTextField(20);
		BcomF = new JTextField(20);
		ByearF = new JTextField(4);
		BmonthF = new JTextField(2);
		
		//라디오버튼 묶기
		bg.add(sang);
		bg.add(jung);
		bg.add(ha);
		JPanel p0 = new JPanel();
		p0.add(sang);
		p0.add(jung);
		p0.add(ha);
		
		//라벨 묶기
		JPanel p1 = new JPanel(new GridLayout(5,1,10,10));
		p1.add(Reg_label[0]);
		p1.add(Reg_label[1]);
		p1.add(Reg_label[2]);
		p1.add(Reg_label[3]);
		p1.add(Reg_label[6]);
		p1.setBounds(50, 30, 100, 200);
		
		// 구입년도 칸 합치기
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p2.add(ByearF); // 연도 입력칸
		p2.add(Reg_label[4]); // "년"
		p2.add(BmonthF); // 월 입력칸
		p2.add(Reg_label[5]); // "월"
		
		//텍스트필드 묶기
		JPanel p3 = new JPanel(new GridLayout(5,1,10,10));
		p3.add(BnameF);
		p3.add(BwriterF);
		p3.add(BcomF);
		p3.add(p2);
		p3.add(p0);
		p3.setBounds(120, 30,180,200);
		
		// 버튼 묶기
		JButton ModifyB = new JButton("수정하기"); 
		ModifyB.addActionListener(new BtnEvent());
		ModifyB.setBounds(115,280,100,30);

		add(p1);
		add(p3);
		add(ModifyB);
		setSize(350,380);
		setVisible(true);
		
		setText();
	}
	// 텍스트 화면에 set시키기
	void setText(){
		// 책리스트 테이블에서 선택된 마우스 row 알아내기
			int row = main.BookRowNo;
			if(row == -1){
				return;
			}
			System.out.println("마우스클릭 row:"+row);
			// 테이블의 선택된 내용 스트링으로 알아낸 뒤 
			Bno = (Integer)main.bSrchTable.getValueAt(row, 0); // 책번호
			String Bn = (String)main.bSrchTable.getValueAt(row, 1); // 책이름 
			String Bw = (String)main.bSrchTable.getValueAt(row, 2); // 저자
//			String Bc = (String)main.bSrchTable.getValueAt(row, 3); // 출판사
			String Bd = (String)main.bSrchTable.getValueAt(row, 6); // 구입연도
			String Bs = (String)main.bSrchTable.getValueAt(row, 4); // 책상태
			// 구입연월 분해
			String Byear = Bd.substring(0, 4);
			String Bmonth = Bd.substring(Bd.length()-3, Bd.length());
			System.out.println("수정하기 상태출력 테스트:"+Bs);
//			System.out.println("구입연월 나누기 테스트:"+Byear+", "+Bmonth);
			// 텍스트 필드에 출력
			BnameF.setText(Bn);
			BwriterF.setText(Bw);
//			BcomF.setText(Bc);
			ByearF.setText(Byear);
			BmonthF.setText(Bmonth);
			//라디오버튼 값 설정
			if(Bs.equals("상")){
				sang.setSelected(true);
			}
			else if(Bs.equals("중")){
				jung.setSelected(true);
			}
			else if(Bs.equals("하")){
				ha.setSelected(true);
			}
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
	// 수정하기 버튼 클릭 이벤트
	class BtnEvent implements ActionListener {
		public void actionPerformed(ActionEvent e){
			// 사용자가 입력한 텍스트 알아내기
			
				String Bname = BnameF.getText();
				String Bwriter = BwriterF.getText();
				String Bcom = BcomF.getText();
				String Byear = ByearF.getText();
				String Bmonth = BmonthF.getText();
				String Bdate = (String)(Byear+Bmonth);
				System.out.println(Bname+" "+Bwriter+" "+Bcom+" "+Bdate+" ");
				
				// 라디오버튼 값 알아내기
				String status = "";
				if(sang.isSelected() == true) {
					status = "상";
				}
				else if(jung.isSelected() == true) {
					status = "중";
				}
				else if(ha.isSelected() == true) {
					status = "하";
				}
				System.out.println(status);
				
				//텍스트 널값 체크
				if(Bname==null || Bname.length()==0){
					JOptionPane.showMessageDialog(null, "도서명을 입력하세요.");
				}
				else if(Bwriter==null || Bwriter.length()==0){
					JOptionPane.showMessageDialog(null, "저자명을 입력하세요.");
				}
				else if(Bcom==null || Bcom.length()==0){
					JOptionPane.showMessageDialog(null, "출판사명을 입력하세요.");
				}
				else if(Bdate==null || Bdate.length()==0){
					JOptionPane.showMessageDialog(null, "구입날짜를 입력하세요.");
				}
				else if(status==null || status.length()==0){
					JOptionPane.showMessageDialog(null, "책상태를 선택하세요.");
				}

				// 서버에 보내기 위한 데이터 묶기
				BBMainData mainD = new BBMainData();
				BBBookData DataB = new BBBookData();
				mainD.protocol = 1203;
				DataB.bb_no= Bno;
				DataB.bb_name = Bname;
				DataB.bb_writer = Bwriter;
				DataB.bb_com = Bcom;
				DataB.Byear = Byear;
				DataB.Bmonth = Bmonth;
				DataB.bb_date = Bdate;
				DataB.bb_staus = status;
				mainD.bookData = DataB;
				// 서버에 보내기
				try {
					main.main.oout.writeObject(mainD);
				} 
				catch (Exception e2) {}
		}
	}
			
	
//	public static void main(String[] args) {
//		new BBModifyDlg();
//	}
}
