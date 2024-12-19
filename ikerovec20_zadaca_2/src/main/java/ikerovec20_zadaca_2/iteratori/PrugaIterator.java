package ikerovec20_zadaca_2.iteratori;

import ikerovec20_zadaca_2.podaci.Stanica;

public class PrugaIterator implements IPrugaIterator {

	private Stanica trenutnaStanica;
	private Stanica prvaStanica;
	private Stanica zadnjaStanica;
	private final String oznaka;
	private final boolean normalniSmjer;
	
	public PrugaIterator(String oznaka, Stanica stanica, boolean normalniSmjer) {
		this.oznaka = oznaka;
		trenutnaStanica = stanica;
		prvaStanica = stanica;
		zadnjaStanica = stanica;
		this.normalniSmjer = normalniSmjer;
	}
	
	@Override
	public Stanica dohvatiSljedecuStanicu() {
		for (var veza : trenutnaStanica.veze) {
			if (veza.pruga != null && veza.pruga.oznakaPruge.contains(oznaka)
					&& !veza.stanica.stanica.matches(trenutnaStanica.stanica)
					&& !veza.stanica.stanica.matches(zadnjaStanica.stanica) && veza.normalniSmjer == this.normalniSmjer) {
				zadnjaStanica = trenutnaStanica;
				trenutnaStanica = veza.stanica;
				return veza.stanica;
			}
		}
		return null;
	}

	@Override
	public boolean postojiSljedecaStanica() {
		for (var veza : trenutnaStanica.veze) {
			if (veza.pruga != null && veza.pruga.oznakaPruge.contains(oznaka) && !veza.stanica.stanica.matches(trenutnaStanica.stanica)
					&& !veza.stanica.stanica.matches(zadnjaStanica.stanica) && veza.normalniSmjer == this.normalniSmjer) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Stanica dohvatiTrenutnuStanicu() {
		return trenutnaStanica;
	}

	@Override
	public Stanica vratiPrvuStanicu() {
		return prvaStanica;
	}
}
