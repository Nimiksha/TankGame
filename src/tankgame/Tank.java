package tankgame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

//Builds the tank structure in the game
public abstract class Tank extends GameObject{

    //declaring variables for speed, speed in x direction, speed in y direction and the angle at which they move
    private int speed;
    private int speedX;
    private int speedY;
    private int angle;

    private String tankID;                          //tank name - tank 1 or 2

    //all the power ups and collisions are set to be false
    private boolean lifePowerUpActive = false;
    private boolean shieldPowerUpActive = false;
    private boolean forwardCollision = false;
    private boolean reverseCollision = false;

    //new object of Tank Stats
    private TankStats tankStats = new TankStats();

    //get the image of the tank and its width and height
    private static Image image = LoadImage.loadImage("/Tank.png");
    private static int  imageWidth = image.getWidth(null);
    private static int  imageHeight = image.getHeight(null);

    //time when key to shoot was pressed
    private long keyPressedTime = System.currentTimeMillis();

    //array list to store all the bullets - for the tank
    private ArrayList<Ammo> ammoList;

    //returns the id of the tank i.e tank 1 or tank 2
    public String getTankId() {
        return tankID;
    }

    //sets the speed of the tank at 2 and creates an array list of ammunition for the tank
    Tank(int x, int y, String tankId){
        super(x, y );
        this.tankID = tankId;
        this.speed = 2;
        this.ammoList = new ArrayList<>();
    }

    //draws out the tank
    @Override
    public void render(Graphics graphics, int xOffset, int yOffset) {
        //
        AffineTransform transform = AffineTransform.getTranslateInstance(this.getX_coordinate()-xOffset, this.getY_coordinate()-yOffset);
        //
        transform.rotate(Math.toRadians(this.angle), image.getWidth(null)/2, image.getHeight(null)/2);

        //
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(image, transform,null);

        //places the stats chart for the 2 players in the game
        if(this.getTankId().equals("Player_1")) {
            this.tankStats.render(50, 50, graphics2D, "Player 1");
        }
        else {
            this.tankStats.render(280, 50, graphics2D, "Player 2");
        }
    }

    //creates the rectangle for the tanks (for collision detection)
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(this.x_coordinate,this.y_coordinate,Tank.imageWidth,Tank.imageHeight);
    }

    //returns the image of the tank
    @Override
    public Image getImage() {
        return Tank.image;
    }

    //returns the width of the image
    @Override
    public int getImageWidth() {
        return Tank.imageWidth;
    }

    //returns the height of the image
    @Override
    public int getImageHeight() {
        return Tank.imageHeight;
    }

    //draws out the bullets
    public void ammoRender(Graphics graphics, int x11 , int x12, int y11, int y12, int x21, int x22, int y21, int y22  ) {

        //iterate through the ammunition array list
        for(Ammo i : ammoList) {
            //shoot from tank 1 and tank 2 based on the coordinates
            if(x12 <= i.getX_coordinate() && i.getX_coordinate() <= x11 &&
                    y12 <= i.getY_coordinate() && i.getY_coordinate() <= y11) {
                i.render(graphics,x12,y12);
            }
            if(x22 <= i.getX_coordinate() && i.getX_coordinate() <= x21 &&
                    y22 <= i.getY_coordinate() && i.getY_coordinate() <= y21) {
                i.render(graphics,x22-TankGame.Width/2,y22);
            }
        }
    }

    //the method defines the movement directions of the tank
    protected void move(Direction dir) {

        //up direction
        if(dir == Direction.UP) {
            speedX = (int) Math.round(speed * Math.cos(Math.toRadians(this.angle)));
            speedY = (int) Math.round(speed * Math.sin(Math.toRadians(this.angle)));

            if(!(this.forwardCollision)) {
                x_coordinate += speedX;
                y_coordinate += speedY;
            }
        }

        //down direction
        else if(dir == Direction.DOWN) {
            speedX = (int) Math.round(speed * Math.cos(Math.toRadians(this.angle)));
            speedY = (int) Math.round(speed * Math.sin(Math.toRadians(this.angle)));

            if(!(this.reverseCollision)) {
                x_coordinate -= speedX;
                y_coordinate -= speedY;
            }
        }
    }

    //shoots bullets from the tank
    public void shoot() {
        long currentTimeMillis = System.currentTimeMillis();                 //get the current time in Millis
        long timeBetweenActions = currentTimeMillis - keyPressedTime;        //difference between current time and time when key to shoot was pressed

        if (timeBetweenActions > 100) {
            int bulletXcord = this.getX_coordinate() + (this.getImageWidth()/2);
            int bulletYcord = this.getY_coordinate()+(this.getImageHeight()/2);

                ammoList.add(new Bullet(bulletXcord, bulletYcord, getAngle()));     //add to the ammo array list of the tank
                keyPressedTime = System.currentTimeMillis();
        }
    }

    //returns the angle of the tank
    public double getAngle() {
        return this.angle;
    }

    //sets the angle of the tank when the tank is movement clockwise or anticlockwise
    public void setAngle(int val) {
        this.angle += val;
    }

    //return the ammunition array list
    public ArrayList<Ammo> getAmmoList(){
        return this.ammoList;
    }

    //increase the score for the player tank
    public void incScore(int val) {
        this.tankStats.boostScore(val);
    }

    //increase life count when power up is picked
    public void lifeBoost() {
          this.tankStats.addLife();
       }

    //increase health when power up is picked
    public void incHealth() {
        this.tankStats.incHealth();
    }

    //decrease health when shot at
    public void decHealth(int val) {
        this.tankStats.StatusUpdate(val);
    }

    //set life power up to be true or false
    public void setLifePower(boolean val) {
        this.lifePowerUpActive = val;
    }

    //set shield power up to be true or false
    public void setShieldPower(boolean val) {
        this.shieldPowerUpActive = val;
    }

    //return tank speed in X direction
    public int getSpeedX() {
        return this.speedX;
    }

    //return tank speed in Y direction
    public int getSpeedY() {
        return this.speedY;
    }

    //return tank score
    public int getScore() {
        return this.tankStats.getScore();
    }

    //return lives remaining
    public int getLife() {
        return this.tankStats.getLivesRemaining();
    }

    //set forward collisions to be true or false
    public void setForwardCollision(boolean val) {
        this.forwardCollision = val;
    }

    //set reverse collisions to be true or false
    public void setReverseCollision(boolean val) {
        this.reverseCollision = val;
    }

}