package eng1.group9;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import eng1.group9.EndScreen.EndScreen;
import eng1.group9.GameScreen.GameScreen;
import eng1.group9.GameScreen.GameScreenInput;
import eng1.group9.GameScreen.GameScreenUI;
import eng1.group9.StartScreen.StartScreen;
import eng1.group9.StartScreen.StartScreenInput;
import eng1.group9.StartScreen.StartScreenUI;

public class HustleGame extends Game {
	private Screen startScreen;
	private Screen gameScreen;
	private Screen endScreen;


	@Override
	public void create () {
		startScreen = new StartScreen(this);
		gameScreen = new GameScreen(this);
		endScreen = new EndScreen(this);

		this.setScreen(gameScreen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}
}
