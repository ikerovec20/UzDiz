package ikerovec20_zadaca_1.konfiguracija;

public class StanicaValidacija implements IValidacija {

	@Override
	public boolean provjeriIspravnostReda(String[] podaci, String datoteka, int brReda) {
		boolean ispravno = true;
		if (!podaci[2].matches("kol\\.|staj\\.")) {
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Vrsta stanice nije ispravna");
			ispravno = false;
		}
		if (!podaci[3].matches("O|Z")) {
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Status stanice nije ispravan");
			ispravno = false;
		}
		if (!podaci[4].matches("DA|NE") || !podaci[5].matches("DA|NE")) {
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Putnici ulaz/izlaz i/ili roba utovar/istovar nisu ispravni");
			ispravno = false;
		}
		if (!podaci[6].matches("L|R|M") || !podaci[12].matches("I|K|Z")) {
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Kategorija i/ili status pruge nije ispravan");
			ispravno = false;
		}
		if (!podaci[7].matches("\\d") || !podaci[9].matches("\\d")) {
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Broj perona ili broj kolosjeka stanice nije ispravan");
			ispravno = false;
		}
		if (!podaci[8].matches("K|E")) {
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Vrsta pruge nije ispravna");
			ispravno = false;
		}
		if (!podaci[10].matches("^\\d*\\,?\\d*$") || !podaci[11].matches("^\\d*\\,?\\d*$")) {
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "DO po osovini i/ili DO po duznom m nije ispravan");
			ispravno = false;
		}
		if (!podaci[13].matches("\\d+")) {
			Konfiguracija.getInstance().ispisiGreskuReda(datoteka, brReda, "Duzina pruge nije ispravna");
			ispravno = false;
		}
		return ispravno;
	}

}
