package Main;

import	javax.swing.*;
import java.awt.*;

import	Member.*;
import	Book.*;
import	Data.*;
import	Message.*;
import	Receive.*;
import	Rental.*;
/*
 * 	네트워크 처리
 * 	3개의 화면을 Tabb 이 생기는 JTabbedPane으로 관리할 화면  
 */
public class BBMain extends JFrame {
	BBLoginDlg		loginDlg;	// 로그인 화면 클래스 선언...
	BBBookSearch bookSearchMain;	// 책 검색 화면 클래스 선언...
	BBBookRegDlg bookRegDlgMain;	// 책 등록 화면 클래스 선언...
	BBRentalDlg bookRentalMain;		// 책 예약 화면 클래스 선언...
	
	JTabbedPane bbMainTP;
	
	public BBMain() {
		super("책바구니");	// 메인 화면 제목표시줄의 제목 지정...
//		loginDlg = new BBLoginDlg();
		//	자기 자신은 출력하지 말고
//		loginDlg.setVisible(true);
		
		// Tabb에 들어갈 클래스생성해준다.
		// 책 검색, 등록, 예약 클래스 생성자 함수 호출...
		bookSearchMain = new BBBookSearch(this);
		bookRegDlgMain = new BBBookRegDlg(this);
		//bookRentalMain = new BBRentalDlg(this);
		
		bbMainTP = new JTabbedPane();	// 메인에 들어갈 JTabbedPane 생성해준다.
		bbMainTP.add(bookSearchMain, "책 검 색");	// 책 검색 클래스 탭에 넣어준다.
		bbMainTP.add(bookRegDlgMain, "책 등 록");	// 책 등록 클래스 탭에 넣어준다.
		bbMainTP.add(bookRentalMain, "예약 현황");// 예약현황 클래스 탭에 넣어준다.
		
		// 이제 오른쪽에 들어갈 것들을 만들어 주자.
		JPanel mainSide = new JPanel(new BorderLayout()); // 먼저 전체를 담을 JPanel을 만든다.
		// 오른쪽 화면은 중간에 공백이 있고 위, 아래에 다른것들이 들어가므로 보더 레이아웃으로 정책 설정...
		mainSide.setPreferredSize(new Dimension(200, 570));// 사이즈 설정....
	
		// 위쪽에 들어갈 것들 만들자.
		JPanel loginStatusP = new JPanel(new BorderLayout());
		loginStatusP.setPreferredSize(new Dimension(200, 300));
		
		JPanel loginInfoP = new JPanel();	// 로그인 정보 패널...
		JPanel sideTblankP = new JPanel();	// 디자인을 위한 빈 패널..
		sideTblankP.setPreferredSize(new Dimension(200, 16));
		loginInfoP.setBackground(Color.WHITE);
		
		JButton logoutB = new JButton("Logout");	// 로그아웃 버튼 만든다.
		JButton msgBoxB = new JButton("메세지함");	// 메세지함 여는 버튼 생성..
		
		loginStatusP.add(sideTblankP, "North"); 	// 로그인 정보 패널을 넣어준다.
		loginStatusP.add(loginInfoP, "Center"); 	// 로그인 정보 패널을 넣어준다.
		loginStatusP.add(logoutB, "South"); 	// 버튼을 넣어준다.
		
		// 이제 옆에 들어갈 것들을 사이드 패널에 넣어준다.
		mainSide.add(msgBoxB, "South");	// 버튼을 아래쪽에 넣어준다.
		mainSide.add(loginStatusP, "North");	// 로그인 상태 패널을 넣어준다.
		
		add(bbMainTP, "Center");
		// JFrame은 기본 레이아웃 정책이 BorderLayout 이다.
		// 이 클래스는 JFrame을 상속 받았으므로 JFrame의 역활도 한다.
		// 따라서 여기서 JTabbedPane 을 넣어주기만 하면 된다.
		// [ this. ] 을 넣어주지 않아도 자동으로 들어가는거기 때문에 생략...
		add(mainSide, "East");	// 사이드 메뉴들을 넣어준다.
		
		setSize(800, 570);	// 메인 사이즈 지정...
		setVisible(true);	// 일단 화면 작업에서는 보여야 되기때문에 ... 나중에 이 줄은 삭제할 예정...
		
	}
	
	public void showMain() {
		loginDlg.setVisible(false);
		loginDlg.dispose();
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new BBMain();
	}
}
