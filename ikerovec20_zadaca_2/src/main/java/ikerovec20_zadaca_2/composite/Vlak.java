package ikerovec20_zadaca_2.composite;

import java.time.LocalTime;

import ikerovec20_zadaca_2.konfiguracija.Konfiguracija;
import ikerovec20_zadaca_2.podaci.Raspored;
import ikerovec20_zadaca_2.podaci.Stanica;

public class Vlak extends VozniRedComposite {
	public String oznaka;
	public String vrstaVlaka;
	public Raspored raspored;
	
	public Vlak(String oznaka, String vrstaVlaka) {
		this.oznaka = oznaka;
		this.vrstaVlaka = vrstaVlaka;
		this.raspored = new Raspored("");
	}
	
	@Override
	public void dodajKomponentu(IKomponentaVoznogReda komponenta) {
		if (!(komponenta instanceof Etapa)) {
			return;
		}
		int indeks = 0;
		boolean pomak = false;
		Etapa komponentaEtapa = (Etapa) komponenta;
		for (int i = 0; i < komponente.size(); i++) {
			Etapa etapa = (Etapa) komponente.get(i);
			if (etapa.vratiPocetnoVrijeme().isAfter(komponentaEtapa.vratiPocetnoVrijeme())) {
				indeks = i;
				pomak = true;
			}
		}
		if (pomak) {
			komponente.add(indeks, komponentaEtapa);	
		}
		else {
			komponente.addLast(komponentaEtapa);
		}
		raspored.prilagodiRaspored(komponentaEtapa.raspored.vratiDane());
	}

	@Override
	public int vratiKm() {
		int ukupno = 0;
		for (var etapa : komponente) {
			ukupno += etapa.vratiKm();
		}
		return ukupno;
	}

	@Override
	public LocalTime vratiPocetnoVrijeme() {
		var etapa = (Etapa) komponente.getFirst();
		return etapa.vrijemePolaska;
	}
	
	@Override
	public LocalTime vratiZavrsnoVrijeme() {
		LocalTime vrijeme = vratiPocetnoVrijeme();
		for (var komp : komponente) {
			
			Etapa etapa = (Etapa) komp;
			vrijeme = vrijeme.plusHours(etapa.vrijemeTrajanja.getHour());
			vrijeme = vrijeme.plusMinutes(etapa.vrijemeTrajanja.getMinute());
		}
		return vrijeme;
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
		if (komponente.size() == 0) {
			Konfiguracija.getInstance().ispisiGresku("Briše se vlak " + oznaka + " koji nema etape.");
			return false;
		}
		for (int i = 0; i < komponente.size(); i++) {
			var komp = komponente.get(i);
			if (!komp.validiraj()) {
				ukloniKomponentu(komp);
			}
		}
		if (komponente.size() == 0) {
			Konfiguracija.getInstance().ispisiGresku("Briše se vlak " + oznaka + " koji nema etape.");
			return false;
		}
		if (komponente.size() >= 2) {
			var komp = (Etapa) komponente.getFirst();
			for (int i = 1; i < komponente.size(); i++) {
				var sljedecaKomp = (Etapa) dohvatiKomponentu(i);
				if (komp.vrijemeZavrsetka.isAfter(sljedecaKomp.vrijemePolaska)) {
					Konfiguracija.getInstance().ispisiGresku("Briše se vlak " + oznaka + " koji ima neispravna vremena završetka etape i početka sljedeće.");
					return false;
				}
				if (!komp.vratiZadnjuStanicu().equals(sljedecaKomp.vratiPrvuStanicu())) {
					Konfiguracija.getInstance().ispisiGresku("Briše se vlak " + oznaka + " koji nema jednaku početnu i završnu stanicu između dvije etape.");
					return false;
				}
				komp = sljedecaKomp;
			}	
		}
		return true;
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
		for (var komp : komponente) {
			var stn = komp.dohvatiStanicu(stanica);
			if (stn != null) {
				return stn;
			}
		}
		return null;
	}
}
