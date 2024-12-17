package ikerovec20_zadaca_2.composite;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import ikerovec20_zadaca_2.podaci.Pruga;
import ikerovec20_zadaca_2.podaci.Stanica;

public class Etapa implements IKomponentaVoznogReda{
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
		ucitajStanice();
	}
	
	public void ucitajStanice() {
		boolean smjer = this.smjer.matches("N");
		var iterator = pruga.dohvatiIterator(pocetnaStanica, smjer);
		int ukupno = 0;
		Stanica stanica = iterator.dohvatiTrenutnuStanicu();
		stanice.add(stanica);
		boolean proslo = false;
		while (iterator.postojiSljedecaStanica() && !stanica.equals(zavrsnaStanica)) {
			stanica = iterator.dohvatiTrenutnuStanicu();
			var nova = iterator.dohvatiSljedecuStanicu();
			if (!proslo) {
				ukupno += stanica.dohvatiVezu(iterator.dohvatiTrenutnuStanicu()).pruga.duzina;
			}
			if (nova.equals(zavrsnaStanica)) {
				proslo = true;
			}
			stanice.add(nova);
		}
		stanice.add(stanica);
		this.ukupnoKm = ukupno;
	}

	
	@Override
	public int vratiKm() {
		return ukupnoKm;
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
		LocalTime vrijeme = vrijemePolaska.plusHours(vrijemeTrajanja.getHour());
		return vrijeme.plusMinutes(vrijemeTrajanja.getMinute());
	}
}
