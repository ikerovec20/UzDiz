package ikerovec20_zadaca_3.App;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

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
	
	public void kupiKartu(String oznaka, String polaznaStanica, String odredisnaStanica, Date datum, String nacin) {
		var vlak = TvrtkaSingleton.getInstance().getVozniRed().dohvatiVlak(oznaka);
		if (vlak == null) {
			System.out.println("Ne postoji vlak s oznakom " + oznaka);
			return;
		}
		
		var polazna = vlak.dohvatiStanicu(polaznaStanica);
		var odredisna = vlak.dohvatiStanicu(odredisnaStanica);
		
		if (polazna == null || odredisna == null || !vlak.jePrijeStanice(polaznaStanica, odredisnaStanica)) {
			System.out.println("Stanice su neispravne.");
			return;
		}
		
		if (kupovinaNaBlagajni == null) {
			System.out.println("Nisu postavljene cijene i popusti.");
			return;
		}
		
		postaviNacinKupovanja(nacin);
		
		var kalendar = Calendar.getInstance();
		kalendar.setTime(datum);
		boolean vikend = kalendar.get(Calendar.DAY_OF_WEEK) == 0 || kalendar.get(Calendar.DAY_OF_WEEK) == 6 ? true : false;
		int km = izracunajKm(vlak, polaznaStanica, odredisnaStanica);
		double izvornaCijena = kupovina.izracunajOsnovnuCijenu(km, vlak);
		double konacnaCijena = kupovina.izracunajKonacnuCijenu(km, vlak, vikend);
		var popusti = kupovina.dohvatiPopuste(vikend);
		Karta karta = new Karta(oznaka, polaznaStanica, odredisnaStanica, polazna.vratiPocetnoVrijeme(), odredisna.vrijemeDolaska, izvornaCijena, 
				popusti.get(1), popusti.get(0), popusti.get(2), konacnaCijena, nacin, datum, LocalTime.now());
	}
	
	private int izracunajKm(Vlak vlak, String polaznaStanica, String odredisnaStanica) {
		var iterator = vlak.dohvatiIterator();
		int km = 0;
		boolean racunaj = false;
		EtapnaStanica stanica = (EtapnaStanica) iterator.dohvatiSljedecuKomponentu();
		if (stanica.stanica.stanica.matches(polaznaStanica)) {
			racunaj = true;
		}
		while (iterator.postojiSljedecaKomponenta()) {
			var nova = (EtapnaStanica) iterator.dohvatiSljedecuKomponentu();
			if (racunaj) {
				km += stanica.stanica.dohvatiVezu(nova.stanica).pruga.duzina;
			}
			if (stanica.stanica.stanica.matches(polaznaStanica)) {
				racunaj = true;
			}
			if (nova.stanica.stanica.matches(odredisnaStanica)) {
				break;
			}
			stanica = nova;
		}
		return km;
	}
}
