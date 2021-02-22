import java.util.LinkedList;

public class Hus {
	private int nummer;
	private LinkedList <Salar> lista;
	
	public Hus () {
		nummer=0;
	}
	
	public void setNr (int n) {
		nummer=n;
	}
	
	public int getNr () {
		return nummer;
	}
}
