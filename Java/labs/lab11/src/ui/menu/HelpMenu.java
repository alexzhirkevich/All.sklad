package ui.menu;

import javafx.scene.control.skin.LabeledSkinBase;
import ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HelpMenu extends JMenu {

	private final MainFrame frame;
	private JMenuItem about;

	public HelpMenu(MainFrame frame){
		super("Help");

		this.frame = frame;

		about = new JMenuItem("About");

		about.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame,
						"Жиркевич Александр Юрьевич" +
						"\nБгу, ФПМИ" +
						"\n2 курс, 10 группа","About",JOptionPane.INFORMATION_MESSAGE);
			}
		});

		add(about);
	}

	@Override
	public void setFont(Font font) {
		super.setFont(font);
		if (about!=null)
			about.setFont(font);
	}
}
