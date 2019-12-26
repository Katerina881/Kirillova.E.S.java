import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
	private JButton createBus;
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
		frame.getContentPane().add(createBus);
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
		
		createBus = new JButton("\u0417\u0430\u043A\u0430\u0437\u0430\u0442\u044C");
		createBus.setBounds(564, 249, 314, 62);
		
		createBus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConfigBusForm config = new ConfigBusForm(new CarDelegate() {
					@Override
					public void Invoke(IBus bus) {
						if (bus != null && list.getSelectedIndex() > -1) {
							int place = levelPark.getParkingBus(list.getSelectedIndex()).Add(bus);
							if (place > -1)
								Draw();
							else
								JOptionPane.showMessageDialog(null,"regrh");
						}
					}
				});
				config.getFrame().setVisible(true);
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
