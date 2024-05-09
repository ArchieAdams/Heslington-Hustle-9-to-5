package io.eng1.group9.scoring;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.eng1.group9.ScreenUi;
import java.util.List;


/**
 * UI handler for EndScreen.
 */
class ScoreScreenUi extends ScreenUi {

  private final ScreenViewport viewport;
  private final Stage stage;
  private final Skin skin;


  /**
   * Instantiates a new End screen ui.
   */
  public ScoreScreenUi() {
    viewport = new ScreenViewport();
    viewport.apply();
    stage = new Stage(viewport);
    skin = new Skin(Gdx.files.internal("Pixthulhu_UI_Skin/pixthulhuui/pixthulhu-ui.json"));

    initScoreLabels();
  }

  private void initScoreLabels() {
    int width = viewport.getScreenWidth();
    int height = viewport.getScreenHeight();
    int y = height / 2 + 320; // Starting y position for the first label
    final Label titleLabel = new Label("High Scores:", skin);
    titleLabel.setPosition((float) width / 2, y, Align.center);
    y -= 80;
    stage.addActor(titleLabel);


    List<ScoreManager.PlayerScore> scores = ScoreManager.getScores();
    for (ScoreManager.PlayerScore score : scores) {
      Label scoreLabel = new Label(score.name + ": " + score.score, skin);
      scoreLabel.setPosition((float) width / 2, y, Align.center);
      y -= 50; // Move next label down
      stage.addActor(scoreLabel);
    }
    y -= 30;
    Label instructionLabel = new Label("Press q to quit or enter to play again", skin);
    stage.addActor(instructionLabel);
    instructionLabel.setPosition((float) width / 2, y, Align.center);
  }

  @Override
  public void update() {
    ScreenUtils.clear(0, 0, 0, 1);
    stage.act(Gdx.graphics.getDeltaTime());
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height, true);
  }
}
