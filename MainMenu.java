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
    abilityCreationButton = new JButton( "Abiltiy Creation" );
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
          COCreationGUI.main( a );
          frame.setVisible( false );
        }

        if( e.getActionCommand().equals( "Ability Creation" ) )
        {
          // put the methods to call your part here
          // validUser may be a pertinent variable( the user that is currently logged in)
        }

        if( e.getActionCommand().equals( "Map Creation" ) )
        {
          // put the methods to call your part here
          // validUser may be a pertinent variable
        }
      }
  }
}
