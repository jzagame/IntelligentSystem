package DVSG1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	
	
	
	GUI(DeliveryAgent daa,MasterRoutingAgent mra){
		da = daa;
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
							msg.addReceiver(mra.getMRA().getAID());
							temp[i].getAgent().send(msg);
							break;
						}
					}
				}
				
			});
		}
	}
	
	
	public void UIMasterRoutingAgent(MasterRoutingAgent mra) {
		JFrame f = new JFrame(mra.getMRA().getName());
		JPanel mainPanel = new JPanel(new BorderLayout(10,10));
		JPanel panel = new JPanel();
		JPanel panelMap = new JPanel();
		JButton btnGenerateRoute = new JButton("Generate Route");
		JButton btnSend = new JButton("Send Route");
		panel.add(btnGenerateRoute);
		panel.add(btnSend);
		panel.setBackground(Color.black);
		panel.setSize(1150,50);
		panelMap.setSize(1150,500);
		mainPanel.add(panel,BorderLayout.NORTH);
		mainPanel.add(panelMap,BorderLayout.SOUTH);
		f.add(mainPanel);
		f.setSize(1200,600);
		f.show();
		
		
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				da.PrintAllAgentConstraint();
//				panelMap.removeAll();
//				panelMap.revalidate();
				
//				try { //Use TO generate My location 
//					FileWriter r = new FileWriter("lib/location.txt");
//					for(int i=0;i<100;i++) {
//						String temp = "L" + (i+1) + ":";
//						for(int j=0;j<2;j++) {
//							temp += getRandomNumber();;
//							if(j+1 != 2) {
//								temp += ",";
//							}
//						}
//						r.write(temp);
//						r.write("\r\n");
//					}
//					r.close();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			}
			
		});
	}
	
	public String getRandomNumber() {
		int x = ThreadLocalRandom.current().nextInt(0,1000);
		return String.valueOf(x);
	}

	
}