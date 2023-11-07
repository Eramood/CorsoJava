package it.ContrattiTelefonici.Type;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContrattoFisso extends ContrattoTelefonico {
	private String citta;
	private static final String lINEA = "Fissa";

	public ContrattoFisso(String numeroTel, String utente,String citta) {
		super(numeroTel, utente);
		this.citta = citta;
	}
	

	public void printBollettaPdf() {
		super.ordinaDate();
		super.printBollettaPdf(lINEA,citta);
	}
	
		public void logBolletteTxt() {
		LocalDate data = LocalDate.now();
		String messageHeader ="Bolletta del:" +data+"||"+lINEA+"||"+citta ;
		super.logBolletteTxt(messageHeader);
		}
	
	
	@Override
	public void stampaBolletta() {
		LocalDate data = LocalDate.now();
		String messaggio = super.getDatiUtente();
		System.out.println("_________________________________________________________________");
		System.out.println("||Bolletta del: "+data+" || "+lINEA +" || " + citta );
		System.out.println(messaggio);
		System.out.println("-----------------------------------------------------------------");
    }
	

	 
}
