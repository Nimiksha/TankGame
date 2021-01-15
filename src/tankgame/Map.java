package tankgame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

//this class renders the mini-map for the tank game
public class Map extends JPanel {

    private GameController gameController;
    private Dimension mapDimension = new Dimension((int) (200),(int) (135));  //dimensions of the mini-map
    private BufferedImage backgroundImg;

    public Map(GameController gameController) {
        this.gameController = gameController;
        this.setSize(mapDimension);                                         //sets the mimi-map dimensions
        this.backgroundImg = LoadImage.loadImage("/Background.bmp");   //loads the background image for the map
    }

    //draws the game objects onto the mini-map
    public void paintComponent(Graphics graphics) {
        graphics.drawImage(backgroundImg, 0, 0, 200, 135, null);

        //iterate through the object array list of game objects amd draws them all on the mini-map
        for(int i = 0; i < gameController.getObjects().size() ; i++) {
            GameObject object = gameController.getObjects().get(i);
            //the objects are drawn smaller in size, given the coordinates and the dimensions
            graphics.drawImage(object.getImage(), (int)(object.getX_coordinate()/10), (int)(object.getY_coordinate()/10), 5, 5, null);
        }
    }

}
