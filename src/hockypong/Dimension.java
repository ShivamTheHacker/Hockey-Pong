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

public class Dimension {

	/*
	 * Make this class for all resources including color, dimensions, pics, etc
	 * that are static.
	 */

	Class<?> c;

	public Dimension(Class<?> c) {
		this.c = c.getClass();
		if (c.equals(Field.class)) {
			// CHECK FOR CLASS?
		}
	}

	// GENERAL
	public int WIDTH = 500;
	public int HEIGHT = 778;
	public int FRAME_SPEED = 10;
	// FIELD
	public int totalFieldWidth = WIDTH;
	public int totalFIeldHeight = HEIGHT;
	public int wallWidth = 20;
	public int playableFieldWidth = WIDTH - wallWidth * 2;
	public int playableFieldHeight = HEIGHT - wallWidth * 2;
	public int bufferNormal = 4;
	public int bufferExtreme = 50 + bufferNormal;
	// SCORE
	public int scoreFont = 60;
	public int scoreX = wallWidth;
	public int userScoreY = HEIGHT / 2 + wallWidth + scoreFont;
	public int comScoreY = HEIGHT / 2 - scoreFont;
	public int winScore = 5;
	public int startScore = 0;
	// GOAL
	public int userGoalY = HEIGHT - wallWidth * 2;
	public int comGoalY = 0;
	public int goalHeight = wallWidth * 2;
	public int goalWidth = WIDTH / 5;
	public int goalOuterWidth = wallWidth / 2;
	public int goalX = WIDTH / 2 - goalWidth / 2;
	// RACQUET
	public int racquetWidth = 60;
	public int racquetHeight = wallWidth / 2;
	public int originalPaddleXSpeed = 1;
	public int originalPaddleYSpeed = 1;
	public int startPaddleX = WIDTH / 2 - racquetWidth;
	public int startUserPaddleY = HEIGHT - wallWidth;
	public int startComPaddleY = wallWidth;
	// BALL
	public int originalBallXSpeed = 1;
	public int originalBallYSpeed = 1;
	public int speedLimit = 1;
	public int minimumSpeed = 1;
	public int ballDiameter = 30;

}
