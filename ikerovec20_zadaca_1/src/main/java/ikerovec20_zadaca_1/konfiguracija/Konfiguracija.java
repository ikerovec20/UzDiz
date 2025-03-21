package ikerovec20_zadaca_1.konfiguracija;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import ikerovec20_zadaca_1.App.TvrtkaSingleton;
import ikerovec20_zadaca_1.builderi.IKompozicijaBuilder;
import ikerovec20_zadaca_1.builderi.IPrugaBuilder;
import ikerovec20_zadaca_1.builderi.KompozicijaBuilder;
import ikerovec20_zadaca_1.builderi.PrugaBuilder;
import ikerovec20_zadaca_1.podaci.Kompozicija;
import ikerovec20_zadaca_1.podaci.KompozicijskoVozilo;
import ikerovec20_zadaca_1.podaci.Pruga;
import ikerovec20_zadaca_1.podaci.PruznaStanica;
import ikerovec20_zadaca_1.podaci.Vozilo;
import ikerovec20_zadaca_1.tvornice.KompozicijskoVoziloCreator;
import ikerovec20_zadaca_1.tvornice.PutnickoVoziloCreator;
import ikerovec20_zadaca_1.tvornice.TeretnoVoziloCreator;
import ikerovec20_zadaca_1.tvornice.VoziloCreator;

public class Konfiguracija {
	private int brGreski = 0;
	private int brGreskiDatoteka = 0;
	
	private static Konfiguracija instance;
	
	static {
		instance = new Konfiguracija();
	}
	
	private Konfiguracija() {}
	
	public static Konfiguracija getInstance() {
		return instance;
	}
	
	public void ucitajKonfiguraciju(String[] args) throws Exception {
		if (args.length != 6) {
			throw new Exception("Neispravan broj argumenata");
		}
		String stanice = "";
		String kompozicije = "";
		String vlakovi = "";
		for (int i = 0; i < args.length - 1; i += 2) {
			switch(args[i]) {
			case "--zs":
				stanice = args[i+1];
				break;
			
			case "--zps":
				vlakovi = args[i+1];
				break;
				
			case "--zk":
				kompozicije = args[i+1];
				break;
				
			default: 
				throw new Exception("Neispravan format parametara.");
			}
		}
		ucitajStanice(stanice);
		ucitajVlakove(vlakovi);
		ucitajKompozicije(kompozicije);
	}
	
	private ArrayList<String[]> ucitajCsvDatoteku(String datoteka, int velicinaZaglavlja, IValidacija validacija) {
		brGreskiDatoteka = 0;
		FileReader citacDatoteke;
		BufferedReader citac;
		ArrayList<String[]> lista = new ArrayList<String[]>();
		try {
			citacDatoteke = new FileReader(datoteka);
			citac = new BufferedReader(citacDatoteke);
			citac.readLine();
			int brReda = 2;
			while(citac.ready()) {
				var linija = citac.readLine();
				var podaci = linija.split(";");
				if (podaci.length != 0 && podaci[0] != "#") {
					for (var podatak : podaci) {
						podatak = podatak.trim();
					}
					if (podaci.length != velicinaZaglavlja) {
						ispisiGreskuReda(datoteka, brReda, "Nedostaju podaci u redu");
						brReda++;
						continue;
					}
					
					if (!validacija.provjeriIspravnostReda(podaci, datoteka, brReda)) {
						brReda++;
						continue;
					}
					lista.add(podaci);
					brReda++;
				}
				else {
					brReda++;
				}
			}
			citac.close();
			return lista;
		} catch (IOException e) {
			System.out.println("Nije moguce ucitati datoteku: " + datoteka);
			brGreski++;
			return null;
		}
	}
	
	private boolean validirajKompoziciju(Kompozicija kompozicija) {
		
		boolean pogoni = true;
		int brPogona = 0;
		for (var zapis : kompozicija.prijevoznaSredstva.entrySet()) {
			if (zapis.getValue().matches("V") && pogoni) {
				pogoni = false;
			}
			else if (zapis.getValue().matches("P") && pogoni) {
				if (zapis.getKey().vrstaPogona.matches("N") || !(zapis.getKey() instanceof KompozicijskoVozilo)) {
					return false;
				}
				brPogona++;
			}
			else if (zapis.getValue().matches("V") && !pogoni) {
				continue;
			}
			else {
				return false;
			}
		}
		if (brPogona > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private void ucitajKompozicije(String datoteka) throws IOException {	
		var lista = ucitajCsvDatoteku(datoteka, 3, new KompozicijaValidacija());
		String zadnjaOznaka = "";
		IKompozicijaBuilder builder = new KompozicijaBuilder();
		int brReda = 1;
		for (var podaci : lista) {
			var oznaka = podaci[0];
			if (brReda == 1) {
				zadnjaOznaka = podaci[0];
			}
			if (!oznaka.matches(zadnjaOznaka)) {
				builder.postaviOznaku(zadnjaOznaka);
				var kompozicija = builder.konstruirajKompoziciju();
				
				if (!validirajKompoziciju(kompozicija)) {
					ispisiGreskuReda(datoteka, brReda, "Kompozicija nije validna");
				}
				TvrtkaSingleton.getInstance().sveKompozicije.put(zadnjaOznaka, kompozicija);
				builder = new KompozicijaBuilder();
				Vozilo vlak = TvrtkaSingleton.getInstance().svaVozila.get(podaci[1]);
				if (vlak == null) {
					ispisiGreskuReda(datoteka, brReda, "Vlak u kompoziciji ne postoji");
				}
				else {
					builder.dodajVlak(vlak, podaci[2]);	
				}
			}
			else {
				Vozilo vlak = TvrtkaSingleton.getInstance().svaVozila.get(podaci[1]);
				if (vlak == null) {
					ispisiGreskuReda(datoteka, brReda, "Vlak u kompoziciji ne postoji");
				}
				else {
					builder.dodajVlak(vlak, podaci[2]);
				}
			}
			zadnjaOznaka = oznaka;
			brReda++;
		}
		builder.postaviOznaku(zadnjaOznaka);
		var kompozicija = builder.konstruirajKompoziciju();
		if (!validirajKompoziciju(kompozicija)) {
			ispisiGreskuReda(datoteka, brReda, "Kompozicija nije validna");
		}
		TvrtkaSingleton.getInstance().sveKompozicije.put(kompozicija.getOznaka(), kompozicija);
	}
	
	
	public void ispisiGreskuReda(String datoteka, int red, String opis) {
		brGreski++;
		brGreskiDatoteka++;
		StringBuilder poruka = new StringBuilder();
		poruka.append(datoteka).append(": ").append("red ").append(red)
		.append(" - ").append(opis)
		.append(" |Br. greski: ").append(brGreski).append(" |Br. greski u datoteci: ").append(brGreskiDatoteka);
		System.out.println(poruka.toString());
	}
	
	
	private void ucitajStanice(String datoteka) throws IOException {
		var lista = ucitajCsvDatoteku(datoteka, 14, new StanicaValidacija());
		int brReda = 1;
		IPrugaBuilder builder = new PrugaBuilder();
		PruznaStanica zadnjaStanica = null;
		String zadnjaOznaka = "";
		for (var podaci : lista) {
			if (brReda == 1) {
				zadnjaOznaka = podaci[1];
			}
				if (!zadnjaOznaka.matches(podaci[1])) {
					Pruga pruga = builder.build();
					TvrtkaSingleton.getInstance().svePruge.put(pruga.oznakaPruge, pruga);
					builder = new PrugaBuilder();
				
					String oznakaPruge = podaci[1];
					String kategorijaPruge = podaci[6];
					String vrstaPruge = podaci[8];
					String statusPruge = podaci[12];
					builder.setKategorijaPruge(kategorijaPruge).setOznakaPruge(oznakaPruge)
					.setVrstaPruge(vrstaPruge).setStatusPruge(statusPruge);
					PruznaStanica stanica = new PruznaStanica(podaci);
					builder.dodajStanicu(stanica);
					zadnjaStanica = stanica;
					zadnjaOznaka = podaci[1];
				}
				else {
					if (zadnjaStanica == null || !zadnjaStanica.stanica.matches(podaci[0])) {
						String oznakaPruge = podaci[1];
						String kategorijaPruge = podaci[6];
						String vrstaPruge = podaci[8];
						String statusPruge = podaci[12];
						builder.setKategorijaPruge(kategorijaPruge).setOznakaPruge(oznakaPruge)
						.setVrstaPruge(vrstaPruge).setStatusPruge(statusPruge);
						PruznaStanica stanica = new PruznaStanica(podaci);
						builder.dodajStanicu(stanica);
						zadnjaStanica = stanica;
						zadnjaOznaka = podaci[1];
					}
					else {
						PruznaStanica stanica = zadnjaStanica.kloniraj();
						int brojKolosjeka = Integer.parseInt(podaci[9]);
						float dozvoljenoOpterecenjeOsovina = Float.parseFloat(podaci[10].replace(",", "."));
						float dozvoljenoOpterecenjeDuzniMetar = Float.parseFloat(podaci[11].replace(",", "."));
						int duzina = Integer.parseInt(podaci[13]);
						stanica.postaviPrugu(brojKolosjeka, dozvoljenoOpterecenjeOsovina, 
								dozvoljenoOpterecenjeDuzniMetar, duzina);
						builder.dodajStanicu(stanica);
						zadnjaStanica = stanica;
						zadnjaOznaka = podaci[1];
					}
				}
				brReda++;
			}
			Pruga pruga = builder.build();
			TvrtkaSingleton.getInstance().svePruge.put(pruga.oznakaPruge, pruga);
		}
	
	private void ucitajVlakove(String datoteka) throws IOException {
		var lista = ucitajCsvDatoteku(datoteka, 18, new VlakValidacija());
		
		int brReda = 1;
		
		for (var podaci : lista) {
			Vozilo vlak;
			VoziloCreator kompozicijskoCreator = new KompozicijskoVoziloCreator();
			VoziloCreator putnickoVoziloCreator = new PutnickoVoziloCreator();
			VoziloCreator teretnoVoziloCreator = new TeretnoVoziloCreator();
			if (podaci[5].startsWith("P")) {
				vlak = putnickoVoziloCreator.kreirajVozilo(podaci);
			}
			else if (podaci[5].matches("N")) {
				vlak = kompozicijskoCreator.kreirajVozilo(podaci);
				if (vlak.vrstaPogona.matches("N")) {
					ispisiGreskuReda(datoteka, brReda, "Kompozicijsko vozilo mora imati pogon");
					vlak = null;
				}
			}
			else {
				vlak = teretnoVoziloCreator.kreirajVozilo(podaci);
			}
			if (vlak != null) {
				TvrtkaSingleton.getInstance().svaVozila.put(vlak.oznaka, vlak);	
			}
			brReda++;
		}
}
	}