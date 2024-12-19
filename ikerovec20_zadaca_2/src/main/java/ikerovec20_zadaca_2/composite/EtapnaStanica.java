package ikerovec20_zadaca_2.composite;

import java.time.LocalTime;

import ikerovec20_zadaca_2.podaci.Stanica;

public class EtapnaStanica implements IKomponentaVoznogReda {

	public Stanica stanica;
	public int km;
	public LocalTime vrijemeDolaska;
	public String oznakaPruge;
	
	public int vrijemeNormalniVlak = -1;
	public int vrijemeBrziVlak = -1;
	public int vrijemeUbrzaniVlak = -1;
	
	public EtapnaStanica(Stanica stanica, int km, LocalTime vrijemeDolaska, int vrijemeNormalniVlak, int vrijemeBrziVlak, int vrijemeUbrzaniVlak, String oznakaPruge) {
		this.stanica = stanica;
		this.km = km;
		this.vrijemeDolaska = vrijemeDolaska;
		this.vrijemeNormalniVlak = vrijemeNormalniVlak;
		this.vrijemeBrziVlak = vrijemeBrziVlak;
		this.vrijemeUbrzaniVlak = vrijemeUbrzaniVlak;
		this.oznakaPruge = oznakaPruge;
	}
	

	@Override
	public int vratiKm() {
		return km;
	}

	@Override
	public boolean validiraj() {
		if (vrijemeNormalniVlak == -1 && vrijemeBrziVlak == -1 && vrijemeUbrzaniVlak == -1) {
			return false;
		}
		return true;
	}

	@Override
	public Stanica vratiPrvuStanicu() {
		return this.stanica;
	}

	@Override
	public Stanica vratiZadnjuStanicu() {
		return this.stanica;
	}

	@Override
	public LocalTime vratiPocetnoVrijeme() {
		return vrijemeDolaska;
	}

	@Override
	public LocalTime vratiZavrsnoVrijeme() {
		return vrijemeDolaska;
	}


	@Override
	public boolean postojiStanica(String stanica) {
		if (this.stanica.stanica.matches(stanica)) {
			return true;
		}
		return false;
	}


	@Override
	public EtapnaStanica dohvatiStanicu(String stanica) {
		if (this.stanica.stanica.matches(stanica)) {
			return this;
		}
		return null;
	}
}