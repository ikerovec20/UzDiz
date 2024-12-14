package ikerovec20_zadaca_2_iteratori;

import ikerovec20_zadaca_2.podaci.Stanica;

public class PrugaIterator implements IPrugaIterator {

	private Stanica trenutnaStanica;
	private Stanica zadnjaStanica;
	private final String oznaka;
	
	public PrugaIterator(String oznaka, Stanica stanica) {
		this.oznaka = oznaka;
		trenutnaStanica = stanica;
		zadnjaStanica = stanica;
	}
	
	@Override
	public Stanica dohvatiSljedecuStanicu() {
		for (var veza : trenutnaStanica.veze) {
//			System.out.println("VEZA: " + veza.stanica.stanica + " : " + veza.pruga.oznakaPruge);
			if (veza.pruga.oznakaPruge.contains(oznaka) && !veza.stanica.stanica.matches(trenutnaStanica.stanica) && !veza.stanica.stanica.matches(zadnjaStanica.stanica)) {
				zadnjaStanica = trenutnaStanica;
				trenutnaStanica = veza.stanica;
				return veza.stanica;
			}
		}
		return null;
	}
	
	public int dohvatiUdaljenostDoStanice() {
		for (var veza : trenutnaStanica.veze) {
			if (veza.pruga.oznakaPruge.contains(oznaka) && !veza.stanica.stanica.matches(trenutnaStanica.stanica) && !veza.stanica.stanica.matches(zadnjaStanica.stanica)) {
				return veza.pruga.duzina;
			}
		}
		return -1;
	}

	@Override
	public boolean postojiSljedecaStanica() {
		for (var veza : trenutnaStanica.veze) {
			if (veza.pruga.oznakaPruge.contains(oznaka) && !veza.stanica.stanica.matches(trenutnaStanica.stanica) && !veza.stanica.stanica.matches(zadnjaStanica.stanica)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Stanica dohvatiTrenutnuStanicu() {
		return trenutnaStanica;
	}
}
