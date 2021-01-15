
package tankgame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.HashMap;

//structures the power ups in the tank game
public class Power extends GameObject {

    //image and dimensions of the power up features
    private Image image;
    private static int imageHeight = 1;
    private static int imageWidth = 1;

    private PowerType powerType;            //type of power up

    private boolean lifePowerUp = false;
    private boolean shieldPowerUp = false;

    //return the type of power up that is being used
    public PowerType getPowerType() {
        return powerType;
    }

    //hash map that stores images for power ups. the key is the type of power up and the value is the image
    public static HashMap<PowerType, Image> PowerUpImage = new HashMap<>();
    static {
        PowerUpImage.put(PowerType.PowerLife, LoadImage.loadImage("/LifePowerUp.png"));
        PowerUpImage.put(PowerType.PowerShield, LoadImage.loadImage("/ShieldPowerUp.png"));
    }

    //set the coordinates, the type and the image for the power ups
    Power(int x, int y, PowerType type) {
        super(x,y);
        this.powerType = type;
        this.image = PowerUpImage.get(type);
    }

    @Override
    public void update() {}

    //makes the power up structure with the image and the coordinates
    @Override
    public void render(Graphics graphics, int xOffset, int yOffset) {
        graphics.drawImage(image,this.x_coordinate -xOffset ,this.y_coordinate -yOffset, null);
    }

    //creates the rectangle for the power ups (for collision detection)
    public Rectangle getRectangle() {
        return new Rectangle(this.x_coordinate,this.y_coordinate,imageWidth,imageHeight);
    }

    //returns the image of the power up
    @Override
    public Image getImage() {
        return this.image;
    }

    //returns the width of the image
    @Override
    public int getImageWidth() {
        return Power.imageWidth;
    }

    //returns the height of the image
    @Override
    public int getImageHeight() {
        return Power.imageHeight;
    }

    //returns the life power up
    public boolean getLifePower() {
        return lifePowerUp;
    }

    //sets the life power up
    public void setLifePower(boolean val) {
        this.lifePowerUp = val ;
    }

    //returns the shield power up
    public boolean getShieldPower() {
        return shieldPowerUp;
    }

    //sets the shield power up
    public void setShieldPower(boolean val) {
        this.shieldPowerUp = val ;
    }

}