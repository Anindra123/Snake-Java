package bivas.snake.game;


import java.awt.Graphics;

public class Food {
	private int xcor ,  ycor , width,height;
	
	
	public Food(int xcor ,int ycor,int tile) {
		this.xcor  = xcor;
		this.ycor  = ycor;
		this.width = tile;
		this.height = tile;
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
