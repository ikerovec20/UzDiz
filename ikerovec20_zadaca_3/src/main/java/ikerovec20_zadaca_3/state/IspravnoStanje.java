package ikerovec20_zadaca_3.state;

import ikerovec20_zadaca_3.podaci.KomponentaPruge;

public class IspravnoStanje extends StanjePruge {

	public IspravnoStanje(KomponentaPruge pruga) {
		super(pruga);
	}

	@Override
	public boolean promjeniStanje(String stanje) {
		switch (stanje) {
		case "K":
			pruga.promjeniStanje(new UKvaruStanje(pruga));
			return true;
		case "Z":
			pruga.promjeniStanje(new ZatvorenoStanje(pruga));
			return true;
		case "I":
			return true;
		default:
			System.out.println("Promjena stanja 'I' -> '" + stanje + "' nije dozvoljena.");
			return false;
		}
	}

	@Override
	public boolean dozvoljenaVoznja() {
		return true;
	}

	@Override
	public boolean dozvoljenaPromjenaStanja(String stanje) {
		switch (stanje) {
		case "K":
			return true;
		case "Z":
			return true;
		case "I":
			return false;
		default:
			System.out.println("Promjena stanja 'I' -> '" + stanje + "' nije dozvoljena.");
			return false;
		}
	}

}
