package ikerovec20_zadaca_3.podaci;

import java.time.LocalDate;

public class Raspored {
	private String[] dani = new String[] {"Po", "U", "Sr", "Č", "Pe", "Su", "N"};
	private boolean[] voziNaDan = new boolean[7];
	
	public Raspored(String dani) {
		for (int i = 0; i < 7; i++) {
			voziNaDan[i] = false;
		}
		
		for (int i = 0; i < 7; i++) {
			if (dani.contains(this.dani[i])) {
				voziNaDan[i] = true;
			}
		}
	}
	
	public String vratiDane() {
		var builder = new StringBuilder();
		for (int i = 0; i < 7; i++) {
			if (voziNaDan[i]) {
				builder.append(this.dani[i]);
			}
		}
		return builder.toString();
	}
	
	public boolean provjeriDane(String dani) {
		boolean vozi = true;
		for (int i = 0; i < 7; i++) {
			if (dani.contains(this.dani[i]) && !voziNaDan[i]) {
				vozi = false;
			}
		}
		return vozi;
	}
	
	public boolean provjeriDatum(LocalDate datum) {
		String dan = "";
		switch (datum.getDayOfWeek()) {
		case MONDAY:
			dan = "Po";
			break;
		case TUESDAY:
			dan = "U";
			break;
		case WEDNESDAY:
			dan = "Sr";
			break;
		case THURSDAY:
			dan = "Č";
			break;
		case FRIDAY:
			dan = "Pe";
			break;
		case SATURDAY:
			dan = "Su";
			break;
		case SUNDAY:
			dan = "N";
			break;
		}
		return provjeriDane(dan);
	}
	
	public void prilagodiRaspored(String dani) {
		for (int i = 0; i < 7; i++) {
			if (dani.contains(this.dani[i])) {
				voziNaDan[i] = true;
			}
		}
	}
}
