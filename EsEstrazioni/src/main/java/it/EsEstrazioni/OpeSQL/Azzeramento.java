package it.EsEstrazioni.OpeSQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import it.EsEstrazioni.Connessione.DbConnection;
import it.EsEstrazioni.Connessione.DbHandler;

public class Azzeramento {
	Scanner scanner = new Scanner(System.in);
	public void DropTabelle() {
		System.out.println("ATTENZIONE: sicuro di voler eliminare le tabelle?"
				+ "s = Si | n = No");
		String risposta = scanner.nextLine();
		if(risposta.contains("s")) {
			DbHandler dbHandler = DbHandler.getInstance();
			try {
				 Connection connection = dbHandler.getConnection();
		            Statement statement = connection.createStatement();
				String eliminaEstrazioni ="DROP TABLE estrazioni;";
				statement.executeUpdate(eliminaEstrazioni);
				String eliminaPartecipanti ="DROP TABLE partecipanti;";
				statement.executeUpdate(eliminaPartecipanti);
	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Eliminazione effettuata \n");
		}else {
			System.out.println("Eliminazione non effettuata \n");
		}
		
	}
}

