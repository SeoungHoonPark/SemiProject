package Member;
/*
 * 로그인 화면을 처리할 클래스
 */
import	javax.swing.*;

import Data.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import Main.*;
public class BBLoginDlg /* extends JDialog */ extends JFrame{

	public JTextField idF;
	public JPasswordField pwF;
	public JButton loginB, closeB, evtBtn;
	public JLabel blankL, loginL, idL, pwL;
	public boolean isState_lgin = false ;
	
	public BBMain main;
	public BBLoginDlg loginD;
	public BBJoinDlg joinDlg ;
	public BBLoginDlg(BBMain m) {
		main = m ;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("책바구니 LogIn");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - 150, dim.height/2 - 80);
		
		
		blankL = new JLabel("");
		loginL = new JLabel("아이디와 비밀번호를 입력하세요!");
		loginL.setHorizontalAlignment(JLabel.CENTER);
		
		Dimension dimT = new Dimension(300, 35);
		loginL.setPreferredSize(dimT);
		
		idL = new JLabel("I D : ", SwingConstants.RIGHT);
		pwL = new JLabel("P W : ", SwingConstants.RIGHT);
		JPanel p1 = new JPanel(new GridLayout(2,1));
		p1.setAlignmentY(JPanel.RIGHT_ALIGNMENT);
		Dimension dim1 = new Dimension(70, 200);
		p1.setPreferredSize(dim1);
		p1.add(idL);
		p1.add(pwL);
		
		idF = new JTextField();
		idF.setHorizontalAlignment(JTextField.LEFT);
		pwF = new JPasswordField();
		pwF.setHorizontalAlignment(JPasswordField.LEFT);
		JPanel p2 = new JPanel(new GridLayout(2,1));
		JPanel pB = new JPanel();
		JPanel timeP = new JPanel(new GridLayout(3, 1));
		timeP.setPreferredSize(new Dimension(300, 35));
		Dimension dim2 = new Dimension(60, 200);
		pB.setPreferredSize(dim2);
		p2.add(idF);
		p2.add(pwF);

		timeP.add(blankL);
		timeP.add(loginL);
		
		JPanel p3 = new JPanel(new BorderLayout());
		p3.add(p1, "West");
		p3.add(p2, "Center");
		p3.add(pB, "East");
		
		loginB = new JButton("Login");
		closeB = new JButton("Join");
		JPanel p4 = new JPanel(new FlowLayout());
		Dimension dimBottom = new Dimension(300, 45);
		p4.setPreferredSize(dimBottom);
		p4.add(loginB);
		p4.add(closeB);
		
		ButtonEventLogin evt = new ButtonEventLogin();
		loginB.addActionListener(evt);
		closeB.addActionListener(evt);
		
		add(timeP, "North");
		add(p3, "Center");
		add(p4, "South");
		
		setSize(300, 180);
		setVisible(true);
		setResizable(false);
	}
	
	public void loginProc(){
		// 아이디와 비밀 번호를 알아내자.
		
					String idStr = idF.getText();
					String pwStr = new String(pwF.getPassword());
				
					// 메인 데이터 만들자.
					BBMainData mainD = new BBMainData();
//					// 로그인 데이터를 만들자.
					BBMemberData loginD = new BBMemberData();
					
					// 프로토콜 정해주고..
					mainD.protocol = 1101;
					// 데이터 넣자.
					loginD.id = idStr;
					loginD.pw = pwStr;
					// 메인데이터에 넣어주자.
					mainD.memberData = loginD;
					main.data = mainD ;
					try {
						main.oout.writeObject(mainD);
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
	}
	
	class ButtonEventLogin implements ActionListener {
		public void actionPerformed(ActionEvent e){
			JButton btn = (JButton) e.getSource();
			if (btn.equals(loginB)){

				loginProc();

			}else {
				joinDlg = new BBJoinDlg(BBLoginDlg.this);
				BBLoginDlg.this.dispose();
			}
		}
	}	

}

