import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.Border;
import javax.swing.SwingUtilities;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class PreMadeMaps
{
	// holds the name of the text file entered in MainMenu.java
	private String mapTextName;

	private int fileRows;
	private int fileColumns;
    private final List<JButton> buttonList = new ArrayList<JButton>();

	private int turnNumber;

	// initialize the Map using the entered text file
	public PreMadeMaps(String mapTextName)
	{
		turnNumber = 1;
		this.mapTextName = mapTextName;
		display();
	}

	// get the amount of rows from the text file
	public void readTextFileRows()
	{
		BufferedReader br = null;
        try
		{
            br = new BufferedReader( new FileReader(mapTextName) );
            String line;

            while ( ( line = br.readLine() ) != null )
			{
				fileRows++;
            }
        }
		catch ( IOException e )
		{
            e.printStackTrace();
        }
		finally
		{
            try
			{
                if ( br != null )
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

	// get the amount of columns from the text file
	public void readTextFileColumns()
	{
		BufferedReader br = null;
        try
		{
            br = new BufferedReader(new FileReader(mapTextName));
            String line;

            String text = br.readLine();
			fileColumns = text.length();
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

	// return the index of the button pressed to command line
    private JButton getGridButton(int row, int column)
	{
        int index = row * 5 + column;
        return buttonList.get(index);
    }

	// create an individual button to place on the grid at position (row, col)
    private JButton createGridButton(final int row, final int col)
	{
        final JButton newButton = new JButton();
        newButton.addActionListener(new ActionListener()
		{
            @Override
            public void actionPerformed(ActionEvent e)
			{
                JButton gridButton = PreMadeMaps.this.getGridButton(row, col);
                // System.out.println("row" + row + ",col" + col);

				Object source = e.getSource();
				if ( source instanceof Component )
				{
					if ( turnNumber == 1 )
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
					else if ( turnNumber == 2 )
					{
						if ( ((Component)source).getBackground() == Color.MAGENTA )
						{
							((Component)source).setBackground( Color.WHITE );
						}
						else
						{
							((Component)source).setBackground( Color.MAGENTA );
						}
					}
					else if ( turnNumber == 3 )
					{
						if ( ((Component)source).getBackground() == Color.BLUE )
						{
							((Component)source).setBackground( Color.WHITE );
						}
						else
						{
							((Component)source).setBackground( Color.BLUE );
						}
					}
					else
					{
						if ( ((Component)source).getBackground() == Color.ORANGE )
						{
							((Component)source).setBackground( Color.WHITE );
						}
						else
						{
							((Component)source).setBackground( Color.ORANGE );
						}
					}
				}
            }
        });

		Border line = new LineBorder( Color.BLACK );
		Border margin = new EmptyBorder( 22, 22, 22, 22 );
		Border compound = new CompoundBorder( line, margin );
		newButton.setBorder( compound );
		newButton.setBackground( Color.WHITE );

        return newButton;
    }

	// create the panel to hold the button grid
    private JPanel createGridPanel()
	{
        JPanel mapPanel = new JPanel(new GridLayout(fileRows, fileColumns));

        for (int i = 0; i < fileRows * fileColumns; i++)
		{
            int row = i / fileRows;
            int col = i % fileColumns;
            JButton gridButton = createGridButton(row, col);
            buttonList.add(gridButton);
            mapPanel.add(gridButton);
        }

		// read from the file to create the predesigned map
		BufferedReader newBufferedReader = null;
        try
		{
            newBufferedReader = new BufferedReader(new FileReader(mapTextName));
            String line;
			int count = 0;

            while ((line = newBufferedReader.readLine()) != null)
			{
				String text = line;
				text.trim();
				if ( text.length() > fileColumns | text.length() < fileColumns )
				{
					System.out.println("Incorrect file format. Needs to be in a rectangular shape.\n");
					System.exit(0);
				}
				else
				{
					for ( int i=0; i<fileColumns; i++ )
					{
						int textCharIsAt = i % fileColumns;
						if ( text.charAt(i) == '-' )
						{
							buttonList.get(count * fileColumns + i).setBackground( Color.BLACK );
							buttonList.get(count * fileColumns + i).setEnabled(false);
						}
						else if ( text.charAt(i) == 'M' )
						{
							buttonList.get(count * fileColumns + i).setBackground( Color.RED );
							buttonList.get(count * fileColumns + i).setEnabled(true);
						}
						else
						{
							buttonList.get(count * fileColumns + i).setBackground( Color.WHITE );
							buttonList.get(count * fileColumns + i).setEnabled(true);
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
                if (newBufferedReader != null)
				{
                    newBufferedReader.close();
                }
            }
			catch (IOException ex)
			{
                ex.printStackTrace();
            }
		}


        return mapPanel;
    }

	// create the JFrame to display to the screen
    private void display()
	{
		// load the rows and columns from the file
		readTextFileColumns();
		readTextFileRows();

		// create the frame
        JFrame mapFrame = new JFrame("Dungeons and Dragons Map");
        mapFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// create the big panel that will hold the grid and the turn buttons
		JPanel MAINPANEL = new JPanel(new FlowLayout());
        MAINPANEL.add(createGridPanel());

		// create the changeTurn panel that will hold the change turn button and the counter
		JPanel changeTurn = new JPanel(new FlowLayout());
		JButton changeTurnButton = new JButton("Change Turn");

		// set the shape of the counter
		JButton currentTurn = new JButton("Player 1's Turn");
		currentTurn.setEnabled(false);
		Border line2 = new LineBorder( Color.BLACK );
		// EmptyBorder(made it a square text on bottom,made it a long rectangle text on right,made it a square text on top,made it a flat rectangle text on left)
		Border margin2 = new EmptyBorder( 5, 5, 5, 30 );
		Border compound2 = new CompoundBorder( line2, margin2 );
		currentTurn.setBorder( compound2 );
		currentTurn.setBackground( Color.WHITE );

		// add an action listener to the change turn button
		changeTurnButton.addActionListener(new ActionListener()
		{
            @Override
            public void actionPerformed(ActionEvent e)
			{
				Object source = e.getSource();
				if ( source instanceof Component )
				{
					// first players turn
					if ( turnNumber == 1 )
					{
						turnNumber++;
						currentTurn.setText("Player 2's Turn");
					}
					// second players turn
					else if ( turnNumber == 2 )
					{
						turnNumber++;
						currentTurn.setText("Player 3's Turn");
					}
					// third players turn
					else if ( turnNumber == 3 )
					{
						turnNumber++;
						currentTurn.setText("Player 4's Turn");
					}
					// fourth players turn
					else
					{
						turnNumber = 1;
						currentTurn.setText("Player 1's Turn");
					}
				}
            }
        });

		// set the shape of the change turn button
		Border line1 = new LineBorder( Color.BLACK );
		Border margin1 = new EmptyBorder( 30, 30, 30, 30 );
		Border compound1 = new CompoundBorder( line1, margin1 );
		changeTurnButton.setBorder( compound1 );
		changeTurnButton.setBackground( Color.WHITE );
		changeTurnButton.setFont(new Font("Arial", Font.PLAIN, 20));
		changeTurn.add(changeTurnButton);

		changeTurn.add(currentTurn);

		// add the change turn panel to the big panel panel
		MAINPANEL.add(changeTurn);
		// add the big main panel to the frame
		mapFrame.add(MAINPANEL);
        mapFrame.pack();
        mapFrame.setLocationRelativeTo(null);
        mapFrame.setVisible(true);
    }

    /*public static void main(String[] args)
	{
        EventQueue.invokeLater(new Runnable()
		{
            @Override
            public void run()
			{
                new PreMadeMaps().display();
            }
        });

    }*/
}
