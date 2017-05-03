import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class COCreationGUI //extends JFrame implements ActionListener, WindowListener, ItemListener
{
  // Look at all these variables
  String[] alignment_array = {"LG", "NG", "CG", "LN", "N", "CN", "LE", "NE", "CE"};
  String[] race_array = {"Dwarf", "Elf", "Gnome", "Half-Elf", "Half-Orc", "Halfling", "Human"};
  String[] npc_job_array = {"Adept", "Aristocrat", "Commoner", "Expert", "Warrior"};

  // Save button
  JButton save = new JButton("Save Changes");
  // Back button
  JButton back = new JButton("Back to Menu");

  // Declare variable for character creation jframe, made global for disposing purposes
  JFrame co_window = new JFrame("Create New Character Object");
  User current_user;

  // Initialize name and description
  JTextField co_name_field = new JTextField("Enter a name here", 40);
  String co_name = "";
  JTextField co_desc_field = new JTextField("Enter a description here", 40);
  String co_desc = "";

  // NPC object
  NonPlayerCharacter test_npc = new NonPlayerCharacter();

  // Details display variables
  JLabel alignment_d = new JLabel(test_npc.getAlign());
  JLabel race_d = new JLabel(test_npc.getRace());
  JLabel job_d = new JLabel(test_npc.getJob());

  // Base stats display variables
  JLabel str_d = new JLabel(Integer.toString(test_npc.getAttributes()[0]));
  JLabel dex_d = new JLabel(Integer.toString(test_npc.getAttributes()[1]));
  JLabel con_d = new JLabel(Integer.toString(test_npc.getAttributes()[2]));
  JLabel int_d = new JLabel(Integer.toString(test_npc.getAttributes()[3]));
  JLabel wis_d = new JLabel(Integer.toString(test_npc.getAttributes()[4]));
  JLabel cha_d = new JLabel(Integer.toString(test_npc.getAttributes()[5]));

  // Saving throw display variables
  JLabel fort_d = new JLabel();
  JLabel ref_d = new JLabel();
  JLabel will_d = new JLabel();

  // Combat stats display variables
  JLabel t_hp_d = new JLabel();
  JLabel b_ac_d = new JLabel();
  JLabel ff_ac_d = new JLabel();
  JLabel t_ac_d = new JLabel();
  JLabel init_d = new JLabel();
  JLabel speed_d = new JLabel();
  // TODO: Add labels for different movement types
  JLabel bab_d = new JLabel();
  JLabel cmb_d = new JLabel();
  JLabel cmd_d = new JLabel();
  // TODO: Support a nondeterministic number of attacks
  JLabel p_att_d = new JLabel();
  JLabel s_att_d = new JLabel();

  // Skills display variables
  JLabel acrobatics_d = new JLabel();
  JLabel appraise_d = new JLabel();
  JLabel bluff_d = new JLabel();
  JLabel climb_d = new JLabel();
  // TODO: Support multiple craft skills
  JLabel craft_d = new JLabel();
  JLabel diplomacy_d = new JLabel();
  JLabel disabledevice_d = new JLabel();
  JLabel disguise_d = new JLabel();
  JLabel escapeartist_d = new JLabel();
  JLabel fly_d = new JLabel();
  JLabel handleanimal_d = new JLabel();
  JLabel heal_d = new JLabel();
  JLabel intimidate_d = new JLabel();
  JLabel arcana_d = new JLabel();
  JLabel dungeoneering_d = new JLabel();
  JLabel engineering_d = new JLabel();
  JLabel geography_d = new JLabel();
  JLabel history_d = new JLabel();
  JLabel local_d = new JLabel();
  JLabel nature_d = new JLabel();
  JLabel nobility_d = new JLabel();
  JLabel planes_d = new JLabel();
  JLabel religion_d = new JLabel();
  JLabel linguistics_d = new JLabel();
  JLabel perception_d = new JLabel();
  // TODO: support multiple perform skills
  JLabel perform_d = new JLabel();
  // TODO: support multiple profession skills
  JLabel profession_d = new JLabel();
  JLabel ride_d = new JLabel();
  JLabel sensemotive_d = new JLabel();
  JLabel sleightofhand_d = new JLabel();
  JLabel spellcraft_d = new JLabel();
  JLabel stealth_d = new JLabel();
  JLabel survival_d = new JLabel();
  JLabel swim_d = new JLabel();
  JLabel usemagicdevice_d = new JLabel();
  // TODO: support custom skills

  // Feats Display Label
  JLabel feats_d = new JLabel();

  // Gear display labels
  JLabel weapon1_d = new JLabel();
  JLabel weapon2_d = new JLabel();
  JLabel armor_d = new JLabel();
  JLabel belt_d = new JLabel();
  JLabel body_d = new JLabel();
  JLabel chest_d = new JLabel();
  JLabel eyes_d = new JLabel();
  JLabel feet_d = new JLabel();
  JLabel hand_d = new JLabel();
  JLabel head_d = new JLabel();
  JLabel headband_d = new JLabel();
  JLabel neck_d = new JLabel();
  JLabel ring1_d = new JLabel();
  JLabel ring2_d = new JLabel();
  JLabel shield_d = new JLabel();
  JLabel shoulder_d = new JLabel();
  JLabel wrist_d = new JLabel();
  // TODO: slotless items

  // Item display label
  JLabel items_d = new JLabel();

  // TODO: abilities and spells

  // Card labels for the layout with description of contents
  // Character fluff details, also includes race and class
  final static String DETAILSPANEL = "Details";
  // Attributes and saves
  final static String BASESTATS = "Base Statistics";
  // Combat, includes HP, AC, attacks, initiative, and movement
  final static String COMBATSTATS = "Combat Statistics";
  // Skills and feats
  final static String SKILLSFEATS = "Skills/Feats";
  // Gear and items
  final static String GEARITEMS = "Gear/Items";
  // Spells and abilities
  final static String SPELLSABILITIES = "Abilities/Spells";

  public void addComponentToPane(Container pane)
  {
    // Declare JTabbedPane that will go inside character creation frame
    JTabbedPane co_tabs = new JTabbedPane();
    // Create a matching panel for card label
    JPanel details = new JPanel();
    details.setLayout(new BoxLayout(details, BoxLayout.PAGE_AXIS));
    JPanel base_stats = new JPanel();
    base_stats.setLayout(new BoxLayout(base_stats, BoxLayout.PAGE_AXIS));
    JPanel combat_stats = new JPanel();
    combat_stats.setLayout(new BoxLayout(combat_stats, BoxLayout.PAGE_AXIS));
    JPanel skills_feats = new JPanel();
    JPanel gear_items = new JPanel();
    JPanel spells_abilities = new JPanel();
    save.addActionListener(new ButtonListener());
    save.setActionCommand("save");
    back.addActionListener(new ButtonListener());
    back.setActionCommand("back");

    // Details panel functionality==============================================

    // Character Object name
    JLabel name = new JLabel("Name: ");
    details.add(name);
    details.add(co_name_field);

    // Character object description
    JLabel desc = new JLabel("Description: ");
    details.add(desc);
    details.add(co_desc_field);

    // Alignment option (randomly generated)
    JLabel alignment_t = new JLabel("Alignment: ");
    details.add(alignment_t);
    details.add(alignment_d);
    JButton gen_align = new JButton("Random");
    gen_align.addActionListener(new ButtonListener());
    gen_align.setActionCommand("align gen");
    details.add(gen_align);

    // Gender options
    JLabel gender = new JLabel("Gender: ");
    details.add(gender);
    JRadioButton option_m = new JRadioButton("Male");
    JRadioButton option_f = new JRadioButton("Female");
    JRadioButton option_na = new JRadioButton("N/A");
    // Group them together
    ButtonGroup gender_group = new ButtonGroup();
    gender_group.add(option_m);
    gender_group.add(option_f);
    gender_group.add(option_na);
    // Add the buttons to the frame
    details.add(option_m);
    details.add(option_f);
    details.add(option_na);

    // Race option (randomly generated)
    JLabel race_t = new JLabel("Race: ");
    details.add(race_t);
    details.add(race_d);
    JButton gen_race = new JButton("Random");
    gen_race.addActionListener(new ButtonListener());
    gen_race.setActionCommand("race gen");
    details.add(gen_race);

    // Class option (randomly generated)
    JLabel job_t = new JLabel("Class: ");
    details.add(job_t);
    details.add(job_d);
    JButton gen_job = new JButton("Random");
    gen_job.addActionListener(new ButtonListener());
    gen_job.setActionCommand("job gen");
    details.add(gen_job);

    // Base stats implementation================================================

    // Labels for displaying base attributes
    JLabel attributes_t = new JLabel("Attributes: \n");
    base_stats.add(attributes_t);
    // Add the labels to the panel
    JLabel str_t = new JLabel("STR:");
    base_stats.add(str_t);
    base_stats.add(str_d);
    JLabel dex_t = new JLabel("DEX:");
    base_stats.add(dex_t);
    base_stats.add(dex_d);
    JLabel con_t = new JLabel("CON:");
    base_stats.add(con_t);
    base_stats.add(con_d);
    JLabel int_t = new JLabel("INT:");
    base_stats.add(int_t);
    base_stats.add(int_d);
    JLabel wis_t = new JLabel("WIS:");
    base_stats.add(wis_t);
    base_stats.add(wis_d);
    JLabel cha_t = new JLabel("CHA:");
    base_stats.add(cha_t);
    base_stats.add(cha_d);
    // Generate random attributes
    //TODO: functionality to choose generation algorithm at run time
    JButton gen_attributes = new JButton("Random");
    gen_attributes.addActionListener(new ButtonListener());
    gen_attributes.setActionCommand("stats gen");
    base_stats.add(gen_attributes);
    // Labels for displaying saving throws
    JLabel saves_t = new JLabel("Saving Throws: \n");
    JLabel fort_t = new JLabel("Fortitude: \n");
    JLabel ref_t = new JLabel("Reflex: \n");
    JLabel will_t = new JLabel("Will: \n");
    // Add them to the pane
    base_stats.add(saves_t);
    base_stats.add(fort_t);
    base_stats.add(fort_d);
    base_stats.add(ref_t);
    base_stats.add(ref_d);
    base_stats.add(will_t);
    base_stats.add(will_d);
    //NOTE: temporary button until listener implementation is fixed
    JButton update_saves = new JButton("Update");
    update_saves.addActionListener(new ButtonListener());
    update_saves.setActionCommand("update saves");
    base_stats.add(update_saves);

    // Combat Stats ============================================================

    // Combat stats title labels
    JLabel t_hp_t = new JLabel("Total HP: ");
    combat_stats.add(t_hp_t);
    combat_stats.add(t_hp_d);
    JButton reroll_hitpoints = new JButton("Reroll Hitpoints");
    reroll_hitpoints.addActionListener(new ButtonListener());
    reroll_hitpoints.setActionCommand("reroll hitpoints");
    combat_stats.add(reroll_hitpoints);
    JLabel b_ac_t = new JLabel("AC: ");
    combat_stats.add(b_ac_t);
    combat_stats.add(b_ac_d);
    JLabel ff_ac_t = new JLabel("Flat Footed AC: ");
    combat_stats.add(ff_ac_t);
    combat_stats.add(ff_ac_d);
    JLabel t_ac_t = new JLabel("Touch AC: ");
    combat_stats.add(t_ac_t);
    combat_stats.add(t_ac_d);
    JLabel init_t = new JLabel("Initiative: ");
    combat_stats.add(init_t);
    combat_stats.add(init_d);
    JLabel speed_t = new JLabel("Speed (feet): ");
    combat_stats.add(speed_t);
    combat_stats.add(speed_d);
    // TODO: Add labels for different movement types
    JLabel bab_t = new JLabel("Base Attack Bonus: ");
    combat_stats.add(speed_t);
    combat_stats.add(speed_d);
    JLabel cmb_t = new JLabel("Combat Maneuver Bonus: ");
    combat_stats.add(cmb_t);
    combat_stats.add(cmb_d);
    JLabel cmd_t = new JLabel("Combat Maneuver Defense: ");
    combat_stats.add(cmd_t);
    combat_stats.add(cmd_d);
    // TODO: Support a nondeterministic number of attacks
    JLabel p_att_t = new JLabel("Primary Attack: ");
    combat_stats.add(p_att_t);
    combat_stats.add(p_att_d);
    JLabel s_att_t = new JLabel("Secondary Attack: ");
    combat_stats.add(s_att_t);
    combat_stats.add(s_att_d);
    JButton update_combat_stats = new JButton("Update");
    update_combat_stats.addActionListener(new ButtonListener());
    update_combat_stats.setActionCommand("update combat stats");
    combat_stats.add(update_combat_stats);

    // Skills and Feats ========================================================

    JLabel acrobatics_t = new JLabel("\nAcrobatics: ");
    JLabel appraise_t = new JLabel("\nAppraise: ");
    JLabel bluff_t = new JLabel("\nBluff: ");
    JLabel climb_t = new JLabel("\nClimb: ");
    // TODO: Support multiple craft skills
    JLabel craft_t = new JLabel("\nCraft: ");
    JLabel diplomacy_t = new JLabel("\nDiplomacy: ");
    JLabel disabledevice_ = new JLabel("\nDisable Device: ");
    JLabel disguise_t = new JLabel("\nDisguise: ");
    JLabel escapeartist_t = new JLabel("\nEscape Artist: ");
    JLabel fly_t = new JLabel("\nFly: ");
    JLabel handleanimal_t = new JLabel("\nHandle Animal: ");
    JLabel heal_t = new JLabel("\nHeal: ");
    JLabel intimidate_t = new JLabel("\nIntimidate: ");
    JLabel arcana_t = new JLabel("\nKnowledge(): ");
    JLabel dungeoneering_t = new JLabel("\nKnowledge(arcana): ");
    JLabel engineering_t = new JLabel("\nKnowledge(engineering): ");
    JLabel geography_t = new JLabel("\nKnowledge(geography): ");
    JLabel history_t = new JLabel("\nKnowledge(history): ");
    JLabel local_t = new JLabel("\nKnowledge(local): ");
    JLabel nature_t = new JLabel("\nKnowledge(nature): ");
    JLabel nobility_t = new JLabel("\nKnowledge(nobility): ");
    JLabel planes_t = new JLabel("\nKnowledge(planes): ");
    JLabel religion_t = new JLabel("\nKnowledge(religion): ");
    JLabel linguistics_t = new JLabel("\nLinguistics: ");
    JLabel perception_t = new JLabel("\nPerception: ");
    // TODO: support multiple perform skills
    JLabel perform_t = new JLabel("\nPerform: ");
    // TODO: support multiple profession skills
    JLabel profession_t = new JLabel("\nProfession: ");
    JLabel ride_t = new JLabel("\nRide: ");
    JLabel sensemotive_t = new JLabel("\nSense Motive: ");
    JLabel sleightofhand_t = new JLabel("\nSleight of Hand: ");
    JLabel spellcraft_t = new JLabel("\nSpellcraft: ");
    JLabel stealth_t = new JLabel("\nStealth: ");
    JLabel survival_t = new JLabel("\nSurvival: ");
    JLabel swim_t = new JLabel("\nSwim: ");
    JLabel usemagicdevice_t = new JLabel("\nUse Magic Device: ");
    // TODO: support custom skills

    // Feats Display Label
    JLabel feats_t = new JLabel("\nFeats: \n");

    // Items and Gear ==========================================================

    // Spells and Abilities ====================================================

    // Add the panel to the pane
    co_tabs.addTab(DETAILSPANEL, details);
    co_tabs.addTab(BASESTATS, base_stats);
    co_tabs.addTab(COMBATSTATS, combat_stats);
    co_tabs.addTab(SKILLSFEATS, skills_feats);
    co_tabs.addTab(GEARITEMS, gear_items);
    co_tabs.addTab(SPELLSABILITIES, spells_abilities);
    // Add the save and back buttons to all the panels
    details.add(save);
    details.add(back);
    base_stats.add(save);
    base_stats.add(back);
    // Add this component to the pane passed into this function
    pane.add(co_tabs, BorderLayout.CENTER);
  }

  public COCreationGUI(User user)
  {
    current_user = user;

    co_window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    co_window.setSize(600, 800);

    // Add content
    this.addComponentToPane(co_window.getContentPane());

    // Display the window
    co_window.setVisible(true);
  }

   class ButtonListener implements ActionListener
   {
     ButtonListener()
     {
     }
     public void actionPerformed( ActionEvent event )
     {
         String buttonActionCommand = event.getActionCommand();
         //TODO: a listener for when job or attributes change that automatically calls calculateSavingThrows()
         switch (buttonActionCommand)
         {
            case "reroll hitpoints":
               // re-roll hitpoints
               break;
            case "update combat stats":
               // re-calculate attack bonuses, AC's, CMB, CMD
               Utilities.calculateAC(test_npc);
               b_ac_d.setText(Integer.toString(test_npc.getAC()[0]));
               ff_ac_d.setText(Integer.toString(test_npc.getAC()[1]));
               t_ac_d.setText(Integer.toString(test_npc.getAC()[2]));
               init_d.setText(Integer.toString(test_npc.getInitiative()));
               speed_d.setText(Integer.toString(test_npc.getSpeed()));
               Utilities.calculateBAB(test_npc);
               bab_d.setText(Integer.toString(test_npc.getBAB()));
               cmb_d.setText(Integer.toString(test_npc.getCMB()));
               cmd_d.setText(Integer.toString(test_npc.getCMD()));
               // Calculate primary attack
               p_att_d.setText(Utilities.convertAttackValue(test_npc, 0));
               // Calculate secondary attack
               s_att_d.setText(Utilities.convertAttackValue(test_npc, 1));
               break;
            case "update saves":
               // re-calculate saves, saving them into character object
               Utilities.calculateSavingThrows(test_npc);
               // Update display labels
               fort_d.setText(Integer.toString(test_npc.getSaves()[0]));
               ref_d.setText(Integer.toString(test_npc.getSaves()[1]));
               will_d.setText(Integer.toString(test_npc.getSaves()[2]));
               break;
            case "align gen":
               // Generate a random alignment
               int align_num = Utilities.randGenN(9);
               test_npc.setAlign(alignment_array[align_num]);
               alignment_d.setText(test_npc.getAlign());
               break;
            case "race gen":
               // Generate a random number and use it to index the race array
               int race_num = Utilities.randGenN(7);
               // DEVELOPMENT
               // set the npc race
               test_npc.setRace(race_array[race_num]);
               // set the label
               race_d.setText(test_npc.getRace());
               break;
            case "job gen":
               int job_num = Utilities.randGenN(5);
               // DEVELOPMENT
               test_npc.setJob(npc_job_array[job_num]);
               job_d.setText(test_npc.getJob());
               break;
            case "stats gen":
               // Generate a new set of ability scores
               // NOTE: classic is hard coded into the statement
               Utilities.generateAttributes(test_npc, "classic");
               // Apply racial modifiers
               // Check that the character object has been given a race
               if (test_npc.getRace() != null)
               {
                  Utilities.applyRacialBonuses(test_npc);
               }
               // Update the display labels
               str_d.setText(Integer.toString(test_npc.getAttributes()[0]));
               dex_d.setText(Integer.toString(test_npc.getAttributes()[1]));
               con_d.setText(Integer.toString(test_npc.getAttributes()[2]));
               int_d.setText(Integer.toString(test_npc.getAttributes()[3]));
               wis_d.setText(Integer.toString(test_npc.getAttributes()[4]));
               cha_d.setText(Integer.toString(test_npc.getAttributes()[5]));
               break;
            case "save":
               /*
                Call a helper function that opens a new file,
                  writes the current character info string to it,
                  then closes the file.
               */
               // Pull the character object attributes from the text fields
               test_npc.setName(co_name_field.getText());
               test_npc.setDesc(co_desc_field.getText());
               try
               {
                  int error_code = Utilities.saveCharacterObject(test_npc, "character");
               }
               catch(IOException ioe)
               {
                  // Handle exception here
               }
               break;
            case "back":
               //TODO: prompt the user to save their character object
               // Return to the menu
               co_window.setVisible(false);
               co_window.dispose();
               MainMenu mainMenu = new MainMenu( current_user );
               break;
         }
     }
   }
}
