package Member;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 *  회원 가입 화면
 */

import Data.*;
import Member.BBJoinDlg.JoinEvent;

public class BBJoinDlg /* extends JDialog */ extends JFrame {
	public JTextField name_t; //이름
	public JTextField id_t; //아이디
	public JButton checkB; //아이디 중복 체크
	public JPasswordField pw_t; // 비밀번호
	public JPasswordField pw_t1; //비밀번호 재입력
	public JTextField email_t; //이메일 아이디
	public JTextField email_t1; //이메일 주소
	public JComboBox phone_t1; // 010
	public JTextField phone_t2; // 두번째자리
	public JTextField phone_t3; //세번째 자리
	public JButton okB; //확인 버튼
	public JButton canB; //취소 버튼
 
 BBLoginDlg loginD;
 public BBJoinDlg(BBLoginDlg login) {
	 loginD = login;
	 
  setTitle("책바구니 회원 가입");
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
//실행창 위치 설정...
	Dimension winSize = Toolkit.getDefaultToolkit().getScreenSize();
	int locW = (winSize.width - 430)/2;
	int locH = (winSize.height - 500)/2; 
  
  this.setBounds(200,200,430,500);
  this.setLayout(null);
  //회원가입 
    JLabel log = new JLabel("회 원 가 입");
    log.setBounds(50,25,850,25);
    this.add(log);
    
    //이름
    JLabel name_la = new JLabel("*이         름");
    name_la.setBounds(50,70,80,50);
    this.add(name_la);
    
    name_t = new JTextField(" ");
    name_t.setBounds(140,85,80,20);
    this.add(name_t);
    
    //아이디
    JLabel id_la = new JLabel("*아  이  디");
    id_la.setBounds(50,120,80,50);
    this.add(id_la);
    
    id_t = new JTextField(" ");
    id_t.setBounds(140,133,80,20);
    this.add(id_t);
    
    //아이디 중복확인 버튼
    checkB = new JButton("Check");
    checkB.setBounds(250,128,90,30);
    this.add(checkB);
    
    //비밀번호 입력
    JLabel pw_la = new JLabel("*비 밀 번 호");
    pw_la.setBounds(50,160,80,50);
    this.add(pw_la);
    
    pw_t = new JPasswordField("");
    pw_t.setBounds(140,175,130,20);
    this.add(pw_t);
    
    //비밀번호 재입력
    JLabel pw_la1 = new JLabel("*재 입 력");
    pw_la1.setBounds(50,200,80,50);
    this.add(pw_la1);
    
    pw_t1 = new JPasswordField("");
    pw_t1.setBounds(140,215,130,20);
    this.add(pw_t1);
    
    //email
    JLabel email_la = new JLabel("*이 메 일");
    email_la.setBounds(50,240,80,50);
    this.add(email_la);
    
    email_t = new JTextField(" ");
    email_t.setBounds(140,255,100,20);
    this.add(email_t);
    
    JLabel email_la1 = new JLabel("@");
    email_la1.setBounds(240,255,20,20);
    this.add(email_la1);
    
    email_t1 = new JTextField(" ");
    email_t1.setBounds(260,255,100,20);
    this.add(email_t1);
    
    //email 입력 예시
    JLabel email_la2 = new JLabel("ex)1234@naver.com");
    email_la2.setBounds(150,275,120,20);
    this.add(email_la2);
    
    //핸드폰 입력
    JLabel phone_la = new JLabel("*휴 대 전 화");
    phone_la.setBounds(50,290,80,50);
    this.add(phone_la);
    
    //번호 앞자리 선택창
    phone_t1 = new JComboBox();
    phone_t1.setBounds(140,305,50,20);
    phone_t1.addItem("010");
    phone_t1.addItem("011");
    phone_t1.addItem("016");
    phone_t1.addItem("019");
    this.add(phone_t1);
   
    //번호 두번째자리
    phone_t2 = new JTextField("");
    phone_t2.setBounds(200,305,70,20);
    this.add(phone_t2);
    
    //번호 세번째자리
    phone_t3 = new JTextField("");
    phone_t3.setBounds(280,305,70,20);
    this.add(phone_t3);

    //확인 버튼 취소 버튼
    okB = new JButton("확인");
    canB = new JButton("취소");
    
    JoinEvent evt = new JoinEvent();
    checkB.addActionListener(evt);
    okB.addActionListener(evt);
    canB.addActionListener(evt);
    
    JPanel p2 = new JPanel();
    p2.add(okB);
    p2.add(canB);
    p2.setBounds(140,370,130,100);
    this.add(p2);
    
    
    
    this.setVisible(true);
    
 }

	public void checkProc(){
 		String idS = id_t.getText();
 		
 		BBMainData data = new BBMainData();
 		BBMemberData lginD = new BBMemberData();
 		
 		lginD.id = idS ;
 		data.protocol = 1001 ;
 		data.memberData = lginD ;
 		
 		try{
 			loginD.main.oout.writeObject(data);
			System.out.println("데이터 넘기기 .................");
 		}
 		catch(Exception e){
 			e.printStackTrace();
 		}
 	}
	
 	public void joinProc(){
 		String idS = id_t.getText();
 		String pwS1 = new String(pw_t.getPassword());
 		String pwS2 = new String(pw_t1.getPassword());
 		if(!(pwS1.equals(pwS2))){
 			pw_t1.setText("");
 			JOptionPane.showMessageDialog(null, "비밀번호를 확인하세요!");
 			return;
 		}
 		String nameS = name_t.getText();
 		
 		String emailS1 = email_t.getText();
 		String emailS2 = email_t1.getText();
 		String emailS = emailS1 +"@"+ emailS2 ;
 		
 		String phoneS1 = (String) phone_t1.getSelectedItem();
 		String phoneS2 = phone_t2.getText();
 		char[] ch1 = phoneS2.toCharArray();
 		String phoneS3 = phone_t3.getText();
 		char[] ch2 = phoneS3.toCharArray();
 		String phoneS = phoneS1 +"-"+phoneS2 + "-" + phoneS3;
 		for (int i = 0; i < ch1.length ; i++ ){
 			if(ch1[i] < '0' || ch1[i] > '9'){
 				phone_t2.setText("");
 	 			JOptionPane.showMessageDialog(null, "전화번호를 확인하세요!");
 	 			return;
 			}
 		}
 		for (int i = 0; i < ch1.length ; i++ ){
 			if(ch2[i] < '0' || ch2[i] > '9'){
 				phone_t3.setText("");
 	 			JOptionPane.showMessageDialog(null, "전화번호를 확인하세요!");
 	 			return;
 			}
 		}
 		
 		System.out.println("회원가입 전화번호 : " + phoneS);
 		
 		BBMainData data = new BBMainData();
 		BBMemberData memberData = new BBMemberData();
 		
 		memberData.id = idS ;
 		memberData.pw = pwS1 ;
 		memberData.name = nameS ;
 		memberData.email = emailS ;
 		memberData.phone = phoneS ;
 		data.protocol = 1011 ;
 		data.memberData = memberData ;
 		
 		try{
 			loginD.main.oout.writeObject(data);
 			System.out.println("회원가입 데이터 넘기기 .................");
 		}
 		catch(Exception e){
 			e.printStackTrace();
 		}
 	}
 	

// public static void main(String[] args) {
//  new BBJoinDlg();
//  
// }
 	class JoinEvent implements ActionListener {
 		public void actionPerformed(ActionEvent e){
 			String str = e.getActionCommand();
 			
 			if (str.equals("Check")){
 				checkProc();
 			}
 			else if (str.equals("취소")){
 				loginD.setVisible(true);
 				BBJoinDlg.this.dispose();
 			}
 			else if (str.equals("확인")){
 				joinProc();
 			}
 		}
 	}
}
