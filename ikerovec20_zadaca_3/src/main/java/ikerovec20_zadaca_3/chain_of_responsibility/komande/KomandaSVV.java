package ikerovec20_zadaca_3.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;

public class KomandaSVV extends Komanda {

	public KomandaSVV(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		String oznaka = parametri.group("oznaka");
		String dan = parametri.group("dan");
		String koeficijent = parametri.group("koeficijent");
		
		int koef = Integer.parseInt(koeficijent);
		TvrtkaSingleton.getInstance().simulacijaVlaka(oznaka, dan, koef);
	}

}
