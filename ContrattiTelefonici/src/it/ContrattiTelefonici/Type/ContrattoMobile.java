package it.ContrattiTelefonici.Type;

import java.time.LocalDate;

public class ContrattoMobile extends ContrattoTelefonico {
	private static final double COSTO_ALLA_RISPOSTA = 1.5; //costante propria
	private static final String lINEA = "Mobile";
	

	public ContrattoMobile(String numeroTel, String utente) {
		super(numeroTel, utente);
	}
	
	@Override
	public void aggiornaBolletta(int sec,String tipoChiamata) {
       super.aggiornaCosto(sec,COSTO_ALLA_RISPOSTA, tipoChiamata);
          }
	
	
	public void printBollettaPdf() {
		super.ordinaDate();
		super.printBollettaPdf(lINEA);
	}
	
	public void logBolletteTxt() {
		LocalDate data = LocalDate.now();
		String messageHeader ="Bolletta del:" +data+"||"+lINEA+"||" ;
		super.logBolletteTxt(messageHeader);
		}
	
	@Override
	public void stampaBolletta() {
		LocalDate data = LocalDate.now();
		String messaggio = getDatiUtente();
		System.out.println("_________________________________________________________________");
		System.out.println("||Bolletta del: "+data+" || "+lINEA);
		System.out.println(messaggio);
		System.out.println("-----------------------------------------------------------------");
    }

}
	

