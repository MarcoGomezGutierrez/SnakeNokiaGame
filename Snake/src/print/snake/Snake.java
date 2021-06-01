package print.snake;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;
import static java.lang.System.currentTimeMillis;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Snake extends JPanel {

	private static final long serialVersionUID = -7218399452839072677L;
	private final int windowWidht;
	private final int windowHeight;
	private final int widhtSnake = 20;
	private final int heightSnake = 20;
	@Getter private Point snake = new Point();
	private Point apple = new Point();
	@Getter @Setter private int direccion = VK_LEFT;
	private List<Point> list = new ArrayList<Point>();
	private boolean gameOver = false;
	private int frecuencia = 80;
	private final ImageIcon manzana = new ImageIcon("src\\img\\manzana.png");
	private ImageIcon serpiente = new ImageIcon("src\\img\\snakeIzquierda.png");
	private int widhtApple = 20;
	private int heightApple = 20;
	
	public Snake(int windowWidht, int windowHeight) {
		this.windowWidht = windowWidht;
		this.windowHeight = windowHeight;
		snake.x = windowWidht / 2;
		snake.y = windowHeight / 2;
		list.add(0, new Point(snake.x, snake.y));
		apple.x = random(windowWidht);
		apple.y = random(windowHeight);
		SnakeThread thread = new SnakeThread();
		thread.start();
		this.setBackground(Color.green.darker().darker());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		list.add(0, new Point(snake.x, snake.y));
		list.remove(list.size() - 1);
		
		//Pintar linea del borde de la pantalla
		g2d.setColor(new Color(139, 69, 19));
		g2d.setStroke(new BasicStroke(5));
		g2d.drawLine(1, 1, 1, windowHeight);
		g2d.drawLine(1, 1, windowWidht, 1);
		g2d.drawLine(windowWidht - 19, 0, windowWidht - 19, windowHeight - 19);
		g2d.drawLine(1, windowHeight - 41, windowWidht, windowHeight - 41);
		
		g2d.setColor(Color.BLACK.brighter());
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(Game.getScore()), 10, 30);
		
		g2d.drawImage(serpiente.getImage(), snake.x, snake.y, widhtSnake, heightSnake, null);
		
		for (int i = 0; i < list.size(); i++) {
			if (i == 0) g2d.drawImage(serpiente.getImage(), snake.x, snake.y, widhtSnake, heightSnake, null);
			else {
				Point point = list.get(i);
				if (snake.x == point.x && snake.y == point.y) gameOver = true;
				else {
					g2d.setStroke(new BasicStroke(1));
					g2d.setColor(Color.GREEN);
					g2d.fillRoundRect(point.x, point.y, widhtSnake, heightSnake, 10, 10);
					g2d.setColor(Color.BLACK);
					g2d.drawRoundRect(point.x, point.y, widhtSnake, heightSnake, 10, 10);
				}
			}
		}
		
		g2d.setColor(new Color(255, 0, 0));
		g2d.drawImage(manzana.getImage(), apple.x, apple.y, widhtApple, heightApple, null);
		
		
		if ((snake.x > (apple.x - widhtApple)) && (snake.x < (apple.x + widhtApple)) && (snake.y > (apple.y - heightApple)) && (snake.y < (apple.y + heightApple))) {
			apple.x = random(windowWidht);
			apple.y = random(windowHeight);
			list.add(0, new Point(snake.x, snake.y));
			Game.setScore(Game.getScore() + 1);
		}
		
		if (gameOver) {
			try { Thread.sleep(500); } catch (InterruptedException e) {}
			Game.gameOver();
		}
	}
	
	
	private int random(int rango) {
		return new Random().nextInt(rango - 100);
	}
	
	private class SnakeThread extends Thread {
		
		private long last = 0;
		
		@Override
		public void run() {
			while (true) {
				if (currentTimeMillis() - last > frecuencia) {
					if (!gameOver) {
						switch (direccion) {
							case VK_UP :
								snake.y = snake.y - heightSnake;
								serpiente = new ImageIcon("src\\img\\snakeArriba.png");
								if (snake.y < 0) gameOver = true;
								if (snake.y > windowHeight) gameOver = true;
								break;
							case VK_DOWN :
								snake.y = snake.y + heightSnake;
								serpiente = new ImageIcon("src\\img\\snakeAbajo.png");
								if (snake.y > windowHeight - 50) gameOver = true;
								if (snake.y < 0) gameOver = true;
								break;
							case VK_LEFT :
								snake.x = snake.x - widhtSnake;
								serpiente = new ImageIcon("src\\img\\snakeIzquierda.png");
								if (snake.x > windowWidht) gameOver = true;
								if (snake.x < 0) gameOver = true;
								break;
							case VK_RIGHT :
								snake.x = snake.x + widhtSnake;
								serpiente = new ImageIcon("src\\img\\snakeDerecha.png");
								if (snake.x > windowWidht - 30) gameOver = true;
								if (snake.x < 0) gameOver = true;
								break;
						}
						repaint();
						last = currentTimeMillis();
					}
				}
			}
		}
	}
}


