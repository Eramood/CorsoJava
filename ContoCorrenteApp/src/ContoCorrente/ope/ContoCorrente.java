package ContoCorrente.ope;

import java.util.Date;
import java.util.Scanner;

import ContoCorrentista.model.Correntista;

public class ContoCorrente {

	private Correntista correntista;
	private double saldo;
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
		int annoAttuale= dataAttuale.getYear();
		int annoNascita= correntista.getDataDiNascita().getYear();
		//effettuo il calcolo dell'eta.
		int eta = annoAttuale-annoNascita;
		
		//inizializzo e controllo il bonus da attribuire
		double bonus=0;
		
		if(eta > 18 && eta < 30) {
			bonus= 100;
		}else if(eta > 31 && eta < 50) {
			bonus= 150;
		}else {
			bonus = 200;
		}
		
		saldo = saldo + bonus;
		
		System.out.println("Ti abbiamo accreditato un bonus di " + bonus + "euro il tuo saldo attuale è di: "+ saldo + "euro");
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
			saldo= saldo - importo;
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
			saldo= saldo + importo;
			System.out.println("il saldo attuale è: " + saldo + " euro");
		}else {
			System.out.println("impossibile versare");
		}
	}
}	
	

