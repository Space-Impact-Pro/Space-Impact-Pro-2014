package utm.ad.spaceImpact;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;



//used to put items onto the world in the game
public class WorldRenderer {
	static final float FRUSTUM_WIDTH = 10;
	static final float FRUSTUM_HEIGHT = 15;
	World world;
	OrthographicCamera cam;
	SpriteBatch batch;
	
	public WorldRenderer (SpriteBatch batch, World world) {
		this.world = world;
		this.cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
		this.batch = batch;
	}
	
	public void render(){
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		renderBackground();
		renderObjects();
	}
	
	public void renderBackground () {
		batch.disableBlending();
		batch.begin();
		batch.draw(Assets.backgroundRegion, cam.position.x - FRUSTUM_WIDTH / 2, cam.position.y - FRUSTUM_HEIGHT / 2, FRUSTUM_WIDTH,
			FRUSTUM_HEIGHT);
		batch.end();
	}
	
	public void renderObjects () {
		batch.enableBlending();
		batch.begin();
		renderShip();
		renderEnemy();
		renderBoss();
		renderBullet();
		batch.end();
	}
	
	private void renderShip () {
		TextureRegion keyFrame = Assets.ship_imageRegion;
//		place the ship on the gamescreen
		float side = world.ship.velocity.x < 0 ? -1 : 1;
		if (side < 0)
			batch.draw(keyFrame, world.ship.position.x + 0.5f, world.ship.position.y - 0.5f, side * 1, 1);
		else
			batch.draw(keyFrame, world.ship.position.x - 0.5f, world.ship.position.y - 0.5f, side * 1, 1);
		//batch.draw(keyFrame, side, side, side, side);
	}
	
	private void renderEnemy () {
		int len = world.enemies.size();
		for (int i = 0; i < len; i++) {
			Enemy enemy = world.enemies.get(i);
			TextureRegion keyFrame = Assets.monsterRegion;
			float side = enemy.velocity.x < 0 ? -1 : 1;
			if (side < 0)
				batch.draw(keyFrame, enemy.position.x + 0.5f, enemy.position.y - 0.5f, side * 1, 1);
			else
				batch.draw(keyFrame, enemy.position.x - 0.5f, enemy.position.y - 0.5f, side * 1, 1);
		}
	}
	
	private void renderBullet(){
		int len= world.bullets.size();
		for (int i = 0; i < len; i++) {
		Bullet bullet = world.bullets.get(i);
		TextureRegion keyFrame = Assets.bulletRegion;
			batch.draw(keyFrame, bullet.position.x + 0.5f, bullet.position.y - 0.5f, Bullet.BULLET_WIDTH, Bullet.BULLET_HEIGHT);
		}
	}
	
	private void renderBoss(){
		int len = world.boss.size();
		TextureRegion keyFrame = Assets.monsterRegion;
		if (len != 0){
			Boss boss = world.boss.get(0);
			batch.draw(keyFrame, boss.position.x+0.5f, boss.position.y+0.5f, 3, 3);
		}

	}
}
