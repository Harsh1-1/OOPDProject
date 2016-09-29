package beans;

public class UserPost {

	private final String username;
	private final String timeCreated;
	private String textEntry;
	private String photoLocation;
	private String linkLocation;
	private String videoLocation;
	
	public UserPost(String username, String timeCreated, String textEntry, String photoLocation,
			String linkLocation, String videoLocation) {
		this.username = username;
		this.timeCreated = timeCreated;
		this.textEntry = textEntry;
		this.photoLocation = photoLocation;
		this.linkLocation = linkLocation;
		this.videoLocation = videoLocation;
	}
	
	public String getUsername() {
		return username;
	}

	public String getTimeCreated() {
		return timeCreated;
	}

	public String getTextEntry() {
		return textEntry;
	}

	public String getPhotoLocation() {
		return photoLocation;
	}

	public String getLinkLocation() {
		return linkLocation;
	}

	public String getVideoLocation() {
		return videoLocation;
	}

	public void setTextEntry(String textEntry) {
		this.textEntry = textEntry;
	}

	public void setPhotoLocation(String photoLocation) {
		this.photoLocation = photoLocation;
	}

	public void setLinkLocation(String linkLocation) {
		this.linkLocation = linkLocation;
	}

	public void setVideoLocation(String videoLocation) {
		this.videoLocation = videoLocation;
	}
}
