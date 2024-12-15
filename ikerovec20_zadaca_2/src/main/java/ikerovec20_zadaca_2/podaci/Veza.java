package ikerovec20_zadaca_2.podaci;

public class Veza {
	
	public Stanica stanica;
	public KomponentaPruge pruga;

	public int vrijemeNormalniVlak = -1;
	public int vrijemeBrziVlak = -1;
	public int vrijemeUbrzaniVlak = -1;
	
	public void setVrijemeNormalniVlak(int vrijemeNormalniVlak) {
		this.vrijemeNormalniVlak = vrijemeNormalniVlak;
	}

	public void setVrijemeBrziVlak(int vrijemeBrziVlak) {
		this.vrijemeBrziVlak = vrijemeBrziVlak;
	}

	public void setVrijemeUbrzaniVlak(int vrijemeUbrzaniVlak) {
		this.vrijemeUbrzaniVlak = vrijemeUbrzaniVlak;
	}
	
	public Veza(Stanica stanica, KomponentaPruge pruga) {
		this.stanica = stanica;
		this.pruga = pruga;
	}
	
	public Veza(Stanica stanica) {
		this.stanica = stanica;
	}
}
