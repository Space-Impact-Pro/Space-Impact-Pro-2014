package utm.ad.spaceImpact;

//import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;

public class SpaceImpactPro extends Game {
	
		// used by all screens
		public static HighscoreManager highscore;
		public SpriteBatch batcher;
		
		@Override
		public void create () 
		{
			batcher = new SpriteBatch();
//			Settings.load();
			Assets.load();  //lepas ni add
			highscore = new HighscoreManager();
			highscore.addScore("test1",10);
			highscore.addScore("test2",20);
			System.out.print(highscore.getHighscoreString());
			setScreen(new FirstMenuScreen(this));
		}
		
		@Override
		public void render() {
			super.render();
		}
	
		
	
	/*
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	*/
}
