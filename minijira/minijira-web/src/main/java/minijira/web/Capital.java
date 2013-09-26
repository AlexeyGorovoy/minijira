package minijira.web;



public class Capital {
	
	private String country;
	private String city;
	
	public Capital(String country, String city) {
		this.country = country;
		this.city = city;
	}
	
	public Capital(String countryAndCity) {
		String tmpCountry;
		String tmpCity;
		int index = countryAndCity.indexOf(" - ");
		tmpCountry = countryAndCity.substring(0, index);
		tmpCity = countryAndCity.substring(index + 3, countryAndCity.length());
		country = tmpCountry;
		city = tmpCity;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getCity() {
		return city;
	}
}
