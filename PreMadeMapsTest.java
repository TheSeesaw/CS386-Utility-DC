import org.junit.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;


public class PreMadeMapsTest {
	PreMadeMaps test1 = new PreMadeMaps("C:/Users/Kevin/workspace/386GroupProject/src/testfilemap2.txt");
	
	@Test
	public void testInstantiation(){
		assertNotNull(test1);
	}
	
	PreMadeMaps test2 = new PreMadeMaps("C:/Users/Kevin/workspace/386GroupProject/src/junitTest2x2.txt");
	@Test
	public void testMapSize2x2Square(){
		assertNotNull(test2);
	}
	
	PreMadeMaps test3 = new PreMadeMaps("C:/Users/Kevin/workspace/386GroupProject/src/junitTest2x3.txt");
	@Test
	public void testMapSize2x3Rectangle(){
		assertNotNull(test3);
	}
	
	PreMadeMaps test4 = new PreMadeMaps("C:/Users/Kevin/workspace/386GroupProject/src/junitTestNotRectangle.txt");
	@Test
	public void testMapSizeNotRectangle(){
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    String expectedOutput = "";
	    assertEquals(expectedOutput, outContent.toString());
	}
}
