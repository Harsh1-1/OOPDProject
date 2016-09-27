package beans;

public class Address {
	private String streetNumber;
	private String streetName;
	private String majorMunicipality;
	private String governingDistrict;
	private String postalArea;
	
	public Address(String streetNumber, String streetName, String majorMunicipality,
			String governingDistrict, String postalArea){
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.majorMunicipality = majorMunicipality;
		this.governingDistrict = governingDistrict;
		this.postalArea = postalArea;
	}
	
	public String toString(){
		String address = 
				"\nStreet Number : " + streetNumber +
				"\nStreet Name : " + streetName +
				"\nMajor Municipality : " + majorMunicipality +
				"\nGoverning District : " + governingDistrict +
				"\nPostal Area : " + postalArea;
		return address;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getMajorMunicipality() {
		return majorMunicipality;
	}

	public String getGoverningDistrict() {
		return governingDistrict;
	}

	public String getPostalArea() {
		return postalArea;
	}
	
	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public void setMajorMunicipality(String majorMunicipality) {
		this.majorMunicipality = majorMunicipality;
	}

	public void setGoverningDistrict(String governingDistrict) {
		this.governingDistrict = governingDistrict;
	}

	public void setPostalArea(String postalArea) {
		this.postalArea = postalArea;
	}
	
	public void setStreetNumber(String streetNumber){
		this.streetNumber = streetNumber;
	}
}
