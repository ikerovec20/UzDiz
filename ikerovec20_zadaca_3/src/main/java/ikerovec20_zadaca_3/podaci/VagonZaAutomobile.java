package ikerovec20_zadaca_3.podaci;

public class VagonZaAutomobile extends TeretniVagon {
	public int brojAutomobila;

	public VagonZaAutomobile(String oznaka, String opis, String proizvodac, String godina, String vrstaPogona,
			String namjena, int maksBrzina, String status, float nosivost, float povrsina, int automobili) {
		super(oznaka, opis, proizvodac, godina, vrstaPogona, namjena, maksBrzina, status, nosivost, povrsina);
		brojAutomobila = automobili;
	}
}
