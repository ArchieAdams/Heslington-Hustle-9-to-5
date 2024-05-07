package io.eng1.group9.scoring;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import io.eng1.group9.gamestate.Day;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for managing and saving high scores.
 *
 */
public class ScoreManager {

  /**
   * The key for the preferences file containing the high scores.
   */
  private static final String PREFERENCES_KEY = "high_scores";

  /**
   * The preferences object used to save and load high scores.
   */
  private static final Preferences preferences = Gdx.app.getPreferences(PREFERENCES_KEY);

  /**
   * Calculate the score.
   *
   * @return The score calculated.
   */
  public static int calculateScore(List<Day> week) {
    int studyCount = 0;
    int dayStudiedOnce = 0;
    int dayRelaxedOnce = 0;
    int dayEatenCount = 0;
    int maxScore = 100;
    int score;
    for (Day day : week) {
      studyCount += day.getNumberOfActivity("Study");
      if (day.getNumberOfActivity("Study") >= 1) {
        dayStudiedOnce++;
      }
      if (day.getNumberOfActivity("Eat") >= 2) {
        dayEatenCount++;
      }
      if (day.getNumberOfActivity("Recreation") > 0) {
        dayRelaxedOnce++;
      }
    }

    System.out.println(studyCount);
    score = studyCount * 10;
    score = Math.min(score, maxScore);

    // Apply penalties
    if (dayStudiedOnce != 7) {
      score = dayStudiedOnce * 10;
      score = Math.min(score, 50);
    }

    if (dayStudiedOnce == 6 && studyCount >= 7){
      score = 70;
    }

    if (dayEatenCount < 7) {
      score -= 10; // Penalty for not eating enough
    }

    if (dayRelaxedOnce < 7) {
      score -= 10; // Penalty for not relaxing enough
    }

    // Cap the score at maxScore
    score = Math.max(score, 0);
    return score;
  }


  /**
   * Saves a new score along with the player's name.
   * If the list of scores already contains 10 scores,
   * the lowest score will be removed to make room for the new score.
   *
   * @param name  The name of the player who achieved the score.
   * @param score The score achieved by the player.
   */
  public static void addScore(String name, int score) {
    List<PlayerScore> scores = getScores();
    scores.add(new PlayerScore(name, score));
    // Sort scores descending
    scores.sort((p1, p2) -> Integer.compare(p2.score, p1.score));
    // Keep only the top 10 scores
    if (scores.size() > 10) {
      scores = new ArrayList<>(scores.subList(0, 10));
    }
    // Save updated scores
    saveScores(scores);
  }

  /**
   * Saves the list of scores to the preferences file.
   *
   * @param scores The list of scores to be saved.
   */
  private static void saveScores(List<PlayerScore> scores) {
    // Convert scores to a single string
    StringBuilder sb = new StringBuilder();
    for (PlayerScore score : scores) {
      sb.append(score.name).append(":").append(score.score).append(";");
    }
    preferences.putString("scoreList", sb.toString());
    preferences.flush();
  }

  /**
   * Retrieves the list of high scores from the preferences file.
   *
   * @return The list of high scores.
   */
  public static List<ScoreManager.PlayerScore> getScores() {
    List<ScoreManager.PlayerScore> scores = new ArrayList<>();
    String scoresStr = preferences.getString("scoreList", "");
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
  public static void printScores() {
    List<PlayerScore> scores = getScores();
    System.out.println("Top Scores:");
    for (PlayerScore score : scores) {
      System.out.println(score.name + ": " + score.score);
    }
  }

  /**
   * Clears all saved scores from the preferences file.
   */
  public static void resetScores() {
    preferences.putString("scoreList", ""); // Clear the score string
    preferences.flush(); // Make sure the changes are saved to the file
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
