package ikerovec20_zadaca_2.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_2.App.TvrtkaSingleton;

public class KomandaIEV extends Komanda {

	public KomandaIEV(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		String oznaka = parametri.group("oznaka");
		
		TvrtkaSingleton.getInstance().ispisiVlak(oznaka);
	}

}
