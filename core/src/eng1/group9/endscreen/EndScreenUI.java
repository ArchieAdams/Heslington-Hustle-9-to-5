package eng1.group9.endscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import eng1.group9.HustleGame;
import eng1.group9.ScreenUI;

import java.util.Map;

/**
 * UI handler for EndScreen
 */
class EndScreenUI extends ScreenUI {

    private final HustleGame game;
    private final ScreenViewport viewport;
    private final Label thanksLabel;
    private final Label scoreLabel;
    private final Stage stage;

    /**
     * @param game HustleGame object that controls the application
     */
    public EndScreenUI(HustleGame game) {
        this.game = game;
        viewport = new ScreenViewport();
        viewport.apply();
        stage = new Stage(viewport);
        Skin skin = new Skin(Gdx.files.internal("Pixthulhu_UI_Skin/pixthulhuui/pixthulhu-ui.json"));
        thanksLabel = new Label("", skin);
        thanksLabel.setAlignment(Align.center);
        scoreLabel = new Label("", skin);
        scoreLabel.setAlignment(Align.center);
        stage.addActor(thanksLabel);
        stage.addActor(scoreLabel);
    }

    @Override
    public void update() {
        ScreenUtils.clear(0, 0, 0, 255);
        Map<String, Integer> activitiesCount = game.getGameState().scoreCalculation();
        int studyCount = activitiesCount.get("Study");
        int eatCount = activitiesCount.get("Eat");
        int recreationCount = activitiesCount.get("Recreation");
        thanksLabel.setText("Thank you for playing Heslington Hustle");
        scoreLabel.setText(String.format("Study: %1d\nEat: %2d\nRecreation: %3d", studyCount, eatCount, recreationCount));

        int width = viewport.getScreenWidth();
        int height = viewport.getScreenHeight();
        thanksLabel.setX((width - thanksLabel.getWidth()) / 2);
        thanksLabel.setY((height - thanksLabel.getHeight()) / 2 + 150);
        scoreLabel.setX((width - scoreLabel.getWidth()) / 2);
        scoreLabel.setY((height - scoreLabel.getHeight()) / 2 - thanksLabel.getHeight() - 50);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
