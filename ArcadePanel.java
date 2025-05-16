
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.ArrayList;

import ships.Ship;
import ships.EnemyShip;
import ships.Bullet;
import levels.Level;
import levels.Level.LevelConstructor;
import interfaces.Animatable;

public class ArcadePanel extends JPanel implements ActionListener
{
    private final int width;
    private final int height;
    private static final Color BACKGROUND = Color.BLACK;

    private boolean paused = false;
    private int score = 0;

    private JLabel scoreLabel;

    private BufferedImage screen;  
    private Graphics buffer;

    private Timer t;
    private Timer nextWaveT;
    private Timer nextLevelT;

    private ArrayList<Animatable> animationObjects;

    private Ship starship;
    private Level level;

    public ArcadePanel(int panelWidth, int panelHeight)
    {
        width = panelWidth;
        height = panelHeight;
        screen =  new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
        buffer = screen.getGraphics();
        buffer.setColor(BACKGROUND);
        buffer.fillRect(0,0,width,height);

        animationObjects = new ArrayList<Animatable>();

        starship = new Ship(50, 0, 6, "space-invaders.png", width);
        starship.setY(height - starship.getIcon().getIconHeight());
        animationObjects.add(starship);
        // Needs to be above timers
        setLayout(new BorderLayout());
        score = new Score().getScore();

        scoreLabel = new JLabel(""+score);
        scoreLabel.setForeground(Color.RED);
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 40));
        add(scoreLabel, BorderLayout.NORTH);
        t = new Timer(5, this);
        t.start();
        nextWaveT = new Timer(1000, new NewWave());
        nextWaveT.start();
        nextLevelT = new Timer(5000, new NextLevel());
        nextLevelT.start();

        level = new Level.LevelConstructor(1, 1, 1).build();

        addKeyListener(new Key());
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g)  
    {
        super.paintComponent(g);
        g.drawImage(screen, 0, 0, getWidth(), getHeight(), null);
    }

    public void actionPerformed(ActionEvent e)
    {
        // Clear the current state of screen by writing over it with a new blank background
        buffer.setColor(BACKGROUND);
        buffer.fillRect(0,0,width,height);

        ArrayList<Animatable> toRemove = new ArrayList<Animatable>();
        for(Animatable animationObject : animationObjects)
        {
            if (!paused) {
                animationObject.step();
            }
            if (animationObject instanceof Bullet) {
                boolean removeBullet = false;
                for (Animatable enemy : animationObjects) {
                    if (enemy instanceof EnemyShip evil) {
                        evil.isHit((Bullet) animationObject);
                        if (evil.getHealth() < 0)
                        {
                            toRemove.add(enemy);
                            removeBullet = true;
                            score++;
                        }
                    }
                }
                if (removeBullet){
                    toRemove.add(animationObject);
                }
            }
            if (animationObject.getY() < 0)
            {
                toRemove.add(animationObject);
            } else {
                animationObject.draw(buffer);
            }
        }

        animationObjects.removeAll(toRemove);
        scoreLabel.setText(""+score);

        if (paused)
        {
            // Draw the pause icon
            buffer.setColor(Color.WHITE);
            buffer.fillRect(410, 400, 30, 100);
            buffer.fillRect(480, 400, 30, 100);
        }

        repaint();
    }

    private void shootBullet()
    {
        Bullet b = starship.shoot();
        animationObjects.add(b);
    }

    private class NextLevel implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if (paused) {return;}
            Level.LevelConstructor newLevel = new Level.LevelConstructor(level.getLevelNumber()+1, level.getSpeed()+1, level.getHealth()+1);
            if (level.getLevelNumber() % 10 == 9)
            {
                // TODO: add some way to increase health as time goes on
                newLevel.addBoss(40);
            }
            level = newLevel.build();
        }
    }

    private class NewWave implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if (paused) {return;}
            if (level.hasBoss())
            {
                EnemyShip boss = EnemyShip.create(width/2, height/2, 40);
                animationObjects.add(boss);
                return;
            }

            for (int i=0; i < width/70; i++)
            {
                EnemyShip ship = EnemyShip.create(70*i, 0, level.getHealth());
                animationObjects.add(ship);
            }

            Score.writeScore(score);
        }
    }

    private class Key extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_F1:
                    System.exit(0);
                    break;

                case KeyEvent.VK_ESCAPE:
                    paused = !paused;
                    break;

                case KeyEvent.VK_SPACE:
                    shootBullet();
                    break;

                default:
                    if (paused){ return; }
                    for (Animatable obj: animationObjects) {
                        obj.atKeyPress(e);
                    }
                    break;
            }
        
        }

        public void keyReleased(KeyEvent e)
        {
            for (Animatable obj: animationObjects) {
                obj.atKeyRelease(e);
            }
        }
    }
}
