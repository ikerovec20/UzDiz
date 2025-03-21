package ikerovec20_zadaca_2.podaci;

public class PutnickoVozilo extends Vozilo {
	public int brojSjedecihMjesta;
	public int brojStajacihMjesta;
	public int brojBicikala;
	public int brojKreveta;
	public PutnickoVozilo(String oznaka, String opis, String proizvodac, String godina, String vrstaPogona, String namjena,
			int maksBrzina, String status, int sjedeca, int stajaca, int bicikli, int kreveti) {
		super(oznaka, opis, proizvodac, godina, vrstaPogona, namjena, maksBrzina, status);
		brojSjedecihMjesta = sjedeca;
		brojStajacihMjesta = stajaca;
		brojBicikala = bicikli;
		brojKreveta = kreveti;
	}
}
