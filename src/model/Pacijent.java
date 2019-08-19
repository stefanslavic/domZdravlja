package model;

import java.util.ArrayList;

public class Pacijent {
	
	protected int id;
	protected String ime;
	protected String prezime;
	protected String jmbg;
	protected String simptom;
	protected String mob; //Medicinska Oblast Bolesti - upisujes kod kog lekara ide, odnosno na osnovu kod simptoma on treba da ide kod lekara
	
//	PACIJENT moze da ima vise DOKTORA
//	Svi Doktori za jednog pacijenta
	protected ArrayList<Doktor> listaDoktora = new ArrayList<Doktor>();
	
//	PACIJENT moze da ima vise PREGLEDA, a ne mora da ima ni jedan
//	Svi pregledi za jednog pacijenta
	protected ArrayList<Pregled> listaPregleda = new ArrayList<Pregled>();

	public Pacijent(int id, String ime, String prezime, String jmbg, String simptom, String mob) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.simptom = simptom;
		this.mob = mob;
	}
	
	public Pacijent(int id, String ime, String prezime, String jmbg, String simptom, String mob,
			ArrayList<Doktor> listaDoktora, ArrayList<Pregled> listaPregleda) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.simptom = simptom;
		this.mob = mob;
		this.listaDoktora = listaDoktora;
		this.listaPregleda = listaPregleda;
	}

	public int getId() {
		return id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getSimptom() {
		return simptom;
	}

	public void setSimptom(String simptom) {
		this.simptom = simptom;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public ArrayList<Doktor> getListaDoktora() {
		return listaDoktora;
	}

	public void setListaDoktora(ArrayList<Doktor> listaDoktora) {
		this.listaDoktora = listaDoktora;
	}

	public ArrayList<Pregled> getListaPregleda() {
		return listaPregleda;
	}

	public void setListaPregleda(ArrayList<Pregled> listaPregleda) {
		this.listaPregleda = listaPregleda;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pacijent))
			return false;
		Pacijent other = (Pacijent) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		String ispis = "Pregled za pacijenta ciji je ID " + this.id + " i ime i prezime pacijentu je " + this.ime + " " + this.prezime
				+ "," + " ima simptom " + this.simptom + " i zakazan pregled kod " + this.mob + ".";
		return ispis;
	}
	
	public String vratiTekstualnuReprezentacijuZaIspis () {
		System.out.format("%-10s %-20s %-20s %-20s %-20s %-30s", "ID", "Ime", "Prezime", "JMBG", "Simptom", "Medicinska oblast bolesti");
		System.out.println();
		System.out.println("========== ==================== ==================== ==================== ==================== =========================");
		return String.format("%-10s %-20s %-20s %-20s %-20s %-30s", this.id, this.ime, this.prezime, this.jmbg, this.simptom, this.mob);
	}
}
