package ikerovec20_zadaca_2.composite;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import ikerovec20_zadaca_2.podaci.Pruga;
import ikerovec20_zadaca_2.podaci.Stanica;

public class Etapa extends VozniRedComposite {
	public Stanica pocetnaStanica;
	public Stanica zavrsnaStanica;
	public String smjer;
	public Pruga pruga;
	public ArrayList<Stanica> stanice;
	public LocalTime vrijemePolaska;
	public LocalTime vrijemeTrajanja;
	public int ukupnoKm = 0;
	
	public Etapa(Stanica pocetna, Stanica zavrsna, String smjer, Pruga pruga, LocalTime vrijemePolaska, LocalTime vrijemeTrajanja) {
		stanice = new ArrayList<Stanica>();
		this.pocetnaStanica = pocetna;
		this.zavrsnaStanica = zavrsna;
		this.smjer = smjer;
		this.pruga = pruga;
		this.vrijemePolaska = vrijemePolaska;
		this.vrijemeTrajanja = vrijemeTrajanja;
	}
	
	public void ucitajStanice() {
		int ukupnoKm = 0;
		boolean smjer = this.smjer.matches("N");
		var iterator = pruga.dohvatiIterator(pocetnaStanica, smjer);
		Stanica stanica = iterator.dohvatiTrenutnuStanicu();
		stanice.add(stanica);
		while (iterator.postojiSljedecaStanica() && stanica != zavrsnaStanica) {
			ukupnoKm += iterator.dohvatiUdaljenostDoStanice();
			stanica = iterator.dohvatiSljedecuStanicu();
			stanice.add(stanica);
		}
		this.ukupnoKm = ukupnoKm;
		stanice.add(stanica);
	}

	@Override
	public int vratiUkupnoKm() {
		return ukupnoKm;
	}
}
