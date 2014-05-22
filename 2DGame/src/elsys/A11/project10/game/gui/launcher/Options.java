package elsys.A11.project10.game.gui.launcher;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import elsys.A11.project10.game.Game;

public class Options extends Launcher {
	private static final long serialVersionUID = 1L;

	private String[] difficulties = { "Easy", "Normal", "Hard", "Pointless" };
	private static String choice = "Normal";
	private Container cp = getContentPane();


	

	private JTextField tf = new JTextField(15);

	private JComboBox<String> cb = new JComboBox<String>();

	private JButton OK = new JButton("OK");

	private int count = 0;
	
	public Options() {
		super(1);
		setTitle("Options");
		setSize(new Dimension(width, height));
		setLocationRelativeTo(null);
		this.setSize(300,200);
		drawButtons();

	}

	public void drawButtons() {
		System.out.println(choice);
		tf.setText(choice);
		for (int i = 0; i < difficulties.length; i++)
			cb.addItem(difficulties[count++]);
		tf.setEditable(false);
		
		OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.setLevelName("/"+choice+"Level.png");
				//System.out.println(Game.getLevelName());
				//System.out.println((choice+"Level.png"));
				//choice = ((JComboBox<?>) e.getSource()).getSelectedItem().toString();
				new Launcher(0);
				dispose();
			}
		});
		
		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choice = ((JComboBox<?>) e.getSource()).getSelectedItem().toString();
				tf.setText(((JComboBox<?>) e.getSource()).getSelectedItem().toString());
			}
		});
		
		//cp.setLayout(new BorderLayout());
		cp.setLayout(new FlowLayout());
		cp.add(tf);
		cp.add(cb);
		cp.add(OK);	}

}
