package ikerovec20_zadaca_3.builderi;

import ikerovec20_zadaca_3.podaci.Kompozicija;
import ikerovec20_zadaca_3.podaci.Vozilo;

public interface IKompozicijaBuilder {
	public KompozicijaBuilder postaviOznaku(String oznaka);
	public KompozicijaBuilder dodajVlak(Vozilo vlak, String uloga);
	public Kompozicija konstruirajKompoziciju();
}
