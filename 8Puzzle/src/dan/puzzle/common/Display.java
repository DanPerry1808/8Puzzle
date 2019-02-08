package dan.puzzle.common;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Display extends Canvas implements Runnable{
	
	private Thread thread;
	private boolean running;
	
	private BufferStrategy bs;
	
	private Grid grid;
	
	private KeyTracker keys;
	
	public Display() {
		setSize(Main.WIDTH, Main.HEIGHT);
		setFocusable(true);
		requestFocusInWindow();
		running = false;
		grid = new Grid();
		keys = new KeyTracker();
		addKeyListener(keys);
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
		System.exit(0);
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
				
				// If up key, move blank down
				if(keys.getKeyDown(0)) {
					grid.moveBlank(Direction.DOWN);
				// If right key, move blank left
				}else if(keys.getKeyDown(1)){
					grid.moveBlank(Direction.LEFT);
				// If down key pressed, move blank up
				}else if(keys.getKeyDown(2)) {
					grid.moveBlank(Direction.UP);
				// If left key, move blank right
				}else if(keys.getKeyDown(3)) {
					grid.moveBlank(Direction.RIGHT);
				}else if(keys.getKeyDown(4)) {
					running = false;
				}
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
		
		g.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
		
		grid.draw(g, 100, 100);
		
		g.dispose();
		bs.show();
	}

}
