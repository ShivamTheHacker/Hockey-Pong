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
import java.util.Random;

public class Ball {
	private Dimension dimension = new Dimension(this.getClass());
	public int ballX = dimension.WIDTH / 2 - dimension.ballDiameter;
	public int ballY = dimension.HEIGHT / 2 - dimension.ballDiameter;
	public int ballXSpeed = dimension.minimumSpeed;
	public int ballYSpeed = dimension.minimumSpeed;
	private Game game;

	public Ball(Game game) {
		this.game = game;
	}

	public void newGame() {

	}

	public void newPoint() {
		ballX = dimension.WIDTH / 2 - dimension.ballDiameter;
		ballY = dimension.HEIGHT / 2 - dimension.ballDiameter;
		ballXSpeed = dimension.minimumSpeed;
		ballYSpeed = dimension.minimumSpeed;
	}

	public void move() {
		wallCollision();
		racquetCollision();
		checkForGoal();
		ballX = ballX + ballXSpeed;
		ballY = ballY + ballYSpeed;
	}

	private void checkForGoal() {
		if (game.goal.inComGoalX()) {
			if (ballY + ballYSpeed < 0) {
				ballYSpeed = Math.abs(ballYSpeed);
			}
		} else { // Top wall bounce
			if (ballY + ballYSpeed < dimension.wallWidth) {
				ballYSpeed = Math.abs(ballYSpeed);
			}
		}
		if (game.goal.inUserGoalX()) {
			if (ballY + ballYSpeed > game.getHeight() - dimension.ballDiameter) {
				ballYSpeed *= -1;
			}
		} else { // Bottom wall bounce
			if (ballY + ballYSpeed > game.getHeight() - dimension.ballDiameter
					- dimension.wallWidth) {
				ballYSpeed *= -1;
			}
		}
	}

	private void racquetCollision() {
		if (collision()) {
			bounce();
		}
		if (comCollision()) {
			// ballXSpeed = dimension.originalPaddleXSpeed;
			ballYSpeed *= -1;
			// ballYSpeed *= randomizeYSpeed();

		}
	}

	private int randomizeXSpeed() {
		Random random = new Random();
		int count = random.nextInt(dimension.speedLimit)
				+ dimension.minimumSpeed;
		return Math.abs(count);
	}

	private int randomizeYSpeed() {
		Random random = new Random();
		int count = random.nextInt(dimension.speedLimit)
				+ dimension.minimumSpeed;
		return Math.abs(count);
	}

	private void bounce() {
		ballXSpeed = dimension.originalPaddleXSpeed;
		if (ballX < game.racquet.userPaddleX + dimension.racquetWidth / 2) {
			if (ballXSpeed < 0) {
				ballXSpeed *= -1;
			}
		} else if (ballX > game.racquet.userPaddleX + dimension.racquetWidth
				/ 2) {
			if (ballXSpeed > 0) {
				ballXSpeed *= -1;
			}
		} else if (ballX == dimension.racquetWidth / 2) {
			ballXSpeed = 0;
		}
		if (Math.abs(ballXSpeed) <= dimension.speedLimit) {
			ballXSpeed *= randomizeXSpeed();
		}
		/*
		 * To use angles in calculating bounce, take the angle and if it is
		 * closer to 0, 180, or 360, then make the x-speed less and y-speed
		 * greater and vice versa if the paddle is tilted 45, 90, etc.
		 */
		ballYSpeed *= -1 * randomizeYSpeed();
	}

	private void wallCollision() {
		if (ballX + ballXSpeed < dimension.wallWidth
				|| ballX + ballXSpeed > game.getWidth()
						- dimension.ballDiameter - dimension.wallWidth) {
			if (ballXSpeed != 0) {
				ballXSpeed *= -1;
			} else {
				ballXSpeed = dimension.originalBallXSpeed;
			}
		}
	}

	public int getX() {
		return ballX;
	}

	public int getY() {
		return ballY;
	}

	private boolean collision() {
		return game.racquet.getBounds().intersects(getBounds());
	}

	private boolean comCollision() {
		return game.comPaddle.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.YELLOW);
		g.fillOval(ballX, ballY, dimension.ballDiameter, dimension.ballDiameter);
	}

	public Rectangle getBounds() {
		return new Rectangle(ballX, ballY, dimension.ballDiameter,
				dimension.ballDiameter);
	}
}

/*
 * && ballX > game.comRacquet.getComX() && ballX < game.comRacquet.getComX() +
 * game.comRacquet.getWidth()
 */

// ballY = game.racquet.getTopY() - ballDiameter;

/*
 * // SHADOW g.setColor(Color.BLACK); if (getShadow()[0] == 0) { if
 * (getShadow()[1] == 0) { g.fillOval(ballX, ballY, ballDiameter, ballDiameter);
 * } else if (getShadow()[1] == 1) { g.fillOval(ballX, ballY -
 * dimension.bufferNormal, ballDiameter, ballDiameter); } else if
 * (getShadow()[1] == 2) { g.fillOval(ballX, ballY + dimension.bufferNormal,
 * ballDiameter, ballDiameter); } } else if (getShadow()[0] == 1) { if
 * (getShadow()[1] == 0) { g.fillOval(ballX - dimension.bufferNormal, ballY,
 * ballDiameter, ballDiameter); } else if (getShadow()[1] == 1) {
 * g.fillOval(ballX - dimension.bufferNormal, ballY - dimension.bufferNormal,
 * ballDiameter, ballDiameter); } else if (getShadow()[1] == 2) {
 * g.fillOval(ballX - dimension.bufferNormal, ballY + dimension.bufferNormal,
 * ballDiameter, ballDiameter); } } else if (getShadow()[0] == 2) { if
 * (getShadow()[1] == 0) { g.fillOval(ballX + dimension.bufferNormal, ballY,
 * ballDiameter, ballDiameter); } else if (getShadow()[1] == 1) {
 * g.fillOval(ballX + dimension.bufferNormal, ballY - dimension.bufferNormal,
 * ballDiameter, ballDiameter); } else if (getShadow()[1] == 2) {
 * g.fillOval(ballX + dimension.bufferNormal, ballY + dimension.bufferNormal,
 * ballDiameter, ballDiameter); } }
 * 
 * private int[] getShadow() { int state[] = { 0, 0 }; if (xSpeed > 0) {
 * state[0] = 1; } else if (xSpeed < 0) { state[0] = 2; } else if (xSpeed == 0)
 * { state[0] = 0; } if (ySpeed > 0) { state[1] = 1; } else if (ySpeed < 0) {
 * state[1] = 2; } else if (ySpeed == 0) { state[1] = 0; } return state; }
 */
