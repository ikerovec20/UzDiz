package ikerovec20_zadaca_3.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;

public class KomandaFV extends Komanda {

	public KomandaFV(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		String vrijeme = parametri.group("vrijeme");
		boolean prije = parametri.group("prije").matches("PRIJE");
		
		TvrtkaSingleton.getInstance().dodajVrijemeFilt(vrijeme, prije);
	}

}
