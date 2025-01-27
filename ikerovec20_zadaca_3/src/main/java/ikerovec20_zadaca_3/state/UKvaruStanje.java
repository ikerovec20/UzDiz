package ikerovec20_zadaca_3.state;

import ikerovec20_zadaca_3.podaci.KomponentaPruge;

public class UKvaruStanje extends StanjePruge {

	public UKvaruStanje(KomponentaPruge pruga) {
		super(pruga);
		statusOznaka = "K";
	}

	@Override
	public boolean promjeniStanje(String stanje) {
		switch (stanje) {
		case "I":
//			pruga.promjeniStanje(new IspravnoStanje(pruga));
			pruga.stanje = new IspravnoStanje(pruga);
			return true;
		case "Z":
//			pruga.promjeniStanje(new ZatvorenoStanje(pruga));
			pruga.stanje = new ZatvorenoStanje(pruga);
			return true;
		case "K":
			return false;
		default:
			System.out.println("Promjena stanja 'K' -> '" + stanje + "' nije dozvoljena.");
			return false;
		}
	}

	@Override
	public boolean dozvoljenaVoznja() {
		return false;
	}

	@Override
	public boolean dozvoljenaPromjenaStanja(String stanje) {
		switch (stanje) {
		case "I":
			return true;
		case "Z":
			return true;
		case "K":
			return false;
		default:
			System.out.println("Promjena stanja 'K' -> '" + stanje + "' nije dozvoljena.");
			return false;
		}
	}

}
