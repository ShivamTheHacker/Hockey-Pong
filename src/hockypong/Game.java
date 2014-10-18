/*
______________________________________
|\   ____\|\   __  \|\   ___ \|\  ___ \               
\ \  \___|\ \  \|\  \ \  \_|\ \ \   __/|              
 \ \  \    \ \  \\\  \ \  \ \\ \ \  \_|/__            
  \ \  \____\ \  \\\  \ \  \_\\ \ \  \_|\ \           
   \ \_______\ \_______\ \_______\ \_______\          
    \|_______|\|_______|\|_______|\|_______|       
 */

package hockypong;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	Dimension dimension = new Dimension(this.getClass());
	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this);
	ComPaddle comPaddle = new ComPaddle(this);
	Field field = new Field(this);
	Goals goal = new Goals(this);
	Score score = new Score(this);

	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}
		});
		setFocusable(true);
	}

	public void move() {
		ball.move();
		racquet.move();
		comPaddle.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		field.paint(g2d);
		goal.paint(g2d);
		ball.paint(g2d);
		comPaddle.paint(g2d);
		int[] scores = score.calculateScores();
		if (score.inGoal) {
			try {
				Thread.sleep(11);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		score.paint(g2d, scores[0], scores[1]);
		racquet.paint(g2d); // must be last
	}

	public void gameOver() {
		System.out.println("DIALOG");
		JOptionPane.showMessageDialog(this, "Game Over", "Game Over",
				JOptionPane.YES_NO_OPTION);
		System.out.println("ENDED");
		ball.newPoint();
		racquet.newPoint();
		comPaddle.newPoint();
		// System.exit(ABORT);
	}

	public int getFrameSpeed() {
		return dimension.FRAME_SPEED;
	}
	/*
	 * 
	 * 
	 * if (updateWait == 0) { if (!goal.inUserGoal() && !goal.inComGoal()) {
	 * ball.paint(g2d); } else if (goal.inUserGoal()) { comScore++;
	 * updateWait++; } else if (goal.inComGoal()) { userScore++; updateWait++; }
	 * if (userScore == 5 || comScore == 5) { ball.newGame(); racquet.newGame();
	 * comPaddle.newGame(); userScore = 0; comScore = 0; } } else {
	 * updateWait++; if (updateWait == 100) { updateWait = 0; } }
	 */

}