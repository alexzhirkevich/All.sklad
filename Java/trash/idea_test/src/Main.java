// Класс представления различного внешнего вида кнопок JButton

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Main extends JFrame
{
	private static final long serialVersionUID = 1L;
	public Main() throws IOException {
		super("Интерфейсы кнопок");
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		// Устанавливаем последовательное расположение
		Container container = getContentPane();
		container.setLayout(new FlowLayout( FlowLayout.LEFT, 10, 10));
		// Простая кнопка
		JButton button = new JButton("Обычная кнопка");
		// Подключение слушателей событий
		button.addActionListener(new ListenerAction());
		button.addChangeListener(new ListenerChange());
		// присоединение слушателя прямо на месте
		button.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println("Это событие мы не увидим");
			}});
		container.add(button);
		// Кнопка со значками на все случаи жизни
		button = new JButton();
		button.setIcon        (new ImageIcon("images/copy.png"));
		button.setRolloverIcon(new ImageIcon("images/cut.png" ));
		button.setPressedIcon (new ImageIcon("images/open.png"));
		button.setDisabledIcon(new ImageIcon("images/save.png"));
		// Убираем все ненужные рамки и закраску
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		container.add(button);
		// Кнопка с описанием интерфейса в виде HTML-текста
		button = new JButton("<html><h2><font color=\"yellow\">Синяя кнопка");
		JFrame j =new JFrame();
		j.setBounds(1,1,200,200);
		//byte[] data = fis.readAllBytes();
		Image image = ImageIO.read(new File("img.png"));
		j.setIconImage(image);
		j.s

		// button.setOpaque(true);
		button.setBackground(Color.blue);
		container.add(button);
		// Изменение выравнивания текста и изображения
		button = new JButton("Изменение выравнивания",
				new ImageIcon("images/exit.png"));
		button.setMargin                (new Insets(10, 10, 10, 10));
		button.setVerticalAlignment     (SwingConstants.TOP   );
		button.setHorizontalAlignment   (SwingConstants.RIGHT );
		button.setHorizontalTextPosition(SwingConstants.LEFT  );
		button.setVerticalTextPosition  (SwingConstants.BOTTOM);
		button.setIconTextGap(10);
		// сделаем кнопку большой, чтобы увидеть выравнивание
		button.setPreferredSize(new Dimension(300, 100));
		container.add(button);
		// отключенная кнопка
		button = new JButton("Выключено");
		button.setEnabled(false);
		container.add(button);
		// выводим окно на экран
		setSize(400, 350);
		setVisible(true);
	}
	class ListenerAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Нажатие кнопки! От - "+
					e.getActionCommand() + "\n");
		}
	}
	class ListenerChange implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			// Источник события
			Object src = e.getSource();
			System.out.println("Cообщение о смене состояния объекта : "
					+ src.getClass());
		}
	}
	public static void main(String[] args) throws IOException {
		new Main();
	}
}