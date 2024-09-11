import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Body extends JPanel implements ActionListener, KeyListener {
    BufferedImage floor = ImageIO.read(getClass().getResource("main/resources/assets/Image/spr_floor.png"));
    BufferedImage roof = ImageIO.read(getClass().getResource("main/resources/assets/Image/spr_roof.png"));

    int windowWidth = 360;
    int windowHeight = 640;

    int floorHeight = windowHeight - floor.getHeight();
    int roofHeight = roof.getHeight();

    //==================================================Image

    Image newYorkImage;
    Image boeingImage;
    Image topBuildingImage;
    Image bottomBuildingImage;
    Image roofImage;
    Image floorImage;

    //==================================================Boeing

    int boeingX = windowWidth / 8;
    int boeingY = windowHeight / 2;
    int boeingWidth = 60;
    int boeingHeight = 30;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gameOver) {
                newGame();
            }
            else velocityY = -11;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    class Boeing {
        int x = boeingX;
        int y = boeingY;
        int width = boeingWidth;
        int height = boeingHeight;
        Image image
        ;
        Boeing(Image image) {
            this.image = image;
        }
    }

    //==================================================Buildings

    int buildingX = windowWidth;
    int buildingY = 0;
    int buildingWidth = 64;
    int buildingHeight = 384;

    class Building {
        int x = buildingX;
        int y = buildingY;
        int width = buildingWidth;
        int height = buildingHeight;
        Image image;
        Boolean passed = false;

        Building(Image image) {
            this.image = image;
        }
    }


    //==================================================Logic

    Boeing boeing;
    int velocityX = -5;
    int velocityY = -0;
    int gravity = 1;
    Timer gameLoopTimer;
    Timer buildingIntervalTimer;
    Boolean gameOver = true;
    double score = 0;

    ArrayList<Building> buildings;
    Random random = new Random();

    Body() throws IOException {
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setBackground(Color.getHSBColor(0.51f, 0.5f, 0.82f));
        setFocusable(true);
        addKeyListener(this);

        //==================================================image loaded

        newYorkImage = new ImageIcon(getClass().getResource("main/resources/assets/Image/spr_newyork.png")).getImage();
        boeingImage = new ImageIcon(getClass().getResource("main/resources/assets/Image/spr_plane.png")).getImage();
        topBuildingImage = new ImageIcon(getClass().getResource("main/resources/assets/Image/spr_bottombuilding.png")).getImage();
        bottomBuildingImage = new ImageIcon(getClass().getResource("main/resources/assets/Image/spr_topbuilding.png")).getImage();
        roofImage = new ImageIcon(getClass().getResource("main/resources/assets/Image/spr_roof.png")).getImage();
        floorImage = new ImageIcon(getClass().getResource("main/resources/assets/Image/spr_floor.png")).getImage();

        //==================================================Boeing initialize

        boeing = new Boeing(boeingImage);

        //==================================================Building initialize

        buildings = new ArrayList<Building>();

        //==================================================Building Distantiate;

        buildingIntervalTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spawnBuilding();
            }
        });

        //==================================================Game Procession

        gameLoopTimer = new Timer(1000/60, this);
//        gameLoopTimer.start();
//        buildingIntervalTimer.start();
    }

    public void spawnBuilding() {
        int randomisedHeight = (int)(78 + buildingY - buildingHeight/4 - Math.random()*(buildingHeight / 2));
        int room = windowHeight / 6;

        Building topBuilding = new Building((topBuildingImage));
        topBuilding.y = randomisedHeight;
        buildings.add(topBuilding);

        Building bottomBuilding = new Building((bottomBuildingImage));
        bottomBuilding.y = topBuilding.y + buildingHeight + room;
        buildings.add(bottomBuilding);
    }

    public void gameScript() {
        //==================================================Plane
        velocityY += gravity;
        boeing.y += velocityY;
        boeing.y = Math.max(boeing.y, 78);
        boeing.y = Math.min(boeing.y, floorHeight);

        //==================================================Buildings
        for (int i = 0; i < buildings.size(); i++) {
            Building building = buildings.get(i);
            building.x += velocityX;

            if (!building.passed && boeing.x > building.x + building.width){
                building.passed = true;
                score += 0.5;
            }

            if (boom(boeing, building)) {
                gameOver = true;
            }
        }

        if ( (boeing.y >= floorHeight) || (boeing.y <= roofHeight)) {
            gameOver = true;
        }
    }

    public void newGame() {
        boeing.y = boeingY;
        velocityY = 0;
        score = 0;
        buildings.clear();
        gameOver = false;
        gameLoopTimer.start();
        buildingIntervalTimer.start();
    }

    public boolean boom(Boeing boeing, Building building) {
        return (boeing.x < building.x + building.width) //plane's top left corner doesn't reach building's top right corner
                && (boeing.x + boeing.width > building.x) //plane's top right corner passes building's top left corner;
                && (boeing.y < building.y + building.height) //plane's top left corner doesn't reach building's bottom left corner
                && (boeing.y + boeing.height > building.y); //plane's bottom left corner passes b's top left corner
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameScript();
        repaint();
        if (gameOver) {
            buildingIntervalTimer.stop();
            gameLoopTimer.stop();
        }
    }

    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics) {
        //==================================================background
        graphics.drawImage(newYorkImage, -180, 601 - 2 * 142, 2 * windowWidth, 2 * 142, null);


        //==================================================plane
        graphics.drawImage(boeing.image, boeing.x, boeing.y, boeing.width, boeing.height, null);

        //==================================================Building
        for (int i = 0; i < buildings.size(); i++) {
            Building building = buildings.get(i);
            graphics.drawImage(building.image, building.x, building.y, building.width, building.height, null);
        }

        //==================================================Frame
        graphics.drawImage(floorImage, 0, 601, windowWidth, 39, null);
        graphics.drawImage(roofImage, 0, 0, windowWidth, 78, null);

        //==================================================Scoreboard
        graphics.setColor(Color.white);
        Font font = new Font("Calibri", Font.PLAIN, 32);
        String scoreText = "";
        graphics.setFont(font);

        FontMetrics fontMetrics = graphics.getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth(scoreText);
        if (gameOver) {
            scoreText = "Game Over: " + String.valueOf((int) score);
            graphics.drawString(scoreText, (windowWidth - textWidth) / 2, roofHeight + 30);
        }
        else {
            scoreText = String.valueOf((int) score);
            graphics.drawString(scoreText, (windowWidth - textWidth) / 2, roofHeight + 30);
        }
    }
}
