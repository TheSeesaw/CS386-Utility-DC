public class PlayerCharacter extends CharacterObject
{
   private String race;
   private String job;
   private int level;

   // Constructor
   public PlayerCharacter()
   {
      // Level defaults to 1
      this.level = 1;
   }

   // Getters
   public String getRace()
   {
      if (this.race != null)
      {
         return this.race;
      }
      else
      {
         return "none";
      }
   }
   public String getJob()
   {
      if (this.race != null)
      {
         return this.job;
      }
      else
      {
         return "none";
      }
   }
   public int getLevel()
   {
      return this.level;
   }
   // Setters
   public void setRace(String new_race)
   {
      this.race = new_race;
   }
   public void setJob(String new_job)
   {
      this.job = new_job;
   }
   // Combat methods
   public void toggleAlive()
   {

   }
   public void useAttack(int[] selected_attack, CharacterObject attack_target)
   {

   }
   public void useAbility(){

   }
}
