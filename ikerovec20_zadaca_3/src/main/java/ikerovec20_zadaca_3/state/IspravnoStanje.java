package ikerovec20_zadaca_3.state;

import ikerovec20_zadaca_3.podaci.KomponentaPruge;

public class IspravnoStanje extends StanjePruge {

	public IspravnoStanje(KomponentaPruge pruga) {
		super(pruga);
	}

	@Override
	public boolean promjeniStanje(String stanje) {
		if (stanje.matches("K")) {
			//promjeni u kvar
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean dozvoljenaVoznja() {
		// TODO Auto-generated method stub
		return false;
	}

}
