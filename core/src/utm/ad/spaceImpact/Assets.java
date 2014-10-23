package utm.ad.spaceImpact;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.audio.Music;
//import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
//	public static Texture background;
//	public static TextureRegion backgroundRegion;
//	
//	public static Texture background2;
//	public static TextureRegion backgroundRegion2
    
	
	public static Texture backgroundFirstMenu;
	public static TextureRegion backgroundRegionFirstMenu;
	
	public static Texture backgroundMenu;
	public static TextureRegion backgroundRegionMenu;
	
	public static Texture backgroundShipSelect;
	public static TextureRegion backgroundRegionShipSelect;
	
	public static Texture backgroundModeSelect;
	public static TextureRegion backgroundRegionModeSelect;
	
	public static Texture backgroundShipSelect1;
	public static TextureRegion backgroundRegionShipSelect1;
	
	public static Texture backgroundGameScreen;
	public static TextureRegion backgroundRegionGameScreen;
	
	
	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}
	
	public static void load () {
		backgroundFirstMenu = loadTexture("data/Guest.png");	//load image to texture
		backgroundRegionFirstMenu = new TextureRegion(backgroundFirstMenu, 0, 0, 320, 480);		//get the region from the texture
		
		backgroundMenu = loadTexture("data/MainMenu.png");
		backgroundRegionMenu = new TextureRegion(backgroundMenu, 0, 0, 320, 480);
		
		backgroundShipSelect = loadTexture("data/SelectShip.png");
		backgroundRegionShipSelect = new TextureRegion(backgroundShipSelect, 0, 0, 320, 480);
	
		backgroundModeSelect = loadTexture("data/ModeSelect.png");
		backgroundRegionModeSelect = new TextureRegion(backgroundModeSelect, 0, 0, 320, 480);
		
		backgroundGameScreen = loadTexture("data/NewGameNext.png");
		backgroundRegionGameScreen = new TextureRegion(backgroundGameScreen,  0, 0, 320, 480);
		
//		backgroundShipSelect1 = loadTexture("data/SelectShip-1.png");
//		backgroundRegionShipSelect1 = new TextureRegion(backgroundShipSelect1, 0, 0, 320, 480);

		
	}
	
	public static void loadShipSelect (int index) { //change the space ship selection screen background based on passed integer index
		if (index == 0)
			backgroundShipSelect = loadTexture("data/SelectShip-1.png");
		else if (index == 1)
			backgroundShipSelect = loadTexture("data/SelectShip-2.png");
		else
			backgroundShipSelect = loadTexture("data/SelectShip-3.png");
			
			backgroundRegionShipSelect = new TextureRegion(backgroundShipSelect, 0, 0, 320, 480);
		
	}
	
	
}

