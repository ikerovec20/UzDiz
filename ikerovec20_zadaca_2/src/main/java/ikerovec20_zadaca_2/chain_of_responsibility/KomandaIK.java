package ikerovec20_zadaca_2.chain_of_responsibility;

import java.util.regex.Matcher;

import ikerovec20_zadaca_2.App.TvrtkaSingleton;

public class KomandaIK extends Komanda {

	public KomandaIK(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		String oznaka = parametri.group("oznaka");
		TvrtkaSingleton.getInstance().ispisiKompoziciju(oznaka);
	}

}
