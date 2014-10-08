package utm.ad.spaceImpact;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class GameScreen extends ScreenAdapter {
	SpaceImpactPro game;
	OrthographicCamera guiCam;
	Rectangle backBounds;
	Vector3 touchPoint;
	String[] highScores;
	float xOffset = 0;

	public GameScreen (SpaceImpactPro game) {
		this.game = game;

		guiCam = new OrthographicCamera(320, 480);
		guiCam.position.set(320 / 2, 480 / 2, 0);
		backBounds = new Rectangle(0, 0, 64, 64);
		touchPoint = new Vector3();
		
		backBounds = new Rectangle(160 - 150, 240 , 300, 32);
//		highScores = new String[5];
//		for (int i = 0; i < 5; i++) {
//			highScores[i] = i + 1 + ". " + Settings.highscores[i];
//			xOffset = Math.max(Assets.font.getBounds(highScores[i]).width, xOffset);
//		}
//		xOffset = 160 - xOffset / 2 + Assets.font.getSpaceWidth() / 2;
	}

	public void update () {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

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
		game.batcher.draw(Assets.backgroundRegionGameScreen, 0, 0, 320, 480);
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
