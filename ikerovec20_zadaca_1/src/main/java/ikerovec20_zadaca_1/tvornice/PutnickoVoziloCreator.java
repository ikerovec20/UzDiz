package ikerovec20_zadaca_1.tvornice;

import ikerovec20_zadaca_1.podaci.PutnickoVozilo;
import ikerovec20_zadaca_1.podaci.Vozilo;

public class PutnickoVoziloCreator extends VoziloCreator {

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
		var brojSjedecihMjesta = Integer.parseInt(parametri[9]);
		var brojStajacihMjesta = Integer.parseInt(parametri[10]);
		var brojBicikala = Integer.parseInt(parametri[11]);
		var brojKreveta = Integer.parseInt(parametri[12]);
		return new PutnickoVozilo(oznaka, opis, proizvodac, godina, vrstaPogona, namjena, maksBrzina, status, brojSjedecihMjesta, brojStajacihMjesta, brojBicikala, brojKreveta);
	}

}
