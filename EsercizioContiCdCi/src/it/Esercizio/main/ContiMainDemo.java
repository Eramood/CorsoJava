package it.Esercizio.main;



import java.time.LocalDate;

import it.Esercizio.Conti.ContoCorrente;
import it.Esercizio.Conti.ContoDeposito;
import it.Esercizio.Conti.ContoInvestimenti;


public class ContiMainDemo {

	public static void main(String[] args) {
		ContoCorrente contoCorrente = new ContoCorrente("Franco");
		ContoDeposito contoDeposito = new ContoDeposito("Luca");
		ContoInvestimenti contoInvestimenti = new ContoInvestimenti("Maria");
		
		LocalDate dataChiusura =LocalDate.of(2021, 12, 31);
		
		LocalDate dataTest1 =LocalDate.of(2022, 1, 1);
		LocalDate dataTest2 =LocalDate.of(2023, 1, 1);
		LocalDate dataTest3 =LocalDate.of(2024, 1, 1);
		
		
		
		contoInvestimenti.chiudiConto();
		
		
		contoCorrente.versamento(100.00,dataTest1 );
		contoCorrente.preleva(50.00,dataTest2);
		contoCorrente.versamento(100.00, dataTest3);
		contoCorrente.preleva(1000.00,dataChiusura);
		contoCorrente.chiudiConto();
	 		
		contoDeposito.versamento(100.00,dataTest1 );
		contoDeposito.preleva(1001, dataTest3);
		contoDeposito.chiudiConto();
		
	}
 
	}

