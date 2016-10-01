package beans;

public class PostIdentifier {
	private final String userName;
	public String getUserName() {
		return userName;
	}

	public String getTimeCreated() {
		return timeCreated;
	}

	private final String timeCreated;
	
	public PostIdentifier(String userName, String timeCreated){
		this.userName = userName;
		this.timeCreated = timeCreated;
	}
}
