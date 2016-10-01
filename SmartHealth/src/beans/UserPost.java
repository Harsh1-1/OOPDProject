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
		setTextEntry(textEntry);
		setPhotoLocation(photoLocation);
		setLinkLocation(linkLocation);
		setVideoLocation(videoLocation);
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
		if(textEntry != null) this.textEntry = textEntry;
		else this.textEntry = "";
	}

	public void setPhotoLocation(String photoLocation) {
		if(photoLocation != null)
			this.photoLocation = photoLocation;
		else this.photoLocation = "";
	}

	public void setLinkLocation(String linkLocation) {
		if(linkLocation != null) this.linkLocation = linkLocation;
		else this.linkLocation = "";
	}

	public void setVideoLocation(String videoLocation) {
		if(videoLocation != null )this.videoLocation = videoLocation;
		else videoLocation = "";
	}
}
