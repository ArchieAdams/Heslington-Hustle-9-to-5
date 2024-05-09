package io.eng1.group9.startscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.eng1.group9.ScreenUi;

/**
 * UI handler for StartScreen.
 */
public class StartScreenUi extends ScreenUi {

  private final ScreenViewport screenViewport;
  private final Stage stage;

  /**
 * Constructor for the StartScreenUi class.
 * Initializes the screen viewport, stage, and skin.
 * Also creates and adds labels to the stage.
 */
  public StartScreenUi() {
    screenViewport = new ScreenViewport();
    screenViewport.setScreenX(10);
    screenViewport.setScreenY(10);

    stage = new Stage(screenViewport);

    Skin skin = new Skin(Gdx.files.internal("Pixthulhu_UI_Skin/pixthulhuui/pixthulhu-ui.json"));

    final Label titleLabel = new Label("HESLINGTON HUSTLE", skin, "title");

    Label instructionLabel = new Label(
        "INSTRUCTIONS: You need to study for an upcoming exam.\n"
            + "You need to manage time so you also eat,\n"
            + "sleep and relax while getting ready for the exam.\n"
            + "WASD / Arrow keys move the player\n"
            + "Enter performs the activity\n\n\n\n\n"
            + "PRESS ENTER TO PLAY",
        skin, "default");
    instructionLabel.setAlignment(Align.center);


    // Set up the font generator and parameter for licenceLabel
    FreeTypeFontGenerator generator =
        new FreeTypeFontGenerator(Gdx.files.internal("Yantramanav/Yantramanav-Regular.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter =
        new FreeTypeFontGenerator.FreeTypeFontParameter();
    parameter.size = 15; // Set the font size for licenceLabel
    BitmapFont licenceFont = generator.generateFont(parameter);
    generator.dispose();

    // Create a LabelStyle for licenceLabel
    Label.LabelStyle licenceStyle = new Label.LabelStyle(licenceFont, Color.WHITE);
    Label licenceLabel = new Label(
        "To view licences used: visit https://eng1-group-9-2024.github.io/Heslington-Hustle-Info/",
        licenceStyle);
    stage.addActor(titleLabel);
    stage.addActor(instructionLabel);
    stage.addActor(licenceLabel);

    int width = screenViewport.getScreenWidth();
    int height = screenViewport.getScreenHeight();

    for (Label label : new Label[]{titleLabel, instructionLabel, licenceLabel}) {
      label.setX((width - label.getWidth()) / 2);
      label.setY(height - label.getHeight() - 200);
      height -= (int) (label.getHeight() + 100);
    }
    licenceLabel.setY(40);
  }

  @Override
  public void update() {
    ScreenUtils.clear(0, 0, 0, 1);
    screenViewport.apply();
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    screenViewport.update(width, height, true);
  }

}
