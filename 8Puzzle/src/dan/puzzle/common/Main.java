package dan.puzzle.common;

import javax.swing.JFrame;

public class Main extends JFrame{
	
	private static final String TITLE = "8Puzzle";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		setTitle(TITLE);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		requestFocus();
		add(new Display());
		pack();
		setVisible(true);
	}

}
