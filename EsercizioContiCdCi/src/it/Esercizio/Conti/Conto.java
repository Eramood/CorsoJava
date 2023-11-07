package it.Esercizio.Conti;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

import it.Esercizio.Movimenti.Movimento;

public class Conto {
	protected String titolare;
    protected LocalDate dataApertura;
    protected LocalDate dataUltimoAggiornamento;
    protected double saldo;
    private ArrayList<Movimento> movimenti;
    
    
    public Conto(String titolare) {
        this.titolare = titolare;
        this.dataApertura = LocalDate.of(2021, 1, 1); // Data di apertura nel 2021
        this.dataUltimoAggiornamento = null;
        this.saldo = 1000.0; // Saldo iniziale di 1000 euro
        this.movimenti = new ArrayList<>();
    }
    
    public void generaInteressi(double tassoInteresseAnnuo, LocalDate dataChiusura) {
        
        System.out.println("saldo iniziale " + saldo);
        double percentualeGiornaliera= tassoInteresseAnnuo / 365;
        int giorniTrascorsi = (int) ChronoUnit.DAYS.between(dataApertura, dataChiusura);
        System.out.println("Giorni trascorsi :"+giorniTrascorsi);
        double interesseGiornaliero = (saldo * percentualeGiornaliera) / 100;
        double saldoFin = saldo + interesseGiornaliero * giorniTrascorsi;
        System.out.println("saldo complessivo " + saldoFin);
        saldo = saldoFin;
        
    }
  public void aggiornaInteressi(double tassoInteresseAnnuo, LocalDate dataUltimoAggiornamento,LocalDate dataMovimento) {
	  System.out.println("saldo iniziale " + saldo);  
	  double percentualeGiornaliera= tassoInteresseAnnuo / 365;
        int giorniTrascorsi = (int) ChronoUnit.DAYS.between(dataUltimoAggiornamento, dataMovimento);
        double interesseGiornaliero = (saldo * percentualeGiornaliera) / 100;
        double saldoFin = saldo + interesseGiornaliero * giorniTrascorsi;
        System.out.println("saldo complessivo " + saldoFin);
        saldo = saldoFin;
    }
    
    
    
    
    
    public void chiudiconto(double tassoInteresseAnnuo) {
    	//LocalDate dataOdierna = LocalDate.now();
    	LocalDate dataOdierna = LocalDate.of(2023, 1, 1);
    	double saldoParziale = saldo;
    	if(dataUltimoAggiornamento != null) {
    	 aggiornaInteressi(tassoInteresseAnnuo,dataUltimoAggiornamento,dataOdierna);
    	 Movimento movimento = new Movimento(dataOdierna, "Chiusura", 0, saldoParziale,saldo);
    	 movimenti.add(movimento);
    	}else {
    	generaInteressi(tassoInteresseAnnuo ,dataOdierna);
    	Movimento movimento = new Movimento(dataOdierna, "Chiusura", 0,saldoParziale ,saldo);
    	movimenti.add(movimento);
    	}
    }
    
    
    public void preleva(double importo, double tassoInteresseAnnuo,LocalDate dataMovimento) {
    	
    	LocalDate datamov = dataMovimento;
    	if(dataUltimoAggiornamento != null) {
    	aggiornaInteressi(tassoInteresseAnnuo,dataUltimoAggiornamento,datamov);
    	}else {
    	generaInteressi(tassoInteresseAnnuo ,datamov);
      //  double percentualeGiornaliera= tassoInteresseAnnuo / 365;
      //  int giorniTrascorsi = (int) ChronoUnit.DAYS.between(dataApertura, datamov);
      // double interesseGiornaliero = (saldo * percentualeGiornaliera) / 100;
      //  double saldoInteressi = saldo + interesseGiornaliero * giorniTrascorsi;
      //  saldo = saldoInteressi;
    	}
    	double saldoParziale = saldo;
        saldo -= importo;
        Movimento movimento = new Movimento(datamov, "Prelievo", importo,saldoParziale ,saldo);
        movimenti.add(movimento);
    	    dataUltimoAggiornamento = datamov;
    }

    public void versamento(double importo, double tassoInteresseAnnuo,LocalDate dataMovimento) {
    	LocalDate datamov = dataMovimento;
    	if(dataUltimoAggiornamento != null) {
    	aggiornaInteressi(tassoInteresseAnnuo,dataUltimoAggiornamento,datamov);
    	}else {
    	generaInteressi(tassoInteresseAnnuo ,datamov);	
    		/*
        double percentualeGiornaliera= tassoInteresseAnnuo / 365;
        int giorniTrascorsi = (int) ChronoUnit.DAYS.between(dataApertura, dataMovimento);
   
        double interesseGiornaliero = (saldo * percentualeGiornaliera) / 100;
        double saldoInteressi = saldo + interesseGiornaliero * giorniTrascorsi;
        saldo = saldoInteressi;
        */
    	}
    	double saldoParziale = saldo;
        saldo += importo;
        Movimento movimento = new Movimento(dataMovimento, "Versamento", importo,saldoParziale ,saldo);
        movimenti.add(movimento);
        dataUltimoAggiornamento = datamov;
        System.out.println();
    }

    public void stampaMovimenti() {
        System.out.println("Elenco dei movimenti del conto:");
        for (Movimento movimento : movimenti) {
            System.out.println("Data: " + movimento.getData());
            System.out.println("Tipo Operazione: " + movimento.getTipoOperazione());
            System.out.println("Saldo parziale: " + movimento.getSaldoParziale());
            System.out.println("Quantità: " + movimento.getQuantita());
            System.out.println("Saldo Dopo l'operazione: " + movimento.getSaldo());
            System.out.println("-------------");
        }
    }
    
    private static LocalDate dataCasuale() {
    	//da migliorare
		Random random = new Random();
		LocalDate dataDaCalcolo = LocalDate.now();
		
		 int giorniSottratti = random.nextInt(730);
		 LocalDate dataPassataCasuale = dataDaCalcolo.minusDays(giorniSottratti);
	
	     return dataPassataCasuale;
	}
    
    
	public String getTitolare() {
		return titolare;
	}

	public void setTitolare(String titolare) {
		this.titolare = titolare;
	}

	public LocalDate getDataApertura() {
		return dataApertura;
	}

	public void setDataApertura(LocalDate dataApertura) {
		this.dataApertura = dataApertura;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
    
}
