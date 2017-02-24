package Rental;
import java.awt.BorderLayout;
import java.io.IOException;

/*
 * 	예약정보 확인 화면    
 */
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Data.BBMainData;
import Main.BBMain;

public class BBRentalMain extends JPanel  {
	public BBMain main;
	
	public BBFromRental FromRental;
	public BBToRental	 ToRental;  
	
	// 상호참조
	
	
	public BBRentalMain(BBMain m) {
		main = m;
		
	//	setLayout(null);
		//frame = new JFrame();
		setLayout(new BorderLayout());
		
		JPanel fromPanel = new JPanel(new BorderLayout());
		JPanel toPanel = new JPanel(new BorderLayout());
		
		JTabbedPane tabbedPane = new JTabbedPane(2);
		int s = tabbedPane.LEFT;   
		System.out.println(s);
		
		// TabbedPane 방향설정 LEFT의 값이 2. 
		
		FromRental = new BBFromRental(this);
		ToRental   = new BBToRental(this);
		
		// 상호참조 클래스 초기화.
		
		tabbedPane.add("대여신청 도서", FromRental);
		tabbedPane.add("신청받은 도서", ToRental);
		

		// tabbedPane("제목", 클래스 변수);
		
	//	tabbedPane.setBounds(0, 0, 600, 570);
		add(tabbedPane);
			
		setSize(600, 570);
		setVisible(true);
		
		
		
	}
	//------------------------ 예약테이블 관련 함수. 마지막 테이블에 입력이 안되고 있음
	public void RentalFInitProc(){
		
		BBMainData Data = new BBMainData();
		
		Data.protocol = 1301;
		System.out.println("예약테이블 요청프로토콜 : "+Data.protocol);
		
		try {
			//main.oout.writeObject(Data);  
			main.oout.writeObject(Data);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void RentalTInitProc(){
		
		BBMainData Data = new BBMainData();
		
		Data.protocol = 1302;
		System.out.println("예약테이블 요청프로토콜 : "+Data.protocol);
		
		try {
			//main.oout.writeObject(Data);  
			main.oout.writeObject(Data);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	//------------------------ 예약테이블 관련 함수. 마지막 테이블에 입력이 안되고 있음

/*  public static void main(String[] args) {
		new BBRentalMain();

	}*/
	
}
