package eng1.group9.startscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import eng1.group9.ScreenUI;

/**
 * UI handler for StartScreen
 */
public class StartScreenUI extends ScreenUI {

    private ScreenViewport screenViewport;
    private Label titleLabel;
    private Label instructionLabel;
    private Label licenceLabel;
    private Stage stage;

    public StartScreenUI(){
        //Create screen view
        screenViewport = new ScreenViewport();
        screenViewport.setScreenX(10);
        screenViewport.setScreenY(10);

        //Create font for the titles
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Yantramanav/Yantramanav-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        BitmapFont titleFont = generator.generateFont(parameter);

        stage = new Stage(screenViewport);

        Label.LabelStyle titleStyle = new Label.LabelStyle(titleFont, Color.WHITE);
        titleLabel = new Label("HESLINGTON HUSTLE", titleStyle);

        parameter.size = 20;
        BitmapFont instructionFont = generator.generateFont(parameter);
        Label.LabelStyle instructionStyle = new Label.LabelStyle(instructionFont, Color.WHITE);
        instructionLabel = new Label("PRESS ENTER TO PLAY\n\nWASD / Arrow keys move the player\nEnter performs the activity", instructionStyle);
        instructionLabel.setAlignment(Align.center);


        parameter.size = 15;
        BitmapFont licenceFont = generator.generateFont(parameter);
        Label.LabelStyle licenceStyle = new Label.LabelStyle(licenceFont, Color.WHITE);
        licenceLabel = new Label("To view licences used: visit https://eng1-group-9-2024.github.io/Heslington-Hustle-Info/", licenceStyle);

        generator.dispose();
        stage.addActor(instructionLabel);
        stage.addActor(titleLabel);
        stage.addActor(licenceLabel);

    }

    @Override
    public void update() {
        ScreenUtils.clear(0, 0, 100, 255);
        screenViewport.apply();

        int width = screenViewport.getScreenWidth();
        int height = screenViewport.getScreenHeight();
        titleLabel.setX((width - titleLabel.getWidth()) /2);
        titleLabel.setY(height - titleLabel.getHeight() - 20);

        instructionLabel.setX((width - instructionLabel.getWidth()) /2);
        instructionLabel.setY((float) height / 2);

        licenceLabel.setX((width - licenceLabel.getWidth()) /2);
        licenceLabel.setY(10);

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        screenViewport.update(width, height, true);
    }

}
