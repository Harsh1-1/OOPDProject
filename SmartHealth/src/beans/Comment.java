package beans;

public class Comment extends UserPost{

	public Comment(String username, String timeCreated, String textEntry, String photoLocation, String linkLocation,
			String videoLocation) {
		super(username, timeCreated, textEntry, photoLocation, linkLocation, videoLocation);
	}

}
