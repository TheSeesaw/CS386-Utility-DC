import java.io.*;


public class Runner
{
  public static void main( String[] args ) throws IOException
  {
    File userFile = new File( "users.txt" );
    UserManagement users = new UserManagement( userFile );
    LoginScreen login = new LoginScreen( users );
    users.updateFile( userFile );
  }
}
