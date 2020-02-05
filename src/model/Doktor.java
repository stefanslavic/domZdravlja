package model;

import java.util.ArrayList;

public class Doktor {
	
	protected int id;
	protected String ime;
	protected String prezime;
	protected String zvanje;
/*	
	Jedan DOKTOR moze da ima vise PACIJENATA
	Svi pacijenti za jednog doktora
*/
	protected ArrayList<Pacijent> listaPacijenata = new ArrayList<Pacijent>();

/*
	DOKTOR moze da ima vise PREGLEDA, a ne mora da ima ni jedan
	Svi pregledi za jednog doktora
*/
	protected ArrayList<Pregled> listaPregleda = new ArrayList<Pregled>();
	
	public Doktor() {
	}

	public Doktor(int id, String ime, String prezime) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
	}

	public Doktor(int id, String ime, String prezime, String specijalnost) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.zvanje = specijalnost;
	}

	public Doktor(int id, String ime, String prezime, String zvanje, ArrayList<Pacijent> listaPacijenata,
			ArrayList<Pregled> listaPregleda) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.zvanje = zvanje;
		this.listaPacijenata = listaPacijenata;
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

	public String getZvanje() {
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public ArrayList<Pacijent> getListaPacijenata() {
		return listaPacijenata;
	}

	public void setListaPacijenata(ArrayList<Pacijent> listaPacijenata) {
		this.listaPacijenata = listaPacijenata;
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
		if (!(obj instanceof Doktor))
			return false;
		Doktor other = (Doktor) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		String ispis = "Doktor sa id " + this.id + " cije je ime i prezime " + this.ime + " " + this.prezime 
				+ " i ima zvanje " + this.zvanje;
		return ispis;
	}
	
	public String vratiTekstualnuReprezentacijuZaIspis () {
		System.out.format("%-10s %-10s %-20s %-20s", "ID", "Ime", "Prezime", "Zvanje");
		System.out.println();
		System.out.println("========== ========== ==================== ====================");
		return String.format("%-10s %-10s %-20s %-20s", this.id, this.ime, this.prezime, this.zvanje);
	}
}
