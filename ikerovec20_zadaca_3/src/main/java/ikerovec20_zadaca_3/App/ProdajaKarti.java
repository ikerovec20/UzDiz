package ikerovec20_zadaca_3.App;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import ikerovec20_zadaca_3.composite.Etapa;
import ikerovec20_zadaca_3.composite.EtapnaStanica;
import ikerovec20_zadaca_3.composite.Vlak;
import ikerovec20_zadaca_3.podaci.Karta;
import ikerovec20_zadaca_3.strategy.IKupovinaKarteStrategy;
import ikerovec20_zadaca_3.strategy.KupovinaKarteAplikacija;
import ikerovec20_zadaca_3.strategy.KupovinaKarteBlagajna;
import ikerovec20_zadaca_3.strategy.KupovinaKarteUVlaku;

public class ProdajaKarti {
	private IKupovinaKarteStrategy kupovinaNaBlagajni;
	private IKupovinaKarteStrategy kupovinaPrekoAplikacije;
	private IKupovinaKarteStrategy kupovinaUVlaku;
	
	private IKupovinaKarteStrategy kupovina;
	
	public void postaviCijene(double cijenaNormalni, double cijenaUbrzani, double cijenaBrzi, double popustVikend, double popustApp, double uvecanjeVlak) {
		kupovinaNaBlagajni = new KupovinaKarteBlagajna(cijenaNormalni, cijenaUbrzani, cijenaBrzi, popustVikend);
		kupovinaPrekoAplikacije = new KupovinaKarteAplikacija(cijenaNormalni, cijenaUbrzani, cijenaBrzi, popustVikend, popustApp);
		kupovinaUVlaku = new KupovinaKarteUVlaku(cijenaNormalni, cijenaUbrzani, cijenaBrzi, popustVikend, uvecanjeVlak);
	}
	
	public void postaviNacinKupovanja(String nacin) {
		switch (nacin) {
		case "B":
			kupovina = kupovinaNaBlagajni;
			break;
		case "V":
			kupovina = kupovinaUVlaku;
			break;
		case "WM":
			kupovina = kupovinaPrekoAplikacije;
			break;
		default:
			//ERROR
			break;
		}
	}
	
	public Karta kupiKartu(String oznaka, String polaznaStanica, String odredisnaStanica, LocalDate datum, String nacin) {
		var vlak = TvrtkaSingleton.getInstance().getVozniRed().dohvatiVlak(oznaka);
		if (vlak == null) {
			System.out.println("Ne postoji vlak s oznakom " + oznaka);
			return null;
		}
		
		var polazna = vlak.dohvatiStanicu(polaznaStanica);
		var odredisna = vlak.dohvatiStanicu(odredisnaStanica);
		
		if (polazna == null || odredisna == null || !vlak.jePrijeStanice(polaznaStanica, odredisnaStanica)) {
			System.out.println("Stanice su neispravne.");
			return null;
		}
		
		if (kupovinaNaBlagajni == null) {
			System.out.println("Nisu postavljene cijene i popusti.");
			return null;
		}
		
		postaviNacinKupovanja(nacin);
		
		var kalendar = Calendar.getInstance();
		boolean vikend = datum.getDayOfWeek() == DayOfWeek.SATURDAY || datum.getDayOfWeek() == DayOfWeek.SUNDAY;
		int km = izracunajKm(vlak, polaznaStanica, odredisnaStanica);
		double izvornaCijena = kupovina.izracunajOsnovnuCijenu(km, vlak);
		double konacnaCijena = kupovina.izracunajKonacnuCijenu(km, vlak, vikend);
		var popusti = kupovina.dohvatiPopuste(vikend);
		Karta karta = new Karta(oznaka, polaznaStanica, odredisnaStanica, polazna.vratiPocetnoVrijeme(), odredisna.vrijemeDolaska, izvornaCijena, 
				popusti.get(1), popusti.get(0), popusti.get(2), konacnaCijena, nacin, datum, LocalTime.now());
		return karta;
	}
	
	private int izracunajKm(Vlak vlak, String polaznaStanica, String odredisnaStanica) {
		var etapaIterator = vlak.dohvatiIterator();
		int km = 0;
		boolean racunaj = false;
		boolean prekid = false;
		while (etapaIterator.postojiSljedecaKomponenta()) {
			var etapa = (Etapa) etapaIterator.dohvatiSljedecuKomponentu();
			var iterator = etapa.dohvatiIterator();
			EtapnaStanica stanica = (EtapnaStanica) iterator.dohvatiSljedecuKomponentu();
			if (stanica.stanica.stanica.matches(polaznaStanica)) {
				racunaj = true;
			}
			while (iterator.postojiSljedecaKomponenta()) {
				var nova = (EtapnaStanica) iterator.dohvatiSljedecuKomponentu();
				if (stanica.stanica.stanica.matches(polaznaStanica)) {
					racunaj = true;
				}
				if (racunaj) {					
					km += stanica.stanica.dohvatiVezu(nova.stanica).pruga.duzina;
				}
				if (nova.stanica.stanica.matches(odredisnaStanica)) {
					prekid = true;
					break;
				}
				stanica = nova;
			}
			if (prekid) {
				break;
			}
		}
		return km;
	}
}
