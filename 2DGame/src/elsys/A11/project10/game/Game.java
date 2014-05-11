package elsys.A11.project10.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import elsys.A11.project10.game.entity.Projectile;
import elsys.A11.project10.game.entity.mob.Player;
import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.input.KeyHandler;
import elsys.A11.project10.game.input.Mouse;
import elsys.A11.project10.game.level.Level;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static int width = 400;
	private static int height = width / 16 * 9;
	private static final int scale = 3;
	private static final String name = "Game";

	public static int ix = 0;
	public static int iy = 0;

	boolean running = false;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private JFrame frame;
	private KeyHandler key;
	private Thread thread;
	private Screen screen;
	private Level level;
	private Player player;
	private Mouse mouse;
	Container pane;

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
		mouse = new Mouse();
		player = new Player(13 * 16, 150, key);
		player.init(level);
		pane = frame.getContentPane();
		addKeyListener(key);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		displayFrame();
		setCoursor();
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
		endOfMapPlayerPos();
		level.render(player.x - screen.getWidth() / 2 + ix, player.y - screen.getHeight() / 2 + iy, screen);
		player.render(screen);

		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		if (level.npcs.size() > 0) level.chasePlayer(player.x, player.y, player.spdNPC);
		// g.drawString("hp " + hp, 400, 400);
		displayHealth(g);
		g.dispose();
		bs.show();

	}

	private void displayFrame() {
		// frame.setUndecorated(true);

		frame.setPreferredSize(new Dimension(width * scale, height * scale));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

	}

		private void displayHealth(Graphics g) {
			g.setFont(new Font("Verdana", 0, 10));
		
			g.setColor(Color.RED);
			g.fillRect(width * scale - 100, 0, (int) player.hp, 25);
			g.setColor(Color.WHITE);
			g.drawString("hp " + player.hp, width * scale - 75, 15);
			
			g.setColor(Color.BLUE);
			g.fillRect(width * scale - 100, 25, player.mana, 25);
			g.setColor(Color.WHITE);
			g.drawString("mana " + player.mana, width * scale - 75, 40);

			Color clr = new Color(150, 110, 110);
			g.setColor(clr);
			g.fillRect(0, height * scale - 50 ,  width * scale , 10 );
			
			Color clr1 = new Color(150, 0, 110);
			g.setColor(clr1);
			g.fillRect( 0, height * scale - 50 ,  player.xp / (player.neededXP / (width * scale) )  , 10 );
			g.setColor(Color.WHITE);
			g.drawString( player.xp + " / " + player.neededXP + " XP " , width * scale - ((width * scale)/2) , height * scale - 42  );

			Color clr2 = new Color(0f, .3f, .5f, .4f);
			g.setColor(clr2);
			g.fillRect( 180, 0 ,  width * scale - 800 , 40 );
			
			g.setColor(Color.ORANGE);
			g.fillRect(width * scale - 1000, height * scale - (height * scale - 10), 100, 25);
			g.setColor(Color.BLACK);
			g.drawString("Dmg: " + Projectile.dmg, width * scale - 975, 25);

			g.setColor(Color.YELLOW);
			g.fillRect(width * scale - 875, height * scale - (height * scale - 10), 100, 25);
			g.setColor(Color.BLACK);
			g.drawString("Spd: " + player.heroSpeed + "/" + player.maxHeroSpeed, width * scale - 850, 25);

			Color clr4 = new Color(.0f, .5f, .8f, .9f);
			g.setColor(clr4);
			g.fillRect(width * scale - 750, height * scale - (height * scale - 10), 100, 25);
			g.setColor(Color.WHITE);
			g.drawString("ManaReg: " + player.regenerateMana + "ps", width * scale - 750, 25);

			g.setColor(clr2);
			g.fillRect( 650, 0 ,  width * scale - 840 , 40 );
			
			Color clr6 = new Color(1f, .5f, .0f, .9f);
			g.setColor(clr6);
			g.fillRect(width * scale - 540, height * scale - (height * scale - 10), 100, 25);
			g.setColor(Color.WHITE);
			g.drawString("NpcsHp: " + player.hpNPC, width * scale - 525, 25);

			Color clr7 = new Color(.7f, .8f, .0f, .9f);
			g.setColor(clr7);
			g.fillRect(width * scale - 420, height * scale - (height * scale - 10), 100, 25);
			g.setColor(Color.WHITE);
			g.drawString("NpcsDmg: " + player.dmgNPC , width * scale - 410, 25);

			Color clr8 = new Color(.9f, .9f, .2f, .9f);
			g.setColor(clr8);
			g.fillRect(width * scale - 300, height * scale - (height * scale - 10), 100, 25);
			g.setColor(Color.WHITE);
			g.drawString("NpcsSpd: " + player.spdNPC , width * scale - 290, 25);

			
			g.setFont(new Font("Verdana", 3, 24));

			Color clr3 = new Color(.0f, .5f, .8f, .6f);
			g.setColor(clr3);
			g.fillOval(-30, -20, 150, 90);

			g.setColor(Color.ORANGE);
			g.drawString(" LVL " + player.heroLvl, 15, 35 );

		}


	private void setCoursor() {
		frame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}

	private void endOfMapPlayerPos() {
		//System.out.println("ix" + ix);
		// " iy " + player.y);
		//System.out.println("game " + this.getWindowWidth() + " screen " + screen.getWidth() + "game " + this.getWindowHeight() + " screen " + screen.getHeight());
		if (player.x < screen.getWidth() / 2 && player.walking)
			ix = screen.getWidth() / 2 - player.x;
		else if (player.x > 128 * 16 - screen.getWidth() / 2 && player.walking) 
			ix = -(screen.getWidth() / 2 - (level.getMapWidth() - player.x));
		// else ix = screen.getWidth() / 2;

		if (player.y < screen.getHeight() / 2 && player.walking) 
			iy = screen.getHeight() / 2 - player.y;
		else if (player.y > 128 * 16 - screen.getHeight() / 2 && player.walking) 
			iy = -(screen.getHeight() / 2 - (level.getMapHeight() - player.y));
	}

	public static int getWindowWidth() {
		return width * scale;
	}

	public static int getWindowHeight() {
		return height * scale;
	}
	
	public static int getScale() {
		return scale;
	}

}
