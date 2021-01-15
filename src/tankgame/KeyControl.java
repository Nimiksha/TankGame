package tankgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//sets the gaming controls i.e. links the keys to their functions
public class KeyControl extends KeyAdapter {

     private PlayerTank tank;
     private final int up;
     private final int down;
     private final int right;
     private final int left;
     private final int shoot;

     public KeyControl(PlayerTank tank, int up, int down, int left, int right, int shoot) {
         this.tank = tank;
         this.up = up;
         this.down = down;
         this.left = left;
         this.right = right;
         this.shoot = shoot;
     }

     //method that sets the action when respective keys are pressed
     //It sets the task/movement in motion when the key is pressed
     @Override
     public void keyPressed(KeyEvent e) {

         int key = e.getKeyCode();    //get the key that is pressed

         if(key == up) {
             tank.setUp(true);
         }
         else if(key == down) {
             tank.setDown(true);
         }
         else if (key == left) {
             tank.setLeft(true);
         }
         else if (key == right) {
             tank.setRight(true);
         }
         else if(key == shoot) {
             tank.setShoot(true);
         }
     }

     //method that sets the action when respective keys are released
     //It stops the action from being performed when the key is released by setting boolean values to false
     @Override
     public void keyReleased(KeyEvent e) {

         int key = e.getKeyCode();    //get the key that is pressed

         if(key == up) {
             tank.setUp(false);
         }
         else if(key == down) {
             tank.setDown(false);
         }
         else if (key == left) {
             tank.setLeft(false);
         }
         else if (key == right) {
             tank.setRight(false);
         }
         else if(key == shoot) {
             tank.setShoot(false);
         }
     }

 }
