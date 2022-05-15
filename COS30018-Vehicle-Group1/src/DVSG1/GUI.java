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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class GUI {
	DeliveryAgent da = new DeliveryAgent();
	LocationDistance dl = new LocationDistance();
	LocationAvailable fl = new LocationAvailable();
	ClusterAvailable ca = new ClusterAvailable();
	
	

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
		ca.CreateDefaultClusterAvailable(fl.getAvailableLocationDetail());
		
		JPanel panelBtn = new JPanel();
		JPanel panelTxt = new JPanel();
		JFrame f = new JFrame(mra.getMRA().getName());
		DisplayFullLocationMap displayFullLocationMap = new DisplayFullLocationMap(fl.getAvailableLocationDetail());
		Box b = new Box(BoxLayout.Y_AXIS); // box to add all panel
		
		Dimension expectedDimension = new Dimension(860, 510); // set panel size fixed
		displayFullLocationMap.setPreferredSize(expectedDimension); 
		displayFullLocationMap.setMaximumSize(expectedDimension);
		displayFullLocationMap.setMinimumSize(expectedDimension);
		
//		JPanel p = displayFullLocationMap; // assign Panel display map into a new panel. fo refresh purpose
		
		JTextField txtCity = new JTextField("Enter Location Name" ,30);
		JTextField txtItemNum = new JTextField("Enter Number Parcel" ,30);
		JButton btnGenerateGARoute = new JButton("Generate GA Route");
		JButton btnSend = new JButton("Send Route");
		JButton btnSubmit = new JButton("Send Requirment");
		
		panelBtn.add(btnSubmit);
		panelBtn.add(btnGenerateGARoute);
		panelBtn.add(btnSend);
		panelTxt.add(txtCity);
		panelTxt.add(txtItemNum);
		b.add(panelBtn);
		b.add(panelTxt);
		
		
		
		b.add(displayFullLocationMap);
		b.add(Box.createVerticalGlue());
		
		f.add(b);
		f.setSize(new Dimension(1200,800));
		
		f.setVisible(true);
		
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int i=0;i<fl.getAvailableLocationDetail().size();i++) {
					Random rand = new Random();
					fl.getAvailableLocationDetail().get(i).setTotalParcel(rand.nextInt(14) + 1);// give random parcel for every location 
				}
				ca.getClusterAvaiableSorted().get(0).CalculateClusterTotalItem();
				ca.getClusterAvaiableSorted().get(1).CalculateClusterTotalItem();
				ca.getClusterAvaiableSorted().get(2).CalculateClusterTotalItem();
				ca.getClusterAvaiableSorted().get(3).CalculateClusterTotalItem();
				// this 4 is for default input calculate total parcel (sum of all customer) in each cluster
				// will removed when the input is done
			}
			
		});
		btnGenerateGARoute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				PathAvailableForEachCluster pafec = FindAvailablePath();
				
//				pafec.PrintAllPossiblePathForClusterDetail();
				
//				pafec.getAllPossiblePathForCluster().get(0).getPossiblePathOfEachCluster().get(0).PrintDistanceMatrix();
				
				int sizeCluster = pafec.getAllPossiblePathForCluster().size();
				int sizeAgent = da.getListAgentConstraint().size();
				int loop = sizeCluster;
				if(sizeCluster > sizeAgent) {
					loop = sizeAgent;
				}
				PathOverallSolutionForEachCluster posfec = new PathOverallSolutionForEachCluster();
				try {
					for(int i=0;i<loop;i++) {
						PathOverallSolution pos = new PathOverallSolution();
						System.out.println("Cluster Name : " + pafec.getAllPossiblePathForCluster().get(i).getClusterName());
						System.out.println("Agent Name : " + da.getAgentConstraintSorted().get(i).getAgentName());
						for(PathAvailable x:pafec.getAllPossiblePathForCluster().get(i).getPossiblePathOfEachCluster()) {
							PathOverallSolutionInformation posi = new PathOverallSolutionInformation();
							
							UberSalesmensch geneticAlgorithm = new UberSalesmensch(x.getPathDetail().size(), 
									SelectionType.TOURNAMENT, x.getDistanceMatrix(), 0, 0);
					        SalesmanGenome result = geneticAlgorithm.optimize();
					        List<LocationDetail> tempL = new ArrayList<LocationDetail>();
					        
					        System.out.println("Possible Solution : " + pafec.getAllPossiblePathForCluster().get(i).getPossiblePathOfEachCluster().size());
					        System.out.print("W -> ");
					        for(int j=0;j<result.getGenome().size();j++) {
					        	LocationDetail tempL1 = new LocationDetail();
					        	tempL1.setLocationName(x.getPathDetail().get(result.getGenome().get(j)).getLocationName());
					        	tempL1.setTotalParcel(x.getPathDetail().get(result.getGenome().get(j)).getTotalParcel());
					        	tempL1.setXYLocation(x.getPathDetail().get(result.getGenome().get(j)).getLocationX(), x.getPathDetail().get(result.getGenome().get(j)).getLocationY());
					        	System.out.print(x.getPathDetail().get(result.getGenome().get(j)).getLocationName()+ " -> ");
					        	tempL.add(tempL1);
					        }
					        posi.setPathOverallSoluitionInformation(tempL);
					        posi.calculateLocationDistance(tempL);
					        posi.calculateTotalParcel();
					        System.out.println(" W");
					        System.out.println("Distance : " + result.getFitness());
					        System.out.println("-------------------------------------------");
					        pos.setPathOverallSolution(posi);
					       
						}
						pos.CalculateBestPathForAgent();
						posfec.setPathOverallSolutionForEachCluster(pos);
						System.out.println("DAta A : " + 
								posfec.getPathOverallSolutionForEachCluster().get(0).getBestPathInCluster().get(0).getLocationName());
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
//				posfec.getPathOverallSolutionForEachCluster().get(0).getBestPathInCluster();
				displayFullLocationMap.ClearAll(posfec,ca);
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
	
	public PathAvailableForEachCluster FindAvailablePath() {
		PathAvailableForEachCluster pafec = new PathAvailableForEachCluster();
		
		System.out.println("-----------------------------------------------------------------------");
		for(ClusterInformation x:ca.getClusterAvaiableSorted()) { 
			//command here for backup, just for check every cluster detail
//			
			GeneratePossiblePath ga = new GeneratePossiblePath();
			PossiblePathOfEachCluster ppoec = new PossiblePathOfEachCluster();
			List<LocationDetail> temp = new ArrayList<LocationDetail>(x.getListLocationInCluster());
			List<LocationDetail> temp2 = new ArrayList<LocationDetail>(x.getListLocationInCluster());
			ga.GenerateClusterPossiblePath(temp,
					temp2, 200,
					x.getListLocationInCluster().size());
			ppoec.setPossiblePathOfEachCluster(ga.getLocationAvailableForEachCluster(),x.getClusterName());
			pafec.setAllPossiblePathForCluster(ppoec);
		}
		return pafec;
	}
	
	
	
}
