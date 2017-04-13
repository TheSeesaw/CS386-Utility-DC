

abstract class CharacterObject
{
   private String name = "";
   private String description = "";
   private String alignment = "N";
   private boolean is_alive = true;
   private int[] attributes = new int[6];
   private int[] saves = new int[3];
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
   void setAttributes(int[] new_attributes)
   {
      this.attributes = new_attributes;
   }
   void setSaves(int[] new_saves)
   {
      this.saves = new_saves;
   }
   //TODO: function that sets one attribute
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
   int[] getAttributes()
   {
      return this.attributes;
   }
   int[] getSaves()
   {
      return this.saves;
   }
   int getABModifier(String attribute_name)
   {
      int ab_score = 0;
      switch(attribute_name)
      {
         case "str":
            ab_score = this.getAttributes()[0];
            break;
         case "dex":
            ab_score = this.getAttributes()[1];
            break;
         case "con":
            ab_score = this.getAttributes()[2];
            break;
         case "int":
            ab_score = this.getAttributes()[3];
            break;
         case "wis":
            ab_score = this.getAttributes()[4];
            break;
         case "cha":
            ab_score = this.getAttributes()[5];
            break;
      }
      // Calculate the modifier from the score
      float modifier = 0;
      /* modifier algorithm is:
      *  (score - 10) / 2, round down if positive
      *  (score - 10) / 2, round up if negative
      *  both cases round toward 0, so the floor function works for both
      */
      modifier = ab_score / 10;
      if (modifier == 0 || modifier == 1)
      {
         // Score is either 10 or 11, modifier = 0
         modifier = 0;
      }
      else
      {
         // Score was 12 or greater, or 9 or less
         modifier = (int)Math.floor(modifier / 2);
      }
      return (int)modifier;
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
