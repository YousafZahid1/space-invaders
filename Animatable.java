package interfaces;

import java.awt.event.KeyEvent;

public interface Animatable extends Drawable {
  public void step();
  public void atKeyPress(KeyEvent e);
  public void atKeyRelease(KeyEvent e);
  public int getX();
  public int getY();
  
}
