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
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Racquet {
	private Dimension dimension = new Dimension(this.getClass());
	public int userPaddleX = dimension.startPaddleX;
	public int userPaddleY = dimension.startUserPaddleY;
	private int userPaddleXSpeed = 0;
	private int userPaddleYSpeed = 0;
	public double rotateAngle = 0.0;
	private Game game;

	public Racquet(Game game) {
		this.game = game;
	}

	public void newGame() {

	}

	public void newPoint() {
		userPaddleX = dimension.startPaddleX;
		userPaddleXSpeed = dimension.originalPaddleXSpeed;
		userPaddleY = dimension.startUserPaddleY;
		userPaddleYSpeed = dimension.originalPaddleYSpeed;
		rotateAngle = 0.0;
	}

	public void move() {
		if (checkBoundsX()) {
			userPaddleX = userPaddleX + userPaddleXSpeed;
		}
		if (checkBoundsY()) {
			userPaddleY = userPaddleY + userPaddleYSpeed;
		}
	}

	public boolean checkBoundsX() {
		boolean check = false;
		if (userPaddleX + userPaddleXSpeed > 0
				&& userPaddleX + userPaddleXSpeed < dimension.WIDTH
						- dimension.racquetWidth) {
			check = true;
		}
		return check;
	}

	public boolean checkBoundsY() {
		boolean check = false;
		if (userPaddleY + userPaddleYSpeed > 0
				&& userPaddleY + userPaddleYSpeed < dimension.HEIGHT
						- dimension.racquetHeight) {
			check = true;
		}
		return check;
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.MAGENTA);
		AffineTransform rotate = AffineTransform.getRotateInstance(rotateAngle,
				userPaddleX, userPaddleY);
		if (rotate != null) {
			g.setTransform(rotate);
		}
		g.fillRect(userPaddleX, userPaddleY, dimension.racquetWidth,
				dimension.racquetHeight);
	}

	public void keyReleased(KeyEvent e) {
		userPaddleXSpeed = 0;
		userPaddleYSpeed = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			userPaddleXSpeed = -dimension.originalPaddleXSpeed;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			userPaddleXSpeed = dimension.originalPaddleXSpeed;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			userPaddleYSpeed = -dimension.originalPaddleYSpeed;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			userPaddleYSpeed = dimension.originalPaddleYSpeed;
		}
		if (e.getKeyCode() == KeyEvent.VK_Z) {
			riseRotateAngle();
		}
		if (e.getKeyCode() == KeyEvent.VK_X) {
			lowerRotateAngle();
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			// LIFT PADDLE
		}

	}

	private void riseRotateAngle() {
		rotateAngle += Math.PI / 4;
	}

	private void lowerRotateAngle() {
		rotateAngle -= Math.PI / 4;
	}

	public Rectangle getBounds() {
		return new Rectangle(userPaddleX, userPaddleY, dimension.racquetWidth,
				dimension.racquetHeight);
	}

	public int getUserX() {
		return userPaddleX;
	}

	public int getUserY() {
		return userPaddleY;
	}
}

/*
 * 
 * if (rotateAngle == Math.PI / 2 || rotateAngle == Math.PI / 2 + Math.PI) {
 * WIDTH = 10; HEIGHT = 60; } else if (rotateAngle == 0.0) { WIDTH = 60; HEIGHT
 * = 10; } else if (rotateAngle == 2 * Math.PI) { WIDTH = 60; HEIGHT = 10;
 * rotateAngle = 0.0; }
 * 
 * 
 * double a = (WIDTH * Math.sqrt(2.0)) / 2; double b = (HEIGHT * Math.sqrt(2.0))
 * / 2; int intA = (int) a; int intB = (int) b; int c = 0; if (a + b > intA +
 * intB + 0.5) { c = intA + intB + 1; } else if (a + b < intA + intB + 0.5) { c
 * = intA + intB - 1; } int boundingBoxY = 0; if (rotateAngle > 0.0) {
 * boundingBoxY -= intA; } else if (rotateAngle < 0.0) { boundingBoxY += intA; }
 * boundingBox = new Rectangle(userPaddleX, boundingBoxY, c, c);
 */