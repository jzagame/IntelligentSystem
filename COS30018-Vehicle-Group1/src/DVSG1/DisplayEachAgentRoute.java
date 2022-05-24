package DVSG1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JPanel;

public class DisplayEachAgentRoute extends JPanel{
	ClusterInformation ci;
	String abc;
	PathOverallSolution pos;
	ClusterAvailable CA;
	DisplayEachAgentRoute(){
		ci = new ClusterInformation();
		pos = new PathOverallSolution();
	}
	
	public void PrintAgentMap(PathOverallSolution temp,ClusterInformation temp1) {
		abc = "asdsa";
		pos = temp;
		ci = temp1;
		System.out.println("Check A");
		paintComponent(this.getGraphics());
	}
	
	public void paintComponent(Graphics g) {
		System.out.println("Check B");
        super.paintComponent(g);
        Color[] color = {Color.BLACK,Color.BLUE,Color.MAGENTA,Color.RED};
        boolean flagX = false;
        boolean flagY = false;
        if(abc != null) {
        	for(LocationDetail x:ci.getListLocationInCluster()) {
            	int X = x.getLocationX();
            	int Y = x.getLocationY();
            	if(X >= 400) {
            		flagX = true;
            		X = X-400;
            	}
            	if(Y >= 250) {
            		flagY = true;
            		Y = Y - 250;
            	}
            	Graphics2D g2d = (Graphics2D) g;
    			g2d.setColor(color[0]);
             	g2d.drawString(x.getLocationName(), X, Y+20);
                g2d.fillOval(X-2,Y+20, 5, 5);

            }
        }
        
        int wareX = 400;
        int wareY = 250;
        if(flagX == true) {
        	wareX = 0;
        }
        if(flagY == true) {
        	wareY = 0;
        }
        
        super.setPreferredSize(new Dimension(450,300));
        Graphics2D g2dd = (Graphics2D) g;
        g2dd.setColor(Color.BLACK);
    	g2dd.drawString("warehouse", wareX,  wareY +20);
        g2dd.fillRect(wareX, wareY, 10, 10);
//        g.drawLine(100, 100, 200, 200); 
//        g2d.translate(100.0, 100.0); 
//        g2d.drawLine(0, 0, 100, 100);
    }
}
