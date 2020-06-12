package bivas.snake.game;

import java.awt.Graphics;

public class Snake {
	
	private int xcor,ycor,width,height;
	
	public Snake(int xcor,int ycor,int tile) {
		this.xcor = xcor;
		this.ycor = ycor;
		width = tile;
		height =  tile;
	}
	public void draw(Graphics g) {
		g.fillRect(xcor*width, ycor*height, width, height);
	}
	public int getXcor() {
		return xcor;
	}
	public int getYcor() {
		return ycor;
	}
}
