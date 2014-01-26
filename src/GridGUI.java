/*	GridGUI.java
	Author: William Woodruff
	-------------

	Provides a graphical grid for the Game of Life to operate on.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GridGUI
{
	private Grid gameGrid;
	private int rows, cols;
	private int ticks = 0;

	private JFrame gameFrame = new JFrame("Conway\'s Game of Life");
	private JPanel gamePanel = new JPanel();
	private JPanel[][] cellPanels;
	private JButton stepButton = new JButton("Step");
	private JLabel tickCount = new JLabel("Ticks: " + ticks);

	public GridGUI(int rows, int cols)
	{
		this.rows = rows;
		this.cols = cols;

		Listener listener = new Listener();
		cellPanels = new JPanel[rows][cols];
		gamePanel.setLayout(new GridLayout(rows, cols));
		gameFrame.getContentPane().setLayout(new BorderLayout());
		gameFrame.getContentPane().add(gamePanel);
		stepButton.addMouseListener(listener);
		gameFrame.getContentPane().add(stepButton, BorderLayout.SOUTH);
		gameFrame.getContentPane().add(tickCount, BorderLayout.NORTH);

		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				cellPanels[i][j] = new JPanel();
				cellPanels[i][j].setOpaque(true);
				cellPanels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				cellPanels[i][j].addMouseListener(listener);
				gamePanel.add(cellPanels[i][j]);
			}
		}

		this.disp();
	}

	public void disp()
	{
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(800, 800);
		gameFrame.setResizable(true);
		gameFrame.setVisible(true);
	}

	public void play()
	{
		Cell[][] gridCells = new Cell[rows][cols];

		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				if (cellPanels[i][j].getBackground().equals(Color.black))
					gridCells[i][j] = new Cell(true);
				else
					gridCells[i][j] = new Cell(false);
			}
		}

		gameGrid = new Grid(gridCells);
		gameGrid.tick();
		gridCells = gameGrid.getState();

		for (int k = 0; k < rows; k++)
		{
			for (int l = 0; l < cols; l++)
			{
				if (gridCells[k][l].isAlive())
				{
					cellPanels[k][l].setBackground(Color.black);
					cellPanels[k][l].repaint();
				}
				else
				{
					cellPanels[k][l].setBackground(Color.white);
					cellPanels[k][l].repaint();
				}
			}
		}

		ticks++;
	}

	private class Listener implements MouseListener
	{
		public void mouseClicked(MouseEvent me)
		{
			if (me.getSource().equals(stepButton))
			{
				GridGUI.this.play();
				tickCount.setText("Ticks: " + ticks);
			}
			else
			{

				for (int i = 0; i < cellPanels.length; i++)
				{
					for (int j = 0; j < cellPanels[0].length; j++)
					{
						if (me.getSource().equals(cellPanels[i][j]))
						{
							if (cellPanels[i][j].getBackground().equals(Color.black))
								cellPanels[i][j].setBackground(Color.white);
							else
								cellPanels[i][j].setBackground(Color.black);
						}
					}
				}
			}
		}

		//unused
		public void mouseEntered(MouseEvent me) {}
		public void mouseExited(MouseEvent me) {}
		public void mousePressed(MouseEvent me) {}
		public void mouseReleased(MouseEvent me) {}
	}
}