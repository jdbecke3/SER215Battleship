/* 
Name: Menu Panel 
Author: Joshua Becker
Create On: 9/9/15
Updated On: 9/19/15
Contributors:
 */

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
import javax.swing.ImageIcon.*;
import javax.swing.JFrame;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public class GameSetUpWindow
{
	private JFrame m_GameSetUpFrame, m_MenuFrame;
	private int m_ScreenWidth, m_ScreenHeight;
	private JButton m_StartNewGame_B, m_BackToMenu_B, m_LoadAGame_B;
	private JPanel m_MenuButton_P;
	private JLabel m_Background_L;
	
    public GameSetUpWindow(JFrame window)// constructer
    {
		m_MenuFrame = window;
		
		createComponents();
		
		buildComponents();
		
		addActionListeners();
		
		addElements();
	}
	/**createComponents
	* creates components and gives them
	* default values.
	* J.B.
	**/
	public void createComponents()
	{
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();// geting size of screen
		m_ScreenWidth = gd.getDisplayMode().getWidth();
		m_ScreenHeight = gd.getDisplayMode().getHeight();
		
		m_Background_L = new JLabel(loadBackround());
		
		m_GameSetUpFrame = new JFrame("Game Set Up");
		
		m_MenuButton_P = new JPanel();
		
		m_StartNewGame_B = new JButton(loadButton("StartANewGameButton"));
		m_LoadAGame_B = new JButton(loadButton("LoadAGameButton"));
		m_BackToMenu_B = new JButton(loadButton("BackToMainMenuButton"));
	}
	/**buildComponents
	* set up components and there attributes.
	* J.B.
	**/
	public void buildComponents()
	{
		m_GameSetUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_GameSetUpFrame.add(m_Background_L);
		m_Background_L.setLayout(new FlowLayout());
		
		m_GameSetUpFrame.setUndecorated(true);
        m_GameSetUpFrame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		m_GameSetUpFrame.setLayout(new BorderLayout());
		m_GameSetUpFrame.setSize(new Dimension(m_ScreenWidth,m_ScreenHeight));
		
		m_MenuButton_P.setLayout(new FlowLayout());
		
		m_BackToMenu_B.setMargin(new Insets(0,0,0,0));
		m_StartNewGame_B.setMargin(new Insets(0,0,0,0));
		m_LoadAGame_B.setMargin(new Insets(0,0,0,0));
		
		m_BackToMenu_B.setActionCommand("BackToMenu");
		m_StartNewGame_B.setActionCommand("StartANewGame");
		m_LoadAGame_B.setActionCommand("LoadAGame");
		
	}
	/**addElements
	* add components to panels and
	* adds panels to Frame
	* J.B.
	**/
	public void addElements()
	{
		m_Background_L.add(m_StartNewGame_B);
		m_Background_L.add(m_LoadAGame_B);
		m_Background_L.add(m_BackToMenu_B);
		
		m_GameSetUpFrame.add(m_Background_L);
		
		m_GameSetUpFrame.pack();
		
		m_GameSetUpFrame.setVisible(true);
	}
	
	/**addActionListeners
	* adds ActionListener, which wait till
	* an action is Performed then sends 
	* a event to the type of listener.
	* J.B.
	**/
	private void addActionListeners()
	{
		m_StartNewGame_B.addActionListener(new ButtonListener());
		m_BackToMenu_B.addActionListener(new ButtonListener());
		m_LoadAGame_B.addActionListener(new ButtonListener());
	}

	/**Listeners
	* Once an event occurs the program goes here
	* and decides what to do with each event.
	*
	*@peram MenuPanel.
	*@peram nothing.
	* J.B.
	**/
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String command = event.getActionCommand();
			switch(command)
			{
				case "LoadAGame":LoadGameWindow gameMenu1 = new LoadGameWindow(m_GameSetUpFrame);m_MenuFrame.setVisible(false);
					break;
				case "BackToMenu": m_MenuFrame.setVisible(true); m_GameSetUpFrame.dispose();
					break;
				case "StartANewGame":StartNewGameWindow gameMenu2 = new StartNewGameWindow(m_GameSetUpFrame);m_MenuFrame.setVisible(false);
					break;
			}
		}  
	}
	
	/**loadImg
	* loads image from file.
	* J.B.
	**/
	private Icon loadButton(String name)
	{
		String path = "";
		path = System.getProperty("user.dir");
		path = path.replace('\\','/');
		path = path.replaceAll("Source", "Assets/GUI/MenuButtons/" + name + ".png");

		try 
		{
		Image img = ImageIO.read(new File(path));
		return new ImageIcon(img);
		
		} catch (IOException ex) 
		{
			System.out.println("FIle Not Found\nFile Path: " + path);
		}
			return null;
	}
	
	private ImageIcon loadBackround()
	{
		String path = "";
		path = System.getProperty("user.dir");
		path = path.replace('\\','/');
		path = path.replaceAll("Source", "Assets/GUI/MenuImages/MenuBG.jpg");
		
		try 
		{
			Image img = ImageIO.read(new File(path));
			Image newimg = img.getScaledInstance(m_ScreenWidth, m_ScreenHeight,  java.awt.Image.SCALE_SMOOTH);
			return new ImageIcon(newimg);
		
		} catch (IOException ex) 
		{
			System.out.println("FIle Not Found\nFile Path: " + path);
		}
			return null;
	}
}
