package ikerovec20_zadaca_3.command;

import java.util.ArrayList;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;
import ikerovec20_zadaca_3.composite.Etapa;
import ikerovec20_zadaca_3.composite.Vlak;
import ikerovec20_zadaca_3.composite.VozniRedComposite;

public class KopirajEtapeVlakaKomanda extends VozniRedKomanda {
	
	private String oznakaVlaka1;
	private String oznakaVlaka2;
	
	private ArrayList<Etapa> dodaneEtape = new ArrayList<Etapa>();
	
	public KopirajEtapeVlakaKomanda(VozniRedComposite primatelj, Vlak vlak, String oznakaVlaka1, String oznakaVlaka2) {
		super(primatelj);
		this.komponenta = vlak;
		this.oznakaVlaka1 = oznakaVlaka1;
		this.oznakaVlaka2 = oznakaVlaka2;
	}

	@Override
	public void izvrsi() {
		if (primatelj == null) {
			primatelj = TvrtkaSingleton.getInstance().getVozniRed().dohvatiVlak(oznakaVlaka1);
			if (primatelj == null) {
				System.out.println("Nije moguce izvrsiti komandu.");
				return;
			}
		}
		
		if (komponenta == null) {
			komponenta = TvrtkaSingleton.getInstance().getVozniRed().dohvatiVlak(oznakaVlaka2);
			if (komponenta == null) {
				System.out.println("Nije moguce izvrsiti komandu.");
				return;
			}
		}
		
		var vlak = (Vlak) this.komponenta;
		var iterator = vlak.dohvatiIterator();
		while (iterator.postojiSljedecaKomponenta()) {
			var etapa = (Etapa) iterator.dohvatiSljedecuKomponentu();
			var nova = etapa.kloniraj();
			var prim = (Vlak) primatelj;
			nova.vrstaEtape = prim.vrstaVlaka;
			primatelj.dodajKomponentu(nova);
			dodaneEtape.add(nova);
		}
	}

	@Override
	public void vrati() {
		if (primatelj == null) {
			return;
		}
		
		for (var etapa : dodaneEtape) {
			primatelj.ukloniKomponentu(etapa);
		}
	}

}
