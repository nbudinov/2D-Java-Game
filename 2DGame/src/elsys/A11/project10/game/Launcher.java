package elsys.A11.project10.game;


import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Launcher extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel window = new JPanel();
	private JButton play, options, help, exit;
	private Rectangle rplay, roptions, rhelp, rexit;

	private int width = 240;
	private int height = 320;
	private int buttonWidth = 80;
	private int buttonHeight = 40;

	public Launcher() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setTitle("Game");
		setSize(new Dimension(width, height));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(window);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		window.setLayout(null);

		drawButtons();

	}

	private void drawButtons() {
		
		createPlayButton(50);
		createOptionsButton(100);
		createHelpButton(150);
		createExitButton(200);

		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game game = new Game();

				game.start();
				
				dispose();
			}
		});

		options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Options");
			}
		});
		
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Help");

			}
		});
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); 
			}
		});

	}

	
	private void createPlayButton(int height){
		play = new JButton("Play");
		rplay = new Rectangle(width / 2 - buttonWidth / 2, height, buttonWidth, buttonHeight);
		play.setBounds(rplay);
		window.add(play);	
	}
	private void createOptionsButton(int height){
		options = new JButton("Options");
		roptions = new Rectangle(width / 2 - buttonWidth / 2, height, buttonWidth, buttonHeight);
		options.setBounds(roptions);
		window.add(options);	
	}
	private void createHelpButton(int height){
		help = new JButton("Help");
		rhelp = new Rectangle(width / 2 - buttonWidth / 2, height, buttonWidth, buttonHeight);
		help.setBounds(rhelp);
		window.add(help);	
	}
	private void createExitButton(int height){
		exit = new JButton("Exit");
		rexit = new Rectangle(width / 2 - buttonWidth / 2, height, buttonWidth, buttonHeight);
		exit.setBounds(rexit);
		window.add(exit);	
	}

	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Launcher ex = new Launcher();
				ex.setVisible(true);
			}
		});

	}
}
