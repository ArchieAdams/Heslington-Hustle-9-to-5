package io.eng1.group9.startscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.eng1.group9.ScreenUi;

/**
 * This class represents the UI for the Start Screen of the game.
 */
public class StartScreenUi extends ScreenUi {
  private final ScreenViewport screenViewport;
  private final Stage stage;
  private final Skin skin;

  /**
   * This class represents the UI for the Start Screen of the game.
   */
  public StartScreenUi() {
    screenViewport = new ScreenViewport();
    stage = new Stage(screenViewport);
    skin = new Skin(Gdx.files.internal("Pixthulhu_UI_Skin/pixthulhuui/pixthulhu-ui.json"));

    setupUi();
  }

  private void setupUi() {
    Table table = new Table();
    table.setFillParent(true); // The table will size itself to the stage
    stage.addActor(table);

    // Title label
    Label titleLabel = new Label("HESLINGTON HUSTLE", skin, "title");
    table.add(titleLabel).padBottom(20).row();

    // Instruction label
    Label instructionLabel = new Label("INSTRUCTIONS: You need to study for an upcoming exam.\n"
            + "You need to manage time so you also eat,\n"
            + "sleep and relax while getting ready for the exam.\n"
            + "WASD / Arrow keys move the player\n"
            + "Enter performs the activity\n\n\n\n\n"
            + "PRESS ENTER TO PLAY", skin, "default");
    instructionLabel.setAlignment(Align.center);
    table.add(instructionLabel).padBottom(20).row();

    // Licence label
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
            Gdx.files.internal("Yantramanav/Yantramanav-Regular.ttf")
            );
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    parameter.size = 15;
    BitmapFont licenceFont = generator.generateFont(parameter);
    generator.dispose();

    Label.LabelStyle licenceStyle = new Label.LabelStyle(licenceFont, Color.WHITE);
    Label licenceLabel = new Label(
        "To view licences used: visit https://eng1-group-9-2024.github.io/Heslington-Hustle-Info/", licenceStyle
             );
    table.add(licenceLabel).padTop(10).row();
  }

  @Override
  public void update() {
    ScreenUtils.clear(0, 0, 0, 1);
    screenViewport.apply();
    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    screenViewport.update(width, height, true);
    stage.getViewport().update(width, height, true);
  }
}
