package DVSG1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import jade.lang.acl.ACLMessage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {
	DeliveryAgent da = new DeliveryAgent();
	LocationDistance dl = new LocationDistance();
	LocationAvailable fl = new LocationAvailable();
	
	GUI(DeliveryAgent daa,MasterRoutingAgent mra,LocationDistance dll,LocationAvailable fll){
		da = daa;
		dl = dll;
		fl = fll;
		UIDeliveryAgent(mra);
		UIMasterRoutingAgent(mra);
	}
	
	public void UIDeliveryAgent(MasterRoutingAgent mra) {
		List<AgentConstraint> x = da.getListAgentConstraint();
		AgentConstraint[] temp = x.toArray(new AgentConstraint[x.size()]);
		int size = temp.length;
		JFrame[] j = new JFrame[size];
		JPanel[] p = new JPanel[size];
		JTextField[] txt = new JTextField[size];
		JButton[] btn = new JButton[size];

		
		
		for(int i=0;i<size;i++) {
			j[i] = new JFrame(temp[i].getAgent().getName());
			p[i] = new JPanel();
			txt[i] = new JTextField("Capacity",10);
			btn[i] = new JButton("Send");
			btn[i].setActionCommand(temp[i].getAgentName());
			p[i].add(txt[i]);
			p[i].add(btn[i]);
			j[i].add(p[i]);
			j[i].setSize(600,600);
			j[i].show();
		}
		
		for(int i=0;i<size;i++) {
			btn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i< size;i++) {
						if(temp[i].getAgentName() == e.getActionCommand()) {
							ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
							msg.setContent(txt[i].getText());
							temp[i].setTotalItem(Integer.valueOf(txt[i].getText()));
							da = new DeliveryAgent(Arrays.asList(temp));
//							msg.setSender(temp[1].getAgent().getAID());
							msg.addReceiver(mra.getMRA().getAID());
							temp[i].getAgent().send(msg);
							System.out.println(msg);
							break;
						}
					}
				}
				
			});
		}
	}
	
	
	public void UIMasterRoutingAgent(MasterRoutingAgent mra) {
		DisplayFullLocationMap displayFullLocationMap = new DisplayFullLocationMap(fl.getAvailableLocationDetail());
		
		JFrame f = new JFrame(mra.getMRA().getName());
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel subPanel = new JPanel(new GridLayout(0,1));
		subPanel.setPreferredSize(new Dimension(860,100));
		JPanel panelBtn = new JPanel();
		JPanel panelTxt = new JPanel();
		JTextField txtCity = new JTextField("Enter Location Name" ,30);
		JTextField txtItemNum = new JTextField("Enter Number Parcel" ,30);
		JButton btnGenerateRoute = new JButton("Generate Route");
		JButton btnSend = new JButton("Send Route");
		panelBtn.add(btnGenerateRoute);
		panelBtn.add(btnSend);
		panelTxt.add(txtCity);
		panelTxt.add(txtItemNum);
		subPanel.add(panelTxt);
		subPanel.add(panelBtn);
		mainPanel.add(subPanel,BorderLayout.NORTH);
		mainPanel.add(displayFullLocationMap,BorderLayout.CENTER);
		f.add(mainPanel);
		f.setSize(860,700);
		f.show();
		
		btnGenerateRoute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClusteringCalculation Cc = new ClusteringCalculation();
				// TODO Auto-generated method stub
				for(LocationDetail l:fl.getAvailableLocationDetail()) {
					ClusterGroupInfo g = new ClusterGroupInfo(dl);
					g.findNearestLocation(fl.getAvailableLocationDetail(), l);
					Cc.setClusterGroupInfo(g);
				}
				
				
			}
			
		});
	}
	
	
	public void circle(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawOval(10,10,10,10);
	}
	
	
	
}
