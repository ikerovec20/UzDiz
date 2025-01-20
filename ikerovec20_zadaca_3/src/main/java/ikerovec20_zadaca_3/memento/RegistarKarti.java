package ikerovec20_zadaca_3.memento;

import java.util.ArrayList;
import java.util.List;

public class RegistarKarti {
	private List<KartaMemento> kupljeneKarte = new ArrayList<KartaMemento>();
	
	public KartaMemento dohvatiMemento(int index) {
		if (index > kupljeneKarte.size() || index <= 0) {
			return null;
		}
		
		return kupljeneKarte.get(index - 1);
	}
	
	public boolean spremiMemento(KartaMemento memento) {
		kupljeneKarte.add(memento);
		return true;
	}
	
	public int dohvatiBrojStanja() {
		return kupljeneKarte.size();
	}
}
