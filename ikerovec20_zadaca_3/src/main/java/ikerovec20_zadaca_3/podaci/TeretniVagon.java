package ikerovec20_zadaca_3.podaci;

public class TeretniVagon extends Vozilo {
	public float nosivost;
	public float povrsina;
	public TeretniVagon(String oznaka, String opis, String proizvodac, String godina, String vrstaPogona, String namjena,
			int maksBrzina, String status, float nosivost, float povrsina) {
		super(oznaka, opis, proizvodac, godina, vrstaPogona, namjena, maksBrzina, status);
		this.nosivost = nosivost;
		this.povrsina = povrsina;
	}
}
