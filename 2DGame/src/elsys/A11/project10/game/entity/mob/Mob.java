package elsys.A11.project10.game.entity.mob;

import elsys.A11.project10.game.entity.Entity;
import elsys.A11.project10.game.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int direction = 1;
	protected boolean moving = false;
	public int hp = 100;
	public boolean dead = false;
	
	public void move(int xDir, int yDir) {
		//hp = 100;
	//	System.out.println(" x = " + x + " y = " + y);
		if (xDir > 0) direction = 1;
		if (xDir < 0) direction = 2;
		if (yDir > 0) direction = 3;
		if (yDir < 0) direction = 4;

		if (!collision(xDir, 0)) {
			x += xDir;
		}
		
		if (!collision(0, yDir)) {
			y += yDir;
		}
		
		if(dieColl(xDir, yDir)) {
			//int i = 0;
				hp = 0;
			
			System.out.println("TI PUKNA " + hp);
			
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
