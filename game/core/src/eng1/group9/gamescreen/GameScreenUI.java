package eng1.group9.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import eng1.group9.HustleGame;
import eng1.group9.ScreenUI;
import eng1.group9.gamestate.TilePosition;
import eng1.group9.gamestate.activities.Activity;
import java.util.Arrays;
import java.util.List;

/**
 * UI handler for GameScreen
 */
public class GameScreenUI extends ScreenUI {

    private HustleGame game;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private FillViewport fillViewport;
    private ScreenViewport screenViewport;
    private float pixelsPerSquare = 120;

    private Texture playerTexture;
    private Texture mapTexture;


    // HUD elements
    private Label.LabelStyle labelStyle;
    private Stage stage;
    private Label timeLabel;
    private ProgressBar energyBar;
    private Label energyLabel;
    private Label dayLabel;
    private TextButton activityButton;

    private List<String> days;


    public GameScreenUI(HustleGame game) {
        days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        fillViewport = new FillViewport(16, 9, camera);
        screenViewport = new ScreenViewport();
        screenViewport.setScreenX(10);
        screenViewport.setScreenY(10);
        playerTexture = new Texture(Gdx.files.internal("player.png"));
        mapTexture = new Texture(Gdx.files.internal("fullmap.png"));
        Skin skin = new Skin(Gdx.files.internal("Pixthulhu_UI_Skin/pixthulhuui/pixthulhu-ui.json"));

        // Set up font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Yantramanav/Yantramanav-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        // Set up HUD
        stage = new Stage(screenViewport);
        Table table = new Table();
        labelStyle = new Label.LabelStyle(font, Color.BLACK);
        timeLabel = new Label("00:00", labelStyle);
        dayLabel = new Label("Monday", labelStyle);
        energyBar = new ProgressBar(0, 100, 1, true, skin);
        energyLabel = new Label("100/100", labelStyle);
        activityButton = new TextButton("Perform Activity", skin);
        activityButton.setWidth(1000);

        stage.addActor(dayLabel);
        stage.addActor(timeLabel);
        stage.addActor(energyBar);
        stage.addActor(energyLabel);
        stage.addActor(activityButton);
    }


    @Override
    public void update() {
        fillViewport.apply();
        batch.setProjectionMatrix(camera.combined);
        showBackground();
        showMap();
        showPlayer();
        showHud();
    }

    @Override
    public void resize(int width, int height) {
        fillViewport.update(width, height);
        screenViewport.update(width, height, true);
    }

    /** Show a coloured background */
    private void showBackground() {
        ScreenUtils.clear(0, 0, 100, 255);
        camera.update();
    }

    /** Draw the map */
    private void showMap() {
        batch.begin();
        batch.draw(mapTexture, 0, 0, mapTexture.getWidth() / pixelsPerSquare, mapTexture.getHeight() / pixelsPerSquare);
        batch.end();
    }

    /** Draw the player */
    private void showPlayer() {
        batch.begin();
        TilePosition playerPos = game.getGameState().getPlayerPosition();
        batch.draw(playerTexture, playerPos.getColumn() + 0.25f, playerPos.getRow() + 0.25f, playerTexture.getWidth() / pixelsPerSquare, playerTexture.getHeight() / pixelsPerSquare);
        batch.end();
        camera.position.set(playerPos.getColumn() + 0.25f, playerPos.getRow() + 0.25f, 0);
    }

    /** Draw the HUD */
    private void showHud() {
        screenViewport.apply();

        int width = screenViewport.getScreenWidth();
        int height = screenViewport.getScreenHeight();

        dayLabel.setX(10);
        dayLabel.setY(height - dayLabel.getHeight() - 10);
        dayLabel.setText(days.get(game.getGameState().getDay()));

        timeLabel.setX(width - timeLabel.getWidth() - 10);
        timeLabel.setY(height - timeLabel.getHeight() - 10);
        timeLabel.setText(game.getGameState().getTime());


        int currentEnergy = game.getGameState().getEnergy();
        energyLabel.setX(10);
        energyLabel.setY(10);
        energyBar.setX(10);
        energyBar.setY(10 + energyLabel.getHeight() + 10);
        energyBar.setValue(currentEnergy);
        energyLabel.setText(currentEnergy + "/100");

        List<Activity> activities = game.getGameState().getActivities();
        String currentActivity = "";
        if (!activities.isEmpty()) {
            currentActivity = activities.get(0).toString();
        }
        activityButton.setX(width - activityButton.getWidth() - 10);
        activityButton.setY(10);
        activityButton.setText(currentActivity);

        stage.draw();
    }
}
