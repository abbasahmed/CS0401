
// CS 401 Fall 2016 Lab 7 Main Program
// Your job is to complete this program so that it runs correctly.
// The Movie class and MovieDB class have already been completed for you.
// Utilizing data abstraction, you can access/use these classes without
// having to know their implementation details.  However, since you have
// the code for them you certainly can look at the implementations.
// You just need to write the correct code in the 3 passages below.  Some
// comments indicate what you need to do in each case.
import java.util.*;
import java.io.*;

public class Lab7 {
	public static void main(String[] args) throws IOException {
		MovieDB movies = new MovieDB(10); // Create MovieDB object. The
		// size is set at 10, meaning it can hold up to 10
		// movies. If we wanted (as discussed in lecture) we
		// could allow for it to be resized so it could hold
		// an arbitrary number of movies.
		loadData(movies); // input movie data from file
		getCommands(movies); // interact with user
		saveData(movies); // save movie data back to file
	}

	public static void loadData(MovieDB movies) throws IOException {
		// Note the syntax below for creating a Scanner to a file
		Scanner S = new Scanner(new FileInputStream("movieFile.txt"));

		// *** CODE SEGMENT 1 *** //
		// Complete this method in the following way:
		// Read in the number of movies from the file
		// For each movie read the data from the file and create a Movie
		// object
		// Add the Movie to the MoviesDB object (movies) using the appropriate
		// method (see MovieDB class)
		int movieNum = Integer.parseInt(S.nextLine());
		Movie[] myMovies = new Movie[movieNum];
		for (int i = 0; i < movieNum; i++) {
			String movieName = S.nextLine();
			String movieDir = S.nextLine();
			String movieStudio = S.nextLine();
			double movieGross = Double.parseDouble(S.nextLine());
			Movie theMovies = new Movie(movieName, movieDir, movieStudio, movieGross);
			myMovies[i] = theMovies;
			movies.addMovie(theMovies);
		}
		S.close();
	}

	public static void getCommands(MovieDB movies) {
		Scanner inScan = new Scanner(System.in);
		System.out.println("Enter your choice:");
		System.out.println("1. List movies");
		System.out.println("2. Add new movie");
		System.out.println("3. Find movie");
		System.out.println("4. Quit");
		String choice = inScan.nextLine();
		while (true) {
			Movie temp;
			if (choice.equals("1")) {
				System.out.println(movies.toString());
			} else if (choice.equals("2")) {
				// *** CODE SEGMENT 2 *** //
				// Complete this choice in the following way:
				// Prompt for and read in each of the values needed
				// for the new Movie object (3 strings, 1 double)
				// Create a new Movie object and then add it to the
				// MovieDB object (movies) using the correct method.
				System.out.println("Please enter the name of your movie");
				String mName = inScan.nextLine();
				System.out.println("Please enter the name of Movie Director");
				String mDir = inScan.nextLine();
				System.out.println("Please enter the Studio name");
				String mStu = inScan.nextLine();
				System.out.println("Please enter the Movie gross");
				double mGross = Double.parseDouble(inScan.nextLine());
				temp = new Movie(mName, mDir, mStu, mGross);
				movies.addMovie(temp);
				System.out.println("Done!");
			} else if (choice.equals("3")) {
				// *** CODE SEGMENT 3 *** //
				// Complete this choice in the following way:
				// Ask the user for the movie name and read it in
				// Call the appropriate method in the MovieDB object
				// (movies) to find the Movie and return it
				// Show the movie's info (or indicate it is not found)
				System.out.println("Please enter the movie you're looking for");
				String movieFinder = inScan.nextLine();
				Movie now = movies.findMovie(movieFinder);
				if (now != null) {
					System.out.println(" The movie is in the text file and here's the info \n" + now.toString());
				} else if (now == null) {
					System.out.println("The movie does not exist in the file, sorry.");
				}

			} else {
				System.out.println("Good-bye");
				break; // any other value -- quit
			}
			System.out.println("Enter your choice:");
			System.out.println("1. List movies");
			System.out.println("2. Add new movie");
			System.out.println("3. Find movie");
			System.out.println("4. Quit");
			choice = inScan.nextLine();
		}
		inScan.close();
	}

	public static void saveData(MovieDB movies) throws IOException {
		PrintWriter P = new PrintWriter("movieFile.txt");
		// Note that we are printing the entire DB to the file with
		// one statement. This is because the toStringFile() method
		// of the MovieDB class calls the toStringFile() method of
		// each Movie within the DB.
		P.print(movies.toStringFile());
		P.close();
	}
}
