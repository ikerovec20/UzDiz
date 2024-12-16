package ikerovec20_zadaca_2.App;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import ikerovec20_zadaca_2.composite.VozniRed;
import ikerovec20_zadaca_2.iteratori.IPrugaIterator;
import ikerovec20_zadaca_2.iteratori.PrugaIterator;
import ikerovec20_zadaca_2.podaci.Kompozicija;
import ikerovec20_zadaca_2.podaci.Pruga;
import ikerovec20_zadaca_2.podaci.Stanica;
import ikerovec20_zadaca_2.podaci.Vozilo;

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
		System.out.printf("|%-10s|%30s|%-30s|%12s|%n"
				, "Oznaka", "Pocetna stanica", "Zavrsna stanica", "Ukupno km");
		for (var zapis : svePruge.entrySet()) {
			Pruga pruga = zapis.getValue();
			System.out.printf("|%-10s|%30s|%-30s|%12d|%n", pruga.oznakaPruge, pruga.dohvatiPrvuStanicu().stanica, pruga.dohvatiZadnjuStanicu().stanica, pruga.ukupnoKm);
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
		System.out.printf("|%-30s|%6s|%-30s|%n", "Stanica", "Vrsta", "Udaljenost od prve stanice");
		for (int i = 0; i < stanice.size() - 1; i++) {
			stanica = stanice.get(i);
			System.out.printf("|%-30s|%6s|%-30s|%n"
			, stanica.stanica, stanica.vrstaStanice, udaljenost);
			
			udaljenost += stanica.dohvatiVezu(stanice.get(i+1)).pruga.duzina;
		}
		stanica = stanice.get(stanice.size() - 1);
		System.out.printf("|%-30s|%6s|%-30s|%n"
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
		System.out.printf("|%-30s|%6s|%-30s|%n"
				, "Stanica", "Vrsta", "Udaljenost od prve stanice");
		IPrugaIterator iterator;
		if (normalniRedoslijed) {
			iterator = new PrugaIterator(oznaka, pruga.dohvatiPrvuStanicu(), true);
		}
		else {
			iterator = new PrugaIterator(oznaka, pruga.dohvatiZadnjuStanicu(), false);
		}
			while (iterator.postojiSljedecaStanica()) {
				System.out.printf("|%-30s|%6s|%-30s|%n"
						, iterator.dohvatiTrenutnuStanicu().stanica, iterator.dohvatiTrenutnuStanicu().vrstaStanice, udaljenostOdPrve);
				udaljenostOdPrve += iterator.dohvatiUdaljenostDoStanice();
				iterator.dohvatiSljedecuStanicu();
			}
			System.out.printf("|%-30s|%6s|%-30s|%n"
					, iterator.dohvatiTrenutnuStanicu().stanica, iterator.dohvatiTrenutnuStanicu().vrstaStanice, udaljenostOdPrve);
	}
	
	public void ispisiTablicuVlakova() {
		//VRIJEME DOLASKA IZRACUNATI
		//METODA VALIDIRAJ ZA COMPOSITE
	}
	
	public VozniRed vozniRed;
	public Map<String, Kompozicija> sveKompozicije = new LinkedHashMap<String, Kompozicija>();
	public Map<String, Pruga> svePruge = new LinkedHashMap<String, Pruga>();
	public Map<String, Vozilo> svaVozila = new LinkedHashMap<String, Vozilo>();
	public Map<String, Stanica> sveStanice = new LinkedHashMap<String, Stanica>();
	public Map<Integer, String> oznakeDana = new LinkedHashMap<Integer, String>();
}
