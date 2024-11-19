package ships;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import interfaces.Animatable;

/**
 * Bullet
 */
public class Bullet implements Animatable {
  private final int x;
  private int y;
  private int dy;
  private static final int width = 10;
  private static final int height = 20;

  private boolean piercing;
  private int damage = 1;

  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }

  public int getDY()
  {
    return dy;
  }

  public boolean isPiercing()
  {
    return piercing;
  }

  public int getDamage()
  {
    return damage;
  }

  public int getWidth()
  {
    return width;
  }

  public int getHeight()
  {
    return height;
  }

  private Bullet(BulletBuilder b)
  {
    this.x = b.x;
    this.y = b.y;
    this.dy = b.dy;
    this.piercing = b.piercing;
    this.damage = b.damage;
  }

  /**
   * BulletBuilder
   */
  public static class BulletBuilder {
    private final int x;
    private final int y;
    private final int dy;
    
    private boolean piercing = false;
    private int damage = 1;

    public BulletBuilder(int x, int y0, int dy)
    {
      this.x = x;
      this.y = y0;
      this.dy = dy;
    }

    public void isPiercing(boolean isPiercing)
    {
      this.piercing = isPiercing;
    }

    public void doesDamage(int damage)
    {
      this.damage = damage;
    }

    public Bullet build()
    {
      return new Bullet(this);
    }
  }

  public void draw(Graphics g)
  {
    g.setColor(Color.WHITE);
    g.fillRect(x, y, width, height);
  }

  public void step()
  {
    // go up, so minus
    y-=dy;
  }

  public void atKeyPress(KeyEvent e) {}
  public void atKeyRelease(KeyEvent e) {}
}
