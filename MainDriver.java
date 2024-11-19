import java.awt.Dimension;
import javax.swing.JFrame;

public class MainDriver {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("ArcadeGame");
        frame.setLocation(20, 20);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final int WIDTH = 900;
        final int HEIGHT = 900;
        ArcadePanel panel = new ArcadePanel(WIDTH, HEIGHT);
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
