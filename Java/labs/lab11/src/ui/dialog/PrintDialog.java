package ui.dialog;

import parking.buffer.ParkingIO;
import ui.MainFrame;

import javax.swing.*;
import javax.swing.plaf.basic.BasicPanelUI;
import javax.swing.plaf.multi.MultiPanelUI;
import javax.swing.plaf.synth.SynthPanelUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PrintDialog extends JDialog {

	private PrintDialog instance;

	private final MainFrame frame;
	private int borderSize;
	private boolean enabled = false;


	private final JLabel labelSorted = new JLabel("Сортировать");
	private final JLabel labelName = new JLabel("Имя");
	private final JLabel labelNumber = new JLabel("Номер");
	private final JLabel labelDate = new JLabel("Дата");
	private final JLabel labelStraight = new JLabel("Возрастание");
	private final JLabel labelReversed = new JLabel("Убывание");
	private final JLabel labelOrder = new JLabel("Порядок");
	private final JLabel labelKey = new JLabel("Ключ");

	private final JCheckBox btnSort = new JCheckBox();
	private final JRadioButton btnName = new JRadioButton();
	private final JRadioButton btnNumber = new JRadioButton();
	private final JRadioButton btnDate = new JRadioButton();
	private final JRadioButton btnOrderStraight = new JRadioButton();
	private final JRadioButton btnOrderReversed = new JRadioButton();

	private JTextArea textArea = new JTextArea();

	private final JButton btnOK = new JButton("OK");
	private final JButton btnCancel = new JButton("Cancel");

	public PrintDialog(MainFrame frame){
		super();
		setTitle("Печать");
		instance = this;
		this.frame = frame;
		borderSize = frame.getFont().getSize();
		setBounds(100,300,borderSize*70,borderSize*20);
		setMinimumSize(getSize());
		setupControls();

		enableBtns(false);
	}

	private void setupControls(){
		JPanel mainMainPanel = new JPanel();
		mainMainPanel.setLayout(new GridLayout(1,2));
		JPanel mainpanel = new JPanel();
		mainpanel.setBorder(BorderFactory.createEmptyBorder(borderSize/2,borderSize/2,borderSize/2,borderSize/2));
		mainpanel.setLayout(new BoxLayout(mainpanel,BoxLayout.PAGE_AXIS));

		JPanel first = new JPanel();
		first.setLayout(new GridLayout(1,2));
		first.add(labelSorted);
		first.add(btnSort,LEFT_ALIGNMENT);
		first.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));

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
		third.setLayout(new GridLayout(2,2));
		third.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));;
		bg2.add(btnOrderStraight);
		bg2.add(btnOrderReversed);

		third.add(labelStraight);
		third.add(btnOrderStraight);
		third.add(labelReversed);
		third.add(btnOrderReversed);

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

		mainpanel.add(first);
		mainpanel.add(new JSeparator());
		mainpanel.add(Box.createGlue());
		mainpanel.add(labelKey);
		mainpanel.add(second);
		mainpanel.add(new JSeparator());
		mainpanel.add(labelOrder);
		mainpanel.add(third);
		mainpanel.add(new JSeparator());
		mainpanel.add(four);
		mainMainPanel.add(mainpanel);

		textArea.setEditable(false);
		textArea.setBackground(Color.lightGray);
		mainMainPanel.add(textArea);
		add(mainMainPanel);
	}

	private void enableBtns(boolean active){
		enabled = active;
		btnDate.setEnabled(active);
		btnName.setEnabled(active);
		btnNumber.setEnabled(active);
		btnOrderStraight.setEnabled(active);
		btnOrderReversed.setEnabled(active);

		btnName.setSelected(active);
		btnOrderStraight.setSelected(active);
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
			if (labelSorted != null)
				labelSorted.setFont(f);
			if (labelName != null)
				labelName.setFont(f);
			if (labelNumber != null)
				labelNumber.setFont(f);
			if (labelDate != null)
				labelDate.setFont(f);
			if (labelStraight != null)
				labelStraight.setFont(f);
			if (labelReversed != null)
				labelReversed.setFont(f);
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
		}
	}

	class OkAction extends AbstractAction{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				textArea.setText("");
				if (!instance.enabled) {
					ParkingIO.printFile(textArea);
					return;
				}
				boolean reversed = false;
				String key;
				String order;
				if (btnName.isSelected())
					key = "name";
				else if(btnNumber.isSelected())
					key = "number";
				else
					key = "date";
				if (btnOrderReversed.isSelected())
					reversed = true;

				ParkingIO.printFile(textArea,key,reversed);

			}catch (Exception ee){ ee.printStackTrace();}
		}
	}
}
