package ikerovec20_zadaca_3.state;

import ikerovec20_zadaca_3.podaci.KomponentaPruge;

public class ZatvorenoStanje extends StanjePruge {

	public ZatvorenoStanje(KomponentaPruge pruga) {
		super(pruga);
		statusOznaka = "Z";
	}

	@Override
	public boolean promjeniStanje(String stanje) {
		switch (stanje) {
		case "T":
			pruga.promjeniStanje(new UTestiranjuStanje(pruga));
			return true;
		case "Z":
			return true;
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
		case "T":
			return true;
		case "Z":
			return false;
		default:
			System.out.println("Promjena stanja 'K' -> '" + stanje + "' nije dozvoljena.");
			return false;
		}
	}

}
