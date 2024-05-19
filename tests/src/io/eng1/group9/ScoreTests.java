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


/**
 * The type Score tests.
 */
@RunWith(GdxTestRunner.class)
public class ScoreTests {

  /**
   * The Study.
   */
  Activity study = new Study(0, 0, "");
  /**
   * The Eat.
   */
  Activity eat = new Eat(0, 0, "");
  /**
   * The Recreation.
   */
  Activity recreation = new Recreation(0, 0, "");

  private Day createDayWithoutStudying() {
    Day day = new Day();
    day.addActivity(eat);
    day.addActivity(eat);
    day.addActivity(recreation);
    return day;
  }

  private Day createDayWithOneStudySession() {
    Day day = createDayWithoutStudying();
    day.addActivity(study);
    return day;
  }

  private Day createDayWithTwoStudySessions() {
    Day day = createDayWithOneStudySession();
    day.addActivity(study);
    return day;
  }

  /**
   * Test full marks.
   */
  @Test
  public void testFullMarks() {
    Day day = createDayWithOneStudySession();

    List<Day> week = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      week.add(day);
    }
    day.addActivity(study);
    for (int i = 0; i < 3; i++) {
      week.add(day);
    }

    Assert.assertEquals("The score should be 100", 100, ScoreManager.calculateScore(week));
  }

  /**
   * Test seventy marks.
   */
  @Test
  public void testSeventyMarks() {
    Day day = createDayWithOneStudySession();
    List<Day> week = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      week.add(day);
    }


    Assert.assertEquals("The score should be 60", 60, ScoreManager.calculateScore(week));
  }

  /**
   * Test day missed and caught up.
   */
  @Test
  public void testDayMissedAndCaughtUp() {
    Day day = createDayWithoutStudying();
    List<Day> week = new ArrayList<>();
    week.add(day);
    day = createDayWithOneStudySession();
    for (int i = 0; i < 5; i++) {
      week.add(day);
    }
    day = createDayWithTwoStudySessions();
    week.add(day);

    Assert.assertEquals("The score should be 60", 60, ScoreManager.calculateScore(week));
  }

  /**
   * Test day missed.
   */
  @Test
  public void testDayMissed() {
    Day day = createDayWithoutStudying();
    List<Day> week = new ArrayList<>();
    week.add(day);
    day = createDayWithOneStudySession();
    for (int i = 0; i < 6; i++) {
      week.add(day);
    }

    Assert.assertEquals("The score should be 40", 40, ScoreManager.calculateScore(week));
  }

  /**
   * Test day without eating.
   */
  @Test
  public void testDayWithoutEating() {
    Day day = new Day();
    day.addActivity(study);
    day.addActivity(recreation);
    List<Day> week = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      week.add(day);
    }

    Assert.assertEquals("The score should be 50", 50, ScoreManager.calculateScore(week));
  }

  /**
   * Test day without recreation.
   */
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

    Assert.assertEquals("The score should be 60", 60, ScoreManager.calculateScore(week));
  }

  /**
   * Test day without recreation and eating.
   */
  @Test
  public void testDayWithoutRecreationAndEating() {
    Day day = new Day();
    day.addActivity(study);
    List<Day> week = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      week.add(day);
    }

    Assert.assertEquals("The score should be 50", 50, ScoreManager.calculateScore(week));
  }

  /**
   * Test zero marks.
   */
  @Test
  public void testZeroMarks() {
    Day day = new Day();
    List<Day> week = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      week.add(day);
    }

    Assert.assertEquals("The score should be 0", 0, ScoreManager.calculateScore(week));
  }

  /**
   * Test bonus marks study.
   */
  @Test
  public void testBonusMarksStudy() {
    Day day = createDayWithOneStudySession();
    List<Day> week = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      week.add(day);
    }
    day = createDayWithTwoStudySessions();
    for (int i = 0; i < 5; i++) {
      week.add(day);
    }

    Assert.assertEquals("The score should be 100", 100, ScoreManager.calculateScore(week));
  }

  /**
   * Test eat three times a day bonus.
   */
  @Test
  public void testEatThreeTimesADayBonus() {
    Day day = createDayWithOneStudySession();
    day.addActivity(eat);
    List<Day> week = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      week.add(day);
    }

    Assert.assertEquals("The score should be 70", 70, ScoreManager.calculateScore(week));
  }

  /**
   * Test week with over ten recreations bonus.
   */
  @Test
  public void testWeekWithOverTenRecreationsBonus() {
    Day day = createDayWithOneStudySession();
    day.addActivity(recreation);
    List<Day> week = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      week.add(day);
    }

    Assert.assertEquals("The score should be 70", 70, ScoreManager.calculateScore(week));
  }
}
