package it.Esercizio.Conti;

import java.time.LocalDate;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContoInvestimenti extends Conto {
private final double tassoInteresseAnnuo = interesseRandom();
private static final Logger logger = LogManager.getLogger(Conto.class);

	private double interesseRandom() {
		Random random = new Random();
		double interesse = random.nextInt(201) - 100;
		logger.debug("Interessi randomici: {}",interesse);
		return interesse;
	}
	
    public ContoInvestimenti(String titolare) {
        super(titolare);
        super.aperturaConto();
    }

    public void generaInteressi(LocalDate dataChiusura) {
       super.generaInteressi(tassoInteresseAnnuo, dataChiusura );
    }
    /*
    public void chiudiConto() {
    	super.chiudiconto(tassoInteresseAnnuo);
    	super.stampaMovimenti();
    }
    */
    public void versamento(double importo, LocalDate dataMovimento) {
    	super.versamento(importo, tassoInteresseAnnuo,dataMovimento);
    }
    public void preleva(double importo, LocalDate dataMovimento) {
    	if(importo<1000) {
    	super.preleva(importo, tassoInteresseAnnuo, dataMovimento);
    	}else {
    		System.out.println("Hai superato il limite di prelievo");
    	}
    }
    
    public void estrattoContoPdf() {
    	super.estrattoContoPdf(tassoInteresseAnnuo);
    }
    
    
    public void chiusuraAnno() {
    	super.chiusuraAnno(tassoInteresseAnnuo);
    }
}
