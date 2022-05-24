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

public class DisplayFullLocationMap extends JPanel{
//	LocationAvailable fullLocation;
	List<LocationDetail> x;
	String abc;
	PathOverallSolutionForEachCluster posfec;
	ClusterAvailable CA;
	DisplayFullLocationMap(List<LocationDetail> temp){
		x = temp;
		posfec = new PathOverallSolutionForEachCluster();
	}
	
	public void ClearAll(PathOverallSolutionForEachCluster temp,ClusterAvailable ca) {
		abc = "asd";
		posfec = temp;
		CA = ca;
		paintComponent(this.getGraphics());
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color[] color = {Color.BLACK,Color.BLUE,Color.MAGENTA,Color.RED};
        if(abc == null) {
        	 LocationDetail[] temp = x.toArray(new LocationDetail[x.size()]);
             for(int i=0;i<temp.length;i++) {
             	Graphics2D g2d = (Graphics2D) g;
             	g2d.drawString(temp[i].getLocationName(), temp[i].getLocationX(),  temp[i].getLocationY()+10);
                g2d.fillOval(temp[i].getLocationX(), temp[i].getLocationY(), 5, 5);
             }
        }else {
        	int index=0;
        	List<String> tempName = new ArrayList<String>();
        	for(ClusterInformation d:CA.getClusterAvaiableSorted()) {
//        		d.PrintClusterDetail(); Print how many customer and Total parcel (sum of All parcel Customer) by cluster
        		for(LocationDetail e:d.getListLocationInCluster()) {
        			Graphics2D g2d = (Graphics2D) g;
        			g2d.setColor(color[index]);
        			if(e.getSend() == true) {
        				g2d.setColor(Color.GREEN);
        			}
                 	g2d.drawString(e.getLocationName(), e.getLocationX(), 
                 			e.getLocationY()+10);
                    g2d.fillOval(e.getLocationX()-2, e.getLocationY()-2, 5, 5);
        		}
        		tempName.add(d.getClusterName());
        		index++;
        	}
        	
        	index = 0;
        	int total = 0;
        	for(PathOverallSolution a:posfec.getPathOverallSolutionForEachCluster()) {
        		Graphics2D g1d = (Graphics2D) g;
        		Color tempColor = null;
        		for(int i=0;i<tempName.size();i++) {
        			if(tempName.get(i).equals(a.getPathOverallSolutionClusterName())) {
        				tempColor = color[i];
        				break;
        			}
        		}
        		g1d.setColor(tempColor);
        		g1d.drawLine(400, 250, a.getBestPathInCluster().get(0).getLocationX(), a.getBestPathInCluster().get(0).getLocationY());
        		
        		for(int i=0;i<a.getBestPathInCluster().size();i++) {
                 	Graphics2D g2d = (Graphics2D) g;
                 	g2d.setColor(tempColor);
            
                    if( (i+1) != a.getBestPathInCluster().size()) {
                    	g2d.drawLine(a.getBestPathInCluster().get(i).getLocationX(), a.getBestPathInCluster().get(i).getLocationY(), 
                        		a.getBestPathInCluster().get(i+1).getLocationX(), a.getBestPathInCluster().get(i+1).getLocationY());
                    	int x = 0;
                    	int y = 0;
                    	int costx = 0;
                    	int costy = 0;
                    	x = Math.abs(a.getBestPathInCluster().get(i+1).getLocationX() - a.getBestPathInCluster().get(i).getLocationX());
                    	y = Math.abs(a.getBestPathInCluster().get(i+1).getLocationY() - a.getBestPathInCluster().get(i).getLocationY());
                    	costx = a.getBestPathInCluster().get(i).getLocationX() + ( x/2);
                    	costy =  a.getBestPathInCluster().get(i).getLocationY() + ( y /2);
                    	if(a.getBestPathInCluster().get(i).getLocationX() > a.getBestPathInCluster().get(i +1).getLocationX()) {
                    		costx = a.getBestPathInCluster().get(i).getLocationX() - (x /2);
                    	}
                    	if(a.getBestPathInCluster().get(i).getLocationY() > a.getBestPathInCluster().get(i+1).getLocationY()) {
                    		costy =  a.getBestPathInCluster().get(i).getLocationY() - ( y /2);
                    	}
                    	g2d.drawString("cost : " +String.valueOf(x+y), costx, costy);
                    	total += (x+y);
                    }else {
                    	g1d.drawLine(400, 250, a.getBestPathInCluster().get(i).getLocationX(), a.getBestPathInCluster().get(i).getLocationY());
                    }  
                    
                 }
        		index++;
        	}
//        	System.out.println("Total Distance : " + total);
        }
       
        
        
        super.setPreferredSize(new Dimension(810,620));
        Graphics2D g2dd = (Graphics2D) g;
        g2dd.setColor(Color.BLACK);
    	g2dd.drawString("warehouse", 400,  250);
        g2dd.fillRect(400, 250, 10, 10);
//        g.drawLine(100, 100, 200, 200); 
//        g2d.translate(100.0, 100.0); 
//        g2d.drawLine(0, 0, 100, 100);
    }
}
