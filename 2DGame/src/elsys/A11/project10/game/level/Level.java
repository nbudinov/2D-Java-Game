package elsys.A11.project10.game.level;

import java.util.Random;

import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.level.tile.Tile;

public class Level {
	private static final Random random = new Random();
	protected int width, height;
	protected int[] tiles;

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];

		generateLevel();
	}

	protected int generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x + y * width] = random.nextInt(10);
			}
		}
		return random.nextInt(10);
	}


	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.getWidth() + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.getHeight() + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y > height ) return Tile.voidTile;
		if (tiles[x + y * width] == 0) return Tile.grass;
		if (tiles[x + y * width] % 5 == 0) return Tile.flower;
		if (tiles[x + y * width] % 9 == 0) return Tile.stone;
		
		return Tile.grass;
	}
}
