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
import java.awt.geom.AffineTransform;

public class ComPaddle {

	private Dimension dimension = new Dimension(this.getClass());
	private int comPaddleX = dimension.startPaddleX;
	private int comPaddleXSpeed = dimension.minimumSpeed;
	private int comPaddleY = dimension.startComPaddleY;
	private int comPaddleYSpeed = dimension.minimumSpeed;
	private int waitCount = 0;
	private double rotateAngle = 0.0;
	private Game game;

	public ComPaddle(Game game) {
		this.game = game;
	}

	public void newGame() {

	}

	public void newPoint() {
		comPaddleX = dimension.startPaddleX;
		comPaddleXSpeed = dimension.minimumSpeed;
		comPaddleY = dimension.startComPaddleY;
		comPaddleYSpeed = dimension.minimumSpeed;
	}

	public void move() {
		if (!collision() && waitCount == 0) {
			if (checkBoundsX()) {
				comPaddleX = comPaddleX + comPaddleXSpeed;
			} else {
				comPaddleXSpeed *= -1;
				comPaddleX = comPaddleX + comPaddleXSpeed;
			}
			if (checkBoundsY()) {
				comPaddleY = comPaddleY + comPaddleYSpeed;
			} else {
				comPaddleYSpeed *= -1;
				comPaddleY = comPaddleY + comPaddleYSpeed;
			}
		} else {
			waitCount++;
			if (waitCount == 2) {
				waitCount = 0;
				comPaddleXSpeed *= -1;
				comPaddleX = comPaddleX + comPaddleXSpeed;
				comPaddleYSpeed *= -1;
				comPaddleY = comPaddleY + comPaddleYSpeed;
			}
		}
	}

	public boolean checkBoundsX() {
		boolean check = false;
		if (comPaddleX + comPaddleXSpeed > 0
				&& comPaddleX + comPaddleXSpeed < dimension.WIDTH
						- dimension.racquetWidth) {
			check = true;
		}
		return check;
	}

	public boolean checkBoundsY() {
		boolean check = false;
		if (comPaddleY + comPaddleYSpeed > 0
				&& comPaddleY + comPaddleYSpeed < dimension.HEIGHT / 2
						- dimension.racquetHeight) {
			check = true;
		}
		return check;
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.MAGENTA);
		AffineTransform rotate = AffineTransform.getRotateInstance(rotateAngle,
				comPaddleX, comPaddleY);
		if (rotate != null) {
			g.setTransform(rotate);
		}
		comPaddleX = game.ball.ballX;
		g.fillRect(comPaddleX, comPaddleY, dimension.racquetWidth,
				dimension.racquetHeight);
	}

	public Rectangle getBounds() {
		return new Rectangle(comPaddleX, comPaddleY, dimension.racquetWidth,
				dimension.racquetHeight);
	}

	private boolean collision() {
		return game.ball.getBounds().intersects(getBounds());
	}

	public int getComX() {
		return comPaddleX;
	}

	public int getComY() {
		return comPaddleY;
	}

	public int getWidth() {
		return dimension.racquetWidth;
	}

	public int getHeight() {
		return dimension.racquetHeight;
	}
}

/*
 * int ballSpeedX = Math.abs(game.ball.ballX); int ballSpeedY =
 * Math.abs(game.ball.ballY); if (xSpeed < 0) { xSpeed = -ballSpeedX; } else {
 * xSpeed = ballSpeedX; } if (ySpeed < 0) { ySpeed = -ballSpeedY; } else {
 * ySpeed = ballSpeedY; }
 * 
 * 
 * public boolean checkPaddleBounds() { boolean check = false; if (comPaddleY >
 * dimension.HEIGHT / 2 - dimension.wallWidth) { check = true; } return check; }
 */
