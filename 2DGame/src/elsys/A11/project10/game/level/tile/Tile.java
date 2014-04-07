package elsys.A11.project10.game.level.tile;

import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.graphics.Sprite;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	public boolean solid;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile stone = new StoneTile(Sprite.stone);
	public static Tile flower = new GrassTile(Sprite.flower);
	public static Tile wall = new WallTile(Sprite.wall);
	public static Tile lava = new LavaTile(Sprite.lava);
	public static Tile ground  = new GroundTile(Sprite.ground);
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen){
		
	}
	
	public boolean solid(){
		return false;
	}
	
	public boolean die() {
		return false; 
	}
	
	
}
