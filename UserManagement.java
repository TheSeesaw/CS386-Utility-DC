import java.util.HashSet;
import java.util.Iterator;
import java.io.*;
import java.util.Scanner;
import java.io.IOException;


public class UserManagement
{
  private HashSet<User> users;
  private File userFile;
  private FileWriter writer;

  public UserManagement( File userFile ) throws IOException
  {
    this.userFile = userFile;
    users  = new HashSet<User>();
    userFile = new File("users.txt");

    addUsersFromList( userFile );
  }

  // a request for a login: return the user if successful, null if unsuccessful
  public User login( String userName, String password )
  {
    User loginUser = getUserFromName( userName );
    // If the userName belongs to a user
    if( loginUser != null)
    {
      // if the passwords match
      if( checkPasswordCorrectness( password, loginUser ) == true )
      {
        return loginUser;
      }
    }
    return null;
  }

  // add a user : u = invalid user name, p = invalid password, t = added successfully
  public char addUser( String userName, String password )
  {

    if( getUserFromName( userName ) != null )
    {
      return 'u';
    }
    if( checkPasswordValidity( password ) == false )
    {
      return 'p';
    }

    password = encrypt( password );
    User newUser = new User( userName, password );
    users.add( newUser );

    return 't';
  }

// update the text file with the new users: just a placeholder right now
  public void updateFile( File userFile )
  {/*

    try
    {


       // Writes the users to the file
       User currentUser;
       Iterator<User> iterator = users.iterator();
       String userString = "";

       while( iterator.hasNext() )
       {
         currentUser = iterator.next();
         userString += "" + currentUser.getUserName() + ", " + currentUser.getPassword() + "\n";
         //System.out.println( userString );
       }
       writer.write( userString );
       writer.flush();
       writer.close();
    }
    catch (IOException e)
    {

    }*/
  }


// given a txt file of existing users add them to the hashset
  private void addUsersFromList( File userFile ) throws IOException
  {
    Scanner inputScanner = new Scanner( userFile );
    String currentUserString;
    while( inputScanner.hasNext()==true)
    {
      currentUserString = inputScanner.nextLine();
      createUserFromString( currentUserString );
    }
  }



  private void createUserFromString( String userString )
  {
    User newUser;
    StringBuilder modifiedString = new StringBuilder( userString );
    String userName = "";
    String password = "";
    boolean isUserName = true;
    for( int i=0; i<userString.length(); i++)
    {
      if( modifiedString.charAt( i ) == ',' )
      {
        i+=1;
        isUserName = false;
      }
      if( isUserName == true )
      {
        userName += modifiedString.charAt( i );
      }
      else
      {
        password += modifiedString.charAt( i );
      }
    }
    password = password.replace(" ", "");
    addUser( userName, password );
  }


// encrypt a given password: currently only a placeholder method
  private String encrypt( String passwordString )
  {
    return passwordString;
  }

// decrypt a given password: currently only a placeholder method
  private String decrypt( String passwordString)
  {
    return passwordString;
  }

// see if a password is valid: valid if not empty
  private boolean checkPasswordValidity( String password )
  {
    if( password.compareTo( "" ) == 0)
    {
      return false;
    }
    else
    {
      return true;
    }
  }

// compare the given password to the password for a user
  private boolean checkPasswordCorrectness( String givenPassword, User givenUser )
  {
    String actualPassword = givenUser.getPassword();
    // user passwords are encrypted so you must decrypt them
    actualPassword = decrypt(actualPassword);
    if( givenPassword.compareTo( actualPassword ) == 0 )
    {
      return true;
    }
    else
    {
      return false;
    }
  }

// given a username return the user that belongs to that name, null if it doesnt exist
  private User getUserFromName( String userName )
  {
    User currentUser;
    User foundUser = null;
    Iterator<User> userIterator = users.iterator();
    while( userIterator.hasNext() )
    {
      currentUser = userIterator.next();
      if( currentUser.getUserName().compareTo( userName ) == 0 )
      {
        foundUser = currentUser;
        return foundUser;
      }
    }
    return foundUser;
  //  return null;
  }

}
