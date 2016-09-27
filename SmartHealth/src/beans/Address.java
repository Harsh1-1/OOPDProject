package beans;

public class Address {
	public String StreetNumber;
	public String StreetName;
	public String MajorMunicipality;
	public String GoverningDistrict;
	public String PostalArea;
	public Address(String StreetNumber, String StreetName, String MajorMunicipality,
			String GoverningDistrict, String PostalArea){
		this.StreetNumber = StreetNumber;
		this.StreetName = StreetName;
		this.MajorMunicipality = MajorMunicipality;
		this.GoverningDistrict = GoverningDistrict;
		this.PostalArea = PostalArea;
	}
	
	public String toString(){
		String address = 
				"\nStreet Number : " + StreetNumber +
				"\nStreet Name : " + StreetName +
				"\nMajor Municipality : " + MajorMunicipality +
				"\nGoverning District : " + GoverningDistrict +
				"\nPostal Area : " + PostalArea;
		return address;
	}
}
