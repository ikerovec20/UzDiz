package ikerovec20_zadaca_1.builderi;

import ikerovec20_zadaca_1.podaci.Kompozicija;
import ikerovec20_zadaca_1.podaci.Vozilo;

public class KompozicijaBuilder implements IKompozicijaBuilder {
	private Kompozicija kompozicija = new Kompozicija();
	
	public KompozicijaBuilder postaviOznaku(String oznaka) {
		kompozicija.setOznaka(oznaka);
		return this;
	}
	
	public KompozicijaBuilder dodajVlak(Vozilo vlak, String uloga) {
		kompozicija.prijevoznaSredstva.put(vlak, uloga);
		return this;
	}
	
	public Kompozicija konstruirajKompoziciju() {
		return kompozicija;
	}
}
