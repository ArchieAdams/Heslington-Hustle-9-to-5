package io.eng1.group9;

import io.eng1.group9.scoring.ScoreManager;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * The type Leaderboard tests.
 */
@RunWith(GdxTestRunner.class)
public class LeaderboardTests {

  /**
   * Test scores empty when starting.
   */
  @Test
  public void testScoresEmptyWhenStarting() {
    ScoreManager.resetScores();
    Assert.assertEquals(ScoreManager.getScores().size(), 0);
  }

  /**
   * Test reset score.
   */
  @Test
  public void testResetScore() {
    ScoreManager.resetScores();
    String name = "test";
    int score = 5;
    ScoreManager.addScore(name, score);
    Assert.assertFalse(ScoreManager.getScores().isEmpty());
    ScoreManager.resetScores();
    Assert.assertEquals(ScoreManager.getScores().size(), 0);
  }

  /**
   * Test score adds.
   */
  @Test
  public void testScoreAdds() {
    ScoreManager.resetScores();
    String name = "test";
    int score = 5;
    ScoreManager.addScore(name, score);
    List<ScoreManager.PlayerScore> playerScores = ScoreManager.getScores();
    Assert.assertFalse(playerScores.isEmpty());
    ScoreManager.PlayerScore playerScore = playerScores.get(0);
    Assert.assertEquals(playerScore.getName(), name);
    Assert.assertEquals(playerScore.getScore(), score);
  }

  /**
   * Test score top ten.
   */
  @Test
  public void testScoreTopTen() {
    ScoreManager.resetScores();
    String name = "test";
    for (int i = 0; i < 10; i++) {
      ScoreManager.addScore(name + i, i);
      List<ScoreManager.PlayerScore> playerScores = ScoreManager.getScores();
      Assert.assertEquals(playerScores.size(), i + 1);
      Assert.assertEquals(playerScores.get(0).getScore(), i);
    }
    name = "newHighScore";
    int score = 50;
    ScoreManager.addScore(name, 50);
    List<ScoreManager.PlayerScore> playerScores = ScoreManager.getScores();
    Assert.assertEquals(10, playerScores.size());
    ScoreManager.PlayerScore playerScore = playerScores.get(0);
    Assert.assertEquals(playerScore.getName(), name);
    Assert.assertEquals(playerScore.getScore(), score);
    playerScore = playerScores.get(9);
    Assert.assertEquals(playerScore.getName(), "test1");
    Assert.assertEquals(playerScore.getScore(), 1);
  }


}
