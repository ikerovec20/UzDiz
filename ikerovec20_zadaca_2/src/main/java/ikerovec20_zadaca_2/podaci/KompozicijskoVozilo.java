package ikerovec20_zadaca_2.podaci;

public class KompozicijskoVozilo extends Vozilo {
	public float maksSnaga;

	public KompozicijskoVozilo(String oznaka, String opis, String proizvodac, String godina, String vrstaPogona,
			String namjena, int maksBrzina, String status, float maksSnaga) {
		super(oznaka, opis, proizvodac, godina, vrstaPogona, namjena, maksBrzina, status);
		this.maksSnaga = maksSnaga;
	}
}
