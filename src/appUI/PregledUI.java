package appUI;

import java.util.List;

import dao.PregledDAO;
import model.Doktor;
import model.Pacijent;
import pomocnaKlasa.PomocnaKlasa;

public class PregledUI {
	
//MENU
	public static void ispisiGlavniMeni () {
				
		System.out.println("Rad sa pregledima - opcije:");
		System.out.println("\t1. Ispisi pacijente koji idu kod doktora");
		System.out.println("\t2. Ispisi doktore koji cekaju pacijenta");
		System.out.println("\t3. Dodavanje pacijenta doktoru");
		System.out.println("\t4. Uklanjanje pacijenta doktoru");
		System.out.println("=======================================================");
		System.out.println("\t0. IZLAZ");
	}
	
	public static void meniPreglediUI() {
		int odluka = -1;
		while (odluka != 0) {
			ispisiGlavniMeni();
			System.out.println("=======================================================");
			System.out.print("Izaberite opciju: ");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {
			case 0:
				System.out.println("Izlaz");
				break;
			case 1:
				ispisiPacijenteZaDoktora();
				break;
			case 2:
				ispisiDoktoreZaPacijente();
				break;
			case 3:
				dodajDoktoraNaPacijenta();
				break;
			case 4:
				ukloniDoktoraSaPacijenta();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}
	//
//========== ISPISI PACIJENTE KOJE DOKTOR TREBA DA PREGLEDA ===================
/*
	cilj ove metode je da od korisnika trazimo ID doktora
	i da na konzoili ispise sve pacijente koje treba da prelgeda doktor sa 
	prosledjenim ID-jem
*/	
	public static void ispisiPacijenteZaDoktora() {
//	prvo pronadjemo doktora za kojeg zelimo da ispisemo sve pacijente
		Doktor doktor = DoktorUI.findDoctor();
		
		if (doktor != null) {
			try {
				List<Pacijent> sviPacijenti = PregledDAO.getPacijentiByDoktorID(doktor.getId());
				
				System.out.println();
				System.out.printf("%-10s %-20s %-20s %-20s %-20s %-30s", "ID", "Ime", "Prezime", "JMBG", "Simptom", "Medicinska oblast bolesti");
				System.out.println();
				System.out.println("========== ==================== ==================== ==================== ==================== =========================");
				for (int i = 0; i < sviPacijenti.size(); i++) {
					System.out.printf("%-10s %-20s %-20s %-20s %-20s %-30s",
							sviPacijenti.get(i).getId(),
							sviPacijenti.get(i).getIme(),
							sviPacijenti.get(i).getPrezime(),
							sviPacijenti.get(i).getJmbg(),
							sviPacijenti.get(i).getSimptom(),
							sviPacijenti.get(i).getMob());
					System.out.println();
				}
				System.out.println("---------- -------------------- -------------------- -------------------- -------------------- -------------------------");
				
			}catch (Exception ex) {
				System.out.println("Greska u radu sa bazom!");
				ex.printStackTrace();
			}
		}
	}
//=============================================================================
	
//========== ISPISI DOKTORE KOJI CEKAJU PACIJENTA NA PREGLED ==================	
/*
	cilj ove metode je da od korisnika trazimo ID pacijenta
	i da na konzoili ispise sve doktore koje treba da prelgedaju tog pacijenta sa
	prosledjenim ID-jem
*/	
	public static void ispisiDoktoreZaPacijente () {
		
		Pacijent pacijent = PacijentUI.findPatient();
		if (pacijent != null) {
			try {
				List<Doktor> sviDoktori = PregledDAO.getDoktorByPacijentID(pacijent.getId());
				System.out.println();
				System.out.printf("%-10s %-10s %-20s %-20s", "ID", "Ime", "Prezime", "Zvanje");
				System.out.println();
				System.out.println("========== ========== ==================== ====================");
				
				for (int i = 0; i < sviDoktori.size(); i++) {
					System.out.printf("%-10s %-10s %-20s %-20s", 
							sviDoktori.get(i).getId(), 
							sviDoktori.get(i).getIme(), 
							sviDoktori.get(i).getPrezime(), 
							sviDoktori.get(i).getZvanje());
					System.out.println();
				}
				System.out.println("---------- ---------- -------------------- --------------------");
			}catch (Exception ex) {
				System.out.println("Greska u radu sa bazom!");
				ex.printStackTrace();
			}
		}
	}
//=============================================================================
	
//======================= DODAJ DOKTORA PACIJENTU =============================	

	public static void dodajDoktoraNaPacijenta() {
		
		Pacijent pacijent = PacijentUI.findPatient();
		Doktor doktor = DoktorUI.findDoctor();
		
		if (doktor != null & pacijent != null) {
			try {
//				uspostavljanje veze izmedju doktra i pacijenta
				PregledDAO.add(pacijent.getId(), doktor.getId());
			}catch (Exception ex) {
				System.out.println("Greska u radu sa bazom!");
				ex.printStackTrace();
			}
		}
	}
//=============================================================================
	
//======================= UKLONI DOKTORA PACIJENTU ============================
	
	public static void ukloniDoktoraSaPacijenta() {
		
		Pacijent pacijent = PacijentUI.findPatient();
		Doktor doktor = DoktorUI.findDoctor();
		
		if (pacijent != null && doktor != null) {
			try {
//				uspostavljanje veze izmedju doktora i pacijenta
				PregledDAO.delete(pacijent.getId(), doktor.getId());
			}catch (Exception ex) {
				System.out.println("Greska u radu sa bazom!");
				ex.printStackTrace();
			}
		}
	}
}
