package bivas.snake.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class Game implements Runnable{
	public Display display;
	private String title;
	private Thread thread;
	private Snake snake;
	private Food apple;
	private Random rand;
	private ArrayList<Food> apples;
	private ArrayList<Snake> bodyparts;
	private Graphics g;
	private int tick = 0;
	private BufferStrategy buffer;
	private boolean running = false;
	private int width;
	private int height;
	private int size =3;
	private int score = 0;
	private int xcor = 10,ycor = 10;
	private boolean left = false,right = true,up = false,down = false;
	public Game(String title,int width,int height) {
		this.height =height;
		this.width = width;
		this.title = title;
		
		
	}
	private void innit() {
		
		display = new Display(title,width,height);
		display.getCanvas().setFocusable(true);
		display.getCanvas().addKeyListener(new Key(this));
		apples = new ArrayList<Food>();
		bodyparts = new ArrayList<Snake>();
		rand = new Random();
		
	}
	private void tick() {
		if(bodyparts.size() == 0) {
			snake = new Snake(xcor,ycor,10);
			bodyparts.add(snake);
		}
		if(apples.size()==0) {
			int x = rand.nextInt(27);
			int y = rand.nextInt(27);
			apple = new Food(x,y,10);
			apples.add(apple);
		}
		for(int i = 0;i<bodyparts.size();i++) {
			if(xcor == bodyparts.get(i).getXcor() && ycor == bodyparts.get(i).getYcor()) {
				if(i!= bodyparts.size()-1) {
					stop();
				}
			}
		}
		for(int i = 0;i<apples.size();i++) {
			if(xcor == apples.get(i).getXcor() && ycor ==apples.get(i).getYcor() ) {
				size++;
				score++;
				apples.remove(i);
				i--;
			}
		}
		
		if(xcor >29)xcor = 0;
		if(xcor <0) xcor = 29;
		if(ycor >29)ycor = 0;
		if(ycor <0) ycor = 29;
		tick++;
		if(tick>250) {
			if(left)xcor--;
			if(right)xcor++;
			if(up)ycor--;
			if(down)ycor++;
			
			
			
			snake = new Snake(xcor,ycor,10);
			bodyparts.add(snake);
			tick = 0;
			if(bodyparts.size()>size) {
				bodyparts.remove(0);
			}
		}
	}
	private void render() {
		buffer = display.getCanvas().getBufferStrategy();
		if(buffer == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = buffer.getDrawGraphics();
		
		g.clearRect(0, 0, width, height);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, width, height);
		
		
		g.setColor(Color.GREEN);
		for(int x = 0;x<width/10;x++) {
			
			g.drawLine(x*10, 0, x*10, height);
		}
		for(int y = 0;y<height/10;y++) {
			
			g.drawLine(0, y*10, width, y*10);
		}
		for(int i = 0;i<bodyparts.size();i++) {
			bodyparts.get(i).draw(g);
		}
		g.setColor(Color.RED);
		for(int i = 0;i<apples.size();i++) {
			apples.get(i).draw(g);
		}
		
		buffer.show();
		g.dispose();
	}
	
	public void run() {
		innit();
		while(running) {
			tick();
			render();
		}
		
		stop();
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key =  e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT && !left) {
			up =false;
			down = false;
			right = true;
		}
		if(key == KeyEvent.VK_LEFT && !right) {
			up =false;
			down = false;
			left = true;
		}
		if(key == KeyEvent.VK_DOWN && !up) {
			left =false;
			right = false;
			down = true;
		}
		if(key == KeyEvent.VK_UP && !down) {
			left =false;
			right = false;
			up = true;
		}
	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this,"Display");
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		JOptionPane.showMessageDialog(null, "Score :"+score,"Game Over",JOptionPane.WARNING_MESSAGE);
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}



