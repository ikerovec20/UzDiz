package ikerovec20_zadaca_3.podaci;

public class Veza {
	
	public Stanica stanica;
	public KomponentaPruge pruga;
	public String oznakaPruge;
	public boolean normalniSmjer = true;

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
	
	public Veza(Stanica stanica, KomponentaPruge pruga, boolean smjer, String oznaka) {
		this.stanica = stanica;
		this.pruga = pruga;
		this.normalniSmjer = smjer;
		this.oznakaPruge = oznaka;
	}
	
	public Veza(Stanica stanica) {
		this.stanica = stanica;
	}
}
