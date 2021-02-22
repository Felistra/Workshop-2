
public class Student {
	private String namn;
	private int alder;
	
	public Student () {
		namn="";
		alder=0;
	}
	
	public void setAlder (int alderIn) {
		alder=alderIn;
	}
	
	public int getAlder () {
		return alder;
	}
	
	public void setNamn (String namnIn) {
		namn=namnIn;
	}
	
	public String getNamn () {
		return namn;
	}

}
