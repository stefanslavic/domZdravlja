package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Pacijent;

public class PacijentDAO {
	
//CRUD OPERATION

	public static Pacijent getPatientByID(int id) throws Exception {
		
		Pacijent pacijent = null;
		
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			String sql = "SELECT "
					+ "ime, prezime, jmbg, simptom, mob "
					+ "FROM "
					+ "pacijent "
					+ "WHERE id_pacijent = " + id;
			
			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);
			
			if (rset.next()) {
				int index = 1;
				
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String jmbg = rset.getString(index++);
				String simptom = rset.getString(index++);
				String mob = rset.getString(index++);
				
				pacijent = new Pacijent(id, ime, prezime, jmbg, simptom, mob);
			}
		}finally {
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
		return pacijent;
	}
	
	public static List<Pacijent> getAll () throws Exception {
		
		List<Pacijent> sviPacijenti = new ArrayList<Pacijent>();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			String sql = "SELECT id_pacijent, ime, prezime, jmbg, simptom, mob FROM pacijent";
			
			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				int index = 1;
				
				int id = rset.getInt(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String jmbg = rset.getString(index++);
				String simptom = rset.getString(index++);
				String mob = rset.getString(index++);
				
				Pacijent pacijent = new Pacijent(id, ime, prezime, jmbg, simptom, mob);
				sviPacijenti.add(pacijent);
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
		return sviPacijenti;
	}
	
	public static boolean add (Pacijent pacijent) throws Exception {
		
		PreparedStatement stmt = null;
		
		try {
			String sql = "INSERT INTO pacijent (id_pacijent, ime, prezime, jmbg, simptom, mob) VALUES (?, ?, ?, ?, ?, ?)";
			
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			
			int index = 1;
			stmt.setInt(index++, pacijent.getId());
			stmt.setString(index++, pacijent.getIme());
			stmt.setString(index++, pacijent.getPrezime());
			stmt.setString(index++, pacijent.getJmbg());
			stmt.setString(index++, pacijent.getSimptom());
			stmt.setString(index++, pacijent.getMob());
			
			return stmt.executeUpdate() == 1;
		}finally {
			try {
				stmt.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static boolean update (Pacijent pacijent) throws Exception {
		
		PreparedStatement stmt = null;
		
		try {
			String sql = "UPDATE pacijent"
					+ " SET ime = ?, prezime = ?, jmbg = ?, simptom = ?, mob = ? "
					+ "WHERE id_pacijent = " + pacijent.getId();
			
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			
			int index = 1;
			stmt.setString(index++, pacijent.getIme());
			stmt.setString(index++, pacijent.getPrezime());
			stmt.setString(index++, pacijent.getJmbg());
			stmt.setString(index++, pacijent.getSimptom());
			stmt.setString(index++, pacijent.getMob());
			
			return stmt.executeUpdate() == 1;
		}finally {
			try {
				stmt.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static boolean delete (int id) throws Exception {
		
		PreparedStatement stmt = null;
		
		try {
			String sql = "DELETE FROM pacijent WHERE id_pacijent = " + id;
			
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
	
	public static List<Pacijent> getAllDesc () throws Exception{
		
		List<Pacijent> sviPacijenti = new ArrayList<Pacijent>();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			
			String sql = "SELECT * FROM pacijent ORDER BY ime DESC";
			
			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				int index = 1;
				
				int id = rset.getInt(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String jmbg = rset.getString(index++);
				String simptom = rset.getString(index++);
				String mob = rset.getString(index++);
				
				Pacijent pacijent = new Pacijent(id, ime, prezime, jmbg, simptom, mob);
				sviPacijenti.add(pacijent);
			
			}
		}finally {
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
		return sviPacijenti;
		}
	
	public static List<Pacijent> getAllAsc () throws Exception{
		
		List<Pacijent> sviPacijenti = new ArrayList<Pacijent>();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			
			String sql = "SELECT * FROM pacijent ORDER BY ime ASC";
			
			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				int index = 1;
				
				int id = rset.getInt(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String jmbg = rset.getString(index++);
				String simptom = rset.getString(index++);
				String mob = rset.getString(index++);
				
				Pacijent pacijent = new Pacijent(id, ime, prezime, jmbg, simptom, mob);
				sviPacijenti.add(pacijent);
			
			}
		}finally {
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
		return sviPacijenti;
		}
	
	public static List<Pacijent> getByPain (String str) throws Exception {
		
		List<Pacijent> pacijenti = new ArrayList<Pacijent>();
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			String sql = "SELECT id_pacijent, ime, prezime FROM pacijent WHERE simptom = ?";
			
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, str);
			
			rset = stmt.executeQuery();
			
			while (rset.next()) {
				index = 1;
				
				int id = rset.getInt(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				
				Pacijent pacijent = new Pacijent(id, ime, prezime);
				pacijenti.add(pacijent);
			}
			
		}finally {
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
		return pacijenti;
	}
}