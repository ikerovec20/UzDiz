package ikerovec20_zadaca_2.composite;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import ikerovec20_zadaca_2.podaci.KomponentaPruge;
import ikerovec20_zadaca_2.podaci.Pruga;
import ikerovec20_zadaca_2.podaci.Raspored;
import ikerovec20_zadaca_2.podaci.Stanica;

public class Etapa extends VozniRedComposite {
	public Stanica pocetnaStanica;
	public Stanica zavrsnaStanica;
	public String smjer;
	public String vrstaEtape;
	public Pruga pruga;
	public ArrayList<Stanica> stanice;
	public LocalTime vrijemePolaska;
	public LocalTime vrijemeTrajanja;
	public LocalTime vrijemeZavrsetka;
	public Raspored raspored;
	public int ukupnoKm = 0;
	
	public Etapa(Stanica pocetna, Stanica zavrsna, String smjer, String vrstaEtape, Pruga pruga, LocalTime vrijemePolaska, LocalTime vrijemeTrajanja, String dani) {
		stanice = new ArrayList<Stanica>();
		this.pocetnaStanica = pocetna;
		this.zavrsnaStanica = zavrsna;
		this.smjer = smjer;
		this.vrstaEtape = vrstaEtape;
		this.pruga = pruga;
		this.vrijemePolaska = vrijemePolaska;
		this.vrijemeTrajanja = vrijemeTrajanja;
		LocalTime vrijeme = vrijemePolaska.plusHours(vrijemeTrajanja.getHour());
		this.vrijemeZavrsetka = vrijeme.plusMinutes(vrijemeTrajanja.getMinute());
		this.raspored = new Raspored(dani);
		ucitajStanice();
	}
	
	public void ucitajStanice() {
		boolean smjer = this.smjer.matches("N");
		var iterator = pruga.dohvatiIterator(pocetnaStanica, smjer);
		int ukupno = 0;
		Stanica stanica = iterator.dohvatiTrenutnuStanicu();
		var brzaVeza = stanica.dohvatiBrzuVezu(pruga.oznakaPruge, smjer);
		var ubrzanaVeza = stanica.dohvatiUbrzanuVezu(pruga.oznakaPruge, smjer);
		int vrijemeNormalno = 0;
		int vrijemeBrzo = brzaVeza == null ? -1 : 0;
		int vrijemeUbrzano = ubrzanaVeza == null ? -1 : 0;
		EtapnaStanica etapna = new EtapnaStanica(stanica, ukupno, vrijemePolaska, vrijemeNormalno, vrijemeBrzo, vrijemeUbrzano);
		LocalTime vrijeme = vrijemePolaska;
		komponente.add(etapna);
//		stanice.add(stanica);
		boolean proslo = false;
		while (iterator.postojiSljedecaStanica() && !stanica.equals(zavrsnaStanica)) {
			stanica = iterator.dohvatiTrenutnuStanicu();
			var nova = iterator.dohvatiSljedecuStanicu();
			if (!proslo) {
				var vz = stanica.dohvatiVezu(nova);
				ukupno += vz.pruga.duzina;
				switch (vrstaEtape) {
				case "N":
					vrijeme = vrijeme.plusMinutes(vz.vrijemeNormalniVlak);
					break;
				case "U":
					if (ubrzanaVeza != null) {
						vrijeme = vrijeme.plusMinutes(ubrzanaVeza.vrijemeUbrzaniVlak);	
					}
					break;
				case "B":
					if (brzaVeza != null) {
						vrijeme = vrijeme.plusMinutes(brzaVeza.vrijemeBrziVlak);
					}
					break;
				}
			}
			if (nova.equals(zavrsnaStanica)) {
				proslo = true;
			}
			var veza = stanica.dohvatiVezu(nova);
			brzaVeza = stanica.dohvatiBrzuVezu(pruga.oznakaPruge, smjer);
			ubrzanaVeza = stanica.dohvatiUbrzanuVezu(pruga.oznakaPruge, smjer);
			vrijemeBrzo = brzaVeza == null ? -1 : brzaVeza.vrijemeBrziVlak;
			vrijemeUbrzano = ubrzanaVeza == null ? -1 : ubrzanaVeza.vrijemeUbrzaniVlak;
			etapna = new EtapnaStanica(nova, ukupno, vrijeme, veza.vrijemeNormalniVlak, vrijemeBrzo, vrijemeUbrzano);
			dodajKomponentu(etapna);
		}
//		etapna = new EtapnaStanica(stanica, ukupno, vrijeme, vrijemeNormalno, vrijemeBrzo, vrijemeUbrzano);
//		dodajKomponentu(etapna);
		this.ukupnoKm = ukupno;
	}

	
	@Override
	public int vratiKm() {
		return komponente.getLast().vratiKm();
//		int km = 0;
//		for (var komp : komponente) {
//			km += komp.vratiKm();
//		}
//		return km;
	}

	@Override
	public Stanica vratiPrvuStanicu() {
		return this.pocetnaStanica;
	}

	@Override
	public Stanica vratiZadnjuStanicu() {
		return this.zavrsnaStanica;
	}

	@Override
	public boolean validiraj() {
		if (pocetnaStanica.equals(zavrsnaStanica)) {
			return false;
		}
		if (vrijemeTrajanja.getHour() == 0 && vrijemeTrajanja.getMinute() == 0) {
			return false;
		}
		if (vrijemeZavrsetka.isBefore(vratiZavrsnoVrijeme())) {
			System.out.println("BRIŠE SE ETAPA: " + pruga.oznakaPruge);
			return false;
		}
		return true;
	}

	@Override
	public LocalTime vratiPocetnoVrijeme() {
		return vrijemePolaska;
	}

	@Override
	public LocalTime vratiZavrsnoVrijeme() {
		return komponente.getLast().vratiZavrsnoVrijeme();
	}
}
