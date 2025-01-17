package ikerovec20_zadaca_3.konfiguracija;

public class DaniValidacija implements IValidacija {

	@Override
	public boolean provjeriIspravnostReda(String[] podaci, String datoteka, int brReda) {
		boolean ispravno = true;
		
		if (ispravno && !podaci[0].matches("\\d+")) {
			ispravno = false;
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Oznaka dana nije ispravna");
		}
		
		if (ispravno && podaci[1] == null || podaci[1].length() == 0) {
			podaci[1] = "PoUSrČPeSuN";
		}
		
		return ispravno;
	}

}
