package it.ContrattiTelefonici.Main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.ContrattiTelefonici.Chiamate.Chiamate;
import it.ContrattiTelefonici.Type.ContrattoFisso;
import it.ContrattiTelefonici.Type.ContrattoMobile;
import it.ContrattiTelefonici.Type.ContrattoTelefonico;


public class MainClass {

	public static void main(String[] args) {
		Random random = new Random();
			
		
		ContrattoMobile contrattoMobile =new ContrattoMobile("3572687498" , "Mario");
		ContrattoFisso contrattoFisso = new ContrattoFisso("9871596420", "Luca","Palermo");
		
		
		simulaChiamata(contrattoFisso, 3);
		simulaChiamata(contrattoMobile, 3);
		 
		 
		 //System.out.println("costo Fisso = " + contrattoFisso.getBolletta());
		 //System.out.println("costo totale chiamate mobili = " + contrattoMobile.getBolletta());
		 
		 
		 
			contrattoFisso.stampaBolletta();//A video
			contrattoMobile.stampaBolletta();
			contrattoFisso.printBollettaPdf();
			contrattoMobile.printBollettaPdf();
			
			contrattoFisso.logBolletteTxt();
			contrattoMobile.logBolletteTxt();
			
			
			//contrattoFisso.costoMedio();
			//contrattoFisso.durataMediaUscita();
		 
		 
	}
	private static void simulaChiamata(ContrattoTelefonico contratto, int numChiamate) {
        Random rand = new Random();
        for (int i = 0; i < numChiamate; i++) {
            int seconds = rand.nextInt(100)+1;
            String tipo;
            if (rand.nextBoolean()) {
                tipo = "Entrata";
               // System.out.println(tipo);
                contratto.aggiornaBolletta(seconds, tipo);
            } else {
                tipo = "Uscita";
               // System.out.println(tipo);
                contratto.aggiornaBolletta(seconds, tipo);
            }
        }
    }
	
	

}

