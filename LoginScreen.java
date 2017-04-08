import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class LoginScreen
{
  private JFrame frame;
  private JPasswordField passwordField;
  private JTextField userNameField;
  private JLabel messageField;
  private JPanel panel1;
  private JPanel buttonPanel;
  private JPanel passwordPanel;
  private JPanel userNamePanel;
  private JPanel messagePanel;
  private JLabel passwordLabel;
  private JLabel userNameLabel;
  private JButton submitButton;
  private JButton createAccountButton;
  private UserManagement users;


  public LoginScreen( UserManagement users )
  {
    // initialize the frame
    this.users = users;
    frame = new JFrame( "Log in screen" );
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    frame.setSize( 300, 200 );

    // create the panels
    // panel1 has text fields
    panel1 = new JPanel();
    buttonPanel = new JPanel();
    userNamePanel = new JPanel();
    passwordPanel = new JPanel();
    messagePanel = new JPanel();

    // create the submit button
    submitButton = new JButton( "Login" );
    createAccountButton = new JButton( "Create Account");

    // create the username and password fields and labels
    userNameLabel = new JLabel( "User Name:" );
    passwordLabel = new JLabel( "Password:" );
    messageField = new JLabel( "ERROR");
    panel1.setLayout( new BoxLayout( panel1, BoxLayout.PAGE_AXIS ) );
    userNameField = new JTextField( 25 );
    passwordField = new JPasswordField( 25 );

    submitButton.addActionListener( new ButtonListener() );
    createAccountButton.addActionListener( new ButtonListener() );
    // add the stuff to the panels
    messagePanel.add( messageField );
    messagePanel.setVisible( false );
    buttonPanel.add( submitButton );
    buttonPanel.add( createAccountButton );
    userNamePanel.add( userNameLabel );
    userNamePanel.add( userNameField );
    passwordPanel.add( passwordLabel );
    passwordPanel.add( passwordField );

    // add the panel to the frame
    panel1.add( messagePanel );
    panel1.add( userNamePanel );
    panel1.add( passwordPanel );
    panel1.add( buttonPanel );
    frame.getContentPane().add( panel1 );
		frame.pack();
		frame.setVisible( true );
  }


  class ButtonListener implements ActionListener
  {
    ButtonListener()
      {
      }
    public void actionPerformed( ActionEvent e )
    {

      // login button pressed
      if ( e.getActionCommand().equals( "Login" ) )
      {
        // attempt to login with given username and password
        User loginUser = users.login( userNameField.getText(), passwordField.getText() );

        // if it doesnt return null the login was successful
        if( loginUser != null )
        {
          frame.setVisible( false );
          frame.dispose();
          MainMenu mainMenu = new MainMenu( loginUser );
        }
        // login attempt unsuccessful
        else
        {
          // error message
          messageField.setForeground( Color.red );
          messageField.setText("Invalid User Name or Password");
        }
        messagePanel.setVisible( true );
        frame.pack();
      }
      // opens up the account creation screen
      else if( e.getActionCommand().equals( "Create Account" ) )
      {
        AccountCreationScreen createAccount = new AccountCreationScreen( users );
      }
    }
  }



}
