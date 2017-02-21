package Member;
/*
 * 로그인 화면을 처리할 클래스
 */
import	javax.swing.*;

import Data.BBMainData;
import Data.BBMemberData;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import Main.*;
public class BBLoginDlg /* extends JDialog */ extends JFrame{

	public JTextField idF;
	public JPasswordField pwF;
	public JButton loginB, closeB, evtBtn;
	public JLabel blankL, loginL, idL, pwL;
	boolean isState_lgin = false ;
	
	BBMain main;
	BBLoginDlg loginD;
	BBJoinDlg joinDlg ;
	public BBLoginDlg(BBMain m) {
		main = m ;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		setResizable(false);
	}
	
	class ButtonEventLogin implements ActionListener {
		public void actionPerformed(ActionEvent e){
			JButton btn = (JButton) e.getSource();
			if (btn.equals(loginB)){
				String id = idF.getText();
				String pw = new String (pwF.getPassword());
		
				BBMainData data = new BBMainData();
				BBMemberData mData = new BBMemberData();
				mData.id = id;
				mData.pw = pw;
				data.memberData = mData;
				data.protocol = 1101; 
				// 이곳에 프로토콜을 넣어주면 클라이언트가 자동 종료됨 (??)
				System.out.println("로그인버튼 클릭(서버로 넘어갈 데이터) : " + data.memberData.toString());
				try{
					
					main.oout.writeObject(data);
				}catch (Exception ee) {
					ee.printStackTrace();
				}
			}else {
				joinDlg = new BBJoinDlg(BBLoginDlg.this);
				BBLoginDlg.this.setVisible(false);
			}
		}
	}	

}

