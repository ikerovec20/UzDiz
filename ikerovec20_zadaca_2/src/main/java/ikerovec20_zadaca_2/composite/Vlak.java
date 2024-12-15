package ikerovec20_zadaca_2.composite;

import java.util.ArrayList;

public class Vlak implements IKomponentaVoznogReda {
	
	private ArrayList<Etapa> etape = new ArrayList<Etapa>();
	
	public void dodajEtapu(Etapa etapa) {
		etape.add(etapa);
	}
	
	public void ukloniEtapu(Etapa etapa) {
		etape.remove(etapa);
	}
}
