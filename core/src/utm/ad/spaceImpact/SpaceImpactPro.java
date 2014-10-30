package utm.ad.spaceImpact;

//import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;

public class SpaceImpactPro extends Game {
	
	// used by all screens
		public SpriteBatch batcher;
		
		@Override
		public void create () 
		{
			batcher = new SpriteBatch();
//			Settings.load();
			Assets.load();  //lepas ni add
			HighscoreManager highscore = new HighscoreManager();
			highscore.addScore("Akram",230);
			setScreen(new FirstMenuScreen(this));
		}
		///score
		public void highscore(String name, int score)
		{
	        HighscoreManager highscore = new HighscoreManager();
	        highscore.addScore(name,score);
	        highscore.addScore("Muin",230);
	        
	        System.out.print(highscore.getHighscoreString());
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
