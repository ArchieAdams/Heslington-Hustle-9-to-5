package eng1.group9.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import eng1.group9.ScreenUI;
import eng1.group9.gamestate.TilePosition;

/**
 * UI handler for GameScreen
 */
public class GameScreenUI extends ScreenUI {

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private FillViewport fillViewport;
    private ScreenViewport screenViewport;
    private float pixelsPerSquare = 120;

    private Texture playerTexture;


    public GameScreenUI() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 16, 9);
        fillViewport = new FillViewport(16, 9, camera);
        //screenViewport = new ScreenViewport(camera);
        playerTexture = new Texture(Gdx.files.internal("player.png"));
    }


    @Override
    public void update() {
        //fillViewport.apply();
        batch.setProjectionMatrix(camera.combined);
        showBackground();
        showMap();
        showPlayer();
        showHud();
    }

    @Override
    public void resize(int width, int height) {
        fillViewport.update(width, height);
    }

    private void showBackground() {
        ScreenUtils.clear(0, 0, 100, 255);
        camera.update();
    }

    private void showMap() {

    }

//    private int getPlayerHorizontalOffset() {
//        return playerTexture.getWidth() / 2;
//    }
//    private int getPlayerVerticalOffset() {
//        return playerTexture.getWidth() / 2;
//    }

    private void showPlayer() {
        batch.begin();
        TilePosition playerPos = new TilePosition(5, 5); // Update to get from gamestate class
        batch.draw(playerTexture, playerPos.getRow() + 0.25f, playerPos.getColumn() + 0.25f, playerTexture.getWidth() / pixelsPerSquare, playerTexture.getHeight() / pixelsPerSquare);
        batch.end();
        camera.position.set(playerPos.getRow() + 0.25f, playerPos.getColumn() + 0.25f, 0);
    }

    private void showHud() {

    }
}
