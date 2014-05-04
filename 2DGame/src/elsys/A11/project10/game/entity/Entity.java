package elsys.A11.project10.game.entity;

import elsys.A11.project10.game.level.Level;

public abstract class Entity {
	public int x, y;
	private boolean removed = false;
	protected Level level;

	

	
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
