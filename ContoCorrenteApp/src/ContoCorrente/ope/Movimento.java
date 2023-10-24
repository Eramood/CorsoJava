package ContoCorrente.ope;

import java.time.LocalDate;
import java.util.Date;

public class Movimento {
	private String tipoOperazione;
    private double importo;
    private double saldoPrima;
    private double saldoDopo;
    private LocalDate dataMovimento;

    public Movimento(String tipoOperazione, double importo, double saldoPrima, double saldoDopo) {
        this.tipoOperazione = tipoOperazione;
        this.importo = importo;
        this.saldoPrima = saldoPrima;
        this.saldoDopo = saldoDopo;
        this.dataMovimento = LocalDate.now();
    }
    
    public Movimento(String tipoOperazione, double importo, double saldoPrima) {
        this.tipoOperazione = tipoOperazione;
        this.importo = importo;
        this.saldoPrima = saldoPrima;
        this.dataMovimento = LocalDate.now();
    }

	public String getTipoOperazione() {
		return tipoOperazione;
	}

	public void setTipoOperazione(String tipoOperazione) {
		this.tipoOperazione = tipoOperazione;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public double getSaldoPrima() {
		return saldoPrima;
	}

	public void setSaldoPrima(double saldoPrima) {
		this.saldoPrima = saldoPrima;
	}

	public double getSaldoDopo() {
		return saldoDopo;
	}

	public void setSaldoDopo(double saldoDopo) {
		this.saldoDopo = saldoDopo;
	}

	public LocalDate getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(LocalDate dataMovimento) {
		this.dataMovimento = dataMovimento;
	}
    
}
