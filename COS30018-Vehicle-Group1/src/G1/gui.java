package G1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

public class gui extends JFrame{

	gui(StoreAgent sa,AgentConstraint[] ac)  
    {  
		JFrame f= new JFrame("Assignment Intelligent System");    
		JPanel panel= new JPanel();  
		JTextField[] txt = new JTextField[ac.length];
		JButton btnSend = new JButton("Send Capacity");
		JButton btnSend1 = new JButton("Send 2");
		for(int i=0;i< ac.length;i++) {
			txt[i] = new JTextField("Capacity" + ac[i].getAgentName().toString() ,12);
			panel.add(txt[i]);
		} 
		
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SendInformation();
			}
			
		});
		btnSend1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SendInformation1();
			}
			
		});
//	    b.setBounds(50,100,95,30); 
	    panel.add(btnSend);
	    panel.add(btnSend1);
		f.add(panel);  
        f.setSize(1200,600);    
        f.show();
        
//        b.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		
//        	}
//        });
    }
	
	public void SendInformation() {
//		String[] x = new String[c.length];
//		for(int i=0;i<c.length;i++) {
//			System.out.println(a[i].getText() + c[i].getAgentName());
//			x[i] = c[i].getAgentName();
//		}
//		jade.core.Runtime runtime = jade.core.Runtime.instance();
		 
		jade.core.Runtime runtime = jade.core.Runtime.instance();
		Profile profile = new ProfileImpl();
		// profile.setParameter( ... );
		ContainerController container = runtime.createAgentContainer( profile );
		Agent R = new ReceiverAgent();
		Agent P = new ReceiverAgent();
		Agent Q = new SenderAgent();
		
		// agent.addBehaviour( ... );
		AgentController ac;
		ACLMessage msg = null;
		
		try {
			msg = new ACLMessage(ACLMessage.INFORM);
			msg.setContent( "Ping" );
			ac = container.acceptNewAgent("DA", Q);
			ac = container.acceptNewAgent("DA4", R);
			msg.addReceiver(new AID("DA4",AID.ISLOCALNAME));
			Q.send(msg);
			((ReceiverAgent) R).setAgent(R);
			ac.start();	
			

			System.out.println(msg);
			
//			ACLMessage x = R.receive();
//			System.out.println(x.getSender().getLocalName()+ x.getContent());
			
			
			
			
			
		} catch (ControllerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

//		new SenderAgent(c,b);
	}
	
	public void SendInformation1() {
		jade.core.Runtime runtime = jade.core.Runtime.instance();
		Profile profile = new ProfileImpl();
		// profile.setParameter( ... );
		ContainerController container = runtime.createAgentContainer( profile );
		Agent R = new ReceiverAgent();
		Agent P = new ReceiverAgent();
		Agent Q = new SenderAgent();
		
		// agent.addBehaviour( ... );
		AgentController ac;
		ACLMessage msg = null;
		
		try {			
			msg = new ACLMessage(ACLMessage.INFORM);
			msg.setContent( "Hello world" );
			ac = container.acceptNewAgent("DA7", Q);
			ac = container.acceptNewAgent("DA5", R);
			
			msg.addReceiver(new AID("DA5",AID.ISLOCALNAME));
			Q.send(msg);
			((ReceiverAgent) R).setAgent(R);
			ac.start();
			
			
			

			System.out.println(msg);
			
//			ACLMessage x = R.receive();
//			System.out.println(x.getSender().getLocalName()+ x.getContent());
			
			
			
			
			
		} catch (ControllerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
    
}
