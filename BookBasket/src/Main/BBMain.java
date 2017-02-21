package Main;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import Book.BBBookRegDlg;
import Book.BBBookSearch;
import Data.BBMainData;
import Data.BBMemberData;
import Member.BBLoginDlg;
import Message.BBMessageMain;
import Receive.BBReceiveThread;
import Rental.BBRentalMain;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaLookAndFeel;
/*
 * 	네트워크 처리
 * 	3개의 화면을 Tabb 이 생기는 JTabbedPane으로 관리할 화면  
 */
public class BBMain extends JFrame {
	BBLoginDlg		loginDlg;	// 로그인 화면 클래스 선언...
	BBBookSearch bookSearchMain;	// 책 검색 화면 클래스 선언...
	BBBookRegDlg bookRegDlgMain;	// 책 등록 화면 클래스 선언...
	BBRentalMain bookRentalMain;		// 책 예약 화면 클래스 선언...
	BBMessageMain msgMain;			// 메세지 함 메인화면 클래스 선언...
	
	
	public JTabbedPane bbMainTP;
	public GradationPanel nameP;
	public JButton logoutB, msgBoxB;
	public JLabel msgL1, msgL2;
	public String name ="박세빈";
	public int msgNum = 3 ;
	public int bookNum = 1;
	
	public Socket socket;
	public ObjectInputStream oin;
	public ObjectOutputStream oout;
	public BBReceiveThread thread;

	public BBMainData data;
	
	public BBMain() {
		
		loginDlg = new BBLoginDlg(this);
		//	자기 자신은 출력하지 말고
		
		setTitle(name + " 님의 책바구니");	// 메인 화면 제목표시줄의 제목 지정...
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 창닫기 옵션지정...
		setSize(800, 570);	// 메인 사이즈 지정
		setResizable(false);
		
		// 실행창 위치 설정...
		Dimension frameSize = getSize();
		Dimension winSize = Toolkit.getDefaultToolkit().getScreenSize();
		int locW = (winSize.width - frameSize.width)/2;
		int locH = (winSize.height - frameSize.height)/2;
		setLocation(locW, locH);
		
//		loginDlg = new BBLoginDlg();
		//	자기 자신은 출력하지 말고
//		loginDlg.setVisible(true);
		
		// Tabb에 들어갈 클래스생성해준다.
		// 책 검색, 등록, 예약 클래스 생성자 함수 호출...
		bookSearchMain = new BBBookSearch(this);
		bookRegDlgMain = new BBBookRegDlg(this);
		bookRentalMain = new BBRentalMain(this);
		
		bbMainTP = new JTabbedPane();	// 메인에 들어갈 JTabbedPane 생성해준다.
		bbMainTP.add(bookSearchMain, "책 검 색");	// 책 검색 클래스 탭에 넣어준다.
		bbMainTP.add(bookRegDlgMain, "책 등 록");	// 책 등록 클래스 탭에 넣어준다.
		bbMainTP.add(bookRentalMain, "예약 현황");// 예약현황 클래스 탭에 넣어준다.
		
		// 이제 오른쪽에 들어갈 것들을 만들어 주자.
		JPanel mainSide = new JPanel(new BorderLayout()); // 먼저 전체를 담을 JPanel을 만든다.
		// 오른쪽 화면은 중간에 공백이 있고 위, 아래에 다른것들이 들어가므로 보더 레이아웃으로 정책 설정...
		mainSide.setPreferredSize(new Dimension(200, 570));// 사이즈 설정....
		JPanel sideRblankP = new JPanel();	// 디자인을 위한 빈 패널..
		sideRblankP.setPreferredSize(new Dimension(7, 600));
		JPanel sideCenterP = new JPanel();	// 사이드 중앙에 들어갈 패널...
		
		// 위쪽에 들어갈 것들 만들자.
		JPanel loginStatusP = new JPanel(new BorderLayout());
		loginStatusP.setPreferredSize(new Dimension(190, 155));
		
		JPanel loginInfoP = new JPanel();	// 로그인 정보 패널...
		JPanel sideTblankP = new JPanel();	// 디자인을 위한 빈 패널..
		sideTblankP.setPreferredSize(new Dimension(200, 11));
		TitledBorder ttlBorder = new TitledBorder(null,"Login Info",TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
		loginStatusP.setBorder(ttlBorder);
//		loginInfoP.setBorder(ttlBorder);
//		loginInfoP.setBorder(new EtchedBorder(BevelBorder.LOWERED));
		
		JPanel alertP = new JPanel();	// 알림 패널...
		alertP.setPreferredSize(new Dimension(160, 50));
		JPanel alertListP = new JPanel(new BorderLayout());
		alertListP.setPreferredSize(new Dimension(160, 60));
		msgL1 = new JLabel("new 메세지 : " + msgNum + " 개" );		// new 메세지 출력 라벨
		msgL2 = new JLabel("빌린 책 : " + bookNum + " 권");		// 빌린책 권수 출력 라벨
		alertP.add(msgL1);
		alertP.add(msgL2);
		alertListP.add(alertP, "South");
		
		// 새로운 메세지가 있으면 그리고 빌린책이 있으면 출력되게 하자.
		if(!(msgNum > 0)){
			msgL1.setVisible(false);
		}
		
		if(!(bookNum > 0)){
			msgL2.setVisible(false);
		}
		
		// 로그인 정보 텍스트 패널을 만들어주자...
		JLabel nameL = new JLabel( name + " 님", SwingConstants.CENTER);
		JLabel wlcomeTxt = new JLabel("로그인 하셨습니다.", SwingConstants.CENTER);
		// 배경 그라데이션 처리를 위해 JPanel을 상속받은 패널로 패널을 만든다.
		nameP = new GradationPanel(Color.decode("#7dd2ff"), Color.decode("#2c7fff"));	
		nameP.setLayout(new GridLayout(2, 1)); // 글이 두줄이므로 그리드레이아웃으로 정책설정...
		nameP.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		nameP.setPreferredSize(new Dimension(160, 45));		// 패널 사이즈 지정...
		// 패널에 넣어주자.
		nameP.add(nameL);
		nameP.add(wlcomeTxt);
		// 로그인 정보패널에 정보텍스트 패널을 넣어주자.
		loginInfoP.add(nameP);
		loginInfoP.add(alertListP, "South");
		
//		JButton logoutB = new JButton("Logout");	// 로그아웃 버튼 만든다.
		JButton msgBoxB = new JButton("메세지함");	// 메세지함 여는 버튼 생성..
//		JPanel logoutBP = new JPanel();	// 버튼이 들어갈 패널 만든다.
		JPanel msgBoxBP = new JPanel();
		msgBoxBP.setPreferredSize(new Dimension(150, 43));
//		logoutBP.add(logoutB);
		msgBoxBP.add(msgBoxB);
		
		BtnEvent evt = new BtnEvent();
//		logoutB.addActionListener(evt);
		msgBoxB.addActionListener(evt);
		
		loginStatusP.add(loginInfoP, "Center"); 	// 로그인 정보 패널을 넣어준다.
//		loginStatusP.add(logoutBP, "South"); 	// 버튼을 넣어준다.
		
		sideCenterP.add(loginStatusP, "North");	// 로그인 정보 전체 패널을 넣어준다.
		sideCenterP.add(sideRblankP, "East");	// 디자인을 위해 공백 패널을 오른쪽에 넣어준다.
		
		// 이제 옆에 들어갈 것들을 사이드 패널에 넣어준다.
		mainSide.add(msgBoxBP, "South");	// 버튼을 아래쪽에 넣어준다.
		mainSide.add(sideRblankP, "East");	// 오른쪽 공백을 주기위해 넣어준다.
		mainSide.add(sideTblankP, "North");	// 위 쪽 공백을 주기위해 넣어준다.
		mainSide.add(sideCenterP, "Center");	// 로그인 상태 패널을 넣어준다.
		
		add(bbMainTP, "Center");
		// JFrame은 기본 레이아웃 정책이 BorderLayout 이다.
		// 이 클래스는 JFrame을 상속 받았으므로 JFrame의 역활도 한다.
		// 따라서 여기서 JTabbedPane 을 넣어주기만 하면 된다.
		add(mainSide, "East");	// 사이드 메뉴들을 넣어준다.
		
		// ==================== 소켓 생성 ====================
		try{	//192.168.25.3
			socket = new Socket("192.168.35.72", 9991);
			oout = new ObjectOutputStream(socket.getOutputStream());
			oin = new ObjectInputStream(socket.getInputStream());
			thread = new BBReceiveThread(this);
			thread.start();
//			BBMainData data = new BBMainData();
//
//			oout.writeObject(data);
		}catch (Exception e) {
			System.out.println("에러 = " + e);
			System.exit(0);
		}
		loginDlg.setVisible(true);
	}
	
	public void showMain() {
		loginDlg.setVisible(false);
		loginDlg.dispose();
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		// LookAndFeel 적용
		try {
			// 맥 스타일s
//			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			
			// 블랙 스타일 
			UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
			SyntheticaLookAndFeel.setFont("Table", 11);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		new BBMain();
	}
	
	class BtnEvent implements ActionListener {
		public void actionPerformed(ActionEvent e){
			String str = (String) e.getActionCommand();
			
			data = new BBMainData();
			data.protocol = 1401;
			BBMemberData temp = new BBMemberData();
			temp.id = loginDlg.idF.getText();
			
			data.memberData = temp;
			
			try{				
				oout.writeObject(data);
				msgMain = new BBMessageMain(BBMain.this);
			}catch (Exception ee) {
			}
			
			if( str.equals("Logout")){	
			}
		}
	}
}
