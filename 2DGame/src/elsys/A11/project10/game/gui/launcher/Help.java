package elsys.A11.project10.game.gui.launcher;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Help extends Launcher {

	private static final long serialVersionUID = 1L;

	private int width = 400;
	private int height = 300;

	private JButton OK;
	private Rectangle rOK;

	private JLabel instr1, instr2, instr3, instr4;

	ImageIcon icon = new ImageIcon("/NormalLevel.png");

	public Help() {
		super(2);
		setTitle("Help");
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

	private void createOkButton() {
		OK = new JButton("OK");
		rOK = new Rectangle(width - 80, height - 60, buttonWidth - 10, buttonHeight - 10);
		OK.setBounds(rOK);
		window.add(OK);
	}

	private void createWindow() {
		instr1 = new JLabel("Use W,A,S,D to move ", SwingConstants.HORIZONTAL);
		instr2 = new JLabel("Use LMB to shoot and RMB for powerful attacks", SwingConstants.HORIZONTAL);
		instr3 = new JLabel("sample text ", SwingConstants.HORIZONTAL);
		instr4 = new JLabel("sample text ", SwingConstants.HORIZONTAL);


		window.setLayout(new GridLayout(0, 1));
		window.add(instr1);
		window.add(instr2);
		window.add(instr3);
		window.add(instr4);
	}

}
