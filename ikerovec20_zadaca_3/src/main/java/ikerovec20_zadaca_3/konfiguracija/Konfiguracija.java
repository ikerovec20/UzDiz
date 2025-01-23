package ikerovec20_zadaca_3.konfiguracija;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;
import ikerovec20_zadaca_3.builderi.IKompozicijaBuilder;
import ikerovec20_zadaca_3.builderi.IPrugaBuilder;
import ikerovec20_zadaca_3.builderi.KompozicijaBuilder;
import ikerovec20_zadaca_3.builderi.PrugaBuilder;
import ikerovec20_zadaca_3.composite.Etapa;
import ikerovec20_zadaca_3.composite.Vlak;
import ikerovec20_zadaca_3.composite.VozniRed;
import ikerovec20_zadaca_3.podaci.KomponentaPruge;
import ikerovec20_zadaca_3.podaci.Kompozicija;
import ikerovec20_zadaca_3.podaci.KompozicijskoVozilo;
import ikerovec20_zadaca_3.podaci.Pruga;
import ikerovec20_zadaca_3.podaci.Stanica;
import ikerovec20_zadaca_3.podaci.Vozilo;
import ikerovec20_zadaca_3.state.IspravnoStanje;
import ikerovec20_zadaca_3.tvornice.KompozicijskoVoziloCreator;
import ikerovec20_zadaca_3.tvornice.PutnickoVoziloCreator;
import ikerovec20_zadaca_3.tvornice.TeretnoVoziloCreator;
import ikerovec20_zadaca_3.tvornice.VagonZaAutomobileCreator;
import ikerovec20_zadaca_3.tvornice.VagonZaRobuCreator;
import ikerovec20_zadaca_3.tvornice.VoziloCreator;

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
	
	public boolean ucitajKonfiguraciju(String[] args) throws Exception {
		if (args.length != 10) {
			System.out.println("Neispravan broj argumenata");
			return false;
		}
		String stanice = "";
		String kompozicije = "";
		String vlakovi = "";
		String dani = "";
		String vozniRed = "";
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
				
			case "--zod":
				dani = args[i+1];
				break;
				
			case "--zvr":
				vozniRed = args[i+1];
				break;
			default: 
				System.out.println("Neispravan format argumenata.");
				return false;
			}
		}
		ucitajStanice(stanice);
		ucitajVlakove(vlakovi);
		ucitajKompozicije(kompozicije);
		ucitajVremenskeOznake(dani);
		ucitajVozniRed(vozniRed);
		return true;
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
				String[] linije = new String[velicinaZaglavlja];
				var linija = citac.readLine();
				var podaci = linija.split(";");
				if (podaci.length != 0 && !podaci[0].startsWith("#")) {
					for (var podatak : podaci) {
						podatak = podatak.trim();
					}
					if (podaci.length > velicinaZaglavlja) {
						ispisiGreskuReda(datoteka, brReda, "Suvišni podaci u redu");
						brReda++;
						continue;
					}
					
					for (int i = 0; i < podaci.length; i++) {
						linije[i] = podaci[i];
					}
					
					if (!validacija.provjeriIspravnostReda(linije, datoteka, brReda)) {
						brReda++;
						continue;
					}
					lista.add(linije);
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
		if (lista == null) {
			return;
		}
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
				else {
					TvrtkaSingleton.getInstance().getSveKompozicije().put(zadnjaOznaka, kompozicija);	
				}
				builder = new KompozicijaBuilder();
				Vozilo vlak = TvrtkaSingleton.getInstance().getSvaVozila().get(podaci[1]);
				if (vlak == null) {
					ispisiGreskuReda(datoteka, brReda, "Vlak u kompoziciji ne postoji");
				}
				else {
					builder.dodajVlak(vlak, podaci[2]);	
				}
			}
			else {
				Vozilo vlak = TvrtkaSingleton.getInstance().getSvaVozila().get(podaci[1]);
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
		TvrtkaSingleton.getInstance().getSveKompozicije().put(kompozicija.getOznaka(), kompozicija);
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
	
	public void ispisiGresku(String opis) {
		brGreski++;
		StringBuilder poruka = new StringBuilder();
		poruka.append(opis)
		.append(" |Br. greski: ").append(brGreski);
		System.out.println(poruka.toString());
	}
	
	public void ucitajVremenskeOznake(String datoteka) {
		var lista = ucitajCsvDatoteku(datoteka, 2, new DaniValidacija());
		if (lista == null) {
			return;
		}
		
		for (var podaci : lista) {
			int index = Integer.parseInt(podaci[0]);
			
			TvrtkaSingleton.getInstance().getOznakeDana().put(index, podaci[1]);
		}
	}
	
	private void ucitajVozniRed(String datoteka) {
		var lista = ucitajCsvDatoteku(datoteka, 9, new VozniRedValidacija());
		if (lista == null) {
			return;
		}
		int brReda = 1;
		
		
		VozniRed vozniRed = new VozniRed();
		for (var podaci : lista) {
			String oznakaPruge = podaci[0];
			String smjer = podaci[1];
			String pocetnaStanica = podaci[2];
			String zavrsnaStanica = podaci[3];
			String oznakaVlaka = podaci[4];
			String vrstaVlaka = podaci[5];
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
			LocalTime vrijemePolaska = LocalTime.parse(podaci[6], formatter);
			LocalTime trajanjeVoznje = LocalTime.parse(podaci[7], formatter);
			int indeksDana;
			if (podaci[8].length() == 0) {
				indeksDana = -1;
			}
			else {
				indeksDana = Integer.parseInt(podaci[8]);
			}
			String daniUTjednu = TvrtkaSingleton.getInstance().getOznakeDana().get(indeksDana);
			if (daniUTjednu == null || daniUTjednu.length() == 0) {
				daniUTjednu = "PoUSrČPeSuN";
			}
			
			var vlak = vozniRed.dohvatiVlak(oznakaVlaka);
			if (vlak == null) {
				vlak = new Vlak(oznakaVlaka, vrstaVlaka);
				vozniRed.dodajKomponentu(vlak);
			}
			if (!vlak.vrstaVlaka.matches(vrstaVlaka)) {
				ispisiGreskuReda(datoteka, brReda, "Vlak s oznakom " + oznakaVlaka + " postoji, ali s različitom vrstom.");
				brReda++;
				continue;
			}
			Pruga pruga = TvrtkaSingleton.getInstance().getSvePruge().get(oznakaPruge);
			Stanica pocetna = null;
			Stanica zavrsna = null;
			if (pruga == null) {
				ispisiGreskuReda(datoteka, brReda, "Ne postoji pruga s oznakom " + oznakaPruge);
				brReda++;
				continue;
			}
			if (pocetnaStanica.length() == 0) {
				if (smjer.matches("N")) {
					pocetna = pruga.pocetnaStanica;
				}
				else {
					pocetna = pruga.zavrsnaStanica;
				}
			}
			else {
				pocetna = TvrtkaSingleton.getInstance().getSveStanice().get(pocetnaStanica);
			}
			
			if (zavrsnaStanica.length() == 0) {
				if (smjer.matches("N")) {
					zavrsna = pruga.zavrsnaStanica;
				}
				else {
					zavrsna = pruga.pocetnaStanica;
				}
			}
			else {
				zavrsna = TvrtkaSingleton.getInstance().getSveStanice().get(zavrsnaStanica);
			}
			
			if (pocetna == null || zavrsna == null || pruga == null) {
				ispisiGreskuReda(datoteka, brReda, "Ne postoji pocetna stanica, zavrsna stanica ili pruga.");
				brReda++;
				continue;
			}
			
			Etapa etapa = new Etapa(pocetna, zavrsna, smjer, vrstaVlaka, pruga, vrijemePolaska, trajanjeVoznje, daniUTjednu);
			vlak.dodajKomponentu(etapa);
 		}
		vozniRed.validiraj();
		TvrtkaSingleton.getInstance().setVozniRed(vozniRed);
	}
	
	private void ucitajVremenaVlakova(ArrayList<String[]> lista) {
		int brReda = 1;
		Stanica zadnjaStanicaBrziVlak = null;
		Stanica zadnjaStanicaUbrzaniVlak = null;
		String zadnjaOznaka = "";
		
		for (var podaci : lista) {
			
			String oznaka = podaci[1];
			Stanica stanica = TvrtkaSingleton.getInstance().getSveStanice().get(podaci[0]);
			int vrijemeUbrzaniVlak = Integer.parseInt(podaci[15]);
			int vrijemeBrziVlak = Integer.parseInt(podaci[16]);
			
			if (brReda == 1) {
				zadnjaOznaka = oznaka;
			}
			
			if (!oznaka.matches(zadnjaOznaka)) {
				zadnjaStanicaBrziVlak = null;
				zadnjaStanicaUbrzaniVlak = null;
			}
			
			if (vrijemeUbrzaniVlak != -1) {
				if (zadnjaStanicaUbrzaniVlak != null) {
					zadnjaStanicaUbrzaniVlak.dodajVezu(stanica, null, true, oznaka).setVrijemeUbrzaniVlak(vrijemeUbrzaniVlak);
					var veza = stanica.dodajVezu(zadnjaStanicaUbrzaniVlak, null, false, oznaka);
					veza.setVrijemeUbrzaniVlak(vrijemeUbrzaniVlak);

					zadnjaStanicaUbrzaniVlak = stanica;
				}
				else {
					zadnjaStanicaUbrzaniVlak = stanica;
				}
			}
			if (vrijemeBrziVlak != -1) {
				if (zadnjaStanicaBrziVlak != null) {
					zadnjaStanicaBrziVlak.dodajVezu(stanica, null, true, oznaka).setVrijemeBrziVlak(vrijemeBrziVlak);
					stanica.dodajVezu(zadnjaStanicaBrziVlak, null, false, oznaka).setVrijemeBrziVlak(vrijemeBrziVlak);
					zadnjaStanicaBrziVlak = stanica;
				}
				else {
					zadnjaStanicaBrziVlak = stanica;
				}
			}
			zadnjaOznaka = oznaka;
			brReda++;
		}
	}
	
	private void ucitajStanice(String datoteka) throws IOException {
		var lista = ucitajCsvDatoteku(datoteka, 17, new StanicaValidacija());
		if (lista == null) {
			return;
		}
		int brReda = 1;
		Stanica zadnjaStanica = null;
		String zadnjaOznaka = "";
		Pruga trenutnaPruga = null;
		IPrugaBuilder prugaBuilder = null;
		for (var podaci : lista) {
			Stanica stanica = TvrtkaSingleton.getInstance().getSveStanice().get(podaci[0]);
			
			String oznakaPruge = podaci[1];
			String kategorijaPruge = podaci[6];
			String vrstaPruge = podaci[8];
			String statusPruge = podaci[12];
			int vrijemeNormalniVlak = Integer.parseInt(podaci[14]);
			if (stanica == null) {
				String imeStanice = podaci[0];
				String vrstaStanice = podaci[2];
				String statusStanice = podaci[3];
				boolean putniciUlazIzlaz = podaci[4].matches("DA");
				boolean robaUtovarIstovar = podaci[5].matches("DA");
				int brojPerona = Integer.parseInt(podaci[7]);
				stanica = new Stanica(imeStanice, vrstaStanice, statusStanice, putniciUlazIzlaz, robaUtovarIstovar, brojPerona);
				TvrtkaSingleton.getInstance().getSveStanice().put(stanica.stanica, stanica);
			}
			
			if (brReda == 1) {
				zadnjaStanica = stanica;
				zadnjaOznaka = podaci[1];
				brReda++;
				prugaBuilder = new PrugaBuilder();
				prugaBuilder.setOznakaPruge(oznakaPruge).setKategorijaPruge(kategorijaPruge).setVrstaPruge(vrstaPruge).setStatusPruge(statusPruge).postaviPocetnuStanicu(stanica);
				trenutnaPruga = prugaBuilder.build();
				continue;
			}
			else if (!zadnjaStanica.stanica.matches(podaci[0]) && zadnjaOznaka.matches(podaci[1])) {
					KomponentaPruge pruga = new KomponentaPruge(podaci);
					zadnjaStanica.dodajVezu(stanica, pruga, true, podaci[1]).setVrijemeNormalniVlak(vrijemeNormalniVlak);
					trenutnaPruga.ukupnoKm += pruga.duzina;
					stanica.dodajVezu(zadnjaStanica, pruga, false, podaci[1]).setVrijemeNormalniVlak(vrijemeNormalniVlak);

			}
			else if (zadnjaStanica.stanica.matches(podaci[0])) {
				KomponentaPruge pruga = new KomponentaPruge(podaci);
				stanica.dodajVezu(stanica, pruga, true, podaci[1]).setVrijemeNormalniVlak(vrijemeNormalniVlak);
			}
			if (!zadnjaOznaka.matches(podaci[1])) {
				trenutnaPruga.zavrsnaStanica = zadnjaStanica;
				TvrtkaSingleton.getInstance().getSvePruge().put(zadnjaOznaka, trenutnaPruga);
				prugaBuilder = new PrugaBuilder();
				prugaBuilder.setOznakaPruge(oznakaPruge).setKategorijaPruge(kategorijaPruge).setVrstaPruge(vrstaPruge).setStatusPruge(statusPruge).postaviPocetnuStanicu(stanica);
				trenutnaPruga = prugaBuilder.build();
			}
				zadnjaStanica = stanica;
				zadnjaOznaka = podaci[1];
				brReda++;
			}
		prugaBuilder.postaviZavrsnuStanicu(zadnjaStanica);
		var pruga = prugaBuilder.build();
		if (!pruga.pocetnaStanica.equals(pruga.zavrsnaStanica)) {
			TvrtkaSingleton.getInstance().getSvePruge().put(zadnjaOznaka, pruga);	
			}
		ucitajVremenaVlakova(lista);
		}
	
	private void ucitajVlakove(String datoteka) throws IOException {
		var lista = ucitajCsvDatoteku(datoteka, 18, new VlakValidacija());
		if (lista == null) {
			return;
		}
		
		int brReda = 1;
		
		for (var podaci : lista) {
			Vozilo vlak;
			VoziloCreator kompozicijskoCreator = new KompozicijskoVoziloCreator();
			VoziloCreator putnickoVoziloCreator = new PutnickoVoziloCreator();
			VoziloCreator teretnoVoziloCreator = new TeretnoVoziloCreator();
			VoziloCreator vagonZaAutomobileCreator = new VagonZaAutomobileCreator();
			VoziloCreator vagonZaRobuCreator = new VagonZaRobuCreator();
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
				switch(podaci[5]) {
				case "TK":
					vlak = teretnoVoziloCreator.kreirajVozilo(podaci);
					break;
				case "TA":
					vlak = vagonZaAutomobileCreator.kreirajVozilo(podaci);
					break;
				case "TRS":
				case "TPS":
				case "TTS":
					vlak = vagonZaRobuCreator.kreirajVozilo(podaci);
					break;
				default: 
					System.out.println("Greska kod kreiranja vlaka");
					vlak = null;
					break;
				}
			}
			if (vlak != null) {
				TvrtkaSingleton.getInstance().getSvaVozila().put(vlak.oznaka, vlak);	
			}
			brReda++;
		}
}
	}