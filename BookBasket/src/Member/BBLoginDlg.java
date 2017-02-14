package Member;
/*
 * 로그인 화면을 처리할 클래스
 */
import	javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import Main.*;
public class BBLoginDlg /* extends JDialog */ extends JFrame{

	JTextField idF;
	JPasswordField pwF;
	JButton loginB, closeB, evtBtn;
	JLabel blankL, loginL, idL, pwL;
	boolean isState_lgin = false ;
	
	BBMain main;
	BBJoinDlg joinDlg ;
	public BBLoginDlg(BBMain m) {
		main = m ;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("LogIn");
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
		JPanel timeP = new JPanel(new GridLayout(2, 1));
		timeP.setPreferredSize(new Dimension(300, 35));
		Dimension dim2 = new Dimension(60, 200);
		pB.setPreferredSize(dim2);
		p2.add(idF);
		p2.add(pwF);
		
		timeP.add(loginL);
		timeP.add(blankL);
		
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
		
		setSize(300, 160);
		setVisible(true);
		
	}
	
	class ButtonEventLogin implements ActionListener {
		public void actionPerformed(ActionEvent e){
			JButton btn = (JButton) e.getSource();
			if (btn.equals(loginB)){
				
				main.isState_login = true;
				main.showMain();
				return;
			}
			else {
				
			}
		}
	}	

}

