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

   // NOTE: Only saves npc character objects for now
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
         case "npc":
            // Open a file to save the object to based on its name
            file_writer = new FileWriter(target_file);
            // Write character data to file
            // Name
            file_writer.write(c_ob.getName() + "\n");
            // Description
            file_writer.write(c_ob.getDesc() + "\n");
            // Alignment
            file_writer.write(c_ob.getAlign() + " ");
            // Race
            file_writer.write(c_ob.getRace() + " ");
            // Class
            file_writer.write(c_ob.getJob() + "\n");
            // Hit dice and hitpoints
            file_writer.close();
            break;
         case "pc":
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
   // TODO: Make this handle other types of character objects
   public static void calculateSavingThrows(NonPlayerCharacter npc_ob)
   {
      // Store modifiers
      int con_mod = npc_ob.getABModifier("con");
      int dex_mod = npc_ob.getABModifier("dex");
      int wis_mod = npc_ob.getABModifier("wis");

      // Get expressions based on class
      int[][] base_saves = {{0,0,1}, {1,0,0}, {0,0,0}};
      int[] new_saves;
      // Get the bonus based on class
      // Some classes have the same bonuses
      if (npc_ob.getJob() == "Adept" || npc_ob.getJob() == "Aristocrat" || npc_ob.getJob() == "Expert")
      {
         new_saves = calculateNPCClassBaseSave(base_saves[0], npc_ob.getLevel());
      }
      else if (npc_ob.getJob() == "Warrior")
      {
         new_saves = calculateNPCClassBaseSave(base_saves[1], npc_ob.getLevel());
      }
      else // Commoner
      {
         new_saves = calculateNPCClassBaseSave(base_saves[2], npc_ob.getLevel());
      }
      // Apply the class bonuses
      new_saves[0] += con_mod;
      new_saves[1] += dex_mod;
      new_saves[2] += wis_mod;
      // Set the character object's new saves
      npc_ob.setSaves(new_saves);
   }

   // Calculate base save, which is added to the ability modifier
   public static int[] calculateNPCClassBaseSave(int[] saves_key, int level)
   {
      int[] class_saves = {0, 0, 0};
      for (int count = 0; count < 3; count++)
      {
         // Check if the save is good
         if (saves_key[count] == 1)
         {
            // Calculate save based on level
            class_saves[count] = 2 + (level / 2);
         }
         else // Else, save is poor
         {
            class_saves[count] = level / 3;
         }
      }
      // Return the class save bonuses
      return class_saves;
   }

   public static int getNPCHitDie(NonPlayerCharacter npc_ob)
   {
      if (npc_ob.getJob() == "Adept" || npc_ob.getJob() == "Commoner")
      {
         return 6;
      }
      else if (npc_ob.getJob() == "Aristocrat" || npc_ob.getJob() == "Expert")
      {
         return 8;
      }
      else // Warrior
      {
         return 10;
      }
   }
   // NOTE: Should also be able to take in PlayerCharacter objects, but not creature objects
   public static void applyRacialBonuses(NonPlayerCharacter npc_ob)
   {
      //TODO: Implement score choice for Half-elf, Half-orc, and Human
      int[] character_ab = npc_ob.getAttributes();
      if (npc_ob.getRace() == "Dwarf")
      {
         // Add 2 to constitution and wisdom,
         // Subract 2 from charisma
         character_ab[2] += 2;
         character_ab[4] += 2;
         character_ab[5] -= 2;
      }
      else if (npc_ob.getRace() == "Elf")
      {
         // Add 2 to dexteriy and intelligence
         // Subtract 2 from constitution
         character_ab[1] += 2;
         character_ab[3] += 2;
         character_ab[2] -= 2;
      }
      else if (npc_ob.getRace() == "Gnome")
      {
         // Add 2 to constitution and charisma
         // Subract 2 from strength
         character_ab[2] += 2;
         character_ab[5] += 2;
         character_ab[0] -= 2;
      }
      else if (npc_ob.getRace() == "Halfling")
      {
         // Add 2 to dexteriy and charisma
         // Subract 2 from strength
         character_ab[1] += 2;
         character_ab[5] += 2;
         character_ab[0] -= 2;
      }
      else // Half-elf, Half-orc, or Human
      {
         // Pick a random score and add 2 to it
         int target_score = randGenN(6);
         character_ab[target_score] += 2;
      }
      // Set the new attributes
      npc_ob.setAttributes(character_ab);
   }
}
