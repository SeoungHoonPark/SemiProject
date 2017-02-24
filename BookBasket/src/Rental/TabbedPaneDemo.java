package Rental;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TabbedPaneDemo extends JFrame {
  JTabbedPane _parentTab, _childTab;

  public TabbedPaneDemo() {
    _parentTab = new JTabbedPane();

    _childTab = new JTabbedPane();
    _childTab.add("Section 1", new JLabel("in a galaxy"));
    _childTab.add("Section 2", new JLabel("far, far away ..."));
    _parentTab.add("Chapter 1", new JLabel("A long time ago"));
    _parentTab.add("Chapter 2", _childTab);

    getContentPane().add(_parentTab);
  }

  public static void main(String[] args) {
    Frame frame;
    WindowListener exitListener;

    exitListener = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        Window window = e.getWindow();
        window.setVisible(false);
        window.dispose();
        System.exit(0);                
      }
    };

    frame = new TabbedPaneDemo();
    frame.addWindowListener(exitListener);
    frame.setSize(400, 400);
    frame.setVisible(true);
  }
}