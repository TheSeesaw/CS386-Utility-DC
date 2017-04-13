import java.util.Random;

public class Utilities
{
   // This function generates a random number between 0 and the max value parameter
   // (inclusive)
   public int randGenN(int max)
   {
      // New Random object
      Random rand = new Random();
      // Generate a number between 0 and max
      int r_value = rand.nextInt(max + 1);
      return r_value;
   }
}
