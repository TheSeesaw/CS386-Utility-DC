public class User
{
  private String userName;
  private String password;
  private int gameMode;
  // private Party currentParty; need the Party class to implement

  public User( String userName, String password )
  {
    this.userName = userName;
    this.password = password;
    this.gameMode = 0;
  }

  public String getUserName()
  {
    return userName;
  }

  public String getPassword()
  {
    return password;
  }

  public int getGameMode()
  {
    return gameMode;
  }

  public void setGameMode( int gameMode )
  {
    this.gameMode = gameMode;
  }

}
