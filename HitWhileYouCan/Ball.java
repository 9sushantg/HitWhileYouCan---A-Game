
import java.awt.Color;
import java.awt.Graphics;
 
public class Ball extends Entity{
 
        public Ball(int x, int y, int width, int height, ID id) {
                super(x, y, width, height, id);
                this.x = Game.GAME_WIDTH / 2 - (this.WIDTH / 2);
                this.y = Game.GAME_HEIGHT / 2 - (this.HEIGHT / 2);
        }
 
        public boolean isCollidingPlayer(Player player) {                             
                        return (this.x < player.WIDTH && ((this.y >= player.y && this.y < player.y + player.HEIGHT)
                                        || (this.y + this.HEIGHT >= player.y && this.y + this.HEIGHT < player.y + player.HEIGHT)));
        }
		
		public boolean isCollidingElseWhere(){
						return (this.x + this.WIDTH > Game.GAME_WIDTH - 21 && (((this.y >= 0 && this.y < 200)
                                        || (this.y + this.HEIGHT >= 0 && this.y + this.HEIGHT < 200)) || ((this.y >= 360 && this.y < 360 + 200)
                                        || (this.y + this.HEIGHT >= 360 && this.y + this.HEIGHT < 360 + 200))));
		}
 
        public void reset() {
                this.x = Game.GAME_WIDTH / 2 - (this.WIDTH / 2);
                this.y = Game.GAME_HEIGHT / 2 - (this.HEIGHT / 2);
                this.xSpeed = 0;
                this.ySpeed = 0;
        }
 
        @Override
        public void update() {
                if ((this.x + this.WIDTH) > Game.GAME_WIDTH) {
					if(Game.alternative % 2 == 0){
						if(Game.judge == 1){
							Game.scoreOne += 6;
						}
						else if(Game.judge == 2){
							Game.scoreOne += 5;
						}
						else if(Game.judge == 3){
							Game.scoreOne += 4;
						}
						else if(Game.judge == 4){
							Game.scoreOne += 3;
						}
						else if(Game.judge == 5){
							Game.scoreOne += 2;
						}
						else if(Game.judge == 6){
							Game.scoreOne += 1;
						}						
                        reset();
					}
					else if(Game.alternative % 2 != 0){
						if(Game.judge == 1){
							Game.scoreTwo += 6;
						}
						else if(Game.judge == 2){
							Game.scoreTwo += 5;
						}
						else if(Game.judge == 3){
							Game.scoreTwo += 4;
						}
						else if(Game.judge == 4){
							Game.scoreTwo += 3;
						}
						else if(Game.judge == 5){
							Game.scoreTwo += 2;
						}
						else if(Game.judge == 6){
							Game.scoreTwo += 1;
						}	
                        reset();
					}
                }
                if (this.x < 0) {
						Game.setMenuState(true); 
						Game.menuCount++;
						Game.setGameState(false);
						Game.alternative++;
						Game.count = 0;
                        reset();
                }
                if (this.y + this.HEIGHT + 30 > Game.GAME_HEIGHT) {
                        this.ySpeed = -this.ySpeed;
                }
                if (this.y < 0) {
                        this.ySpeed = -this.ySpeed;
                }
                this.x += this.xSpeed;
                this.y += this.ySpeed;
 
        }
 
        @Override
        public void render(Graphics g) {
			if(Game.alternative % 2 == 0)
				g.setColor(Color.RED);
			else if(Game.alternative % 2 != 0)
                g.setColor(Color.GREEN);
            g.fillOval(this.x, this.y, this.WIDTH, this.HEIGHT);         
        }
}