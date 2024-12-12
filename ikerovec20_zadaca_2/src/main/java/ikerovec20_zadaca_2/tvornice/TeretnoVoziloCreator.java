package ikerovec20_zadaca_2.tvornice;

import ikerovec20_zadaca_2.podaci.TeretniVagon;
import ikerovec20_zadaca_2.podaci.VagonZaAutomobile;
import ikerovec20_zadaca_2.podaci.VagonZaRobu;
import ikerovec20_zadaca_2.podaci.Vozilo;

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
		
		var vrstaPrijevoza = parametri[5];
		switch(vrstaPrijevoza) {
		case "TK":
			return new TeretniVagon(oznaka, opis, proizvodac, godina, vrstaPogona, namjena, maksBrzina, status, nosivost, povrsina);	
		case "TA":
			var brojAutomobila = Integer.parseInt(parametri[13]);
			return new VagonZaAutomobile(oznaka, opis, proizvodac, godina, vrstaPogona, namjena, maksBrzina, status, nosivost, povrsina, brojAutomobila);
		case "TRS":
		case "TPS":
		case "TTS":
			float zapremina = Float.parseFloat(parametri[16].replace(",", "."));
			return new VagonZaRobu(oznaka, opis, proizvodac, godina, vrstaPogona, namjena, maksBrzina, status, nosivost, povrsina, zapremina);
		default: 
			System.out.println("Greska kod kreiranja vagona");
			return null;
		}
	}

}
