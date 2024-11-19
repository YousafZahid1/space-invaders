package ships;

import java.util.Random;

import interfaces.Animatable;

/**
 * EnemyShip
 */
public class EnemyShip extends BaseShip implements Animatable {
  private int health;
  private boolean boss = false;

  private EnemyShip(int x0, int y0, int dy, String icon, int health)
  {
    // Same as superclass but with dx=0
    super(x0, y0, 0, dy, icon);
    this.health = health;
  }

  private EnemyShip(int x0, int y0, String icon, int health)
  {
    this(x0, y0, 1, icon, health);
  }

  private static String chooseIcon()
  {
    String icon = "";
    int[][] imageMap = new int[3][2];
    for (int i=0; i<3; i++)
    {
      for (int j=0; j<2; j++){
        imageMap[i][j] = i;
      }
    }
    // new Random().nextInt(3 - 2 + 1) + 1
    int temp = new Random().nextInt(3)+1;
    for (int[] arr: imageMap){
      if (arr[0] == temp)
      {
        switch (temp) {
          case 1:
            icon = "blue-enemy.png";
            break;

          case 2:
            icon = "purple-enemy.png";
            break;

          case 3:
            icon = "dark-blue-enemy.png";
            break;

          default:
            System.out.println("Check you random function, invalid output");
            break;
        }
      }
    }
        return icon;
  }

  public static EnemyShip create(int x0, int y0, int health)
  {
    return new EnemyShip(x0, y0, chooseIcon(), health);
  }

  public static EnemyShip create(int x0, int y0, int dy, int health)
  {
    return new EnemyShip(x0, y0, dy, chooseIcon(), health);
  }

  // getter
  public boolean isBoss()
  {
    return boss;
  }

  // setter
  public EnemyShip isBoss(boolean isBoss)
  {
    boss = isBoss;
    return this;
  }

  public int getHealth()
  {
    return health;
  }

  public void setHealth(int health)
  {
    this.health = health;
  }

  public boolean isHit(Bullet b)
  {
    int x1 = getX();
    int y1 = getY();
    int w1 = this.getIcon().getIconWidth();
    int h1 = this.getIcon().getIconHeight();
    int x2 = b.getX();
    int y2 = b.getY();
    int w2 = b.getWidth();
    int h2 = b.getHeight();
    
    boolean hit = ( x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2 );
    
    // If there's a hit, decrease the health of the ship
    if (hit) {
      this.health -= b.getDamage();
    }

    return hit;
  }

  public void step()
  {
    setY(getY()+getDY());
  }
  

}
