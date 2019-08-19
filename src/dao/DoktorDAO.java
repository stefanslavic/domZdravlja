package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Doktor;

public class DoktorDAO {

//	CRUD OPERACIJE NAD DOKTOROM
	
	public static Doktor getDoctorByID (int id) throws Exception {
		
		Doktor doktor = null;
			
//		Statement je objekat, i ovde si ga postavio na null
//		da ne ukazuje ni na jednu vrednost
//		Zapravo on se kreira kao SQL upit nad bazom podataka
		Statement stmt = null;
		
//		ResultSet je takodje objekat koji je postavljen na null
//		kasnije u kodu mozes videti da u njega ce se postaviti 
//		taj upit koji si trazio u bazi
		ResultSet rset = null;
		
		try {
//			SQL upit
// 			OBAVEZNO PISATI NAZIVE TABELA I KOLONA IDENTICNO 
//			(cak i po case-u) KAO U SKRIPTI ZA KREIRANJE BAZE! 
//			zbog operativnog sistema, (case sensitive)
			String sql = "SELECT "
					+ "ime, prezime, zvanje "
					+ "FROM "
					+ "doktor "
					+ "WHERE "
					+ "id_doktor = " + id;
			
// 			kreiranje SQL naredbe, jednom za svaki SQL upit
			stmt = ConnectionManager.getConnection().createStatement();
// 			izvrsavanje naredbe i prihvatanje rezultata (SELECT), jednom za svaki SQL upit
//			u result setu su sve stringovi
			rset = stmt.executeQuery(sql);
			
			if (rset.next()) {
				int index = 1;
//				citanje rezultata upita
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String zvanje = rset.getString(index++);
				
				doktor = new Doktor(id, ime, prezime, zvanje);
			}
//			zatvaranje naredbe i rezultata
		} finally {
			try {
				stmt.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				rset.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return doktor;
		
	}
	
	public static Doktor getDoctorByName(String name) throws Exception{
		
		Doktor doktor = null;
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			String sql = "SELECT id_doktor, ime, prezime, zvanje FROM doktor WHERE ime = ?";
			
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, name);
			
			rset = stmt.executeQuery();
			
			if (rset.next()) {
				index = 1;
				int id = rset.getInt(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String zvanje = rset.getString(index++);
				
				doktor = new Doktor(id, ime, prezime, zvanje);
				
			}
		} finally {
			try {
				stmt.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				rset.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return doktor;
	}
	
//==================== METODA ZA ISPIS SVIH DOKTORA ===========================
	
	public static List<Doktor> getAll () throws Exception{
		
		List<Doktor> doktori = new ArrayList<Doktor>();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			String sql = "SELECT id_doktor, ime, prezime, zvanje FROM doktor";
			
			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				int index = 1;
				
				int id = rset.getInt(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String zvanje = rset.getString(index++);
				
				Doktor doktor = new Doktor(id, ime, prezime, zvanje);
				doktori.add(doktor);
			}
				
			} finally {
				try {
					stmt.close();
				}catch (Exception ex) {
					ex.printStackTrace();
				}
				try {
					rset.close();
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			return doktori;
	}
//=============================================================================
	
	
	public static boolean add (Doktor doktor) throws Exception{
		
		PreparedStatement stmt = null;
		
		try {
			String sql = "INSERT INTO doktor (id_doktor, ime, prezime, zvanje) VALUES (?, ?, ?, ?)";
			
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			
			int index = 1;
			stmt.setInt(index++, doktor.getId());
			stmt.setString(index++, doktor.getIme());
			stmt.setString(index++, doktor.getPrezime());
			stmt.setString(index++, doktor.getZvanje());
			
			return stmt.executeUpdate()	== 1;
		}finally {
			try {
				stmt.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
//=============================================================================
	
//==================== METODA ZA IZMENU NA OSNOVU ID ==========================

	public static boolean update (Doktor doktor) throws Exception{
		
		PreparedStatement stmt = null;
		
		try {
			
			String sql = "UPDATE doktor "
					+ "SET ime = ?, prezime = ?, zvanje = ? "
					+ "WHERE id_doktor = " + doktor.getId();
			
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			
			int index = 1;
			stmt.setString(index++, doktor.getIme());
			stmt.setString(index++, doktor.getPrezime());
			stmt.setString(index++, doktor.getZvanje());
			
			return stmt.executeUpdate() == 1;
			
		}finally {
			try {
				stmt.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
//=============================================================================
	
//==================== METODA ZA BRISANJE NA OSNOVU ID ========================
	
	public static boolean delete (int id) throws Exception {
		
		PreparedStatement stmt = null;
		
		try {
			
			String sql = "DELETE FROM doktor WHERE id_doktor = " + id;
			
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			return stmt.executeUpdate() == 1;
		}finally {
			try {
				stmt.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
//=============================================================================
}
