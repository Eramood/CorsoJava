package ContoCorrente.main;

import ContoCorrente.ope.ContoCorrente;
import ContoCorrentista.model.Correntista;

import java.util.Scanner;

public class MainPage {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		Correntista correntista = new Correntista();
        correntista.RegCorrentista();
        
        
        ContoCorrente conto= new ContoCorrente(correntista);
        conto.calcoloBonus();
        
        
        //conto.prelievo();
        //conto.versamento();
        
        System.out.println("Benvenuto: "+ correntista.getNome());
        //System.out.println("data di nascita "+ correntista.getDataDiNascita()); l'ho usato in fase di debug

        Menu menu = new Menu(conto);
        menu.mostraMenu();
      /*
        boolean scelta = true; 
        
        while(scelta) {
        	
        	System.out.println("vuoi eseguire operazioni sul conto?: \n SI No");
        	
        	String risposta = scanner.nextLine();
        	
        	if(risposta.contains("s") || risposta.contains("si")) {
        		System.out.println("scegli che operazione effettuare: \n -> v=Versamento \n -> p=Prelievo \n -> c=Chiudi il conto");
        		String operazione = scanner.nextLine();
        			
        			if(operazione.contains("v")) {
        				conto.versamento();
        			}else if(operazione.contains("p")) {
        				conto.prelievo();
        			}else if (operazione.contains("c")) {
        				conto.chiudiContoPdf();
        				conto.chiudiContoCsv();
        			}else {
        				System.out.println("operazione non valida");
        			}
        		
        	}else if (risposta.contains("n") || risposta.contains("no")){
        		System.out.println("Chiusura delle operazioni...Stampo l'estratto conto");
        		conto.chiudiContoCsv();
        		conto.chiudiContoPdf();
        		scelta=false;
        	}else {
        		System.out.println("scelta non valida");
        	}
        }
       */
       
        
       
        
        System.out.println("il saldo finale è: " + conto.getSaldo() + "Euro");
        scanner.close();
	}

}
