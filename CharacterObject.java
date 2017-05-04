

abstract class CharacterObject
{
   private String name = "";
   private String description = "";
   private String alignment = "N";
   private boolean is_alive = true;
   private String creature_size = "Medium";
   private int[] attributes = new int[6];
   private int[] saves = new int[3];
   //public Ability[] abilities = null;
   // Hit die defaults to lowest die, a d6
   private int hit_die_size = 6;
   private static int current_hp = 1;
   private int total_hp = 1;
   private int init_base = 0;
   private int speed = 30;
   // AC values are held as integer values: [base AC, flat-footed, touch]
   private int[] ac_values = new int[3];
   private int bab = 0;
   // NOTE: only two attack signatures are supported
   private int[][] attacks = new int[2][8];

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
   void setHDSize(int new_hd)
   {
      this.hit_die_size = new_hd;
   }
   void setInitiativeBase(int init_bonus)
   {
      this.init_base = init_bonus;
   }
   void setSpeed(int new_speed)
   {
      this.speed = new_speed;
   }
   void setAC(int[] new_ac)
   {
      this.ac_values = new_ac;
   }
   void setCreatureSize(String new_size)
   {
      if (new_size.equals("large"))
      {
         this.creature_size = "large";
      }
      else if (new_size.equals("small"))
      {
         this.creature_size = "small";
      }
      else // Set to default, medium
      {
         this.creature_size = "medium";
      }
   }
   void setBAB(int new_bab)
   {
      this.bab = new_bab;
   }
   //TODO: function that sets one attribute
   //TODO: function that sets one save
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
      modifier = ab_score - 10;
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
   int getHDSize()
   {
      return this.hit_die_size;
   }
   int[] getAC()
   {
      return this.ac_values;
   }
   String getCreatureSize()
   {
      return this.creature_size;
   }
   int getBAB()
   {
      return this.bab;
   }
   int getInitiative()
   {
      return this.init_base + this.getABModifier("dex");
   }
   int getSpeed()
   {
      return this.speed;
   }
   int getCMB()
   {
      int size_mod = 0;
      if (this.creature_size.equals("small"))
      {
         size_mod = -1;
      }
      else if (this.creature_size.equals("large"))
      {
         size_mod = 1;
      }
      return this.getABModifier("str") + size_mod + this.bab;
   }
   int getCMD()
   {
      int size_mod = 0;
      if (this.creature_size.equals("small"))
      {
         size_mod = -1;
      }
      else if (this.creature_size.equals("large"))
      {
         size_mod = 1;
      }
      return 10 + this.getABModifier("dex") + this.getABModifier("str") + size_mod + this.bab;
   }
   void initializeAttackSignature(int attack_index)
   {
      int[] attack_sig = new int[8];
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
      // NOTE: assume a natural melee attack
      int size_bonus = 0;
      int damage_die_size = 3;
      if (this.creature_size.equals("small"))
      {
         size_bonus = 1;
         damage_die_size = 2;
      }
      else if (this.creature_size.equals("large"))
      {
         size_bonus = -1;
         damage_die_size = 4;
      }
      attack_sig[0] = size_bonus + this.getBAB() + this.getABModifier("str");
      attack_sig[1] = 20;
      attack_sig[2] = 2;
      attack_sig[3] = 0;
      attack_sig[4] = 5;
      attack_sig[5] = 1;
      attack_sig[6] = damage_die_size;
      attack_sig[7] = this.getABModifier("str");
      this.attacks[attack_index] = attack_sig;
   }
   int[] getAttackSignature(int attack_index)
   {
      return this.attacks[attack_index];
   }
   // Toggles whether a character object is alive or not
   abstract void toggleAlive();


   // The attack target receives the attack and takes damage if it hits
   abstract void useAttack(int[] selected_attack, CharacterObject attack_target);
   abstract void useAbility();
}
