package ikerovec20_zadaca_3.state;

import ikerovec20_zadaca_3.podaci.KomponentaPruge;

public class UKvaruStanje extends StanjePruge {

	public UKvaruStanje(KomponentaPruge pruga) {
		super(pruga);
	}

	@Override
	public boolean promjeniStanje(String stanje) {
		return false;
	}

	@Override
	public boolean dozvoljenaVoznja() {
		// TODO Auto-generated method stub
		return false;
	}

}
