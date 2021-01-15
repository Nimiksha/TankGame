package tankgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

//to display the statistics of the tanks
public class TankStats {

    private int health;
    private int score;
    private int life;

    //constructor to initialize Player Health to 100%, set score to be 0 and allots 3 lives
    public TankStats() {
        this.score = 0 ;
        this.health = 100;
        this.life = 3;
    }

    //Display the stats for Players
    public void render(int x, int y, Graphics graphics, String player) {

        Font font = new Font("Arial", 0, 20);  //set font for the text (Player stats)

        //edge cases for health
        if(this.health < 0 || this.health > 100) {
            health = 100;
        }

        //set the health bar
        graphics.setColor(new Color(75, this.health * 2, 0));  //set the color(rgb) for the health bar
        graphics.fillRect(x, y, this.health * 2, 20 );     //as the value of health decreases, the color in the bar changes
        graphics.setColor(Color.black);                                  //set border color
        graphics.drawRect(x, y, 200, 20 );                 //draw border for the bar

        //set the layout to display Player stats - the score, the # of lives remaining and the Tank ID
        //the x and y mark the coordinate placement of the stats
        graphics.setFont(font);
        graphics.setColor(Color.white);
        graphics.drawString("Score: " + score, x, y + 60);
        graphics.drawString("Life: " + life, x, y + 80);
        graphics.drawString(player, x, y + 100);


    }

    //Decreases the Health and reduces # of lives remaining when shot at
    public void StatusUpdate(int decrement) {

        //decrease the health quotient by the value
        this.health -= decrement;

        //if health becomes 0 , while there are still lives remaining, decrease life
        if(this.health < 0 && this.life > 0) {
            this.health = 100;            //reset health back to 100, for the new life in play
            this.life -- ;                //reduce value of life by 1

            //if all lives are used up, declare Game to be over
            if(this.life == 0) {
                TankGame.setGameOver(true);
                System.out.println("GAME. OVER.");
            }
        }
    }

    //Boosts the Players' score
    public void boostScore(int increment) {
        this.score += increment;

        //edge case - To ensure that if the score is not a -ve value
        if(this.score < 0) {
            this.score = 0;
        }
    }

    //Add a life (power up)
    public void addLife() {
        this.life ++;
    }

    //Boosts the Players' health (power up)
    public void incHealth() {
        this.health += 20;
        //edge case
        if(this.health > 100) {
            this.health = 100;
        }
    }

    //Returns the Players' score
    public int getScore() {
        return this.score;
    }

    //Returns the # of lives remaining
    public int getLivesRemaining() {
        return this.life;
    }

}
