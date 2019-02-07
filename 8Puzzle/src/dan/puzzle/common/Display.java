package dan.puzzle.common;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Display extends Canvas implements Runnable{
	
	private Thread thread;
	private boolean running;
	
	private BufferStrategy bs;
	
	private Grid grid;
	
	public Display() {
		setSize(Main.WIDTH, Main.HEIGHT);
		running = false;
		grid = new Grid();
		start();
	}
	
	private void start() {
		thread = new Thread(this, "8Puzzle");
		running = true;
		thread.start();
	}
	
	private void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		final double UPDATES = 60.0;
		final double UPDATE_TIME = 1000000000 / UPDATES;
		double delta = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / UPDATE_TIME;
			lastTime = now;
			
			while(delta >= 1) {
				delta--;
			}
			
			while(!isDisplayable()) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			render();
		}
		stop();
	}
	
	private void render() {
		bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		grid.draw(g);
		
		g.dispose();
		bs.show();
	}

}
