package DVSG1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

public class DisplayFullLocationMap extends JPanel{
//	LocationAvailable fullLocation;
	List<LocationDetail> x;
	
	DisplayFullLocationMap(List<LocationDetail> temp){
		x = temp;
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.setPreferredSize(new Dimension(810,620));
        
//        List<LocationDetail> x = fullLocation.getAvailableLocationDetail();
        LocationDetail[] temp = x.toArray(new LocationDetail[x.size()]);
        for(int i=0;i<temp.length;i++) {
        	Graphics2D g2d = (Graphics2D) g;
        	if(i < 50) {
            	g2d.setColor(Color.BLUE);
            }else {
            	g2d.setColor(Color.GREEN);
            }
        	g2d.drawString(temp[i].getLocationName(), temp[i].getLocationX(),  temp[i].getLocationY());
            g2d.drawOval(temp[i].getLocationX(), temp[i].getLocationY(), 5, 5);
//            if(temp[i].getLocationName().equals("L66")) {
//            	g2d.drawOval(temp[i].getLocationX() - 150 , temp[i].getLocationY() - 80, 300, 160);
//            } // testing for clustering
            
        }
        Graphics2D g2dd = (Graphics2D) g;
        g2dd.setColor(Color.BLACK);
    	g2dd.drawString("warehouse", 400,  250);
        g2dd.drawOval(400, 250, 5, 5);
        
        
//        g.drawLine(100, 100, 200, 200); 
//        g2d.translate(100.0, 100.0); 
//        g2d.drawLine(0, 0, 100, 100);
    }
}
