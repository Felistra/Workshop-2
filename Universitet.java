
public class Universitet {
	private Hus h;
	private Salar s;
	private Student st;
	
	public Universitet (Student stIn) {
		h=new Hus(); //Aggregat då objektet Hus är ett objekt av objektet Universitet
		s=new Salar(); //Aggregat då objektet Salar är ett objekt av objektet Universitet
		st=stIn; //Association då objektet Student har en statisk koppling till Universitet men är fristående från objektet Universitet
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
		System.out.println("Studenten " + u.getStudent() + " är " + u.getAlder() + " år gammal och befinner sig i salen " + u.getSalnamn() + " i hus nummer " + u.getNr());
	}

}
