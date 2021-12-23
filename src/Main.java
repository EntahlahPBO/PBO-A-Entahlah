import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        
    	JFrame frame = new JFrame();	//create frame
        GamePlay gamePlay = new GamePlay();
        
        //frame.setSize(420, 420);
        frame.setBounds(10, 10, 700, 600);
        frame.setTitle("Wall Breaker");	//set title for frame								
        frame.setResizable(false);		//set frame to be unresizeable
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//to exit on close
        frame.add(gamePlay);
        frame.setVisible(true);			//make frame visible 
        
        ImageIcon image = new ImageIcon("C:\\Users\\albto\\eclipse-workspace\\FP PBO\\asset\\Logo 2.png");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(0,0,0));
    }
}