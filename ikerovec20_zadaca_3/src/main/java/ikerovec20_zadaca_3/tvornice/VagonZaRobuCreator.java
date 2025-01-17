package ikerovec20_zadaca_3.tvornice;

import ikerovec20_zadaca_3.podaci.VagonZaRobu;
import ikerovec20_zadaca_3.podaci.Vozilo;

public class VagonZaRobuCreator extends VoziloCreator {

	@Override
	public Vozilo kreirajVozilo(String[] parametri) {
		String oznaka = parametri[0];
		String opis = parametri[1];
		String proizvodac = parametri[2];
		String godina = parametri[3];
		String namjena = parametri[4];
		String vrstaPogona = parametri[6];
		int maksBrzina = Integer.parseInt(parametri[7]);
		String status = parametri[17];
		float nosivost = Float.parseFloat(parametri[14].replace(",", "."));
		float povrsina = Float.parseFloat(parametri[15].replace(",", "."));
		float zapremina = Float.parseFloat(parametri[16].replace(",", "."));
		return new VagonZaRobu(oznaka, opis, proizvodac, godina, vrstaPogona, namjena, maksBrzina, status, nosivost, povrsina, zapremina);
	}

}
