package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Doktor;

public class DoktorDAO {

//CRUD OPERATION
	
	public static Doktor getDoctorByID (int id) throws Exception {
		
		Doktor doktor = null;
			
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			String sql = "SELECT "
					+ "ime, prezime, zvanje "
					+ "FROM "
					+ "doktor "
					+ "WHERE "
					+ "id_doktor = " + id;

			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);
			
			if (rset.next()) {
				int index = 1;
				
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
	
	public static List<Doktor> getDocByTitle (String str) throws Exception {
		
		List<Doktor> doktori = new ArrayList<Doktor>();
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			String sql = "SELECT id_doktor, ime, prezime FROM doktor WHERE zvanje = ?";
			
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, str);
			
			rset = stmt.executeQuery();
			
			while (rset.next()) {
				index = 1;
				
				int id = rset.getInt(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				
				Doktor doktor = new Doktor(id, ime, prezime);
				doktori.add(doktor);
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
		return doktori;
	}
}