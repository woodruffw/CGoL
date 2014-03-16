/*	StartGUI.java
	Author: William Woodruff
	-------------

	Provides the starting GUI for the Game of Life.
	Launches GridGUI, which contains the main GUI.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class StartGUI
{
	private int rows, cols;

	private JFrame startFrame = new JFrame("Conway\'s Game of Life");
	private JPanel startPanel = new JPanel(new GridLayout(2, 1));

	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu = new JMenu("File");
	private JMenuItem loadItem = new JMenuItem("Load Grid From File...");
	private JMenuItem aboutItem = new JMenuItem("About");
	private JMenuItem quitItem = new JMenuItem("Quit");

	private JLabel rowLabel = new JLabel("Rows:");
	private JLabel colLabel = new JLabel("Columns:");
	private JTextField rowField = new JTextField();
	private JTextField colField = new JTextField();
	private JButton playButton = new JButton("Play");

	/* constructor */
	public StartGUI()
	{
		startFrame.setJMenuBar(menuBar);
		menu.add(loadItem);
		menu.add(aboutItem);
		menu.add(quitItem);
		menuBar.add(menu);

		startFrame.getContentPane().setLayout(new BorderLayout());
		startPanel.add(rowLabel);
		startPanel.add(rowField);
		startPanel.add(colLabel);
		startPanel.add(colField);
		startFrame.getContentPane().add(startPanel);
		startFrame.getContentPane().add(playButton, BorderLayout.SOUTH);

		Listener listener = new Listener();

		loadItem.addActionListener(listener);
		aboutItem.addActionListener(listener);
		quitItem.addActionListener(listener);
		playButton.addActionListener(listener);
	}

	/* launchpoint */
	public static void main(String[] args)
	{
		new StartGUI().disp();
	}

	/*  disp
		preps the startFrame for display
	*/
	public void disp()
	{
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame.setSize(500, 250);
		startFrame.setResizable(false);
		startFrame.setVisible(true);
	}

	/* nested action listening class */
	private class Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource().equals(loadItem))
			{
				JFileChooser fileChooser = new JFileChooser();
				int action = fileChooser.showOpenDialog(null);

				if (action == JFileChooser.APPROVE_OPTION)
				{
					try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile())))
					{
						String line;
						ArrayList<String> lines = new ArrayList<String>();

						while ((line = reader.readLine()) != null)
						{
							lines.add(line);
						}

						Cell[][] state = new Cell[lines.size()][lines.get(0).length()];
						for (int i = 0; i < lines.size(); i++)
						{
							for (int j = 0; j < lines.get(i).length(); j++)
							{
								state[i][j] = new Cell((lines.get(i).charAt(j) == '1') ? true : false);
							}
						}

						new GridGUI(state);
					}
					catch (IOException ioe)
					{
						JOptionPane.showMessageDialog(null, "Error: File could not be read.", "IOE",
													JOptionPane.ERROR_MESSAGE);
					}
					catch (Exception gex)
					{
						JOptionPane.showMessageDialog(null, "Error: File appears misformatted or corrupted.", "NPE/IOOBE",
													JOptionPane.ERROR_MESSAGE);
					}
				}
			}

			else if (e.getSource().equals(aboutItem))
			{
				JOptionPane.showMessageDialog(null, "This program implements Conway\'s Game of Life, a cellular automaton.\n"
												+ "Author: William Woodruff (william@tuffbizz.com)\n"
												+ "Licensed under the MIT License. Source at http://github.com/woodruffw/CGoL.",
												"About This Program",
												JOptionPane.INFORMATION_MESSAGE);
			}

			else if (e.getSource().equals(quitItem))
			{
				System.exit(0);
			}

			else if (e.getSource().equals(playButton))
			{
				try
				{
					rows = Math.abs(Integer.parseInt(rowField.getText()));
					cols = Math.abs(Integer.parseInt(colField.getText()));
					new GridGUI(rows, cols);
				}
				catch (NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "Error: Input cannot be parsed. Enter only positive integers.", "NFE",
													JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}