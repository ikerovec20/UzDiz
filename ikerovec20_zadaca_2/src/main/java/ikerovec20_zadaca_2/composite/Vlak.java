package ikerovec20_zadaca_2.composite;

import java.time.LocalTime;

import ikerovec20_zadaca_2.podaci.Stanica;

public class Vlak extends VozniRedComposite {
	public String oznaka;
	public String vrstaVlaka;
	public String daniVoznje;
	
	public Vlak(String oznaka, String vrstaVlaka, String daniVoznje) {
		this.oznaka = oznaka;
		this.vrstaVlaka = vrstaVlaka;
		this.daniVoznje = daniVoznje;
	}
	
	@Override
	public void dodajKomponentu(IKomponentaVoznogReda komponenta) {
		if (!(komponenta instanceof Etapa)) {
			return;
		}
		int i = 0;
		int indeks = 0;
		Etapa komponentaEtapa = (Etapa) komponenta;
		if (komponentaEtapa.pocetnaStanica.stanica.matches(komponentaEtapa.zavrsnaStanica.stanica)) {
			return;
		}
		for (var komp : komponente) {
			Etapa etapa = (Etapa) komp;
			if (komponentaEtapa.vrijemePolaska.isBefore(etapa.vrijemePolaska)) {
				indeks = i;
			}
			i++;
		}
		komponente.add(indeks, komponentaEtapa);
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
			return false;
		}
		for (int i = 0; i < komponente.size(); i++) {
			var komp = komponente.get(i);
			if (!komp.validiraj()) {
				ukloniKomponentu(komp);
			}
		}
		if (komponente.size() >= 2) {
			var komp = komponente.getFirst();
			for (int i = 1; i < komponente.size(); i++) {
				var sljedecaKomp = dohvatiKomponentu(i);
				if (komp.vratiZavrsnoVrijeme().isAfter(sljedecaKomp.vratiPocetnoVrijeme())) {
					return false;
				}
				if (!komp.vratiZadnjuStanicu().equals(sljedecaKomp.vratiPrvuStanicu())) {
					return false;
				}
				komp = sljedecaKomp;
			}	
		}
		return true;
	}
}
