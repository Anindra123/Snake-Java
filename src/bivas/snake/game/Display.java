package bivas.snake.game;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	private String title;
	private JFrame frame;
	private Canvas canvas;
	private int width;
	private int height;
	public Display(String title,int width,int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		createDisplay();
	}
	public void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width,height));
		frame.add(canvas);
		frame.setResizable(false);
		frame.pack();
	}
	public Canvas getCanvas() {
		return canvas;
	}
	public JFrame getFrame() {
		return frame;
	}
}
