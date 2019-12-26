import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class BusForm {

	private JFrame frmBus;
	Bus bus;
	BusPanel panel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					BusForm window = new BusForm();
					window.frmBus.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BusForm() {
		initialize();
	}

	private void initialize() {
		frmBus = new JFrame();
		frmBus.setTitle("Bus");
		frmBus.setBounds(100, 100, 900, 500);
		frmBus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBus.getContentPane().setLayout(null);
		
		JButton buttonCreate = new JButton("\u0421\u043E\u0437\u0434\u0430\u0442\u044C");
		buttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BusPanel.flag = 0;
	            bus = new Bus(100 + (int) (Math.random() * 300), 1000 + (int) (Math.random() * 2000),
Color.RED, Color.YELLOW, true, true);
	            bus.SetPosition(10 + (int) (Math.random() * 100), 10 + (int) (Math.random() * 100),
frmBus.getWidth() -100 , frmBus.getHeight()-100);
	            panel = new BusPanel();
	    		panel.setBounds(10, 11, frmBus.getWidth(), frmBus.getHeight());
	    		frmBus.getContentPane().add(panel);
	    		panel.position(bus);
	            frmBus.repaint();
			}
		});
		buttonCreate.setBounds(10, 11, 89, 23);
		frmBus.getContentPane().add(buttonCreate);
		
		JButton buttonUp = new JButton("");
		buttonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bus.MoveTransport(Bus.Direction.Up);
				frmBus.repaint();
			}
		});
		buttonUp.setForeground(new Color(255, 255, 0));
		buttonUp.setIcon(new ImageIcon("C:\\Users\\\u0415\u0432\u0433\u0435\u043D\u0438\u0439\\Desktop\\3.png"));
		buttonUp.setBounds(808, 382, 30, 27);
		frmBus.getContentPane().add(buttonUp);
		
		JButton buttonRight = new JButton("");
		buttonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bus.MoveTransport(Bus.Direction.Right);
				frmBus.repaint();
			}
		});
		buttonRight.setIcon(new ImageIcon("C:\\Users\\\u0415\u0432\u0433\u0435\u043D\u0438\u0439\\Desktop\\4.png"));
		buttonRight.setBounds(844, 420, 30, 30);
		frmBus.getContentPane().add(buttonRight);
		
		JButton buttonDown = new JButton("");
		buttonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bus.MoveTransport(Bus.Direction.Down);
				frmBus.repaint();
			}
		});
		buttonDown.setIcon(new ImageIcon("C:\\Users\\\u0415\u0432\u0433\u0435\u043D\u0438\u0439\\Desktop\\1.png"));
		buttonDown.setBounds(808, 420, 30, 30);
		frmBus.getContentPane().add(buttonDown);
		
		JButton buttonLeft = new JButton("");
		buttonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bus.MoveTransport(Bus.Direction.Left);
				frmBus.repaint();
			}
		});
		buttonLeft.setIcon(new ImageIcon("C:\\Users\\\u0415\u0432\u0433\u0435\u043D\u0438\u0439\\Desktop\\2.png"));
		buttonLeft.setBounds(772, 420, 30, 30);
		frmBus.getContentPane().add(buttonLeft);
	}
}
