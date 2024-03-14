package eng1.group9;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import eng1.group9.endscreen.EndScreen;
import eng1.group9.gamescreen.GameScreen;
import eng1.group9.startscreen.StartScreen;

public class HustleGame extends Game {
	private Screen startScreen;
	private Screen gameScreen;
	private Screen endScreen;


	@Override
	public void create () {
		startScreen = new StartScreen(this);
		gameScreen = new GameScreen(this);
		endScreen = new EndScreen(this);

		setGameScreen();
	}

	public void setStartScreen() {
		setScreen(startScreen);
	}
	public void setGameScreen() {
		setScreen(gameScreen);
	}
	public void setEndScreen() {
		setScreen(endScreen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}
}
