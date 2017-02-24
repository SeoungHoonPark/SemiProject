package Rental;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
 
public class TabbedPaneExample 
{
    private JFrame frm;
    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    
    public TabbedPaneExample()
    {
        //생성 및 초기화
        frm = new JFrame();
        tabbedPane = new JTabbedPane();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        label1 = new JLabel("I'm the First");
        label2 = new JLabel("I'm Second");
        label3 = new JLabel("I'm number Three");
        
        //패널에 컴포넌트 추가
        panel1.add(label1);
        panel2.add(label2);
        panel3.add(label3);
        
        //탭에 패널 추가 및 이름 설정
        tabbedPane.add("First", panel1);
        tabbedPane.add("Second", panel2);
        tabbedPane.add("Third", panel3);
        
        /*탭이름 재지정 메소드*/
        //tabbedPane.setTitleAt(0, "첫째");
        //tabbedPane.setTitleAt(1, "둘째");
        //tabbedPane.setTitleAt(2, "셋째");
        
        /*탭위치 설정 메소드*/
        //tabbedPane.setTabPlacement(JTabbedPane.TOP); //상단위치
        //tabbedPane.setTabPlacement(JTabbedPane.BOTTOM); //하단위치                                    
        //tabbedPane.setTabPlacement(JTabbedPane.LEFT); //좌측위치
        //tabbedPane.setTabPlacement(JTabbedPane.RIGHT); //우측위치
 
        //프래임 기본 설정
        frm.add(tabbedPane);
        frm.setSize(300,200);
        frm.setTitle("탭 예제");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
    }
 
    public static void main(String[] args) 
    {
        new TabbedPaneExample();
    }
}