package ikerovec20_zadaca_2.composite;

public class VozniRed extends VozniRedComposite {
	public Vlak dohvatiVlak(String oznaka) {
		for (var komp : komponente) {
			Vlak vlak = (Vlak) komp;
			if (vlak.oznaka.matches(oznaka)) {
				return vlak;
			}
		}
		return null;
	}

	@Override
	public void ucitajStanice() {
		for (var vlak : komponente) {
			vlak.ucitajStanice();
		}
		
	}

	@Override
	public int vratiUkupnoKm() {
		int ukupnoKm = 0;
		for (var vlak : komponente) {
			ukupnoKm += vlak.vratiUkupnoKm();
		}
		return ukupnoKm;
 	}
	
	
}
