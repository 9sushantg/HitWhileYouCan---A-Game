import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;
public class Die extends JPanel{
	private int _value; 
	private int _diam = 9;
	private static Random random = new Random();
	public Die(){
		setBackground(Color.white);
		setPreferredSize(new Dimension(60,60));
		roll(); 
	}
	public int roll(){
		int val = random.nextInt(6) + 1; 
		setValue(val);
		return val;
	}
	public int getValue(){
		return _value;
	}
	public void setValue(int spots) {
		_value = spots;
		repaint();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g); 
		int w = getWidth();
		int h = getHeight();
		switch(_value){
		case 1: 
			drawSpot(g, w/2, h/2);
			break;
		case 3: 
			drawSpot(g, w/2, h/2);
		case 2: 
			drawSpot(g, w/4, h/4);
			drawSpot(g, 3*w/4, 3*h/4);
			break;
		case 5: 
			drawSpot(g, w/2, h/2);
		case 4: 
			drawSpot(g, w/4, h/4);
			drawSpot(g, 3*w/4, 3*h/4);
			drawSpot(g, 3*w/4, h/4);
			drawSpot(g, w/4, 3*h/4);
			break;
		case 6: 
			drawSpot(g, w/4, h/4);
			drawSpot(g, 3*w/4, 3*h/4);
			drawSpot(g, 3*w/4, h/4);
			drawSpot(g, w/4, 3*h/4);
			drawSpot(g, w/4, h/2);
			drawSpot(g, 3*w/4, h/2);
			break;
		}
	}
	private void drawSpot(Graphics g, int x, int y){
		g.fillOval(x-_diam/2, y-_diam/2, _diam, _diam);
	}
}