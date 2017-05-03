import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AccountCreationScreen
{
  private JFrame frame;
  private UserManagement users;
  private JPanel panel1;
  private JPanel panel2;
  private JPanel messagePanel;
  private JPanel userNamePanel;
  private JPanel emailPanel;
  private JPanel passwordPanel;
  private JPanel confirmPasswordPanel;
  private JLabel userNameLabel;
  private JLabel emailLabel;
  private JLabel passwordLabel;
  private JLabel messageField;
  private JLabel confirmPasswordLabel;
  private JTextField userNameField;
  private JTextField emailField;
  private JTextField passwordField;
  private JTextField confirmPasswordField;
  private JButton submitButton;
  private JButton returnButton;

  public AccountCreationScreen(UserManagement users)
  {
    this.users = users;
    frame = new JFrame( "User Creation Screen" );
    panel1 = new JPanel();
    panel2 = new JPanel();
    frame.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
    panel1.setLayout( new BoxLayout( panel1, BoxLayout.PAGE_AXIS ) );

    // message panel
    messagePanel = new JPanel();
    messageField = new JLabel( "ERROR" );
    messagePanel.add( messageField );
    messagePanel.setVisible( false );
    panel1.add( messagePanel );
    messageField.setForeground( Color.red );

    // user name
    userNamePanel = new JPanel();
    userNameLabel = new JLabel( "User Name:" );
    userNameField = new JTextField( 25 );
    userNamePanel.add( userNameLabel );
    userNamePanel.add( userNameField );
    panel1.add( userNamePanel );

    // email
    emailPanel = new JPanel();
    emailLabel = new JLabel( "e-mail:" );
    emailField = new JTextField( 25 );
    emailPanel.add( emailLabel );
    emailPanel.add( emailField );
    panel1.add( emailPanel );

    // password
    passwordPanel = new JPanel();
    passwordLabel = new JLabel( "Password:" );
    passwordField = new JTextField( 25 );
    passwordPanel.add( passwordLabel );
    passwordPanel.add( passwordField );
    panel1.add( passwordPanel );

    // confirm password
    confirmPasswordPanel = new JPanel();
    confirmPasswordLabel = new JLabel( "Confirm Password");
    confirmPasswordField = new JTextField( 25 );
    confirmPasswordPanel.add( confirmPasswordLabel );
    confirmPasswordPanel.add( confirmPasswordField );
    panel1.add( confirmPasswordPanel );

    // submit button
    submitButton = new JButton( "Submit" );
    panel2.add( submitButton );
    submitButton.addActionListener( new ButtonListener() );

    // return Button
    returnButton = new JButton( "Return to Login" );
    panel2.add( returnButton );
    returnButton.addActionListener( new ButtonListener() );

    panel1.add( panel2 );

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
        if ( e.getActionCommand().equals( "Submit" ) )
        {
          String password = passwordField.getText();
          if( password.compareTo( confirmPasswordField.getText() ) == 0 )
          {

            char checkPasswordCorrectness = users.addUser( userNameField.getText(), passwordField.getText(), false );
            // error
            if( checkPasswordCorrectness == 'u' )
            {
              messageField.setText( "Username Unavailable" );
            }
            // error
            else if( checkPasswordCorrectness == 'p' )
            {
              messageField.setText( "Invalid Password: must be at least 5 characters and contain 1 number" );
            }

            // successful login attempt
            else if( checkPasswordCorrectness == 't' )
            {

              frame.setVisible( false );
              frame.dispose();
            }
          }
          else
          {
            messageField.setText("Passwords don\'t match");
          }
          messagePanel.setVisible( true );
          frame.pack();
        }

        else if ( e.getActionCommand().equals( "Return to Login" ) )
        {
          frame.setVisible( false );
          frame.dispose();
        }
      }
    }
  }
