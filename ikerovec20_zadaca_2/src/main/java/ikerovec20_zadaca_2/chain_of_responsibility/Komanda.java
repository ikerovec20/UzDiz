package ikerovec20_zadaca_2.chain_of_responsibility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Komanda implements IKomanda {

	protected Komanda sljedecaKomanda;
	protected Pattern predlozak;
	
	public Komanda(String predlozak) {
		this.predlozak = Pattern.compile(predlozak);
	}
	
	@Override
	public boolean obradiKomandu(String parametri) {
		Matcher matcher = predlozak.matcher(parametri);
		if (matcher.matches()) {
			obradi(matcher);
			return true;
		}
		else if (sljedecaKomanda != null) {
			return sljedecaKomanda.obradiKomandu(parametri);
		}
		else {
			return false;
		}
	}

	protected abstract void obradi(Matcher parametri);
	
	public void postaviSljedeceg(Komanda komanda) {
		sljedecaKomanda = komanda;
	}
	
	public Komanda dohvatiSljedeceg() {
		return sljedecaKomanda;
	}
}
