package ui.dialog;

import parking.buffer.ParkingException;
import parking.buffer.ParkingIO;
import ui.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

public class DeleteDialog extends JDialog {

	private final MainFrame frame;

	int borderSize;
	boolean isEnabled = true;
	private final JLabel labelFull = new JLabel("Весь файл");
	private final JLabel labelName = new JLabel("Имя");
	private final JLabel labelNumber = new JLabel("Номер");
	private final JLabel labelDate = new JLabel("Дата");
	private final JLabel labelKey = new JLabel("Ключ");
	private final JTextArea areaValue = new JTextArea(1, 1);

	private final JRadioButton btnName = new JRadioButton();
	private final JRadioButton btnNumber = new JRadioButton();
	private final JRadioButton btnDate = new JRadioButton();

	private final JButton btnOK = new JButton("OK");
	private final JButton btnCancel = new JButton("Cancel");

	JCheckBox full = new JCheckBox();

	public DeleteDialog(MainFrame frame) {
		super();
		setTitle("Удалить");
		this.frame = frame;
		borderSize = frame.getFont().getSize();
		setBounds(300, 300, 20 * borderSize, 15 * borderSize);
		setMinimumSize(getSize());
		setupControls();
	}

	void setupControls() {

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));

		JPanel first = new JPanel();
		first.setLayout(new GridLayout(1, 2));
		first.add(labelFull);
		first.add(full);

		full.addActionListener(e -> enableBtns(!isEnabled));

		JPanel second = new JPanel();
		second.setLayout(new GridLayout(3, 2));
		second.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		second.add(labelName);
		second.add(btnName);
		second.add(labelNumber);
		second.add(btnNumber);
		second.add(labelDate);
		second.add(btnDate);

		ButtonGroup bg = new ButtonGroup();
		bg.add(btnName);
		bg.add(btnNumber);
		bg.add(btnDate);

		JPanel third = new JPanel();
		third.setLayout(new GridLayout(1, 2));
		third.setBorder(new EmptyBorder(10, 0, 0, 0));

		third.add(btnOK);
		third.add(btnCancel);

		btnCancel.addActionListener(e -> setVisible(false));
		btnOK.addActionListener(new OkAction());

		areaValue.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		areaValue.setRows(1);
		areaValue.setText("Ключ");
		areaValue.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
		areaValue.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				areaValue.setText("");
				areaValue.setCaretColor(Color.black);
			}

			@Override
			public void focusLost(FocusEvent e) {

			}
		});
		areaValue.setSize(areaValue.getWidth(), borderSize);

		mainPanel.add(first);
		mainPanel.add(new JSeparator());
		mainPanel.add(labelKey);
		mainPanel.add(second);
		mainPanel.add(areaValue);
		mainPanel.add(new JSeparator());
		mainPanel.add(third);

		add(mainPanel);
		setEnabled(true);
		btnName.setSelected(true);
	}

	public void enableBtns(boolean en) {
		isEnabled = en;
		btnDate.setEnabled(en);
		btnName.setEnabled(en);
		btnNumber.setEnabled(en);
		areaValue.setEnabled(en);
	}

	@Override
	public void setFont(Font f) {
		super.setFont(f);
		if (f != null) {
			borderSize = f.getSize();
			if (labelDate != null)
				labelDate.setFont(f);
			if (labelFull != null)
				labelFull.setFont(f);
			if (labelKey != null)
				labelKey.setFont(f);
			if (labelName != null)
				labelName.setFont(f);
			if (labelNumber != null)
				labelNumber.setFont(f);
			if (areaValue != null) {
				areaValue.setFont(f);
				areaValue.setSize(areaValue.getWidth(), borderSize);
			}
		}
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		setFont(frame.getFont());
	}

	class OkAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (!isEnabled) {
				ParkingIO.deleteFile();
				JOptionPane.showMessageDialog(areaValue, "Файл с данными очищен", "Success", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				return;
			}
			String key;
			String value = areaValue.getText();
			if (value == null || value.equals("")) {
				JOptionPane.showMessageDialog(areaValue, "Не указан ключ", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (btnName.isSelected()) {
				key = "name";
			} else if (btnNumber.isSelected())
				key = "number";
			else
				key = "date";

			try {
				ParkingIO.ParkingResult res = ParkingIO.deleteFile(key, value);
				if (res == ParkingIO.ParkingResult.OK) {
					JOptionPane.showMessageDialog(areaValue, "Запись удалена", "Success", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
				} else if (res == ParkingIO.ParkingResult.KeyNotFound) {
					JOptionPane.showMessageDialog(areaValue, "Ключ не найден", "Error", JOptionPane.ERROR_MESSAGE);
				}

			} catch (Exception ignore) {
				ignore.printStackTrace();
			}
		}
	}
}