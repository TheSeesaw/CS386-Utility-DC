import java.io.*;
import java.util.ArrayList;

public class GetAbilities {

public static void main(String[] arg){

// Create the data objects for us to restore.
String name="";
int level=0;
ArrayList effects = new ArrayList();

// Wrap all in a try/catch block to trap I/O errors.
try{
// Open file to read from, named Abilities.sav.
FileInputStream saveFile = new FileInputStream("Abilities.sav");

// Create an ObjectInputStream to get objects from save file.
ObjectInputStream save = new ObjectInputStream(saveFile);

// Now we do the restore.
// readObject() returns a generic Object, we cast those back
// into their original class type.
// For primitive types, use the corresponding reference class.
name = (String) save.readObject();
level = (Integer) save.readObject();
effects = (ArrayList) save.readObject();

// Close the file.
save.close(); // This also closes saveFile.
}
catch(Exception exc){
exc.printStackTrace(); // If there was an error, print the info.
}

// Do these here with the restored data.
//should send the data to the calling class; 'return this information'

}
}
