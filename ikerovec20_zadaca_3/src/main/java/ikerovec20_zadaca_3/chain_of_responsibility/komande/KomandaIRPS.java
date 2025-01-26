package ikerovec20_zadaca_3.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;

public class KomandaIRPS extends Komanda {

	public KomandaIRPS(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		String status = parametri.group("status");
		String oznaka = parametri.group("oznaka");
		
		TvrtkaSingleton.getInstance().ispisiPrugeStatus(status, oznaka);
	}

}
