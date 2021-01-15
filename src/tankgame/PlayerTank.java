package tankgame;

//for the player tanks (extend the Tank class which is a Game Object)
public class PlayerTank extends Tank{

    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;
    private boolean shoot;

    PlayerTank(int x, int y, String tankId){
        super(x, y, tankId);
    }

    //to update the movement of the player tanks
    public void update() {

        if (this.up) {
            this.move(Direction.UP);
        }

        if (this.down) {
            this.move(Direction.DOWN);
        }

        if (this.left) {
            this.rotateAntiClockwise();
        }

        if (this.right) {
            this.rotateClockwise();
        }

        if(this.shoot) {
            this.shoot();   //shoot bullets
        }

        this.setForwardCollision(false);
        this.setReverseCollision(false);


       //iterate through the array list of ammunition for the tank
        for(int i = 0; i < this.getAmmoList().size(); i++) {
            //update the ammunition i.e removes the bullets after they are shot
            if(this.getAmmoList().get(i).removeObj) {
                this.getAmmoList().remove(i);
            }
            //call to update() in the Ammo class to update the bullets
            else {
                this.getAmmoList().get(i).update();
            }
        }
    }

    //rotates the tank by an angle of 3 (to the right)
    private void rotateClockwise() {
        this.setAngle(3);
    }

    //rotates the tank by an angle of -3 (to the left)
    private void rotateAntiClockwise() {
        this.setAngle(-3);
    }

    //values based on key controls - key pressed or released set the values true or false for the action to happen
    public void setUp(boolean value) {
        this.up = value;
    }

    public void setDown(boolean value) {
        this.down = value;
    }

    public void setRight(boolean value) {
        this.right = value;
    }

    public void setLeft(boolean value)  {
        this.left = value;
    }

    public void setShoot(boolean value)  {
        this.shoot = value;
    }

}