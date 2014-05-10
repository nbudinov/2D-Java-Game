package elsys.A11.project10.game.launcher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Help extends Launcher {

	private static final long serialVersionUID = 1L;

	protected JPanel window = new JPanel();
	Container pane = getContentPane();
	
	
	private int width = 400;
	private int height = 300;
	
	private JButton OK;
	private Rectangle rOK;
	
	private JLabel instr1, instr2, instr3, instr4;
	
	public Help() {
		super(1);
		setTitle("Options");
		setSize(new Dimension(width, height));
		setLocationRelativeTo(null);
		drawButtons();

	}
	
	private void drawButtons() {
		createWindow();
		createOkButton();	
		
		OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				new Launcher(0);

			}
		});
		
	}
	
	private void createOkButton(){
		OK = new JButton("OK");
		rOK = new Rectangle(width-80, height - 60, buttonWidth-10, buttonHeight-10);
		OK.setBounds(rOK);
		pane.add(OK);	
	}
	
	private void createWindow(){
		instr1 = new JLabel("Use W,A,S,D to move ", SwingConstants.HORIZONTAL);
		instr2 = new JLabel("Use mouse button to shoot and the Shift key for melee attacks", SwingConstants.HORIZONTAL);
		instr3 = new JLabel("sample text ", SwingConstants.HORIZONTAL);
		instr4 = new JLabel("sample text ", SwingConstants.HORIZONTAL);
		
		pane.setLayout(new GridLayout(0, 1));
		pane.add(instr1);
		pane.add(instr2);
		pane.add(instr3);
		pane.add(instr4);
	}

}
