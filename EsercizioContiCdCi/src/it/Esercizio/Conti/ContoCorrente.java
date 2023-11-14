package it.Esercizio.Conti;

import java.time.LocalDate;

public class ContoCorrente extends Conto {
	private final double tassoInteresseAnnuo = 7;
    

    public ContoCorrente(String titolare) {
        super(titolare);
        super.aperturaConto();
    }

    public void generaInteressi(LocalDate dataChiusura) {
       super.generaInteressi(tassoInteresseAnnuo, dataChiusura );
    }
    /*
    public void chiudiConto() {
    	super.chiudiconto();
    	super.stampaMovimenti();
    }
    */
    public void versamento(double importo, LocalDate dataMovimento) {
    	super.versamento(importo, tassoInteresseAnnuo,dataMovimento);
    }
    public void prelievo(double importo, LocalDate dataMovimento) {
    	super.preleva(importo, tassoInteresseAnnuo, dataMovimento);
    }
 
    public void estrattoContoPdf() {
    	super.estrattoContoPdf(tassoInteresseAnnuo);
    }
    
    public void chiusuraAnno() {
    	super.chiusuraAnno(tassoInteresseAnnuo);
    }
    
	
}
