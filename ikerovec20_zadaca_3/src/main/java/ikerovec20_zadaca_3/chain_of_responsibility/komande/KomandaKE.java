package ikerovec20_zadaca_3.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;

public class KomandaKE extends Komanda {

	public KomandaKE(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		String vlak1 = parametri.group("primatelj");
		String vlak2 = parametri.group("kopiraj");
		
		TvrtkaSingleton.getInstance().kopirajEtapeVlaka(vlak1, vlak2);
	}

}
