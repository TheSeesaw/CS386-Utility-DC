import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MainMenu
{
  private JFrame frame;
  private JLabel messageField;
  private JButton characterCreationButton;
  private JButton abilityCreationButton;
  private JButton mapCreationButton;
  private User validUser;
  private JPanel panel1;

  public MainMenu( User validUser )
  {
    this.validUser = validUser;
    frame = new JFrame( "Main Menu");
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    characterCreationButton = new JButton( "Character Creation" );
    abilityCreationButton = new JButton( "Ability Creation" );
    mapCreationButton = new JButton( "Map Creation");
    characterCreationButton.addActionListener( new ButtonListener() );
    abilityCreationButton.addActionListener( new ButtonListener() );
    mapCreationButton.addActionListener( new ButtonListener() );
    panel1 = new JPanel();
    panel1.add( characterCreationButton );
    panel1.add( abilityCreationButton );
    panel1.add( mapCreationButton );
    frame.add( panel1 );
    frame.pack();
    frame.setSize( 300, 200 );
    frame.setVisible( true );
  }

    class ButtonListener implements ActionListener
    {
      ButtonListener()
        {
        }
      public void actionPerformed( ActionEvent e )
      {
        if( e.getActionCommand().equals( "Character Creation" ) )
        {
          //COCreationGUI characterCreation = new COCreationGUI();
          String[] a = {""};
          COCreationGUI co_window = new COCreationGUI(validUser);
        //  frame.setVisible( false );
        }
		// handle map creation button
      else if( e.getActionCommand().equals( "Map Creation" ) )
      {
	  // enter the name of the text file you will load here
        PreMadeMaps maps_window = new PreMadeMaps("testfilemap2.txt");

      }

      // the ability stuff should go here
      else if( e.getActionCommand().equals( "Ability Creation" ) )
      {
        // open the ability window
        // make sure the default close operation for your frame is HIDE_ON_CLOSE not EXIT_ON_CLOSE
      }


      }


  }
}
