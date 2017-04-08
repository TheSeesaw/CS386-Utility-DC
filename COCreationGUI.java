import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class COCreationGUI //extends JFrame implements ActionListener, WindowListener, ItemListener
{
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
    JPanel base_stats = new JPanel();
    JPanel combat_stats = new JPanel();
    JPanel skills_feats = new JPanel();
    JPanel gear_items = new JPanel();
    JPanel spells_abilities = new JPanel();

    // Add a button for visibility
    //TODO Fill in the remaining fields for the character creation panels
    details.add(new JButton("Visibility Button"));

    // Add the panel to the pane
    co_tabs.addTab(DETAILSPANEL, details);
    co_tabs.addTab(BASESTATS, base_stats);
    co_tabs.addTab(COMBATSTATS, combat_stats);
    co_tabs.addTab(SKILLSFEATS, skills_feats);
    co_tabs.addTab(GEARITEMS, gear_items);
    co_tabs.addTab(SPELLSABILITIES, spells_abilities);
    // Add this component to the pane passed into this function
    pane.add(co_tabs, BorderLayout.CENTER);
  }

  public COCreationGUI(String user)
  {
    String current_user = user;
    // Declare variable for character creation jframe
    JFrame co_window = new JFrame("Create New Character Object");
    co_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    co_window.setSize(600, 800);

    // Add content
    this.addComponentToPane(co_window.getContentPane());

    // Display the window
    co_window.setVisible(true);
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
}
