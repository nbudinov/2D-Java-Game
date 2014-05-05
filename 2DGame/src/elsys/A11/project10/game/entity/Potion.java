package elsys.A11.project10.game.entity;

import java.util.Random;

import elsys.A11.project10.game.entity.Entity;
import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.graphics.Sprite;

public class Potion extends Entity {
	
	public int px1; 
	public int px2;
	public int py2;
	public int py3;

	public int type;
	
	Random rand = new Random();

	//private Sprite speedPotionSprite;  	//increases speed with 1
	private Sprite sprite;
	int offset = 13;
	
	public Potion(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
		px1 = (x-offset);
		px2 = (x+offset);
		py2 = (y-offset);
		py3 = (y+offset);
	
		if (type == 0 ) sprite = Sprite.speedPotionSprite;
		if (type == 1 ) sprite = Sprite.dmgPotionSprite;
		if (type == 2 ) sprite = Sprite.hpPotionSprite;
		if (type == 3 ) sprite = Sprite.manaPotionSprite;
		
		}
	
	public void render(int x, int y, Screen screen) {
		//int r = rand.nextInt(2 - 0) + 0;
		//System.out.println("r = " + r);
		
		screen.renderTile(x, y, sprite, false, false);
	}
	
	
	
}