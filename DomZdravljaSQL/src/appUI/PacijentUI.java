package appUI;

import java.util.List;

import dao.PacijentDAO;
import model.Pacijent;
import pomocnaKlasa.PomocnaKlasa;

public class PacijentUI {

//MENU
	public static void ispisiGlavniMeni () {
			
		System.out.println("Rad sa pacijentima - opcije:");
		System.out.println("\t1. Unos podataka o novom pacijentu");
		System.out.println("\t2. Izmena podataka o pacijentu na osnovu ID");
		System.out.println("\t3. Brisanje podataka o pacijentu");
		System.out.println("\t4. Ispisi sve pacijente");
		System.out.println("\t5. Ispisi podatke o odredjenom pacijentu");
		System.out.println("\t6. Soritiraj po imenu");
		System.out.println("\t7. Pretraga pacijenta po zadatim bolovima");
		System.out.println("=======================================================");
		System.out.println("\t0. IZLAZ");
	}
	
	public static void meniPacijentUI() {
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
				unosNovogPacijenta();
				break;
			case 2:
				izmenaPodatakaOPacijentu();
				break;
			case 3:
				birisanjePodatkaOPacijetnu();
				break;
			case 4:
				ispisiSvePacijente();
				break;
			case 5:
				Pacijent pacijent = findPatient();
				if (pacijent != null) {
					System.out.println(pacijent.vratiTekstualnuReprezentacijuZaIspis());
				}
				break;
			case 6:
				sortirajPoImenu();
				break;
			case 7:
				pretragaPoBolovima();
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}
	
//CRUD Operation
	
	//Find patient by ID
	public static Pacijent findPatient () {
		System.out.print("Unesite ID pacijenta: ");
		int idPac = PomocnaKlasa.ocitajCeoBroj();
		try {
			Pacijent pacijent = PacijentDAO.getPatientByID(idPac);
			if (pacijent == null) {
				System.out.println("Pacijent sa ID " + idPac + " ne postoji u evidenciji!");
			}
			return pacijent;
		}catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");
			ex.printStackTrace();
		}
		return null;
	}
	
	//All patients
	public static void ispisiSvePacijente() {
		
		try {
			
			List<Pacijent> sviPacijenti = PacijentDAO.getAll();
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
	
	//Create new patient 
	public static void unosNovogPacijenta() {
		
		try {
		
			Pacijent pacijent = null; 
			
			System.out.print("Unesite ime pacijenta: ");
			String pacIme = PomocnaKlasa.ocitajTekst();
			
			System.out.print("Unesite prezime pacijenta: ");
			String pacPrezime = PomocnaKlasa.ocitajTekst();
			
			System.out.print("Unesite jmbg pacijenta: ");
			String pacJmbg = PomocnaKlasa.ocitajTekst();
			
			System.out.print("Unesite simptom pacijenta: ");
			String pacSimptom = PomocnaKlasa.ocitajTekst();
			
			System.out.print("Unesite medicinsku oblast bolesti pacijenta: ");
			String pacMob = PomocnaKlasa.ocitajTekst();
			
			pacijent = new Pacijent(0, pacIme, pacPrezime, pacJmbg, pacSimptom, pacMob);
			
			PacijentDAO.add(pacijent);
			
			System.out.println();
			System.out.println("\tUspesno ste dodali novog pacijenta!");
			System.out.println();
			
		}catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");
			ex.printStackTrace();
		}
	}
	
	//Update patient 
	public static void izmenaPodatakaOPacijentu() {
		Pacijent pacijent = findPatient();
		
		if (pacijent != null) {
			System.out.print("Unesite novo ime pacijentu: ");
			String pacIme = PomocnaKlasa.ocitajTekst();
			pacijent.setIme(pacIme);
			
			System.out.print("Unesite novo prezime pacijentu: ");
			String pacPrezime = PomocnaKlasa.ocitajTekst();
			pacijent.setPrezime(pacPrezime);
			
			System.out.print("Unesite novi JMBG pacijentu: ");
			String pacJmbg = PomocnaKlasa.ocitajTekst();
			pacijent.setJmbg(pacJmbg);
			
			System.out.print("Unesite novi simptom pacijentu: ");
			String pacSimptom = PomocnaKlasa.ocitajTekst();
			pacijent.setSimptom(pacSimptom);
			
			System.out.print("Unesite novu medicinsku oblast bolesti pacijentu: ");
			String pacMob = PomocnaKlasa.ocitajTekst();
			pacijent.setMob(pacMob);
			
			try {
				PacijentDAO.update(pacijent);
				System.out.println();
				System.out.println("\tUspesno ste izmenili pacijenta!");
				System.out.println();
			}catch (Exception ex) {
				System.out.println("Greska u radu sa bazom!");
				ex.printStackTrace();
			}
		}
	}
	
	//Delete patient 
	public static void birisanjePodatkaOPacijetnu() {
		
		Pacijent pacijent = findPatient();
		if (pacijent != null) {
			try {
				PacijentDAO.delete(pacijent.getId());
				
				System.out.println();
				System.out.println("\tUspesno ste izbrisali pacijenta!");
				System.out.println();
			}catch (Exception ex) {
				System.out.println("Greska u radu sa bazom!");
				ex.printStackTrace();
			}
		}
	}
	
	//Sort patient by name (Desc&Asc)
	public static void sortirajPoImenu () {
		
		try {
			System.out.print("Pacijente je moguce sortirati\n\t1 - Ime Rastuce\n\t2 - Ime Opadajuce\nIzaberite opciju:  ");
			
			int sortOpcija = PomocnaKlasa.ocitajCeoBroj();
			switch (sortOpcija) {
			case 1:
				List<Pacijent> sviPacijenti = PacijentDAO.getAllDesc();
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
				break;
			case 2:
				List<Pacijent> sviPacijenti1 = PacijentDAO.getAllAsc();
				System.out.println();
				System.out.printf("%-10s %-20s %-20s %-20s %-20s %-30s", "ID", "Ime", "Prezime", "JMBG", "Simptom", "Medicinska oblast bolesti");
				System.out.println();
				System.out.println("========== ==================== ==================== ==================== ==================== =========================");
				
				for (int i = 0; i < sviPacijenti1.size(); i++) {
					System.out.printf("%-10s %-20s %-20s %-20s %-20s %-30s",
							sviPacijenti1.get(i).getId(),
							sviPacijenti1.get(i).getIme(),
							sviPacijenti1.get(i).getPrezime(),
							sviPacijenti1.get(i).getJmbg(),
							sviPacijenti1.get(i).getSimptom(),
							sviPacijenti1.get(i).getMob());
					System.out.println();
				}
				System.out.println("---------- -------------------- -------------------- -------------------- -------------------- -------------------------");
				break;
			}
			
		}catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");
			ex.printStackTrace();
		}
	}
	
	//Search patient by Pain
	public static void pretragaPoBolovima () {
		System.out.print("Unesite bol pacijenta: ");
		String str = PomocnaKlasa.ocitajTekst();
		
		try {
			List<Pacijent> pacijenti = PacijentDAO.getByPain(str);
			
			System.out.println();
			System.out.printf("%-10s %-10s %-20s", "ID", "Ime", "Prezime");
			System.out.println();
			System.out.println("========== ========== ====================");
			
			for (int i = 0; i < pacijenti.size(); i++) {
				System.out.printf("%-10s %-10s %-20s", 
								pacijenti.get(i).getId(),
								pacijenti.get(i).getIme(),
								pacijenti.get(i).getPrezime());
				System.out.println();
			}
			System.out.println();
			System.out.println("Ukupan broj pacijenata sa bolom " + str + " je: " + pacijenti.size());
			System.out.println("---------- ---------- -------------------");
		}catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");
			ex.printStackTrace();
		}
	}
}
