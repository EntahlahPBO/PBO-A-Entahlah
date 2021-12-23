import java.awt.Color;
import java.awt.Graphics;

public class Brick {
	private int width;
	private int height;
	private int x;
	private int y;
	private int xi;
	private int yi;

	public Brick(int x, int y, int width, int height, int xi, int yi) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xi = xi;
		this.yi = yi;
	}

	public void render(Graphics g) {
		g.setColor(Color.decode("#E57842"));
		g.fillRect(this.x, this.y, width, height);
	}

	public int getXi() {
		return this.xi;
	}

	public int getYi() {
		return this.yi;
	}
}
