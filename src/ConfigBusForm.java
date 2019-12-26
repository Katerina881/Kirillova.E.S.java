import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.EventQueue;

public class ConfigBusForm {

	private JFrame frame;
	private Color curColor;
	private IBus curBus;
	private IBus tempBus;
	private BusPanel drawPanel;
	private IForm frm;
    private CarDelegate eventAddCar;
    
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigBusForm window = new ConfigBusForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}
	
	public ConfigBusForm() {
		initialize();
	}

	public ConfigBusForm(CarDelegate eventAddCar) {
		initialize();
		this.eventAddCar = eventAddCar;
	}
	
	private void Draw() {
		drawPanel.validate();
		drawPanel.repaint();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 758, 512);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel CommonPanel = new JPanel();
		CommonPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		CommonPanel.setBounds(27, 53, 206, 201);
		frame.getContentPane().add(CommonPanel);
		CommonPanel.setLayout(null);
		
		JLabel labelCommonBus = new JLabel("\u041E\u0431\u044B\u0447\u043D\u044B\u0439 \u0430\u0432\u0442\u043E\u0431\u0443\u0441");
		labelCommonBus.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { 
				tempBus = null;
			}

		    @Override
		    public void mousePressed(MouseEvent e) {
		    	tempBus = new CommonBus(15, 15, Color.WHITE);
		        tempBus.SetPosition(drawPanel.getWidth()/2 - 50, drawPanel.getHeight()/2, drawPanel.getWidth(), drawPanel.getHeight());
		    }
		});
		labelCommonBus.setBounds(10, 47, 186, 51);
		CommonPanel.add(labelCommonBus);
		labelCommonBus.setHorizontalAlignment(SwingConstants.CENTER);
		labelCommonBus.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel labelBus = new JLabel("\u0414\u0432\u0443\u0445\u044D\u0442\u0430\u0436\u043D\u044B\u0439 \u0430\u0432\u0442\u043E\u0431\u0443\u0441");
		labelBus.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { 
				tempBus = null;
			}

		    @Override
		    public void mousePressed(MouseEvent e) { 
		    	tempBus = new Bus(15, 15, Color.WHITE, Color.WHITE, true, true);
				tempBus.SetPosition(drawPanel.getWidth()/2 - 50, drawPanel.getHeight()/2, drawPanel.getWidth(), drawPanel.getHeight());
		    }
		});
		labelBus.setHorizontalAlignment(SwingConstants.CENTER);
		labelBus.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelBus.setBounds(10, 112, 186, 51);
		CommonPanel.add(labelBus);
		
		JButton btnAdd = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eventAddCar.Invoke(curBus);
				frame.dispose();
			}
		});
		btnAdd.setBounds(68, 298, 120, 46);
		frame.getContentPane().add(btnAdd);
		
		JButton btnCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(68, 359, 120, 46);
		frame.getContentPane().add(btnCancel);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		mainPanel.setBounds(246, 26, 312, 379);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		drawPanel = new BusPanel();
		drawPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (tempBus != null) {
					curBus = tempBus;
					drawPanel.position(curBus);
					Draw();
				}
			}
		});
		drawPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		drawPanel.setBounds(12, 27, 288, 113);
		mainPanel.add(drawPanel);
		
		JLabel mainColorLabel = new JLabel("\u041E\u0441\u043D\u043E\u0432\u043D\u043E\u0439 \u0446\u0432\u0435\u0442"); 
		mainColorLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (curColor != null && curBus != null) {
					curBus.SetMainColor(curColor);
					Draw();
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {   
				curColor = null;
			}
		});
		mainColorLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainColorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainColorLabel.setBounds(12, 153, 288, 62);
		mainPanel.add(mainColorLabel);
		
		JLabel dopColorLabel = new JLabel("\u0414\u043E\u043F. \u0446\u0432\u0435\u0442");
		dopColorLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (curColor != null && curBus != null && curBus instanceof Bus) {
					((Bus)curBus).SetDopColor(curColor);
					Draw();
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {   
				curColor = null;
			}
		});
		dopColorLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		dopColorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dopColorLabel.setBounds(12, 228, 288, 62);
		mainPanel.add(dopColorLabel);
		
		JLabel labelForm = new JLabel("\u0424\u043E\u0440\u043C\u0430 \u0434\u0432\u0435\u0440\u0435\u0439"); 
		labelForm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (curBus != null && frm != null) {
					Enum.rand();
					curBus.SetFrm(frm);
					Draw();
				}
			}
		});
		labelForm.setHorizontalAlignment(SwingConstants.CENTER);
		labelForm.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelForm.setBounds(12, 304, 288, 62);
		mainPanel.add(labelForm);
		
		JPanel panelColors = new JPanel();
		panelColors.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelColors.setBounds(570, 26, 156, 250);
		frame.getContentPane().add(panelColors);
		panelColors.setLayout(null);
		
		JPanel panelWhite = new JPanel();
		panelWhite.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curColor = Color.WHITE;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				curColor = null;
			}
		});
		panelWhite.setBackground(Color.WHITE);
		panelWhite.setBounds(94, 10, 50, 50);
		panelColors.add(panelWhite);
		
		JPanel panelBlue = new JPanel();
		panelBlue.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curColor = Color.BLUE;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				curColor = null;
			}
		});
		panelBlue.setBackground(Color.BLUE);
		panelBlue.setBounds(94, 70, 50, 50);
		panelColors.add(panelBlue);
		
		JPanel paneYellow = new JPanel();
		paneYellow.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curColor = Color.YELLOW;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				curColor = null;
			}
		});
		paneYellow.setBackground(Color.YELLOW);
		paneYellow.setBounds(94, 130, 50, 50);
		panelColors.add(paneYellow);
		
		JPanel panelOrange = new JPanel();
		panelOrange.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curColor = Color.ORANGE;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				curColor = null;
			}
		});
		panelOrange.setBackground(Color.ORANGE);
		panelOrange.setBounds(94, 190, 50, 50);
		panelColors.add(panelOrange);
		
		JPanel panelBlack = new JPanel();
		panelBlack.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curColor = Color.black;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				curColor = null;
			}
		});
		panelBlack.setBackground(Color.BLACK);
		panelBlack.setBounds(12, 10, 50, 50);
		panelColors.add(panelBlack);
		
		JPanel panelGreen = new JPanel();
		panelGreen.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curColor = Color.GREEN;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				curColor = null;
			}
		});
		panelGreen.setBackground(Color.GREEN);
		panelGreen.setBounds(12, 70, 50, 50);
		panelColors.add(panelGreen);
		
		JPanel panelRed = new JPanel();
		panelRed.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curColor = Color.RED;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				curColor = null;
			}
		});
		panelRed.setBackground(Color.RED);
		panelRed.setBounds(12, 130, 50, 50);
		panelColors.add(panelRed);
		
		JPanel panelGray = new JPanel();
		panelGray.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				curColor = Color.GRAY;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				curColor = null;
			}
		});
		panelGray.setBackground(Color.GRAY);
		panelGray.setBounds(12, 190, 50, 50);
		panelColors.add(panelGray);
		
		JPanel panelDopFunc = new JPanel();
		panelDopFunc.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelDopFunc.setBounds(570, 298, 156, 131);
		frame.getContentPane().add(panelDopFunc);
		panelDopFunc.setLayout(null);
		
		JLabel labelForm1 = new JLabel("\u0424\u043E\u0440\u043C\u0430 1");
		labelForm1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frm = new FormCommon();			
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				frm = null;	
			}
		});
		labelForm1.setBounds(10, 11, 136, 30);
		panelDopFunc.add(labelForm1);
		labelForm1.setHorizontalAlignment(SwingConstants.CENTER);
		labelForm1.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel labelForm2 = new JLabel("\u0424\u043E\u0440\u043C\u0430 2");
		labelForm2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frm = new FormDvoinaya();			
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				frm = null;	
			}
		});
		labelForm2.setBounds(10, 52, 136, 30);
		panelDopFunc.add(labelForm2);
		labelForm2.setHorizontalAlignment(SwingConstants.CENTER);
		labelForm2.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel labelForm3 = new JLabel("\u0424\u043E\u0440\u043C\u0430 3");
		labelForm3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frm = new FormKryg();			
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				frm = null;	
			}
		});
		labelForm3.setBounds(10, 93, 136, 30);
		panelDopFunc.add(labelForm3);
		labelForm3.setHorizontalAlignment(SwingConstants.CENTER);
		labelForm3.setBorder(new LineBorder(new Color(0, 0, 0)));
	}
}
