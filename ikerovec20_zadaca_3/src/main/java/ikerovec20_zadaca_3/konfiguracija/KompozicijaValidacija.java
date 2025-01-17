package ikerovec20_zadaca_3.konfiguracija;

public class KompozicijaValidacija implements IValidacija {

	@Override
	public boolean provjeriIspravnostReda(String[] podaci, String datoteka, int brReda) {
		if (!podaci[2].matches("P|V")) {
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "uloga nije ispravna");
			return false;
		}
		else {
			return true;
		}
	}

}
