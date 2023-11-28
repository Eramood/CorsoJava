package it.EsEstrazioni.main;

import java.sql.Connection;
import java.util.Scanner;

import it.EsEstrazioni.Connessione.DbConnection;
import it.EsEstrazioni.OpeSQL.Azzeramento;
import it.EsEstrazioni.OpeSQL.Estrazioni;
import it.EsEstrazioni.OpeSQL.Inizializza;
import it.EsEstrazioni.OpeSQL.StampeStatistiche;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
    	Scanner scanner = new Scanner(System.in);
        Inizializza inizializzadb = new Inizializza();
        Azzeramento resetDb = new Azzeramento();
        Estrazioni estrazione = new Estrazioni(); 
        StampeStatistiche stamp = new StampeStatistiche();
        
        boolean check = true;// variabile per entrata o uscita dal ciclo(menu);
        while (check) {
        	  System.out.println("Quale operazione vuoi eseguire: "
        	  		+ "\n#I = inizializzare il Database"
        	  		+ "\n#E = estrazione"
        	  		+ "\n#S = stampa stato estrazioni"
        	  		+ "\n#A = azzerare il db"
        	  		+ "\npremi qualsasi altro pulsante per uscire dal programma"
                        );
        	  String risposta = scanner.nextLine();
        	  if(risposta.contains("i") || risposta.contains("I") ){
        		  inizializzadb.CreaTable();
        		  inizializzadb.CaricaDat();
        	  }else if(risposta.contains("e")|| risposta.contains("E")) {
        		  estrazione.estrazioneCasuale();
        	  }else if(risposta.contains("s")||risposta.contains("S")) {
            		  stamp.stampaVideo();
            		  stamp.stampaPdf();
        	  }else if (risposta.contains("a")|| risposta.contains("A")) {
        		  resetDb.DropTabelle();
        	  }else {
        		
        		  System.out.println("Chiusura del programma");
        		  check = false;
        	  }
        	}
        
    }
}
