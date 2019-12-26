import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class ParkingBusForm {

	private JFrame frame;
	BusPanel panel;
	ParkingBusPanel parkpanel;
	private JTextField inputText;
	IForm frm;
	JList list;
	private JPanel panel1;
	private JButton pakingBus;
	private JButton parkingCommonBus;
	BigParkingBus levelPark;
	private final int countLevel = 5;
	private HashSet<IBus> hashSetBus = new HashSet<IBus>();
	public ParkingBus<IBus, IForm> parkingBus;

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

	public ParkingBusForm() {
		initialize();
		levelPark = new BigParkingBus(countLevel, parkpanel.getWidth(), parkpanel.getHeight());
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(parkpanel);
		frame.getContentPane().add(panel1);
		frame.getContentPane().add(pakingBus);
		frame.getContentPane().add(parkingCommonBus);
		list = new JList();
		DefaultListModel dlm = new DefaultListModel();
		for (int i = 0; i < countLevel; i++)
			dlm.addElement("Level " + (i+1));
		list.setBounds(569, 13, 309, 154);
		frame.getContentPane().add(list);
		list.setModel(dlm);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 1) {
		        	Draw();
		        }
			}
		});
	}
	
	private void Draw() {
		parkpanel.setParkingBus(levelPark.getParkingBus(list.getSelectedIndex()));
		parkpanel.validate();
		parkpanel.repaint();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100,  908, 646);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		parkpanel = new ParkingBusPanel();
		parkpanel.setBounds(0, 0, 557, 599);
		
		parkingCommonBus = new JButton("\u041F\u0440\u0438\u043F\u0430\u0440\u043A\u043E\u0432\u0430\u0442\u044C \u0430\u0432\u0442\u043E\u0431\u0443\u0441");
		parkingCommonBus.setBounds(564, 180, 314, 62);
		parkingCommonBus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() > -1) {
					JColorChooser colorChooser = new JColorChooser();
					Color mainColor = colorChooser.showDialog(new Component() {}, "Color", Color.BLACK);
					CommonBus bus = new CommonBus(15, 15, mainColor);
					levelPark.getParkingBus(list.getSelectedIndex()).Add(bus, frm);
					Draw();
				}
			}
		});
		pakingBus = new JButton("\u041F\u0440\u0438\u043F\u0430\u0440\u043A\u043E\u0432\u0430\u0442\u044C \u0434\u0432\u0443\u0445\u044D\u0442\u0430\u0436\u043D\u044B\u0439 \u0430\u0432\u0442\u043E\u0431\u0443\u0441\r\n");
		pakingBus.setBounds(564, 249, 314, 68);
		pakingBus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() > -1) {
					JColorChooser colorChooser = new JColorChooser();
					Color mainColor = colorChooser.showDialog(new Component() {}, "Color", Color.BLACK);
					Color dopColor = colorChooser.showDialog(new Component() {}, "Color", Color.BLACK);
					Bus bus = new Bus(15,15, mainColor, dopColor, true, true);
					levelPark.getParkingBus(list.getSelectedIndex()).Add(bus, frm);
		            Draw();
				}
			}
		});
		panel1 = new JPanel();
		panel1.setBounds(564, 324, 314, 262);
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
				if (list.getSelectedIndex() > -1) {
					if (inputText.getText() != "") {
			            IBus bus = levelPark.getParkingBus(list.getSelectedIndex()).Remove(Integer.parseInt(inputText.getText()));
			            if (bus != null){
			                bus.SetPosition(panel.getWidth() / 2 - 30, panel.getHeight() / 2, panel.getWidth(),panel.getHeight());
			                panel.position(bus);
			                hashSetBus.add(bus);
			            }
			            else {
			            	panel.position(null);
			            }
			            panel.validate();
			            panel.repaint();
			            Draw();
			        }
				}
			}
		});
		getBus.setBounds(106, 68, 117, 25);
		panel1.add(getBus);
	}
}
