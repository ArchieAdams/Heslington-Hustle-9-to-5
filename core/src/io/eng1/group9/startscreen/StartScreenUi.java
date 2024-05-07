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

  private final Label titleLabel;
  private final Label instructionLabel;
  private final Label licenceLabel;

  /**
 * Constructor for the StartScreenUi class.
 * Initializes the screen viewport, stage, and skin.
 * Also creates and adds labels to the stage.
 */
  public StartScreenUi() {
    /**
     * Initializes a new ScreenViewport with the specified screen coordinates.
     * @param screenX The x-coordinate of the top-left corner of the screen.
     * @param screenY The y-coordinate of the top-left corner of the screen.
     */
    screenViewport = new ScreenViewport();
    screenViewport.setScreenX(10);
    screenViewport.setScreenY(10);

    /**
     * Initializes a new Stage with the specified viewport.
     * @param viewport The viewport to use for the stage.
     */
    stage = new Stage(screenViewport);

    /**
     * Loads a new Skin from the specified file path.
     * @param filePath The file path to the JSON file containing the skin data.
     */
    Skin skin = new Skin(Gdx.files.internal("Pixthulhu_UI_Skin/pixthulhuui/pixthulhu-ui.json"));

    /**
     * Creates a new Label with the specified text, skin, and style.
     * @param text The text to display in the label.
     * @param skin The skin to use for the label.
     * @param style The style to use for the label.
     */
    titleLabel = new Label("HESLINGTON HUSTLE", skin, "title");

    /**
     * Creates a new Label with the specified text, skin, and style.
     * @param text The text to display in the label.
     * @param skin The skin to use for the label.
     * @param style The style to use for the label.
     */
    instructionLabel = new Label(
        "INSTRUCTIONS: You need to study for an upcoming exam.\n"
            + "You need to manage time so you also eat,\n"
            + "sleep and relax while getting ready for the exam.\n"
            + "WASD / Arrow keys move the player\n"
            + "Enter performs the activity\n\n\n\n\n"
            + "PRESS ENTER TO PLAY",
        skin, "default");
    instructionLabel.setAlignment(Align.center);


    // Setup the font generator and parameter for licenceLabel
    FreeTypeFontGenerator generator =
        new FreeTypeFontGenerator(Gdx.files.internal("Yantramanav/Yantramanav-Regular.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter =
        new FreeTypeFontGenerator.FreeTypeFontParameter();
    parameter.size = 15; // Set the font size for licenceLabel
    BitmapFont licenceFont = generator.generateFont(parameter);
    generator.dispose();

    // Create a LabelStyle for licenceLabel
    Label.LabelStyle licenceStyle = new Label.LabelStyle(licenceFont, Color.WHITE);
    licenceLabel = new Label(
        "To view licences used: visit https://eng1-group-9-2024.github.io/Heslington-Hustle-Info/",
        licenceStyle);
    /**
     * Adds the specified actor to the stage.
     * @param actor The actor to add to the stage.
     */
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
