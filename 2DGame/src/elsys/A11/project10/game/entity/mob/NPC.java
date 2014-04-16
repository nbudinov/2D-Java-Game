package elsys.A11.project10.game.entity.mob;

import java.util.Random;

import elsys.A11.project10.game.entity.Projectile;
import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.graphics.Sprite;

public class NPC extends Mob {
	Random rand = new Random();


	public NPC(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public NPC() {

	}

	public void tick() {
		
	}

	public void render(int x, int y, Screen screen) {
		if (hp > 0)
			screen.renderPlayer(x, y, Sprite.playerFrontStill, true, false);
		else
			screen.renderPlayer(x, y, Sprite.playerDead, false, false);
	}
}
