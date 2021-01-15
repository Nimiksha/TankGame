package tankgame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

//Defines/builds the bullet object of the tank game. It extends the abstract Ammo class
public class Bullet extends Ammo {

    private static Image bulletImage = LoadImage.loadImage("/Bullet.gif");     //loads the image of a bullet
    private static int imageWidth = bulletImage.getWidth(null);            //width of the image
    private static int imageHeight = bulletImage.getHeight(null);          //height of the image

    //x and y coordinates, along with the angle at which the bullet will be shot
    Bullet(int x, int y, double angle) {
        super(x, y, angle);
    }

    //makes the bullet with the image and the coordinates
    @Override
    public void render(Graphics graphics, int xOffset, int yOffset) {
        graphics.drawImage(bulletImage,this.x_coordinate - xOffset,this.y_coordinate - yOffset, null);
    }

    //creates the rectangle for the bullets (for collision detection)
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(this.x_coordinate,this.y_coordinate, Bullet.imageWidth, Bullet.imageHeight);
    }

    //returns the image of the Bullet
    @Override
    public Image getImage() {
        return Bullet.bulletImage;
    }

    //returns the width of the image
    @Override
    public int getImageWidth() {
        return Bullet.imageWidth;
    }

    //returns the height of the image
    @Override
    public int getImageHeight() {
        return Bullet.imageHeight;
    }

}