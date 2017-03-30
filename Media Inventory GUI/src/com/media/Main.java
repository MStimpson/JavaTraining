package com.media;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class Main {

  public static void main(String args[]) {

    JFrame frame = new JFrame("Media Inventory");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(100, 100, 450, 300);
    frame.setResizable(false);
    
    TabBuilder tb = new TabBuilder();
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    tabbedPane.addTab("Overview", tb.initialTab());
    tabbedPane.addTab("Search", tb.searchTab());
    tabbedPane.addTab("Add", tb.addTab());

    
    frame.add(tabbedPane, BorderLayout.CENTER);
    frame.setSize(475, 225);
    frame.setVisible(true); 
   
  }
}