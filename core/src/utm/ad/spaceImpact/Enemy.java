package utm.ad.spaceImpact;

public class Enemy extends DynamicGameObject {
	public static final float ENEMY_WIDTH = 1;
	public static final float ENEMY_HEIGHT = 0.6f;
	public static final float ENEMY_VELOCITY = 3f;
	
	int hp;
	
	public Enemy(float x, float y){
		super(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
		velocity.set(ENEMY_VELOCITY, 0);
		hp = 3;
	}
	
	public Enemy(float x, float y, float width, float height){
		super(x, y, width, height);
		velocity.set(ENEMY_VELOCITY, 0);
		hp = 3;
	}
	
	public void update (float deltaTime) {
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x - ENEMY_WIDTH / 2;
		bounds.y = position.y - ENEMY_HEIGHT / 2;

		if (position.x < ENEMY_WIDTH / 2) {
			position.x = ENEMY_WIDTH / 2;
			velocity.x = ENEMY_VELOCITY;
		}
		if (position.x > World.WORLD_WIDTH - ENEMY_WIDTH / 2) {
			position.x = World.WORLD_WIDTH - ENEMY_WIDTH / 2;
			velocity.x = -ENEMY_VELOCITY;
		}
	}
	
	public void decreaseHp(){
		hp -= 1;
	}
	
	public int getHp(){
		return hp;
	}
}
