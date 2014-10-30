package utm.ad.spaceImpact;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

//todo update ship based on Ship.java
public class SelectShipScreen extends ScreenAdapter {
	SpaceImpactPro game;
	OrthographicCamera guiCam;
	Rectangle backBounds;
	Rectangle ship1Bounds;
	Rectangle ship2Bounds;
	Rectangle ship3Bounds;
	Vector3 touchPoint;
	String[] highScores;
	float xOffset = 0;

	public SelectShipScreen (SpaceImpactPro game) {
		this.game = game;

		guiCam = new OrthographicCamera(320, 480);
		guiCam.position.set(320 / 2, 480 / 2, 0);
		backBounds = new Rectangle(0, 0, 64, 64);
		
		ship1Bounds = new Rectangle(60, 200, 50, 50);
		ship2Bounds = new Rectangle(60 + 120, 200, 50, 50);
		ship3Bounds = new Rectangle(60 + 60, 120, 50, 50);
		
		touchPoint = new Vector3();

//		xOffset = 160 - xOffset / 2 + Assets.font.getSpaceWidth() / 2;
	}

	public void update () {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			//ship Selection
			if (ship1Bounds.contains(touchPoint.x, touchPoint.y)){
				Assets.loadShipSelect(0);
				game.setScreen(new SelectShip1Screen(game));
				Assets.loadShip(0);
				return;
			}
			if (ship2Bounds.contains(touchPoint.x, touchPoint.y)){
				Assets.loadShipSelect(1);
				game.setScreen(new SelectShip1Screen(game));
				Assets.loadShip(1);
				return;
			}
			if (ship3Bounds.contains(touchPoint.x, touchPoint.y)){
				Assets.loadShipSelect(2);
				game.setScreen(new SelectShip1Screen(game));
				Assets.loadShip(2);
				return;
			}
			if (backBounds.contains(touchPoint.x, touchPoint.y)) {
				//Assets.playSound(Assets.clickSound);
				game.setScreen(new MainMenuScreen(game));
				return;
			}
		}
	}

	public void draw () {
		GL20 gl = Gdx.gl;
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();

		game.batcher.setProjectionMatrix(guiCam.combined);
		game.batcher.disableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.backgroundRegionShipSelect, 0, 0, 320, 480);
		game.batcher.end();

//		game.batcher.enableBlending();
//		game.batcher.begin();
//		game.batcher.draw(Assets.highScoresRegion, 10, 360 - 16, 300, 33);

//		float y = 230;
//		for (int i = 4; i >= 0; i--) {
//			Assets.font.draw(game.batcher, highScores[i], xOffset, y);
//			y += Assets.font.getLineHeight();
//		}

//		game.batcher.draw(Assets.arrow, 0, 0, 64, 64);
//		game.batcher.end();
	}

	@Override
	public void render (float delta) {
		update();
		draw();
	}
}

//