package it.ContrattiTelefonici.Type;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import it.ContrattiTelefonici.Chiamate.Chiamate;


public class ContrattoTelefonico {
	    private String numeroTel;
	    private String utente;
	    private double costoTelefonate; //costo complessivo -> bolletta
	    private int numTelefonate; //contatore delle chiamate
	    private ArrayList<Chiamate> telefonate;
	    

	    private static final double COSTO_AL_SECONDO = 0.01;
	    private static final String PATHFILE = "./PdfFolder"; //destinazione del file creato

	    public ContrattoTelefonico(String numeroTel, String utente) {
	        this.numeroTel = numeroTel;
	        this.utente = utente;
	        this.costoTelefonate = 0.0;
	        this.numTelefonate = 0;
	        this.telefonate = new ArrayList<>();
	    }
	    
	    public void aggiornaBolletta(int sec, String tipoChiamata) {
	    	LocalDate datarandom = dataCasuale();
	    	double sconto=0;
	    	double costoScontato=0;
	    	if (tipoChiamata.contains("Entrata")){
	    	double costoSingolaChiamata = 0*COSTO_AL_SECONDO;
		       costoTelefonate =costoTelefonate + costoSingolaChiamata;
		       numTelefonate++;
		       telefonate.add(new Chiamate(sec, tipoChiamata, costoSingolaChiamata,datarandom,sconto,costoScontato));
			}else {
				double costoSingolaChiamata = sec*COSTO_AL_SECONDO;
	        		if(datarandom.getMonthValue()==7 || datarandom.getMonthValue()==8 ) {
	        		sconto = (costoSingolaChiamata * 40)/100;
	        		costoScontato = costoSingolaChiamata - sconto;
	        		costoTelefonate =costoTelefonate +costoScontato;
	        		numTelefonate++;
	        		telefonate.add(new Chiamate(sec, tipoChiamata, costoSingolaChiamata,datarandom,sconto,costoScontato));
	        		}else {
	        			costoTelefonate =costoTelefonate + costoSingolaChiamata;
	        			numTelefonate++;
	        			telefonate.add(new Chiamate(sec, tipoChiamata, costoSingolaChiamata,datarandom,sconto,costoScontato));
	        		}
			 }
	    }
	    
	    	public double costoMedio() {
	    	double costoMedioCalcolato;
	    	double costoCalcolato=0;
	    	int numeroCallUscita=0;
	    	ArrayList<Chiamate> telefonate = getTelefonate();
	        for (int i = 0; i < telefonate.size(); i++) {
	            Chiamate telefonata = telefonate.get(i);
	            if(telefonata.getTipoChiamata().contains("Uscita")) {
	            	numeroCallUscita++;
	            
	            	costoCalcolato = costoCalcolato + telefonata.getCosto();
	            }
	        }
	        if(costoCalcolato == 0) {
	        	costoMedioCalcolato = 0;
	        }else {
	        	costoMedioCalcolato = costoCalcolato/ numeroCallUscita ;
	        }
	        			
			//System.out.println(costoMedioCalcolato);
			return costoMedioCalcolato;
		}
	    	
	    	
	    	private int durataMediaUscita() {
		    	int durataMediaUscita;
		    	int durataCalcolata=0;
		    	int numeroCallUscita=0;
		    	ArrayList<Chiamate> telefonate = getTelefonate();
		        for (int i = 0; i < telefonate.size(); i++) {
		            Chiamate telefonata = telefonate.get(i);
		            if(telefonata.getTipoChiamata().contains("Uscita")) {
		            	numeroCallUscita++;
		            	durataCalcolata = durataCalcolata + telefonata.getDurata();
		            }
		        }
		        if(durataCalcolata == 0) {
		        	durataMediaUscita = 0;
		        }else {
		        durataMediaUscita = durataCalcolata/ numeroCallUscita ;
		        }
				//System.out.println(durataMediaUscita);
				return durataMediaUscita;
			}
	    	
	    	
	    	 int durataMediaEntrata() {
		    	int durataMediaEntrata;
		    	int durataCalcolata=0;
		    	int numeroCallEntrata=0;
		    	ArrayList<Chiamate> telefonate = getTelefonate();
		        for (int i = 0; i < telefonate.size(); i++) {
		            Chiamate telefonata = telefonate.get(i);
		            if(telefonata.getTipoChiamata().contains("Entrata")) {
		            	numeroCallEntrata++;
		            	durataCalcolata = durataCalcolata + telefonata.getDurata();
		            }
		        }
		        if(durataCalcolata == 0) {
		        	durataMediaEntrata = 0;
		        }else {
		        durataMediaEntrata = durataCalcolata/ numeroCallEntrata ;
		        }
				//System.out.println(durataMediaUscita);
				return durataMediaEntrata;
			}
	    
	    	
	    protected  String getDatiUtente() {
	    	DecimalFormat df = new DecimalFormat("0.00");
	    	String formatCosto = df.format(getBolletta());
	        String datiUtenti = "||Utente: "+ utente+"||"+numeroTel+"||numero chiamate: "+numTelefonate+"||Da pagare: "+formatCosto+"||";
	        return datiUtenti;
	    }
	    
	    public double getBolletta() {
	    	return costoTelefonate;
	    }
	    
	    
		public String getNumeroTel() {
			return numeroTel;
		}
		public void setNumeroTel(String numeroTel) {
			this.numeroTel = numeroTel;
		}
		


		public int getNumTelefonate() {
			return numTelefonate;
		}
		public void setNumTelefonate(int numTelefonate) {
			this.numTelefonate = numTelefonate;
		}
		
		public void aggiornaCosto(int sec ,double costoAggiuntivo , String tipoChiamata) {
			LocalDate datarandom = dataCasuale();
			double sconto=0;
	    	double costoScontato=0;
			if (tipoChiamata.contains("Entrata")){
		    	double costoSingolaChiamata = 0*COSTO_AL_SECONDO+costoAggiuntivo;
			       if(datarandom.getMonthValue()==7 || datarandom.getMonthValue()==8 ) {
		        		sconto = (costoSingolaChiamata * 40)/100;
		        		costoScontato = costoSingolaChiamata - sconto;
		        		costoTelefonate =costoTelefonate +costoScontato;
		        		//System.out.println("sconto :" +sconto+" Valore scontato: "+costoScontato);
		        		numTelefonate++;
		        		telefonate.add(new Chiamate(sec, tipoChiamata, costoSingolaChiamata,datarandom,sconto,costoScontato));
		        		}else {
		        			costoTelefonate =costoTelefonate + costoSingolaChiamata;
		        			numTelefonate++;
		        			telefonate.add(new Chiamate(sec, tipoChiamata, costoSingolaChiamata,datarandom,sconto,costoScontato));
		        		}
			}else {
				double costoSingolaChiamata = sec*COSTO_AL_SECONDO+costoAggiuntivo;
        		if(datarandom.getMonthValue()==7 || datarandom.getMonthValue()==8 ) {
        		sconto = (costoSingolaChiamata * 40)/100;
        		costoScontato = costoSingolaChiamata - sconto;
        		costoTelefonate =costoTelefonate +costoScontato;
        		//System.out.println("sconto :" +sconto+" Valore scontato: "+costoScontato);
        		numTelefonate++;
        		telefonate.add(new Chiamate(sec, tipoChiamata, costoSingolaChiamata,datarandom,sconto,costoScontato));
        		}else {
        			costoTelefonate =costoTelefonate + costoSingolaChiamata;
        			numTelefonate++;
        			telefonate.add(new Chiamate(sec, tipoChiamata, costoSingolaChiamata,datarandom,sconto,costoScontato));
        		}
			  }
			}
		
		
		
		public void stampaBolletta() {
			System.out.println("Stampo la Bolletta a video");
	    }
		
		public void ordinaDate() {
			List<Chiamate> chiamateList = getTelefonate();
			
			System.out.println("Lista prima dell'ordinamento:");
	        for (Chiamate chiamata : chiamateList) {
	            System.out.println(chiamata.getData());
	        }
	        //con lambda expression 
			 Collections.sort(chiamateList, (c1, c2) -> c1.getData().compareTo(c2.getData()));
			/*
	        Collections.sort(chiamateList, new Comparator<Chiamate>() {
	           public int compare(Chiamate c1, Chiamate c2) {
	                return c2.getData().compareTo(c1.getData());
	            }
	        });
	        */
	        
			 System.out.println("Lista dopo l'ordinamento:");
		        for (Chiamate chiamata : chiamateList) {
		            System.out.println(chiamata.getData());
		        }
		        
		}
		
		
		public String getUtente() {
			return utente;
		}
		public void setUtente(String utente) {
			this.utente = utente;
		}
		
		private static LocalDate dataCasuale() {
			Random random = new Random();
			LocalDate dataOggi = LocalDate.now();
			//System.out.println(dataOggi);
			 int giorniSottratti = random.nextInt(365);
			 LocalDate dataPassataCasuale = dataOggi.minusDays(giorniSottratti);
		    // System.out.println(dataPassataCasuale);
		    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    // String dataFormattata = dataPassataCasuale.format(formatter);
		     return dataPassataCasuale;
		}
		
		
		public String messageHeaderFormat() {
			return 
					"|"+StringUtils.rightPad("utente",10)+"|"+
					  "|"+StringUtils.rightPad("Numero",10)+"|"+
					  		"|"+StringUtils.rightPad("N Chiamate",10)+"|"+
					  			"|"+StringUtils.rightPad("Importo",10)+"|";
		}
	
		public String messageBodyFormat(String formatCosto) {
			return  "|"+StringUtils.rightPad(utente,10)+"|"+
					  "|"+StringUtils.rightPad(numeroTel,10)+"|"+
				  		"|"+StringUtils.rightPad(Integer.toString(numTelefonate),10)+"|"+
				  			"|"+StringUtils.rightPad(formatCosto,10)+"|";
		}
		
		
		public String headerDettaglioChiamateFormat() {
			return "|"+StringUtils.rightPad("data",10)+"|"+
  					"|"+StringUtils.rightPad("Secodi",7)+"|"+
						"|"+StringUtils.rightPad("Tipo",7)+"|"+
							"|"+StringUtils.rightPad("Costo",5)+"|"+
								"|"+StringUtils.rightPad("Sconto",6)+"|"+
									"|"+StringUtils.rightPad("TotScontao",10)+"|";
		}
		
		
		
		
		
		public void printBollettaPdf(String linea) {
			DecimalFormat df = new DecimalFormat("0.00");
			LocalDate data = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			try {
				
				LocalDate dataPdf = LocalDate.now();
				String formatCosto = df.format(costoTelefonate);
				
				
				
				String nomeFile =PATHFILE+"/Bolletta_"+utente+"_"+linea+"_"+dataPdf+".pdf";
				
				 Document document = new Document();
				 
				  String messageHeader ="Bolletta del:" +data+"||"+linea ; 
				  String messageHeader1 = messageHeaderFormat();
				  String messageBody = messageBodyFormat(formatCosto);
				  String headerDettaglioChiamate = headerDettaglioChiamateFormat();
						  			
	
				    //creazione istanza per l'output
				    FileOutputStream outputStream = 
				    		new FileOutputStream(nomeFile);
				    
				    //Create PDFWriter instance.
			        PdfWriter.getInstance(document, outputStream);
			        
			        //Create Font objects
			        Font font = new Font(Font.FontFamily.COURIER,15);
			        
			        document.open();
			        
			        document.add(new Paragraph( messageHeader , font));
			        document.add(new Paragraph( StringUtils.rightPad("*",49,"*"), font));
			        document.add(new Paragraph( messageHeader1 , font));
			        document.add(new Paragraph( messageBody , font));
			        document.add(new Paragraph( StringUtils.rightPad("*",49,"*"), font));
			        document.add(new Paragraph("Dettaglio delle Chiamate:"));
			        document.add(new Paragraph(headerDettaglioChiamate, font));
			        ArrayList<Chiamate> telefonate = getTelefonate();
			        for (int i = 0; i < telefonate.size(); i++) {
			            Chiamate telefonata = telefonate.get(i);
			            String costoSingola = df.format(telefonata.getCosto());
			            String scontoSingola = df.format(telefonata.getSconto());
			            String totSconto= df.format(telefonata.getTotaleScontato());
			            String dataFormattata = telefonata.getData().format(formatter);
			            document.add(new Paragraph(
			            		"|"+StringUtils.rightPad(dataFormattata,10)+"|"+
			            				"|"+StringUtils.rightPad(Integer.toString(telefonata.getDurata()),7)+"|"+
			            				"|"+StringUtils.rightPad(telefonata.getTipoChiamata(),7)+"|"+
			            				"|"+StringUtils.rightPad(costoSingola,5)+"|"+
			            				"|"+StringUtils.rightPad(scontoSingola,6)+"|"+
			            				"|"+StringUtils.rightPad(totSconto,10)+"|"
			            				,font));
			        }
			        document.add(new Paragraph("Il costo medio è :" + Double.toString(costoMedio()) , font));
			        document.add(new Paragraph("La durata media in secondi in uscita è :" + Integer.toString(durataMediaUscita()) , font));
			        document.add(new Paragraph("La durata media in secondi in entrata è :" + Integer.toString(durataMediaEntrata()) , font));
			        
			        document.close();
			        outputStream.close();
			        
		}catch (Exception e) {
			e.printStackTrace();
	       }
		}
		
		
		public void printBollettaPdf(String linea,String citta) {
			DecimalFormat df = new DecimalFormat("0.00");
			LocalDate data = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			try {
				LocalDate dataPdf = LocalDate.now();
				String formatCosto = df.format(costoTelefonate);
				
				
				
				String nomeFile =PATHFILE+"/Bolletta_"+utente+"_"+linea+"_"+dataPdf+".pdf";
				
				 Document document = new Document();
				 
				  String messageHeader ="Bolletta del:" +data+"||"+linea+"||"+citta ; 
				  String messageHeader1 = messageHeaderFormat();
				  String messageBody = messageBodyFormat(formatCosto);
	 			  String headerDettaglioChiamate = headerDettaglioChiamateFormat();
				  
				
				    //creazione istanza per l'output
				    FileOutputStream outputStream = 
				    		new FileOutputStream(nomeFile);
				    
				    //Create PDFWriter instance.
			        PdfWriter.getInstance(document, outputStream);
			        
			        //Create Font objects
			        Font font = new Font(Font.FontFamily.COURIER,15);
			        
			        document.open();
	
			        document.add(new Paragraph( messageHeader , font));
			        document.add(new Paragraph( StringUtils.rightPad("*",49,"*"), font));
			        document.add(new Paragraph( messageHeader1 , font));
			        document.add(new Paragraph( messageBody , font));
			        document.add(new Paragraph( StringUtils.rightPad("*",49,"*"), font));
			        document.add(new Paragraph("Dettaglio delle Chiamate:"));
			        document.add(new Paragraph(headerDettaglioChiamate, font));
			        ArrayList<Chiamate> telefonate = getTelefonate();
			        for (int i = 0; i < telefonate.size(); i++) {
			            Chiamate telefonata = telefonate.get(i);
			            String dataFormattata = telefonata.getData().format(formatter);
			            String costoSingola = df.format(telefonata.getCosto());
			            String scontoSingola = df.format(telefonata.getSconto());
			            String totSconto= df.format(telefonata.getTotaleScontato());
			            document.add(new Paragraph(
			            		"|"+StringUtils.rightPad(dataFormattata,10)+"|"+
			            				"|"+StringUtils.rightPad(Integer.toString(telefonata.getDurata()),7)+"|"+
			            				"|"+StringUtils.rightPad(telefonata.getTipoChiamata(),7)+"|"+
			            				"|"+StringUtils.rightPad(costoSingola,5)+"|"+
			            				"|"+StringUtils.rightPad(scontoSingola,6)+"|"+
			            				"|"+StringUtils.rightPad(totSconto,10)+"|"
			            				,font));
			        }
			        document.add(new Paragraph("Il costo medio è :" + Double.toString(costoMedio()) , font));
			        document.add(new Paragraph("La durata media in secondi in uscita è :" + Integer.toString(durataMediaUscita()) , font));
			        document.add(new Paragraph("La durata media in secondi in entrata è :" + Integer.toString(durataMediaEntrata()) , font));

			        document.close();
			        outputStream.close();
			        
		}catch (Exception e) {
			e.printStackTrace();
	       }
		
		}
		
		
		protected void logBolletteTxt(String messageHeader ){
			DecimalFormat df = new DecimalFormat("0.00");
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dataTxt = LocalDate.now();
			String formatCosto = df.format(costoTelefonate);
			String nomeFile =PATHFILE+"/Bolletta_"+utente+"_"+dataTxt+".txt";
			try {
				File file =new File(nomeFile);
				FileWriter fileWriter= new FileWriter(file,true);
				BufferedWriter writer = new BufferedWriter(fileWriter);
				
				  String messageHeaderBody = messageHeader;
				  String messageHeader1 = messageHeaderFormat();
				  String messageBody = messageBodyFormat(formatCosto);
	 			  String headerDettaglioChiamate = headerDettaglioChiamateFormat();
				
	 			  	
				
	 			  	writer.write( messageHeaderBody);
	 			  	writer.newLine();
	 			  	writer.write( StringUtils.rightPad("*",49,"*"));
	 			  	writer.newLine();
	 			    writer.write( messageHeader1 );
	 			    writer.newLine();
	 			    writer.write( messageBody );
	 			    writer.newLine();
	 			    writer.write( StringUtils.rightPad("*",49,"*"));
	 			    writer.newLine();
	 			    writer.write("Dettaglio delle Chiamate:");
	 			    writer.newLine();
	 			    writer.write(headerDettaglioChiamate);
				
	 			    
	 			    
	 			   ArrayList<Chiamate> telefonate = getTelefonate();
			        for (int i = 0; i < telefonate.size(); i++) {
			            Chiamate telefonata = telefonate.get(i);
			            String dataFormattata = telefonata.getData().format(formatter);
			            String costoSingola = df.format(telefonata.getCosto());
			            String scontoSingola = df.format(telefonata.getSconto());
			            String totSconto= df.format(telefonata.getTotaleScontato());
			            writer.newLine();
			            writer.append(
			            		"|"+StringUtils.rightPad(dataFormattata,10)+"|"+
			            				"|"+StringUtils.rightPad(Integer.toString(telefonata.getDurata()),7)+"|"+
			            				"|"+StringUtils.rightPad(telefonata.getTipoChiamata(),7)+"|"+
			            				"|"+StringUtils.rightPad(costoSingola,5)+"|"+
			            				"|"+StringUtils.rightPad(scontoSingola,6)+"|"+
			            				"|"+StringUtils.rightPad(totSconto,10)+"|");
			        }
			    writer.newLine();
				writer.newLine();
				writer.close();	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		public ArrayList<Chiamate> getTelefonate() {
			return telefonate;
		}
		public void setTelefonate(ArrayList<Chiamate> telefonate) {
			this.telefonate = telefonate;
		}

		
		
}
