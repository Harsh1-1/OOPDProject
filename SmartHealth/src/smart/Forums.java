package smart;

import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import beans.Forum;
import beans.Moderator;
import beans.Post;
import beans.PostIdentifier;

public class Forums extends State{

	private static final int CLOSE_FORUM = 1;
	private static final int COMMENT_ON_POST = 2;
	private static final int RATE_POST = 3;
	private static final int HOME_PAGE = 4;
	private models.Forums model = new models.Forums();
	
	private final int forumID;
	Forums(Scanner sc, int forumID) {
		super(sc);
		this.forumID = forumID;
	}

	private int getModeratorChoice(){
		System.out.println(CLOSE_FORUM + ". Close Forum");
		System.out.println(COMMENT_ON_POST + ". Comment");
		System.out.println(RATE_POST + ". Rate");
		System.out.println(HOME_PAGE + ". Go to home page");
		System.out.println("Enter your choice ");
		int choice = sc.nextInt();
		if(choice < CLOSE_FORUM || choice > HOME_PAGE){
			System.out.println("Invalid choice");
			return -1;
		}
		return choice;
	}
	
	private int getChoice(){
		System.out.println(COMMENT_ON_POST - 1 + ". Comment");
		System.out.println(RATE_POST - 1 + ". Rate");
		System.out.println(HOME_PAGE - 1 + ". Go to home page");
		System.out.println("Enter your choice ");
		int choice = sc.nextInt();
		if(choice < COMMENT_ON_POST - 1 || choice > HOME_PAGE - 1){
			System.out.println("Invalid choice");
			return -1;
		}
		return choice + 1;
	}
	@Override
	State handle() {
		if(model.isClosed(forumID)){
			System.out.println("The forum is closed and cannot be viewed");
			return new ForumList(sc);
		}
		Forum curForum = model.getForumContent(forumID);
		curForum.display();
		int choice;
		if(SmartHealth.curUser.getUserType().equals("MOD")){
			choice = getModeratorChoice();
			if(choice == -1) return this;
		}else{
			choice = getChoice();
			if(choice == -1) return this;
		}
		switch(choice){
		case CLOSE_FORUM:
			boolean closedSuccessfully = model.closeForum(forumID, (Moderator)SmartHealth.curUser);
			if(closedSuccessfully) System.out.println("Forum has been closed");
			break;
		case COMMENT_ON_POST:
			System.out.println("Enter the ID of post you want to comment on");
			int id = sc.nextInt();
			PostIdentifier pi = findPostbyID(curForum, id);
			if(pi == null){
				System.out.println("Post does not exist");
				return this;
			}
			System.out.println("Enter the Text");
			sc.nextLine();
			String text = sc.nextLine();
			System.out.println("Enter the Photo Location");
			String photoLocation = sc.next();
			if(!isValidURL(photoLocation)){
				System.out.println("Invalid URL");
				return this;
			}
			System.out.println("Enter the Link Location");
			String linkLocation = sc.next();
			if(!isValidURL(photoLocation)){
				System.out.println("Invalid URL");
				return this;
			}
			System.out.println("Enter the Video Location");
			String videoLocation = sc.next();
			if(!isValidURL(photoLocation)){
				System.out.println("Invalid URL");
				return this;
			}
			model.commentOnPost(pi.getUserName(), pi.getTimeCreated(), 
					SmartHealth.curUser.getUserId(), text, photoLocation, linkLocation, videoLocation);
			break;
		case RATE_POST:
			System.out.println("Enter the ID of post you want to rate");
			id = sc.nextInt();
			pi = findPostbyID(curForum, id);
			if(pi == null){
				System.out.println("Post does not exist");
				return this;
			}
			System.out.println("Enter the stars you want to give");
			int stars = sc.nextInt();
			model.ratePost(pi.getUserName(), pi.getTimeCreated(), 
					SmartHealth.curUser.getUserId(), stars);
			break;
		case HOME_PAGE:
			return new LoggedIn(sc);
		default:
			System.out.println("Invalid Choice");
		}
		return this;
	}
	
	private PostIdentifier findPostbyID(Forum forum, int id){
		ArrayList<Post> posts = forum.getPosts();
		for(Post p : posts){
			if(p.getID() == id){
				return new PostIdentifier(p.getUsername(),p.getTimeCreated());
			}
		}
		return null;
	}
	
	boolean isValidURL(String URL){
		try{
			URL url = new URL(URL);
			url.toURI();
			return true;
		}
		catch(Exception ex){
			System.out.println("Could not verify URL");
		}
		return false;
	}
}
