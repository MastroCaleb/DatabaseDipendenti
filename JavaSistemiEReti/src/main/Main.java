package main;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.io.PrintStream;
//import java.util.ArrayList;

import javax.swing.DefaultListModel;

import menu.MainMenu;
import workers.Dipendente;

public class Main {
	
	static String sp = File.separator;
	public static DefaultListModel<Dipendente> list = new DefaultListModel<Dipendente>();
	static String path = root()+sp+"DatabaseDipendenti";
	
	public static final void main(String[]args) throws IOException {
		
		try {
			loadDataBase();
		}
		catch(FileNotFoundException f){
            saveDataBase();
        }
		
		new MainMenu();
	}
	
	@SuppressWarnings("unchecked")
	public static void loadDataBase() throws IOException {
		File db = new File(path+sp+"database"+sp, "dipendenti.data"); //File dove leggere e scrivere i dipendenti
		FileInputStream fileIn = new FileInputStream(db);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        try {
            list = (DefaultListModel<Dipendente>)in.readObject();
        }
        catch (IOException i) {
            i.getLocalizedMessage();
        }
        catch (ClassNotFoundException c) {
            System.out.println("No previous data found.");
        }
	}
	
	public static void saveDataBase() throws IOException {
		File db = new File(path+sp+"database"+sp, "dipendenti.data"); //File dove leggere e scrivere i dipendenti
		db.getParentFile().mkdirs();
		db.createNewFile();
		
		FileOutputStream fileOut = new FileOutputStream(db);
        @SuppressWarnings("resource")
		ObjectOutputStream out = new ObjectOutputStream(fileOut);

        try {
            out.writeObject(list);
        }
        catch (FileNotFoundException e) {
            out.writeObject(list);
        }
	}
	
	/*
		private void consoleVersion() throws IOException {
			
	
			File txt = new File(path+sp+"lista"+sp, "lista_dipendenti.txt"); //File txt con ogni dipendente
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			txt.getParentFile().mkdirs();
			
			try {
				txt.createNewFile();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
			PrintStream fs = new PrintStream(new FileOutputStream(txt, true));
			
			String choice = " ";
			
			
			System.out.println(txt.getPath());
			
			do {
				Dipendente dp = Dipendente.input();
				list.addElement(dp);
				System.out.println("Aggiungere altro? Y/N");
				try {
					choice = br.readLine();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}while(choice.equals("Y"));
			
			for(int i=0; i<list.size();i++) {
				fs.println(list.get(i).toString());
			}
		}
	*/
	
	public static String root(){
		File file = new File(".").getAbsoluteFile();
		File root = file.getParentFile();
		while (root.getParentFile() != null) {
		    root = root.getParentFile();
		}
		return root.getPath();
	}
	
}
