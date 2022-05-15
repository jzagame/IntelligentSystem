package G1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class gui {
	gui()  
    {  
		JFrame f= new JFrame("Panel Example");    
		JPanel panel=new JPanel();  
		panel.setBounds(40,30,1100,500);    
		panel.setBackground(Color.white);  
		JButton b1=new JButton("Find Agent");     
		b1.setBounds(50,100,80,30);    
		b1.setBackground(Color.yellow);   
		JButton b2=new JButton("Button 2");   
		b2.setBounds(100,100,80,30);    
		b2.setBackground(Color.green);   
		panel.add(b1); panel.add(b2);  
		f.add(panel);  
        f.setSize(1200,600);    
        f.setLayout(null);    
        f.setVisible(true);    
        b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CheckAgent c = new CheckAgent();
				c.setup();
			}
        });
    }  
    public static void main(String args[])  
    {  
    	new gui();  
    }  
}
