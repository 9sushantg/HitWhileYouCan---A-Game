import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;
public class RollDicePanel extends JPanel{
	private static Die _left;
	RollDicePanel(){
		_left = new Die();
		JButton rollButton = new JButton("Roll");
		rollButton.setFont(new Font("Sansserif", Font.PLAIN, 24));
		rollButton.addActionListener(new RollListener());
		JPanel dicePanel = new JPanel();
		dicePanel.setLayout(new GridLayout(1, 1, 0, 0));
		dicePanel.add(_left);
		this.setLayout(new BorderLayout());
		this.add(rollButton, BorderLayout.NORTH);
		this.add(dicePanel , BorderLayout.CENTER);
	}
	public static Die getDie(){
		return _left;
	} 
	private class RollListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			_left.roll();
		}
	}
}
