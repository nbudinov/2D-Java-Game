package elsys.A11.project10.game.level.tile;

import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.graphics.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	
}
