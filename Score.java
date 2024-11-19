import java.io.*;
import java.util.Scanner;

public class Score {
  private static final String fpName = "test.data";

  public int getScore()
  {
    try {
      Scanner s = new Scanner(new File(fpName));
      int x =  Integer.parseInt(s.nextLine().strip());
      s.close();
      return x;
    } catch (Exception e)
    {
      System.out.println("Error:");
      e.printStackTrace();
      writeScore(0);
      return 0;
    }
  }

  public static void writeScore(int score)
  {
    try {
      FileWriter f = new FileWriter(fpName);
      f.write(""+score);
      f.close();
    } catch (Exception e)
    {
      System.out.println("Error writing: ");
      e.printStackTrace();
    }
  }
}
