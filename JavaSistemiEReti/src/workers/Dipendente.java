package workers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Dipendente implements Serializable{
	
	private String nome;
	private String cognome;
	private Date data;
	
	public Dipendente(String nome, String cognome, Date data) {
		super();
		this.nome = nome;
		this.cognome= cognome;
		this.data = data;
	}
	
	public Dipendente() {
		super();
	}
	
	@SuppressWarnings("deprecation")
	public static Dipendente input(){
		Dipendente dp = new Dipendente();
		try {
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    		
		    System.out.println("Inserisci Nome: ");
		    String nome = br.readLine();
		    System.out.println("Inserisci Cognome: ");
		    String cognome = br.readLine();
		    System.out.println("Inserisci Data di Nascita: (dd-mm-yyyy) ");
		    SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
		    
		    Date data = formatter.parse(br.readLine());
		    
		    dp=new Dipendente(nome,cognome,data);
		    
		} 
		catch(IOException ioe) {
		    System.out.println(ioe.getMessage());
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		return dp;
	}
	
	public boolean isEmpty() {
		if(nome == null || cognome == null || data == null) {
			return true;
		}
		return false;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public String toFormattedString(Date data) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
        return formatter.format(data);
    }

	@Override
	public String toString() {
		return nome+" "+cognome+" "+toFormattedString(data);
	}

}
