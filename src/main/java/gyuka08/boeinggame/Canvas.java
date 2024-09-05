package main.java.gyuka08.boeinggame;

import java.awt.*;
import javax.swing.*;

public class Canvas extends JPanel{
    int windowWidth = 360;
    int windowHeight = 640;

    //Image
    Image newYorkImage;
    Image boeingImage;
    Image topBuildingImage;
    Image buildingImage;


    Canvas() {
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setBackground(Color.blue);

        //image loaded
        newYorkImage = new ImageIcon(getClass().getResource("src/main/resources/assets/Image/spr_newyork.png")).getImage();
        boeingImage = new ImageIcon(getClass().getResource("src/main/resources/assets/Image/spr_boeing767.png")).getImage();
        topBuildingImage = new ImageIcon(getClass().getResource("src/main/resources/assets/Image/spr_building")).getImage();
        buildingImage = new ImageIcon(getClass().getResource("src/main/resources/assets/Image/spr_building")).getImage();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics) {
        //newyok
        graphics.drawImage(newYorkImage, 0, 0, windowWidth, windowHeight, null);
    }
}
