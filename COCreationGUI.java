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
  // Tools object, allows access to methods in Utilities
  Utilities tools = new Utilities();
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
  // --DEVELOPMENT--
  // NPC object
  NonPlayerCharacter test_npc = new NonPlayerCharacter();
  JLabel alignment_t = new JLabel("Alignment: ");
  JLabel alignment_d = new JLabel(test_npc.getAlign());
  JLabel race_t = new JLabel("Race: ");
  JLabel race_d = new JLabel(test_npc.getRace());
  JLabel job_t = new JLabel("Class: ");
  JLabel job_d = new JLabel(test_npc.getJob());

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
    JPanel combat_stats = new JPanel();
    JPanel skills_feats = new JPanel();
    JPanel gear_items = new JPanel();
    JPanel spells_abilities = new JPanel();
    save.addActionListener(new ButtonListener());
    save.setActionCommand("save");
    back.addActionListener(new ButtonListener());
    back.setActionCommand("back");

    // Details panel functionality

    // Character Object name
    JLabel name = new JLabel("Name: ");
    details.add(name);
    details.add(co_name_field);

    // Character object description
    JLabel desc = new JLabel("Description: ");
    details.add(desc);
    details.add(co_desc_field);

    // Alignment option (randomly generated)
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
    details.add(race_t);
    details.add(race_d);
    JButton gen_race = new JButton("Random");
    gen_race.addActionListener(new ButtonListener());
    gen_race.setActionCommand("race gen");
    details.add(gen_race);

    // Class option (randomly generated)
    details.add(job_t);
    details.add(job_d);
    JButton gen_job = new JButton("Random");
    gen_job.addActionListener(new ButtonListener());
    gen_job.setActionCommand("job gen");
    details.add(gen_job);

    // Add the panel to the pane
    co_tabs.addTab(DETAILSPANEL, details);
    co_tabs.addTab(BASESTATS, base_stats);
    co_tabs.addTab(COMBATSTATS, combat_stats);
    co_tabs.addTab(SKILLSFEATS, skills_feats);
    co_tabs.addTab(GEARITEMS, gear_items);
    co_tabs.addTab(SPELLSABILITIES, spells_abilities);
    // Add the save button to all the panels
    details.add(save);
    details.add(back);
    // Add this component to the pane passed into this function
    pane.add(co_tabs, BorderLayout.CENTER);
  }

  public COCreationGUI(User user)
  {
    current_user = user;

    co_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
         switch (buttonActionCommand)
         {
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
                  int error_code = tools.saveCharacterObject(test_npc, "character");
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

/*
// Taken from TabDemo.java
public static void main(String[] args) {
    //Schedule a job for the event dispatch thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable()
    {
        public void run()
        {
            createAndShowGUI();
        }
    }
    );
}
*/



 /*


 // Create matching JPanels for each of the labels

 JPanel base_stats = new JPanel();
 JPanel combat_stats = new JPanel();
 JPanel skills_feats = new JPanel();
 JPanel gear_items = new JPanel();
 JPanel spells_abilities = new JPanel();

 // Button Begins character creation, will be removed when linked with menu program
 Button create_new;
 // Button stops creation, and saves all relevant data to a text file before closing the program
 Button save;
 // Test label to let the user know what they need to enter next
 // Initialized with no text, text is updated through creation
 JLabel status_label = new JLabel();
 // Create a new window, set size, make it visible
 public static void main(String[] args)
 {
    COCreationGUI new_co_window = new COCreationGUI("Create New Character Object");
    new_co_window.setSize(1000, 600);
    new_co_window.setVisible(true);
 }
 // This is mostly copied from tutorial @ https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html
 public COCreationGUI(String title)
 {
    // Set the title of the super panel
    super(title);
    // Initialize the panel that holds the cards
    cards = new JPanel(new CardLayout());
    // Add the tabs to the cards panel
    cards.add(details, DETAILSPANEL);
    cards.add(base_stats, BASESTATS);
    cards.add(combat_stats, COMBATSTATS);
    cards.add(skills_feats, SKILLSFEATS);
    cards.add(gear_items, GEARITEMS);
    cards.add(spells_abilities, SPELLSABILITIES);
    // TESTING COPIED FROM JAVA tutorial
    JPanel comboBoxPane = new JPanel(); // flow layout
    String comboBoxItems[] = {DETAILSPANEL, BASESTATS, COMBATSTATS, SKILLSFEATS, GEARITEMS, SPELLSABILITIES};
    JComboBox cb = new JComboBox(comboBoxItems);
    cb.setEditable(false);
    cb.addItemListener(this);
    comboBoxPane.add(cb);
    add(comboBoxPane, BorderLayout.PAGE_START);
    add(cards, BorderLayout.CENTER);
    // Configures layout for superpanel
    //setLayout();
    addWindowListener(this);
    create_new = new Button("Create New Character Object");
    save = new Button("Save");
    add(create_new);
    add(save);
    add(status_label);
    create_new.addActionListener(this);
    // Set the command for the create new Button
    create_new.setActionCommand("create");
    save.addActionListener(this);
    save.setActionCommand("save");

 }

 // I'm guessing this is an abstract method defined in one of the implemented classes
 public void actionPerformed(ActionEvent a_event)
 {
    String buttonActionCommand = a_event.getActionCommand();
    switch (buttonActionCommand)
    {
       case "create":
          // Create a text box to enter the name of the new character object
          status_label.setText("Please Enter a the Character's name");
          break;
       case "save":
           Call a helper function that opens a new file,
             writes the current character info string to it,
             then closes the file.

          break;
    }

 }
 public void itemStateChanged(ItemEvent evt)
 {
    CardLayout cl = (CardLayout)(cards.getLayout());
    cl.show(cards, (String)evt.getItem());
 }
 // Same for this one, except more specific
 public void windowClosing(WindowEvent w_event)
 {
    this.dispose();
 }
 // I'm guessing these are more of the abstract methods that need to be implemented somehow to be valid
 public void windowOpened(WindowEvent a_event) {}
 public void windowActivated(WindowEvent a_event) {}
 public void windowIconified(WindowEvent a_event) {}
 public void windowDeiconified(WindowEvent a_event) {}
 public void windowDeactivated(WindowEvent a_event) {}
 public void windowClosed(WindowEvent a_event)
 {
    System.exit(0);
 }
 */
