package ikerovec20_zadaca_1.builderi;

import ikerovec20_zadaca_1.podaci.Kompozicija;
import ikerovec20_zadaca_1.podaci.Vozilo;

public interface IKompozicijaBuilder {
	public KompozicijaBuilder postaviOznaku(String oznaka);
	public KompozicijaBuilder dodajVlak(Vozilo vlak, String uloga);
	public Kompozicija konstruirajKompoziciju();
}
