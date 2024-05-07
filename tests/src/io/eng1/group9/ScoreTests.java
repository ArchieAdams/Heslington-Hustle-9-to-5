package io.eng1.group9;

import io.eng1.group9.gamestate.Day;
import io.eng1.group9.gamestate.activities.Activity;
import io.eng1.group9.gamestate.activities.Eat;
import io.eng1.group9.gamestate.activities.Recreation;
import io.eng1.group9.gamestate.activities.Study;
import io.eng1.group9.scoring.ScoreManager;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(GdxTestRunner.class)
public class ScoreTests {

  Activity study = new Study(0, 0, "");
  Activity eat = new Eat(0, 0, "");
  Activity recreation = new Recreation(0, 0, "");

  private Day createDayWithoutStudy(){
    Day day = new Day();
    day.addActivity(eat);
    day.addActivity(eat);
    day.addActivity(recreation);
    return day;
  }

  private Day createDayWithOneStudy(){
    Day day = createDayWithoutStudy();
    day.addActivity(study);
    return day;
  }

  private Day createDayWithTwoStudy(){
    Day day = createDayWithOneStudy();
    day.addActivity(study);
    return day;
  }

  @Test
  public void testFullMarks() {
    Day day = createDayWithOneStudy();

    List<Day> week = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      week.add(day);
    }
    day.addActivity(study);
    for (int i = 0; i < 3; i++) {
      week.add(day);
    }

    Assert.assertEquals("The score should be 100", 100,
        ScoreManager.calculateScore(week));
  }

  @Test
  public void testSeventyMarks() {
    Day day = createDayWithOneStudy();
    List<Day> week = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      week.add(day);
    }


    Assert.assertEquals("The score should be 70", 70,
        ScoreManager.calculateScore(week));
  }

  @Test
  public void testDayMissedAndCaughtUp() {
    Day day = createDayWithoutStudy();
    List<Day> week = new ArrayList<>();
    week.add(day);
    day = createDayWithOneStudy();
    for (int i = 0; i < 5; i++) {
      week.add(day);
    }
    day = createDayWithTwoStudy();
    week.add(day);

    Assert.assertEquals("The score should be 70", 70,
        ScoreManager.calculateScore(week));
  }

  @Test
  public void testDayMissed() {
    Day day = createDayWithoutStudy();
    List<Day> week = new ArrayList<>();
    week.add(day);
    day = createDayWithOneStudy();
    for (int i = 0; i < 6; i++) {
      week.add(day);
    }

    Assert.assertEquals("The score should be 50", 50,
        ScoreManager.calculateScore(week));
  }

  @Test
  public void testDayWithoutEating() {
    Day day = new Day();
    day.addActivity(study);
    day.addActivity(recreation);
    List<Day> week = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      week.add(day);
    }

    Assert.assertEquals("The score should be 60", 60,
        ScoreManager.calculateScore(week));
  }

  @Test
  public void testDayWithoutRecreation() {
    Day day = new Day();
    day.addActivity(study);
    day.addActivity(eat);
    day.addActivity(eat);
    List<Day> week = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      week.add(day);
    }

    Assert.assertEquals("The score should be 60", 60,
        ScoreManager.calculateScore(week));
  }

  @Test
  public void testDayWithoutRecreationAndEating() {
    Day day = new Day();
    day.addActivity(study);
    List<Day> week = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      week.add(day);
    }

    Assert.assertEquals("The score should be 50", 50,
        ScoreManager.calculateScore(week));
  }

  @Test
  public void testZeroMarks() {
    Day day = new Day();
    List<Day> week = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      week.add(day);
    }

    Assert.assertEquals("The score should be 0", 0,
        ScoreManager.calculateScore(week));
  }
}
