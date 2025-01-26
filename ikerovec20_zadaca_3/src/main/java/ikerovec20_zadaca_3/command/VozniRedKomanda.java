package ikerovec20_zadaca_3.command;

import ikerovec20_zadaca_3.composite.IKomponentaVoznogReda;
import ikerovec20_zadaca_3.composite.VozniRedComposite;

public abstract class VozniRedKomanda {
	
	protected IKomponentaVoznogReda komponenta;
	protected VozniRedComposite primatelj;
	
	public VozniRedKomanda(VozniRedComposite primatelj) {
		this.primatelj = primatelj;
	}
	
	public abstract void izvrsi();
	public abstract void vrati();
}
