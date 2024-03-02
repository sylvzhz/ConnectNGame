/**
 * 
 * This class reads the leaderboard text file for display and
 * update the best score when a round is over and new best score
 * is presented.
 * 
 */

package edu.wm.cs.cs301.connectn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;  // Import the File class

public class Leaderboard{
	int scoreSmall = 0;
	int scoreMid = 0;
	int scoreLarge = 0;
	String line1 = "";
	String line2 = "";
	String line3 = "";
	Scanner scanner;
	
	public void printFile() {
		File myLeaderboard = new File(System.getProperty("user.dir")+"/resources/leaderboard");
		//read initial file
		try {
			scanner = new Scanner(myLeaderboard);
			System.out.println("Best Score:");
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				System.out.println(data);
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public void updateFile(String ver, HumanPlayer humanPlayer) throws IOException {
		File myLeaderboard = new File(System.getProperty("user.dir")+"/resources/leaderboard");
		
		//read current best score
		scanner = new Scanner(myLeaderboard);
		if (scanner.hasNextLine()) {
			line1 = scanner.nextLine();
			scoreSmall = line1.charAt(line1.length()-1);
		}
		if (scanner.hasNextLine()) {
			String toBeLine2 = scanner.nextLine();
			if (!toBeLine2.equals("")) {
				line2 = toBeLine2;
				scoreMid = line2.charAt(line2.length()-1);
			}
		}
		if (scanner.hasNextLine()) {
			String toBeLine3 = scanner.nextLine();
			if (!toBeLine3.equals("")) {
				line3 = toBeLine3;
				scoreLarge = line3.charAt(line3.length()-1);
			}
		}
		
		scanner.close();
		
		//update file
		FileWriter update = new FileWriter(myLeaderboard);
		if (ver.equals("Small") && (scoreSmall == 0 | scoreSmall > humanPlayer.getMoves())) {
			scoreSmall = humanPlayer.getMoves();
			update.write("Best score for mode Small: " + scoreSmall + "\n" + line2 + "\n" + line3);
		}
		if (ver.equals("Medium") && (scoreMid == 0 | scoreMid > humanPlayer.getMoves())) {
			scoreMid = humanPlayer.getMoves();
			update.write(line1 + "\n" + "Best score for mode Medium: " + scoreMid + "\n" + line3);
		}
		if (ver.equals("Large") && (scoreLarge == 0 | scoreLarge > humanPlayer.getMoves())) {
			scoreLarge = humanPlayer.getMoves();
			update.write(line1 + "\n" + line2 + "\n" + "Best score for mode Large: " + scoreLarge);
		}
		update.close();
		
	}
}
