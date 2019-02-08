package dan.puzzle.common;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyTracker implements KeyListener {
	
	// up, right, down, left, esc
	private boolean[] keysDown;
	
	public KeyTracker() {
		keysDown = new boolean[] {false, false, false, false, false};
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			keysDown[0] = true;
			break;
		case KeyEvent.VK_RIGHT:
			keysDown[1] = true;
			break;
		case KeyEvent.VK_DOWN:
			keysDown[2] = true;
			break;
		case KeyEvent.VK_LEFT:
			keysDown[3] = true;
			break;
		case KeyEvent.VK_ESCAPE:
			keysDown[4] = true;
			break;
		}
	}
	
	public boolean getKeyDown(int key) {
		if(keysDown[key]) {
			keysDown[key] = false;
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
