/* DOESN'T WORK
 
  
  package elsys.A11.project10.game.input;
 

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Menu extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;


	public Menu() {
		
	//	this.getContentPane().setLayout(new FlowLayout());

		JButton restart = new JButton("Restart");
		JButton exit = new JButton("Exit");
		
		restart.addActionListener(this);		
		exit.addActionListener(this);

		add(restart);
		add(exit);
	
	}
	
	
	public void actionPerformed(ActionEvent comm) {
			
		String action = comm.getActionCommand();
		if(action.equals("Restart")) {
			System.out.println("RESTARTED");
		}
		if(action.equals("Exit")) {
			System.out.println("EXITED");
		}
		
	}

	
	public void showIt() {
		JFrame menu = new JFrame();
		menu.pack();
		menu.setVisible(true);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
*/