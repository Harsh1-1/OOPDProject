package smart;

import java.util.ArrayList;
import java.util.Scanner;

import beans.ForumIdentifier;
import beans.Moderator;

public class ForumList extends State{

	private final models.ForumList model = new models.ForumList();
	private static final int LIST_FORUMS = 2;
	private static final int VIEW_FORUM = 3;
	private static final int CREATE_FORUM = 1;
	private static final int HOME_PAGE = 4;
	
	ForumList(Scanner sc) {
		super(sc);
	}

	private int getModeratorChoice(){
		int choice;
		System.out.println(CREATE_FORUM + ". Create Forum");
		System.out.println(LIST_FORUMS + ". List Forums");
		System.out.println(VIEW_FORUM + ". View Forum");
		System.out.println(HOME_PAGE + ". Go to homepage");
		System.out.println("Enter your choice ");
		choice = sc.nextInt();
		if(choice < CREATE_FORUM || choice > HOME_PAGE){
			System.out.println("Invalid Choice");
			return -1;
		}
		return choice;
	}
	
	private int getChoice(){
		int choice;
		System.out.println(LIST_FORUMS - 1 + ". List Forums");
		System.out.println(VIEW_FORUM - 1 + ". View Forum");
		System.out.println(HOME_PAGE - 1 + ". Go to homepage");
		System.out.println("Enter your choice ");
		choice = sc.nextInt();
		if(choice < LIST_FORUMS - 1 || choice > HOME_PAGE - 1){
			System.out.println("Invalid Choice");
			return -1;
		}
		return choice + 1;
	}
	
	@Override
	State handle() {
		int choice;
		if(SmartHealth.curUser.getUserType().equals("MOD")){ 
			choice = getModeratorChoice();
			if(choice == -1) return this;
		}else{
			choice = getChoice();
			if(choice == -1) return this;
		}
		switch(choice){
		case VIEW_FORUM:
			System.out.println("Enter the ID of the Forum you want to view ");
			int forumChoice = sc.nextInt();
			if(forumChoice < 1 || forumChoice > model.numIDs()){
				System.out.println("Invalid Choice");
				return this;
			}
			return new Forums(sc,forumChoice);
		case CREATE_FORUM:
			System.out.println("Enter the Forum topic");
			sc.nextLine();
			String topic = sc.nextLine();
			System.out.println("Enter a short description of the topic ");
			sc.nextLine();
			String summary = sc.nextLine();
			model.createForum(topic, summary, (Moderator)SmartHealth.curUser);
			break;
		case LIST_FORUMS:
			ArrayList<ForumIdentifier> forums;
			if(SmartHealth.curUser.getUserType().equals("MOD"))
				forums = model.listForums();
			else forums = model.listOpenForums();
			for(ForumIdentifier f : forums){
				System.out.println(f.getForumID() + ". " + f.getForumDesc());
			}
			break;
		case HOME_PAGE:
			return new LoggedIn(sc);
		default:
			System.out.println("Invalid choice");
			return this;
		}
		return this;
	}
	
}
