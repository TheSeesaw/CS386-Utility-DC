import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.Border;
import javax.swing.SwingUtilities;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;

public class PreMadeMaps
{
	static int HEIGHTFINAL;
	static int WIDTHFINAL;
	
	public void readTextFileHeight()
	{
		BufferedReader br = null;
        try 
		{
            br = new BufferedReader(new FileReader("testfilemap.txt"));
            String line;
			
            while ((line = br.readLine()) != null) 
			{
				HEIGHTFINAL++;
            }
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        } 
		finally 
		{
            try 
			{
                if (br != null) 
				{
                    br.close();
                }
            } 
			catch (IOException ex) 
			{
                ex.printStackTrace();
            }
		}
	}
	
	public void readTextFileWidth()
	{
		BufferedReader br = null;
        try 
		{
            br = new BufferedReader(new FileReader("testfilemap.txt"));
            String line;
			
            String text = br.readLine();
			WIDTHFINAL = text.length();
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        } 
		finally 
		{
            try 
			{
                if (br != null) 
				{
                    br.close();
                }
            } 
			catch (IOException ex) 
			{
                ex.printStackTrace();
            }
		}
	}
	
	// create initial JFrame to hold the map
	// JFrame mapFrame = new JFrame("Dungeons and Dragons Blank Map");
	// create the array of buttons that represent a tile
	JButton[][] buttonGrid;
	
	// creates a blank map that can be edited at the users choice
	public void createEmptyMapGrid( int width, int height )
	{
		// create the JFrame to hold the grid
		JFrame mapFrame = new JFrame("Dungeons and Dragons Map");
		
		mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mapFrame.setLayout( new GridLayout ( height, width ) );
		
		// create actionlistener for mouse click
		ActionListener listener = new ActionListener() 
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				Object source = e.getSource();
				if ( source instanceof Component )
				{
					if ( ((Component)source).getBackground() == Color.GREEN )
					{
						((Component)source).setBackground( Color.WHITE );
					}
					else
					{
						((Component)source).setBackground( Color.GREEN );
					}
				}
			}
		};
		
		// initialize buttonGrid to be JButtons
		buttonGrid = new JButton[height][width];
		for ( int i = 0; i < height; i++ )
		{
			for ( int j = 0; j < width; j++ )
			{
				// create each button for every tile
				buttonGrid[i][j] = new JButton();
				
				// sets base color to white
				buttonGrid[i][j].setForeground( Color.WHITE );
				buttonGrid[i][j].setBackground( Color.WHITE );
				buttonGrid[i][j].addActionListener(listener);
				
				// sets border color to black
				Border line = new LineBorder( Color.BLACK );
				Border margin = new EmptyBorder( 22, 22, 22, 22 );
				Border compound = new CompoundBorder( line, margin );
				buttonGrid[i][j].setBorder( compound );
				
				mapFrame.add(buttonGrid[i][j]);
			}
		}

		/*ERROR HERE FOR NOT RECTANGLE SIZES*/
		BufferedReader br = null;
        try 
		{
            br = new BufferedReader(new FileReader("testfilemap.txt"));
            String line;
			int count = 0;
			
            while ((line = br.readLine()) != null) 
			{
				String text = line;
				if ( text.length() > WIDTHFINAL | text.length() < WIDTHFINAL )
				{
					System.out.println("Incorrect file format. Needs to be in a rectangular shape.\n");
					System.exit(0);
				}
				else
				{
					for ( int i = 0; i < WIDTHFINAL; i++ )
					{
						if ( text.charAt(i) == '-' )
						{
							buttonGrid[count][i].setBackground( Color.BLACK );
							buttonGrid[count][i].setEnabled(false);
						}
						else
						{
							buttonGrid[count][i].setBackground( Color.WHITE );
							buttonGrid[count][i].setEnabled(true);
						}
					}
				}
				count++;
            }
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        } 
		finally 
		{
            try 
			{
                if (br != null) 
				{
                    br.close();
                }
            } 
			catch (IOException ex) 
			{
                ex.printStackTrace();
            }
		}
		
		mapFrame.pack();
		mapFrame.setVisible( true );
	}

	public static void main(String[] args)
	{	
		PreMadeMaps mpp = new PreMadeMaps();
		mpp.readTextFileHeight();
		mpp.readTextFileWidth();
		SwingUtilities.invokeLater( new Runnable() 
		{
			@Override
			public void run()
			{
				new PreMadeMaps().createEmptyMapGrid( WIDTHFINAL, HEIGHTFINAL );
			}
		});
	}
}