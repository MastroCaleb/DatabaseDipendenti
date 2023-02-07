package menu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import main.Main;
import workers.Dipendente;

@SuppressWarnings("serial")
public class MainMenu{
	
	JLabel empty = new JLabel("<html><h3>Database Vuoto</h3></html>");
	JLabel added = new JLabel("<html><h3>Aggiunto al Database</h3></html>");
	JLabel changed = new JLabel("<html><h3>Dipendente Modificato</h3></html>");
	JLabel cleared = new JLabel("<html><h3>Database Pulito</h3></html>");
	
	Dipendente selected = new Dipendente("", "", new Date());
	
	int currentIndex = 0;
	
	MenuPane menu = new MenuPane();
	
	AddPane addMenu = new AddPane();
	
	DatabasePane dataMenu = new DatabasePane();
	
	ModifyPane modMenu = new ModifyPane();
	
	JFrame frame = new JFrame("Database");
	
	JList<Dipendente> list = new JList<Dipendente>(Main.list);
	
	public MainMenu() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	frame.update(frame.getGraphics());
                menu.setVisible(true);
                addMenu.setVisible(true);
                dataMenu.setVisible(true);
                frame.setSize(600, 400);
                frame.add(menu);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
	
	public class MenuPane extends JPanel{
		public MenuPane(){
			setBorder(new EmptyBorder(10, 10, 10, 10));
	        setLayout(new GridBagLayout());

	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridwidth = GridBagConstraints.REMAINDER;
	        gbc.anchor = GridBagConstraints.NORTH;

	        add(new JLabel("<html><h1><strong><i>Menù Database</i></strong></h1><hr></html>"), gbc);
	        add(cleared, gbc);
	        cleared.setVisible(false);

	        gbc.anchor = GridBagConstraints.CENTER;
	        gbc.fill = GridBagConstraints.HORIZONTAL;
	        

	        JPanel buttons = new JPanel(new GridBagLayout());
	        JButton add = new JButton("Aggiungi Dipendente");
	        JButton database = new JButton("Database");
	        JButton clear = new JButton("Pulisci Database");
	        JButton exit = new JButton("Exit");
	        
	        database.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		frame.remove(menu);
	        		frame.add(dataMenu);
	        		empty.setVisible(Main.list.isEmpty());
	        		cleared.setVisible(false);
	        		dataMenu.updateUI();
	            }
	        });
	        
	        add.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		frame.remove(menu);
	        		frame.add(addMenu);
	        		cleared.setVisible(false);
	        		addMenu.updateUI();
	            }
	        });
	        
	        clear.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		Main.list.clear();
	        		try {
						Main.saveDataBase();
					} 
	        		catch (IOException e1) {
						e1.printStackTrace();
					}
	        		menu.updateUI();
	        		list.updateUI();
	        		cleared.setVisible(true);
	            }
	        });
	        
	        exit.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		cleared.setVisible(false);
	                System.exit(0);
	            }
	        });
	        
	        buttons.add(add, gbc);
	        buttons.add(database, gbc);
	        buttons.add(clear, gbc);
	        buttons.add(exit, gbc);

	        gbc.weighty = 1;
	        add(buttons, gbc);
		}
	}
	
	public class AddPane extends JPanel{
		public AddPane(){
			setBorder(new EmptyBorder(10, 10, 10, 10));
	        setLayout(new GridBagLayout());

	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridwidth = GridBagConstraints.REMAINDER;
	        gbc.anchor = GridBagConstraints.NORTH;

	        add(new JLabel("<html><h1><strong><i>Nuovo Dipendente</i></strong></h1><hr></html>"), gbc);
	        add(added, gbc);
	        added.setVisible(false);

	        gbc.anchor = GridBagConstraints.CENTER;
	        gbc.fill = GridBagConstraints.HORIZONTAL;

	        JPanel buttons = new JPanel(new GridBagLayout());
	        JTextField nameField = new JTextField();
	        nameField.setToolTipText("Nome");
	        JTextField surnameField = new JTextField();
	        surnameField.setToolTipText("Cognome");
	        JTextField dateField = new JTextField();
	        dateField.setToolTipText("Data di Nascita");
	        JButton add = new JButton("Aggiungi Nuovo");
	        JButton exit = new JButton("Menu Principale");
	        
	        add.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
	    		    
	    		    Date data = null;
					try {
						data = formatter.parse(dateField.getText());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					added.setVisible(false);
					added.setVisible(true);
	        		Dipendente dp = new Dipendente(nameField.getText(), surnameField.getText(), data);
	        		
	        		nameField.setText(" ");
	        		surnameField.setText(" ");
	        		dateField.setText(" ");
	        		
	        		Main.list.addElement(dp);
	        		try {
						Main.saveDataBase();
					} 
	        		catch (IOException e1) {
						e1.printStackTrace();
					}
	        		list.updateUI();
	            }
	        });
	        
	        exit.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		added.setVisible(false);
	        		frame.remove(addMenu);
	        		frame.add(menu);
	        		menu.updateUI();
	            }
	        });
	        
	        buttons.add(nameField, gbc);
	        buttons.add(surnameField, gbc);
	        buttons.add(dateField, gbc);
	        
	        buttons.add(add, gbc);
	        buttons.add(exit, gbc);

	        gbc.weighty = 5;
	        add(buttons, gbc);
		}
	}
	
	public class DatabasePane extends JPanel{
		public DatabasePane(){
			setBorder(new EmptyBorder(10, 10, 10, 10));
	        setLayout(new GridBagLayout());

	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridwidth = GridBagConstraints.REMAINDER;
	        gbc.anchor = GridBagConstraints.NORTH;

	        add(new JLabel("<html><h1><strong><i>Database dei Dipendenti</i></strong></h1><hr></html>"), gbc);
	        add(empty, gbc);

	        gbc.anchor = GridBagConstraints.CENTER;
	        gbc.fill = GridBagConstraints.HORIZONTAL;

	        JPanel buttons = new JPanel(new GridBagLayout());
	        
	        list = new JList<Dipendente>(Main.list);
	        
	        JTextField idField = new JTextField();
	        idField.setToolTipText("Numero di Database dell'Elemento");
	        JButton modify = new JButton("Modifica");
	        JButton exit = new JButton("Exit");
	        
	        exit.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		frame.remove(dataMenu);
	        		frame.add(menu);
	        		menu.updateUI();
	            }
	        });
	        
	        modify.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		if(idField.getText().isBlank()) {
	        			return;
	        		}
	        		try {
	        			if(Main.list.elementAt(Integer.parseInt(idField.getText())).isEmpty()) {
		        			return;
		        		}
	        		}
	        		catch(ArrayIndexOutOfBoundsException ai){
	        			idField.setText("");
	        			return;
	        		}
	        		
	        		
	        		selected = Main.list.elementAt(Integer.parseInt(idField.getText()));
	        		
	        		frame.remove(dataMenu);
	        		frame.add(modMenu);
	        		modMenu.updateUI();
	            }
	        });
	        
	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setViewportView(list);
	        list.addMouseMotionListener(new MouseMotionAdapter() {
	            @Override
	            public void mouseMoved(MouseEvent e) {
	                JList l = (JList)e.getSource();
	                ListModel m = l.getModel();
	                int index = l.locationToIndex(e.getPoint());
	                if( index>-1 ) {
	                    l.setToolTipText("Index: " + String.valueOf(index));
	                }
	            }
	        });
	        list.setLayoutOrientation(JList.VERTICAL);
	        buttons.add(scrollPane, gbc);
	        buttons.add(idField, gbc);
	        buttons.add(modify, gbc);
	        buttons.add(exit, gbc);

	        gbc.weighty = 1;
	        add(buttons, gbc);
		}
	}
	
	public class ModifyPane extends JPanel{
		public ModifyPane(){
			setBorder(new EmptyBorder(10, 10, 10, 10));
	        setLayout(new GridBagLayout());

	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridwidth = GridBagConstraints.REMAINDER;
	        gbc.anchor = GridBagConstraints.NORTH;

	        add(new JLabel("<html><h1><strong><i>Modifica Dipendente</i></strong></h1><hr></html>"), gbc);
	        add(changed, gbc);
	        changed.setVisible(false);

	        gbc.anchor = GridBagConstraints.CENTER;
	        gbc.fill = GridBagConstraints.HORIZONTAL;
	        
	        JPanel buttons = new JPanel(new GridBagLayout());
	        JTextField nameField = new JTextField(selected.getNome());
	        nameField.setText(selected.getNome());
	        nameField.setToolTipText("Nome");
	        JTextField surnameField = new JTextField(selected.getCognome());
	        surnameField.setText(selected.getCognome());
	        surnameField.setToolTipText("Cognome");
	        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
	        JTextField dateField = new JTextField(formatter.format(selected.getData()));
	        dateField.setText(formatter.format(selected.getData()));
	        dateField.setToolTipText("Data di Nascita");
	        JButton add = new JButton("Modifica");
	        JButton exit = new JButton("Menu Principale");
	        
	        add.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	    		    
	    		    Date data = null;
					try {
						data = formatter.parse(dateField.getText());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					changed.setVisible(false);
					changed.setVisible(true);
	        		Dipendente dp = new Dipendente(nameField.getText(), surnameField.getText(), data);
	        		
	        		nameField.setText(" ");
	        		surnameField.setText(" ");
	        		dateField.setText(" ");
	        		
	        		Main.list.set(currentIndex, dp);
	        		try {
						Main.saveDataBase();
					} 
	        		catch (IOException e1) {
						e1.printStackTrace();
					}
	        		list.updateUI();
	            }
	        });
	        
	        exit.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		changed.setVisible(false);
	        		frame.remove(modMenu);
	        		frame.add(menu);
	        		menu.updateUI();
	            }
	        });
	        
	        buttons.add(nameField, gbc);
	        buttons.add(surnameField, gbc);
	        buttons.add(dateField, gbc);
	        
	        buttons.add(add, gbc);
	        buttons.add(exit, gbc);

	        gbc.weighty = 5;
	        add(buttons, gbc);
		}
	}
}
