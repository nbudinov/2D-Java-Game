package elsys.A11.project10.game.graphics;

import java.util.Random;

import elsys.A11.project10.game.entity.mob.Player;
import elsys.A11.project10.game.level.tile.Tile;

public class Screen {
	private int width, height;
	public int[] pixels;
	public int xOffset, yOffset;
	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

	}


	public void renderTile(int xPos, int yPos, Tile tile) {
		xPos -= xOffset;
		yPos -= yOffset;
		for (int y = 0; y < tile.sprite.getSIZE(); y++) {
			int ya = (y + yPos);

			for (int x = 0; x < tile.sprite.getSIZE(); x++) {
				int xa = x + xPos;
				if (xa < -tile.sprite.getSIZE() || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.getSIZE()];

			}
		}
	}

	public void renderPlayer(int xPos, int yPos, Sprite sprite, boolean xFlip, boolean yFlip) {
		xPos -= xOffset;
		yPos -= yOffset;
		for (int y = 0; y < sprite.getSIZE(); y++) {
			int ya = (y + yPos);
			int yf = y;
			if (yFlip) yf = 15 - y;
			for (int x = 0; x < sprite.getSIZE(); x++) {
				int xa = x + xPos;
				int xf = x;
				if (xFlip) xf = 15 - x;
				if (xa < -sprite.getSIZE() || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int colour = sprite.pixels[xf + yf * sprite.getSIZE()];
				if (colour != 0xff000000) pixels[xa + ya * width] = colour;
			}
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

}