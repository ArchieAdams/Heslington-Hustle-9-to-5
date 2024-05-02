package io.eng1.group9.endscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.eng1.group9.HustleGame;
import io.eng1.group9.ScreenUi;
import java.util.Map;

/**
 * UI handler for EndScreen.
 */
class EndScreenUi extends ScreenUi {

  private final HustleGame game;
  private final ScreenViewport viewport;
  private final Label thanksLabel;
  private final Label scoreLabel;
  private final Stage stage;
  private final Label nameLabel;
  private String name;

  /**
   * Instantiates a new End screen ui.
   *
   * @param game HustleGame object that controls the application
   */
  public EndScreenUi(HustleGame game) {
    this.game = game;
    viewport = new ScreenViewport();
    viewport.apply();
    stage = new Stage(viewport);
    Skin skin = new Skin(Gdx.files.internal("Pixthulhu_UI_Skin/pixthulhuui/pixthulhu-ui.json"));

    thanksLabel = new Label("", skin);
    thanksLabel.setAlignment(Align.center);

    scoreLabel = new Label("", skin);
    scoreLabel.setAlignment(Align.center);

    nameLabel = new Label("", skin);
    nameLabel.setAlignment(Align.center);

    stage.addActor(thanksLabel);
    stage.addActor(scoreLabel);
    stage.addActor(nameLabel);
  }

  @Override
  public void update() {
    ScreenUtils.clear(0, 0, 0, 255);
    Map<String, Integer> activitiesCount = game.getGameState().scoreCalculation();
    int studyCount = activitiesCount.get("Study");
    int eatCount = activitiesCount.get("Eat");
    int recreationCount = activitiesCount.get("Recreation");
    thanksLabel.setText("Thank you for playing Heslington Hustle");
    scoreLabel.setText(
        String.format("On average per day you:\nStudied: %1d\nAte: %2d\nRelaxed: %3d\nScore: %4d",
            Math.round(studyCount / 7f), Math.round(eatCount / 7f),
            Math.round(recreationCount / 7f), game.getGameState().calculateScore()));
    nameLabel.setText("Enter name: " +  game.getGameState().getName());

    int width = viewport.getScreenWidth();
    int height = viewport.getScreenHeight();
    thanksLabel.setX((width - thanksLabel.getWidth()) / 2);
    thanksLabel.setY((height - thanksLabel.getHeight()) / 2 + 150);
    scoreLabel.setX((width - scoreLabel.getWidth()) / 2);
    scoreLabel.setY((height - scoreLabel.getHeight()) / 2 - thanksLabel.getHeight() - 50);
    nameLabel.setX((width - nameLabel.getWidth()) / 2);
    nameLabel.setY((height - nameLabel.getHeight()) / 2 - nameLabel.getHeight() - 200);
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height, true);
  }
}
