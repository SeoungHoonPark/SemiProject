package Main;

import java.awt.*;
import javax.swing.*;

/**
* 이 클래스는 그라데이션 처리를 위한 클래스이다.
*/
public class GradationPanel extends JPanel {
	Color clr1;
	Color clr2;
    public GradationPanel(Color c1, Color c2) {
    	clr1 = c1 ;
    	clr2 = c2 ;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color color1 = clr1;
        Color color2 = clr2;
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
    
}