package ikerovec20_zadaca_1.konfiguracija;

public class VlakValidacija implements IValidacija {

	@Override
	public boolean provjeriIspravnostReda(String[] podaci, String datoteka, int brReda) {
		boolean ispravno = true;
		if (!podaci[4].matches("PSVPVK|PSVP|PSBP")) {
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Namjena vlaka nije ispravna");
			ispravno = false;
		}
		if (!podaci[5].matches("N|P|TA|TK|TRS|TTS|TPS")) {
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Vrsta prijevoza nije ispravna");
			ispravno = false;
		}
		if (!podaci[6].matches("D|E|N")) {
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Vrsta pogona nije ispravna");
			ispravno = false;
		}
		if (!podaci[7].matches("\\d+")) {
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Maks brzina nije ispravna");
			ispravno = false;
		}
		if (!podaci[8].matches("^\\d*\\,?\\d*")) {
			if (!podaci[8].matches("-1")) {
				Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Maks snaga nije ispravna");
				ispravno = false;
			}
		}
		for (int i = 9; i < 14; i++) {
			if (!podaci[i].matches("\\d+")) {
				Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Broj mjesta/kreveta/automobila/bicikala nije ispravan");
				ispravno = false;
			}
		}
		for (int i = 14; i < 17; i++) {
			if (!podaci[i].matches("^\\d*\\,?\\d*$")) {
				Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Nosivost/povrsina/zapremina nije ispravna");
				ispravno = false;
			}
		}
		if (!podaci[17].matches("I|K")) {
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Status nije ispravan");
			ispravno = false;
		}
		return ispravno;
	}

}
