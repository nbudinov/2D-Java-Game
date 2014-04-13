package elsys.A11.project10.game.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import elsys.A11.project10.game.entity.Projectile;
import elsys.A11.project10.game.entity.mob.Mob;
import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.level.tile.Tile;

public class Level {
	
//	Tile[] tiles;
	
	public static final Random random = new Random();
	protected int width, height;
	protected int[] tilesIn;
	protected int[] tiles;
	public List<Projectile> projectiles = new ArrayList<Projectile>();
		
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesIn = new int[width * height];

		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();

	}
	
	public void loadLevel(String path) {
	}

	protected void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tilesIn[x + y * width] = random.nextInt(10);
			}
		}
	}

	public void tick(){
		for (int i= 0 ; i < projectiles.size(); i++){
			projectiles.get(i).tick();
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
		
		for (int i= 0 ; i < projectiles.size(); i++){
			projectiles.get(i).render(screen);
		}
	}
	public Tile getTile(int x, int y) {
	//	System.out.println(" X = " + x + " Y = " + y  + " W = " + width + " H = " + height);
		if (x < 0 || y < 0 || x >= width || y >= height ) return Tile.voidTile;
		if (tiles[x + y * width] == 0xff00ff00) return Tile.grass;
		if (tiles[x + y * width] == 0xffffff00) return Tile.flower;
		if (tiles[x + y * width] == 0xff7f7f00) return Tile.stone;
		if (tiles[x + y * width] == 0xffff0000) return Tile.lava;
		//if (tiles[x + y * width] == ) return Tile.wall;
		//if (tiles[x + y * width] == ) return Tile.ground;
		return Tile.grass;
	}
	
	
}
