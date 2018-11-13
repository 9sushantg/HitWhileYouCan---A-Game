
import java.awt.Color;
import java.awt.Graphics;
 
public class Player extends Entity {
 
        public Player(int x, int y, int width, int height, ID id) {
                super(x, y, width, height, id);
        }
 
        @Override
        public void update() {
                        if (this.x < 0) {
                                this.x = 0;
                        }
                        if (this.x + this.WIDTH > this.WIDTH){
                                this.x = 0;
                        }
                        if (this.y < 0) {
                                this.y = 0;
                        }
                        if (this.y + this.HEIGHT + 30 > Game.GAME_HEIGHT) {
                                this.y = Game.GAME_HEIGHT - this.HEIGHT - 30;
                        }
 
                     //to move the player
                        this.x += this.xSpeed;
                        this.y += this.ySpeed;
        }
 
        @Override
        public void render(Graphics g){
                g.setColor(Color.BLUE);
                g.fillRect(this.x, this.y, this.WIDTH, this.HEIGHT);
				if(Game.alternative % 2 == 0)
					g.setColor(Color.RED);
				else if(Game.alternative % 2 != 0)
					g.setColor(Color.GREEN);
				g.fillRect(Game.GAME_WIDTH - 21, 0, 15, 200);             
				g.fillRect(Game.GAME_WIDTH - 21, 360, 15, 200);               
        }
 
}