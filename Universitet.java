
public class Universitet {
	private Hus h;
	private Salar s;
	private Student st;
	
	public Universitet (Student stIn) {
		h=new Hus(); //Aggregat d� objektet Hus �r ett objekt av objektet Universitet
		s=new Salar(); //Aggregat d� objektet Salar �r ett objekt av objektet Universitet
		st=stIn; //Association d� objektet Student har en statisk koppling till Universitet men �r frist�ende fr�n objektet Universitet
	}
	
	public void setStudent () {
		st.setNamn("Felicia");
	}
	
	public String getStudent () {
		return st.getNamn();
	}
	
	public void setAlder () {
		st.setAlder(25);
	}
	
	public int getAlder () {
		return st.getAlder();
	}
	
	public void setSalNamn () {
		s.setSalNamn("Flying Horse");
	}
	
	public String getSalnamn () {
		return s.getSalNamn();
	}
	
	public void setNr () {
		h.setNr(10);
	}
	
	public int getNr () {
		return h.getNr();
	}

	public static void main(String[] args) {
		Student st = new Student(); 
		Universitet u = new Universitet(st); 
		u.setStudent();
		u.setAlder();
		u.setSalNamn();
		u.setNr();
		System.out.println("Studenten " + u.getStudent() + " �r " + u.getAlder() + " �r gammal och befinner sig i salen " + u.getSalnamn() + " i hus nummer " + u.getNr());
	}

}
