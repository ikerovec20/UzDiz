package ikerovec20_zadaca_3.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;

public class KomandaUFS extends Komanda {

	public KomandaUFS(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		TvrtkaSingleton.getInstance().makniSveFilt();
	}

}
