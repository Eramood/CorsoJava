package it.Esercizio.Movimenti;

import java.time.LocalDate;

public class Movimento {
	private LocalDate data;
    private String tipoOperazione;
    private double quantita;
    private double saldoParziale;
    private double saldo;
    

    public Movimento(LocalDate data, String tipoOperazione, double quantita, double saldoParziale,double saldo) {
        this.data = data;
        this.tipoOperazione = tipoOperazione;
        this.quantita = quantita;//importo
        this.saldoParziale = saldoParziale;//saldo pre operazione
        this.saldo = saldo;//saldo compresa l'opreazione
    }

    @Override
    public String toString() {
        return "Data: " + data + ", Tipo Operazione: " + tipoOperazione + ", Quantita: " + quantita + ", Saldo Parziale: " + saldo;
    }

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getTipoOperazione() {
		return tipoOperazione;
	}

	public void setTipoOperazione(String tipoOperazione) {
		this.tipoOperazione = tipoOperazione;
	}

	public double getQuantita() {
		return quantita;
	}

	public void setQuantita(double quantita) {
		this.quantita = quantita;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldoParziale) {
		this.saldo = saldoParziale;
	}

	public double getSaldoParziale() {
		return saldoParziale;
	}

	public void setSaldoParziale(double saldoParziale) {
		this.saldoParziale = saldoParziale;
	}

	
}
