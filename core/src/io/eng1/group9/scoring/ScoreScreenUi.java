package io.eng1.group9.scoring;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
    Table table = new Table();
    table.setFillParent(true);
    stage.addActor(table);

    // Title Label
    final Label titleLabel = new Label("High Scores:", skin);
    table.add(titleLabel).padTop(20).padBottom(40).center().row();

    // Score Labels
    List<ScoreManager.PlayerScore> scores = ScoreManager.getScores();
    for (ScoreManager.PlayerScore score : scores) {
      Label scoreLabel = new Label(score.name + ": " + score.score, skin);
      table.add(scoreLabel).center().padBottom(10).row();
    }

    // Instruction Label
    Label instructionLabel = new Label("Press q to quit or enter to play again", skin);
    table.add(instructionLabel).padTop(40).center();
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
