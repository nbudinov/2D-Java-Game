package elsys.A11.project10.game.entity;

import java.util.Random;

import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.level.Level;

public abstract class Entity {
	public int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	//public void tick(){
	//}
	
	//public void render(Screen screen){
	//}
	
	public void remove(){
		removed = true;
	}
	
	public boolean isRemoved(){
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
	
}
