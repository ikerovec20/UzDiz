package ikerovec20_zadaca_3.composite;

import java.time.LocalTime;

import ikerovec20_zadaca_3.konfiguracija.Konfiguracija;
import ikerovec20_zadaca_3.podaci.Pruga;
import ikerovec20_zadaca_3.podaci.Raspored;
import ikerovec20_zadaca_3.podaci.Stanica;

public class Etapa extends VozniRedComposite {
	public Stanica pocetnaStanica;
	public Stanica zavrsnaStanica;
	public String smjer;
	public String vrstaEtape;
	public Pruga pruga;
	public LocalTime vrijemePolaska;
	public LocalTime vrijemeTrajanja;
	public LocalTime vrijemeZavrsetka;
	public Raspored raspored;
	public int ukupnoKm = 0;
	
	public Etapa(Stanica pocetna, Stanica zavrsna, String smjer, String vrstaEtape, Pruga pruga, LocalTime vrijemePolaska, LocalTime vrijemeTrajanja, String dani) {
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
	
	private Etapa(Etapa etapa) {
		this.pocetnaStanica = etapa.pocetnaStanica;
		this.zavrsnaStanica = etapa.zavrsnaStanica;
		this.smjer = etapa.smjer;
		this.vrstaEtape = etapa.vrstaEtape;
		this.pruga = etapa.pruga;
		this.vrijemePolaska = etapa.vrijemePolaska;
		this.vrijemeTrajanja = etapa.vrijemeTrajanja;
		LocalTime vrijeme = etapa.vrijemePolaska.plusHours(etapa.vrijemeTrajanja.getHour());
		this.vrijemeZavrsetka = vrijeme.plusMinutes(etapa.vrijemeTrajanja.getMinute());
		this.raspored = new Raspored(etapa.raspored.vratiDane());
		ucitajStanice();
	}
	
	public Etapa kloniraj() {
		return new Etapa(this);
	}
	
	public void ucitajStanice() {
		boolean smjer = this.smjer.matches("N");
		var iterator = pruga.dohvatiIterator(pocetnaStanica, smjer);
		int ukupno = 0;
		Stanica stanica = iterator.dohvatiTrenutnuStanicu();
		int vrijemeNormalno = 0;
		EtapnaStanica etapna = new EtapnaStanica(stanica, ukupno, vrijemePolaska, vrijemeNormalno, -1, -1, this.pruga.oznakaPruge);
		LocalTime vrijeme = vrijemePolaska;
		komponente.add(etapna);
		boolean zadnjaStanica = false;
		boolean dodajStanicu = true;
		while (iterator.postojiSljedecaStanica() && !stanica.equals(zavrsnaStanica)) {
			stanica = iterator.dohvatiTrenutnuStanicu();
			var nova = iterator.dohvatiSljedecuStanicu();
			if (!zadnjaStanica) {
				var vz = stanica.dohvatiVezu(nova);
				ukupno += vz.pruga.duzina;
				if (vrstaEtape.matches("N")) {
					vrijeme = vrijeme.plusMinutes(vz.vrijemeNormalniVlak);	
				}
			}
			if (nova.equals(zavrsnaStanica)) {
				zadnjaStanica = true;
			}
			var veza = stanica.dohvatiVezu(nova);
			if (dodajStanicu) {
				etapna = new EtapnaStanica(nova, ukupno, vrijeme, veza.vrijemeNormalniVlak, -1, -1, this.pruga.oznakaPruge);
				dodajKomponentu(etapna);
				if (zadnjaStanica) {
					dodajStanicu = false;
				}
			}
		}
		if (!vrstaEtape.matches("N")) {
			ucitajVremenaStanica();	
		}
	}

	private void ucitajVremenaStanica() {
		boolean smjer = this.smjer.matches("N");
		boolean prvi = true;
		for (var komp : komponente) {
			var stanica = (EtapnaStanica) komp;
			switch (vrstaEtape) {
			case "U":
				var vezaUbrzana = stanica.stanica.dohvatiUbrzanuVezu(pruga.oznakaPruge, smjer);
				if (vezaUbrzana != null) {
					if (prvi) {
						stanica.vrijemeUbrzaniVlak = 0;
						prvi = false;
					}
					dodajVrijemeStanici(vezaUbrzana.stanica, vezaUbrzana.vrijemeUbrzaniVlak);
				}
				break;
			case "B":
				var vezaBrza = stanica.stanica.dohvatiBrzuVezu(pruga.oznakaPruge, smjer);
				if (vezaBrza != null) {
					if (prvi) {
						stanica.vrijemeBrziVlak = 0;
						prvi = false;
					}
					dodajVrijemeStanici(vezaBrza.stanica, vezaBrza.vrijemeBrziVlak);
				}
				break;
			}
		}
	}
	
	private void dodajVrijemeStanici(Stanica stanica, int vrijeme) {
		for (var komp : komponente) {
			EtapnaStanica etapnaStanica = (EtapnaStanica) komp;
			if (etapnaStanica.stanica.equals(stanica)) {
				switch (vrstaEtape) {
				case "B":
					etapnaStanica.vrijemeBrziVlak = vrijeme;
					etapnaStanica.vrijemeDolaska = etapnaStanica.vrijemeDolaska.plusMinutes(vrijeme);
					break;
				case "U":
					etapnaStanica.vrijemeUbrzaniVlak = vrijeme;
					etapnaStanica.vrijemeDolaska = etapnaStanica.vrijemeDolaska.plusMinutes(vrijeme);
					break;
				}
				break;
			}
		}
	}
	
	@Override
	public int vratiKm() {
		return komponente.getLast().vratiKm();
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
			Konfiguracija.getInstance().ispisiGresku("Briše se etapa koja ima istu početnu i završnu stanicu.");
			return false;
		}
		if (vrijemeTrajanja.getHour() == 0 && vrijemeTrajanja.getMinute() == 0) {
			Konfiguracija.getInstance().ispisiGresku("Briše se etapa koja nema vrijeme trajanja.");
			return false;
		}
		if (vrijemeZavrsetka.isBefore(vratiZavrsnoVrijeme())) {
			Konfiguracija.getInstance().ispisiGresku("Briše se etapa kojoj je vrijeme završetka neispravno.");
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
			var stanica = (EtapnaStanica) komp;
			if (stanica.postojiStanica(prvaStanica)) {
				return true;
			}
			if (stanica.postojiStanica(drugaStanica)) {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean provjeriStatusPruge(Stanica prva, Stanica druga) {
		return pruga.provjeriStatus(prva, druga, smjer.matches("N"));
	}
}
