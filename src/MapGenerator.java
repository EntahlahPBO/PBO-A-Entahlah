import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	public int map[][];

	private int brickWidth;
	private int brickHeight;
	private int separator;
	private int edge;

	private int totalBrick;

	private int row;
	private int col;

	public MapGenerator(int[][] levelmap) {
		this.row = levelmap[0].length;
		this.col = levelmap.length;

		map = new int[col][row];
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				map[i][j] = levelmap[i][j];
			}
		}

		separator = 5;
		edge = 80;
		brickWidth = calculateBrickWidth();
		brickHeight = calculateBrickHeight();

	}

	public void draw(Graphics2D g) {
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				if (map[i][j] > 0) {
					g.setColor(Color.decode("#E57842"));
					g.fillRect(j * (brickWidth + separator) + edge, i * (brickHeight + separator) + edge, 
							brickWidth, brickHeight);
				}
			}
		}
	}

	private int calculateBrickWidth() {
		int width = (700 - (2 * edge) - (row - 1) * separator) / (row);
		return width;
	}
	
	private int calculateBrickHeight() {
		int height = (300 - (2 * edge) - (col - 1) * separator) / (col);
		return height;
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