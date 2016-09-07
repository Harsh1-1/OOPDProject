package smart;

class Address {
	String StreetNumber;
	String StreetName;
	String MajorMunicipality;
	String GoverningDistrict;
	String PostalArea;
	Address(String StreetNumber, String StreetName, String MajorMunicipality,
			String GoverningDistrict, String PostalArea){
		this.StreetNumber = StreetNumber;
		this.StreetName = StreetName;
		this.MajorMunicipality = MajorMunicipality;
		this.GoverningDistrict = GoverningDistrict;
		this.PostalArea = PostalArea;
	}
	
	public String toString(){
		String address = 
				"Street Number : " + StreetNumber +
				"Street Name : " + StreetName +
				"Major Municipality : " + MajorMunicipality +
				"Governing District : " + GoverningDistrict +
				"Postal Area : " + PostalArea;
		return address;
	}
}
