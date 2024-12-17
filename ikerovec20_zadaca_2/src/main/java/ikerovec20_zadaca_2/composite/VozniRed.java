package ikerovec20_zadaca_2.composite;

import java.time.LocalTime;

import ikerovec20_zadaca_2.podaci.Stanica;

public class VozniRed extends VozniRedComposite {
	public Vlak dohvatiVlak(String oznaka) {
		for (var komp : komponente) {
			Vlak vlak = (Vlak) komp;
			if (vlak.oznaka.matches(oznaka)) {
				return vlak;
			}
		}
		return null;
	}

	
	@Override
	public int vratiKm() {
		int ukupno = 0;
		for (var vlak : komponente) {
			ukupno += vlak.vratiKm();
		}
		return ukupno;
	}


	@Override
	public Stanica vratiPrvuStanicu() {
		return komponente.getFirst().vratiPrvuStanicu();
	}


	@Override
	public Stanica vratiZadnjuStanicu() {
		return komponente.getLast().vratiZadnjuStanicu();
	}


	@Override
	public boolean validiraj() {
		for (int i = 0; i < komponente.size(); i++) {
			var komp = komponente.get(i);
			if (!komp.validiraj()) {
				ukloniKomponentu(komp);
			}
		}
		return true;
	}


	@Override
	public LocalTime vratiPocetnoVrijeme() {
		return komponente.getFirst().vratiPocetnoVrijeme();
	}


	@Override
	public LocalTime vratiZavrsnoVrijeme() {
		return komponente.getLast().vratiZavrsnoVrijeme();
	}
}
