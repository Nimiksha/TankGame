package tankgame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

//abstract class for all the objects in the tank game - tanks, walls, bullets and power ups
public abstract class GameObject {

    protected int x_coordinate, y_coordinate;      //x(horizontal) and y(vertical) coordinates of the screen
    protected boolean removeObj = false;           //for removing game objects

    //for the placement of the object on the screen
    GameObject(int x_coordinate, int y_coordinate){
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
    }

    //to update the functionality or behaviour of/triggered by the object
    public abstract void update();

    //to render/make the various objects on the gam screen
    public abstract void render(Graphics graphics, int xOffset, int yOffset);

    //return the rectangle of the objects
    public abstract Rectangle getRectangle();

    //return the image of the objects
    public abstract Image getImage();

    //return the image width of the objects
    public abstract int getImageWidth();

    //return the image height of the objects
    public abstract int getImageHeight();

    //return the horizontal location of the object on the game screen
    public int getX_coordinate() {
        return this.x_coordinate;
    }

    //return the vertical location of the object on the game screen
    public int getY_coordinate() {
        return this.y_coordinate;
    }

    //set the horizontal location of the object on the game screen
    public void setX_coordinate(int x_coordinate) {
        this.x_coordinate = x_coordinate;
    }

    //set the vertical location of the object on the game screen
    public void setY_coordinate(int y_coordinate) {
        this.y_coordinate = y_coordinate;
    }

    //returns true/false based on if the object is to be removed
    public boolean getRemoval() {
        return this.removeObj;
    }

    //sets the removal of objects to true or false based on if the object needs to be removed after collision
    public void setRemoval(boolean val) {
        this.removeObj = val;
    }

}