package main.java.gyuka08.boeinggame;

import javax.swing.*;

public class BoeingGame {
    public static void main(String[] Akbar) throws Exception {
        int windowWidth = 360;
        int windowHeight = 640;

        JFrame window = new JFrame("Jihad");
//        window.setVisible(true);
        window.setSize(windowWidth, windowHeight);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Canvas windowCanvas = new Canvas();
        window.add(windowCanvas);
        window.pack();
        window.setVisible(true);

    }
}