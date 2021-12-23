import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
	private static final int MAINMENU = 0;
	private static final int PLAY = 1;
	private static final int CREDIT = 2;
	private static final int PAUSED = 3;

	private int state = MAINMENU;
	private int score = 0;

	private int totalBricks;

	private Timer timer;
	private int delay = 1;

	private int playerX = 300;

	private int ballposX = 350;
	private int ballposY = 530;
	private int ballXdir = -2;
	private int ballYdir = -3;

	private MapGenerator map;
	private MainMenu mainMenu;
	private CreditPage creditPage;
	private PauseMenu pauseMenu;
	private Paddle paddle;

	private Font gameFont1;

	private int levelPBO[][] = { { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },
			{ 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1 }, { 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1 },
			{ 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1 }, };

	public GamePlay() {
		mainMenu = new MainMenu();
		map = new MapGenerator(levelPBO);
		totalBricks = map.getTotalBrick();
		paddle = new Paddle(300, 550, 100, 8);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);

		try {
			String fontPath = "C:\\Users\\albto\\eclipse-workspace\\FP PBO\\asset\\gameFont1.ttf";
			gameFont1 = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(60f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(gameFont1);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		// background
		g.setColor(Color.decode("#462D28"));
		g.fillRect(1, 1, 692, 592);

		// borders
		g.setColor(Color.black);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(692, 0, 3, 592);

		// drawing stuff
		if (state == MAINMENU) {
			mainMenu.draw((Graphics2D) g);
		} else if (state == CREDIT) {
			creditPage.draw((Graphics2D) g);
		} else if (state == PAUSED) {
			pauseMenu.draw((Graphics2D) g);
		} else if (state == PLAY) {
			timer.start();
			map.draw((Graphics2D) g);
			paddle.draw((Graphics2D) g);

			// the scores
			g.setColor(Color.white);
			g.setFont(gameFont1);
			g.drawString("" + score, 600, 40);

			// the ball
			g.setColor(Color.decode("#FFE60F"));
			g.fillOval(ballposX, ballposY, 20, 20);

			// when you won the game
			if (totalBricks <= 0) {
				state = MAINMENU;
				ballXdir = 0;
				ballYdir = 0;
				g.setColor(Color.decode("#90EE90"));
				g.setFont(gameFont1);
				g.drawString("You Win", 295, 270);
				g.drawString("Esc - Main Menu", 260, 300);
				g.drawString("Enter - Restart", 260, 330);
			}

			// when you lose the game
			if (ballposY > 570) {
				state = MAINMENU;
				ballXdir = 0;
				ballYdir = 0;
				g.setColor(Color.decode("#C0C0C0"));
				g.setFont(gameFont1);

				g.drawString("Game Over, Scores: " + score, 235, 300);
				g.drawString("Esc - Main Menu", 260, 330);
				g.drawString("Enter - Restart", 260, 360);
			}
		}
		g.dispose();
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			paddle.moveRight();
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			paddle.moveLeft();
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (state == MAINMENU) {
				state = PLAY;
				ballposX = 350;
				ballposY = 530;
				ballXdir = -2;
				ballYdir = -3;
				playerX = 300;

				score = 0;
				map = new MapGenerator(levelPBO);
				totalBricks = map.getTotalBrick();

				repaint();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_C) {
			if (state == MAINMENU) {
				state = CREDIT;
				creditPage = new CreditPage();
				repaint();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (state != PLAY) {
				state = MAINMENU;
				mainMenu = new MainMenu();
				repaint();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (state == PLAY) {
				state = PAUSED;
				pauseMenu = new PauseMenu();
				repaint();
			} else if (state == PAUSED) {
				state = PLAY;
				repaint();
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		timer.start();
		if (state == PLAY) {
			if (new Rectangle(ballposX, ballposY, 20, 20).intersects(
					new Rectangle(paddle.getPosX(), paddle.getPosY(), paddle.getWidth(), paddle.getHeight()))) {
				ballYdir = -ballYdir;
			} else if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 30, 8))) {
				ballYdir = -ballYdir;
			} else if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 30, 550, 40, 8))) {
				ballYdir = -ballYdir;
			}

			// check map collision with the ball
			int width = map.getBrickWidth();
			int height = map.getBrickHeight();
			int separator = map.getSeparator();
			int edge = map.getEdge();

			A: for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j] != 0) {
						int brickX = j * (width + separator) + edge;
						int brickY = i * (height + separator) + edge;
						int brickWidth = width;
						int brickHeight = height;

						Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);

						if (ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							score += 5;
							totalBricks--;

							// when ball hit right or left of brick
							if (ballposX + brickWidth / 2 <= brickRect.x
									|| ballposX + brickHeight / 2 >= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							}
							// when ball hits top or bottom of brick
							else {
								ballYdir = -ballYdir;
							}

							break A;
						}
					}
				}
			}

			ballposX += ballXdir;
			ballposY += ballYdir;

			if (ballposX < 0) {
				ballXdir = -ballXdir;
			}
			if (ballposY < 0) {
				ballYdir = -ballYdir;
			}
			if (ballposX > 670) {
				ballXdir = -ballXdir;
			}

			repaint();
		}
	}

	public void moveRight() {
		playerX += 20;
	}

	public void moveLeft() {
		playerX -= 20;
	}

	public void keyReleased(KeyEvent e) {
		// nothing
	}

	public void keyTyped(KeyEvent e) {
		// nothing
	}
}