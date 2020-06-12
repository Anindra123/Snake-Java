package bivas.snake.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Key extends KeyAdapter {
	
	Game game;
	public Key(Game game) {
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
	}

	


}
