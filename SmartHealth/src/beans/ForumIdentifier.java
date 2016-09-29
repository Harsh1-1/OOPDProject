package beans;

public class ForumIdentifier {
	private final int forumID;
	private final String forumDesc;
	
	public ForumIdentifier(int forumID, String forumDesc){
		this.forumID = forumID;
		this.forumDesc = forumDesc;
	}
	
	public int getForumID() {
		return forumID;
	}

	public String getForumDesc() {
		return forumDesc;
	}

}
