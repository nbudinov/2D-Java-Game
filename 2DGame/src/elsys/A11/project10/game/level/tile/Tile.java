package elsys.A11.project10.game.level.tile;

import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.graphics.Sprite;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	private boolean solid;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile stone = new StoneTile(Sprite.stone);
	public static Tile flower = new GrassTile(Sprite.flower);
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen){
		
	}
	
	public boolean isSolid(){
		return this.solid;
	}
	
	public void setSolid(boolean solid){
		this.solid = solid;
	}
}
