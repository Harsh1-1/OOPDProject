package smart;

import java.util.Scanner;

public class Forums extends State{

	private final int forumID;
	Forums(Scanner sc, int forumID) {
		super(sc);
		this.forumID = forumID;
	}

	@Override
	State handle() {
		
		return this;
	}

}
