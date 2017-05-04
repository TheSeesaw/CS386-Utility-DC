import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class AbilityCreationGUI
{
  // Save buttons for each panel
  JButton save = new JButton("Save Changes");

  // Back buttons for each panel
  JButton back = new JButton("Back to Menu");

  // Declare variable for character ability creation jframe, made global for disposing purposes
  JFrame ab_window = new JFrame("Create New Ability or Spell Object");
  User current_user;

  // Initialize ability name, level, and description
  JTextField ab_name_field = new JTextField("Enter a name here", 40);
  String ab_name = "";
  JTextField ab_level_field = new JTextField("Enter the level here", 40);
  String ab_name = "";
  JTextField ab_desc_field = new JTextField("Enter a description here", 40);
  String ab_desc = "";

 
  // Card labels for the layout with description of contents
  // Spells and abilities
  final static String SPELLSABILITIES = "Abilities/Spells";

  public void addComponentToPane(Container pane)
  {
    // Declare JTabbedPane that will go inside ability creation frame
    JTabbedPane ab_tabs = new JTabbedPane();

    // Create a matching panel for card label
    JPanel spells_abilities = new JPanel();
    //spells_abilities.setLayout(new BoxLayout(spells_abilities, BoxLayout.PAGE_AXIS));

    save.addActionListener(new ButtonListener());
    save.setActionCommand("save");

    back.addActionListener(new ButtonListener());
    back.setActionCommand("back");

    // Details panel functionality==============================================

    // Ability Object name
    JLabel name = new JLabel("Name: ");
    spells_abilities.add(name);
    spells_abilities.add(ab_name_field);

    // Ability Object level
    JLabel level = new JLabel("Level: ");
    spells_abilities.add(level);
    spells_abilities.add(ab_level_field);

    // Ability object description
    JLabel desc = new JLabel("Description: ");
    spells_abilities.add(desc);
    spells_abilities.add(ab_desc_field);

    // Spells and Abilities ====================================================

    JLabel ability_name_label = new JLabel("Name of Ability: ");
    JLabel ability_level_label = new JLabel("Level of Ability: ");
    JLabel ability_effects_label = new JLabel("Effects of Ability: ");

    // Add the panel to the pane
    ab_tabs.addTab(SPELLSABILITIES, spells_abilities);
    // Add the save and back buttons to the panel
    spells_abilities.add(save);
    spells_abilities.add(back);
    // Add this component to the pane passed into this function
    pane.add(ab_tabs, BorderLayout.CENTER);
  }

  public AbilityCreationGUI(User user)
  {
    current_user = user;

    ab_window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    ab_window.setSize(600, 800);

    // Add content
    this.addComponentToPane(ab_window.getContentPane());

    // Display the window
    ab_window.setVisible(true);
  }

   class ButtonListener implements ActionListener
   {
     ButtonListener()
     {
     }
     public void actionPerformed( ActionEvent event )
     {
         String buttonActionCommand = event.getActionCommand();
         switch (buttonActionCommand)
         {
            
            case "save":
               /*
                Call a helper function that saves an object 
               */
               
               try
               {
                  int error_code = CreateAbilities;
               }
               catch(IOException ioe)
               {
                  // Handle exception here
               }
               break;
            case "back":
               //TODO: prompt the user to save their ability/spell object
               // Return to the menu
               ab_window.setVisible(false);
               ab_window.dispose();
               MainMenu mainMenu = new MainMenu( current_user );
               break;
         }
     }
   }
}
