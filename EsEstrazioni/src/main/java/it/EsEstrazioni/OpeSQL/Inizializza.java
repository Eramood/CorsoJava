package it.EsEstrazioni.OpeSQL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.EsEstrazioni.Connessione.DbConnection;
import it.EsEstrazioni.Connessione.DbHandler;

public class Inizializza {
	private static final String PATHCSV = "./FileDaCaricare/esercizioPartecipanti.CSV";
	
	public void CreaTable() {
		System.out.println("Creazione delle tabelle in corso");
		DbHandler dbHandler = DbHandler.getInstance();
		try {
			 Connection connection = dbHandler.getConnection();
	            Statement statement = connection.createStatement();
			String creaPartecipanti ="CREATE TABLE partecipanti("
					+ "id_part INT AUTO_INCREMENT PRIMARY KEY,"
					+"nome VARCHAR(50),"
					+"sede VARCHAR(50)"
					+");";
			statement.executeUpdate(creaPartecipanti);
			String creaEstrazioni ="CREATE TABLE estrazioni("
					+ "id_ESTRAZIONE INT AUTO_INCREMENT PRIMARY KEY,"
					+"id_part INT," 
                    +"data_estrazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP," 
                    +"FOREIGN KEY (id_part) REFERENCES partecipanti(id_part)" 
                    +")";
			statement.executeUpdate(creaEstrazioni);
				
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		System.out.println("Fine creazione tabelle \n");
	}
	
	
	public void CaricaDat() {
	System.out.println("Carico i dati del file");
	DbHandler dbHandler = DbHandler.getInstance();
	try {
		 Connection connection = dbHandler.getConnection();
            Statement statement = connection.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(PATHCSV)); 
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                String nome = data[0];
                String sede = data[1];
                System.out.println("nome: "+ nome +" sede: "+sede+";");
                String inserimento = "INSERT INTO partecipanti(nome , sede) VALUES"
                +"("+"'"+nome+"'"+","+"'"+sede+"'"+");";
                statement.executeUpdate(inserimento);
            }

            System.out.println("Dati caricati con successo. \n");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
	
}
