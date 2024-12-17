package ikerovec20_zadaca_2.composite;

import java.time.LocalTime;

import ikerovec20_zadaca_2.podaci.Stanica;

public class Vlak extends VozniRedComposite {
	public String oznaka;
	public String vrstaVlaka;
	
	public Vlak(String oznaka, String vrstaVlaka) {
		this.oznaka = oznaka;
		this.vrstaVlaka = vrstaVlaka;
	}
	
	@Override
	public void dodajKomponentu(IKomponentaVoznogReda komponenta) {
		if (!(komponenta instanceof Etapa)) {
			return;
		}
		int indeks = 0;
		boolean pomak = false;
		Etapa komponentaEtapa = (Etapa) komponenta;
//		if (komponentaEtapa.pocetnaStanica.stanica.matches(komponentaEtapa.zavrsnaStanica.stanica)) {
//			return;
//		}
		for (int i = 0; i < komponente.size(); i++) {
			Etapa etapa = (Etapa) komponente.get(i);
			boolean pravo = etapa.vratiPocetnoVrijeme().isAfter(komponentaEtapa.vratiPocetnoVrijeme());
			if (etapa.vratiPocetnoVrijeme().isAfter(komponentaEtapa.vratiPocetnoVrijeme())) {
				indeks = i;
				pomak = true;
				System.out.println("NOVA ETAPA VRIJEME: " + komponentaEtapa.vratiPocetnoVrijeme() + " | STARA ETAPA VRIJEME: " + etapa.vratiPocetnoVrijeme() + " | VRIJEDNOST: " + pravo);
			}
		}
//		if (indeks+1 >= komponente.size()) {
//			komponente.addLast(komponentaEtapa);
//		}
//		else {
//			komponente.add(indeks+1, komponentaEtapa);		
//		}
		if (pomak) {
			komponente.add(indeks, komponentaEtapa);	
		}
		else {
			komponente.addLast(komponentaEtapa);
		}
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
		if (komponente.size() == 0) {
			return false;
		}
		if (komponente.size() >= 2) {
			var komp = (Etapa) komponente.getFirst();
			for (int i = 1; i < komponente.size(); i++) {
				var sljedecaKomp = (Etapa) dohvatiKomponentu(i);
				if (komp.vrijemeZavrsetka.isAfter(sljedecaKomp.vrijemePolaska)) {
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
