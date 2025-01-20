package ikerovec20_zadaca_3.memento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class KartaMemento {
	
	private String oznakaVlaka;
	private String pocetnaStanica;
	private String odredisnaStanica;
	private LocalTime vrijemeKretanja;
	private LocalTime vrijemeDolaska;
	private double izvornaCijena;
	private double popustApp = 0;
	private double popustVikend = 0;
	private double povecanje = 0;
	private double konacnaCijena;
	private String nacinKupovine;
	private LocalDate datumKupovine;
	private LocalTime vrijemeKupovine;
	
	public KartaMemento(String oznakaVlaka, String pocetnaStanica, String odredisnaStanica, LocalTime vrijemeKretanja,
			LocalTime vrijemeDolaska, double izvornaCijena, double popustApp, double popustVikend, double povecanje,
			double konacnaCijena, String nacinKupovine, LocalDate datumKupovine, LocalTime vrijemeKupovine) {
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

	public String getOznakaVlaka() {
		return oznakaVlaka;
	}

	public void setOznakaVlaka(String oznakaVlaka) {
		this.oznakaVlaka = oznakaVlaka;
	}

	public String getPocetnaStanica() {
		return pocetnaStanica;
	}

	public void setPocetnaStanica(String pocetnaStanica) {
		this.pocetnaStanica = pocetnaStanica;
	}

	public String getOdredisnaStanica() {
		return odredisnaStanica;
	}

	public void setOdredisnaStanica(String odredisnaStanica) {
		this.odredisnaStanica = odredisnaStanica;
	}

	public LocalTime getVrijemeKretanja() {
		return vrijemeKretanja;
	}

	public void setVrijemeKretanja(LocalTime vrijemeKretanja) {
		this.vrijemeKretanja = vrijemeKretanja;
	}

	public LocalTime getVrijemeDolaska() {
		return vrijemeDolaska;
	}

	public void setVrijemeDolaska(LocalTime vrijemeDolaska) {
		this.vrijemeDolaska = vrijemeDolaska;
	}

	public double getIzvornaCijena() {
		return izvornaCijena;
	}

	public void setIzvornaCijena(double izvornaCijena) {
		this.izvornaCijena = izvornaCijena;
	}

	public double getPopustApp() {
		return popustApp;
	}

	public void setPopustApp(double popustApp) {
		this.popustApp = popustApp;
	}

	public double getPopustVikend() {
		return popustVikend;
	}

	public void setPopustVikend(double popustVikend) {
		this.popustVikend = popustVikend;
	}

	public double getPovecanje() {
		return povecanje;
	}

	public void setPovecanje(double povecanje) {
		this.povecanje = povecanje;
	}

	public double getKonacnaCijena() {
		return konacnaCijena;
	}

	public void setKonacnaCijena(double konacnaCijena) {
		this.konacnaCijena = konacnaCijena;
	}

	public String getNacinKupovine() {
		return nacinKupovine;
	}

	public void setNacinKupovine(String nacinKupovine) {
		this.nacinKupovine = nacinKupovine;
	}

	public LocalDate getDatumKupovine() {
		return datumKupovine;
	}

	public void setDatumKupovine(LocalDate datumKupovine) {
		this.datumKupovine = datumKupovine;
	}

	public LocalTime getVrijemeKupovine() {
		return vrijemeKupovine;
	}

	public void setVrijemeKupovine(LocalTime vrijemeKupovine) {
		this.vrijemeKupovine = vrijemeKupovine;
	}
}
