package io.eng1.group9;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;

/**
 * Base class which handles stuff common to all screens in the game.
 */
public abstract class BaseScreen implements Screen {

  private final ScreenUi ui;
  private final InputAdapter input;

  /**
   * Instantiates a new Base screen.
   *
   * @param ui    ScreenUI that handles UI updates
   * @param input ScreenInput that handles user input
   */
  public BaseScreen(ScreenUi ui, InputAdapter input) {
    this.ui = ui;
    this.input = input;
  }

  @Override
  public void show() {
    Gdx.input.setInputProcessor(input);
  }

  @Override
  public void render(float delta) {
    update(delta);
    updateUi();
  }

  public abstract void update(float delta);

  @Override
  public void resize(int width, int height) {
    ui.resize(width, height);
  }

  @Override
  public void pause() {
  }

  @Override
  public void resume() {
  }

  @Override
  public void hide() {
  }

  @Override
  public void dispose() {
  }


  /**
   * Update the UI.
   */
  public void updateUi() {
    ui.update();
  }

  public ScreenUi getUi() {
    return ui;
  }

  public InputAdapter getInput() {
    return input;
  }

}
