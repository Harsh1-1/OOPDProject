package smart;

class Qualification {
	int id;
	String name;
	Qualification(int id,String name){
		this.id = id;
		this.name = name;
	}
	public String toString(){
		return name;
	}
}
