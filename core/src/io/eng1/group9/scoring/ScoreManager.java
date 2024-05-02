package io.eng1.group9.scoring;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoreManager {
  private static final String PREFERENCES_KEY = "high_scores";
  private final Preferences prefs;

  public ScoreManager() {
    prefs = Gdx.app.getPreferences(PREFERENCES_KEY);
  }

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

  private void saveScores(List<PlayerScore> scores) {
    // Convert scores to a single string
    StringBuilder sb = new StringBuilder();
    for (PlayerScore score : scores) {
      sb.append(score.name).append(":").append(score.score).append(";");
    }
    prefs.putString("scoreList", sb.toString());
    prefs.flush();
  }

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

  public void printScores() {
    List<PlayerScore> scores = getScores();
    System.out.println("Top Scores:");
    for (PlayerScore score : scores) {
      System.out.println(score.name + ": " + score.score);
    }
  }


  static class PlayerScore {
    String name;
    int score;

    PlayerScore(String name, int score) {
      this.name = name;
      this.score = score;
    }
  }
}
