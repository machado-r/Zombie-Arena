package game;

import java.awt.Rectangle;

import javax.swing.JFrame;


public class GameContainer extends JFrame{
	
	
	public GameContainer(){
		
		add(new Core());
		setTitle("Zumbi Arena");
		setSize(1000,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setExtendedState(MAXIMIZED_BOTH);
		setResizable(true);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new GameContainer();
	}

}