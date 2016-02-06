import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.ListIterator;

public class BigSum {


    public static ArrayList<String> numbers;
    public static long[] total;
    
    public BigSum(){
    	numbers = new ArrayList<String>();
    	total = new long[6];
    }

	/////////////////////////////////////////////////////////////////////////
	///////////////////////////// Main Method ///////////////////////////////
	/////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
		BigSum test = new BigSum();
		int count = 0;
		String temp = null;
		String theSum = "";
		long startTime;
		long endTime;
		long duration;
		long templong = 0L;
		boolean first = true;

		// fill the arraylist
		filler();

		for (int j = 0; j < 6; j++) {
			total[j] += 100000000000L;
		}

		System.out.println(100000000000L);

		//ListIterator<String> nums = numbers.listIterator();
		startTime = System.nanoTime();
		while (numbers.size() > 0) {
	   		bigsum(numbers.remove(0));
	   		count++;
	   		System.out.println(count + " additions");
		}	

		for(int i = 0; i < 6; i++){
				if (first == true) {
					if (total[i] != 100000000000L) {

					temp = String.valueOf(total[i]);
					templong = Long.parseLong(temp);
					templong -= 100000000000L;
					temp = String.valueOf(templong);
					System.out.println(temp + " has " + temp.length() + " digits.");
					theSum += temp.substring(2);
					first = false;
				}
				} else {
					temp = String.valueOf(total[i]);
					System.out.println(temp + " has " + temp.length() + " digits.");
					theSum += temp.substring(2);
				}
		}
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("Execution took " + duration/1000000 + " milliseconds");
		System.out.println("Sum: " + theSum);
		System.out.println("Sum has " + theSum.length() + " digits.");
		System.out.println("First 10 Digits: " + theSum.substring(0,10));
 	}	

	/////////////////////////////////////////////////////////////////////////
	//////////////////////////// Filler Method //////////////////////////////
	/////////////////////////////////////////////////////////////////////////
 	
    public static void filler() {
		String line = null;
		int size = 10;
	
		try {
			Scanner sc = new Scanner(new File("input2.txt"));

   				while(sc.hasNext()) {
	   				line = sc.next();
   					numbers.add(line);
				}
    	}
    	catch (FileNotFoundException e){
    		System.out.print("file not found");
    	}
	}

	/////////////////////////////////////////////////////////////////////////
	//////////////////////////// BigSum Method //////////////////////////////
	/////////////////////////////////////////////////////////////////////////

	public static void bigsum(String toAdd){
		// Used to store segments of numbers for addition
		ArrayList<Long> a = new ArrayList<Long>();
		// Local version of input parameter, for manipulation
		String aString = toAdd;
		// Length of input parameter
		int aLen = toAdd.length();
		// Number of segments needed to do addition
		int aSegs = 0;
		// The size of the segment that is not of size 10, if any exists
		int aMod = 0;
		long segVal = 0;
		long carry = 0;
		int i = 0;
		int j = 0;
		String segString = "";

		String tempstr = "";
		long templong = 0L;
		aMod = aLen%10;
		if (aMod == 0) {
			aSegs = aLen/10;
		}
		else{
			aSegs = aLen/10 + 1;
		}

		for (i = aSegs-1; i >= 0; i--){
			if ((aMod != 0) && (i == (aSegs-1))) {
				//System.out.println(aString.substring(0,mod));
				segVal = Long.parseLong(aString.substring(0,aMod));
				segString = String.valueOf(segVal);
				//System.out.println("non-10 segment to add: " + segVal + " has " + segString.length() + " digits." );
				a.add(segVal);
				if (aString.length() > 10) {
					aString = aString.substring(aMod);
				}
			}
			else if (aString.length() > 10) {
				//System.out.println(aString.substring(0,5));
				segVal = Long.parseLong(aString.substring(0,10));
				segString = String.valueOf(segVal);
				//System.out.println("10 segment to add: " + segVal + " has " + segString.length() + " digits." );
				a.add(segVal);
				aString = aString.substring(10);
			}
			else if (aString.length() != 0) {
				//System.out.println(aString.substring(0,5));
				segVal = Long.parseLong(aString.substring(0,10));
				segString = String.valueOf(segVal);
				//System.out.println("Last 10 segment to add: " + segVal + " has " + segString.length() + " digits." );
				a.add(segVal);
			}
		}

		i = 5;
		j = aSegs - 1;

		//if (j < i){

		while (!a.isEmpty()) {
			System.out.println("Adding: " + a.get(j));
			tempstr = String.valueOf(total[i]);
			templong = Long.parseLong(tempstr);
			if (j != 0) {
				tempstr = tempstr.substring(2);
				templong = Long.parseLong(tempstr);
			}
			total[i] = templong + a.remove(j) + carry + 100000000000L;
			if (( total[i] - 100000000000L) > 9999999999L ) {
				carry = 1;
			}
			else {
				carry = 0;
			}
			i--;
			j--;
		}
	}	

	/////////////////////////////////////////////////////////////////////////
	/////////////////////////// End of Code /////////////////////////////////
	/////////////////////////////////////////////////////////////////////////

}
