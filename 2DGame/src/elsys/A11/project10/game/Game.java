package elsys.A11.project10.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import elsys.A11.project10.game.entity.mob.Player;
import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.input.KeyHandler;
import elsys.A11.project10.game.level.Level;
import elsys.A11.project10.game.entity.mob.Mob;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static final int width = 300;
	private static final int height = width / 16 * 9;
	private static final int scale = 3;
	private static final String name = "Game";

	boolean running = false;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private JFrame frame;
	private KeyHandler key;
	private Thread thread;
	private Screen screen;
	private Level level;
	private Player player;
	private JFrame butt;

	
	public synchronized void start() {
		thread = new Thread(this, name);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Game() {
		frame = new JFrame(name);
		screen = new Screen(width, height);
		key = new KeyHandler();
		level = new Level("/level.png");
		player = new Player(40 * 16, 60 * 16, key);
		player.init(level);
		
		addKeyListener(key);
		displayFrame();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60;
		long timer = System.currentTimeMillis();
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		requestFocus();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				frame.setTitle(name + " | " + ticks + "ups, " + frames + "fps");

				frames = 0;
				ticks = 0;
			}
		}

	}



	private void tick() {

		key.tick();
		player.tick();
		level.tick();
		if (key.isEsc()) System.exit(0);

	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		level.render( player.x - screen.getWidth() / 2, player.y - screen.getHeight() / 2,  screen); //    player.x - screen.getWidth() / 2, player.y - screen.getHeight() / 2
		player.render(screen);
		
		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 20));
		//g.drawString("hp " + hp, 400, 400);
		g.dispose();
		bs.show();

	}

	private void displayFrame() {
		 //frame.setUndecorated(true);
			
		//frame.add(field, BorderLayout.SOUTH);
		//JTextArea area = new JTextArea(50, 100);
		//frame.add(area, BorderLayout.CENTER);
		//frame.add(panel);
		
		frame.setPreferredSize(new Dimension(width * scale, height * scale));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

	}

}
