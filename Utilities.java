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

   public static int rollDie(int sides)
   {
      // New Random object
      Random dice = new Random();
      // Add one since numbers start at 1
      int d_value = dice.nextInt(sides) + 1;
      return d_value;
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
      // Switch for pc, npc, and creature character objects
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

   // Generate Ability scores
   public static void generateAttributes(CharacterObject c_ob, String pattern)
   {
      // Initialize new attributes
      int[] new_attributes = new int[6];
      switch(pattern)
      {
         // Generate attributes as per the 3d6 rule
         case "classic":
            for (int count = 0; count < 6; count++)
            {
               new_attributes[count] = (rollDie(6) + rollDie(6) + rollDie(6));
            }
            break;
      }
      // Save the new attributes
      c_ob.setAttributes(new_attributes);
   }
   // Calculate saving throws
   public static void calculateSavingThrows(CharacterObject c_ob)
   {
      // Store modifiers
      int con_mod = c_ob.getABModifier("con");
      int dex_mod = c_ob.getABModifier("dex");
      int wis_mod = c_ob.getABModifier("wis");

   }

   // Gets saving throw values
   
}
