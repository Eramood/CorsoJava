package it.esDao.main;

import java.util.List;
import java.util.Scanner;

import it.esDao.interfac.EditoreDao;
import it.esDao.interfac.EditoreDaoImpl;
import it.esDao.model.Editore;
import it.esDao.model.Genere;

public class TestEditoreMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		EditoreDao editoreDao = new EditoreDaoImpl();
		
		   // a. Metodo getAll()
	    List<Editore> editori = editoreDao.getAll();
	    System.out.println("Editori presenti su Db:");
	    for (Editore editore : editori) {
	        System.out.println(editore.getidEditore() + ": " + editore.getNome());
	    }

	    
	    // b. Metodo 
        
        System.out.println("Inserisci l'id dell'editore da cercare");
		String idRicerca = scanner.nextLine();
        Editore editoreById = editoreDao.getEditoreById(idRicerca);
        System.out.println("\nDati del'editore richiesto :");
        System.out.println(editoreById.getidEditore() + ": " + editoreById.getNome());
	    
        
        //c
        System.out.println("Inserisci il nome Dell'editore da inserire");
		String editoreNuovo = scanner.nextLine();
        Editore nuovoEditore = new Editore();
        nuovoEditore.setNome(editoreNuovo);
        editoreDao.insert(nuovoEditore);
        

        // Aggiorniamo la lista dopo l'inserimento
        List<Editore> editoriDopoInserimento = editoreDao.getAll();
        System.out.println("Editori dopo l'inserimento:");
        for (Editore editore : editoriDopoInserimento) {
            System.out.println(editore.getidEditore() + ": " + editore.getNome());
        }
        
        
        
        //d
        Editore editoreDaAggiornare = new Editore();
        System.out.println("Inserisci l'id dell'editore da aggiornare");
		String editoreidDaAggiornare = scanner.nextLine();
		System.out.println("Inserisci il nuovo nome");
		String nuovoNomeEditore = scanner.nextLine();
        editoreDaAggiornare.setidEditore(editoreidDaAggiornare);; 
        editoreDaAggiornare.setNome(nuovoNomeEditore); 

        editoreDao.update(editoreDaAggiornare);
        

        // Aggiorniamo la lista dopo l'aggiornamento
        List<Editore> editoriDopoAggiornameto = editoreDao.getAll();
        System.out.println("Editori dopo l'inserimento:");
        for (Editore editore : editoriDopoAggiornameto) {
            System.out.println(editore.getidEditore() + ": " + editore.getNome());
        }
        
        //e
        Editore editoreDaEliminare = new Editore();
        System.out.println("Inserisci il nome Dell'editore da eliminare");
		String editoreDelete = scanner.nextLine();
        editoreDaEliminare.setNome(editoreDelete); 

       editoreDao.delete(editoreDaEliminare);
        

        // Aggiorniamo la lista dopo la cancellazione
        List<Editore> editoriDopoElimkinazione = editoreDao.getAll();
        System.out.println("Editori dopo l'inserimento:");
        for (Editore editore : editoriDopoElimkinazione) {
            System.out.println(editore.getidEditore() + ": " + editore.getNome());
        }
        
        
	}

}
