package utm.ad.spaceImpact;

public class Bullet extends DynamicGameObject {
	public static final float BULLET_WIDTH=0.3f;
	public static final float BULLET_HEIGHT=0.3f;
	public static final float BULLET_VELOCITY = 10f;
	
	private float time = 0;
	
	private boolean decayed;
	
	public Bullet (float x, float y){
		super(x,y, BULLET_WIDTH, BULLET_HEIGHT);
		velocity.set(0, BULLET_VELOCITY);
		decayed = false;
	}
	
	public void update(float deltaTime){
		position.add(0, velocity.y * deltaTime);
		bounds.x = position.x - BULLET_WIDTH / 2;
		bounds.y = position.y - BULLET_HEIGHT / 2;
		time += deltaTime;
		
		if (time > 1){
			decayed = true;
			velocity.set(0,0);
		}
	}
	
	public void hitEnemy(){
		velocity.set(0,0);
		decayed = true;
	}
	
	public boolean isDecayed(){
		return decayed;
	}
	
}
