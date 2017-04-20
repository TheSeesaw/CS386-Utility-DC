import java.io.*;


public class Runner
{
  public static void main( String[] args ) throws IOException
  {
     // Check if the users.txt file exists or not
    File userFile = new File( "users.txt" );
    UserManagement users = new UserManagement( userFile );
    LoginScreen login = new LoginScreen( users );
  }
}
