package it.esDao.model;

import java.util.Date;

public class Autore {
	    private int idAutore;
	    private String nomeA;
	    private String cognomeA;
	    private Date annoNascita;
	    private Date annoMorte;
	    private char sesso;
	    private String nazione;
		public int getIdAutore() {
			return idAutore;
		}
		public void setIdAutore(int idAutore) {
			this.idAutore = idAutore;
		}
		public String getNomeA() {
			return nomeA;
		}
		public void setNomeA(String nomeA) {
			this.nomeA = nomeA;
		}
		public String getCognomeA() {
			return cognomeA;
		}
		public void setCognomeA(String cognomeA) {
			this.cognomeA = cognomeA;
		}
		public Date getAnnoNascita() {
			return annoNascita;
		}
		public void setAnnoNascita(Date annoNascita) {
			this.annoNascita = annoNascita;
		}
		public Date getAnnoMorte() {
			return annoMorte;
		}
		public void setAnnoMorte(Date annoMorte) {
			this.annoMorte = annoMorte;
		}
		public char getSesso() {
			return sesso;
		}
		public void setSesso(char sesso) {
			this.sesso = sesso;
		}
		public String getNazione() {
			return nazione;
		}
		public void setNazione(String nazione) {
			this.nazione = nazione;
		}

	  
	
}
