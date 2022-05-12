package DVSG1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

public class DisplayFullLocationMap extends JPanel{
	FullLocation fullLocation;
	
	DisplayFullLocationMap(FullLocation temp){
		fullLocation = temp;
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<AvailableLocation> x = fullLocation.getAvailableLocationDetail();
        AvailableLocation[] temp = x.toArray(new AvailableLocation[x.size()]);
        for(int i=0;i<temp.length;i++) {
        	Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.black);
            g2d.setBackground(Color.black);
            g2d.drawOval(temp[i].getLocationX(), temp[i].getLocationY(), 5, 5);
        }
        System.out.println(temp.length + "<<<- This one");
        
//        g.drawLine(100, 100, 200, 200); 
//        g2d.translate(100.0, 100.0); 
//        g2d.drawLine(0, 0, 100, 100);
    }
}
