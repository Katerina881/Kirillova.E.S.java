import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashSet;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
	private JMenuBar menuBar;
	private JMenu menuSelect;
	private JMenuItem btnSave;
	private JMenuItem btnLoad;
	private JMenuItem btnSaveLvl;
	private JMenuItem btnLoadLvl;
	
	private Logger loggerInfo;
	private Logger loggerError;

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
		loggerInfo = Logger.getLogger("Info");
		loggerError = Logger.getLogger("Errors");
		try {
			FileHandler fhInfo = new FileHandler("infoLogs.txt");
			FileHandler fhError = new FileHandler("errorLogs.txt");
			loggerInfo.addHandler(fhInfo);
			loggerError.addHandler(fhError);
			loggerInfo.setUseParentHandlers(false);
			loggerError.setUseParentHandlers(false);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fhInfo.setFormatter(simpleFormatter);
			fhError.setFormatter(simpleFormatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		menuSelect = new JMenu("\u0424\u0430\u0439\u043B");
		menuBar.add(menuSelect);
		
		btnSave = new JMenuItem("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SaveToolStripMenuItem_Click();
				} catch (HeadlessException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		menuSelect.add(btnSave);
		
		btnLoad = new JMenuItem("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044C");
		btnLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					LoadToolStripMenuItem_Click();
				} catch (NumberFormatException | HeadlessException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		menuSelect.add(btnLoad);
		
		btnSaveLvl = new JMenuItem("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C \u0443\u0440\u043E\u0432\u0435\u043D\u044C");
		btnSaveLvl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SaveLvl();
				} catch (NumberFormatException | HeadlessException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		menuSelect.add(btnSaveLvl);
		
		btnLoadLvl = new JMenuItem("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044C \u0443\u0440\u043E\u0432\u0435\u043D\u044C");
		btnLoadLvl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					LoadLvl();
				} catch (NumberFormatException | HeadlessException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		menuSelect.add(btnLoadLvl);
		
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 1) {
		        	Draw();
		        }
			}
		});
	}
	
	private void SaveToolStripMenuItem_Click() throws HeadlessException, IOException {
		try {
	        FileDialog fileDialog = new FileDialog(new Frame(), "Save", FileDialog.SAVE);
	        fileDialog.setVisible(true);
	        levelPark.SaveData(fileDialog.getDirectory() + fileDialog.getFile());
	        loggerInfo.info("сохранение в файле " + fileDialog.getName());
			JOptionPane.showMessageDialog(null,"Сохранение прошло успешно");
        }
        catch (Exception e) {
			loggerError.warning(e.getMessage());
			JOptionPane.showMessageDialog(null,"не сохранилось");
		}
    }
	
	private void SaveLvl() throws HeadlessException, IOException {
		try {
			FileDialog fileDialog = new FileDialog(new Frame(), "Save", FileDialog.SAVE);
			fileDialog.setVisible(true);
		    levelPark.SaveLvl(fileDialog.getDirectory() + fileDialog.getFile(), list.getSelectedIndex());
		    loggerInfo.info("сохранение в файле " + fileDialog.getName());
		    JOptionPane.showMessageDialog(null,"Сохранение прошло успешно");
		}
		catch (Exception e) {
			loggerError.warning(e.getMessage());
			JOptionPane.showMessageDialog(null,"Не сохранилось");
		}
	}
	
	private void LoadLvl() throws HeadlessException, IOException {
		 try {
	    		FileDialog fileDialog = new FileDialog(new Frame(), "Save", FileDialog.LOAD);
	            fileDialog.setVisible(true);
	            levelPark.LoadLvl(fileDialog.getDirectory() + fileDialog.getFile(), list.getSelectedIndex());
				JOptionPane.showMessageDialog(null,"Загрузили уровень");
				loggerInfo.info("Загрузка уровня из файла" + fileDialog.getName());
	            Draw();
	        }
	        catch (ParkingOccupiedPlaceException ex) {
	        	loggerError.warning(ex.getMessage().toString());
				JOptionPane.showMessageDialog(null,"Занятое место");
	        }
	        catch (Exception ex) {
	        	loggerError.warning(ex.getMessage());
				JOptionPane.showMessageDialog(null,"Неизвестная ошибка");
			}
	}
	
	private void LoadToolStripMenuItem_Click() throws NumberFormatException, HeadlessException, IOException
    {
		try {
    		FileDialog fileDialog = new FileDialog(new Frame(), "Save", FileDialog.LOAD);
            fileDialog.setVisible(true);
            levelPark.LoadLvl(fileDialog.getDirectory() + fileDialog.getFile(), list.getSelectedIndex());
			JOptionPane.showMessageDialog(null,"Загрузили");
			loggerInfo.info("Загрузка из файла" + fileDialog.getName());
            Draw();
        }
        catch (ParkingOccupiedPlaceException ex) {
        	loggerError.warning(ex.getMessage().toString());
			JOptionPane.showMessageDialog(null,"Занятое место");
        }
        catch (Exception ex) {
        	loggerError.warning(ex.getMessage());
			JOptionPane.showMessageDialog(null,"Неизвестная ошибка");
		}
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
							try {
								int place = levelPark.getParkingBus(list.getSelectedIndex()).Add(bus);
								loggerInfo.info("Добавление автобуса " + bus.toString() + " на место " + place);
								Draw();
							}
							catch (ParkingOverflowException ex)
			                 {
			                     loggerError.warning(ex.getMessage());
			                     JOptionPane.showMessageDialog(null,"Переполнение");
			                 }
			                 catch (Exception ex)
			                 {
			                     loggerError.warning(ex.getMessage());
			                     JOptionPane.showMessageDialog(null,"Неизвестная ошибка");
			                 }
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
						try {
				            IBus bus = levelPark.getParkingBus(list.getSelectedIndex()).Remove(Integer.parseInt(inputText.getText()));
				            bus.SetPosition(panel.getWidth() / 2 - 30, panel.getHeight() / 2, panel.getWidth(),panel.getHeight());
				            panel.position(bus);
				            hashSetBus.add(bus);
				            panel.validate();
				            panel.repaint();
				            Draw();
						}
				        catch (ParkingNotFoundException ex) {
				            panel.position(null);
				            panel.validate();
						    panel.repaint();
						    Draw();
				        	JOptionPane.showMessageDialog(null,"не забрали");
				            loggerError.warning(ex.getMessage().toString());
				        }
						catch (Exception ex)
		                {
		        			JOptionPane.showMessageDialog(null,"Неизвестная ошибка");
		                	loggerError.warning(ex.getMessage().toString());    
						}
			        }
				}
			}
		});
		getBus.setBounds(106, 68, 117, 25);
		panel1.add(getBus);
	}
}
