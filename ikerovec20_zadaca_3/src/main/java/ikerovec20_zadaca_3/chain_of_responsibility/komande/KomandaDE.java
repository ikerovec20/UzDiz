package ikerovec20_zadaca_3.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;

public class KomandaDE extends Komanda {

	public KomandaDE(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		String oznakaVlaka = parametri.group("oznakaVlaka");
		String vrstaVlaka = parametri.group("vrstaVlaka");
		String pocetnaStanica = parametri.group("pocetnaStanica");
		String odredisnaStanica = parametri.group("odredisnaStanica");
		String pruga = parametri.group("pruga");
		String smjer = parametri.group("smjer");
		String vrijemePolaska = parametri.group("vrijemePolaska");
		String trajanje = parametri.group("trajanje");
		String dani = parametri.group("dani");

		TvrtkaSingleton.getInstance().dodajEtapuKomanda(oznakaVlaka, vrstaVlaka, pocetnaStanica, odredisnaStanica, smjer, pruga, vrijemePolaska, trajanje, dani);
	}

}
