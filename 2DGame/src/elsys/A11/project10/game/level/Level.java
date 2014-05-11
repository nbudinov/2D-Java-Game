package elsys.A11.project10.game.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import elsys.A11.project10.game.entity.Potion;
import elsys.A11.project10.game.entity.Projectile;
import elsys.A11.project10.game.entity.mob.NPC;
import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.level.tile.Tile;

public class Level {

	public static final Random random = new Random();
	public static int width, height;
	private int mapWidth, mapHeight;
	public int[] tilesIn;
	public int[] tiles;
	public List<Projectile> projectiles = new ArrayList<Projectile>();
	public List<NPC> npcs = new ArrayList<NPC>();
	public List<Potion> potions = new ArrayList<Potion>();
	// public static int time = 0;
	public double xRand = 1;
	public double yRand = 1;

	// public Level(int width, int height) {
	// this.width = width;
	// this.height = height;
	// tilesIn = new int[width * height];

	// generateLevel();
	// }

	public Level(String path) {
		loadLevel(path);
	}

	public void tick() {
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).tick();
			removeProjectile(i);
		}
		for (int i = 0; i < npcs.size(); i++) {
			npcs.get(i).tick();
		}

	}

	public void loadLevel(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(Level.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];
			mapWidth = width * 16;
			mapHeight = height * 16;

			image.getRGB(0, 0, width, height, tiles, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Load error level");
		}

	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll / 16;
		int x1 = (xScroll + screen.getWidth() + 16) / 16;
		int y0 = yScroll / 16;
		int y1 = (yScroll + screen.getHeight() + 16) / 16;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}

		for (int i = 0; i < npcs.size(); i++) {
			npcs.get(i).render((int) npcs.get(i).x, (int) npcs.get(i).y, screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}

		for (int i = 0; i < potions.size(); i++) {
			potions.get(i).render(potions.get(i).x, potions.get(i).y, screen);
		}

	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == 0xff00ff00) return Tile.grass;
		if (tiles[x + y * width] == 0xffffff00) return Tile.flower;
		if (tiles[x + y * width] == 0xff7f7f00) return Tile.stone;
		if (tiles[x + y * width] == 0xffff0000) return Tile.lava;
		if (tiles[x + y * width] == 0xff808080) return Tile.wall;
		if (tiles[x + y * width] == 0xff7F0000) return Tile.ground;
		if (tiles[x + y * width] == 0xff6D7F3F) return Tile.chair;
		if (tiles[x + y * width] == 0xffDDFF7F) return Tile.table;
		if (tiles[x + y * width] == 0xff7FFCFF) return Tile.bed;

		return Tile.grass;
	}

	public void chasePlayer(int px, int py, double offset) {
		for (int n = 0; n < npcs.size(); n++) {
			int dx = Math.abs((int) npcs.get(n).x - px);
			int dy = Math.abs((int) npcs.get(n).y - py);
			// boolean test = Math.sqrt(Math.pow(Math.abs((int) npcs.get(0).x -
			// px), 2) + Math.pow(Math.abs((int) npcs.get(0).y - py), 2)) <
			// NPC.range;
			// System.out.println(test);
			//System.out.println((Math.sqrt(Math.pow(Math.abs((int) npcs.get(0).x - px), 2) + Math.pow(Math.abs((int) npcs.get(0).y - py), 2))));
			if (Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)) < NPC.range) {
				npcs.get(n).time = 0;
				if (npcs.get(n).x > px)
					npcs.get(n).xMove = -offset;
				else if (npcs.get(n).x < px) npcs.get(n).xMove = offset;
				else npcs.get(n).xMove = 0;

				if (npcs.get(n).y > py)
					npcs.get(n).yMove = -offset;
				else if (npcs.get(n).y < py) npcs.get(n).yMove = offset;
				else npcs.get(n).yMove = 0;
				
				//if (npcs.get(n).x == px) npcs.get(n).yMove = 0; 
				//if (npcs.get(n).y == py) npcs.get(n).xMove = 0;

			} else {
				npcs.get(n).mobRandomMovement(n);
			}
		}
	}

	/*
	 * public void mobRandomMovement(int n) { if (time == 60) { xRand = new
	 * Random().nextInt(3) - 1; yRand = new Random().nextInt(3) - 1; time = 0; }
	 * npcs.get(n).xMove = xRand; npcs.get(n).yMove = yRand;
	 * 
	 * }
	 */
	private void removeProjectile(int i) {
		if (projectiles.get(i).getxMoved() + projectiles.get(i).getyMoved() > Projectile.getRange()) projectiles.remove(i);
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

}
