package model;

public class Pregled {

	//DA BI SE JEDAN PREGLED DESIO MORA DA POSTOJI 
	//MAKAR JEDAN DOKTOR I JEDAN PACIJENT
	
	protected Doktor doktor;
	protected Pacijent pacijent;
	
	protected String datumPregleda;
	
	public Pregled() {
		super();
	}
	
	public Pregled(Doktor doktor, Pacijent pacijent, String datumPregleda) {
		
		this.doktor = doktor;
		this.pacijent = pacijent;
		this.datumPregleda = datumPregleda;
	}

	public Doktor getDoktor() {
		return doktor;
	}

	public void setDoktor(Doktor doktor) {
		this.doktor = doktor;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public String getDatumPregleda() {
		return datumPregleda;
	}

	public void setDatumPregleda(String datumPregleda) {
		this.datumPregleda = datumPregleda;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pregled))
			return false;
		Pregled other = (Pregled) obj;
		if (doktor == null) {
			if (other.doktor != null)
				return false;
		} else if (!doktor.equals(other.doktor))
			return false;
		if (pacijent == null) {
			if (other.pacijent != null)
				return false;
		} else if (!pacijent.equals(other.pacijent))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String ispis = pacijent + " Datum pregleda: " + this.datumPregleda;
		return ispis;
	}
	
}
