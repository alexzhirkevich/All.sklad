package ui.menu;

import ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ParkingMenuBar extends JMenuBar {

	private final JFrame frame;
	private final FileMenu fileMenu;
	private final ViewMenu viewMenu;
	private final HelpMenu helpMenu;

	public ParkingMenuBar(final MainFrame frame) {
		this.frame = frame;
		fileMenu = new FileMenu(frame);
		viewMenu = new ViewMenu(frame);
		helpMenu = new HelpMenu(frame);
		add(fileMenu);
		add(viewMenu);
		add(helpMenu);
	}

	public File getSelectedFile(){
		return fileMenu.getSelectedFile();
	}

	public Font getSelectedFont(){
		return viewMenu.getSelectedFont();
	}

	@Override
	public void setFont(Font font) {
		super.setFont(font);
		if (fileMenu!=null)
			fileMenu.setFont(font);
		if (viewMenu!=null)
			viewMenu.setFont(font);
		if (helpMenu!=null)
			helpMenu.setFont(font);
	}
}
