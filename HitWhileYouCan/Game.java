import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;

public class Game extends JPanel implements Runnable, KeyListener {
        public static final int GAME_WIDTH = 1000;
        public static final int GAME_HEIGHT = 600;
 
        private String[] instructions = { "'A' to start the game", "WASD or Arrow Keys for Player Movement", "SPACE to Start/Reset ball", "ESC to quit", " ", 
		"Note: Lesser the number of hitting chances, ", "more is the number of points you score in each goal!",
                                                                        " "};
        private String[] instructions1 = { "'A' for next Session", "WASD or Arrow Keys for Player Movement", "SPACE to Start/Reset ball", "ESC to quit", " ", 
		 "Note: Lesser the number of hitting chances, ", "more is the number of points you score in each goal!",
                                                                        " ", "Current winner: Player One", "Score: "};
        private String[] instructions2 = { "'A' for next Session", "WASD or Arrow Keys for Player Movement", "SPACE to Start/Reset ball", "ESC to quit", " ", 
		 "Note: Lesser the number of hitting chances, ", "more is the number of points you score in each goal!",
                                                                        " ", "Current winner: Player Two", "Score: "};
        private String[] instructions3 = { "'A' for next Session", "WASD or Arrow Keys for Player Movement", "SPACE to Start/Reset ball", "ESC to quit", " ", 
		 "Note: Lesser the number of hitting chances, ", "more is the number of points you score in each goal!",
                                                                        " ", "Tie", "Score: "};
        private String[] instructions4 = { "'A' to proceed further	", "WASD or Arrow Keys for Player Movement", "SPACE to Start/Reset ball", "ESC to quit", " ", 
		 "Note: Lesser the number of hitting chances, ", "more is the number of points you score in each goal!",
                                           " ", "Game Over"};
 
        private static boolean menu = true;
		public static int menuCount = 0;
        private static boolean game = false;
		public static int judge = 0;
 
        private Thread t;
        private boolean running = false;
 
        public static int scoreOne;
        public static int scoreTwo;
        public static String scoreOneString;
        public static String scoreTwoString;
 
        protected Player player;
        protected Ball ball;
        protected static int ballSpeed;
		
		public static int count;
		public static int alternative = 0;
		
		public static void setMenuState(boolean menu){
			Game.menu = menu;
		}
		public static void setGameState(boolean game){
			Game.game = game;
		}             
 
        // BOOLEANS THAT WILL BE USED FOR SMOOTHER MOVEMENT
        private boolean right = false, left = false, left1 = false, up = false, down = false, space = false;
 
        public Game() {
                player = new Player(0, 360, 15, 200, ID.PLAYER);
                ball = new Ball(0, 0, 50, 50, ID.BALL);
				count = 0;
        }
 
        // INITIALIZES NEW THREAD AND STARTS IT (MAKES IT CALL RUN METHOD)
        private void start() {
                if (t == null) {
                        t = new Thread(this);
                }
                t.start();
                running = true;
        }
 
        // MAIN GAME LOOP (NEEDS REVISING)
        public void run(){
                while (running){
                        updateLogic();
                        repaint();
 
                        try {
                                Thread.sleep(2);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }
        }
 
        //UPDATES ALL OBJECT POSITIONS AND USED TO DECIDE GAME STATE
        public void updateLogic() {
                if (menu){
                        if (left1) {
                                menu = false;
                                game = true;
                        }
                } else if (game) {
                        // X DIRECTION MOVEMENT PLAYER 
							if (right)
                                player.xSpeed = 3;
                        else if (left)
                                player.xSpeed = -3;
                        else
                                player.xSpeed = 0;               
 
                        // Y DIRECTION MOVEMENT PLAYER 
                        if (down)
                                player.ySpeed = 3;
                        else if (up)
                                player.ySpeed = -3;
                        else
                                player.ySpeed = 0;
							
						judge = RollDicePanel.getDie().getValue();
 
                        if (ball.isCollidingPlayer(player) && count != (RollDicePanel.getDie().getValue() + 1)) {                          
                                ball.x = 15;
                                ball.xSpeed--;                                      
                                ball.xSpeed = -ball.xSpeed;
								count++;
                        }
						else if(count == (RollDicePanel.getDie().getValue() + 1)){
							menu = true;
							game = false;
							menuCount++;
							alternative++;
							count = 0;
							ball.reset();
						}
                        if (ball.isCollidingElseWhere()) {
                                ball.x = GAME_WIDTH - 21 - ball.WIDTH;             
                                ball.xSpeed = -ball.xSpeed;
                        }
 
                        // UPDATE OBJECT LOGIC
                        player.update();
                        ball.update();
                        scoreOneString = "Player One: " + scoreOne;
                        scoreTwoString = "Player Two: " + scoreTwo;
                }
        }
       
         public void drawCenteredString(String s, int w, int h, Graphics g) {
                    FontMetrics fm = g.getFontMetrics();
                    int x = (w - fm.stringWidth(s)) / 2;
                    int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
                    g.drawString(s, x, y);
         }
     
        // METHOD OF JPANEL TO BE USED TO SHOW OBJECTS
        @Override
        public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setFont(new Font("Arial", Font.BOLD, 18));
			    g.setColor(Color.darkGray);
                g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
				g.setColor(Color.yellow);
                if (menu){
					if(menuCount == 20){
                        for (int i = 0; i < instructions4.length + 1; i++) {
								if(i == instructions4.length){
									if(scoreOne > scoreTwo){
										drawCenteredString("Winner is Player One with score: " + Integer.toString(scoreOne), Game.GAME_WIDTH, Game.GAME_HEIGHT + (i * 45) - 150, g);
										break;
									}
									else if(scoreOne < scoreTwo){
										drawCenteredString("Winner is Player Two with score: " + Integer.toString(scoreTwo), Game.GAME_WIDTH, Game.GAME_HEIGHT + (i * 45) - 150, g);
										break;
									}
									else if(scoreOne == scoreTwo){
										drawCenteredString("Match tied. Score: " + Integer.toString(scoreOne), Game.GAME_WIDTH, Game.GAME_HEIGHT + (i * 45) - 150, g);
										break;
									}
								}

                                drawCenteredString(instructions4[i], Game.GAME_WIDTH, Game.GAME_HEIGHT + (i * 45) - 150, g);
                        }
					}
					else if(scoreOne > scoreTwo){
                        for (int i = 0; i < instructions1.length + 1; i++) {
								if(i == instructions1.length){
									drawCenteredString(Integer.toString(scoreOne), Game.GAME_WIDTH, Game.GAME_HEIGHT + (i * 45) - 150, g);
									break;
								}
                                drawCenteredString(instructions1[i], Game.GAME_WIDTH, Game.GAME_HEIGHT + (i * 45) - 150, g);
                        }	
						
					}
					else if(scoreOne < scoreTwo){
                        for (int i = 0; i < instructions2.length + 1; i++) {
								if(i == instructions2.length){
									drawCenteredString(Integer.toString(scoreTwo), Game.GAME_WIDTH, Game.GAME_HEIGHT + (i * 45) - 150, g);
									break;
								}
                                drawCenteredString(instructions2[i], Game.GAME_WIDTH, Game.GAME_HEIGHT + (i * 45) - 150, g);
                        }
					}
					else if(scoreOne == scoreTwo && menuCount != 0){
                        for (int i = 0; i < instructions3.length + 1; i++) {
								if(i == instructions3.length){
									drawCenteredString(Integer.toString(scoreTwo), Game.GAME_WIDTH, Game.GAME_HEIGHT + (i * 45) - 150, g);
									break;
								}
                                drawCenteredString(instructions3[i], Game.GAME_WIDTH, Game.GAME_HEIGHT + (i * 45) - 150, g);
                        }
					}
					else{
                        for (int i = 0; i < instructions.length; i++) {
                                drawCenteredString(instructions[i], Game.GAME_WIDTH, Game.GAME_HEIGHT + (i * 45) - 150, g);
                        }
					}
                } else if(game){
						
						g.setColor(Color.black);
						g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);   
						
						g.setColor(Color.red);
						
                        g.drawString(scoreOneString, (Game.GAME_WIDTH / 4) - (scoreOneString.length() * 8 / 2),
                                        Game.GAME_HEIGHT - 40);
										
						g.setColor(Color.green);
										
                        g.drawString(scoreTwoString, (Game.GAME_WIDTH / 4 * 3) - (scoreTwoString.length() * 8 / 2),
                                        Game.GAME_HEIGHT - 40);
                        player.render(g);
                        ball.render(g);
                }
        }
 
        public void waitForStart(){
                while (!space) {
                }
				Random ran = new Random();
				int num = ran.nextInt(2);
				if(num == 0){
					ball.xSpeed = ran.nextInt(2) + 1;
                    ball.ySpeed = -(ran.nextInt(2) + 1);
				}
				else{
                        ball.xSpeed = -(ran.nextInt(2) + 1);
                        ball.ySpeed = ran.nextInt(2) + 1;
				}                     
        }
 
        // KEYBOARD INPUT EVENTS TRIGGERED BY OS
        @Override
        public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
 
                if(key == KeyEvent.VK_ESCAPE){
                        System.exit(0);
                }
                        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                                right = true;
                        }
 
                        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
							if(key == KeyEvent.VK_A){
								left1 = true;
							}
                                left = true;
                        }
 
                        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                                down = true;
                        }
 
                        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                                up = true;
                        }                              
 
                if (key == KeyEvent.VK_SPACE) {
                        space = true;
                        ball.reset();                                                             
                        waitForStart();
                }
        }
 
        @Override
        public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
 
                        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                                right = false;
                        }
 
                        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
							if(key == KeyEvent.VK_A){
								left1 = false;
							}
                                left = false;
                        }
 
                        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                                down = false;
                        }
 
                        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                                up = false;
                        }
 
        }
 
        // UNUSED METHOD REQUIRED BY KEYLISTENER INTERFACE
        @Override
        public void keyTyped(KeyEvent e) {
        }
 
        // STARTING POINT
        public static void main(String[] args) {
                Window window = new Window("HitWhileYouCan", GAME_WIDTH, GAME_HEIGHT);
                Game game = new Game();
				window.addGameInstance(game);
                window.addListener(game);
				Dice d = new Dice();
				JFrame window1 = new JFrame();
				window1.setTitle("Dice");
				window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window1.setContentPane(new RollDicePanel());
				window1.pack();
				window1.show();
				game.start();
        }
}
class Dice extends JApplet{
	public Dice(){
		this.setContentPane(new RollDicePanel());
	}
}





