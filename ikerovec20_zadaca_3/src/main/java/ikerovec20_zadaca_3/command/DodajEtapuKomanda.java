package ikerovec20_zadaca_3.command;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;
import ikerovec20_zadaca_3.composite.Etapa;
import ikerovec20_zadaca_3.composite.Vlak;
import ikerovec20_zadaca_3.composite.VozniRedComposite;

public class DodajEtapuKomanda extends VozniRedKomanda {

	private String oznakaVlaka;
	
	public DodajEtapuKomanda(VozniRedComposite primatelj, Etapa etapa, String oznakaVlaka, String vrstaVlaka) {
		super(primatelj);
		this.komponenta = etapa;
		this.oznakaVlaka = oznakaVlaka;
	}

	@Override
	public void izvrsi() {
		if (primatelj == null) {
			primatelj = TvrtkaSingleton.getInstance().getVozniRed().dohvatiVlak(oznakaVlaka);
			if (primatelj == null) {
				System.out.println("Nije moguce izvrsiti komandu.");
				return;
			}
		}
		Etapa etapa = (Etapa) komponenta;
		if (etapa.vrstaEtape == null) {
			Vlak v = (Vlak) primatelj;
			etapa.vrstaEtape = v.vrstaVlaka;
		}
		primatelj.dodajKomponentu(etapa);
	}

	@Override
	public void vrati() {
		if (primatelj == null) {
			return;
		}
		primatelj.ukloniKomponentu(komponenta);
	}

}
