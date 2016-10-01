package beans;

import java.util.ArrayList;

public class Post extends UserPost{
	
	private final int ID;
	private ArrayList<Comment> comments;
	private double rating;
	
	public Post(String username, String timeCreated, String textEntry, String photoLocation, String linkLocation,
			String videoLocation, ArrayList<Comment> comments, double rating, int ID) {
		super(username, timeCreated, textEntry, photoLocation, linkLocation, videoLocation);
		this.comments = comments;
		this.rating = rating;
		this.ID = ID;
	}
	
	public void display(){
		String indentation = "----";
		System.out.println(indentation + "Post ID: " + ID);
		System.out.println(indentation + "Rating: " + rating);
		System.out.println(indentation + "Username: " + getUsername());
		System.out.println(indentation + "Time Created: " + getTimeCreated());
		System.out.println(indentation + "Text: " + getTextEntry());
		System.out.println(indentation + "Photo: " + getPhotoLocation());
		System.out.println(indentation + "Link: " + getLinkLocation());
		System.out.println(indentation + "Video: " + getVideoLocation());
		for(Comment c : comments){
			c.display();
		}
	}
	
	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void addComments(Comment comment) {
		this.comments.add(comment);
	}

	public double getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getID() {
		return ID;
	}
}
