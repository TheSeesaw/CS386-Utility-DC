import java.util.HashSet;
import java.util.Iterator;
import java.io.*;
import java.util.Scanner;
import java.io.IOException;


public class UserManagement
{
  private HashSet<User> users;
  private File userFile;

// initialize the user management
  public UserManagement( File userFile ) throws IOException
  {

    this.userFile = userFile;
    users  = new HashSet<User>();
    addUsersFromList( userFile );

  }

  // a request for a login: return the user if successful, null if unsuccessful
  public User login( String userName, String password )
  {

    // find the user with the given username
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
  public char addUser( String userName, String password, boolean isFromFile )
  {
    // username already taken
    if( getUserFromName( userName ) != null )
    {
      return 'u';
    }

    // invalid password
    if( checkPasswordValidity( password ) == false )
    {
      return 'p';
    }


    password = encrypt( password );
    // create a new user with the provided info and add it to the user hash set
    User newUser = new User( userName, password );
    users.add( newUser );

    // if this is a new user add their info to the end of the file
    if( isFromFile == false )
    {
      updateFile( newUser );
    }

    return 't';
  }



// update the text file with the new users
  public void updateFile( User user )
  {

    try
    {

      FileOutputStream fos = new FileOutputStream( userFile, true );

	    BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( fos ) );

      String userString = "" + user.getUserName() + ", " + user.getPassword();
      bw.write( userString );
      bw.newLine();

	    bw.close();


    }
    catch(IOException e)
    {

    }

  }



// given a txt file of existing users add them to the hashset
  private void addUsersFromList( File userFile ) throws IOException
  {

    Scanner inputScanner = new Scanner( userFile );
    String currentUserString;

    // go through the file line by line creating new users for each line
    while( inputScanner.hasNext()==true)
    {
      currentUserString = inputScanner.nextLine();
      createUserFromString( currentUserString );
    }

  }


// given a sring of user data, convert it to a user
  private void createUserFromString( String userString )
  {

    User newUser;
    StringBuilder modifiedString = new StringBuilder( userString );
    String userName = "";
    String password = "";
    boolean isUserName = true;
    // everything before the ',' is the username
    // everything after the ',' is the password
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
    // remove the whitespace
    userName = userName.replace(" ", "");
    password = password.replace(" ", "");

    // add the user
    addUser( userName, password, true );
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

    // iterate through the list comparing the given username against all usernames
    while( userIterator.hasNext() )
    {

      currentUser = userIterator.next();
      // when you find a match return the user
      if( currentUser.getUserName().compareTo( userName ) == 0 )
      {

        foundUser = currentUser;
        return foundUser;

      }

    }
    // if no users were found this will return null
    return foundUser;
  }


}
