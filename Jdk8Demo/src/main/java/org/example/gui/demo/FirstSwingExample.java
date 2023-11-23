package org.example.gui.demo;

import javax.swing.*;

public class FirstSwingExample {

    public static void main(String[] args) {

        //creating instance of JFrame
        JFrame f = new JFrame();

        //creating instance of JButton
        JButton b = new JButton("click");

        //x axis, y axis, width, height
        b.setBounds(130, 100, 100, 40);

        //adding button in JFrame
        f.add(b);

        //400 width and 500 height
        f.setSize(400, 500);
        //using no layout managers
        f.setLayout(null);
        //making the frame visible
        f.setVisible(true);
    }
}
