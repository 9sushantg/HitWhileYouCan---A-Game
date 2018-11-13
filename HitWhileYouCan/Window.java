
import java.awt.Dimension;
import java.awt.event.KeyListener;
 
import javax.swing.JFrame;
 
public class Window extends JFrame {
 
        public Window(String name, int width, int height) {
                setTitle(name);
                setPreferredSize(new Dimension(width, height));
                setMinimumSize(new Dimension(width, height));
                setMaximumSize(new Dimension(width, height));
                setResizable(false);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true); 
        }
 
        public void addGameInstance(Game game) {
                add(game);
        }
 
        public void addListener(KeyListener listener) {
                this.addKeyListener(listener);
        }
}