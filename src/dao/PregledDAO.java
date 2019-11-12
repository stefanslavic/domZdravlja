package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Doktor;
import model.Pacijent;


public class PregledDAO {
	
//CRUD OPERATION
	
	public static List<Pacijent> getPacijentiByDoktorID (int doktorID) throws Exception {
		
		List<Pacijent> listaPacijenataKojeCekaDoktor = new ArrayList<Pacijent>();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			
			String sql = "SELECT id_pacijent FROM pregled WHERE id_doktor = " + doktorID;  
			
			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				int pacijentID = rset.getInt(1);
				
				Pacijent pacijent = PacijentDAO.getPatientByID(pacijentID);
				listaPacijenataKojeCekaDoktor.add(pacijent);
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
		return listaPacijenataKojeCekaDoktor;
	}
	
	public static List<Doktor> getDoktorByPacijentID (int pacijentID) throws Exception{
		
		List<Doktor> listaDoktoraKojePacijentCeka = new ArrayList<Doktor>();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			
			String sql = "SELECT id_doktor FROM pregled WHERE id_pacijent = " + pacijentID;
			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				int doktorID = rset.getInt(1);
				
				Doktor doktor = DoktorDAO.getDoctorByID(doktorID);
				listaDoktoraKojePacijentCeka.add(doktor);
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
		return listaDoktoraKojePacijentCeka;
	}
	
	public static boolean add (int pacijentID, int doktorID) throws Exception {
		
		Statement stmt = null;
		try {
			
			String sql = "INSERT INTO pregled (id_pacijent, id_doktor) VALUES (" + pacijentID + ", " + doktorID + ")";
			
			stmt = ConnectionManager.getConnection().createStatement();
			return stmt.executeUpdate(sql) == 1;
		}finally {
			try {
				stmt.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static boolean delete (int pacijentID, int doktorID) throws Exception {
		
		Statement stmt = null;
		
		try {
			
			String sql = "DELETE FROM pregled WHERE id_pacijent = " + pacijentID + " AND id_doktor = " + doktorID;
			
			stmt = ConnectionManager.getConnection().createStatement();
			return stmt.executeUpdate(sql) == 1;
		}finally {
			try {
				stmt.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}