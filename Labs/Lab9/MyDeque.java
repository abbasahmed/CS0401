
// CS 401 Fall 2016
// MyDeque class to implement a simple deque
// Complete the methods indicated.  Be careful about the
// special cases indicated.  See lab for details on how
// to implement the methods.

public class MyDeque implements SimpleDeque {
	Object[] theData;
	int numItems;

	public MyDeque(int maxItems) {
		theData = new Object[maxItems];
		numItems = 0;
		// numItems = maxItems;
	}

	public boolean isEmpty() {
		return (numItems == 0);
	}

	public void addFront(Object X) {

		if (theData.length == numItems)
			return;

		else {
			for (int i = numItems - 1 ; i >= 0 ; i--) {
				theData[i+1] = theData[i];
			}

			theData[0] = X;
			numItems++;
		}
		// Add new item at front of list (shifting old items
		// to right first). If the list is full, do not add
		// the item (just do nothing).
	}

	public void addRear(Object X) {

		if (theData.length == numItems)
			return;
		else {
			theData[numItems] = X;
			numItems++;
		}
		// Add new item at rear of list. If the list is full,
		// do not add the item (just do nothing).
	}

	public Object removeFront() {

		if (numItems == 0) {
			return null;
		} else {
			Object front = theData[0];
			theData[0] = 0;
			for (int i = 0; i < numItems; i++) {
				theData[i] = theData[i + 1];
			}
			numItems--;

			return front;
		}
		// Remove and return front item from list, shifting remaining
		// items to the left to fill the spot. If list is empty,
		// return null.
	}

	public Object removeRear() {
		if (numItems == 0) {
			return null;
		} else {
			Object last = theData[numItems - 1];
			theData[numItems - 1] = null;
			numItems--;
			return last;
		}
		// Remove and return rear item from list. If list is empty,
		// return null.
	}
}