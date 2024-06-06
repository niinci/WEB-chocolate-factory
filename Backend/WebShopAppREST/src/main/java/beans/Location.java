package beans;

public class Location {
	private String id;
	private double longitude;
	private double latitude; 
	private String address; 

	public Location() {}
	public Location(String id, double longitude, double latitude, String address) {
		this.id = id; 
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
