package ui;

import ui.dialog.AppendDialog;
import ui.dialog.DeleteDialog;
import ui.dialog.FindDialog;
import ui.dialog.PrintDialog;
import ui.menu.ParkingMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainFrame extends JFrame {

	private MainFrame instance;
	protected final ParkingMenuBar menuBar;
	protected final JButton btnAppend;
	protected final JButton btnFind;
	protected final JButton btnPrint;
	protected final JButton btnDelete;
	private Font font = new Font(Font.SANS_SERIF,Font.PLAIN,20);

	public MainFrame(){
		super("Parking");
		setJMenuBar(menuBar = new ParkingMenuBar(this));
		instance = this;
		btnAppend = new JButton("Добавить");
		btnFind = new JButton("Найти");
		btnPrint = new JButton("Распечатать");
		btnDelete = new JButton("Удалить");
		setButtonsEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400,300,400,300);
		setupControls();
	}

	private void setupControls() {
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.darkGray);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		mainPanel.setLayout(new GridLayout(2,2));
		mainPanel.add(btnAppend);
		mainPanel.add(btnPrint);
		mainPanel.add(btnFind);
		mainPanel.add(btnDelete);
		getContentPane().add(mainPanel);


		btnAppend.addActionListener(e ->  new AppendDialog(this).setVisible(true) );

		btnPrint.addActionListener(e -> new PrintDialog(this).setVisible(true));

		btnFind.addActionListener( e -> new FindDialog(this).setVisible(true));

		btnDelete.addActionListener(e -> new DeleteDialog(this).setVisible(true));
	}

	public void setButtonsEnabled(boolean enabled){
		btnAppend.setEnabled(enabled);
		btnFind.setEnabled(enabled);
		btnPrint.setEnabled(enabled);
		btnDelete.setEnabled(enabled);
	}

	@Override
	public void setFont(Font font){
		font = font;
		if (btnAppend!=null)
			btnAppend.setFont(font);
		if (btnFind!=null)
			btnFind.setFont(font);
		if (btnPrint!=null)
			btnPrint.setFont(font);
		if (btnDelete!=null)
			btnDelete.setFont(font);
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		setFont(font);
	}

	public void enableAppend(boolean en){
		btnAppend.setEnabled(en);
	}

	@Override
	public Font getFont() {
		return font;
	}
}
