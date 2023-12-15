package it.esDao.main;

import java.util.List;
import java.util.Scanner;

import it.esDao.interfac.LibroDao;
import it.esDao.interfac.LibroDaoImpl;
import it.esDao.model.Autore;
import it.esDao.model.Genere;
import it.esDao.model.Libro;

public class TestLibroMain {

	public static void main(String[] args) {
		LibroDao libroDao = new LibroDaoImpl();
		Scanner scanner = new Scanner(System.in);
		
		// a. Metodo getAll()
		List<Libro> libri = libroDao.getAll();
		System.out.println("Lista dei Libri:");
		for (Libro libro : libri) {
		    System.out.println(libro.getIdLibro() + ": " + libro.getTitolo() + " - Autore: " + libro.getIdAutore() + " - Genere: " + libro.getIdGenere());
		}
		
		
		
		 int idLibroDaRicerca = 1; 
	        Libro libroCercato = libroDao.getLibroById(idLibroDaRicerca);
	        if (libroCercato != null) {
	            System.out.println("\nLibro trovato:");
	            System.out.println(libroCercato.getIdLibro() + ": " + libroCercato.getTitolo());
	        } else {
	            System.out.println("\nLibro non trovato.");
	        }
		
		
	        // insert del nuovo libro , devo implementare gli scanner per le informazoni
	        Libro nuovoLibro = new Libro();
	        nuovoLibro.setTitolo("Nuovo Libro");
	        nuovoLibro.setIdAutore(1); 
	        nuovoLibro.setIdGenere(1); 
	        nuovoLibro.setIdEditore("Riz0"); 
	        nuovoLibro.setNumPag(300);
	        nuovoLibro.setAnno(2023);
	        libroDao.insert(nuovoLibro);
	        System.out.println("\nInserimento del libro riuscito.");
	
	        Libro libroDaAggiornare = libroDao.getLibroById(idLibroDaRicerca);
	        if (libroDaAggiornare != null) {
	            libroDaAggiornare.setTitolo("Nuovo Titolo");
	            libroDaAggiornare.setIdAutore(2); 
	            libroDaAggiornare.setNumPag(400);
	            libroDaAggiornare.setAnno(2024);
	            libroDaAggiornare.setIdGenere(2); 
	            libroDaAggiornare.setIdEditore("XYZ01"); 
	            libroDao.update(libroDaAggiornare);
	            System.out.println("\nAggiornamento del libro riuscito.");
	        } else {
	            System.out.println("\nLibro non trovato per l'aggiornamento.");
	        }
	        
	        Libro libroDaEliminare = libroDao.getLibroById(idLibroDaRicerca);
	        if (libroDaEliminare != null) {
	            libroDao.delete(libroDaEliminare);
	            System.out.println("\nEliminazione del libro riuscita.");
	        } else {
	            System.out.println("\nLibro non trovato per l'eliminazione.");
	        }
	
	}

}
