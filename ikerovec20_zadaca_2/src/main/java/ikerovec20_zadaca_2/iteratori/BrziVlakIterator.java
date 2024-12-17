package ikerovec20_zadaca_2.iteratori;

import ikerovec20_zadaca_2.podaci.Stanica;

public class BrziVlakIterator implements IPrugaIterator {

	private Stanica trenutnaStanica;
	private Stanica prvaStanica;
	private Stanica zadnjaStanica;
	private final String oznaka;
	private final boolean normalniSmjer;
	
	public BrziVlakIterator(String oznaka, Stanica stanica, boolean normalniSmjer) {
		this.oznaka = oznaka;
		trenutnaStanica = stanica;
		prvaStanica = stanica;
		zadnjaStanica = stanica;
		this.normalniSmjer = normalniSmjer;
	}
	
	@Override
	public Stanica dohvatiTrenutnuStanicu() {
		return trenutnaStanica;
	}

	@Override
	public Stanica dohvatiSljedecuStanicu() {
		for (var veza : trenutnaStanica.veze) {
			if (veza.oznakaPruge.contains(oznaka)
					&& !veza.stanica.stanica.matches(trenutnaStanica.stanica)
					&& !veza.stanica.stanica.matches(zadnjaStanica.stanica) && veza.normalniSmjer == this.normalniSmjer && veza.vrijemeNormalniVlak != -1) {
				return veza.stanica;
			}
		}
		return null;
	}

	@Override
	public Stanica vratiPrvuStanicu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean postojiSljedecaStanica() {
		// TODO Auto-generated method stub
		return false;
	}

}
