package ikerovec20_zadaca_3.composite;

import java.time.LocalTime;

import ikerovec20_zadaca_3.podaci.Stanica;

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
		komponente.sort(((o1, o2) -> o1.vratiPocetnoVrijeme().compareTo(o2.vratiPocetnoVrijeme())));
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


	@Override
	public boolean postojiStanica(String stanica) {
		for (var komp : komponente) {
			if (komp.postojiStanica(stanica)) {
				return true;
			}
		}
		return false;
	}


	@Override
	public EtapnaStanica dohvatiStanicu(String stanica) {
		for (var komp: komponente) {
			var stn = komp.dohvatiStanicu(stanica);
			if (stn != null) {
				return stn;
			}
		}
		return null;
	}


	@Override
	public boolean jePrijeStanice(String prvaStanica, String drugaStanica) {
		for (var komp : komponente) {
			var vlak = (Vlak) komp;
			if (vlak.postojiStanica(prvaStanica)) {
				return true;
			}
			if (vlak.postojiStanica(drugaStanica)) {
				return false;
			}
		}
		return false;
	}
}
