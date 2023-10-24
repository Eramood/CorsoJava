package ContoCorrentista.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import ContoCorrente.ope.Movimento;

public class Correntista {
	
	private String nome;
	private LocalDate dataDiNascita;
	private List<Movimento> movimenti;
	
	 public Correntista() {
	        movimenti = new ArrayList<>();
	    }

	public void RegCorrentista() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("inserisci il tuo nome: ");
		this.nome=scanner.nextLine();
		
        System.out.print("Inserisci la tua data di nascita: "
        		+ "\nusa il formato yyyy-MM-dd "
        		+ "\n ---->");
        try { //controllo se il formato è corretto
            this.dataDiNascita = LocalDate.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println(" ! Errore: Formato data di nascita non valido. ! ");
        }
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public List<Movimento> getMovimenti() {
		return movimenti;
	}

	public void setMovimenti(List<Movimento> movimenti) {
		this.movimenti = movimenti;
	}
	
	
	
}
