package ikerovec20_zadaca_2.composite;

import java.time.LocalTime;

import ikerovec20_zadaca_2.podaci.Stanica;

public interface IKomponentaVoznogReda {
	public int vratiKm();
	public boolean validiraj();
	public Stanica vratiPrvuStanicu();
	public Stanica vratiZadnjuStanicu();
	public LocalTime vratiPocetnoVrijeme();
	public LocalTime vratiZavrsnoVrijeme();
}
