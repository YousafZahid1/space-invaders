package ships;

import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import interfaces.Drawable;

public class BaseShip implements Drawable {
  private static final String iconDir = "../icons/";
  private int x;
  private int y;
  private int dx;
  private int dy;
  private ImageIcon icon;


  public BaseShip(int x0, int y0, int dx, int dy, String iconPath)
  {
    icon = new ImageIcon(iconDir+iconPath);
    x = x0;
    y = y0;
    this.dx = dx;
    this.dy = dy;
  }

  public BaseShip(int x0, int y0, String iconPath)
  {
    this(x0, y0, 1, 1, iconPath);
  }

  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }

  public ImageIcon getIcon()
  {
    return icon;
  }

  public int getDX()
  {
    return dx;
  }

  public int getDY()
  {
    return dy;
  }

  public void setX(int x)
  {
    this.x = x;
  }

  public void setY(int y)
  {
    this.y = y;
  }

  public void setIcon(String iconPath)
  {
    icon = new ImageIcon(iconDir+iconPath);
  }

  public void setIcon(ImageIcon newIcon)
  {
    icon = newIcon;
  }

  public void setDX(int dx)
  {
    this.dx = dx;
  }

  public void setDY(int dy)
  {
    this.dy = dy;
  }

  public void draw(Graphics g)
  {
    g.drawImage(icon.getImage(), x, y, icon.getIconWidth(), icon.getIconHeight(), null);
  }

  public String toString()
  {
    return String.format("%s at (%d+%d, %d+%d) using icon %s", this.getClass().getName(), x, dx, y, dy, icon.toString());
  }

  public void atKeyPress(KeyEvent e)
  {
    // do nothing
    ;
  }

  public void atKeyRelease(KeyEvent e)
  {
    // do nothing
    ;
  }
}
