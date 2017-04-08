public interface CharacterObject
{
   public String name;
   public String description;
   private boolean is_alive;
   public Ability[] abilities;
   private int current_hp;
   public static int total_hp;

   // Toggles whether a character object is alive or not
   public void toggleAlive();

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
   public void useAttack(int[] selected_attack, CharacterObject attack_target);
   public void useAbility();
}
