package ikerovec20_zadaca_3.konfiguracija;

public class VozniRedValidacija implements IValidacija {

	@Override
	public boolean provjeriIspravnostReda(String[] podaci, String datoteka, int brReda) {
		boolean ispravno = true;
		if (ispravno && !podaci[1].matches("N|O")) {
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Smjer etape nije ispravan");
			ispravno = false;
		}
		
		if (ispravno && !podaci[5].matches("N|U|B")) {
			if (podaci[5].length() == 0) {
				podaci[5] = "N";
			}
			else {
				Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Vrsta vlaka nije ispravna");
				ispravno = false;
			}
		}
		
		if (ispravno && !podaci[6].matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Vrijeme polaska vlaka nije ispravno");
			ispravno = false;
		}
		
		if (ispravno && !podaci[7].matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Vrijeme putovanja vlaka nije ispravno");
			ispravno = false;
		}
		
		if (ispravno && podaci[8] != null && podaci[8].length() != 0 && !podaci[8].matches("\\d+")) {
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Oznake dana nisu ispravne");
			ispravno = false;
		}
		else if (podaci[8] == null) {
			podaci[8] = "";
		}
		
		return ispravno;
	}
}
