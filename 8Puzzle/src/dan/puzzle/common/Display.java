package dan.puzzle.common;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Display extends Canvas implements Runnable{
	
	private Thread thread;
	private boolean running;
	
	private BufferStrategy bs;
	
	public Display() {
		setSize(Main.WIDTH, Main.HEIGHT);
		running = false;
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
		while(running) {
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
		g.drawLine(100, 100, 200, 200);
		
		g.dispose();
		bs.show();
	}

}
