package elsys.A11.project10.game.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import elsys.A11.project10.game.Game;
import elsys.A11.project10.game.gui.launcher.Launcher;

public class InGameMenu extends JFrame{

	protected Container window = new Container();
	private JButton resume, restart, menu, exit;
	private Rectangle rresume, rrestart, rmenu, rexit;

	private int width = 400 * 3;
	private int height = (400 / 16 * 9) * 3;
	protected int buttonWidth = 80;
	protected int buttonHeight = 40;

	private static final long serialVersionUID = 1L;

	public InGameMenu() {

	}

	public void init() {
		setSize(new Dimension(width, height));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(window);
		// setUndecorated(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		window.setLayout(null);
		drawButtons();
	}

	private void drawButtons() {
		createResume(150);
		createRestart(200);
		createMenu(250);
		createExit(300);

		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game game = new Game();
				game.start();

				dispose();

			}
		});

		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Launcher laucnher = new Launcher(0);

				dispose();

			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		
		window.add(resume);
		window.add(restart);
		window.add(menu);
		window.add(exit);
	}
	private void createResume(int height) {
		restart = new JButton("Restart");
		rrestart = new Rectangle(width / 2 - buttonWidth / 2, height, buttonWidth, buttonHeight);
		restart.setBounds(rrestart);
		window.add(restart);
	}

	private void createRestart(int height) {
		restart = new JButton("Restart");
		rrestart = new Rectangle(width / 2 - buttonWidth / 2, height, buttonWidth, buttonHeight);
		restart.setBounds(rrestart);
		window.add(restart);
	}

	private void createMenu(int height) {
		menu = new JButton("Main Menu");
		rmenu = new Rectangle(width / 2 - buttonWidth / 2, height, buttonWidth, buttonHeight);
		menu.setBounds(rmenu);
		window.add(menu);
	}

	private void createExit(int height) {
		exit = new JButton("Exit");
		rexit = new Rectangle(width / 2 - buttonWidth / 2, height, buttonWidth, buttonHeight);
		exit.setBounds(rexit);
		window.add(exit);
	}


}
