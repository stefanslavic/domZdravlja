package appUI;

import java.util.List;

import dao.DoktorDAO;
import model.Doktor;
import pomocnaKlasa.PomocnaKlasa;

public class DoktorUI {

//MENU
	public static void ispisGlavniMeni() {
			
		System.out.println("Rad sa doktorima - opcije:");
		System.out.println("\t1. Unos podataka o novom doktoru");
		System.out.println("\t2. Izmena podataka o doktoru na osnovu ID");
		System.out.println("\t3. Brisanje podataka o doktoru");
		System.out.println("\t4. Ispisi sve doktore");
		System.out.println("\t5. Ispisi podatke o odredjenom doktoru");
		System.out.println("\t6. Pretraga doktora po zvanju");
		System.out.println("=======================================================");
		System.out.println("\t0. IZLAZ");
	}
	
	
	public static void meniDoktorUI () {
		int odluka = -1;
		while (odluka != 0) {
			ispisGlavniMeni();
			System.out.println("=======================================================");
			System.out.print("Izaberite opciju: ");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {
			case 0:
				System.out.println("Izlaz");
				break;
			case 1:
				unosNovogDoktora();
				break;
			case 2:
				izmenaPodatkaODoktoru();
				break;
			case 3:
				brisanjePodatkaODoktoru();
				break;
			case 4:
				ispisiSveDoktore();
				break;
			case 5:
				Doktor doc = findDoctor();
				if (doc != null) {
					System.out.println(doc.vratiTekstualnuReprezentacijuZaIspis());
				}
				break;
			case 6:
				pretragaPoZvanju();
				break;
			default:
				System.out.println("Nepostojeca komanda!!!");
				break;
			}
		}
	}
//CRUD Operation
	
    //Find doctor by ID	
	public static Doktor findDoctor () {
		System.out.print("Unesi ID doktora: ");
		int idDoc = PomocnaKlasa.ocitajCeoBroj();
		try {
			Doktor doktor = DoktorDAO.getDoctorByID(idDoc);
			if (doktor == null) {
				System.out.println("Doktor sa ID " + idDoc + " ne postoji u evidenciji");
			}
			return doktor;
		}catch (Exception ex) {
			System.out.println("Greska u radu sa bazom");
			ex.printStackTrace();
		}
		return null;
	}
	
	//All doctors
	public static void ispisiSveDoktore () {
		
		try {
			
			List<Doktor> sviDoktori = DoktorDAO.getAll();
			
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
		}
	}
	
	//Create new doctor
	public static void unosNovogDoktora() {
		try {
			
			Doktor doktor = null;
			
			System.out.print("Unesite ime: ");
			String docIme = PomocnaKlasa.ocitajTekst();
			
			System.out.print("Unesite prezime: ");
			String docPrezime = PomocnaKlasa.ocitajTekst();
			
			System.out.print("Unesite zvanje: ");
			String docZvanje = PomocnaKlasa.ocitajTekst();
			
			doktor = new Doktor(0, docIme, docPrezime, docZvanje);
			
			DoktorDAO.add(doktor);
			
			System.out.println();
			System.out.println("\tUspesno ste dodali novog doktora!");
			System.out.println();
			
		}catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");
			ex.printStackTrace();
		}
	}
	
	//Update doctor
	public static void izmenaPodatkaODoktoru () {
		Doktor doktor = findDoctor();
		if (doktor != null)	{
			System.out.print("Unesite novo ime: ");
			String docIme = PomocnaKlasa.ocitajTekst();
			doktor.setIme(docIme);
			
			System.out.print("Unesite novo prezime: ");
			String docPrezime = PomocnaKlasa.ocitajTekst();
			doktor.setPrezime(docPrezime);
			
			System.out.print("Unesite novo zvanje: ");
			String docZvanje = PomocnaKlasa.ocitajTekst();
			doktor.setZvanje(docZvanje);
			
			try {
				DoktorDAO.update(doktor);
				System.out.println();
				System.out.println("\tUspesno ste izmenili doktora!");
				System.out.println();
			}catch (Exception ex) {
				System.out.println("Greska u radu sa bazom!");
				ex.printStackTrace();
			}
		}
	}
	
	//Delete doctor
	public static void brisanjePodatkaODoktoru () {
		Doktor doktor = findDoctor();
		if (doktor != null) {
			try {
				DoktorDAO.delete(doktor.getId());
				
				System.out.println();
				System.out.println("\tUspesno ste izbrisali doktora!");
				System.out.println();
				
			} catch (Exception ex) {
				System.out.println("Greska u radu sa bazom!");
				ex.printStackTrace();
			}
		}
	}
	
	//Search doctor by title
	public static void pretragaPoZvanju() {
		System.out.print("Unesite zvanje doktora: ");
		String str = PomocnaKlasa.ocitajTekst();
		
		try {
			
			List<Doktor> doktori = DoktorDAO.getDocByTitle(str);
			System.out.println();
			System.out.printf("%-10s %-10s %-20s", "ID", "Ime", "Prezime");
			System.out.println();
			System.out.println("========== ========== ====================");
			
			for (int i = 0; i < doktori.size(); i++) {
				System.out.printf("%-10s %-10s %-20s", 
						doktori.get(i).getId(),
						doktori.get(i).getIme(),
						doktori.get(i).getPrezime());
				System.out.println();
			}
			System.out.println();
			System.out.println("Ukupan broj doktora sa zvanjem " + str + " je: " + doktori.size());
			System.out.println("---------- ---------- -------------------");
		}catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!!!");
			ex.printStackTrace();
		}
	}
}
