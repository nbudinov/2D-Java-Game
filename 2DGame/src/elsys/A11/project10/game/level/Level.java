package elsys.A11.project10.game.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import elsys.A11.project10.game.entity.Projectile;
import elsys.A11.project10.game.entity.mob.Mob;
import elsys.A11.project10.game.entity.mob.NPC;
import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.level.tile.Tile;

public class Level {
	
	
	public static final Random random = new Random();
	protected int width, height;
	protected int[] tilesIn;
	protected int[] tiles;
	public List<Projectile> projectiles = new ArrayList<Projectile>();
	public List<NPC> npcs = new ArrayList<NPC>();
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesIn = new int[width * height];

	//	generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
	}


	public void tick(){
		for (int i= 0 ; i < projectiles.size(); i++){
			projectiles.get(i).tick();
			removeProjectile(i);
		}
			for (int i= 0 ; i < npcs.size(); i++){
			npcs.get(i).tick();

		}
			
	}

	public void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
		//	System.out.println(" W =" + width + " H = " + height);	
			tiles = new int[w * h];
			
			image.getRGB(0, 0, w, h, tiles, 0, w);
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
				//System.out.println("y = " + y0 + " x = " + x0 );
				getTile(x, y).render(x, y, screen);
			}
		}
		
		for (int i= 0 ; i < projectiles.size(); i++){
			projectiles.get(i).render(screen);
		}
		for (int i= 0 ; i < npcs.size(); i++){
			npcs.get(i).render(npcs.get(i).x,npcs.get(i).y,screen);
		}
		
	}
	
	public Tile getTile(int x, int y) {
	//	System.out.println(" X = " + x + " Y = " + y  + " W = " + width + " H = " + height);
		if (x < 0 || y < 0 || x >= width || y >= height )  return Tile.voidTile;
		if (tiles[x + y * width] == 0xff00ff00) return Tile.grass;
		if (tiles[x + y * width] == 0xffffff00)	return Tile.flower;
		if (tiles[x + y * width] == 0xff7f7f00) return Tile.stone;
		if (tiles[x + y * width] == 0xffff0000) return Tile.lava;
		if (tiles[x + y * width] == 0xff808080) return Tile.wall;
		if (tiles[x + y * width] == 0xff7F0000) return Tile.ground;
		if (tiles[x + y * width] == 0xff6D7F3F) return Tile.chair;
		if (tiles[x + y * width] == 0xffDDFF7F) return Tile.table;
		if (tiles[x + y * width] == 0xff7FFCFF) return Tile.bed;
		
		
		return Tile.grass;
	}
	
	private void removeProjectile(int i){
		if (projectiles.get(i).getxMoved()+projectiles.get(i).getyMoved() > Projectile.getRange())
			projectiles.remove(i);
		}
	
	
}
