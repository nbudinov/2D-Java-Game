package elsys.A11.project10.game.entity.mob;

import java.util.ArrayList;
import java.util.List;

import elsys.A11.project10.game.entity.Entity;
import elsys.A11.project10.game.entity.Projectile;
import elsys.A11.project10.game.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	public int direction = 1;
	protected boolean moving = false;
	public int hp = 100;
	public boolean dead = false;
	
	public List<Projectile> projectiles = new ArrayList<Projectile>();
	
	public void move(int xDir, int yDir) {
		//hp = 100;
	//	System.out.println(" x = " + x + " y = " + y);
		if (xDir > 0) direction = 1;
		if (xDir < 0) direction = 2;
		if (yDir > 0) direction = 3;
		if (yDir < 0) direction = 4;
		if (yDir < 0 && xDir < 0) direction = 5;
		if (yDir < 0 && xDir > 0) direction = 6;
		if (yDir > 0 && xDir < 0) direction = 7;
		if (yDir > 0 && xDir > 0) direction = 8;

		if (!collision(xDir, 0)) {
			x += xDir;
		}
		
		if (!collision(0, yDir)) {
			y += yDir;
		}
		
		if(dieColl(xDir, yDir)) {
			//int i = 0;
				hp = 0;
			
			//System.out.println("TI PUKNA " + hp);
			
		}
		
	}

	public boolean dieColl(int xDir, int yDir) {
		boolean die = false;
		int corner;
		for(corner = 0; corner < 4; corner++){
			int xcor = ( ((x + xDir)  + corner * 2 + 6) / 16);
			int ycor = ( ((y + yDir) + corner  * 2 + 7) / 16);
			if (level.getTile(xcor, ycor).die()) die = true;
		}
	return die;
	}
	
	protected void shoot(int x, int ys, int direction){
		Projectile p = new Projectile(x,y,direction);
		level.projectiles.add(p);

	}

	private boolean collision(int xDir, int yDir) {
		boolean solid = false;
		int corner;
		for(corner = 0; corner < 4; corner++){
			int xcor = ( ((x + xDir)  + corner * 2 + 6) / 16);
			int ycor = ( ((y + yDir) + corner  * 2 + 7) / 16);
			if( level.getTile( xcor, ycor ).solid() )  solid = true;				
		}	
	
		//System.out.println(" = " + (x + xDir) / 16 + " =  "+(y + yDir) / 16);
		return solid;
	}
	

}
