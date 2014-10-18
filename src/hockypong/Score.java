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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Score {

	public boolean inGoal = false;
	public int userScore;
	public int comScore;
	private Dimension dimension = new Dimension(this.getClass());
	private Game game;

	public Score(Game game) {
		this.game = game;
	}

	public void paint(Graphics2D g, int a, int b) {
		g.setColor(Color.RED);
		Font font = new Font("Garamond", Font.BOLD | Font.ITALIC,
				dimension.scoreFont);
		g.setFont(font);
		g.drawString(Integer.toString(a), dimension.scoreX,
				dimension.userScoreY);
		g.drawString(Integer.toString(b), dimension.scoreX, dimension.comScoreY);
	}

	public int[] calculateScores() {
		// KEEPS COMPADDLE AT NEW GAME EVERY LOOP.
		if (!game.goal.inUserGoal() && !game.goal.inComGoal()) {
			inGoal = false;
		} else if (game.goal.inUserGoal()) {
			inGoal = true;
			comScore++;
			game.ball.newPoint();
			game.racquet.newPoint();
			game.comPaddle.newPoint();
		} else if (game.goal.inComGoal()) {
			inGoal = true;
			userScore++;
			game.ball.newPoint();
			game.racquet.newPoint();
			game.comPaddle.newPoint();
		}
		if (userScore == dimension.winScore || comScore == dimension.winScore) {
			game.ball.newGame();
			game.racquet.newGame();
			game.comPaddle.newGame();
			userScore = dimension.startScore;
			comScore = dimension.startScore;
		}
		int[] scores = { comScore, userScore };
		return scores;
	}

}