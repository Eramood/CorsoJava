package it.Esercizio.Conti;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.Esercizio.Movimenti.Movimento;

public class Conto {
	protected String titolare;
    protected LocalDate dataApertura;
    protected LocalDate dataUltimoAggiornamento;
    protected double saldo;
    private ArrayList<Movimento> movimenti;
    private static final String PATHFILE = "./PdfFolder"; //destinazione del file creato
    protected double interesseComplessivo;
    protected double percTassa = 26;
    
    private static final Logger logger = LogManager.getLogger(Conto.class);
    
    public Conto(String titolare) {
        this.titolare = titolare;
        this.dataApertura = null;  // Data di apertura nel 2021
        this.dataUltimoAggiornamento = null;
        this.saldo = 0; // Saldo iniziale di 1000 euro
        this.movimenti = new ArrayList<>();
        this.interesseComplessivo =0;
    }
    
    public void aperturaConto() {
    	saldo = 1000;
    	dataApertura =LocalDate.of(2021, 1, 1);
    	dataUltimoAggiornamento = dataApertura;
    	Movimento movimento = new Movimento(dataApertura, "Apri conto", 0 ,saldo ,saldo);
    	movimenti.add(movimento);
    }
    
    public double generaInteressi(double tassoInteresseAnnuo, LocalDate dataPrimoInteresse) {
    	DecimalFormat df = new DecimalFormat("0.00");
    	double saldoParziale = saldo;
        double percentualeGiornaliera= tassoInteresseAnnuo / 365;
        int giorniTrascorsi = (int) ChronoUnit.DAYS.between(dataApertura, dataPrimoInteresse);
        double interesseGiornaliero = (saldo * percentualeGiornaliera) / 100;
        double interesseApertura = interesseGiornaliero * giorniTrascorsi;
        double interesseParziale = interesseComplessivo;
        interesseComplessivo += interesseApertura;
        Movimento movimento = new Movimento(dataPrimoInteresse, "Agg int",interesseApertura,interesseParziale ,interesseComplessivo);
        movimenti.add(movimento);
        logger.info("Calcolo interessi - Data iniziale: {},Data Del Aggiornata: {} , Giorni Trascorsi: {},Saldo base:{},Perc Giornaliera:{}, Interesse Giornaliero: {}, , Interesse di Apertura: {},, Interesse Complessivo: {} ",
        		dataApertura,dataPrimoInteresse, giorniTrascorsi,df.format(saldo),percentualeGiornaliera ,df.format(interesseGiornaliero),df.format(interesseApertura),df.format(interesseComplessivo));
        return interesseApertura;
    }
    
    
  public double aggiornaInteressi(double tassoInteresseAnnuo, LocalDate dataUltimoAggiornamento,LocalDate dataMovimento) {
	  DecimalFormat df = new DecimalFormat("0.00");
	 
	  double percentualeGiornaliera= tassoInteresseAnnuo / 365;
        int giorniTrascorsi = (int) ChronoUnit.DAYS.between(dataUltimoAggiornamento, dataMovimento);
        double interesseGiornaliero = (saldo * percentualeGiornaliera) / 100;
        double interesse = interesseGiornaliero * giorniTrascorsi;
        double interesseParziale = interesseComplessivo;
        interesseComplessivo += interesse;
       Movimento movimento = new Movimento(dataMovimento, "Int attuale",interesse,interesseParziale ,interesseComplessivo);
       movimenti.add(movimento);
       logger.info("Calcolo interessi - Data iniziale: {},Data Del Aggiornata: {} , Giorni Trascorsi: {},Saldo base:{},Perc Giornaliera:{}, Interesse Giornaliero: {}, Interesse totale: {}, Interessi Totali: {}   ",
    		       dataUltimoAggiornamento,dataMovimento, giorniTrascorsi,df.format(saldo),df.format(percentualeGiornaliera),df.format(interesseGiornaliero),df.format(interesse),df.format(interesseComplessivo));
        return interesse;
    }
    
  	public void chiusuraAnno(double tassoInteresseAnnuo) {
  		DecimalFormat df = new DecimalFormat("0.00");
  		double saldoParziale = saldo;
  		int annoPrecedente =dataUltimoAggiornamento.getYear();
  		int annoSuccessivo = annoPrecedente + 1;
  		LocalDate dataAggiornata = LocalDate.of(annoPrecedente, 12, 31);
  		double percentualeGiornaliera= tassoInteresseAnnuo / 365;
        int giorniTrascorsi = (int) ChronoUnit.DAYS.between(dataUltimoAggiornamento, dataAggiornata);
        double interesseGiornaliero = (saldo * percentualeGiornaliera) / 100;
        double interesseChiusura = interesseGiornaliero * giorniTrascorsi;
        interesseComplessivo += interesseChiusura;
        double saldoFin = saldo + interesseChiusura;
        saldo = saldoFin;
        logger.info("Chiusura anno- Data1: {}, Data2: {}, Giorni trascorsi:{},Saldo base:{},Perc Giornaliera:{} ,Interessi giornaliero: {},Interesse Calcolato: {}, Interesse Complessivo {} ",
        				dataUltimoAggiornamento,dataAggiornata,giorniTrascorsi,df.format(saldoParziale),percentualeGiornaliera,df.format(interesseGiornaliero),df.format(interesseChiusura),df.format(interesseComplessivo));
        LocalDate dataAggiornataNuova = LocalDate.of(annoSuccessivo, 1, 1);
        dataUltimoAggiornamento = dataAggiornataNuova;
  		Movimento movimento = new Movimento(dataAggiornata, "Int lordo",interesseChiusura,saldoParziale ,saldo);
        movimenti.add(movimento);
        tassazione(dataAggiornata);
  	}
    
    
    
    
    public void chiudiconto(double tassoInteresseAnnuo, LocalDate dataOdierna) {
    	double interesse=0;
    	double saldoParziale = saldo;
    	if(dataUltimoAggiornamento != null) {
    	interesse= aggiornaInteressi(tassoInteresseAnnuo,dataUltimoAggiornamento,dataOdierna);
    	saldo += interesseComplessivo;
    	Movimento movimento = new Movimento(dataOdierna, "Aggiornamento", +interesseComplessivo, saldoParziale,saldo);
    	 movimenti.add(movimento);
    	}else {
    	interesse= generaInteressi(tassoInteresseAnnuo ,dataOdierna);
    	saldo += interesseComplessivo;
    	Movimento movimento = new Movimento(dataOdierna, "Aggiornamento", +interesseComplessivo,saldoParziale ,saldo);
    	movimenti.add(movimento);
    	}
   
    }
    
    
    public void preleva(double importo, double tassoInteresseAnnuo,LocalDate dataMovimento) {
    	LocalDate datamov = dataMovimento;
    	if(importo <= saldo) {
    	
    	if(dataUltimoAggiornamento != null) {
    		if(datamov.getYear() > dataUltimoAggiornamento.getYear() ) {
        		chiusuraAnno(tassoInteresseAnnuo);
        	}
    	aggiornaInteressi(tassoInteresseAnnuo,dataUltimoAggiornamento,datamov);
    	}else {
    	generaInteressi(tassoInteresseAnnuo ,datamov);
    	}
    	double saldoParziale = saldo;
        saldo -= importo;
       // System.out.println("prelievo" + importo +" Saldo pre prelievo + interessi: "+ saldoParziale + " Saldo dopo prelievo: "+saldo);
        Movimento movimento = new Movimento(datamov, "Prelievo", importo,saldoParziale ,saldo);
        movimenti.add(movimento);
    	 dataUltimoAggiornamento = datamov;
    	 logger.info("Prelievo- Data prelievo: {}, Importo: {}, Saldo parziale{}, Saldo Finale{}",
    			 datamov,importo,saldoParziale,saldo);
    	}else {
    		System.out.println("Operazione fallita l'importo supera il saldo :" + saldo);
    	}
    }

    public void versamento(double importo, double tassoInteresseAnnuo,LocalDate dataMovimento) {
    	//System.out.println("Versamento----------");
    	
    	LocalDate datamov = dataMovimento;
    	if(dataUltimoAggiornamento != null) {
    		if(datamov.getYear() > dataUltimoAggiornamento.getYear() ) {
        		chiusuraAnno(tassoInteresseAnnuo);
        		//System.out.println("Chiusura anno---------------<"); 
        	}
    	aggiornaInteressi(tassoInteresseAnnuo,dataUltimoAggiornamento,datamov);
    	}else {
    	generaInteressi(tassoInteresseAnnuo ,datamov);	
    	}
    	double saldoParziale = saldo;
        saldo += importo;
        Movimento movimento = new Movimento(dataMovimento, "Versamento", importo,saldoParziale ,saldo);
        movimenti.add(movimento);
        dataUltimoAggiornamento = datamov;
        logger.info("Versamento- Data Versamento: {}, Importo: {}, Saldo parziale{}, Saldo Finale{}",
   			 datamov,importo,saldoParziale,saldo);
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
    
    private static LocalDate dataCasuale(LocalDate dataUltimoAggiornamento) {
		Random random = new Random();
		LocalDate dataDaCalcolo = dataUltimoAggiornamento;
		 int giorniDaAggiungere = random.nextInt(183);
		 LocalDate dataCasuale = dataDaCalcolo.plusDays(giorniDaAggiungere);
	     return dataCasuale;
	}
    
    public String EcIntestazioneFormat() {
		return "|"+StringUtils.rightPad("data",13)+"|"+
					"|"+StringUtils.rightPad("Operazione",13)+"|"+
						"|"+StringUtils.rightPad("importo",10)+"|"+
						"|"+StringUtils.rightPad("Saldo Parziale",10)+"|"+
						"|"+StringUtils.rightPad("Saldo",10)+"|";
	}
    
    public void estrattoContoPdf(double tassoInteresseAnnuo){
    	LocalDate dataRiferimento = LocalDate.now();
    	if(dataRiferimento.getYear() > dataUltimoAggiornamento.getYear() ) {
    		chiusuraAnno(tassoInteresseAnnuo);
    	}
    	DecimalFormat df = new DecimalFormat("0.00");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	String nomeFile =PATHFILE+"/EC_"+titolare+"_"+dataRiferimento+".pdf";
    	chiudiconto(tassoInteresseAnnuo, dataRiferimento);
    	try {
    	Document document = new Document();
    	 FileOutputStream outputStream = 
		    		new FileOutputStream(nomeFile);
		    
		    //Create PDFWriter instance.
	        PdfWriter.getInstance(document, outputStream);
	        
	        //Create Font objects
	        Font font = new Font(Font.FontFamily.COURIER, 12);
	        document.open();
	        document.add(new Paragraph(StringUtils.rightPad("-",130,"-")));
	        document.add(new Paragraph( "Data: "+ dataRiferimento +" | "+ "Titolare: "+titolare+" | "+"Tasso: "+ tassoInteresseAnnuo   , font));
	        document.add(new Paragraph(StringUtils.rightPad("-",130,"-")));
	        document.add(new Paragraph(EcIntestazioneFormat()  , font));
	        for (Movimento movimento : movimenti) {
	        	 String dataFormattata = movimento.getData().format(formatter);
	        	
	        	 document.add(new Paragraph(
	        			 		"|"+StringUtils.rightPad(dataFormattata,13)+"|"+
	        			 				"|"+StringUtils.rightPad(movimento.getTipoOperazione(),13)+"|"+
	        			 				"|"+StringUtils.rightPad(df.format(movimento.getQuantita()),10)+"|"+
	        			 						"|"+StringUtils.rightPad(df.format(movimento.getSaldoParziale()),14)+"|"+
	        			 						"|"+StringUtils.rightPad(df.format(movimento.getSaldo()),10)+"|", font));
	           }
	        document.add(new Paragraph(StringUtils.rightPad("-",130,"-")));
	        document.add(new Paragraph("|"+StringUtils.rightPad("Saldo Totale Attuale",56)+"|"+
	 										"|"+StringUtils.rightPad(df.format(saldo), 10)+"|",font));
	        document.close();
	        outputStream.close();
    	
    	}catch (Exception e) {
			e.printStackTrace();
	       }
    }
    
    public void tassazione(LocalDate dataAggiornata) {
    	DecimalFormat df = new DecimalFormat("0.00");
    	//System.out.println("interesse complessivo:" +interesseComplessivo);
    	double tasse = (interesseComplessivo * percTassa )/100;
    	Double interessiNetti = interesseComplessivo - tasse;
    	saldo -= interesseComplessivo;
    	double saldoParziale = saldo;
    	double saldoAlNetto = saldo + interessiNetti;
    	saldo = saldoAlNetto;
    	logger.info("Tassazione: Data iniziale: {}, Data Aggiornata: {}, Interessi Totali: {}, Tassa al 26%: {}, Interesse Netto: {} ",
    			dataAggiornata,dataUltimoAggiornamento, df.format(interesseComplessivo), df.format(tasse),df.format(interessiNetti),df.format(interesseComplessivo));
    	Movimento movimento2 = new Movimento(dataAggiornata, "Tassa int",interesseComplessivo,-tasse ,interessiNetti);
    	movimenti.add(movimento2);
    	Movimento movimento = new Movimento(dataAggiornata, "Int netto",interessiNetti,saldoParziale ,saldo);
        movimenti.add(movimento);
        interesseComplessivo =0;
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
