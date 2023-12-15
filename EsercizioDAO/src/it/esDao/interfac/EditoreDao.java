package it.esDao.interfac;

import java.util.List;

import it.esDao.model.Editore;


public interface EditoreDao {
	 	List<Editore> getAll();
	    Editore getEditoreById(String id_editore);
	    void insert(Editore editore);
	    void update(Editore editore);
	    void delete(Editore editore);
}
