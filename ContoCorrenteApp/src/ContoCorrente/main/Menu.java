package ContoCorrente.main;

import java.util.Scanner;

import ContoCorrente.ope.ContoCorrente;

public class Menu {
	private ContoCorrente conto;
	
	public Menu(ContoCorrente conto) {
		this.conto = conto;
		
	}
	
	public void mostraMenu() {
		Scanner scanner = new Scanner(System.in);
        
		boolean scelta = true; 
        
        while(scelta) {
        	
        	System.out.println("vuoi eseguire operazioni sul conto?: \n |->s/SI<-| |->n/No<-|");
        	
        	String risposta = scanner.nextLine();
        	
        	if(risposta.contains("s") || risposta.contains("si")) {
        		System.out.println("scegli che operazione effettuare: \n--> v=Versamento \n--> p=Prelievo \n--> c=Chiudi il conto");
        		String operazione = scanner.nextLine();
        			
        			if(operazione.contains("v")) {
        				eseguiVersamento();
        			}else if(operazione.contains("p")) {
        				eseguiPrelievo();
        			}else if (operazione.contains("c")) {
        				eseguiChiusuraConto();
        				scelta=false;
        			}else {
        				System.out.println("operazione non valida");
        			}
        		
        	}else if (risposta.contains("n") || risposta.contains("no")){
        		System.out.println("Chiusura delle operazioni...Stampo l'estratto conto");
        		eseguiChiusuraConto();
        		scelta=false;
        	}else {
        		System.out.println("scelta non valida");
        	}
        }
        
	}
	private void eseguiPrelievo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quanto Vuoi Prelevare: ");
        double importo = scanner.nextDouble();
        conto.prelievo(importo);
    }

    private void eseguiVersamento() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci la quota da versare: ");
        double importo = scanner.nextDouble();
        conto.versamento(importo);
    }

    private void eseguiChiusuraConto() {
        conto.chiudiContoCsv();
        conto.chiudiContoPdf();
    }
	
	
}
