package ikerovec20_zadaca_3.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;

public class KomandaDV extends Komanda {

	public KomandaDV(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		String oznaka = parametri.group("oznaka");
		String vrsta = parametri.group("vrsta");
		
		TvrtkaSingleton.getInstance().dodajVlakKomanda(oznaka, vrsta);
	}

}
