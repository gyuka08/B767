import java.awt.*;
import javax.swing.*;

public class Body extends JPanel{
    int windowWidth = 360;
    int windowHeight = 640;

    //Image
    Image newYorkImage;
    Image boeingImage;
    Image topBuildingImage;
    Image buildingImage;
    Image roofImage;
    Image floorImage;

    //Boeing
    int boeingX = windowWidth / 8;
    int boeingY = windowHeight / 2;
    int boeingWidth = 72;
    int boeingHeight = 22;

    class Boeing {
        int x = boeingX;
        int y = boeingY;
        int width = boeingWidth;
        int height = boeingHeight;
    }

    Body() {
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setBackground(Color.getHSBColor(0.51f, 0.5f, 0.82f));

        //image loaded
        newYorkImage = new ImageIcon(getClass().getResource("main/resources/assets/Image/spr_newyork.png")).getImage();
        boeingImage = new ImageIcon(getClass().getResource("main/resources/assets/Image/spr_boeing767.png")).getImage();
        topBuildingImage = new ImageIcon(getClass().getResource("main/resources/assets/Image/spr_building.png")).getImage();
        buildingImage = new ImageIcon(getClass().getResource("main/resources/assets/Image/spr_building.png")).getImage();
        roofImage = new ImageIcon(getClass().getResource("main/resources/assets/Image/spr_roof.png")).getImage();
        floorImage = new ImageIcon(getClass().getResource("main/resources/assets/Image/spr_floor.png")).getImage();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics) {
        //newyok
        graphics.drawImage(floorImage, 0, 601, windowWidth, 39, null);
        graphics.drawImage(roofImage, 0, 0, windowWidth, 78, null);
        graphics.drawImage(newYorkImage, -180, 601 - 2 * 142, 2 * windowWidth, 2 * 142, null);
    }
}
