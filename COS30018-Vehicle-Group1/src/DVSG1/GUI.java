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
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {
	private DeliveryAgent da = new DeliveryAgent();
	private LocationDistance dl = new LocationDistance();
	private LocationAvailable fl = new LocationAvailable();
	private PathAvailableForEachCluster pafec;

	GUI(DeliveryAgent daa,MasterRoutingAgent mra,LocationDistance dll,LocationAvailable fll){
		da = daa;
		dl = dll;
		fl = fll;
		
		UIDeliveryAgent(mra);
		UIMasterRoutingAgent(mra);
	}
	
	public void init() {
		ClusterAvailable ca = new ClusterAvailable();
		pafec = new PathAvailableForEachCluster();
		ca.CreateDefaultClusterAvailable(fl.getAvailableLocationDetail());
		
		for(ClusterInformation x:ca.getClusterAvaiableSorted()) { 		
			GeneratePossiblePath ga = new GeneratePossiblePath();
			PossiblePathOfEachCluster ppoec = new PossiblePathOfEachCluster();
			List<LocationDetail> temp = new ArrayList<LocationDetail>(x.getListLocationInCluster());
			ga.GenerateClusterPossiblePath(temp,
					x.getListLocationInCluster(), 70,
					x.getListLocationInCluster().size());
			ppoec.setPossiblePathOfEachCluster(ga.getLocationAvailableForEachCluster(),x.getClusterName());
			pafec.setAllPossiblePathForCluster(ppoec);
		}
		
//		pafec.PrintAllPossiblePathForClusterDetail();	
//		pafec.getAllPossiblePathForCluster().get(0).getPossiblePathOfEachCluster().get(0).PrintDistanceMatrix();
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
	
	
	public void UIMasterRoutingAgent(MasterRoutingAgent mra ) {
		DisplayFullLocationMap displayFullLocationMap = new DisplayFullLocationMap(fl.getAvailableLocationDetail());
		
		JFrame f = new JFrame(mra.getMRA().getName());
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel subPanel = new JPanel(new GridLayout(0,1));
		subPanel.setPreferredSize(new Dimension(860,100));
		JPanel panelBtn = new JPanel();
		JPanel panelTxt = new JPanel();
		JTextField txtCity = new JTextField("Enter Location Name" ,30);
		JTextField txtItemNum = new JTextField("Enter Number Parcel" ,30);
		JButton btnGenerateGARoute = new JButton("Generate GA Route");
		JButton btnGenerateACORoute = new JButton("Generate ACO Route");
		JButton btnSend = new JButton("Send Route");
		JButton btnSubmit = new JButton("Send Requirment");
		
		panelBtn.add(btnSubmit);
		panelBtn.add(btnGenerateGARoute);
		panelBtn.add(btnGenerateACORoute);
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
		
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<fl.getAvailableLocationDetail().size();i++) {
					Random rand = new Random();
					fl.getAvailableLocationDetail().get(i).setTotalParcel(rand.nextInt(9) + 1);// give random parcel for every location 
				}
				// for initialize cluster 
				init();
			}
			
		});
		
		btnGenerateGARoute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Print print = new Print(pafec, da, AlgorithmType.GA);
				print.Output();
			}
		
		});
		
		btnGenerateACORoute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Print print = new Print(pafec, da, AlgorithmType.ACO);
				print.Output();
			}
			
		});
		
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
	}
	
	
	public void circle(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawOval(10,10,10,10);
	}
	
	
	
}
