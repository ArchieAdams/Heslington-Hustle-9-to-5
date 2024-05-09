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
import io.eng1.group9.scoring.ScoreManager;
import java.util.Map;

/**
 * UI handler for EndScreen.
 */
class EndScreenUi extends ScreenUi {

  private final HustleGame game;
  private final ScreenViewport viewport;
  private final Stage stage;
  private final Label thanksLabel;
  private final Label scoreLabel;
  private final Label nameLabel;
  private Label eatLabel;
  private Label studyLabel;
  private Label recreationLabel;
  private final Skin skin;

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
    skin = new Skin(Gdx.files.internal("Pixthulhu_UI_Skin/pixthulhuui/pixthulhu-ui.json"));

    thanksLabel = new Label("Thank you for playing Heslington Hustle", skin);
    scoreLabel = new Label("", skin);
    nameLabel = new Label("Enter name: " + game.getGameState().getName(), skin);

    thanksLabel.setAlignment(Align.center);
    scoreLabel.setAlignment(Align.center);
    nameLabel.setAlignment(Align.center);

    eatLabel = null;
    studyLabel = null;
    recreationLabel = null;

    stage.addActor(thanksLabel);
    stage.addActor(scoreLabel);
    stage.addActor(nameLabel);

    initializeScreenLayout();
  }

  private void initializeScreenLayout() {
    Map<String, Integer> activitiesCount = game.getGameState().getActivityCount();

    int studyCount = activitiesCount.get("Study");
    int eatCount = activitiesCount.get("Eat");
    int recreationCount = activitiesCount.get("Recreation");

    scoreLabel.setText(
        String.format("On average per day you:\nStudied: %1d\nAte: %2d\nRelaxed: %3d\nScore: %4d",
            Math.round(studyCount / 7f), Math.round(eatCount / 7f),
            Math.round(recreationCount / 7f), game.getGameState().getScore()));

    positionTopLabels();
    positionBottomLabels(activitiesCount, studyCount, eatCount, recreationCount);
  }

  private void positionTopLabels() {
    int width = viewport.getScreenWidth();
    float height = viewport.getScreenHeight();

    thanksLabel.setPosition((width - thanksLabel.getWidth()) / 2, height / 2 + 250);
    scoreLabel.setPosition((width - scoreLabel.getWidth()) / 2, height / 2 + 110);
  }

  private void positionBottomLabels(Map<String, Integer> activitiesCount,
                                    int studyCount,
                                    int eatCount,
                                    int recreationCount) {
    int width = viewport.getScreenWidth();
    int labelSpacing = 30;
    int startY = viewport.getScreenHeight() / 2 - 50;

    int eatThreshold = ScoreManager.getEatThreshold();
    if (activitiesCount.containsKey("Eat") && eatCount > eatThreshold) {
      eatLabel = new Label(
          "ACHIEVEMENT: Eat more than " + eatThreshold + " times   +10 points", skin
      );
      eatLabel.setPosition((width - eatLabel.getWidth()) / 2, startY);
      stage.addActor(eatLabel);
      startY -= labelSpacing;
    }

    int studyThreshold = ScoreManager.getStudyThreshold();
    if (activitiesCount.containsKey("Study") && studyCount > studyThreshold) {
      System.out.println("studied");
      studyLabel = new Label(
          "ACHIEVEMENT: Study more than " + studyThreshold + " times  +10 points", skin
      );
      studyLabel.setPosition((width - studyLabel.getWidth()) / 2, startY);
      stage.addActor(studyLabel);
      startY -= labelSpacing;
    }

    int recThreshold = ScoreManager.getRecThreshold();
    if (activitiesCount.containsKey("Recreation") && recreationCount > recThreshold) {
      recreationLabel = new Label(
          "ACHIEVEMENT: Do more than " + recThreshold + " recreational "
          + "activities  + 10 points", skin);
      recreationLabel.setPosition((width - recreationLabel.getWidth()) / 2, startY);
      stage.addActor(recreationLabel);
      startY -= labelSpacing;
    }
    nameLabel.setPosition((width - nameLabel.getWidth()) / 2, (startY - labelSpacing));
  }

  @Override
  public void update() {
    ScreenUtils.clear(0, 0, 0, 255);
    nameLabel.setText("Enter name: " + game.getGameState().getName());
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height, true);
  }
}
