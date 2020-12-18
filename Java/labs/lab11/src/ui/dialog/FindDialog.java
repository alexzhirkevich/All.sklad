package ui.dialog;

import parking.buffer.ParkingIO;
import parking.index.KeyComp;
import parking.index.KeyCompReverse;
import parking.lang.Note;
import ui.MainFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Comparator;

public class FindDialog extends JDialog {

	private FindDialog instance;

	private final MainFrame frame;
	private int borderSize;
	private boolean enabled = false;


	private final JLabel labelName = new JLabel("Имя");
	private final JLabel labelNumber = new JLabel("Номер");
	private final JLabel labelDate = new JLabel("Дата");
	private final JLabel labelMore = new JLabel("Больше");
	private final JLabel labelLess = new JLabel("Меньше");
	private final JLabel labelEq = new JLabel("Равно");
	private final JLabel labelOrder = new JLabel("Найти");
	private final JLabel labelKey = new JLabel("Ключ");

	private final JCheckBox btnSort = new JCheckBox();
	private final JRadioButton btnName = new JRadioButton();
	private final JRadioButton btnNumber = new JRadioButton();
	private final JRadioButton btnDate = new JRadioButton();
	private final JRadioButton btnMore = new JRadioButton();
	private final JRadioButton btnLess = new JRadioButton();
	private final JRadioButton btnEq = new JRadioButton();

	private JTextArea textArea = new JTextArea();

	private JTextArea keyArea = new JTextArea();


	private final JButton btnOK = new JButton("OK");
	private final JButton btnCancel = new JButton("Cancel");

	public FindDialog(MainFrame frame){
		super();
		setTitle("Поиск");
		instance = this;
		this.frame = frame;
		borderSize = frame.getFont().getSize();
		setBounds(100,300,borderSize*70,borderSize*20);
		setMinimumSize(getSize());
		setupControls();
	}

	private void setupControls(){
		JPanel mainMainPanel = new JPanel();
		mainMainPanel.setLayout(new GridLayout(1,2));
		JPanel mainpanel = new JPanel();
		mainpanel.setBorder(BorderFactory.createEmptyBorder(borderSize/2,borderSize/2,borderSize/2,borderSize/2));
		mainpanel.setLayout(new BoxLayout(mainpanel,BoxLayout.PAGE_AXIS));

		btnSort.addActionListener(e -> enableBtns(!enabled));

		JPanel second = new JPanel();
		ButtonGroup bg = new ButtonGroup();
		bg.add(btnName);
		bg.add(btnNumber);
		bg.add(btnDate);
		
		second.setLayout(new GridLayout(3,2));
		second.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		second.add(labelName);
		second.add(btnName);
		second.add(labelNumber);
		second.add(btnNumber);
		second.add(labelDate);
		second.add(btnDate);


		JPanel third = new JPanel();
		ButtonGroup bg2 = new ButtonGroup();
		third.setLayout(new GridLayout(3,2));
		third.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));;
		bg2.add(btnMore);
		bg2.add(btnLess);
		bg2.add(btnEq);

		third.add(labelEq);
		third.add(btnEq);
		third.add(labelMore);
		third.add(btnMore);
		third.add(labelLess);
		third.add(btnLess);

		JPanel four = new JPanel();
		four.setLayout(new GridLayout(1,2));
		four.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		four.add(btnOK);
		four.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		btnOK.addActionListener(new OkAction());

		mainpanel.add(Box.createGlue());
		mainpanel.add(labelKey);
		mainpanel.add(second);
		mainpanel.add(new JSeparator());
		mainpanel.add(labelOrder);
		mainpanel.add(third);
		mainpanel.add(keyArea);
		mainpanel.add(four);

		textArea.setEditable(false);
		textArea.setBackground(Color.lightGray);
		keyArea.setBorder(new LineBorder(Color.blue,2));
		keyArea.setText("Ключ");
		keyArea.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				keyArea.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {

			}
		});

		mainMainPanel.add(mainpanel);
		mainMainPanel.add(textArea);
		add(mainMainPanel);

		btnName.setSelected(true);
		btnEq.setSelected(true);
	}

	private void enableBtns(boolean active){
		enabled = active;
		btnDate.setEnabled(active);
		btnName.setEnabled(active);
		btnNumber.setEnabled(active);
		btnMore.setEnabled(active);
		btnLess.setEnabled(active);
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		setFont(frame.getFont());
	}

	@Override
	public void setFont(Font f) {
		super.setFont(f);
		if (f!=null) {
			borderSize = f.getSize();
			if (labelName != null)
				labelName.setFont(f);
			if (labelNumber != null)
				labelNumber.setFont(f);
			if (labelDate != null)
				labelDate.setFont(f);
			if (labelMore != null)
				labelMore.setFont(f);
			if (labelLess != null)
				labelLess.setFont(f);
			if (labelKey != null)
				labelKey.setFont(f);
			if (labelOrder != null)
				labelOrder.setFont(f);
			if (btnOK != null)
				btnOK.setFont(f);
			if (btnCancel != null)
				btnCancel.setFont(f);
			if (textArea != null)
				textArea.setFont(f);
			if (labelEq!=null)
				labelEq.setFont(f);
			if (keyArea!=null)
				keyArea.setFont(f);
		}
	}

	class OkAction extends AbstractAction{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				textArea.setText("");
				String value = keyArea.getText();
				if (value== null || value.equals("")){
					JOptionPane.showMessageDialog(keyArea,"Не указан ключ","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				boolean reversed = false;
				String key;
				String cmd = "f";
				if (btnName.isSelected())
					key = "name";
				else if(btnNumber.isSelected())
					key = "number";
				else
					key = "date";

				if (btnLess.isSelected())
					cmd+="l";
				else if(btnMore.isSelected())
					cmd+="r";

				String[] args = {cmd,key,value};

				ParkingIO.ParkingResult res;
				if (cmd.equals("f"))
					res = ParkingIO.findByKey(textArea,args);
				else if (cmd.equals("fl"))
					res = ParkingIO.findByKey(textArea,args,new KeyComp());
				else
					res = ParkingIO.findByKey(textArea,args,new KeyCompReverse());

				if (res == ParkingIO.ParkingResult.KeyNotFound){
					JOptionPane.showMessageDialog(keyArea,"Ключ не найден","Error",JOptionPane.ERROR_MESSAGE);

				}

			}catch (Exception ee){ ee.printStackTrace();}
		}
	}
}
