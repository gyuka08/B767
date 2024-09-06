import javax.swing.*;

public class App {
    public static void main(String[] Akbar) throws Exception {
        int windowWidth = 360;
        int windowHeight = 640;

        JFrame window = new JFrame("Jihad");
//        window.setVisible(true);
        window.setSize(windowWidth, windowHeight);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Body windowBody = new Body();
        window.add(windowBody);
        window.pack();
        window.setVisible(true);

    }
}