package Rental;
/*
 * 	예약정보 확인 화면  
 */
import javax.swing.*;

public class BBRentalMain  /* extends JPanel */ extends JFrame  {
	JFrame frame;
	BBFromRental FromRental;
	BBToRental	 ToRental;  
	
	// 상호참조
	
	public BBRentalMain() {
		frame = new JFrame();
		JPanel fromPanel = new JPanel();
		JPanel toPanel = new JPanel();
		
		
		JTabbedPane tabbedPane = new JTabbedPane(2);
		int s = tabbedPane.LEFT;   
		System.out.println(s);
		
		// TabbedPane 방향설정 LEFT의 값이 2. 
		
		FromRental = new BBFromRental(this);
		ToRental   = new BBToRental(this);
		
		// 상호참조 클래스 초기화.
		
		tabbedPane.add("신청받은 책", FromRental);
		tabbedPane.add("대여해준 책", ToRental);

		// tabbedPane("제목", 클래스 변수);
		
		frame.add(tabbedPane);
		
		frame.setSize(600, 570);
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {
		new BBRentalMain();

	}
}
