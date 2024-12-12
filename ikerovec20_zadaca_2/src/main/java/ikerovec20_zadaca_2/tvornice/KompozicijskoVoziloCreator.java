package ikerovec20_zadaca_2.tvornice;


import ikerovec20_zadaca_2.podaci.KompozicijskoVozilo;
import ikerovec20_zadaca_2.podaci.Vozilo;

public class KompozicijskoVoziloCreator extends VoziloCreator {
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
		float maksSnaga = Float.parseFloat(parametri[8].replace(",", "."));
		
		return new KompozicijskoVozilo(oznaka, opis, proizvodac, godina, vrstaPogona, namjena, maksBrzina, status, maksSnaga);
	}

}
