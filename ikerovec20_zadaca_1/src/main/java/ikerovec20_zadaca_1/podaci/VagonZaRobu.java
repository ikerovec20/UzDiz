package ikerovec20_zadaca_1.podaci;

public class VagonZaRobu extends TeretniVagon {
	public float zapremina;

	public VagonZaRobu(String oznaka, String opis, String proizvodac, String godina, String vrstaPogona, String namjena,
			int maksBrzina, String status, float nosivost, float povrsina, float zapremina) {
		super(oznaka, opis, proizvodac, godina, vrstaPogona, namjena, maksBrzina, status, nosivost, povrsina);
		this.zapremina = zapremina;
	}
}
