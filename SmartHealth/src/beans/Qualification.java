package beans;

public class Qualification {
	int id;
	String name;
	public Qualification(int id,String name){
		this.id = id;
		this.name = name;
	}
	public String toString(){
		return name;
	}
	public int getQualificationID(){
		return id;
	}
}
