package it.esDao.interfac;

import java.util.List;

import it.esDao.model.Libro;

public interface LibroDao {
	   List<Libro> getAll();
	    Libro getLibroById(int idLibro);
	    void insert(Libro libro);
	    void update(Libro libro);
	    void delete(Libro libro);
}
