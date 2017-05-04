import java.io.*;
import java.util.ArrayList;

public class CreateAbilities{

public static void main(String[] arg){

// Create some data objects for us to save.
// --potentially a GUI would allow user to input these values into fields to create ability
String name="MAGICMISSLE";
int level=1;
ArrayList effects = new ArrayList();
effects.add("effect1");
effects.add("effect2");


try{  // Catch errors in I/O if necessary.
// Open a file to write to, named Abilities.sav.
FileOutputStream saveFile=new FileOutputStream("Abilities.sav");

// Create an ObjectOutputStream to put objects into save file.
ObjectOutputStream save = new ObjectOutputStream(saveFile);

// Now we do the save.
save.writeObject(name);
save.writeObject(level);
save.writeObject(effects);


// Close the file.
save.close(); // This also closes saveFile.
}
catch(Exception exc){
exc.printStackTrace(); // If there was an error, print the info.
}
}
}