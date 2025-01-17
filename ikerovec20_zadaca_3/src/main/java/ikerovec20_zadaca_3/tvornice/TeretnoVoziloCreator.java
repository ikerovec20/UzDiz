package ikerovec20_zadaca_3.tvornice;

import ikerovec20_zadaca_3.podaci.TeretniVagon;
import ikerovec20_zadaca_3.podaci.Vozilo;

public class TeretnoVoziloCreator extends VoziloCreator {

	@Override
	public Vozilo kreirajVozilo(String[] parametri) {
		var oznaka = parametri[0];
		var opis = parametri[1];
		var proizvodac = parametri[2];
		var godina = parametri[3];
		var namjena = parametri[4];
		var vrstaPogona = parametri[6];
		var maksBrzina = Integer.parseInt(parametri[7]);
		var status = parametri[17];
		float nosivost = Float.parseFloat(parametri[14].replace(",", "."));
		float povrsina = Float.parseFloat(parametri[15].replace(",", "."));
		return new TeretniVagon(oznaka, opis, proizvodac, godina, vrstaPogona, namjena, maksBrzina, status, nosivost, povrsina);
	}

}
