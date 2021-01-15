package tankgame;

import java.awt.Graphics;
import java.util.ArrayList;

//establishes the controls in the game
public class GameController {

    private ArrayList<GameObject> objects;     //array list to store all the objects in the game
    private Collision collision;

    public GameController() {
        objects = new ArrayList<>();
        collision = new Collision();
    }

    //returns the list of all objects in the array list
    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    //recursively calls the update method on all objects of the tank game
    public void update() {
        for(int i = 0; i < objects.size(); i++) {              //iterate through the array list
            GameObject gameObject1 = objects.get(i);

            if(gameObject1 instanceof Tank) {                 //check if the object is a tank object

                for(int j = 0; j < objects.size(); j++) {

                    if(j != i) {
                        GameObject gameObject2 = objects.get(j);
                        collision.Collision(gameObject1, gameObject2);  //check the collisions between the tank and the other objects
                    }
                }
            }
            gameObject1.update();
        }
    }

    //creates the split screen view of the tank game
    public void render(Graphics graphics) {

        int tank1_x1, tank1_x2, tank1_y1, tank1_y2;      //X and Y coordinates for Tank1
        int tank2_x1, tank2_x2, tank2_y1, tank2_y2;      //X and Y coordinates for Tank2

        //sets the placement of objects based on their X coordinates
        if (objects.get(0).getX_coordinate() < (TankGame.Width - 6) / 4) {  //tank (width - 6) to create a 6 pixel divider line
            tank1_x2 = 0;
            tank1_x1 = (TankGame.Width - 6) / 2;
        }
        else if(objects.get(0).getX_coordinate() > (TankGame.GameWidth - (TankGame.Width - 6) / 4)) {
            tank1_x1 = TankGame.GameWidth;
            tank1_x2 = TankGame.GameWidth - (TankGame.Width - 6) / 2;
        }
        else {
            tank1_x1 = objects.get(0).getX_coordinate() + (TankGame.Width - 6) / 4;
            tank1_x2 = objects.get(0).getX_coordinate() - (TankGame.Width - 6) / 4;
        }

        //sets the placement of objects based on their Y coordinates
        if (objects.get(0).getY_coordinate() < TankGame.Height/2) {
            tank1_y2 = 0;
            tank1_y1 = TankGame.Height;
        }
        else if(objects.get(0).getY_coordinate() > (TankGame.GameHeight - TankGame.Height/2)) {
            tank1_y1 = TankGame.GameHeight;
            tank1_y2 = TankGame.GameHeight - TankGame.Height;
        }
        else {
            tank1_y1 = objects.get(0).getY_coordinate() + TankGame.Height/2;
            tank1_y2 = objects.get(0).getY_coordinate() - TankGame.Height/2;
        }

        //similar method repeated for Tank 2 -- X coordinate (width)
        if (objects.get(1).getX_coordinate() < (TankGame.Width - 6) / 4) {
            tank2_x2 = 0;
            tank2_x1 = (TankGame.Width - 6) / 4;
        }
        else if(objects.get(1).getX_coordinate() > (TankGame.GameWidth - (TankGame.Width - 6 ) / 4) ) {
            tank2_x1 = TankGame.GameWidth;
            tank2_x2 = TankGame.GameWidth - (TankGame.Width - 6) / 2;
        }
        else {
            tank2_x1 = objects.get(1).getX_coordinate() + (TankGame.Width - 6) / 4;
            tank2_x2 = objects.get(1).getX_coordinate() - (TankGame.Width - 6) / 4;
        }

        //similar method repeated for Tank 2 -- Y coordinate (height)
        if (objects.get(1).getY_coordinate() < TankGame.Height/2) {
            tank2_y2 = 0;
            tank2_y1 = TankGame.Height;
        }
        else if(objects.get(1).getY_coordinate() > (TankGame.GameHeight - TankGame.Height/2)) {
            tank2_y1 = TankGame.GameHeight;
            tank2_y2 = TankGame.GameHeight - TankGame.Height;
        }
        else {
            tank2_y1 = objects.get(1).getY_coordinate() + TankGame.Height/2;
            tank2_y2 = objects.get(1).getY_coordinate() - TankGame.Height/2;
        }

        //iterate through the array list and render them on the split screens based on their coordinates
        for(int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);

            //left screen
            if(tank1_x2 <= object.getX_coordinate() &&
                    object.getX_coordinate() + object.getImageWidth() <= tank1_x1 &&
                    tank1_y2 <= object.getY_coordinate() &&
                    object.getY_coordinate() <= tank1_y1) {
                objects.get(i).render(graphics,tank1_x2,tank1_y2);
            }

            //right screen
            if(tank2_x2 <= object.getX_coordinate() &&
                    object.getX_coordinate() < tank2_x1 &&
                    tank2_y2 <= object.getY_coordinate() &&
                    object.getY_coordinate() <= tank2_y1) {
                objects.get(i).render(graphics,(tank2_x2-TankGame.Width/2 + 4),tank2_y2);
            }

            //placing tanks on the screens
            if(object instanceof Tank) {
                ((Tank) object).ammoRender(graphics, tank1_x1, tank1_x2, tank1_y1, tank1_y2, tank2_x1, tank2_x2, tank2_y1, tank2_y2);
            }
        }
    }

    //add more game objects to the array list
    public void addObject(GameObject object) {
        objects.add(object);
    }

    //remove game objects from the array list. will be used to remove objects from the screen after collisions
    public void removeObject(GameObject object) {
        objects.remove(object);
    }

}