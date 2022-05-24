package DVSG1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class DisplayEachAgentRoute extends JPanel{
	String abc;
	List<LocationDetail> pos;
	DisplayEachAgentRoute(){
		pos = new ArrayList<LocationDetail>();
	}
	
	public void PrintAgentMap(List<LocationDetail> temp) {
		abc = "asdsa";
		pos = temp;
		paintComponent(this.getGraphics());
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color[] color = {Color.BLACK,Color.BLUE,Color.MAGENTA,Color.RED};
        boolean flagX = false;
        boolean flagY = false;
        int wareX = 400;
        int wareY = 250;
        if(abc != null) {
        	for(LocationDetail x:pos) {
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
                g2d.fillOval(X,Y, 5, 5);

            }
        	for(int i=0;i<pos.size();i++) {
        		Graphics2D g1d = (Graphics2D) g;
             	g1d.setColor(color[0]);
        		int X = pos.get(i).getLocationX();
            	int Y = pos.get(i).getLocationY();
            	if(X >= 400) {
            		flagX = true;
            		X = X-400;
            		wareX = 0;
            	}
            	if(Y >= 250) {
            		flagY = true;
            		Y = Y - 250;
            		wareY = 0;
            	}
            	if(i == 0) {
            		g1d.drawLine(wareX, wareY, X, Y);
            	}
            	if(i+1 != pos.size()) {
            		int Xnext = pos.get(i+1).getLocationX();
            		int Ynext = pos.get(i+1).getLocationY();
            		if(Xnext >= 400) {
                		Xnext = Xnext - 400;
                	}
                	if(Ynext >= 250) {
                		Ynext = Ynext - 250;
                	}
                	g1d.drawLine(X, Y, Xnext, Ynext);
            	}else {
            		
            		g1d.drawLine(wareX, wareY, X, Y);
            	}
            	
        	}
        }
        super.setPreferredSize(new Dimension(450,300));
        Graphics2D g2dd = (Graphics2D) g;
        g2dd.setColor(Color.BLACK);
    	g2dd.drawString("warehouse", wareX,  wareY);
        g2dd.fillRect(wareX, wareY, 10, 10);
//        g.drawLine(100, 100, 200, 200); 
//        g2d.translate(100.0, 100.0); 
//        g2d.drawLine(0, 0, 100, 100);
    }
}
