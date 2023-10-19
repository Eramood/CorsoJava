package ContoCorrentista.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Correntista {
	
	private String nome;
	private Date dataDiNascita;

	public void RegCorrentista() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("inserisci il tuo nome: ");
		this.nome=scanner.nextLine();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.print("Inserisci la tua data di nascita: \n usa il formato yyyy-MM-dd");
        try { //controllo se il formato è corretto
            this.dataDiNascita = dateFormat.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Errore: Formato data di nascita non valido.");
        }
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	
	
	
}
