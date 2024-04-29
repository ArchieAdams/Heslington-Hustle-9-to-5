package eng1.group9;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("HeslingtonHustle");
        config.setDecorated(true);
        //config.setWindowPosition(10,10);

        config.setWindowedMode(1820, 880);
        new Lwjgl3Application(new HustleGame(), config);
    }
}
