package ikerovec20_zadaca_3.App;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import ikerovec20_zadaca_3.chain_of_responsibility.IVI2S.IspisIVI2S;
import ikerovec20_zadaca_3.chain_of_responsibility.IVI2S.IspisK;
import ikerovec20_zadaca_3.chain_of_responsibility.IVI2S.IspisP;
import ikerovec20_zadaca_3.chain_of_responsibility.IVI2S.IspisS;
import ikerovec20_zadaca_3.chain_of_responsibility.IVI2S.IspisV;
import ikerovec20_zadaca_3.composite.Etapa;
import ikerovec20_zadaca_3.composite.EtapnaStanica;
import ikerovec20_zadaca_3.composite.IKomponentaVoznogReda;
import ikerovec20_zadaca_3.composite.Vlak;
import ikerovec20_zadaca_3.composite.VozniRed;
import ikerovec20_zadaca_3.iteratori.IPrugaIterator;
import ikerovec20_zadaca_3.iteratori.PrugaIterator;
import ikerovec20_zadaca_3.observer.DojavljacKorisnika;
import ikerovec20_zadaca_3.observer.Korisnik;
import ikerovec20_zadaca_3.podaci.Kompozicija;
import ikerovec20_zadaca_3.podaci.Pruga;
import ikerovec20_zadaca_3.podaci.Stanica;
import ikerovec20_zadaca_3.podaci.Vozilo;

public class TvrtkaSingleton {
	private static TvrtkaSingleton instance;
	
	static {
		instance = new TvrtkaSingleton();
	}
	
	private TvrtkaSingleton() {}
	
	public static TvrtkaSingleton getInstance() {
		return instance;
	}
	
	public void ispisiKompoziciju(String kompozicija) {
		Kompozicija komp = sveKompozicije.get(kompozicija);
		if (komp == null) {
			System.out.println("Nije pronadena kompozicija s tom oznakom");
			return;
		}
		System.out.printf("|%-8s|%12s|%-60s|%6s|%8s|%16s|%16s|%n"
				, "Oznaka", "Uloga", "Opis", "Godina", "Namjena"
				, "Vrsta pogona", "Maks brzina");
		for (var zapis : komp.prijevoznaSredstva.entrySet()) {
			var vlak = zapis.getKey();
			System.out.printf("|%-8s|%12s|%-60s|%6s|%8s|%16s|%16s|%n", vlak.oznaka, zapis.getValue(),
					vlak.opis, vlak.godina, vlak.namjena, 
					vlak.vrstaPogona, vlak.maksBrzina);
		}
	}
	
	public void ispisiPruge() {
		System.out.printf("|%-10s|%23s|%-23s|%12s|%n"
				, "Oznaka", "Pocetna stanica", "Zavrsna stanica", "Ukupno km");
		for (var zapis : svePruge.entrySet()) {
			Pruga pruga = zapis.getValue();
			System.out.printf("|%-10s|%23s|%-23s|%12d|%n", pruga.oznakaPruge, pruga.dohvatiPrvuStanicu().stanica, pruga.dohvatiZadnjuStanicu().stanica, pruga.ukupnoKm);
		}
	}
	
	public void ispisiStanice(String pocetna, String zavrsna) {
		Stanica pocetnaStanica = sveStanice.get(pocetna);
		Stanica zavrsnaStanica = sveStanice.get(zavrsna);
		
		if (pocetnaStanica == null) {
			System.out.println("Pocetna stanica ne postoji.");
			return;
		}
		else if (zavrsnaStanica == null) {
			System.out.println("Zavrsna stanica ne postoji.");
			return;
		}
		
		ArrayList<Stanica> stanice = new ArrayList<Stanica>();
		stanice = pronadiPutDoStanice(stanice, pocetnaStanica, zavrsnaStanica);
		
		if (stanice == null) {
			System.out.println("Ne postoji put između te dvije stanice.");
			return;
		}
		
		int udaljenost = 0;
		Stanica stanica = null;
		System.out.printf("|%-23s|%6s|%-23s|%n", "Stanica", "Vrsta", "Udaljenost od prve stanice");
		for (int i = 0; i < stanice.size() - 1; i++) {
			stanica = stanice.get(i);
			System.out.printf("|%-23s|%6s|%-23s|%n"
			, stanica.stanica, stanica.vrstaStanice, udaljenost);
			
			udaljenost += stanica.dohvatiVezu(stanice.get(i+1)).pruga.duzina;
		}
		stanica = stanice.get(stanice.size() - 1);
		System.out.printf("|%-23s|%6s|%-23s|%n"
		, stanica.stanica, stanica.vrstaStanice, udaljenost);
	}
	
	//referenca: https://medium.com/@shaswata.ssaha/finding-paths-in-graphs-using-depth-first-search-dfs-in-java-4ac76eab29d5
	private ArrayList<Stanica> pronadiPutDoStanice(ArrayList<Stanica> posjeceneStanice, Stanica pocetna, Stanica zavrsna) {
		var stanice = new ArrayList<Stanica>(posjeceneStanice);
		stanice.add(pocetna);
		
		if (pocetna.stanica.matches(zavrsna.stanica)) {
			return stanice;
		}
		
		for (var veza : pocetna.veze) {
			if (!veza.stanica.stanica.matches(pocetna.stanica) && !stanice.contains(veza.stanica)) {
				var lista = pronadiPutDoStanice(stanice, veza.stanica, zavrsna);
				if (lista != null && lista.contains(zavrsna)) {
					return lista;
				}
			}
		}
		return null;
	}
	
	public void ispisiPrugu(String oznaka, boolean normalniRedoslijed) {
		var pruga = svePruge.get(oznaka);
		if (pruga == null) {
			System.out.println("Ne postoji pruga s tom oznakom.");
			return;
		}
		int udaljenostOdPrve = 0;
		System.out.printf("|%-23s|%6s|%-23s|%n"
				, "Stanica", "Vrsta", "Udaljenost od prve stanice");
		IPrugaIterator iterator;
		if (normalniRedoslijed) {
			iterator = new PrugaIterator(oznaka, pruga.dohvatiPrvuStanicu(), true);
		}
		else {
			iterator = new PrugaIterator(oznaka, pruga.dohvatiZadnjuStanicu(), false);
		}
			while (iterator.postojiSljedecaStanica()) {
				var stanica = iterator.dohvatiTrenutnuStanicu();
				System.out.printf("|%-23s|%6s|%-23s|%n"
						, stanica.stanica, stanica.vrstaStanice, udaljenostOdPrve);
				iterator.dohvatiSljedecuStanicu();
				udaljenostOdPrve += stanica.dohvatiVezu(iterator.dohvatiTrenutnuStanicu()).pruga.duzina;
			}
			System.out.printf("|%-23s|%6s|%-23s|%n"
					, iterator.dohvatiTrenutnuStanicu().stanica, iterator.dohvatiTrenutnuStanicu().vrstaStanice, udaljenostOdPrve);
	}
	
	public void ispisiTablicuVlakova() {		
		System.out.printf("%-20s %-23s %-23s %-20s %-20s %-16s %n", "Oznaka", "Polazna stanica", "Odredisna stanica", "Vrijeme polaska", "Vrijeme dolaska", "Ukupno km");
		for (var vlak : vozniRed.vratiKomponente()) {
			Vlak vl = (Vlak) vlak;
			System.out.printf("%-20s %-23s %-23s %-20s %-20s %-16s %n", vl.oznaka, vl.vratiPrvuStanicu().stanica, vl.vratiZadnjuStanicu().stanica, vl.vratiPocetnoVrijeme(), vl.vratiZavrsnoVrijeme(), vl.vratiKm());
		}
	}
	
	public void ispisiVlak(String oznaka) {
		Vlak vlak = vozniRed.dohvatiVlak(oznaka);
		if (vlak == null) {
			System.out.println("Ne postoji vlak s oznakom " + oznaka);
			return;
		}
		
		System.out.printf("%-12s %-13s %-23s %-23s %-16s %-16s %-10s %-11s %n", "Oznaka vlaka", "Oznaka pruge", "Polazna stanica", "Odredisna stanica", "Vrijeme polaska", "Vrijeme dolaska", "Ukupno km", "Dani");
		var komponente = vlak.vratiKomponente();
		for (int i = 0; i < komponente.size(); i++) {
			Etapa etapa = (Etapa) komponente.get(i);
			System.out.printf("%-12s %-13s %-23s %-23s %-16s %-16s %-10s %-11s %n", vlak.oznaka, etapa.pruga.oznakaPruge, etapa.pocetnaStanica.stanica, etapa.zavrsnaStanica.stanica, etapa.vratiPocetnoVrijeme(), etapa.vratiZavrsnoVrijeme(), etapa.vratiKm(), etapa.raspored.vratiDane());

		}
	}
	
	public void ispisiVlakoveDani(String dani) {
		System.out.printf("%-12s %-13s %-23s %-23s %-16s %-16s %-11s %n", "Oznaka vlaka", "Oznaka pruge", "Polazna stanica", "Odredisna stanica", "Vrijeme polaska", "Vrijeme dolaska", "Dani");
		
		for (var komponenta : vozniRed.vratiKomponente()) {
			Vlak vlak = (Vlak) komponenta;
			
			for (var komp : vlak.vratiKomponente()) {
				Etapa etapa = (Etapa) komp;
				if (etapa.raspored.provjeriDane(dani)) {
					System.out.printf("%-12s %-13s %-23s %-23s %-16s %-16s %-11s %n", vlak.oznaka, etapa.pruga.oznakaPruge, etapa.pocetnaStanica.stanica, 
							etapa.zavrsnaStanica.stanica, etapa.vrijemePolaska, etapa.vratiZavrsnoVrijeme(), etapa.raspored.vratiDane());	
				}
				
			}
		}

	}
	
	public void ispisiIVRV(String oznaka) {
		var vlak = vozniRed.dohvatiVlak(oznaka);
		if (vlak == null) {
			System.out.println("Ne postoji vlak s tom oznakom.");
			return;
		}
		
		var etapaIterator = vlak.dohvatiIterator();
		System.out.printf("%-12s %-13s %-23s %-16s %-4s%n", "Oznaka vlaka", "Oznaka pruge", "Stanica", "Vrijeme polaska", "km");

		int ukupno = 0;
		EtapnaStanica zadnjaStanica = null;
		while (etapaIterator.postojiSljedecaKomponenta()) {
			var etapa = (Etapa) etapaIterator.dohvatiSljedecuKomponentu();
			var stanicaIterator = etapa.dohvatiIterator();
			while (stanicaIterator.postojiSljedecaKomponenta()) {
				var stanica = (EtapnaStanica) stanicaIterator.dohvatiSljedecuKomponentu();
				switch (etapa.vrstaEtape) {
				case "B":
					if (stanica.vrijemeBrziVlak != -1) {
						if (zadnjaStanica == null || !stanica.stanica.equals(zadnjaStanica.stanica)) {
							System.out.printf("%-12s %-13s %-23s %-16s %-4s%n", oznaka, etapa.pruga.oznakaPruge, stanica.stanica.stanica, stanica.vratiZavrsnoVrijeme(), ukupno + stanica.vratiKm());
						}
					}
					break;
				case "U":
					if (stanica.vrijemeUbrzaniVlak != -1) {
						if (zadnjaStanica == null || !stanica.stanica.equals(zadnjaStanica.stanica)) {
							System.out.printf("%-12s %-13s %-23s %-16s %-4s%n", oznaka, etapa.pruga.oznakaPruge, stanica.stanica.stanica, stanica.vratiZavrsnoVrijeme(), ukupno + stanica.vratiKm());
						}
					}
					break;
				default: 
					if (zadnjaStanica == null || !stanica.stanica.equals(zadnjaStanica.stanica)) {
						System.out.printf("%-12s %-13s %-23s %-16s %-4s%n", oznaka, etapa.pruga.oznakaPruge, stanica.stanica.stanica, stanica.vratiZavrsnoVrijeme(), ukupno + stanica.vratiKm());
					}
					break;
				}
				if (!stanicaIterator.postojiSljedecaKomponenta()) {
					ukupno = stanica.vratiKm();
					zadnjaStanica = stanica;
				}
			}
		}
	}
	
	public void ispisIVI2S(String pocetna, String odredisna, String dani, LocalTime pocetno, LocalTime zavrsno, String stupci) {
		var prvaStanica = sveStanice.get(pocetna);
		var drugaStanica = sveStanice.get(odredisna);
		if (prvaStanica == null || drugaStanica == null) {
			System.out.println("Jedna od stanica ne postoji.");
			return;
		}
		
		if (pocetno.isAfter(zavrsno)) {
			System.out.println("Vremena su neispravna.");
			return;
		}
		
		var vlakovi = vozniRed.vratiKomponente();
		ArrayList<IKomponentaVoznogReda> izabraniVlakovi = new ArrayList<IKomponentaVoznogReda>();
		for (var vlak : vlakovi) {
			Vlak v = (Vlak) vlak;
			if (!v.raspored.provjeriDane(dani)) {
				continue;
			}
			if (v.postojiStanica(pocetna) && v.postojiStanica(odredisna)) {
				var poc = v.dohvatiStanicu(pocetna);
				var zav = v.dohvatiStanicu(odredisna);
				
				if (!v.jePrijeStanice(pocetna, odredisna)) {
					continue;
				}
				
				if (poc.vratiPocetnoVrijeme().isAfter(pocetno) && zav.vratiZavrsnoVrijeme().isBefore(zavrsno)) {
					izabraniVlakovi.add(v);
				}
			}
		}
		if (izabraniVlakovi.size() == 0) {
			System.out.println("Ne postoje vlakovi s određenim argumentima.");
			return;
		}
		ArrayList<IKomponentaVoznogReda> stanice = new ArrayList<IKomponentaVoznogReda>();
		boolean uzimajStanice = false;
		boolean prekid = false;
		int brReda = 0;
		var v = (Vlak) izabraniVlakovi.get(0);
		var iterator = v.dohvatiIterator();
		while (iterator.postojiSljedecaKomponenta()) {
			var komp = (Etapa) iterator.dohvatiSljedecuKomponentu();
			var stanicaIterator = komp.dohvatiIterator();
			var prethodna = (EtapnaStanica) stanicaIterator.dohvatiTrenutnuKomponentu();
			while (stanicaIterator.postojiSljedecaKomponenta()) {
				var stanica = (EtapnaStanica) stanicaIterator.dohvatiSljedecuKomponentu();
				if (stanica.postojiStanica(pocetna)) {
					uzimajStanice = true;
				}
				if (stanica.postojiStanica(odredisna) && uzimajStanice) {
					prekid = true;
				}
				if (uzimajStanice) {
					if (!prethodna.stanica.stanica.matches(stanica.stanica.stanica) || brReda == 0) {
						stanice.add(stanica);	
					}
					if (prekid) {
						break;
					}
				}
				prethodna = stanica;
				brReda++;
			}
			if (prekid) {
				break;
			}
		}
		posloziIspis(stanice, izabraniVlakovi, stupci);
	}
	
	private void posloziIspis(ArrayList<IKomponentaVoznogReda> stanice, ArrayList<IKomponentaVoznogReda> izabraniVlakovi, String ispis) {
		var redoslijed = ispis.toCharArray();
		ArrayList<IspisIVI2S> stupci = new ArrayList<IspisIVI2S>();
		
		for (int i = 0; i < redoslijed.length; i++) {
			switch (redoslijed[i]) {
			case 'K':
				stupci.add(new IspisK(stanice));
				break;
			case 'V':
				stupci.add(new IspisV(stanice, izabraniVlakovi));
				break;
			case 'P':
				stupci.add(new IspisP(stanice));
				break;
			case 'S':
				stupci.add(new IspisS(stanice));
				break;
			}
		}
		var lanac = stupci.get(0);
		for (int i = 1; i < stupci.size(); i++) {
			lanac.postaviSljedeceg(stupci.get(i));
			lanac = stupci.get(i);
		}
		for (int i = -1; i < stanice.size(); i++) {
			for (int j = 0; j < redoslijed.length; j++) {
				stupci.get(0).ispisiRed(redoslijed[j]);	
			}
			System.out.printf("%n", "");
		}
	}
	
	public void dodajKorisnika(String ime, String prezime) {
		Korisnik korisnik = new Korisnik(ime, prezime);
		korisnici.add(korisnik);
		System.out.println("Korisnik " + ime + " " + prezime + " je dodan u registar.");
	}
	
	public void ispisiKorisnike() {
		System.out.printf("%-25s %-25s%n", "Ime", "Prezime");
		for (var korisnik : korisnici) {
			System.out.printf("%-25s %-25s%n", korisnik.ime, korisnik.prezime);
		}
	}
	
	public void pretplatiKorisnika(String ime, String prezime, String oznakaVlaka, String stanica) {
		Korisnik korisnik = null;
		for (var kor : korisnici) {
			if (kor.ime.matches(ime) && kor.prezime.matches(prezime)) {
				korisnik = kor;
				break;
			}
		}
		if (korisnik == null) {
			System.out.println("Ne postoji trazeni korisnik.");
			return;
		}
		
		var vlak = vozniRed.dohvatiVlak(oznakaVlaka);
		if (vlak == null) {
			System.out.println("Ne postoji trazeni vlak.");
			return;
		}
		
		if (stanica != null && sveStanice.get(stanica) == null) {
			System.out.println("Ne postoji trazena stanica.");
			return;
		}
		System.out.println("Korisnik " + ime + " " + prezime + " je pretplaćen.");
		dojavljac.pretplati(korisnik, oznakaVlaka, stanica);
	}
	
	public void simulacijaVlaka(String oznakaVlaka, String dan, int koeficijent) {
		var vlak = vozniRed.dohvatiVlak(oznakaVlaka);
		if (vlak == null) {
			System.out.println("Ne postoji trazeni vlak.");
			return;
		}
		
		if (koeficijent <= 0) {
			System.out.println("Koeficijent ne smije biti 0 ili manji.");
			return;
		}
		
		if (!vlak.raspored.provjeriDane(dan)) {
			System.out.println("Vlak ne vozi na te dane.");
			return;
		}
		
		var simulacija = new SimulacijaVlaka(oznakaVlaka, vlak, dojavljac, koeficijent, dan);
		simulacije.add(simulacija);
		var dretvaFactory = Thread.ofVirtual().factory();
		var dretva = dretvaFactory.newThread(simulacija);
		dretva.start();
	}
	
	public void zaustaviSimulacije() {
		if (simulacije.size() == 0) {
			System.out.println("Nema aktivnih simulacija.");
		}
		for (var sim : simulacije) {
			sim.zaustavi();
		}
	}
	
	private VozniRed vozniRed;
	private DojavljacKorisnika dojavljac = new DojavljacKorisnika();
	private ArrayList<SimulacijaVlaka> simulacije = new ArrayList<SimulacijaVlaka>();
	private Map<String, Kompozicija> sveKompozicije = new LinkedHashMap<String, Kompozicija>();
	private Map<String, Pruga> svePruge = new LinkedHashMap<String, Pruga>();
	private Map<String, Vozilo> svaVozila = new LinkedHashMap<String, Vozilo>();
	private Map<String, Stanica> sveStanice = new LinkedHashMap<String, Stanica>();
	private Map<Integer, String> oznakeDana = new LinkedHashMap<Integer, String>();
	private ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
	public VozniRed getVozniRed() {
		return vozniRed;
	}

	public void setVozniRed(VozniRed vozniRed) {
		this.vozniRed = vozniRed;
	}

	public DojavljacKorisnika getDojavljac() {
		return dojavljac;
	}

	public ArrayList<SimulacijaVlaka> getSimulacije() {
		return simulacije;
	}

	public Map<String, Kompozicija> getSveKompozicije() {
		return sveKompozicije;
	}

	public Map<String, Pruga> getSvePruge() {
		return svePruge;
	}

	public Map<String, Vozilo> getSvaVozila() {
		return svaVozila;
	}

	public Map<String, Stanica> getSveStanice() {
		return sveStanice;
	}

	public Map<Integer, String> getOznakeDana() {
		return oznakeDana;
	}

	public ArrayList<Korisnik> getKorisnici() {
		return korisnici;
	}
}
