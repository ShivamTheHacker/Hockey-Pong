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

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main extends JPanel {

	public void startHere() throws InterruptedException {
		JFrame frame = new JFrame("Mini Tennis");
		Game game = new Game();
		frame.add(game);
		frame.setSize(500, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(game.getFrameSpeed());
		}

	}

	public static void main(String[] args) throws InterruptedException {
		new Main().startHere();
	}

}