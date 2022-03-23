package print.snake;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;
import static java.lang.System.exit;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Game extends JFrame {

	private static final long serialVersionUID = 1560586985826681820L;
	private static int widht = 640;
	private static int height = 480;
	private Snake snake =  new Snake(widht, height);
	private static Game frame = new Game();
	private static GameOver frameOver = new GameOver(widht, height);
	public static int score = 0;
	private ImageIcon icon = new ImageIcon("src\\img\\icon.png");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Game() {
		setTitle("Snake");
		setSize(widht, height);
		setIconImage(icon.getImage());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - widht / 2, dim.height / 2 - height / 2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Teclas teclas = new Teclas();
		this.addKeyListener(teclas);
		this.getContentPane().add(snake);
		this.setVisible(true);
	}
	
	private class Teclas extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
				case VK_ESCAPE :
					exit(0);
					break;
				case VK_UP :
					if (snake.getDireccion() != VK_DOWN) snake.setDireccion(VK_UP);
					break;
				case VK_DOWN :
					if (snake.getDireccion() != VK_UP) snake.setDireccion(VK_DOWN);
					break;
				case VK_LEFT :
					if (snake.getDireccion() != VK_RIGHT) snake.setDireccion(VK_LEFT);
					break;
				case VK_RIGHT :
					if (snake.getDireccion() != VK_LEFT) snake.setDireccion(VK_RIGHT);
					break;
			}
		}
	}
	
	public static void gameOver() {
		frameOver.setScore(score);
		frame.setVisible(false);
		frameOver.setVisible(true);
	}
	
	public static void startGame() {
		score = 0;
		frame = new Game();
		frame.setVisible(true);
		frameOver.setVisible(false);
	}
	
	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		Game.score = score;
	}
}
