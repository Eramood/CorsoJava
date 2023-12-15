package it.esDao.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import it.esDao.interfac.AutoreDaoImpl;
import it.esDao.interfac.LibroDao;
import it.esDao.interfac.LibroDaoImpl;
import it.esDao.model.Autore;
import it.esDao.model.Libro;
import it.esDao.service.PrintService;

public class LibroPrintService implements PrintService<Libro> {
	private static final String PATHARCH = "./Archivio";
	private LibroDao libroDao;
	Scanner scanner = new Scanner(System.in);
    public LibroPrintService() {
        this.libroDao = new LibroDaoImpl();
    }

    @Override
    public void saveListAsPdf() {
        // Implementa la generazione del PDF per la lista di libri
        System.out.println("Salvataggio lista libri in formato PDF");
    }

    @Override
    public void saveListAsCsv() {
        // Implementa la generazione del CSV per la lista di libri
        System.out.println("Salvataggio lista libri in formato CSV");
    }

    @Override
    public void saveListAsTxt() {
    	List<Libro> libri = libroDao.getAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATHARCH+"/ListaLibri.txt"))) {
            for (Libro libro : libri) {
                writer.write(libro.getTitolo());
                writer.newLine();  
            }
            System.out.println("Salvataggio lista libri in formato TXT");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAsPdf(Libro libro) {
        // Implementa la generazione del PDF per un singolo libro
        System.out.println("Salvataggio libro in formato PDF");
    }

    @Override
    public void saveAsCsv(Libro libro) {
        // Implementa la generazione del CSV per un singolo libro
        System.out.println("Salvataggio libro in formato CSV");
    }

    @Override
    public void saveAsTxt(Libro libro) {
    	System.out.println("Inserisci l'id del libro da cercare");
    	int idlibro = scanner.nextInt(); 
    	Libro libroDaSalvare = libroDao.getLibroById(idlibro);
    	AutoreDaoImpl autore = new AutoreDaoImpl();
    	Autore autoreStampa = autore.getAutoreById(libroDaSalvare.getIdAutore());
    	 try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATHARCH + "/"+libroDaSalvare.getTitolo() + ".txt"))) {
             writer.write("Titolo :" + libroDaSalvare.getTitolo());
             writer.newLine();
             writer.write("Anno :" + libroDaSalvare.getAnno());
             writer.newLine();
             writer.write("Autore :" + autoreStampa.getCognomeA());
             System.out.println("Salvataggio libro in formato TXT");
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}
