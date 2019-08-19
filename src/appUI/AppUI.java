package appUI;

import dao.ConnectionManager;
import pomocnaKlasa.PomocnaKlasa;

public class AppUI {

	public static void main(String[] args) {

		try {
//			otvaranje konekcije sa bazom
			ConnectionManager.open();
		}catch (Exception ex) {
			System.out.println("Neuspesna konekcija na bazu!");
			
			ex.printStackTrace();
//			u slucaju neuspesne konecije gasi aplikaciju
			return;
		}
		
		int odluka = -1;
		while (odluka != 0) {
			AppUI.ispisiTekstOsnovneOpcie();
			System.out.println("=============================");
			System.out.print("Izaberite opciju: ");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {
			case 0:
				System.out.println("Izlaz iz programa");
				break;
			case 1:
				PacijentUI.meniPacijentUI();
				break;
			case 2:
				DoktorUI.meniDoktorUI();
				break;
			case 3:
				PregledUI.meniPreglediUI();
				break;

			default:
				break;
			}
		}
		
		try {
			ConnectionManager.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static void ispisiTekstOsnovneOpcie () {
		System.out.println("Dom Zdravlja - Osnovne opcije");
		System.out.println("\t1. Rad sa pacijentima");
		System.out.println("\t2. Rad sa doktorima");
		System.out.println("\t3. Rad sa pregledima");
		System.out.println("=============================");
		System.out.println("\t0. IZLAZ");
	}

}
