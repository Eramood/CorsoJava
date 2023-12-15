package it.esDao.main;

import java.util.List;
import java.util.Scanner;

import it.esDao.interfac.GenereDao;
import it.esDao.interfac.GenereDaoImpl;
import it.esDao.model.Genere;

public class TestMainGenere {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		GenereDao genereDao = new GenereDaoImpl();

        // a. Metodo getAll()
        List<Genere> generi = genereDao.getAll();
        System.out.println("Generi presenti su Db:");
        for (Genere genere : generi) {
            System.out.println(genere.getidGenere() + ": " + genere.getDescrizione());
        }
        	
        
        // b. Metodo getGenereById
        
        System.out.println("Inserisci il genere da cercare");
		int idRicerca = scanner.nextInt();
        Genere genereById = genereDao.getGenereById(idRicerca);
        System.out.println("\nDati del genere richiesto :");
        System.out.println(genereById.getidGenere() + ": " + genereById.getDescrizione());

        
        //c
        Genere nuovoGenere = new Genere();
        nuovoGenere.setDescrizione("Bianco");
        genereDao.insert(nuovoGenere);
       

        // Aggiorniamo la lista dopo l'inserimento
        generi = genereDao.getAll();
        System.out.println("Generi dopo l'inserimento:");
        for (Genere genere : generi) {
            System.out.println(genere.getidGenere() + ": " + genere.getDescrizione());
        }
        
        // d. Metodo update
        Genere genereDaAggiornare = new Genere();
        genereDaAggiornare.setidGenere(10); 
        genereDaAggiornare.setDescrizione("Rosa"); 

        genereDao.update(genereDaAggiornare);
      

        // Aggiorniamo la lista dopo l'aggiornamento
        List<Genere> generiDopoAggiornamento = genereDao.getAll();
        System.out.println("Generi dopo l'aggiornamento:");
        for (Genere genere : generiDopoAggiornamento) {
            System.out.println(genere.getidGenere() + ": " + genere.getDescrizione());
        }
        
        //e
        Genere genereDaEliminare = new Genere();
        genereDaEliminare.setDescrizione("Rosa"); // Imposta la descrizione del genere da eliminare
       genereDao.delete(genereDaEliminare);
    

        // Aggiorniamo la lista dopo la cancellazione
        List<Genere> generiDopoCancellazione = genereDao.getAll();
        System.out.println("Generi dopo la cancellazione:");
        for (Genere genere : generiDopoCancellazione) {
            System.out.println(genere.getidGenere() + ": " + genere.getDescrizione());
        }
    }
    }
	


