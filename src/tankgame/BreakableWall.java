package tankgame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

//Builds the Breakable wall structure in the game
public class BreakableWall extends GameObject {

    private static final Image breakableWallImage = LoadImage.loadImage("/BreakableWall.gif");   //loads the image of a breakable wall
    private static int imageWidth = breakableWallImage.getWidth(null);          //width of the image
    private static int imageHeight = breakableWallImage.getHeight(null);        //height of the image

    BreakableWall(int x, int y) {
        super(x, y);
    }    //x and y coordinates

    @Override
    public void update() {}

    //makes the breakable wall structure with the image and the coordinates
    @Override
    public void render(Graphics graphics, int xOffset, int yOffset) {
        graphics.drawImage(breakableWallImage,this.x_coordinate - xOffset, this.y_coordinate - yOffset, null);
    }

    //creates the rectangle for the breakable walls (for collision detection)
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(this.x_coordinate, this.y_coordinate, BreakableWall.imageWidth, BreakableWall.imageHeight);
    }

    //returns the image of Breakable Wall
    @Override
    public Image getImage() {
        return BreakableWall.breakableWallImage;
    }

    //returns the width of the image
    @Override
    public int getImageWidth() {
        return BreakableWall.imageWidth;
    }

    //returns the height of the image
    @Override
    public int getImageHeight() {
        return BreakableWall.imageHeight;
    }

}