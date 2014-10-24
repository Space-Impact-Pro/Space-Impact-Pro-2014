package utm.ad.spaceImpact;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


//used for object that appears on screen that does not move
public class GameObject {
	public final Vector2 position;
	public final Rectangle bounds;

	public GameObject (float x, float y, float width, float height) {
		this.position = new Vector2(x, y);
		this.bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
	}
}
