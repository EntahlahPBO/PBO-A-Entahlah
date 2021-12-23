import java.awt.Graphics2D;

public class Paddle {
	private int posX;
	private int posY;
	private int width;
	private int height;

	public Paddle(int posX, int posY, int width, int height) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
	}

	public void draw(Graphics2D g) {
		g.fillRect(posX, posY, width, height);
	}

	public void moveRight() {
		if (posX >= 600) {
			posX = 600;
		} else {
			posX += 20;
		}
	}

	public void moveLeft() {
		if (posX < 10) {
			posX = 10;
		} else {
			posX -= 20;
		}
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
