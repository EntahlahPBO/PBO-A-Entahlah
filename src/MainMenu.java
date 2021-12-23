import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class MainMenu{
	private Font GameFont2;
	
	public MainMenu(){
		try {
        	String fontPath = "C:\\Users\\albto\\eclipse-workspace\\FP PBO\\asset\\GameFont2.ttf";
        	GameFont2 = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(45f);
        	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        	ge.registerFont(GameFont2);
        }
        catch(IOException | FontFormatException e) {
        	e.printStackTrace();
        }
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.decode("#DFA45B"));
        g.fillRect(1, 1, 700, 600);
		
        g.setFont(GameFont2);
		g.setColor(Color.decode("#462D28"));
		g.drawString("BLOCKBREAKER", 75, 270);
		
		g.setFont(GameFont2.deriveFont(15f));
		g.setColor(Color.decode("#462D28"));
		
		String[] texts = {"Enter - Play", "C - Credit"};
		
		for(int i = 0; i < texts.length; i++) {
			int width = g.getFontMetrics(GameFont2.deriveFont(15f)).stringWidth(texts[i]);
			int y = 310 + i * 40;
			g.drawString(texts[i], 353 - (width / 2), y + 25);
		}
	}
}
