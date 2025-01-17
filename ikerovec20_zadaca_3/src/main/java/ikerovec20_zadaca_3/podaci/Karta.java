package ikerovec20_zadaca_3.podaci;

import java.time.LocalTime;
import java.util.Date;

public class Karta {
	public String oznakaVlaka;
	public String pocetnaStanica;
	public String odredisnaStanica;
	public LocalTime vrijemeKretanja;
	public LocalTime vrijemeDolaska;
	public double izvornaCijena;
	public double popustApp = 0;
	public double popustVikend = 0;
	public double povecanje = 0;
	public double konacnaCijena;
	public String nacinKupovine;
	public Date datumKupovine;
	public LocalTime vrijemeKupovine;
	
	public Karta(String oznakaVlaka, String pocetnaStanica, String odredisnaStanica, LocalTime vrijemeKretanja,
			LocalTime vrijemeDolaska, double izvornaCijena, double popustApp, double popustVikend, double povecanje,
			double konacnaCijena, String nacinKupovine, Date datumKupovine, LocalTime vrijemeKupovine) {
		super();
		this.oznakaVlaka = oznakaVlaka;
		this.pocetnaStanica = pocetnaStanica;
		this.odredisnaStanica = odredisnaStanica;
		this.vrijemeKretanja = vrijemeKretanja;
		this.vrijemeDolaska = vrijemeDolaska;
		this.izvornaCijena = izvornaCijena;
		this.popustApp = popustApp;
		this.popustVikend = popustVikend;
		this.povecanje = povecanje;
		this.konacnaCijena = konacnaCijena;
		this.nacinKupovine = nacinKupovine;
		this.datumKupovine = datumKupovine;
		this.vrijemeKupovine = vrijemeKupovine;
	}
}
