package ikerovec20_zadaca_3.strategy;

import java.util.ArrayList;

import ikerovec20_zadaca_3.composite.Vlak;

public class KupovinaKarteBlagajna implements IKupovinaKarteStrategy {

	private double cijenaNormalniVlak;
	private double cijenaUbrzaniVlak;
	private double cijenaBrziVlak;
	private double popustVikend;
	
	public KupovinaKarteBlagajna(double cijenaNormalniVlak, double cijenaUbrzaniVlak, double cijenaBrziVlak,
			double popustVikend) {
		super();
		this.cijenaNormalniVlak = cijenaNormalniVlak;
		this.cijenaUbrzaniVlak = cijenaUbrzaniVlak;
		this.cijenaBrziVlak = cijenaBrziVlak;
		this.popustVikend = popustVikend;
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
		
		return km * cijenaPoKm;
	}

	@Override
	public double izracunajKonacnuCijenu(int km, Vlak vlak, boolean vikend) {
		double osnovna = izracunajOsnovnuCijenu(km, vlak);
		return osnovna * (1 - (popustVikend / 100));
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
		popusti.add(0.0);
		return popusti;
	}
	
}
