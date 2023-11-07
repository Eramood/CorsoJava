package it.ContrattiTelefonici.Chiamate;

import java.time.LocalDate;

public class Chiamate {
			private LocalDate data;
			private int durata;
			private String tipoChiamata;
			private double costo;
			private double sconto;
			private double totaleScontato;
			
	public Chiamate(int durata,String tipoChiamata,double costo, LocalDate data,double sconto,double totaleScontato) {
		this.data=data;
		this.durata=durata;
		this.tipoChiamata=tipoChiamata;
		this.costo=costo;
		this.sconto=sconto;
		this.totaleScontato=totaleScontato;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public String getTipoChiamata() {
		return tipoChiamata;
	}

	public void setTipoChiamata(String tipoChiamata) {
		this.tipoChiamata = tipoChiamata;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getSconto() {
		return sconto;
	}

	public void setSconto(double sconto) {
		this.sconto = sconto;
	}

	public double getTotaleScontato() {
		return totaleScontato;
	}

	public void setTotaleScontato(double totaleScontato) {
		this.totaleScontato = totaleScontato;
	}
	
}
