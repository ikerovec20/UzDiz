package ikerovec20_zadaca_3.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;

public class KomandaCVP extends Komanda {

	public KomandaCVP(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		double cijenaNormalni = Double.parseDouble(parametri.group("cijenaNormalni"));
		double cijenaUbrzani = Double.parseDouble(parametri.group("cijenaUbrzani"));
		double cijenaBrzi = Double.parseDouble(parametri.group("cijenaBrzi"));
		double popustSuN = Double.parseDouble(parametri.group("popustSuN"));
		double popustWebMob = Double.parseDouble(parametri.group("popustWebMob"));
		double uvecanjeVlak = Double.parseDouble(parametri.group("uvecanjeVlak"));
		
		TvrtkaSingleton.getInstance().postaviCijeneKarti(cijenaNormalni, cijenaUbrzani, cijenaBrzi, popustSuN, popustWebMob, uvecanjeVlak);
	}

}
