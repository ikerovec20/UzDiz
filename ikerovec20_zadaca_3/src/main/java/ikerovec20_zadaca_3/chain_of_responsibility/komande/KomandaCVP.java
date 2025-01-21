package ikerovec20_zadaca_3.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;

public class KomandaCVP extends Komanda {

	public KomandaCVP(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		
		String cijenaN = parametri.group("cijenaNormalni").replace(',', '.');
		String cijenaU = parametri.group("cijenaUbrzani").replace(',', '.');
		String cijenaB = parametri.group("cijenaBrzi").replace(',', '.');
		String popustS = parametri.group("popustSuN").replace(',', '.');
		String popustW = parametri.group("popustWebMob").replace(',', '.');
		String uvecanje = parametri.group("uvecanjeVlak").replace(',', '.');

		double cijenaNormalni = Double.parseDouble(cijenaN);
		double cijenaUbrzani = Double.parseDouble(cijenaU);
		double cijenaBrzi = Double.parseDouble(cijenaB);
		double popustSuN = Double.parseDouble(popustS);
		double popustWebMob = Double.parseDouble(popustW);
		double uvecanjeVlak = Double.parseDouble(uvecanje);
		
		TvrtkaSingleton.getInstance().postaviCijeneKarti(cijenaNormalni, cijenaUbrzani, cijenaBrzi, popustSuN, popustWebMob, uvecanjeVlak);
	}

}
