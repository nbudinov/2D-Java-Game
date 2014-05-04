package elsys.A11.project10.game.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

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
			// System.out.println(" IMG === " + image);
			width = image.getWidth();
			height = image.getHeight();
			// int h = image.getHeight();
			// int w = image.getWidth();
			// System.out.println(" W =" + width + " H = " + height);
			tiles = new int[width * height];
			mapWidth = width*16;
			mapHeight = height*16;

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
		//System.out.println("xS = " + xScroll + "  yS = " + yScroll);

		// System.out.println("x0 = " + x0 + " x1 = " + x1 + " y0 = " + y0 +
		// " y1 = " + y1 );
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				// System.out.println("y = " + y0 + " x = " + x0 );
				getTile(x, y).render(x, y, screen);
			}
		}

		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < npcs.size(); i++) {
			npcs.get(i).render(npcs.get(i).x, npcs.get(i).y, screen);
		}

	}

	public Tile getTile(int x, int y) {
		// System.out.println(" X = " + x + " Y = " + y + " W = " + width +
		// " H = " + height);
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
