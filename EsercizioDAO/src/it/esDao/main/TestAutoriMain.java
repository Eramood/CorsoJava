package it.esDao.main;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import it.esDao.interfac.AutoreDao;
import it.esDao.interfac.AutoreDaoImpl;
import it.esDao.model.Autore;


public class TestAutoriMain {

	public static void main(String[] args) {
		AutoreDao autoreDao = new AutoreDaoImpl();
		Scanner scanner = new Scanner(System.in);
		
		   // a. Metodo getAll()
	    List<Autore> autori = autoreDao.getAll();
	    System.out.println("Autori presenti su Db:");
	    for (Autore autore : autori) {
	        System.out.println(autore.getIdAutore() +":" + autore.getCognomeA() + " " + autore.getNomeA());
	    }
	    
	    
	    System.out.println("Inserisci l'id dell'autore da cercare");
	    int idAutoreDaRicerca = scanner.nextInt(); 
        Autore autoreCercato = autoreDao.getAutoreById(idAutoreDaRicerca);
        if (autoreCercato != null) {
            System.out.println("\nAutore trovato:");
            System.out.println(autoreCercato.getIdAutore() + ": " + autoreCercato.getNomeA() + " " + autoreCercato.getCognomeA());
        } else {
            System.out.println("\nAutore non trovato.");
        }
        
        

        // c. Metodo insert()
        Autore nuovoAutore = new Autore();
        nuovoAutore.setNomeA("Nuovo");
        nuovoAutore.setCognomeA("Autore");
        nuovoAutore.setAnnoNascita(Date.valueOf("1990-01-01")); 
        nuovoAutore.setAnnoMorte(null); 
        nuovoAutore.setSesso('M'); 
        nuovoAutore.setNazione("Italia"); 
        autoreDao.insert(nuovoAutore);
        System.out.println("\nInserimento dell'autore riuscito.");

        // Aggiorniamo la lista dopo l'inserimento
        List<Autore> autoriDopoInserimento = autoreDao.getAll();
        System.out.println("Lista degli Autori dopo l'inserimento:");
        for (Autore autore : autoriDopoInserimento) {
            System.out.println(autore.getIdAutore() + ": " + autore.getNomeA() + " " + autore.getCognomeA());
        }
        
       
        System.out.println("Inserisci l'id dell'autore da aggiornare");
	    int idAutoreDaAgg = scanner.nextInt(); 
        Autore autoreDaAggiornare = autoreDao.getAutoreById(idAutoreDaAgg);
        if (autoreDaAggiornare != null) {
        	System.out.println("Aggiorna la nazione, inserisci la nuova nazione");
    	    String nuovaNazione = scanner.nextLine(); 
            autoreDaAggiornare.setNazione(nuovaNazione);
            autoreDao.update(autoreDaAggiornare);
            System.out.println("\nAggiornamento dell'autore riuscito.");

            // Aggiorniamo la lista dopo l'aggiornamento
            List<Autore> autoriDopoAggiornamento = autoreDao.getAll();
            System.out.println("Lista degli Autori dopo l'aggiornamento:");
            for (Autore autore : autoriDopoAggiornamento) {
                System.out.println(autore.getIdAutore() + ": " + autore.getNomeA() + " " + autore.getCognomeA() + ", Nazione: " + autore.getNazione());
            }
        } else {
            System.out.println("\nAutore non trovato per l'aggiornamento.");
        }

        // e. Metodo delete()
        System.out.println("Inserisci l'id dell'autore da eliminare");
	    int idAutoreDaEliminare = scanner.nextInt(); 
        Autore autoreDaEliminare = autoreDao.getAutoreById(idAutoreDaEliminare);
        if (autoreDaEliminare != null) {
            autoreDao.delete(autoreDaEliminare);
            System.out.println("\nEliminazione dell'autore riuscita.");

            // Aggiorniamo la lista dopo l'eliminazione
            List<Autore> autoriDopoEliminazione = autoreDao.getAll();
            System.out.println("Lista degli Autori dopo l'eliminazione:");
            for (Autore autore : autoriDopoEliminazione) {
                System.out.println(autore.getIdAutore() + ": " + autore.getNomeA() + " " + autore.getCognomeA());
            }
        } else {
            System.out.println("\nAutore non trovato per l'eliminazione.");
        }




	}

}
