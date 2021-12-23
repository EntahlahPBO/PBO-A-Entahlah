import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class CreditPage{
	private Font creditFont;
	
	public CreditPage(){
		try {
        	String fontPath = "C:\\Users\\albto\\eclipse-workspace\\FP PBO\\asset\\GameFont2.ttf";
        	creditFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(18f);
        	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        	ge.registerFont(creditFont);
        }
        catch(IOException | FontFormatException e) {
        	e.printStackTrace();
        }
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.decode("#DFA45B"));
        g.fillRect(1, 1, 700, 600);
		
        String[] texts = {"  Developed by :", "Gery Febrian S", "Nuzul Abatony"};
        
        g.setFont(creditFont);
		g.setColor(Color.decode("#462D28"));
		
		for(int i = 0; i < texts.length; i++) {
			int width = g.getFontMetrics(creditFont).stringWidth(texts[i]);
			if(i > 0) {
				g.setFont(creditFont.deriveFont(15f));
				width = g.getFontMetrics(creditFont.deriveFont(15f)).stringWidth(texts[i]);
			}
			int y = 210 + i * 40;
			g.drawString(texts[i], 353 - (width / 2), y + 25);
		}
	}
}
