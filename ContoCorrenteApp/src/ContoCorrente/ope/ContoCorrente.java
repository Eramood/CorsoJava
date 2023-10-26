package ContoCorrente.ope;



import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.opencsv.CSVWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import ContoCorrentista.model.Correntista;

public class ContoCorrente {

	private Correntista correntista;
	private double saldo;
	private double saldoIniziale;
	Scanner scanner = new Scanner(System.in);
	
	public ContoCorrente(Correntista correntista) {
		this.correntista=correntista;
		//inizializzo il saldo del conto.
		this.saldo=0;
		
	}
	
	public void calcoloBonus() {
		Date dataAttuale = new Date(); 
		LocalDate annoAttuale = LocalDate.now();;
		//effettuo il calcolo dell'eta.
		Period eta = Period.between(correntista.getDataDiNascita(),annoAttuale);
		//System.out.println("la tua eta è " + eta);
		
		//inizializzo e controllo il bonus da attribuire
		
		  double bonus=0;
		  
		  if(eta.getYears() >= 18 && eta.getYears() <= 30) { 
			  bonus= 100; 
			  }else if(eta.getYears() >= 31 && eta.getYears() <= 50) {
				 bonus= 150; 
		  }else{
			  bonus = 200; }
		  
		  double saldoIniziale =saldo;
		  saldo =saldo+bonus;
		  
		  
		  Movimento movimento = new Movimento("Bonus", bonus ,saldoIniziale,saldo);
			correntista.getMovimenti().add(movimento);
		  
		  System.out.println("Conto ti abbiamo accreditato un bonus di " + bonus);
	}

	 
	
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public void prelievo(double importo) {
		if (importo <= saldo) {
			
			double importoInz = saldo;
			
			saldo=saldo-importo;
			
			Movimento movimento = new Movimento("Prelievo", importo,importoInz ,saldo);
			correntista.getMovimenti().add(movimento);
			
			System.out.println("il saldo attuale è: " + saldo + " euro");
		}else {
			System.out.println("impossibile prelevare");
		}
	}
	
	public void versamento(double importo) {
		if (importo > 0) {
			
			double importoInz = saldo;
			saldo=saldo+importo;
			
			Movimento movimento = new Movimento("Versamento",importo,importoInz,saldo);
			correntista.getMovimenti().add(movimento);
			
			System.out.println("il saldo attuale è: " + saldo + " euro");
		}else {
			System.out.println("impossibile versare");
		}
	}
	
	public void chiudiContoPdf(){
		try {
		LocalDate dataChiusura = LocalDate.now();
		String nomeCorrentista = correntista.getNome();
		String destFile = "E:/Corsojava/workspace1/ContoCorrenteApp"; //destinazione del file creato
		String nomeFile =destFile+"/EC_"+ nomeCorrentista +"_"+dataChiusura+".pdf";
		
	    Document document = new Document();
	   
	    //creazione istanza per l'output
	    FileOutputStream outputStream = 
	    		new FileOutputStream(nomeFile);
	    
	    //Create PDFWriter instance.
        PdfWriter.getInstance(document, outputStream);
        
        //Create Font objects
        Font font = new Font(Font.FontFamily.COURIER,15);
        
        
        //Open the document.
        document.open();
 
        //Add content to the document.
        document.add(new Paragraph("Estratto conto"+" "+correntista.getNome()+ "|"+ dataChiusura , font));
        document.add(new Paragraph("_______________________________________________________", font));
        document.add(new Paragraph (StringUtils.rightPad("Tipo Operazione",10," ")+"|"+StringUtils.rightPad("Importo",10," ")+ "|"+StringUtils.rightPad("Saldo iniziale",10," ")+ "|"+StringUtils.rightPad("Saldo Finale",10," ")+"|",font));
        document.add(new Paragraph("_______________________________________________________", font));
        for(int i=0; i < correntista.getMovimenti().size(); i++) {
        	Movimento movimento = correntista.getMovimenti().get(i);
        	
        	
        	String stringImporto = Double.toString(movimento.getImporto());  // converto il valore importo double a string
        	String formatImporto = StringUtils.rightPad(stringImporto,10,"");
        	
        	String stringSaldoIniz = Double.toString(movimento.getSaldoPrima());
        	String formatSaldoIniz = StringUtils.rightPad(stringSaldoIniz,10,"");
        	
        	String stringSaldoFin = Double.toString(movimento.getSaldoDopo());
        	String formatSaldoFin = StringUtils.rightPad(stringSaldoFin,10,"");
        	
        	String formatTipoOpe = StringUtils.rightPad(movimento.getTipoOperazione(),14);
        	
        	document.add(new Paragraph("_______________________________________________________", font));
        	document.add(new Paragraph(formatTipoOpe+" | "+formatImporto+" | "+formatSaldoIniz+" | "+formatSaldoFin+" | ", font));
        }
        
    
        document.add(new Paragraph("Saldo Totale = "+ saldo));
 
        //Close document e outputStream.
        document.close();
        outputStream.close();
 
        System.out.println("Pdf creato");
		}catch (Exception e) {
			e.printStackTrace();
		
		
	}
 }
	
	public void chiudiContoCsv(){
		
		LocalDate dataChiusura = LocalDate.now();
		String nomeCorrentista = correntista.getNome();
		String destFile = "E:/Corsojava/workspace1/ContoCorrenteApp";//inserire la cartella di destinazione
		String file = destFile+"/EC_"+ nomeCorrentista +"_"+dataChiusura+".csv";
		try {
			
		FileWriter outputfile = new FileWriter(file);
		CSVWriter writer = new CSVWriter(outputfile);
		
		String stringaHeader="Correntista: "+ nomeCorrentista +" "+ "Data: "+dataChiusura;
		String[] header= {stringaHeader};
		writer.writeNext(header); 
		
		String[] header1 = { "Tipo operazione", "Importo","Saldo iniziale" ,"Saldo Finale" }; 
        writer.writeNext(header1); 
        
        for(int i=0; i < correntista.getMovimenti().size(); i++) {
        	Movimento movimento = correntista.getMovimenti().get(i);
        	
        	String stringImporto = Double.toString(movimento.getImporto());
        	String stringSaldoIniz = Double.toString(movimento.getSaldoPrima());
        	String stringSaldoFin = Double.toString(movimento.getSaldoDopo());
        	
        	String[] data = { movimento.getTipoOperazione(), stringImporto,stringSaldoIniz ,stringSaldoFin }; 
            writer.writeNext(data); 
        }
        
        String[] dataSaldo= {"Saldo Totale:",Double.toString(saldo)};
        writer.writeNext(dataSaldo);
        
		
		
		
		writer.close(); 
		}catch (Exception e) { 
	        e.printStackTrace(); 
		}
	}

	public double getSaldoIniziale() {
		return saldoIniziale;
	}

	public void setSaldoIniziale(double saldoIniziale) {
		this.saldoIniziale = saldoIniziale;
	}
}
	

