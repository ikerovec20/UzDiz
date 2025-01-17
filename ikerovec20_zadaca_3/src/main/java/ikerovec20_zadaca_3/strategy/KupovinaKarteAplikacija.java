package ikerovec20_zadaca_3.strategy;

import java.util.ArrayList;

import ikerovec20_zadaca_3.composite.Vlak;

public class KupovinaKarteAplikacija implements IKupovinaKarteStrategy {

	private double cijenaNormalniVlak;
	private double cijenaUbrzaniVlak;
	private double cijenaBrziVlak;
	private double popustVikend;
	private double popust;
	
	public KupovinaKarteAplikacija(double cijenaNormalniVlak, double cijenaUbrzaniVlak, double cijenaBrziVlak,
			double popustVikend, double popust) {
		super();
		this.cijenaNormalniVlak = cijenaNormalniVlak;
		this.cijenaUbrzaniVlak = cijenaUbrzaniVlak;
		this.cijenaBrziVlak = cijenaBrziVlak;
		this.popustVikend = popustVikend;
		this.popust = popust;
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
		double konacna = osnovna * (1 - (popust / 100));
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
		popusti.add(popust);
		popusti.add(0.0);
		return popusti;
	}

}
