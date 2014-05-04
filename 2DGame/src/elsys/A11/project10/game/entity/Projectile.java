package elsys.A11.project10.game.entity;

import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.graphics.Sprite;

public class Projectile extends Entity {
	public double x,y;
	private double direction;
	private Sprite sprite;
	private double nx, ny;
	private int speed = 5	;
	private int xMoved, yMoved;
	private static int dmg = 20;
	private static int rateOfFire = 5;
	private static int range = 500;

	public Projectile(int x, int y, double direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		sprite = Sprite.projectile;
		
		nx = Math.cos(direction) * speed;
		ny = Math.sin(direction) * speed;
		System.out.println(ny);

		//setDir(direction);
	}

	public void tick() {
		move();

	}

	private void move() {
		//moveProjectile(Mouse.getMouseX(), Mouse.getMouseY(), speed);
		//System.out.println(level.npcs.size());
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
