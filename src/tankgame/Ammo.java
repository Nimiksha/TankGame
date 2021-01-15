package tankgame;

import tankgame.GameObject;

//abstract class for bullets meant for shooting
public abstract class Ammo extends GameObject {

    private final int speed = 10;    //initial speed initialized
    private int speedX;              //speed in x direction
    private int speedY;              //speed in y direction

    //constructor to initialize the speedX and speedY
    Ammo(int x, int y, double angle){
        super(x-6,y-6);
        this.speedX = (int) (speed * Math.cos(Math.toRadians(angle)));  //(x) speed based on the angle (in radians)
        this.speedY = (int) (speed * Math.sin(Math.toRadians(angle)));  //(y) speed based on the angle (in radians)
    }

    //updates for functionality of Ammunition elements
    @Override
    public void update() {
        //if the object is not removed, change the coordinates as per the speed in X and Y direction
        //allows the bullets to be shot
        if(this.getRemoval()) { }
        else {
            x_coordinate += speedX;
            y_coordinate += speedY;
        }
    }

}
