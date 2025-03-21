package ikerovec20_zadaca_1.App;

import java.util.LinkedHashMap;
import java.util.Map;
import ikerovec20_zadaca_1.podaci.Kompozicija;
import ikerovec20_zadaca_1.podaci.Pruga;
import ikerovec20_zadaca_1.podaci.PruznaStanica;
import ikerovec20_zadaca_1.podaci.Vozilo;

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
			System.out.printf("|%-10s|%30s|%-30s|%12d|%n", pruga.oznakaPruge, pruga.dohvatiPrvuStanicu(true).stanica, pruga.dohvatiZadnjuStanicu(true).stanica, pruga.vratiUkupnuDuzinu());
		}
	}
	
	public void ispisiStanice(String pocetna, String zavrsna) {
		int indeksPocetne = -1;
		int indeksZavrsne = -1;
		for (var zapis : svePruge.entrySet()) {
			var pruga = zapis.getValue();
			for (int i = 0; i < pruga.stanice.size(); i++) {
				var stanica = pruga.stanice.get(i);
				if (stanica.stanica.matches(pocetna)) {
					indeksPocetne = i;
				}
				if (stanica.stanica.matches(zavrsna)) {
					indeksZavrsne = i;
				}
			}
			if (indeksPocetne == -1 || indeksZavrsne == -1) {
				indeksPocetne = -1;
				indeksZavrsne = -1;
			}
			else {
				System.out.printf("|%-30s|%6s|%-30s|%n", "Stanica", "Vrsta", "Udaljenost od prve stanice");
				int udaljenost = 0;
				if (indeksPocetne <= indeksZavrsne) {
					for (int i = indeksPocetne; i <= indeksZavrsne; i++) {
						PruznaStanica stanica = pruga.stanice.get(i);
						if (i != indeksPocetne) {
							udaljenost += stanica.duzina;	
						}
						if (i > 0 && stanica.stanica.matches(pruga.stanice.get(i-1).stanica)) {
							continue;
						}
						System.out.printf("|%-30s|%6s|%-30s|%n", stanica.stanica, stanica.vrstaStanice, udaljenost);
					}
					return;
				}
				else {
					for (int i = indeksPocetne; i >= indeksZavrsne; i--) {
						PruznaStanica stanica = pruga.stanice.get(i);
						if (i == indeksPocetne) {
							System.out.printf("|%-30s|%6s|%-30s|%n", stanica.stanica, stanica.vrstaStanice, udaljenost);
							continue;
						}
						else {
							udaljenost += pruga.stanice.get(i+1).duzina;
						}
						if (i < pruga.stanice.size() - 1 && stanica.stanica.matches(pruga.stanice.get(i+1).stanica)) {
							continue;
						}
						System.out.printf("|%-30s|%6s|%-30s|%n", stanica.stanica, stanica.vrstaStanice, udaljenost);
					}
					return;
				}
			}
		}
		System.out.println("Nisu pronadjene stanice u istoj pruzi");
	}
	
	public void ispisiPrugu(String oznaka, boolean normalniRedoslijed) {
		var pruga = svePruge.get(oznaka);
		if (pruga == null) {
			System.out.println("Pruga s oznakom ne postoji");
			return;
		}
		int udaljenostOdPrve = 0;
		System.out.printf("|%-30s|%6s|%-30s|%n"
				, "Stanica", "Vrsta", "Udaljenost od prve stanice");
		if (normalniRedoslijed) {
			for (int i = 0; i < pruga.stanice.size(); i++) {
				PruznaStanica stanica = pruga.stanice.get(i);
				if (i > 0 && stanica.stanica.matches(pruga.stanice.get(i-1).stanica)) {
					continue;
				}
				udaljenostOdPrve += stanica.duzina;
				System.out.printf("|%-30s|%6s|%-30s|%n"
						, stanica.stanica, stanica.vrstaStanice, udaljenostOdPrve);
			}
		}
		else {
			for (int i = pruga.stanice.size() -1 ; i >= 0; i--) {
				PruznaStanica stanica = pruga.stanice.get(i);
				if (i == pruga.stanice.size() - 1) {
					System.out.printf("|%-30s|%6s|%-30s|%n"
							, stanica.stanica, stanica.vrstaStanice, udaljenostOdPrve);
					continue;
				}
				else {
					udaljenostOdPrve += pruga.stanice.get(i+1).duzina;
				}
				if (i < pruga.stanice.size() - 1 && stanica.stanica.matches(pruga.stanice.get(i+1).stanica)) {
					continue;
				}
				System.out.printf("|%-30s|%6s|%-30s|%n"
						, stanica.stanica, stanica.vrstaStanice, udaljenostOdPrve);
			}
		}
	}
	
	public Map<String, Kompozicija> sveKompozicije = new LinkedHashMap<String, Kompozicija>();
	public Map<String, Pruga> svePruge = new LinkedHashMap<String, Pruga>();
	public Map<String, Vozilo> svaVozila = new LinkedHashMap<String, Vozilo>();
}
