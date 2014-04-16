package elsys.A11.project10.game.entity.mob;

import java.util.Random;

import elsys.A11.project10.game.entity.Projectile;
import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.graphics.Sprite;

public class NPC extends Mob {
	Random rand = new Random();
	private int offset = 13;
	public int hbx1;
	public int hby1;
	public int hbx2;
	public int hby2;
	public int hbx3;
	public int hby3;
	public int hbx4;
	public int hby4;

	public NPC(int x, int y) {
		this.x = x;
		this.y = y;
		hbx1 = (x-offset);
		hby1 = (y-offset);
		hbx2 = (x+offset);
		hby2 = (y-offset);
		hbx3 = (x+offset);
		hby3 = (y+offset);
		hbx4 = (x-offset);
		hby4 = (y+offset);
	}

	public void tick() {
		//System.out.println(" hbx1 "+hbx1+" hbx2 "+hbx2+" hxb3 "+ hbx3 + " hbx4 "+ hbx4);
		//System.out.println(" hby1 "+hby1+" hby2 "+hby2+" hby3 "+ hby3 + " hby4 "+ hby4);
		//System.out.println(hp);
	}

	public void render(int x, int y, Screen screen) {
		if (hp > 0)
			screen.renderPlayer(x, y, Sprite.playerFrontStill, true, false);
		else
			screen.renderPlayer(x, y, Sprite.playerDead, false, false);
	}
}
