import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class MapGenerator {
	public int map[][];

	private int brickWidth;
	private int brickHeight;
	private int separator;
	private int edge;

	private int totalBrick;

	private int row;
	private int col;

	private List<Brick> bricks;

	public MapGenerator(int[][] levelmap) {
		this.row = levelmap[0].length;
		this.col = levelmap.length;

		this.bricks = new ArrayList<Brick>();

		separator = 5;
		edge = 80;
		brickWidth = calculateBrickWidth();
		brickHeight = calculateBrickHeight();

		map = new int[col][row];
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				map[i][j] = levelmap[i][j];
				if (map[i][j] != 0) {
					this.bricks.add(new Brick(calculatePosX(j), calculatePosY(i), brickWidth, brickHeight, i, j));
				}
			}
		}
	}

	public void draw(Graphics2D g) {
		updateBricks();
		for (Brick brick : this.bricks) {
			brick.render(g);
		}
	}

	private void updateBricks() {
		List<Brick> toRemove;
		toRemove = new ArrayList<Brick>();
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				if (this.map[i][j] == 0) {
					for (Brick brick : this.bricks) {
						if (brick.getXi() == i && brick.getYi() == j) {
							toRemove.add(brick);
						}
					}
				}
			}
		}

		this.bricks.removeAll(toRemove);
	}

	private int calculateBrickWidth() {
		int width = (700 - (2 * edge) - (row - 1) * separator) / (row);
		return width;
	}

	private int calculateBrickHeight() {
		int height = (300 - (2 * edge) - (col - 1) * separator) / (col);
		return height;
	}

	private int calculatePosX(int k) {
		int posX0 = k * (brickWidth + separator) + edge;
		return posX0;
	}

	private int calculatePosY(int k) {
		int posY0 = k * (brickHeight + separator) + edge;
		return posY0;
	}

	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
	}

	public int getTotalBrick() {
		totalBrick = 0;
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; ++j) {
				if (map[i][j] == 1) {
					totalBrick++;
				}
			}
		}
		return totalBrick;
	}

	public int getBrickWidth() {
		return brickWidth;
	}

	public int getBrickHeight() {
		return brickHeight;
	}

	public int getSeparator() {
		return separator;
	}

	public int getEdge() {
		return edge;
	}
}