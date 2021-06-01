package print.snake;

import static java.awt.event.KeyEvent.VK_ESCAPE;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.lang.System.exit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;

public class GameOver extends JFrame {

	private static final long serialVersionUID = -1729121631312306371L;
	private JPanel contentPane;
	private JLabel lbl_score = new JLabel();
	
	public GameOver(int widht, int height) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(dim.width / 2 - widht / 2, dim.height / 2 - height / 2, widht, height);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setOpaque(true);
		contentPane.setBackground(Color.RED);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		panel.setBackground(Color.GRAY.brighter());
		
		JLabel lbl = new JLabel("GAME OVER");
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("MingLiU_HKSCS-ExtB", Font.BOLD, 28));
		panel.add(lbl);
		lbl_score.setFont(new Font("Times New Roman", Font.BOLD, 28));
		
		
		panel.add(lbl_score, BorderLayout.SOUTH);
		
		Teclas teclas = new Teclas();
		this.addKeyListener(teclas);
	}
	
	public void setScore(int score) {
		lbl_score.setText("Score: " + String.valueOf(score));
	}
	
	private class Teclas extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
				case VK_ESCAPE :
					exit(0);
					break;
				case VK_SPACE :
					Game.startGame();
					break;
					
			}
		}
	}
}
