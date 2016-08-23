package smart;
import java.util.Scanner;

class SmartHealth {
	static User curUser;
	static int curPos = -1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		State s = new Login(sc);
		while(true){
			s = s.handle();
		}
	}

}
