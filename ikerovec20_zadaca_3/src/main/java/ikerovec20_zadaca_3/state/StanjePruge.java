package ikerovec20_zadaca_3.state;

import ikerovec20_zadaca_3.podaci.KomponentaPruge;

public abstract class StanjePruge {
	
	protected KomponentaPruge pruga;
	
	public StanjePruge(KomponentaPruge pruga) {
		this.pruga = pruga;
	}
	
	public abstract boolean promjeniStanje(String stanje);
	public abstract boolean dozvoljenaVoznja();
}
