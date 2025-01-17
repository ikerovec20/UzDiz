package ikerovec20_zadaca_3.strategy;

import java.util.ArrayList;

import ikerovec20_zadaca_3.composite.Vlak;

public class KupovinaKarteUVlaku implements IKupovinaKarteStrategy {

	private double cijenaNormalniVlak;
	private double cijenaUbrzaniVlak;
	private double cijenaBrziVlak;
	private double popustVikend;
	private double povecanje;
	
	public KupovinaKarteUVlaku(double cijenaNormalniVlak, double cijenaUbrzaniVlak, double cijenaBrziVlak,
			double popustVikend, double povecanje) {
		super();
		this.cijenaNormalniVlak = cijenaNormalniVlak;
		this.cijenaUbrzaniVlak = cijenaUbrzaniVlak;
		this.cijenaBrziVlak = cijenaBrziVlak;
		this.popustVikend = popustVikend;
		this.povecanje = povecanje;
	}

	@Override
	public double izracunajOsnovnuCijenu(int km, Vlak vlak) {
		double cijenaPoKm = 0;
		switch (vlak.vrstaVlaka) {
		case "N":
			cijenaPoKm = cijenaNormalniVlak;
			break;
		case "U":
			cijenaPoKm = cijenaUbrzaniVlak;
			break;
		case "B":
			cijenaPoKm = cijenaBrziVlak;
			break;
		}
		
		double osnovnaCijena = km * cijenaPoKm;
		return osnovnaCijena;
	}

	@Override
	public double izracunajKonacnuCijenu(int km, Vlak vlak, boolean vikend) {
		double osnovna = izracunajOsnovnuCijenu(km, vlak);
		
		double konacna = osnovna * (1 + (povecanje / 100));
		if (vikend) {
			konacna = konacna * (1 - (popustVikend / 100));
		}
		return konacna;
	}

	@Override
	public ArrayList<Double> dohvatiPopuste(boolean vikend) {
		ArrayList<Double> popusti = new ArrayList<Double>();
		if (vikend) {
			popusti.add(popustVikend);
		}
		else {
			popusti.add(0.0);
		}
		popusti.add(0.0);
		popusti.add(povecanje);
		return popusti;
	}

}
