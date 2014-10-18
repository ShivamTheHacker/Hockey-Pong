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
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Goals {

	private Dimension dimension = new Dimension(this.getClass());
	private Game game;

	public Goals(Game game) {
		this.game = game;
	}

	public void paint(Graphics2D g) {
		// INSIDE OF GOAL
		g.setColor(Color.ORANGE);
		g.fillRect(dimension.goalX, dimension.userGoalY, dimension.goalWidth,
				dimension.goalHeight);
		g.fillRect(dimension.goalX, dimension.comGoalY, dimension.goalWidth,
				dimension.goalHeight);
		g.setColor(Color.WHITE);

		// USER GOAL BORDERS
		g.fillRect(dimension.goalX, dimension.userGoalY,
				dimension.goalOuterWidth, dimension.goalHeight);
		g.fillRect(dimension.goalX + dimension.goalWidth, dimension.userGoalY,
				10, dimension.goalHeight);
		g.fillRect(dimension.goalX, dimension.userGoalY
				- dimension.goalOuterWidth, dimension.goalWidth
				+ dimension.goalOuterWidth, dimension.goalOuterWidth);

		// COM GOAL BORDERS
		g.fillRect(dimension.goalX, dimension.comGoalY,
				dimension.goalOuterWidth, dimension.goalHeight);
		g.fillRect(dimension.goalX + dimension.goalWidth, dimension.comGoalY,
				dimension.goalOuterWidth, dimension.goalHeight);
		g.fillRect(dimension.goalX, dimension.comGoalY + dimension.goalHeight,
				dimension.goalWidth + dimension.goalOuterWidth,
				dimension.goalOuterWidth);
	}

	public Rectangle userGoalBounds() {
		return new Rectangle(dimension.goalX + dimension.ballDiameter,
				dimension.userGoalY - dimension.ballDiameter,
				dimension.goalWidth - dimension.ballDiameter,
				dimension.goalHeight + dimension.ballDiameter);
	}

	public Rectangle comGoalBounds() {
		return new Rectangle(dimension.goalX + dimension.ballDiameter,
				dimension.comGoalY + dimension.ballDiameter,
				dimension.goalWidth - dimension.ballDiameter,
				dimension.goalHeight - dimension.ballDiameter);
	}

	public boolean userGoalCollision() {
		return game.ball.getBounds().intersects(userGoalBounds());
	}

	public boolean comGoalCollision() {
		return game.ball.getBounds().intersects(comGoalBounds());
	}

	public boolean inUserGoalX() {
		boolean goal = false;
		if (game.ball.ballX > dimension.goalX
				&& game.ball.ballX < dimension.goalX + dimension.goalWidth) {
			goal = true;
		}
		return goal;
	}

	public boolean inUserGoalY() {
		boolean goal = false;
		if (game.ball.ballY > dimension.userGoalY
				&& game.ball.ballY < game.getHeight()) {
			goal = true;
		}
		return goal;
	}

	public boolean inUserGoal() {
		boolean goal = false;
		if (inUserGoalX() && inUserGoalY()) {
			goal = true;
		}
		return goal;
	}

	public boolean inComGoalX() {
		boolean goal = false;
		if (game.ball.ballX > dimension.goalX
				&& game.ball.ballX < dimension.goalX + dimension.goalWidth) {
			goal = true;
		}
		return goal;
	}

	public boolean inComGoalY() {
		boolean goal = false;
		if (game.ball.ballY > dimension.comGoalY
				&& game.ball.ballY < dimension.comGoalY + dimension.goalHeight) {
			goal = true;
		}
		return goal;
	}

	public boolean inComGoal() {
		boolean goal = false;
		if (inComGoalX() && inComGoalY()) {
			goal = true;
		}
		return goal;
	}
}
