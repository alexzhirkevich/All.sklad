package ui.menu;

import parking.buffer.ParkingIO;
import ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class FileMenu extends JMenu {

	private MainFrame frame;
	private JFileChooser jFileChooser;
	private JMenuItem openFile;
	private JMenuItem saveFile;
	private JMenuItem quit;

	public FileMenu(MainFrame frame){
		super("File");
		this.frame = frame;
		jFileChooser = new 	JFileChooser();
		add(openFile = createOpenFile());
		add(saveFile = createSaveFile());
		add(quit = createQuit());
	}

	private JMenuItem createQuit(){
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		return quit;
	}

	private JMenuItem createOpenFile(){
		JMenuItem file = new JMenuItem("Open");

		file.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jFileChooser != null){
					jFileChooser.showOpenDialog(file);
				}
				if (jFileChooser.getSelectedFile()!=null){
					frame.setButtonsEnabled(true);
					ParkingIO.setPath(getSelectedFile().getAbsolutePath());
				}
			}
		});

		return file;
	}

	private JMenuItem createSaveFile(){
		JMenuItem file = new JMenuItem("New");

		file.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jFileChooser != null){
					jFileChooser.showSaveDialog(file);
				}
				if (jFileChooser.getSelectedFile()!=null){
					frame.enableAppend(true);
					ParkingIO.setPath(jFileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});

		return file;
	}

	public File getSelectedFile(){
		return jFileChooser!=null?jFileChooser.getSelectedFile():null;
	}

	@Override
	public void setFont(Font font) {
		super.setFont(font);
		if (jFileChooser!=null)
			jFileChooser.setFont(font);
		if (openFile!=null)
			openFile.setFont(font);
		if (saveFile!=null)
			saveFile.setFont(font);
		if (quit!=null)
			quit.setFont(font);
	}
}
