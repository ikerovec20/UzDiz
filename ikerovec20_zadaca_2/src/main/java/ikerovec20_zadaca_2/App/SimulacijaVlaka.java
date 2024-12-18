package ikerovec20_zadaca_2.App;
import java.time.LocalTime;

import ikerovec20_zadaca_2.composite.Etapa;
import ikerovec20_zadaca_2.composite.EtapnaStanica;
import ikerovec20_zadaca_2.composite.Vlak;
import ikerovec20_zadaca_2.observer.IDojavljac;

public class SimulacijaVlaka implements Runnable {

	public String oznaka;
	private Vlak vlak;
	private IDojavljac dojavljac;
	private int koeficijent;
	private String dan;
	private boolean zaustavi = false;
	
	public void zaustavi() {
		this.zaustavi = true;
	}
	
	@Override
	public void run() {
		double sec = 60 * 1000 / koeficijent;
		long virtualnaSekunda = (long) sec;
		var iterator = vlak.dohvatiIterator();
		boolean pocetak = true;
		LocalTime vrijeme = null;
		while (iterator.postojiSljedecaKomponenta()) {
			Etapa etapa = (Etapa) iterator.dohvatiSljedecuKomponentu();
			if (!etapa.raspored.provjeriDane(dan)) {
				continue;
			}

			var etapaIterator = etapa.dohvatiIterator();
			var pocetnaStanica = etapaIterator.dohvatiTrenutnuKomponentu();
			if (pocetak) {
				vrijeme = pocetnaStanica.vratiPocetnoVrijeme();
				pocetak = false;
			}
			while (vrijeme.compareTo(pocetnaStanica.vratiPocetnoVrijeme()) != 0) {
				try {
					Thread.sleep(virtualnaSekunda);
					vrijeme = vrijeme.plusMinutes(1);
					System.out.println("VRIJEME: " + vrijeme + " DOLAZAK: " + pocetnaStanica.vratiPocetnoVrijeme());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				if (zaustavi) {
					System.out.println("Zaustavlja se simulacija vlaka " + oznaka);
					return;
				}
				System.out.println("Vlak " + oznaka + " krenuo je sa stanice " + vlak.vratiPrvuStanicu().stanica + " u vrijeme " + vrijeme);
			while (etapaIterator.postojiSljedecaKomponenta()) {
				EtapnaStanica stanica = (EtapnaStanica) etapaIterator.dohvatiSljedecuKomponentu();
				if (stanica.equals(pocetnaStanica)) {
					continue;
				}
				switch (vlak.vrstaVlaka) {
				case "U":
					if (stanica.vrijemeUbrzaniVlak == -1) {
						continue;
					}
					break;
				case "B":
					if (stanica.vrijemeBrziVlak == -1) {
						continue;
					}
					break;
				}
				LocalTime vrijemeDolaska = stanica.vratiPocetnoVrijeme();
				while (vrijeme.compareTo(vrijemeDolaska) != 0) {
					try {
						Thread.sleep(virtualnaSekunda);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (zaustavi) {
						System.out.println("Zaustavlja se simulacija vlaka " + oznaka);
						return;
					}
					vrijeme = vrijeme.plusMinutes(1);
					System.out.println("VRIJEME: " + vrijeme + " DOLAZAK: " + vrijemeDolaska);
				}
				System.out.println("Vlak " + oznaka + " stigao je na stanicu " + stanica.vratiPrvuStanicu().stanica + " u vrijeme " + vrijeme);
				dojavljac.javiKorisnicima(oznaka, stanica.stanica.stanica);	
			}
		}
		System.out.println("Simulacija vlaka " + oznaka + " završena.");
	}

	public SimulacijaVlaka(String oznaka, Vlak vlak, IDojavljac dojavljac, int koef, String dan) {
		super();
		this.oznaka = oznaka;
		this.vlak = vlak;
		this.dojavljac = dojavljac;
		this.koeficijent = koef;
		this.dan = dan;
	}
}
