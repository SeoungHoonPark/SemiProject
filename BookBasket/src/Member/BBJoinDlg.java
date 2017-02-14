package Member;
/*
 *  회원 가입 화면
 */
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class BBJoinDlg /* extends JDialog */ extends JFrame {
 
 public BBJoinDlg() {
  
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setBounds(200,200,450,500);
  this.setLayout(null);
  //회원가입 
    JLabel log = new JLabel("Membersh");
    log.setBounds(30,40,800,40);
    this.add(log);
    
    //이름
    JLabel name_la = new JLabel("*name");
    name_la.setBounds(50,70,80,50);
    this.add(name_la);
    
    JTextField name_t = new JTextField(" ");
    name_t.setBounds(140,85,80,20);
    this.add(name_t);
    
    //아이디
    JLabel id_la = new JLabel("*id");
    id_la.setBounds(50,120,80,50);
    this.add(id_la);
    
    JTextField id_t = new JTextField(" ");
    id_t.setBounds(140,133,80,20);
    this.add(id_t);
    
    //아이디 중복확인 버튼
    JButton b = new JButton("*Check ID of ID");
    b.setBounds(250,133,110,20);
    this.add(b);
    
    //비밀번호 입력
    JLabel pw_la = new JLabel("*Password");
    pw_la.setBounds(50,160,80,50);
    this.add(pw_la);
    
    JPasswordField pw_t = new JPasswordField("");
    pw_t.setBounds(140,175,130,20);
    this.add(pw_t);
    
    //비밀번호 재입력
    JLabel pw_la1 = new JLabel("*Jaeimnyeok");
    pw_la1.setBounds(50,200,80,50);
    this.add(pw_la1);
    
    JPasswordField pw_t1 = new JPasswordField("");
    pw_t1.setBounds(140,215,130,20);
    this.add(pw_t1);
    
    //email
    JLabel email_la = new JLabel("*email");
    email_la.setBounds(50,240,80,50);
    this.add(email_la);
    
    JTextField email_t = new JTextField(" ");
    email_t.setBounds(140,255,100,20);
    this.add(email_t);
    
    JLabel email_la1 = new JLabel("@");
    email_la1.setBounds(240,255,20,20);
    this.add(email_la1);
    
    JTextField email_t1 = new JTextField(" ");
    email_t1.setBounds(260,255,100,20);
    this.add(email_t1);
    
    //email 입력 예시
    JLabel email_la2 = new JLabel("ex)1234@naver.com");
    email_la2.setBounds(150,275,120,20);
    this.add(email_la2);
    
    //핸드폰 입력
    JLabel phone_la = new JLabel("*Cell phone");
    phone_la.setBounds(50,290,80,50);
    this.add(phone_la);
    
    //번호 앞자리 선택창
    JComboBox phone_t1 = new JComboBox();
    phone_t1.setBounds(140,305,50,20);
    phone_t1.addItem("010");
    this.add(phone_t1);
   
    //번호 두번째자리
    JTextField phone_t2 = new JTextField("");
    phone_t2.setBounds(200,305,70,20);
    this.add(phone_t2);
    
    //번호 세번째자리
    JTextField phone_t3 = new JTextField("");
    phone_t3.setBounds(280,305,70,20);
    this.add(phone_t3);
    
    JButton chcek = new JButton("확인");
    JButton cen = new JButton("취소");
    JPanel p2 = new JPanel();
    p2.add(chcek);
    p2.add(cen);
    p2.setBounds(140,370,130,100);
    this.add(p2);
    
    this.setVisible(true);
    
 }

 public static void main(String[] args) {
  new BBJoinDlg();
  
 }

}
