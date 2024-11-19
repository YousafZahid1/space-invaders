package levels;

public class Level {
  private int speed;
  private int health;
  private int levelNumber;

  // optional
  private boolean hasBoss;
  private int bossHealth;

  public int getSpeed()
  {
    return speed;
  }

  public int getHealth()
  {
    return health;
  }

  public int getLevelNumber()
  {
    return levelNumber;
  }

  public boolean hasBoss()
  {
    return hasBoss;
  }

  public int getBossHealth()
  {
    return bossHealth;
  }

  private Level(LevelConstructor builder)
  {
    this.levelNumber = builder.levelNumber;
    this.speed = builder.speed;
    this.health = health;
    this.hasBoss = builder.hasBoss;
    this.bossHealth = builder.bossHealth;
  }

  public static class LevelConstructor {
    private int speed;
    private int levelNumber;
    private int health;

    // optional
    private boolean hasBoss = false;
    private int bossHealth = 0;

    public LevelConstructor(int levelNumber, int speed, int health)
    {
      this.levelNumber = levelNumber;
      this.speed = speed;
      this.health = health;
    }

    public void addBoss(int health)
    {
      this.hasBoss = true;
      this.bossHealth = health;
    }

    public Level build()
    {
      return new Level(this);
    }
  }
}
