package io.eng1.group9.scoring;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A class for managing and saving high scores.
 *
 * @author Tabnine
 */
public class ScoreManager {

  /**
   * The key for the preferences file containing the high scores.
   */
  private static final String PREFERENCES_KEY = "high_scores";

  /**
   * The preferences object used to save and load high scores.
   */
  private final Preferences prefs;

  /**
   * Constructs a new instance of the ScoreManager class.
   */
  public ScoreManager() {
    this.prefs = Gdx.app.getPreferences(PREFERENCES_KEY);
  }

  /**
   * Saves a new score along with the player's name. If the list of scores already contains 10 scores,
   * the lowest score will be removed to make room for the new score.
   *
   * @param name  The name of the player who achieved the score.
   * @param score The score achieved by the player.
   */
  public void saveScore(String name, int score) {
    List<PlayerScore> scores = getScores();
    scores.add(new PlayerScore(name, score));
    // Sort scores descending
    Collections.sort(scores, new Comparator<PlayerScore>() {
      @Override
      public int compare(PlayerScore p1, PlayerScore p2) {
        return Integer.compare(p2.score, p1.score);
      }
    });
    // Keep only the top 10 scores
    if (scores.size() > 10) {
      scores = scores.subList(0, 10);
    }
    // Save updated scores
    saveScores(scores);
  }

  /**
   * Saves the list of scores to the preferences file.
   *
   * @param scores The list of scores to be saved.
   */
  private void saveScores(List<PlayerScore> scores) {
    // Convert scores to a single string
    StringBuilder sb = new StringBuilder();
    for (PlayerScore score : scores) {
      sb.append(score.name).append(":").append(score.score).append(";");
    }
    prefs.putString("scoreList", sb.toString());
    prefs.flush();
  }

  /**
   * Retrieves the list of high scores from the preferences file.
   *
   * @return The list of high scores.
   */
  public List<PlayerScore> getScores() {
    List<ScoreManager.PlayerScore> scores = new ArrayList<>();
    String scoresStr = prefs.getString("scoreList", "");
    if (!scoresStr.isEmpty()) {
      for (String entry : scoresStr.split(";")) {
        String[] parts = entry.split(":");
        if (parts.length == 2) {
          scores.add(new PlayerScore(parts[0], Integer.parseInt(parts[1])));
        }
      }
    }
    return scores;
  }

  /**
   * Prints the top scores to the console.
   */
  public void printScores() {
    List<PlayerScore> scores = getScores();
    System.out.println("Top Scores:");
    for (PlayerScore score : scores) {
      System.out.println(score.name + ": " + score.score);
    }
  }

  /**
   * A class representing a player's score.
   */
  static class PlayerScore {
    String name;
    int score;

    /**
     * Constructs a new instance of the PlayerScore class.
     *
     * @param name  The name of the player.
     * @param score The score achieved by the player.
     */
    public PlayerScore(String name, int score) {
      this.name = name;
      this.score = score;
    }
  }
}
