package ui.dialog;

import parking.buffer.BufferObject;
import parking.buffer.ParkingException;
import parking.buffer.ParkingIO;
import parking.lang.Car;
import parking.lang.Note;
import ui.MainFrame;
import ui.menu.ParkingMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppendDialog extends JDialog {

	private final MainFrame frame;
	private int borderSize;
	private boolean zipped = false;
	private final JTextField fio = new JTextField("", 40);
	private final JTextField carNumber = new JTextField("", 9);
	private final JTextField dateStart = new JTextField("", 10);
	private final JTextField dateEnd = new JTextField("", 10);
	private final JTextField price = new JTextField("", 10);
	private final JCheckBox zip = new JCheckBox();
	private final JButton ok = new JButton("Ok");
	private final JButton cancel = new JButton("Cancel");
	private final JLabel labelNumber = new JLabel("Номер машины");
	private final JLabel labelPrice = new JLabel("Стоимость парковки");
	private final JLabel labelFio = new JLabel("Фамилия Имя");
	private final JLabel labelDateStart = new JLabel("Дата начала");
	private final JLabel labelDateEnd = new JLabel("Дата конца");
	private final JLabel labelZip = new JLabel("Архив");

	public AppendDialog(final MainFrame frame){
		setTitle("Добавить запись");
		this.frame = frame;
		borderSize = (int)(((ParkingMenuBar)frame.getJMenuBar()).getSelectedFont().getSize()*0.7);
		setBounds(300,300,borderSize*50,borderSize*30);
		setMinimumSize(getSize());
		getRootPane().setBorder(BorderFactory.createEmptyBorder(borderSize,borderSize,borderSize,borderSize));
		createControls();
	}

	private void createControls(){
		JPanel first = new JPanel();
		first.setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
		first.setLayout(new GridLayout(1,2));
		first.add(labelFio);
		first.add(fio);

		JPanel second = new JPanel();
		second.setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
		second.setLayout(new GridLayout(1,2));
		second.add(labelNumber);
		second.add(carNumber);

		JPanel third = new JPanel();
		third.setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
		third.setLayout(new GridLayout(1,2));
		third.add(labelDateStart);
		third.add(dateStart);

		JPanel third2 = new JPanel();
		third2.setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
		third2.setLayout(new GridLayout(1,2));
		third2.add(labelDateEnd);
		third2.add(dateEnd);

		JPanel third3 = new JPanel();
		third3.setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
		third3.setLayout(new GridLayout(1,2));
		third3.add(labelPrice);
		third3.add(price);

		JPanel four = new JPanel();
		four.setBorder(BorderFactory.createEmptyBorder(borderSize/2, borderSize/2, borderSize/2, borderSize/2));
		four.setLayout(new GridLayout(1,4));
		four.add(labelZip);
		four.add(zip);
		four.add(ok);
		four.add(cancel);

		zip.addActionListener(e -> zipped = !zipped);

		cancel.addActionListener(e -> setVisible(false));

		ok.addActionListener(new OkAction());

		setLayout(new GridLayout(6, 1));
		add(first);
		add(second);
		add(third);
		add(third2);
		add(third3);
		add(four);
	}

	@Override
	public void setFont(Font f) {
		super.setFont(f);
		if (f!=null) {
			borderSize = f.getSize();
			if (fio != null)
				fio.setFont(f);
			if (carNumber != null)
				carNumber.setFont(f);
			if (dateStart != null)
				dateStart.setFont(f);
			if (ok != null)
				ok.setFont(f);
			if (labelDateStart != null)
				labelDateStart.setFont(f);
			if (labelFio != null)
				labelFio.setFont(f);
			if (labelNumber != null)
				labelNumber.setFont(f);
			if (labelZip != null)
				labelZip.setFont(f);
			if (dateEnd != null)
				dateEnd.setFont(f);
			if (labelDateEnd != null)
				labelDateEnd.setFont(f);
			if (price != null)
				price.setFont(f);
			if (labelPrice != null)
				labelPrice.setFont(f);
			if (cancel != null)
				cancel.setFont(f);
		}
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		setFont(frame.getFont());
	}

	class OkAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (fio.getText() == null || fio.getText().equals("")){
				JOptionPane.showMessageDialog(fio,"ФИО не указано","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if (carNumber.getText() == null || carNumber.getText().equals("")){
				JOptionPane.showMessageDialog(fio,"Номер не указан","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if (dateStart.getText() == null || dateStart.getText().equals("") || dateEnd.getText() == null || dateEnd.getText().equals("")){
				JOptionPane.showMessageDialog(fio,"Дата не указана","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}

			SimpleDateFormat dateFormat = new SimpleDateFormat();
			Date start;
			Date end;
			try{
				start = dateFormat.parse(dateStart.getText());
				end = dateFormat.parse(dateEnd.getText());
			}catch (ParseException ee){
				JOptionPane.showMessageDialog(fio,"Неверный формат даты.\nПример:16.12.2020, 15:14","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try{
				Double.parseDouble(price.getText());
			}catch (NumberFormatException eee){
				JOptionPane.showMessageDialog(fio,"Цена парковки указана неверно","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				Car car = new Car(carNumber.getText(),fio.getText());
				Note note = new Note(car,start,end,Double.parseDouble(price.getText()));
				ParkingIO.appendFile(new BufferObject(note,zipped));
				JOptionPane.showMessageDialog(fio,"Запись добавлена","Success",JOptionPane.INFORMATION_MESSAGE);
				frame.setButtonsEnabled(true);
			} catch (ParkingException ee) {
				JOptionPane.showMessageDialog(fio,"Номер должен быть уникальным","Error",JOptionPane.ERROR_MESSAGE);
			}catch (Exception ignore){
				System.err.println(ignore);
			}
		}
	}
}
