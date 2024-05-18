package io.eng1.group9.endscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.eng1.group9.HustleGame;
import io.eng1.group9.ScreenUi;
import io.eng1.group9.scoring.ScoreManager;
import java.util.Map;

class EndScreenUi extends ScreenUi {
  private final HustleGame game;
  private final Stage stage;
  private final Skin skin;
  private Label nameLabel;
  private Table table;

  public EndScreenUi(HustleGame game) {
    this.game = game;
    stage = new Stage(new ScreenViewport());
    skin = new Skin(Gdx.files.internal("Pixthulhu_UI_Skin/pixthulhuui/pixthulhu-ui.json"));
    setupUi();
  }

  private void setupUi() {
    table = new Table();
    table.setFillParent(true);
    stage.addActor(table);
    table.setDebug(true);

    // Thank you label
    Label thanksLabel = new Label("Thank you for playing Heslington Hustle", skin);
    thanksLabel.setAlignment(Align.center);
    table.add(thanksLabel).center().padBottom(60);
    table.row();

    // Score labels, detailed per day
    updateScoreLabels(table);

    // Achievement labels
    addAchievementLabels(table);
  }

  public void updateNameLabel() {
    nameLabel.setText("Enter name: " + game.getGameState().getName());
    table.invalidate(); // Forces the table to re-layout its children
  }

  private void updateScoreLabels(Table table) {


    Map<String, Integer> activitiesCount = game.getGameState().getActivityCount();
    int studyCount = activitiesCount.get("Study");
    int eatCount = activitiesCount.get("Eat");
    int recreationCount = activitiesCount.get("Recreation");

    table.row();
    Label dailyAverageLabel = new Label("On average per day you:", skin);
    dailyAverageLabel.setAlignment(Align.center);
    table.add(dailyAverageLabel).center();

    table.row();
    Label studiedLabel = new Label(String.format("Studied %d times", Math.round(studyCount / 7f)), skin);
    studiedLabel.setAlignment(Align.center);
    table.add(studiedLabel).center();

    table.row();
    Label ateLabel = new Label(String.format("Ate: %d times", Math.round(eatCount / 7f)), skin);
    ateLabel.setAlignment(Align.center);
    table.add(ateLabel).center();

    table.row();
    Label relaxedLabel = new Label(String.format("Relaxed: %d times", Math.round(recreationCount / 7f)), skin);
    relaxedLabel.setAlignment(Align.center);
    table.add(relaxedLabel).center().padBottom(60);

    table.row();
    Label scoreLabel = new Label(String.format("Score: %d", game.getGameState().getScore()), skin);
    scoreLabel.setAlignment(Align.center);
    table.add(scoreLabel).center().padBottom(60);
  }

  private void addAchievementLabels(Table table) {
    Map<String, Integer> activitiesCount = game.getGameState().getActivityCount();
    int defaultEatCount = activitiesCount.getOrDefault("Eat", 0);
    int eatThreshold = ScoreManager.getEatThreshold();
    addLabelIfThresholdExceeded(table, "Eat", defaultEatCount, eatThreshold);

    int defaultStudyCount = activitiesCount.getOrDefault("Study", 0);
    int studyThreshold = ScoreManager.getStudyThreshold();
    addLabelIfThresholdExceeded(table, "Study", defaultStudyCount, studyThreshold);

    int defaultRecreationCount = activitiesCount.getOrDefault("Recreation", 0);
    int recreationThreshold = ScoreManager.getRecThreshold();
    addLabelIfThresholdExceeded(table, "Recreation", defaultRecreationCount, recreationThreshold);


    if (ScoreManager.isPenaltyApplied()) {
      table.row();
      Label penaltyLabel = new Label(
          "A score penalty was applied. Make sure to eat, study and relax every day!", skin
          );
      penaltyLabel.setAlignment(Align.center);
      table.add(penaltyLabel).center().padTop(60);
    }

    // Name label
    table.row();
    nameLabel = new Label("Enter name: " + game.getGameState().getName(), skin);
    nameLabel.setAlignment(Align.center);
    table.add(nameLabel).center().padTop(60);

  }

  private void addLabelIfThresholdExceeded(Table table, String activity, int count, int threshold) {
    table.row();
    if (count > threshold) {
      Label label = new Label(String.format(
          "ACHIEVEMENT: %s more than %d times +10 points", activity, threshold), skin
          );
      label.setAlignment(Align.center);
      table.add(label).center();
      table.row();
    }
  }

  @Override
  public void update() {
    ScreenUtils.clear(0, 0, 0, 255);
    stage.act(Gdx.graphics.getDeltaTime());
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);
  }
}
