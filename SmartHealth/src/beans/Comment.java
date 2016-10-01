package beans;

public class Comment extends UserPost{

	public Comment(String username, String timeCreated, String textEntry, String photoLocation, String linkLocation,
			String videoLocation) {
		super(username, timeCreated, textEntry, photoLocation, linkLocation, videoLocation);
	}

	public void display(){
		String indentation = "--------";
		System.out.println(indentation + "Username: " + getUsername());
		System.out.println(indentation + "Time Created: " + getTimeCreated());
		System.out.println(indentation + "Text: " + getTextEntry());
		System.out.println(indentation + "Photo: " + getPhotoLocation());
		System.out.println(indentation + "Link: " + getLinkLocation());
		System.out.println(indentation + "Video: " + getVideoLocation());
	}
}
