package ui.menu;

import ui.MainFrame;
import ui.dialog.JFontChooser;

import javax.swing.*;
import java.awt.*;

public class ViewMenu extends JMenu {

	private JFontChooser fc;
	private MainFrame frame;
	private JMenu lookAndFeelMenu;
	private JMenuItem fontMenuItem;

	public ViewMenu(final MainFrame frame){
		super("View");
		fc = new JFontChooser();
		this.frame = frame;
		add(lookAndFeelMenu =createLookAndFeel());
		add(fontMenuItem = createFont());
	}

	private JMenu createLookAndFeel() {

		JMenu lookAndFeel = new JMenu("Look and Feel");
		ButtonGroup radiogroup = new ButtonGroup();
		UIManager.LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();

		for (int i = 0; i < plafs.length; i++) {
			String plafName = plafs[i].getName();
			final String plafClassName = plafs[i].getClassName();
			JMenuItem item = lookAndFeel.add(new JRadioButtonMenuItem(plafName));
			item.addActionListener(e -> {
				try {
					Rectangle bound = frame.getBounds();
					UIManager.setLookAndFeel(plafClassName);
					SwingUtilities.updateComponentTreeUI(frame);
					frame.pack();
					frame.setBounds(bound);
				} catch (Exception ex) {
					System.err.println(ex);
				}
			});
			radiogroup.add(item);
		}
		return lookAndFeel;
	}

	private JMenuItem createFont()
	{
		JMenuItem fontMenu = new JMenuItem("Font");
		fc.setSelectedFont(frame.getFont());
		fontMenu.addActionListener(e -> {
			fc.showDialog(fontMenu);
			Font font = fc.getSelectedFont();
			if (font != null){
				frame.setFont(font);
			}
		});

		return fontMenu;
	}

	public Font getSelectedFont(){
		return fc.getSelectedFont();
	}

	@Override
	public void setFont(Font font) {
		super.setFont(font);
		if (lookAndFeelMenu!=null)
			lookAndFeelMenu.setFont(font);
		if (fontMenuItem!=null)
			fontMenuItem.setFont(font);
	}
}
