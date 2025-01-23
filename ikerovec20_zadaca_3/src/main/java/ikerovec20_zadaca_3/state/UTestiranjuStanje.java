package ikerovec20_zadaca_3.state;

import ikerovec20_zadaca_3.podaci.KomponentaPruge;

public class UTestiranjuStanje extends StanjePruge {

	public UTestiranjuStanje(KomponentaPruge pruga) {
		super(pruga);
		statusOznaka = "T";
	}

	@Override
	public boolean promjeniStanje(String stanje) {
		switch (stanje) {
		case "I":
			pruga.promjeniStanje(new IspravnoStanje(pruga));
			return true;
		case "K":
			pruga.promjeniStanje(new UKvaruStanje(pruga));
			return true;
		case "Z":
			pruga.promjeniStanje(new ZatvorenoStanje(pruga));
			return true;
		case "T":
			return true;
		default:
			System.out.println("Promjena stanja 'T' -> '" + stanje + "' nije dozvoljena.");
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
		case "I":
			return true;
		case "K":
			return true;
		case "Z":
			return true;
		case "T":
			return false;
		default:
			System.out.println("Promjena stanja 'T' -> '" + stanje + "' nije dozvoljena.");
			return false;
		}
	}

}
