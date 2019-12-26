import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import javafx.scene.control.ChoiceDialog;

public class ParkingBusForm {

	private JFrame frame;
	BusPanel panel;
	ParkingBusPanel parkpanel;
	private JTextField inputText;
	IForm frm;
	
	public ParkingBus<IBus, IForm> parkingBus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParkingBusForm window = new ParkingBusForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ParkingBusForm() {
		initialize();
		parkingBus = new ParkingBus<IBus, IForm>(15, parkpanel.getWidth(), parkpanel.getHeight());
		parkpanel.setParkingBus(parkingBus);
	}
	private void Draw() {
		parkpanel.validate();
		parkpanel.repaint();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100,  908, 646);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		parkpanel = new ParkingBusPanel();
		JButton parkingCommonBus = new JButton("\u041F\u0440\u0438\u043F\u0430\u0440\u043A\u043E\u0432\u0430\u0442\u044C \u0430\u0432\u0442\u043E\u0431\u0443\u0441");
		JButton pakingBus = new JButton("\u041F\u0440\u0438\u043F\u0430\u0440\u043A\u043E\u0432\u0430\u0442\u044C \u0434\u0432\u0443\u0445\u044D\u0442\u0430\u0436\u043D\u044B\u0439 \u0430\u0432\u0442\u043E\u0431\u0443\u0441\r\n");
		parkingCommonBus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JColorChooser colorChooser = new JColorChooser();
				Color mainColor = JColorChooser.showDialog(new Component() {}, "Color", Color.BLACK);
				CommonBus bus = new CommonBus(15, 15, mainColor);
				parkingBus.Add(bus, frm);
				Draw();
			}
		});
		
		pakingBus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JColorChooser colorChooser = new JColorChooser();
				Color mainColor = JColorChooser.showDialog(new Component() {}, "Color", Color.BLACK);
				Color dopColor = JColorChooser.showDialog(new Component() {}, "Color", Color.BLACK);
				Bus bus = new Bus(15,15, mainColor, dopColor, true, true);
				parkingBus.Add(bus, frm);
				Draw();
			}
		});
		JPanel panel1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(parkpanel, GroupLayout.PREFERRED_SIZE, 567, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel1, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
						.addComponent(parkingCommonBus, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
						.addComponent(pakingBus, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(parkingCommonBus, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pakingBus, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
							.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE))
						.addComponent(parkpanel, GroupLayout.PREFERRED_SIZE, 599, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel1.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setEnabled(false);
		textPane.setEditable(false);
		textPane.setText("\u041C\u0435\u0441\u0442\u043E :");
		textPane.setBounds(43, 33, 66, 22);
		panel1.add(textPane);
		
		inputText = new JTextField();
		inputText.setBounds(121, 33, 81, 22);
		panel1.add(inputText);
		inputText.setColumns(10);
		
		panel = new BusPanel();
		panel.setBounds(12, 122, 290, 127);
		panel1.add(panel);
		
		JButton getBus = new JButton("\u0417\u0430\u0431\u0440\u0430\u0442\u044C");
		getBus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (inputText.getText() != "")
		        {
		            IBus car = parkingBus.Remove(Integer.parseInt(inputText.getText()));
		            if (car != null){
		                car.SetPosition(panel.getWidth() / 2 - 30, panel.getHeight() / 2, panel.getWidth(),panel.getHeight());
		                panel.position(car);
		            }
		            else {
		            	panel.position(null);
		            }
		            panel.validate();
		            panel.repaint();
		            Draw();
		        }
			}
		});
		getBus.setBounds(106, 68, 117, 25);
		panel1.add(getBus);
		frame.getContentPane().setLayout(groupLayout);
	}

}
