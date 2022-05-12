package G1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import DVSG1.FullLocation;

public class MapLocation extends JPanel{
	
	FullLocation fullLocation;
	
	MapLocation(FullLocation temp){
		fullLocation = temp;
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.setBackground(Color.black);
        g2d.drawOval(0, 0, 5, 5);
        
//        g.drawLine(100, 100, 200, 200); 
//        g2d.translate(100.0, 100.0); 
//        g2d.drawLine(0, 0, 100, 100);
    }
}
