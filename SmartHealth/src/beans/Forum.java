package beans;

import java.util.ArrayList;

public class Forum {

	private final int forumID;
	private String topic;
	private String url;
	private String summary;
	private String whenCreated;
	private String whenClosed;
	private String createdBy;
	private String deletedBy;
	private ArrayList<Post> posts;
	
	public Forum(int forumID, String topic, String url, String summary, 
			String whenCreated, String createdBy, ArrayList<Post> posts,String whenClosed, String deletedBy){
		this.forumID = forumID;
		this.topic = topic;
		this.url = url;
		this.summary = summary;
		this.whenCreated = whenCreated;
		this.createdBy = createdBy;
		this.whenClosed = whenClosed;
		this.deletedBy = deletedBy;
		this.posts = posts;
	}
	
	public Forum(int forumID, String topic, String url, String summary, 
			String whenCreated, String createdBy, ArrayList<Post> posts){
		this(forumID, topic, url, summary, whenCreated, createdBy, posts,"NOT CLOSED", "");
	}

	public int getForumID() {
		return forumID;
	}

	public String getTopic() {
		return topic;
	}

	public String getUrl() {
		return url;
	}

	public String getSummary() {
		return summary;
	}

	public String getWhenCreated() {
		return whenCreated;
	}

	public String getWhenClosed() {
		return whenClosed;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getDeletedBy() {
		return deletedBy;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setWhenCreated(String whenCreated) {
		this.whenCreated = whenCreated;
	}

	public void setWhenClosed(String whenClosed) {
		this.whenClosed = whenClosed;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	public ArrayList<Post> getPosts() {
		return posts;
	}

	public void addPosts(Post post) {
		this.posts.add(post);
	}
}
