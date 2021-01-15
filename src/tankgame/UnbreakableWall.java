package tankgame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

//Builds the Unbreakable wall structure in the game
public class UnbreakableWall extends GameObject {

    private static final Image unbreakableWallImage = LoadImage.loadImage("/UnbreakableWall.gif");  //loads the image of the unbreakable wall
    private static int imageWidth = unbreakableWallImage.getWidth(null);          //width of the image
    private static int imageHeight = unbreakableWallImage.getHeight(null);        //height of the image


    UnbreakableWall(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {
    }

    //makes the unbreakable wall structure with the image and the coordinates
    @Override
    public void render(Graphics graphics, int xOffset, int yOffset) {
        graphics.drawImage(unbreakableWallImage, this.x_coordinate - xOffset,this.y_coordinate -yOffset, null);
    }

    //creates the rectangle for the unbreakable walls (for collision detection)
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(this.x_coordinate, this.y_coordinate, UnbreakableWall.imageWidth, UnbreakableWall.imageHeight);
    }

    //returns the image of Unbreakable Wall
    @Override
    public Image getImage() {
        return UnbreakableWall.unbreakableWallImage;
    }

    //returns the width of the image
    @Override
    public int getImageWidth() {
        return UnbreakableWall.imageWidth;
    }

    //returns the height of the image
    @Override
    public int getImageHeight() {
        return UnbreakableWall.imageHeight;
    }

}