package it.esDao.interfac;
import java.util.List;

import it.esDao.model.Autore;

public interface AutoreDao {
	  List<Autore> getAll();
	    Autore getAutoreById(int idAutore);
	    void insert(Autore autore);
	    void update(Autore autore);
	    void delete(Autore autore);
}
