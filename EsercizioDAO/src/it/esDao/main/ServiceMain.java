package it.esDao.main;

import it.esDao.model.Libro;
import it.esDao.service.impl.LibroPrintService;

public class ServiceMain {

	public static void main(String[] args) {
		 LibroPrintService libroPrintService = new LibroPrintService();
		 Libro libro = new Libro();
		 libroPrintService.saveListAsTxt();
		 libroPrintService.saveAsTxt(libro);
	}

}
