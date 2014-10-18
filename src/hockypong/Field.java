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

public class Field {

	private Dimension dimension = new Dimension(this.getClass());
	private Game game;

	public Field(Game game) {
		this.game = game;
	}

	public void paint(Graphics2D g) {
		if (!bounceOff()) {
			g.setColor(Color.BLUE);
		} else {
			g.setColor(Color.RED);
		}
		g.fillRect(0, 0, dimension.WIDTH, dimension.HEIGHT);
		g.setColor(Color.CYAN);
		g.fillRect(dimension.wallWidth, dimension.wallWidth, dimension.WIDTH
				- dimension.wallWidth * 2, dimension.HEIGHT
				- dimension.wallWidth * 2);
		g.setColor(Color.BLUE);
		g.fillRect(0, dimension.HEIGHT / 2 - dimension.wallWidth,
				dimension.WIDTH, dimension.wallWidth);
	}

	public boolean bounceOff() {
		boolean change = false;
		if (game.ball.ballX < dimension.wallWidth + dimension.bufferNormal
				|| game.ball.ballX > dimension.WIDTH - dimension.bufferExtreme) {
			change = true;
		}
		if (game.ball.ballY < dimension.wallWidth + dimension.bufferNormal
				|| game.ball.ballY > dimension.HEIGHT - dimension.bufferExtreme) {
			change = true;
		}
		return change;
	}

}
