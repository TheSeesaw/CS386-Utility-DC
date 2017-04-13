abstract class CharacterObject
{
   private String name = "";
   private String description = "";
   private String alignment = "N";
   private boolean is_alive = true;
   //public Ability[] abilities = null;
   private int current_hp = 1;
   private static int total_hp = 1;

   // Setters
   void setName(String a_name)
   {
      this.name = a_name;
   }
   void setDesc(String a_desc)
   {
      this.description = a_desc;
   }
   void setAlign(String an_align)
   {
      this.alignment = an_align;
   }
   // Getters
   String getName()
   {
      return this.name;
   }
   String getDesc()
   {
      return this.description;
   }
   String getAlign()
   {
      return this.alignment;
   }
   // Toggles whether a character object is alive or not
   abstract void toggleAlive();

   /* attacks are int arrays that hold eight values:
      0: attack bonus
      1: critical lowest value
      2: critical multiplier
      3: damage type 0 = blunt, 1 = pierce, 2 = slash
      4: range (in feet)
      5: number of damage die
      6: damage die type
      7: damage bonus
   */
   // The attack target receives the attack and takes damage if it hits
   abstract void useAttack(int[] selected_attack, CharacterObject attack_target);
   abstract void useAbility();
}
