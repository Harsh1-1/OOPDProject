package beans;

import java.util.ArrayList;

public class Post extends UserPost{
	
	private ArrayList<Comment> comments;
	private int rating;
	
	public Post(String username, String timeCreated, String textEntry, String photoLocation, String linkLocation,
			String videoLocation, ArrayList<Comment> comments, int rating) {
		super(username, timeCreated, textEntry, photoLocation, linkLocation, videoLocation);
		this.comments = comments;
		this.rating = rating;
	}
	
	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void addComments(Comment comment) {
		this.comments.add(comment);
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
