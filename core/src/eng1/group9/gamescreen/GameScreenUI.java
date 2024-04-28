package eng1.group9.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import eng1.group9.HustleGame;
import eng1.group9.ScreenUI;
import eng1.group9.gamestate.activities.Activity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * UI handler for GameScreen
 */
public class GameScreenUI extends ScreenUI {

    private final HustleGame game;

    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final FillViewport fillViewport;
    private final ScreenViewport screenViewport;
    private final float pixelsPerSquare = 120;

    private final Texture playerTexture;
    private final Texture mapTexture;
    private final Stage stage;
    private final Label timeLabel;
    private final ProgressBar energyBar;
    private final Label energyLabel;
    private final Label dayLabel;
    private final Label scoreLabel;
    private final TextButton activityButton;
    private final List<String> days;
    private final Vector3 previousPos;
    // HUD elements
    private Label.LabelStyle labelStyle;


    public GameScreenUI(HustleGame game) {
        days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        previousPos = new Vector3(0, 0, 0);
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
        timeLabel = new Label("00:00", skin);
        dayLabel = new Label("Monday", skin);
        energyBar = new ProgressBar(0, 100, 1, true, skin);
        energyLabel = new Label("100/100", skin);
        scoreLabel = new Label("", skin);
        activityButton = new TextButton("Perform Activity", skin);
        activityButton.setWidth(1000);
        dayLabel.setFontScale(1.2f);
        timeLabel.setFontScale(1.2f);
        timeLabel.setAlignment(Align.right);

        stage.addActor(dayLabel);
        stage.addActor(timeLabel);
        stage.addActor(energyBar);
        stage.addActor(energyLabel);
        stage.addActor(activityButton);
        stage.addActor(scoreLabel);
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

    /**
     * Show a coloured background
     */
    private void showBackground() {
        ScreenUtils.clear(0, 0, 100, 255);
        camera.update();
    }

    /**
     * Draw the map
     */
    private void showMap() {
        batch.begin();
        batch.draw(mapTexture, 0, 0, mapTexture.getWidth() / pixelsPerSquare, mapTexture.getHeight() / pixelsPerSquare);
        batch.end();
    }

    /**
     * Draw the player
     */
    private void showPlayer() {
        batch.begin();
        int x_offset = 1;
        int y_offset = 1;
        Vector2 playerPos = game.getGameState().getPlayerPosition();
        Vector3 targetPosition = new Vector3(x_offset + playerPos.x + 0.25f, y_offset + playerPos.y + 0.25f, 0);
        Vector3 interpolatedPos = previousPos.lerp(targetPosition, 0.1f);
        batch.draw(playerTexture, interpolatedPos.x, interpolatedPos.y, playerTexture.getWidth() / pixelsPerSquare, playerTexture.getHeight() / pixelsPerSquare);
        batch.end();
        camera.position.lerp(targetPosition, 0.1f);
    }

    /**
     * Draw the HUD
     */
    private void showHud() {
        screenViewport.apply();

        int width = screenViewport.getScreenWidth();
        int height = screenViewport.getScreenHeight();

        dayLabel.setX(10);
        dayLabel.setY(height - dayLabel.getHeight() - 10);
        dayLabel.setText(days.get(game.getGameState().getDayCount()));

        timeLabel.setX(width - timeLabel.getWidth() - 10);
        timeLabel.setY(height - timeLabel.getHeight() - 10);
        timeLabel.setText("Time left: " + game.getGameState().getTime());


        int currentEnergy = game.getGameState().getEnergy();
        energyLabel.setX(10);
        energyLabel.setY(10);
        energyBar.setX(10);
        energyBar.setY(10 + energyLabel.getHeight() + 10);
        energyBar.setValue(currentEnergy);
        energyLabel.setText(currentEnergy + "/100");

        Map<String, Integer> activityCounts = game.getGameState().scoreCalculation();
        scoreLabel.setText(String.format("Study: %1d\nEat: %2d\nRecreation: %3d", activityCounts.get("Study"), activityCounts.get("Eat"), activityCounts.get("Recreation")));
        scoreLabel.setX(width - scoreLabel.getWidth() - 10);
        scoreLabel.setY(height / 2f - scoreLabel.getHeight());
        scoreLabel.setAlignment(Align.right);

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
