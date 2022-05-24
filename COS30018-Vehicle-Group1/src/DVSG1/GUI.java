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
import jade.tools.sniffer.Message;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class GUI {
	DeliveryAgent da = new DeliveryAgent();
	LocationDistance dl = new LocationDistance();
	LocationAvailable fl = new LocationAvailable();
	ClusterAvailable ca = new ClusterAvailable();
	PathAvailableForEachCluster pafec = new PathAvailableForEachCluster();
	PathOverallSolutionForEachCluster posfec = new PathOverallSolutionForEachCluster();
	List<PathAssignToAgent> pata = new ArrayList<PathAssignToAgent>();
	List<JButton> buttonDA = new ArrayList<JButton>();
	
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
		JButton[] btnsend = new JButton[size];
		JButton[] btnReceived = new JButton[size];
		JButton[] btnDone = new JButton[size];
		JButton[] btnReject = new JButton[size];
		Box[] b = new Box[size]; // box to add all panel
		Dimension expectedDimension = new Dimension(500, 300); // set panel size fixed
		DisplayEachAgentRoute[] displayEachAgentRoute = new DisplayEachAgentRoute[size];
		
		
		
		

//		b[i].add(displayEachAgentRoute);
		for(int i=0;i<size;i++) {
			j[i] = new JFrame(temp[i].getAgentName());
			b[i] = new Box(BoxLayout.Y_AXIS);
			displayEachAgentRoute[i] = new DisplayEachAgentRoute();
			displayEachAgentRoute[i].setPreferredSize(expectedDimension); 
			displayEachAgentRoute[i].setMaximumSize(expectedDimension);
			displayEachAgentRoute[i].setMinimumSize(expectedDimension);
			p[i] = new JPanel();
			txt[i] = new JTextField("Capacity",10);
			btnsend[i] = new JButton("Send");
			btnReceived[i] = new JButton("Accept");
			btnDone[i] = new JButton("Done");
			btnReject[i] = new JButton("Reject");
			
			btnsend[i].setName("Send");
			btnReceived[i].setName("Accept");
			btnDone[i].setName("Done");
			btnReject[i].setName("Reject");
			
			btnsend[i].setActionCommand(temp[i].getAgentName());
			btnReceived[i].setActionCommand(temp[i].getAgentName());
			btnDone[i].setActionCommand(temp[i].getAgentName());
			btnReject[i].setActionCommand(temp[i].getAgentName());
			
			btnDone[i].setEnabled(false);
			btnReceived[i].setEnabled(false);
			btnReject[i].setEnabled(false);
			
			p[i].add(txt[i]);
			p[i].add(btnsend[i]);
			p[i].add(btnReceived[i]);
			p[i].add(btnReject[i]);
			p[i].add(btnDone[i]);
			
			b[i].add(p[i]);
			b[i].add(displayEachAgentRoute[i]);
			b[i].add(Box.createVerticalGlue());
			
			buttonDA.add(btnReject[i]);
			buttonDA.add(btnsend[i]);
			buttonDA.add(btnReceived[i]);
			buttonDA.add(btnDone[i]);
			
			j[i].add(b[i]);
			j[i].setSize(new Dimension(700,600));
			
			j[i].setVisible(true);
		}
		
		
		
		for(int i=0;i<size;i++) {
			btnsend[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i< size;i++) {
						if(temp[i].getAgentName() == e.getActionCommand()) {
							btnsend[i].setEnabled(false);
							ACLMessage msgg = new ACLMessage(ACLMessage.INFORM);
							msgg.setContent(txt[i].getText());
							temp[i].setTotalItem(Integer.valueOf(txt[i].getText()));
							msgg.setSender(temp[i].getAgentAMSAgentDescription().getName());
							msgg.addReceiver(mra.getMRA().getAID());
							mra.getMRA().send(msgg);
							break;
						}
					}
				}
				
			});
		}
		
		for(int i=0;i<size;i++) {
			btnReceived[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i< size;i++) {
						if(temp[i].getAgentName() == e.getActionCommand()) {
							btnReceived[i].setEnabled(false);
							btnReject[i].setEnabled(false);
							btnDone[i].setEnabled(true);
							ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
							msg.setContent("Accept");
							msg.setSender(mra.getMRA().getAID());
							msg.addReceiver(temp[i].getAgentAMSAgentDescription().getName());
							mra.getMRA().send(msg);
							temp[i].setAgentStatus(false);
							for(int j=0;j<pata.size();j++) {
								if(temp[i].getAgentName().equals(pata.get(j).getAgentName())) {
									displayEachAgentRoute[i].PrintAgentMap(pata.get(j).getListLocation());
									for(ClusterInformation temp:ca.getClusterAvailable()) {
										if(temp.getClusterName().equals(pata.get(j).getClusterName())) {
											temp.setSendListLocationStatus(pata.get(j).getListLocation());
											break;
										}
									}
									break;
								}
							}
							break;
						}
					}
				}
				
			});
		}
		
		for(int i=0;i<size;i++) {
			btnReject[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i< size;i++) {
						if(temp[i].getAgentName() == e.getActionCommand()) {
							btnReceived[i].setEnabled(false);
							btnReject[i].setEnabled(false);
							btnsend[i].setEnabled(true);
							ACLMessage msg = new ACLMessage(Message.REFUSE);
							msg.setContent("Bye i dont want see you");
							msg.addReceiver(mra.getMRA().getAID());
							msg.setSender(temp[i].getAgentAMSAgentDescription().getName());
							mra.getMRA().send(msg);
							for(AgentConstraint a:da.getAgentConstraintSorted()) {
								if(a.getAgentName().equals(temp[i].getAgentName())) {
									a.setAgentStatus(true);
									a.setTotalItem(0);
									break;
								}
							}
							for(int j=0;j<pata.size();j++) {
								if(pata.get(j).getAgentName().equals(temp[i].getAgentName())) {
									pata.remove(j);
									break;
								}
							}
						}
					}
				}
				
			});
		}
		
		for(int i=0;i<size;i++) {
			btnDone[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					for(int i=0;i<size;i++) {
						if(temp[i].getAgentName() == e.getActionCommand()) {
							ACLMessage msg = new ACLMessage(Message.INFORM);
							msg.setContent("Done");
							msg.setSender(temp[i].getAgentAMSAgentDescription().getName());
							msg.addReceiver(mra.getMRA().getAID());
							mra.getMRA().send(msg);
							temp[i].setAgentStatus(true);
							btnDone[i].setEnabled(false);
							for(int j=0;j<pata.size();j++) {
								if(pata.get(j).getAgentName().equals(temp[i].getAgentName())) {
									pata.remove(j);
									break;
								}
							}
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
		
		Dimension expectedDimension = new Dimension(1000, 600); // set panel size fixed
		displayFullLocationMap.setPreferredSize(expectedDimension); 
		displayFullLocationMap.setMaximumSize(expectedDimension);
		displayFullLocationMap.setMinimumSize(expectedDimension);
		
		JTextField txtCity = new JTextField("Enter Location Name" ,30);
		JTextField txtItemNum = new JTextField("Enter Number Parcel" ,30);
		JButton btnGenerateGARoute = new JButton("Generate GA Route");
		JButton btnGenerateACORoute = new JButton("Generate ACO Route");
		JButton btnSend = new JButton("Send Route");
		JButton btnSubmit = new JButton("Send Requirment");
		
		btnGenerateGARoute.setEnabled(false);
		btnGenerateACORoute.setEnabled(false);
		btnSend.setEnabled(false);
		
		panelBtn.add(btnSubmit);
		panelBtn.add(btnGenerateACORoute);
		panelBtn.add(btnGenerateGARoute);
		panelBtn.add(btnSend);

		
		
		b.add(panelBtn);
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
					fl.getAvailableLocationDetail().get(i).setTotalParcel(rand.nextInt(9) + 1);// give random parcel for every location 
				}
				ca.getClusterAvaiableSorted().get(0).CalculateClusterTotalItem();
				ca.getClusterAvaiableSorted().get(1).CalculateClusterTotalItem();
				ca.getClusterAvaiableSorted().get(2).CalculateClusterTotalItem();
				ca.getClusterAvaiableSorted().get(3).CalculateClusterTotalItem();
				// this 4 is for default input calculate total parcel (sum of all customer) in each cluster
				// will removed when the input is done	
				btnSubmit.setEnabled(false);
				btnGenerateGARoute.setEnabled(true);
				btnGenerateACORoute.setEnabled(true);
			}
			
		});
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				for(int i=0;i<da.getFreeListAgentConstraint().size();i++) {
					ACLMessage msg = new ACLMessage(Message.INFORM);
					msg.addReceiver(da.getFreeListAgentConstraint().get(i).getAgentAMSAgentDescription().getName());
					msg.setContent(posfec.getPathOverallSolutionForEachCluster().get(i).getBestPathString());
					msg.setSender(mra.getMRA().getAID());
					mra.getMRA().send(msg);
					if(pafec.getAllPossiblePathForCluster().size() == (i+1)) {
						break;
					}
				}
				btnGenerateGARoute.setEnabled(true);
				btnGenerateACORoute.setEnabled(true);
				btnSend.setEnabled(false);
				for(PathAssignToAgent p:pata) {
					for(JButton x:buttonDA) {
						if(x.getName().equals("Accept") && x.getActionCommand().equals(p.getAgentName())) {
							x.setEnabled(true);
						}
						if(x.getName().equals("Reject") && x.getActionCommand().equals(p.getAgentName())) {
							x.setEnabled(true);
						}
					}
				}
			}
			
		});
		
		
		btnGenerateGARoute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				pafec = FindAvailablePath();
				if(pafec.getAllPossiblePathForCluster().size() > 0) {
					posfec = new PathOverallSolutionForEachCluster();
					
					int sizeCluster = pafec.getAllPossiblePathForCluster().size();
					int sizeAgent = da.getFreeListAgentConstraint().size();
					int loop = sizeCluster;
					if(sizeCluster > sizeAgent) {
						System.out.println(sizeCluster + ":" + sizeAgent);
						loop = sizeAgent;
					}
					try {
						for(int i=0;i<loop;i++) {
							PathOverallSolution pos = new PathOverallSolution();
							System.out.println("Cluster Name : " + pafec.getAllPossiblePathForCluster().get(i).getClusterName());
							System.out.println("Agent Name : " + da.getFreeListAgentConstraint().get(i).getAgentName());
							int ct = 1;
							for(PathAvailable x:pafec.getAllPossiblePathForCluster().get(i).getPossiblePathOfEachCluster()) {
								PathOverallSolutionInformation posi = new PathOverallSolutionInformation();
								
								UberSalesmensch geneticAlgorithm = new UberSalesmensch(x.getPathDetail().size(), 
										SelectionType.TOURNAMENT, x.getDistanceMatrix(), 0, 0);
						        SalesmanGenome result = geneticAlgorithm.optimize();
						        List<LocationDetail> tempL = new ArrayList<LocationDetail>();
						        
						        System.out.println("Possible Solution " + ct + " : "  + pafec.getAllPossiblePathForCluster().get(i).getPossiblePathOfEachCluster().size());
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
						        posi.calculateLocationDistance();
						        posi.calculateTotalParcel();
						        posi.setFitness(result.getFitness());
						        System.out.println(" W");
						        System.out.println("Distance : " + result.getFitness());
						        
						        System.out.println("-------------------------------------------");
						        pos.setPathOverallSolution(posi);
						        ct++;
							}
							pos.setPathOverallSolutionClusterName(pafec.getAllPossiblePathForCluster().get(i).getClusterName());
							pos.CalculateBestPathForAgent(pos.getPathOverallSolution());
							posfec.setPathOverallSolutionForEachCluster(pos);
							List<LocationDetail> record = new ArrayList<LocationDetail>(pos.getBestPathInCluster());
							PathAssignToAgent pataTemp = new PathAssignToAgent(pafec.getAllPossiblePathForCluster().get(i).getClusterName(),record,
									da.getFreeListAgentConstraint().get(i).getAgentName(),true);
							pata.add(pataTemp);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					btnGenerateGARoute.setEnabled(false);
					btnGenerateACORoute.setEnabled(false);
					btnSend.setEnabled(true);
					
//					posfec.getPathOverallSolutionForEachCluster().get(0).getBestPathInCluster();
					displayFullLocationMap.ClearAll(posfec,ca);
				}else {
					JOptionPane.showMessageDialog(btnGenerateGARoute, "No Agent , Please Wait for Agent done","Alert",2);
				}
				// TODO Auto-generated method stub
//				pafec.PrintAllPossiblePathForClusterDetail();			
//				pafec.getAllPossiblePathForCluster().get(0).getPossiblePathOfEachCluster().get(0).PrintDistanceMatrix();		
			}
		});
		
		btnGenerateACORoute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pafec = FindAvailablePath();
				if(pafec.getAllPossiblePathForCluster().size() > 0) {
					posfec = new PathOverallSolutionForEachCluster();
					
					int sizeCluster = pafec.getAllPossiblePathForCluster().size();
					int sizeAgent = da.getFreeListAgentConstraint().size();
					int loop = sizeCluster;
					if(sizeCluster > sizeAgent) {
						loop = sizeAgent;
					}
					try {
						for(int i=0;i<loop;i++) {
							PathOverallSolution pos = new PathOverallSolution();
							System.out.println("Cluster Name : " + pafec.getAllPossiblePathForCluster().get(i).getClusterName());
							System.out.println("Agent Name : " + da.getFreeListAgentConstraint().get(i).getAgentName());
							int ct = 1;
							for(PathAvailable x:pafec.getAllPossiblePathForCluster().get(i).getPossiblePathOfEachCluster()) {
								PathOverallSolutionInformation posi = new PathOverallSolutionInformation();
								
								AntColonyOptimization antColony = new AntColonyOptimization(x.getDistanceMatrix(), 0, 0);  
								antColony.startAntOptimization();
								List<LocationDetail> tempL = new ArrayList<LocationDetail>();
								
								System.out.println("Possible Solution " + ct + " : " + pafec.getAllPossiblePathForCluster().get(i).getPossiblePathOfEachCluster().size());   
								System.out.print("W -> ");                                                                                                        
								for(int j=0;j<antColony.getBest().length;j++) {                                                                                   
									LocationDetail tempL1 = new LocationDetail();                                                                                 
									tempL1.setLocationName(x.getPathDetail().get(antColony.getBest()[j]).getLocationName());                                      
									tempL1.setTotalParcel(x.getPathDetail().get(antColony.getBest()[j]).getTotalParcel());                                        
									tempL1.setXYLocation(x.getPathDetail().get(antColony.getBest()[j]).getLocationX(),                                            
											x.getPathDetail().get(antColony.getBest()[j]).getLocationY());                                                        
									System.out.print(x.getPathDetail().get(antColony.getBest()[j]).getLocationName()+ " -> ");                                    
									tempL.add(tempL1);                                                                                                            
								}                                                                                                                                 
								posi.setPathOverallSoluitionInformation(tempL);                                                                                   
								posi.calculateLocationDistance();                                                                                            
								posi.calculateTotalParcel();      
								posi.setFitness( (int)antColony.getBestTour());
								System.out.println(" W");                                                                                                         
								System.out.println("Distance : " + antColony.getBestTour());                                                                       
								System.out.println("-------------------------------------------");                                                                
								pos.setPathOverallSolution(posi);
								ct++;
						    }
							pos.setPathOverallSolutionClusterName(pafec.getAllPossiblePathForCluster().get(i).getClusterName());
							pos.CalculateBestPathForAgent(pos.getPathOverallSolution());
							posfec.setPathOverallSolutionForEachCluster(pos);
							List<LocationDetail> record = new ArrayList<LocationDetail>(pos.getBestPathInCluster());
							PathAssignToAgent pataTemp = new PathAssignToAgent(pafec.getAllPossiblePathForCluster().get(i).getClusterName(),record,
									da.getFreeListAgentConstraint().get(i).getAgentName(),true);
							pata.add(pataTemp);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					btnGenerateGARoute.setEnabled(false);
					btnGenerateACORoute.setEnabled(false);
					btnSend.setEnabled(true);
					
	//				posfec.getPathOverallSolutionForEachCluster().get(0).getBestPathInCluster();
					displayFullLocationMap.ClearAll(posfec,ca);
				}else {
					JOptionPane.showMessageDialog(btnGenerateACORoute, "No Agent , Please Wait for Agent done","Alert",2);
				}
				// TODO Auto-generated method stub
	//			pafec.PrintAllPossiblePathForClusterDetail();
	//			pafec.getAllPossiblePathForCluster().get(0).getPossiblePathOfEachCluster().get(0).PrintDistanceMatrix();
			}
		});
	}
	
	
	public void circle(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawOval(10,10,10,10);
	}
	
	public PathAvailableForEachCluster FindAvailablePath() {
		PathAvailableForEachCluster pafec = new PathAvailableForEachCluster();
		if(da.getFreeListAgentConstraint().size() > 0) {
			int i=0;
			System.out.println("-----------------------------------------------------------------------");
			for(ClusterInformation x:ca.getClusterAvaiableSortedForPath()) { 
				//command here for backup, just for check every cluster detail
				System.out.println( i + " : here");
				GeneratePossiblePath ga = new GeneratePossiblePath();
				PossiblePathOfEachCluster ppoec = new PossiblePathOfEachCluster();
				List<LocationDetail> temp = new ArrayList<LocationDetail>(x.getUnsendListLocationInCluster());
				List<LocationDetail> temp2 = new ArrayList<LocationDetail>(x.getUnsendListLocationInCluster());
				ga.GenerateClusterPossiblePath(temp,
						temp2, da.getFreeListAgentConstraint().get(i).getTotalItem(),
						x.getUnsendListLocationInCluster().size());
				ppoec.setPossiblePathOfEachCluster(ga.getLocationAvailableForEachCluster(),x.getClusterName());
				pafec.setAllPossiblePathForCluster(ppoec);
				i++;
				if(i == da.getFreeListAgentConstraint().size() ) {
					break;
				}
			}
		}
		return pafec;
	}
	
	
	
	
}
