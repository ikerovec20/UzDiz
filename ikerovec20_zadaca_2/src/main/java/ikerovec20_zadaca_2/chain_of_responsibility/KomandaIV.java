package ikerovec20_zadaca_2.chain_of_responsibility;

import java.util.regex.Matcher;

import ikerovec20_zadaca_2.App.TvrtkaSingleton;

public class KomandaIV extends Komanda {

	public KomandaIV(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		TvrtkaSingleton.getInstance().ispisiTablicuVlakova();
	}

}
