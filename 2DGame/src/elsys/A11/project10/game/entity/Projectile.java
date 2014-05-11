package elsys.A11.project10.game.entity;

import elsys.A11.project10.game.entity.Entity;
import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.graphics.Sprite;

public class Projectile extends Entity {
	public double x,y;
	private Sprite sprite;
	private double nx, ny;
	private int speed = 10	;
	private int xMoved, yMoved;
	public static int dmg = 10;
	private static int rateOfFire = 5;
	private static int range = 100;

	public Projectile(int x, int y, double direction) {
		this.x = x;
		this.y = y;
		
		if(dmg >= 10) sprite = Sprite.projectile;
		if(dmg >= 20) sprite = Sprite.projectile20;
		if(dmg >= 30) sprite = Sprite.projectile30;
		if(dmg >= 40) sprite = Sprite.projectile40;
		if(dmg >= 50) sprite = Sprite.projectile50;
		if(dmg >= 100) sprite = Sprite.projectileMax;
		
		
		
		nx = Math.cos(direction) * speed;
		ny = Math.sin(direction) * speed;

		//setDir(direction);
	}

	public void tick() {
		move();

	}

	private void move() {
		x += nx;
		y += ny;
		xMoved+=1;
		yMoved+=1;
	}
	
	

	public void render(Screen screen) {
		screen.renderTile((int)this.x, (int)this.y, sprite, false, false);
	}


	public static int getDmg() {
		return dmg;
	}

	public static int getRateOfFire() {
		return rateOfFire;
	}

	public static int getRange() {
		return range;
	}
	
	public int getxMoved(){
		return xMoved;
	}
	
	public int getyMoved(){
		return xMoved;
	}
}
