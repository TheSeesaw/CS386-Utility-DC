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

public class MapGrid
{
	// create initial JFrame to hold the map
	// JFrame mapFrame = new JFrame("Dungeons and Dragons Blank Map");
	// create the array of buttons that represent a tile
	JButton[][] buttonGrid;
	
	// creates a blank map that can be edited at the users choice
	public void createEmptyMapGrid( int width, int length )
	{
		// create the JFrame to hold the grid
		JFrame mapFrame = new JFrame("Dungeons and Dragons Map");
		
		mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mapFrame.setLayout( new GridLayout ( width, length ) );
		
		// create actionlistener for mouse click
		ActionListener listener = new ActionListener() 
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				Object source = e.getSource();
				if ( source instanceof Component )
				{
					if ( ((Component)source).getBackground() == Color.BLACK )
					{
						((Component)source).setBackground( Color.WHITE );
					}
					else
					{
						((Component)source).setBackground( Color.BLACK );
					}
				}
			}
		};
		
		// initialize buttonGrid to be JButtons
		buttonGrid = new JButton[width][length];
		for ( int i = 0; i < length; i++ )
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
		
		mapFrame.pack();
		mapFrame.setVisible( true );
	}
	
	// creates a pre-made map that represents a room
	public void createTestMapGrid( int width, int length )
	{
		JFrame mapFrame = new JFrame("Dungeons and Dragons Premade Map");
		
		mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mapFrame.setLayout( new GridLayout ( width, length ) );
		
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
		
		buttonGrid = new JButton[width][length];
		for ( int i = 0; i < length; i++ )
		{
			for ( int j = 0; j < width; j++ )
			{
				buttonGrid[i][j] = new JButton();
				
				buttonGrid[i][j].setForeground( Color.WHITE );
				buttonGrid[i][j].setBackground( Color.WHITE );
				buttonGrid[i][j].addActionListener(listener);
				
				Border line = new LineBorder( Color.BLACK );
				Border margin = new EmptyBorder( 20, 20, 20, 20 );
				Border compound = new CompoundBorder( line, margin );
				buttonGrid[i][j].setBorder( compound );
				
				mapFrame.add(buttonGrid[i][j]);
			}
		}
		
		// sets border to black
		for ( int i = 0; i < length; i++ )
		{
			for ( int j = 0; j < width; j++ )
			{
				if ( i == 0 || i == 19 || j == 0 || j == 19 )
				{
					buttonGrid[i][j].setBackground( Color.BLACK );
					buttonGrid[i][j].setEnabled(false);
				}
			}
		}
		// sets individual pieces to black to look like room
		// locks room pieces that cant be moved
		
		// sets doors open
		buttonGrid[0][1].setBackground( Color.WHITE );
		buttonGrid[0][1].setEnabled(true);
		buttonGrid[0][2].setBackground( Color.WHITE );
		buttonGrid[0][2].setEnabled(true);
		buttonGrid[19][9].setBackground( Color.WHITE );
		buttonGrid[19][9].setEnabled(true);
		buttonGrid[19][10].setBackground( Color.WHITE );
		buttonGrid[19][10].setEnabled(true);
		
		// set lower left room
		buttonGrid[10][1].setBackground( Color.BLACK );
		buttonGrid[10][1].setEnabled(false);
		buttonGrid[10][2].setBackground( Color.BLACK );
		buttonGrid[10][2].setEnabled(false);
		buttonGrid[10][3].setBackground( Color.BLACK );
		buttonGrid[10][3].setEnabled(false);
		buttonGrid[10][4].setBackground( Color.BLACK );
		buttonGrid[10][4].setEnabled(false);
		buttonGrid[11][4].setBackground( Color.BLACK );
		buttonGrid[11][4].setEnabled(false);
		buttonGrid[14][4].setBackground( Color.BLACK );
		buttonGrid[14][4].setEnabled(false);
		buttonGrid[15][4].setBackground( Color.BLACK );
		buttonGrid[15][4].setEnabled(false);
		buttonGrid[15][5].setBackground( Color.BLACK );
		buttonGrid[15][5].setEnabled(false);
		buttonGrid[15][6].setBackground( Color.BLACK );
		buttonGrid[15][6].setEnabled(false);
		buttonGrid[15][7].setBackground( Color.BLACK );
		buttonGrid[15][7].setEnabled(false);
		buttonGrid[16][7].setBackground( Color.BLACK );
		buttonGrid[16][7].setEnabled(false);
		buttonGrid[17][7].setBackground( Color.BLACK );
		buttonGrid[17][7].setEnabled(false);
		buttonGrid[18][7].setBackground( Color.BLACK );
		buttonGrid[18][7].setEnabled(false);
		
		// set lower right room
		buttonGrid[18][12].setBackground( Color.BLACK );
		buttonGrid[18][12].setEnabled(false);
		buttonGrid[17][12].setBackground( Color.BLACK );
		buttonGrid[17][12].setEnabled(false);
		buttonGrid[16][12].setBackground( Color.BLACK );
		buttonGrid[16][12].setEnabled(false);
		buttonGrid[15][12].setBackground( Color.BLACK );
		buttonGrid[15][12].setEnabled(false);
		buttonGrid[15][13].setBackground( Color.BLACK );
		buttonGrid[15][13].setEnabled(false);
		buttonGrid[15][14].setBackground( Color.BLACK );
		buttonGrid[15][14].setEnabled(false);
		buttonGrid[14][14].setBackground( Color.BLACK );
		buttonGrid[14][14].setEnabled(false);
		buttonGrid[13][14].setBackground( Color.BLACK );
		buttonGrid[13][14].setEnabled(false);		
		buttonGrid[12][14].setBackground( Color.BLACK );
		buttonGrid[12][14].setEnabled(false);
		buttonGrid[11][14].setBackground( Color.BLACK );
		buttonGrid[11][14].setEnabled(false);
		buttonGrid[10][14].setBackground( Color.BLACK );
		buttonGrid[10][14].setEnabled(false);
		buttonGrid[9][14].setBackground( Color.BLACK );
		buttonGrid[9][14].setEnabled(false);
		buttonGrid[10][15].setBackground( Color.BLACK );
		buttonGrid[10][15].setEnabled(false);
		buttonGrid[10][18].setBackground( Color.BLACK );
		buttonGrid[10][18].setEnabled(false);
		
		// set upper right room
		buttonGrid[1][14].setBackground( Color.BLACK );
		buttonGrid[1][14].setEnabled(false);
		buttonGrid[2][14].setBackground( Color.BLACK );
		buttonGrid[2][14].setEnabled(false);
		buttonGrid[3][14].setBackground( Color.BLACK );
		buttonGrid[3][14].setEnabled(false);
		buttonGrid[4][14].setBackground( Color.BLACK );
		buttonGrid[4][14].setEnabled(false);
		buttonGrid[5][14].setBackground( Color.BLACK );
		buttonGrid[5][14].setEnabled(false);
		buttonGrid[6][14].setBackground( Color.BLACK );
		buttonGrid[6][14].setEnabled(false);
		buttonGrid[3][15].setBackground( Color.BLACK );
		buttonGrid[3][15].setEnabled(false);
		buttonGrid[3][17].setBackground( Color.BLACK );
		buttonGrid[3][17].setEnabled(false);
		buttonGrid[3][18].setBackground( Color.BLACK );
		buttonGrid[3][18].setEnabled(false);
		
		// set upper left room
		buttonGrid[1][5].setBackground( Color.BLACK );
		buttonGrid[1][5].setEnabled(false);
		buttonGrid[2][5].setBackground( Color.BLACK );
		buttonGrid[2][5].setEnabled(false);
		buttonGrid[3][5].setBackground( Color.BLACK );
		buttonGrid[3][5].setEnabled(false);
		buttonGrid[4][5].setBackground( Color.BLACK );
		buttonGrid[4][5].setEnabled(false);
		buttonGrid[4][6].setBackground( Color.BLACK );
		buttonGrid[4][6].setEnabled(false);
		buttonGrid[4][9].setBackground( Color.BLACK );
		buttonGrid[4][9].setEnabled(false);
		buttonGrid[4][10].setBackground( Color.BLACK );
		buttonGrid[4][10].setEnabled(false);
		buttonGrid[4][11].setBackground( Color.BLACK );
		buttonGrid[4][11].setEnabled(false);
		buttonGrid[4][12].setBackground( Color.BLACK );
		buttonGrid[4][12].setEnabled(false);
		buttonGrid[4][13].setBackground( Color.BLACK );
		buttonGrid[4][13].setEnabled(false);
			
		mapFrame.pack();
		mapFrame.setVisible( true );
	}
	
	/**
	*  TODO:
	*  - Work on making different access for admin and player
	*  - Admin can set objects and NPCs
	*  - Work on visible and not visible, i.e. greying out not
	*    visible areas to the players
	*  - Different colors for each different player
	*/

	public static void main(String[] args)
	{
		// This sets up and runs the blank room
		/*SwingUtilities.invokeLater( new Runnable() 
		{
			@Override
			public void run()
			{
				new MapGrid().createEmptyMapGrid( 20, 20 );
			}
		});*/
		
		// this sets up and runs the pre-made romom
		SwingUtilities.invokeLater( new Runnable() 
		{
			@Override
			public void run()
			{
				new MapGrid().createTestMapGrid( 20, 20 );
			}
		});
	}
}