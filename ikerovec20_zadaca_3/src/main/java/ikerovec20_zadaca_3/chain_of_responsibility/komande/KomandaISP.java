package ikerovec20_zadaca_3.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;

public class KomandaISP extends Komanda {

	public KomandaISP(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		String oznaka = parametri.group("oznaka");
		String redoslijed = parametri.group("redoslijed");
		TvrtkaSingleton.getInstance().ispisiPrugu(oznaka, redoslijed.matches("N"));
	}

}
