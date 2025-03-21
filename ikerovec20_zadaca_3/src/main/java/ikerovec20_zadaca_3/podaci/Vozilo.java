package ikerovec20_zadaca_3.podaci;

public abstract class Vozilo {
	public Vozilo(String oznaka, String opis, String proizvodac, String godina, String vrstaPogona, String namjena,
			int maksBrzina, String status) {
		super();
		this.oznaka = oznaka;
		this.opis = opis;
		this.proizvodac = proizvodac;
		this.godina = godina;
		this.vrstaPogona = vrstaPogona;
		this.namjena = namjena;
		this.maksBrzina = maksBrzina;
		this.status = status;
	}
	public String oznaka;
	public String opis;
	public String proizvodac;
	public String godina;
	public String vrstaPogona;
	public String namjena;
	public int maksBrzina;
	public String status;
}
