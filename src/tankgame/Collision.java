package tankgame;

import java.awt.Rectangle;

//defines what happens when the objects (bullets, tanks, walls, power ups) in the game collide/intersect with each other
public class Collision {

    private Music music = new Music();           //create an object of Music class to insert music effects on collisions

    /*
    Collision() checks for collisions and their types in the Tank Game
    We assume the things colliding (intersection of rectangles) are a tank and some other object -
    either a tank, a wall or a Power up feature */
    public void Collision(GameObject tank, GameObject object) {

        Rectangle rec1 = tank.getRectangle();    //get the rectangle of the tank
        Rectangle rec2 = object.getRectangle();  //get the rectangle of the other object

        int speedX = ((Tank) tank).getSpeedX();  //speed of object in X direction (casting the Game objects to type tank)
        int speedY = ((Tank) tank).getSpeedY();  //speed of object in Y direction (casting the Game objects to type tank)

        //Check Reverse collision
        Rectangle rec1Reverse = new Rectangle(tank.getX_coordinate()-speedX, tank.getY_coordinate()-speedY ,
                                             tank.getImageWidth(), tank.getImageHeight());

        //Check Forward collision
        Rectangle rec1Forward = new Rectangle(tank.getX_coordinate()+ speedX, tank.getY_coordinate()+speedY ,
                                             tank.getImageWidth(), tank.getImageHeight());

        //if object is a tank
        if(object instanceof Tank) {

            //check forward and reverse collisions
            if(rec1Forward.intersects(rec2)) {
                ((Tank) tank).setForwardCollision(true);
            }
            else if (rec1Reverse.intersects(rec2)) {
                ((Tank) tank).setReverseCollision(true);
            }

            //Check for collision of ammunition's, from the tank -- i.e see if a bullet hits the other tank
            //iterate through the ammo array list for the tanl
            for (int i = 0; i < ((Tank) tank).getAmmoList().size(); i++) {
                GameObject ammoObject = ((Tank) tank).getAmmoList().get(i);  //get the bullet object(s)
                Rectangle ammoRec = ammoObject.getRectangle();               //get the rectangle of the ammunition

                //if the bullet hits the tank, give a explosion sound and remove the bullet object
                if(rec2.intersects(ammoRec)) {
                    music.explosion();
                    ammoObject.setRemoval(true);

                    //for the bullet from the tank increase the tank(shooter's) score by 10
                    // and decrease the health of the tank hit by 5
                    if(ammoObject instanceof Bullet) {
                            ((Tank) tank).incScore(10);
                            ((Tank) object).decHealth(5);
                    }
                }
            }
        }

        //if object is a breakable wall
        else if (object instanceof BreakableWall) {

            //check forward and reverse collisions
            if(rec1Forward.intersects(rec2)) {
                ((Tank) tank).setForwardCollision(true);
            }
            else if (rec1Reverse.intersects(rec2)) {
                ((Tank) tank).setReverseCollision(true);
            }

            //iterating through the ammo list (which are the bullets fired) of the tank
            for(int i = 0; i < ((Tank) tank).getAmmoList().size(); i++) {
                GameObject ammoObject = ((Tank) tank).getAmmoList().get(i);
                Rectangle ammoRec = ammoObject.getRectangle();

                //if an ammunition hits the wall, explosion sound, along with removal of both the wall and the ammunition.
                // Also the tank player gets 5 points
                if(rec2.intersects(ammoRec)) {
                    music.explosion();
                    ammoObject.setRemoval(true);
                    object.setRemoval(true);
                    ((Tank) tank).incScore(5);
                }
            }
        }

        //if object is a unbreakable wall
        else if (object instanceof UnbreakableWall) {

            //check forward and reverse collisions
            if(rec1Forward.intersects(rec2)) {
                ((Tank) tank).setForwardCollision(true);
            }
            else if (rec1Reverse.intersects(rec2)) {
                ((Tank) tank).setReverseCollision(true);
            }
            
            for(int i = 0; i<((Tank) tank).getAmmoList().size(); i++) {
                GameObject ammoObject = ((Tank) tank).getAmmoList().get(i);
                Rectangle ammoRec = ammoObject.getRectangle();

                //if an ammunition hits the wall, no points given and the wall is not removed. only the bullet is removed
                if(rec2.intersects(ammoRec)) {
                    music.explosion();
                    ammoObject.setRemoval(true);
                }
            }
        }

        //if object is a Life power up
        else if (object instanceof Power && ((Power) object).getPowerType() == PowerType.PowerLife) {

            //if the tank rectangle intersects the life powerUp rectangle, the tank gains 1 life
            if(rec1.intersects(rec2)) {
                ((Tank) tank).setLifePower(true);
                object.setRemoval(true);             //remove the power up object from the screen
                music.powerUpSound();
                ((Tank) tank).lifeBoost();
            }
        }

        //if object is a Shield power up
        else if (object instanceof Power && ((Power) object).getPowerType() == PowerType.PowerShield) {

            //if the tank rectangle intersects the shield powerUp rectangle, the tank health increases by 20 points
            if(rec1.intersects(rec2)) {
                ((Tank) tank).setShieldPower(true);
                object.setRemoval(true);             //remove the power up object from the screen
                music.powerUpSound();
                ((Tank) tank).incHealth();
            }
        }
    }

}