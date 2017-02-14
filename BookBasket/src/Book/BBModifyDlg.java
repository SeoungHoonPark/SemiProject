package Book;
import Book.BBBookSearch;
import javax.swing.*;
import java.awt.*;
/*
 * 	책정보 수정 화면
 *  필요한 항목: 도서명, 저자, 출판사, 구입날짜, 책상태, 수정버튼
 */
public class BBModifyDlg extends JFrame  {
	BBBookSearch main;
	
	public BBModifyDlg(BBBookSearch m) {
		main = m;
		
		
		setTitle("등록한 책 수정");
		setLayout(null);
		// 폰트 설정
		setUIFont(new javax.swing.plaf.FontUIResource("고딕", Font.PLAIN, 12));
		// 라디오버튼
		ButtonGroup bg = new ButtonGroup();
		JRadioButton sang = new JRadioButton("상");
		JRadioButton jung = new JRadioButton("중");
		JRadioButton ha = new JRadioButton("하");
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
		p2.add(textF[3]); // 연도 입력칸
		p2.add(Reg_label[4]); // "년"
		p2.add(textF[4]); // 월 입력칸
		p2.add(Reg_label[5]); // "월"
		
		//텍스트필드 묶기
		JPanel p3 = new JPanel(new GridLayout(5,1,10,10));
		p3.add(textF[0]);
		p3.add(textF[1]);
		p3.add(textF[2]);
		p3.add(p2);
		p3.add(p0);
		p3.setBounds(120, 30,180,200);
		
		// 버튼 묶기
		JButton ModifyB = new JButton("수정하기"); 
		ModifyB.setBounds(115,280,100,30);

		add(p1);
		add(p3);
		add(ModifyB);
		setSize(350,380);
		setVisible(true);
		

		Dimension frameSize = getSize();
		Dimension winSize = Toolkit.getDefaultToolkit().getScreenSize();
		int locW = (winSize.width - frameSize.width)/2;
		int locH = (winSize.height - frameSize.height)/2;
		setLocation(locW, locH);
		
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
//		new BBModifyDlg();
//	}
}
