import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class COCreationGUI extends Frame implements ActionListener, WindowListener
{
   // Declare variables for character creation window
   COCreationGUI new_co_window;
   // Card titles for the layout with description of contents
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
      // Need to do more research into what each of these statements do
      super(title);
      // Configure the layout of the page
      setLayout(new CardLayout());

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
            /* Call a helper function that opens a new file,
               writes the current character info string to it,
               then closes the file.
            */
            break;
      }

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
}
