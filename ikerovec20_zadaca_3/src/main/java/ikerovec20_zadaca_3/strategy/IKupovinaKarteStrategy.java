package ikerovec20_zadaca_3.strategy;

import java.util.ArrayList;

import ikerovec20_zadaca_3.composite.Vlak;

public interface IKupovinaKarteStrategy {
	public double izracunajOsnovnuCijenu(int km, Vlak vlak);
	public double izracunajKonacnuCijenu(int km, Vlak vlak, boolean vikend);
	public ArrayList<Double> dohvatiPopuste(boolean vikend);
}
