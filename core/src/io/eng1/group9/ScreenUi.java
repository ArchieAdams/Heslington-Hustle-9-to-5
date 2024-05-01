package io.eng1.group9;

/**
 * Base class for updating the UI.
 */
public abstract class ScreenUi {

  /**
   * Update the UI.
   */
  public abstract void update();

  /**
   * Resize.
   *
   * @param width  the width
   * @param height the height
   */
  public abstract void resize(int width, int height);
}
