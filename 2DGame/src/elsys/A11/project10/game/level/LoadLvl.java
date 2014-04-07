package elsys.A11.project10.game.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import elsys.A11.project10.game.level.tile.Tile;

public class LoadLvl extends Level
{

	public LoadLvl(String path) {
		super(path);
	}

	public void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(LoadLvl.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
		//	System.out.println(" W =" + width + " H = " + height);
		//	height = 16;
		//	width = 16;
		//	width = image.getWidth();
		//	height = image.getHeight();
			
			//	tiles = new Tile[w * h];
			tiles = new int[w * h];
			
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Load error level");
		}
		
	}
	
	public void generateLevel() {
	}

}
