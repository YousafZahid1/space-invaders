package ships;

import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.HashSet;

import interfaces.Animatable;

public class Ship extends BaseShip implements Animatable {
  private int maxX;
  private boolean updateDx = false;
  private Set<Powerups> powerups;

  public Ship(int x0, int y0, int dx, String icon, int maxX)
  {
    // Same as superclass but with dy=0
    super(x0, y0, dx, 0, icon);
    this.maxX = maxX;
    powerups = new HashSet<Powerups>();
  }

  public Ship(int x0, int y0, String icon, int maxX)
  {
    this(x0, y0, 1, icon, maxX);
  }

  public void addPowerup(Powerups powerup)
  {
    powerups.add(powerup);
  }

  public void powerupExpired(Powerups powerup)
  {
    powerups.remove(powerup);
  }

  public Bullet shoot()
  {
    Bullet.BulletBuilder b = new Bullet.BulletBuilder(
      getX()+getIcon().getIconWidth()/2,
      getY(),
      1
    );
    final int baseDamage = 1;
    for (Powerups p: powerups)
    {
      switch (p) {
        case Powerups.PIERCING:
          b.isPiercing(true);
          break;

        case Powerups.DOUBLE_DAMAGE:
          b.doesDamage(2*baseDamage);
          break;

        case Powerups.TRIPLE_DAMAGE:
          b.doesDamage(3*baseDamage);
          break;

        case Powerups.ONE_SHOT_KILL:
          b.doesDamage(100*baseDamage);
          break;
      }
    }
    return b.build();
  }

  public void step()
  {
    if (updateDx)
    {
      setX(getX()+getDX());
    }
    if (getX() < 0)
    {
      setX(0);
    }
    if (getX() > maxX - getIcon().getIconWidth())
    {
      setX(maxX - getIcon().getIconWidth());
    }
  }

  public void atKeyPress(KeyEvent e)
  {
    int dx;
    switch (e.getKeyCode()) {
      case KeyEvent.VK_LEFT:
        dx = getDX();
        if (dx > 0) {
          setDX(-dx);
        }
        updateDx = true;
        break;

      case KeyEvent.VK_RIGHT:
        dx = getDX();
        if (dx < 0) {
          setDX(-dx);
        }
        updateDx = true;
        break;
    }
  }

  public void atKeyRelease(KeyEvent e)
  {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_LEFT:
        updateDx = false;
        break;

      case KeyEvent.VK_RIGHT:
        updateDx = false;
        break;
    }
  }
}
