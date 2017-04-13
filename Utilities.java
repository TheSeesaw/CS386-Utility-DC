import java.util.Random;
import java.io.*;

public class Utilities
{
   // This function generates a random number between 0 and the max value parameter -1
   public static int randGenN(int max)
   {
      // New Random object
      Random rand = new Random();
      // Generate a number between 0 and max
      int r_value = rand.nextInt(max);
      return r_value;
   }

   // NOTE: Only saves character objects for now
   // NOTE: Only takes in NPC objects (needs a conditional to check for object type)
   public static int saveCharacterObject(NonPlayerCharacter c_ob, String type) throws IOException
   {

      // Check if there is already an object by this name
      // DEVELOPMENT: for now, assume that no file exists
      File target_file = new File(c_ob.getName() + ".txt");
      target_file.createNewFile();
      FileWriter file_writer = null;
      // Switch for character, ability, and map objects
      String type_switch = type;
      switch(type_switch)
      {
         case "character":
            // Open a file to save the object to based on its name
            file_writer = new FileWriter(target_file);
            // Write character data to file
            file_writer.write(c_ob.getName() + "\n");
            file_writer.write(c_ob.getDesc() + "\n");
            file_writer.write(c_ob.getAlign() + " ");
            file_writer.write(c_ob.getRace() + " ");
            file_writer.write(c_ob.getJob() + "\n");
            file_writer.close();
            break;
      }

      return 1;
   }
}
