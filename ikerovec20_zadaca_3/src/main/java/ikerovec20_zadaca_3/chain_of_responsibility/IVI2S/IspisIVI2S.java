package ikerovec20_zadaca_3.chain_of_responsibility.IVI2S;

import java.util.ArrayList;

import ikerovec20_zadaca_3.composite.IKomponentaVoznogReda;

public abstract class IspisIVI2S implements IIVI2S {

	protected int index;
	protected ArrayList<IKomponentaVoznogReda> komponente;
	protected IspisIVI2S sljedeciStupac;
	protected char oznaka;
	
	public IspisIVI2S(ArrayList<IKomponentaVoznogReda> komponente) {
		this.komponente = komponente;
		index = -1;
	}
	
	public void postaviSljedeceg(IspisIVI2S stupac) {
		this.sljedeciStupac = stupac;
	}
	
	@Override
	public void ispisiRed(char oznaka) {
		if (oznaka == this.oznaka) {
			ispisi();	
		}
		if (sljedeciStupac != null) {
			sljedeciStupac.ispisiRed(oznaka);
		}
	}

	protected abstract void ispisi();
	
}
