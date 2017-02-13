package Member;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;

/*
 * 	회원 가입 화면
 */
import javax.swing.*;

public class BBJoinDlg /* extends JDialog */ extends JFrame {

	public BBJoinDlg() {
		Frame f = new Frame();
		f.setBounds(200,200,800,700);
		f.setLayout(null);
		//회원가입 
				Label log = new Label("Membersh");
				log.setBounds(30,40,800,40);
				f.add(log);
				
				//이름
				Label name_la = new Label("*name");
				name_la.setBounds(50,100,80,50);
				f.add(name_la);
				
				TextField name_t = new TextField(" ");
				name_t.setBounds(140,115,80,20);
				f.add(name_t);
				
				//아이디
				Label id_la = new Label("*id");
				id_la.setBounds(50,180,80,50);
				f.add(id_la);
				
				TextField id_t = new TextField(" ");
				id_t.setBounds(140,193,80,20);
				f.add(id_t);
				
				//아이디 중복확인 버튼
				Button b = new Button("*Check ID of ID");
				b.setBounds(250,193,110,20);
				f.add(b);
				
				//비밀번호 입력
				Label pw_la = new Label("*Password");
				pw_la.setBounds(50,220,80,50);
				f.add(pw_la);
				
				JPasswordField pw_t = new JPasswordField(" ");
				pw_t.setBounds(140,235,130,20);
				f.add(pw_t);
				
				//비밀번호 재입력
				Label pw_la1 = new Label("*Jaeimnyeok");
				pw_la1.setBounds(50,260,80,50);
				f.add(pw_la1);
				
				JPasswordField pw_t1 = new JPasswordField(" ");
				pw_t1.setBounds(140,275,130,20);
				f.add(pw_t1);
				
				//email
				Label email_la = new Label("*email");
				email_la.setBounds(50,300,80,50);
				f.add(email_la);
				
				TextField email_t = new TextField(" ");
				email_t.setBounds(140,315,100,20);
				f.add(email_t);
				
				Label email_la1 = new Label("@");
				email_la1.setBounds(240,315,20,20);
				f.add(email_la1);
				
				TextField email_t1 = new TextField(" ");
				email_t1.setBounds(260,315,100,20);
				f.add(email_t1);
				
				//email 입력 예시
				Label email_la2 = new Label("ex)1234@naver.com");
				email_la2.setBounds(150,335,120,20);
				f.add(email_la2);
				
				//핸드폰 입력
				Label phone_la = new Label("*Cell phone");
				phone_la.setBounds(50,350,80,50);
				f.add(phone_la);
				
				//번호 앞자리 선택창
				Choice phone_t1 = new Choice();
				phone_t1.setBounds(140,365,50,20);
				phone_t1.add("010");
				f.add(phone_t1);
			
				//번호 두번째자리
				TextField phone_t2 = new TextField("");
				phone_t2.setBounds(200,365,70,20);
				f.add(phone_t2);
				
				//번호 세번째자리
				TextField phone_t3 = new TextField("");
				phone_t3.setBounds(280,365,70,20);
				f.add(phone_t3);
				
				
				f.setVisible(true);
	}

	public static void main(String[] args) {
		BBJoinDlg f = new BBJoinDlg();
		f.frameInit();
		f.dispose();
	}

}
