/**CS0401 Intermediate programming with Java
 * 
 * CS401 Assignment 4
 * 
 * Name: Abbas Z Ahmed
 * Peoplesoft No. 4165368
 * Pitt Email ID: aza16@pitt.edu
 * 
 * Professor Ramirez 
 * 
 * Classes: Monday and Thursday at 1 PM - 2:15 PM
 * Lab: Thursday 10 AM-11:50AM
 * Lab Instructor: Zinan Zhuang
 * 
 * The purpose of this project is to create your own String Builder class by working on inheritance of classes 
 * and operations on arrays.
 * 
 */

/**
 * @author abbas
 *
 */


// CS 0401 Fall 2016
// MySBuilder class.  You must implement this class for Assignment 4.  A shell of
// the class is already provided -- you must fill in the method bodies.

// Note 1: All code for these methods must be your own!  Do not copy these methods
// from code on the internet.  If you do so it will be a violation of the student
// academic integrity code!

// Note 2: You may NOT use StringBuilder or StringBuffer or any similar class in
// any of these methods!  You also may not use String for anything other than the
// argument and return object types (when needed) - i.e. you may not create String 
// objects in order to use String methods to perform any of the methods here.

// Note 3: Some of the methods have additional requirements / restrictions.  Read
// the comments for each method carefully before implementing it.


import java.util.*;

public class MySBuilder extends SimpleSBuilder {
	// Data is inherited
	// See SimpleSBuilder for inherited methods

	// Redefining inherited constructors to get correct type
	public MySBuilder(int capacity) {
		super(capacity); // constructor
	}

	public MySBuilder(String str) {
		super(str); // constructor
	}

	public MySBuilder(char[] str) {
		// Build a new MySBuilder from an array of char
		// Array length should be twice the length of str
		data = new char[str.length * 2]; // creates a new array of twice the
											// size

		for (int i = 0; i < str.length; i++) {
			data[i] = str[i]; // copies the data
		}

		len = str.length; // updates the logical size
	}

	public MySBuilder(MySBuilder old) { // Copy Constructor
		// Copy constructor
		// Array length should be twice the logical size of old
		char[] copyData = new char[old.len * 2];

		for (int i = 0; i < old.len; i++) { // copies the data
			copyData[i] = old.data[i];
		}

		len = old.len; // updating the logical size
		data = copyData;
	}

	// For all append methods, if the additional characters will
	// fit in the array, just add them. If they will not fit, resize
	// the array to twice the logical size of the resulting SBuilder
	// (so it is exactly 1/2 full following the operation)

	public MySBuilder append(String str) {
		// Append str to end of this MySBuilder
		// return this
		append(str.toCharArray()); // calling the char array append method by
									// converting the string to char array

		return this;
	}

	public MySBuilder append(MySBuilder S) {
		// Append S to end of this MySBuilder
		// return this
		if (data.length < len + S.len) { // resizes the data
			resize((len + S.len) * 2);
		}

		for (int i = 0; i < S.len; i++) {
			append(S.data[i]); // calling the char append method
		}

		return this;
	}

	public MySBuilder append(char[] str) {
		// append str to end of this MySBuilder
		// return this
		if (data.length < len + str.length) {
			resize((len + str.length) * 2); // resizes the data
		}

		for (int i = 0; i < str.length; i++) {
			append(str[i]); // calling the char append method
		}

		return this;
	}

	public MySBuilder append(char c) {
		// append c to end of this MySBuilder
		// return this

		if (data.length < len + 1) {
			resize((len + 1) * 2);
		}

		data[len++] = c; // appends c

		return this;
	}

	public MySBuilder delete(int start, int end) {
		// delete characters from start (inclusive) to end (exclusive)
		// from this MySBuilder, shifting to fill in the gap. If range
		// is invalid do nothing.
		// return this

		if (end > len) {
			end = len;
		}

		for (int i = start; i < len; i++) {
			data[i] = data[(end - start) + i]; // shifts the data elements
			data[(end - start) + i] = 0; // deletes the elements
		}

		len = len - (end - start); // updating logical size

		return this;
	}

	public MySBuilder deleteCharAt(int index) {
		// delete char at index from this MySBuilder, shifting to fill in the
		// gap. If index is invalid do nothing
		// return this
		if (index < len) {

			for (int i = index; i < len; i++) { // shifts the data
				data[i] = data[i + 1];
			}

			data[len] = 0; // deletes the char
			len--; // updating the logical size

		}

		return this;
	}

	public int indexOf(String str) {
		// return the beginning index where str matches a substring within
		// this MySBuidler. If there is no match return -1.

		return indexOf(str, 0); // calling indexOf method below
	}

	public int indexOf(String str, int fromIndex) {
		// return the beginning index where str matches a substring within
		// this MySBuilder, starting the search at location fromIndex.
		// If there is no match return -1.
		char[] indexFind = str.toCharArray();

		if (fromIndex < 0 || fromIndex > len) // if fromIndex is not valid then
												// return -1
			return -1;

		for (int i = fromIndex; i <= len - indexFind.length; i++) {

			boolean wordMatched = true;

			for (int j = 0; j < indexFind.length; j++) { // finds the index
				if (data[i + j] != indexFind[j]) {
					wordMatched = false;
					break;
				}
			}
			if (wordMatched) {
				return i;
			}
		}
		return -1;
	}

	public MySBuilder insert(int offset, String str) {
		// insert str into this MySBuilder, beginning at index offset. Shift
		// any existing characters to the right to make space. If offset < 0
		// or offset > len do nothing. If the array must be resized, it should
		// be twice the size of the resulting string.
		// return this
		insert(offset, str.toCharArray()); // calls the insert method below
		return this;
	}

	public MySBuilder insert(int offset, char[] str) {
		// Same as above but with array of char argument
		if (offset >= 0 && offset <= len) {

			if (data.length < len + str.length) {
				resize((len + str.length) * 2); // resizes if the data length is
												// not enough to insert the
												// char[str]
			}

			for (int i = len; i >= offset; i--) {
				data[i + str.length] = data[i]; // shifts the data
			}

			for (int i = offset; i < offset + str.length; i++) { // inserts the
																	// str into
																	// data
				data[i] = str[i - offset];
			}

			len += str.length; // updating logical size
		}
		return this;
	}

	public MySBuilder insert(int offset, char c) {
		// Same as above but with char argument
		if (offset < data.length) {

			char[] singleChar = new char[len];

			for (int i = 0; i < len; i++) {
				singleChar[i] = 0; // filling up the array with 0's as char
									// except last element
			}

			singleChar[len] = c; // assigning the last element of the array the
									// char c
			insert(offset, singleChar);
		}
		return this;
	}

	public MySBuilder insert(int offset, MySBuilder S) {
		// Same as above but with MySBuilder argument
		char[] copyData = new char[S.len];

		for (int i = 0; i < len; i++) {
			copyData[i] = S.data[i]; // makes a copy of the data before
										// inserting
		}

		insert(offset, copyData); // calls the insert method i.e. (int offset,
									// char[] str)
		return this;
	}

	public MySBuilder replace(int start, int end, String str) {
		// Remove the characters from start (inclusive) to end (exclusive)
		// and insert str starting at start. Do not call delete followed
		// by insert, since this will require shifting twice within the
		// array and is very inefficient. You should shift only one time
		// (the direction depends on the relative lengths of the substring
		// removed vs the string inserted). Note that if the lengths of
		// the removed and inserted strings are the same, you should not
		// shift at all.
		// return this

		int diff = end - start;

		int count;

		if (start < 0 && end > len && start >= end) { // this is for invalid
														// values
			return null;
		}

		if (diff > str.length()) { // if the string can fit in the data, execute
									// this

			count = start;

			for (int i = 0; i < str.length(); i++) { // replacing the char
				data[count] = str.charAt(i);
				count++;
			}

			count = 0;

			for (int i = (start + str.length()); i < len; i++) { // shifting
																	// data
				data[i] = data[end + count];
				count++;
			}

			len = len - diff + str.length(); // updating logical size

			return this;

		} else if (diff < str.length()) { // if the string cannot fit in the
											// data, execute this

			if (len - diff + str.length() > capacity()) {
				resize((len + str.length() - diff) * 2); // resize
			}

			for (int i = len - 1; i >= end; i--) {
				data[i + (str.length() - diff)] = data[i]; // shifting data
			}

			count = start;

			for (int i = 0; i < str.length(); i++) {
				data[count] = str.charAt(i); // replacing the char
				count++;
			}

			len = len - diff + str.length();

			return this;

		} else if (diff == str.length()) { // if there are same number of
											// elements, execute this

			for (int i = start; i < end; i++) {
				data[i] = str.charAt(i - start); // replacing the char
			}

			return this;
		}

		return null;
	}

	public String substring(int start, int end) {
		// return a new String which is the substring of this MySBuilder
		// from start (inclusive) to end (exclusive). If the range is
		// invalid return null

		if (start >= 0 && end <= len) {
			int count = 0;

			char[] getData = new char[end - start];

			for (int i = start; i < end; i++) { // gets data from the interval
												// of [start, end)
				getData[count] = data[i];
				count++;
			}

			String finalSubString = new String(getData, 0, count);

			return finalSubString;

		} else
			return null;
	}

	public String[] split(char delim) {
		// Simplified split method returns array of String which the delim
		// character will divide the MySBuilder into. Be careful of special
		// cases where delim occurs in consecutive locations or at the front
		// or end of the MySBuilder. In these cases the extra delim characters
		// should be ignored. See examples in SBuilderTest.java. You MAY NOT
		// use any predefined split() method or variant thereof to do this
		// method.
		// It must be done from scratch on the underlying array of characters.
		// You may use an ArrayList<String> to store your temporary results, and
		// the ArrayList method toArray(T [] a) method to return them. See the
		// ArrayList API for details.

		char[] temp = new char[data.length];

		boolean falseDelim = true;

		int count = 0;

		ArrayList<String> splittedWords = new ArrayList<String>();

		for (int i = 0; i < len; i++) {

			if (data[i] != delim) { // if the char is not a delimiter

				temp[count] = data[i]; // copy the data into array of char
				count++;
				falseDelim = false;

			} else if (!falseDelim) {

				String wordS = new String(temp, 0, count); // make a string out
															// of the char
				splittedWords.add(wordS);
				count = 0;
				falseDelim = true;

			}
		}

		if (count != 0) { // to get rid of the extra delimiters
			String wordS = new String(temp, 0, count);
			splittedWords.add(wordS);
		}

		String[] returnWords = new String[splittedWords.size()];
		return splittedWords.toArray(returnWords);

	}
}