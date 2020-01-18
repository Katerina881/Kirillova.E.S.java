import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FrmBus {

	private JFrame frmBus;
	IBus bus;
	BusPanel panel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmBus window = new FrmBus();
					window.frmBus.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmBus() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBus = new JFrame();
		frmBus.setTitle("Bus");
		frmBus.setBounds(100, 100, 900, 500);
		frmBus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBus.getContentPane().setLayout(null);
		
		JButton buttonCreate = new JButton("\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u0434\u0432\u0443\u0445\u044D\u0442\u0430\u0436\u043D\u044B\u0439 \u0430\u0432\u0442\u043E\u0431\u0443\u0441");
		buttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		buttonCreate.setBounds(176, 11, 209, 23);
		frmBus.getContentPane().add(buttonCreate);
		
		JButton buttonUp = new JButton("");
		buttonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bus.MoveTransport(Dir.Direction.Up);
				frmBus.repaint();
			}
		});
		buttonUp.setForeground(new Color(255, 255, 0));
		buttonUp.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\Зелёная_стрелка1.png"));
		buttonUp.setBounds(808, 382, 30, 30);
		frmBus.getContentPane().add(buttonUp);
		
		JButton buttonRight = new JButton("");
		buttonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bus.MoveTransport(Dir.Direction.Right);
				frmBus.repaint();
			}
		});
		buttonRight.setIcon(new ImageIcon("C:\\\\Users\\\\user\\\\Desktop\\\\Зелёная_стрелка4.png"));
		buttonRight.setBounds(844, 420, 30, 30);
		frmBus.getContentPane().add(buttonRight);
		
		JButton buttonDown = new JButton("");
		buttonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bus.MoveTransport(Dir.Direction.Down);
				frmBus.repaint();
			}
		});
		buttonDown.setIcon(new ImageIcon("C:\\\\Users\\\\user\\\\Desktop\\\\Зелёная_стрелка2.png"));
		buttonDown.setBounds(808, 420, 30, 30);
		frmBus.getContentPane().add(buttonDown);
		
		JButton buttonLeft = new JButton("");
		buttonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bus.MoveTransport(Dir.Direction.Left);
				frmBus.repaint();
			}
		});
		buttonLeft.setIcon(new ImageIcon("C:\\\\Users\\\\user\\\\Desktop\\\\Зелёная_стрелка3.png"));
		buttonLeft.setBounds(772, 420, 30, 30);
		frmBus.getContentPane().add(buttonLeft);
		
		JButton button = new JButton("\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u0430\u0432\u0442\u043E\u0431\u0443\u0441");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	            bus = new CommonBus(100 + (int) (Math.random() * 300), 1000 + (int) (Math.random() * 2000),Color.RED);
	            bus.SetPosition(10 + (int) (Math.random() * 100), 10 + (int) (Math.random() * 100),
frmBus.getWidth() -100 , frmBus.getHeight()-100);
	            panel = new BusPanel();
	    		panel.setBounds(10, 11, frmBus.getWidth(), frmBus.getHeight());
	    		frmBus.getContentPane().add(panel);
	    		panel.position(bus);
	            frmBus.repaint();
			}
		});
		button.setBounds(10, 11, 136, 23);
		frmBus.getContentPane().add(button);
	}
}
