package tankgame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.*;

//Driver Class
public class TankGame extends Canvas {

    //Dimensions of the game window
    public static final int Width = 1280;
    public static final int Height = 600;
    //dimensions of the game world
    public static final int GameWidth = 1900 ;
    public static final int GameHeight = 1340;
    
    private final String gameName = "TANK GAME";
    //get background image, title image and game over images
    private BufferedImage background = LoadImage.loadImage("/Background.bmp");
    private Image OpeningImage = LoadImage.loadImage("/Title.bmp");
    private Image EndGameImage = LoadImage.loadImage("/GameOver.jpg");
    private boolean running = false;
    private boolean startGame = false;
    private static boolean gameOver = false;

    private GameController controller;
    private PlayerTank player_1;
    private PlayerTank player_2;
    private Music backgroundMusic;
    private Map minimap;
    private JFrame frame;

    private String[] gameLayout = {
            "000000000000000000000000000000000000000000000000000000000000",
            "0                                                        210",
            "0                                  111                   110",
            "0                      0                                1 10",
            "0                    00      113              110         10",
            "0                                        101 11           10",
            "04       0 0                00                             0",
            "0        00         000                         3          0",
            "0                                        111               0",
            "0000                                    111                0",
            "0                          11111111111                     0",
            "0    3    111              1       4 1                     0",
            "0         111              1         1                     0",
            "0                          1      3  1                   000",
            "0               111        11110111111                  0000",
            "0       4                                              00  0",
            "0                                   3                      0",
            "0                                                          0",
            "00000                               11        3            0",
            "04                       0                                 0",
            "0                        0         01                      0",
            "0        111               0                               0",
            "0      111111             0000         01                  0",
            "0                     3                           000      0",
            "0             00                                    0      0",
            "0             1                                            0",
            "0                                                1110      0",
            "0                          0                               0",
            "0                 0                            0         110",
            "0                              0             11            0",
            "01111                          0             1             0",
            "0 111                         11111111       11            0",
            "0                                             1        4   0",
            "0                                            11            0",
            "0                                            1             0",
            "0            0                            001100000        0",
            "0          00000000              3                         0",
            "0                                                          0",
            "0             1                                            0",
            "01            1                                      4     0",
            "000000000000000000000000000000000000000000000000000000000000"};

    public TankGame() {

        controller = new GameController();
        player_1 = new PlayerTank(500, 200, "Player_1");  //setting initial placement of Tank1
        player_2 = new PlayerTank(900, 230, "Player_2");  //setting initial placement of Tank2

        controller.addObject(player_1);
        controller.addObject(player_2);
        backgroundMusic = new Music();
        backgroundMusic.playMusic();

        //setting the keys
        //for player 1 - Up: ArrowUp, Down: ArrowDown; Left: ArrowLeft; Right: ArrowRight; Shoot: Enter
        this.addKeyListener(new KeyControl(player_1, 38, 40, 37, 39, 10));
        //for player 2 - Up: W, Down: S; Left: A; Right: D; Shoot: Shift
        this.addKeyListener(new KeyControl(player_2, 87, 83, 65, 68, 32));

        //print the layout of the wall in the game
        for(int i = 0; i < gameLayout.length; i++ ) {
            String temp = gameLayout[i];
            for(int j = 0; j < temp.length(); j++ ) {
                //if the character is 0, add an unbreakable wall
                if(temp.charAt(j) == '0')
                     {
                    controller.addObject(new UnbreakableWall(j*32,i*32));
                }
                //if the character is 1, add a breakable wall
                else if(temp.charAt(j) == '1')
                     {
                    controller.addObject(new BreakableWall(j*32,i*32));
                }
                //if the character is 3, add a shield power up
                else if (temp.charAt(j) == '3'){
                    controller.addObject(new Power(j*32, i*32, PowerType.PowerShield));
                }
                //if the character is 4, add a life power up
                else if(temp.charAt(j) == '4'){
                    controller.addObject(new Power( j*32, i*32, PowerType.PowerLife));
                }
                //if it is a tab character, add nothing
                else if(temp.charAt(j) == ' ') { }
            }
        }

        //add mini-map of this tank game
        minimap = new Map(controller);
        this.initWindow(this);
    }


    public void initWindow(TankGame tankGame) {
        //class extends canvas to draw the game on JFrame
        frame = new JFrame(tankGame.gameName);
        frame.setSize(Width, Height);              //set width and height of game
        frame.setResizable(false);                 //window is not resizable
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //close the window when exited
        frame.setFocusable(true);

        //draw out the game and its components on the game window
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                graphics.drawImage(OpeningImage, 0, 0,Width, Height, null);
            }
        };
        panel.setBackground(Color.BLACK);
        panel.setLayout(null);

        //Start game button
        JButton startButton = new JButton();
        panel.add(startButton);
        startButton.setText("START GAME");
        startButton.setSize(300, 70);
        startButton.setLocation(450, 350);
        startButton.setFont(new Font("Arial" , 0, 30));
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.darkGray);
        startButton.setVisible(true);
        //Start game when the button is clicked
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame = true;
            }
        });

        //Quit game button
        JButton quitButton = new JButton();
        panel.add(quitButton);
        panel.add(quitButton);
        quitButton.setText("QUIT GAME");
        quitButton.setSize(300, 70);
        quitButton.setLocation(450, 450);
        quitButton.setFont(new Font("Arial" , 0, 30));
        quitButton.setBackground(Color.black);
        quitButton.setForeground(Color.darkGray);
        quitButton.setVisible(true);
        //Quit game when the button is clicked
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.setLocation(0,0);
        panel.setSize(Width, Height);
        panel.setVisible(true);
        frame.add(panel);
        frame.setVisible(true);
        requestFocus();

        while(true) {
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException exception) {
                exception.printStackTrace();
            }

            //start game (becomes true when the button is clicked)
            if(startGame) {
                //setup the game layout, add the objects and map
                frame.remove(panel);
                frame.add(minimap);
                frame.add(tankGame);
                frame.pack();
                minimap.setLocation(1085,0);   //set minimap on the tp right side of the game window
                requestFocus();
                frame.setSize(Width, Height);
                tankGame.start();                    //start the game
                startGame = false;                   //reset the startGame bool value to false
            }
        }
    }

    private void update() {
        controller.update();
    }

    private void render() {
        BufferStrategy strategy = getBufferStrategy();
        if (strategy == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics drawGraphics = strategy.getDrawGraphics();
        drawGraphics.setColor(Color.BLACK);
        drawGraphics.fillRect(0, 0, Width, Height);

        //if game is over
        if(gameOver) {
            String winner;
            //based on which tank have higher score, print the winner
            if(player_1.getScore() > player_2.getScore()) {
                winner = "Tank One";
            }
            else {
                winner = "Tank Two";
            }
            frame.remove(minimap);     //remove the mini-map from the game over screen
            drawGraphics.drawImage(EndGameImage, 0, 0, Width, Height, null);     //display the Game Over window
            drawGraphics.setColor(Color.WHITE);
            drawGraphics.setFont(new Font("Arial" , 0, 60));
            drawGraphics.drawString(winner + " Wins!", 400, 500);
        }
        //if game is not over, keep playing
        else {
            drawGraphics.drawImage(background, 0, 0,(Width - 8)/2, Height, null);
            drawGraphics.drawImage(background, (Width / 2) + 4, 0,(Width - 8) / 2, Height, null);
            minimap.repaint();
            controller.render(drawGraphics);
        }

        drawGraphics.dispose();
        strategy.show();
    }

    //credit: Help from a Ajay Mahajan
    //start the game
    public void start() {
        final int ticksPerSecond = 60;
        final int timePerTick = 1000/ticksPerSecond;
        final int maxSkip = 5;
        long nextTick = System.currentTimeMillis();
        int loops ;
        running = true;

        //based on the time
        while(running) {
            loops = 0;
            while(System.currentTimeMillis() > nextTick  && loops < maxSkip) {
                this.removeObjects();
                update();
                nextTick += timePerTick;
                loops++;
            }
            render();
        }
    }

    //remove the objects from the screen
    public void removeObjects() {
        for(int i = 0; i < controller.getObjects().size(); i++) {
            GameObject object = controller.getObjects().get(i);
            if(object.getRemoval()) {
                controller.removeObject(object);
            }
        }
    }

    //set game over to be true or false
    public static void setGameOver(boolean gameOver) {
        TankGame.gameOver = gameOver;
    }

    //main method. Kicks the Tank Game into action
    public static void main(String[] args) {
        new TankGame();
    }

}