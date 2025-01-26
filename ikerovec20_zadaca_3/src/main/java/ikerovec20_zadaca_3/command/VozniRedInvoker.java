package ikerovec20_zadaca_3.command;

import java.util.ArrayList;
import java.util.Stack;

public class VozniRedInvoker {
	private Stack<VozniRedKomanda> povijest = new Stack<VozniRedKomanda>();
	private ArrayList<VozniRedKomanda> redKomandi = new ArrayList<VozniRedKomanda>();
	
	public void dodajKomandu(VozniRedKomanda komanda) {
		redKomandi.add(komanda);
	}
	
	public void izvrsiKomande() {
		for (int i = 0; i < redKomandi.size(); i++) {
			var komanda = redKomandi.get(i);
			if (komanda instanceof DodajVlakKomanda) {
				komanda.izvrsi();
				povijest.add(komanda);
				redKomandi.remove(komanda);
			}
		}
		
		for (int i = 0; i < redKomandi.size(); i++) {
			var komanda = redKomandi.get(i);
				komanda.izvrsi();
				povijest.add(komanda);
				redKomandi.remove(komanda);
		}
	}
	
	public void vratiZadnjuKomandu() {
		if (!povijest.isEmpty()) {
			var komanda = povijest.pop();
			komanda.vrati();
		}
	}
	
	public void vratiSveKomande() {
		while (!povijest.isEmpty()) {
			var komanda = povijest.pop();
			komanda.vrati();
		}
	}
}
