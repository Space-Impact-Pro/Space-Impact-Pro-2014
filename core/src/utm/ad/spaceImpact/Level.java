package utm.ad.spaceImpact;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Level {
	private List<Enemy> enemyList;
	public final Random rand;
	public Boss boss;
	
	float y;
	
	public Level(){
		enemyList = new ArrayList<Enemy>();
		
		rand = new Random();
	}
	
	public void generateLevel () {
		y = 5f;
		float maxDistance = 0.5f;
		while (y < World.WORLD_HEIGHT - World.WORLD_WIDTH / 2) {
			int type = rand.nextFloat() > 0.8f ? Enemy.ENEMY_TYPE_MOVING : Enemy.ENEMY_TYPE_STATIC;
			float x = rand.nextFloat() * (World.WORLD_WIDTH - Enemy.ENEMY_WIDTH) + Enemy.ENEMY_WIDTH / 2;

			Enemy enemy = new Enemy(type, x, y);
			enemyList.add(enemy);

			y += 2.5f;
			y -= rand.nextFloat() * (maxDistance / 3);
		}

		boss = new Boss(World.WORLD_WIDTH / 2, y);
	}
	
	public float getBossLocationY(){
		return y;
	}
	
	public Boss getBoss(){
		return boss;
	}
	
	public List<Enemy> getEnemy(){
		return enemyList;
	}
}
