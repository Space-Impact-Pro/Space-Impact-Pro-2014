package utm.ad.spaceImpact.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import utm.ad.spaceImpact.SpaceImpactPro;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Space Impact Pro";
		config.width = 320;
		config.height = 480;
		new LwjglApplication(new SpaceImpactPro(), config);
	}
}
