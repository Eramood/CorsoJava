package ContoCorrente.ope;



import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;

import java.io.FileOutputStream;
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
		//estraggo la data odierna per il calcolo
		Date dataAttuale = new Date(); 
		//trovo l'anno di nasciat e l'anno attuale
		LocalDate annoAttuale = LocalDate.now();;
		//effettuo il calcolo dell'eta.
		Period eta = Period.between(correntista.getDataDiNascita(),annoAttuale);
		System.out.println("la tua eta è " + eta);
		
		//inizializzo e controllo il bonus da attribuire
		
		  double bonus=0;
		  
		  if(eta.getYears() >= 18 && eta.getYears() <= 30) { 
			  bonus= 100; 
			  }else if(eta.getYears() >= 31 && eta.getYears() <= 50) {
				 bonus= 150; 
		  }else{
			  bonus = 200; }
		  
		  saldoIniziale = saldo;
		  saldo = saldo + bonus;
		  
		  
		  Movimento movimento = new Movimento("Bonus", bonus ,saldoIniziale ,saldo);
			correntista.getMovimenti().add(movimento);
		  
		  System.out.println("Ti abbiamo accreditato un bonus di " + bonus +
		  "euro il tuo saldo attuale è di: "+ saldo + "euro")
		 ;
	}

	 
	
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public void prelievo() {
		double importo;
		System.out.println("inserisci l'importo da prelevare");
		importo = scanner.nextDouble();
		if (importo <= saldo) {
			
			double importoInz = saldo;
			
			saldo=saldo-importo;
			
			Movimento movimento = new Movimento("prelievo", importo,importoInz ,saldo);
			correntista.getMovimenti().add(movimento);
			
			System.out.println("il saldo attuale è: " + saldo + " euro");
		}else {
			System.out.println("impossibile prelevare");
		}
	}
	
	public void versamento() {
		double importo;
		System.out.println("inserisci l'importo da versare");
		importo = scanner.nextDouble();
		if (importo > 0) {
			
			double importoInz = saldo;
			
			saldo= saldo + importo;
			
			Movimento movimento = new Movimento("versamento", importo,importoInz ,saldo);
			correntista.getMovimenti().add(movimento);
			
			System.out.println("il saldo attuale è: " + saldo + " euro");
		}else {
			System.out.println("impossibile versare");
		}
	}
	
	public void chiudiConto(){
		try {
		LocalDate dataChiusura = LocalDate.now();
		String nomeCorrentista = correntista.getNome();
		String nomeFile = "EC_"+ nomeCorrentista +"_"+dataChiusura+".pdf";
		//String destFile = "E:\\Corsojava"; destinazione del file creato
		
	    Document document = new Document();
	    
	    //creazione istanza per l'output
	    FileOutputStream outputStream = 
	    		new FileOutputStream(nomeFile);
	    
	    //Create PDFWriter instance.
        PdfWriter.getInstance(document, outputStream);
        
      //Open the document.
        document.open();
 
        //Add content to the document.
        document.add(new Paragraph("Estratto conto"+" | "+correntista.getNome()+ " | "+ dataChiusura));
        
        document.add(new Paragraph (StringUtils.rightPad("Tipo Operazione",20," ")+"|"+StringUtils.rightPad("Importo",20," ")+ "|"+StringUtils.rightPad("Saldo iniziale",20," ")+ "|"+StringUtils.rightPad("Saldo Finale",20," ")+"|"));
        
        for(int i=0; i < correntista.getMovimenti().size(); i++) {
        	Movimento movimento = correntista.getMovimenti().get(i);
        	
        	String stringImporto = Double.toString(movimento.getImporto());  // converto il valore importo double a string
        	String formatImporto = StringUtils.rightPad(stringImporto,20,"");//uso la variabile formatImporto per formattare correttamente l'importo
        	
        	String stringSaldoIniz = Double.toString(movimento.getSaldoPrima());
        	String formatSaldoIniz = StringUtils.rightPad(stringSaldoIniz,20,"");
        	
        	String stringSaldoFin = Double.toString(movimento.getSaldoDopo());
        	String formatSaldoFin = StringUtils.rightPad(stringSaldoFin,20,"");
        	
        	
        	
        	document.add(new Paragraph(StringUtils.rightPad(movimento.getTipoOperazione(),20," ")+ "|"+formatImporto+"|"+formatSaldoIniz+"|"+formatSaldoFin+"|"));
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

	public double getSaldoIniziale() {
		return saldoIniziale;
	}

	public void setSaldoIniziale(double saldoIniziale) {
		this.saldoIniziale = saldoIniziale;
	}
}
	

