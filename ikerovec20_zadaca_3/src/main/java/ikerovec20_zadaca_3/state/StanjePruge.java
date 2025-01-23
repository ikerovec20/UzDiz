package ikerovec20_zadaca_3.state;

import ikerovec20_zadaca_3.podaci.KomponentaPruge;

public abstract class StanjePruge {
	
	protected KomponentaPruge pruga;
	protected String statusOznaka;
	
	public StanjePruge(KomponentaPruge pruga) {
		this.pruga = pruga;
	}
	
	public String dohvatiOznaku() {
		return statusOznaka;
	}
	
	public abstract boolean promjeniStanje(String stanje);
	public abstract boolean dozvoljenaPromjenaStanja(String stanje);
	public abstract boolean dozvoljenaVoznja();
}
