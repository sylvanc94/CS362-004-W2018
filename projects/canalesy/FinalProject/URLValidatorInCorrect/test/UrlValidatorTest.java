import junit.framework.TestCase;
import java.util.Scanner;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!

public class UrlValidatorTest extends TestCase {

	public UrlValidatorTest(String testName) {
		super(testName);
	}

	// All done, I also fixed comments to include citation of the piazzaboard thread that clarified manual testing expectations -Tim
	public void testManualTest() {

		/*
		 * Description: > Manual testing allows humans to provide input into a program
		 * to test it. This function allows a user to enter an input, sends the input to
		 * the validator, then alerts the user if the validator deemed the input correct
		 * or not. The user can enter input they know to be valid, and input they know
		 * to be false to gauge the accuracy of the validator's isValid() function.
		 *
		 * Citations: > [1] -
		 * https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html
		 */

		// Print instructions to user.
		System.out.printf("\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
		System.out.println("Manual test session has started");
		System.out.println("Enter a URL into the command line to test isValid() function [Type 'exit' to end session]:");

		// Set up our URL validator
		UrlValidator validator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

		// Declarations
		String input = ""; // Variable to hold user input
		boolean result; // Variable to hold the results of manual test
		int manual = 0; // Select manual mode or pre-defined URL mode

		/* IMPORTANT NOTE: This is our manual test implementation w/ input, usable when manual var = 1. Per lesson 5 of course 
				 materials, human input is involved in manual testing. That said, instructor mentioned manual console input is 
				 *not* required on piazza: https://piazza.com/class/jbk4lmzexg4ce?cid=224 (thread titled: "Project Part B manual testing").
				 We ended up building two test options, one with human input from console and the other no console input/pre-set values. 
		 */
		if (manual == 1) {
			// While loop will repeat until user types the command "exit"
			while (!(input.equals("exit"))) {

				Scanner s = new Scanner(System.in); // See ref [1], read input from System.in
				input = s.next(); // See ref [1], set our "input" variable to console input

				// if the user is not exiting the program...
				if (!(input.equals("exit"))) {
					result = validator.isValid(input); // Call the isValid function and pass in the user input
					if (result == true) {
						System.out.println("isValid() function considered provided URL valid");
					} // Print valid warning
					else {
						System.out.println("isValid() function considered provided URL *NOT* valid");
					} // Print invalid warning
				}

			}
		/* IMPORTANT NOTE: This is our manual test implementation w/ out console input, usable when manual var != 1. Per lesson 5 of course 
				 materials, human input is involved in manual testing. That said, instructor mentioned manual console input is 
				 *not* required on piazza: https://piazza.com/class/jbk4lmzexg4ce?cid=224 (thread titled: "Project Part B manual testing"). As such, 
				 we have implemented the block below - which mimics human console input and uses pre-set values in our function calls. 
		*/	
		} else {
			String urls[] = {
					// All these URLs are passing if using the code from UrlValidatorCorrect
//					"https://www.google.com", // Will crash the program
//					"ftp://www.google.com", // Will crash the program
					"http://www.google.com", 
					"http://192.168.0.1", 
					"http://goo.gl/",
//					"sftp://www.google.com/", // Will crash the program
					"http://www.invest.google.com/", 
					"http://100.220.30.140", 
					"http://www.www.net/",
					"http://info.info.info.info.info" };

			for (int i = 0; i < urls.length; i++) {
				if (validator.isValid(urls[i])) {
					System.out.printf("pass: %s", urls[i]);
					System.out.println();
				} else {
					System.out.printf("fail: %s", urls[i]);
					System.out.println();
				}
			}
		}

		// Alert user Manual Testing has concluded
		System.out.println("Manual test session has ended");
		System.out.printf("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");

	}

	// This is done, please let me know if there are questions/concerns on slack so we can discuss any potential changes as a group -Tim
	public void testYourFirstPartition() {

	 /* Description: 
	  *  > Partition testing involves breaking down data input into segments, then testing 
	  *    both valid and invalid inputs for each respective segment. The goal with this black 
	  *    box testing approach is to simplify the targeting of errors by controlling individual 
	  *    aspects of the input to such an extent that if the software under test returns an error,
	  *    we have a good understanding of which data component may be mis-handled in the underlying 
	  *    program. See reference [1] for additional information. In this partition testing, we will 
	  *    focus on:
	  * 
	  *    - What happens when partitions are omitted from a URL. 
	  *
	  *    The partitions to test are below: 
	  *     
	  *     <UrlScheme> :// <UrlAuthority> : <UrlPort> / <Path> / <?=UrlQuery>
	  *    
	  * Citations: 
	  *  > [1] - https://www.tutorialspoint.com/software_testing_dictionary/equivalence_partitioning_testing.htm
	  *
	  * */  
	   
	   // Print information to user. 
	   System.out.printf("\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
	   System.out.printf("First Partition test session has started.\n");
	   System.out.printf("Testing omitted partitions from http://calendar.oregonstate.edu:80/search/?query=graduation.\n\n");	   
	   
	   // Set up our URL validator
	   UrlValidator validator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);	
	   
	   // Create a URL array strand of partitions for valid URL: 'http://calendar.oregonstate.edu:80/search/?query=graduation' 
	   // (tester can plug URL into browser to verify validity)	   
	   String validPartitions[] = {
			   "http://",
			   "calendar.oregonstate.edu",
			   ":80",
			   "/search",
			   "/?query=graduation"   
       };	   
	   
		/* PARTITION TEST ONE:
	    *   > Description: Test all partitions together. If this fails, then we know there is an error with one or more 
	    *     partitions - as we know provided URL is a valid. The subsequent partition tests will help identify which segment 
	    *     is responsible for error.
	    *    
	    *   > Tests:  
	    *  		1. Test full URL - http://calendar.oregonstate.edu:80/search/?query=graduation
	    */ 
	
		 // Variable declarations
		 String URL = "";
		 boolean result;
		 int errorCounter = 0; 
		 
		 // Test all partitions combined 
		 URL = validPartitions[0] + validPartitions[1] + validPartitions[2] + validPartitions[3] + validPartitions[4]; // Build full URL
		 result = validator.isValid(URL);   // Call the isValid function and pass in the URL

		 //increment error counter if appropriate 
		 if(result == false) {
			 errorCounter++;
		 }
		 
		 // Print Results 
		 System.out.printf("Partition Test: Evaluating all partitions combined together\n");
		 System.out.printf("test: '%s' | expected result: true, isValid() returned: %s \n\n", URL, result); // Print logging to user
	
		 /* PARITION TEST TWO:  
		  *  > Description: The isValid() function may not be adequately handling one or more of the URL partitions. Removing one or more 
		  *    partitions may result in the isValid() function incorrectly thinking the partitioned URL is valid or invalid. 
		  *    Our second testing technique is to take our URL, and remove individual components. We will omit partitions (or sets of partitions) to
		  *    find which ones aren't being handled by isValid() correctly. We can review printed logging to identify potential errors.  
		  *      
		  *  > Test Structure: If we just remove a single partition at a time, we may not be able to identify errors. Consider a case where 
		  *    there are two partitions being mis-handled by inValid(). Let's say a valid port and a valid query are both not recognized. If we 
		  *    take the full URL and remove the port, it will fail (on account of the query). If we take the full URL and remove the query, it will
		  *    fail (on account of the port). If we remove both, isValid() may then recognize the URL as correct. As such, we need to consider 
		  *    multiple combinations of the URL. Just as this test suite attempts to find occurances where isValid() is failing to recognize a valid 
		  *    URL, we need to be sure we can identify when isValid() is not recognizing a valid URL. Therefore, we need to also introduce 
		  *    invalid URL's (such as removing partitions like authority). 
		  *    
		  *  > Tests: 
		  *    A map of the partition sets this function tests is below:   
		  *    
		  *  	1. Initial test, Remove Schema: <UrlAuthority> <UrlPort> <Path> <?=UrlQuery>       | expect: true  (scheme not mandatory)                  
		  *  	   		i.   Sub-test, remove auth:         <UrlPort> <Path> <?=UrlQuery>		   | expect: false (we always need auth) 
		  *  	   		ii.	 Sub-test, remove port:         <UrlAuthority> <Path> <?=UrlQuery>     | expect: true  (port not mandatory) 
		  *  			iii. Sub-test, remove Path:		    <UrlAuthority> <UrlPort> <?=UrlQuery>  | expect: true  (don't need path) 
		  *  			iv.  Sub-test, remove UrlQuery:     <UrlAuthority> <UrlPort> <Path>        | expect: true  (don't need query)
		  *     Note: Testers, go to 'calendar.oregonstate.edu:80/about/?query=graduation'. This is why we expect true for no scheme. 
		  *  
		  *  	2. Initial test, Remove Authority: <UrlScheme> <UrlPort> <Path> <?=UrlQuery>       | expect: false (we always need auth) 
		  *  	   		i.   Sub-test, remove scheme:       <UrlPort> <Path> <?=UrlQuery>          | expect: false (we always need auth) 
		  *  	   		ii.	 Sub-test, remove port:         <UrlScheme> <Path> <?=UrlQuery>        | expect: false (we always need auth) 
		  *  			iii. Sub-test, remove Path:         <UrlScheme> <UrlPort> <?=UrlQuery>     | expect: false (we always need auth) 
		  *  			iv.  Sub-test, remove UrlQuery:     <UrlScheme> <UrlPort> <Path>           | expect: false (we always need auth) 
		  *     Note: Testers, go to 'http://:80/about/?query=graduation'. This is why we expect false for no scheme. 
		  *  
		  *  	3. Initial test, Remove Port: <UrlScheme> <UrlAuthority> <Path> <?=UrlQuery>       | expect: true  (dont need port)
		  *  	   		i.   Sub-test, remove scheme:       <UrlAuthority> <Path> <?=UrlQuery>     | expect: true  (scheme not mandatory) 
		  *  	   		ii.	 Sub-test, remove authority:    <UrlScheme> <Path> <?=UrlQuery>        | expect: false (always need auth)   
		  *  			iii. Sub-test, remove Path:         <UrlScheme> <UrlAuthority> <?=UrlQuery>| expect: true  (don't need path if query)   
		  *  			iv.  Sub-test, remove UrlQuery:     <UrlScheme> <UrlAuthority> <Path>      | expect: true  (dont need query)
		  *     Note: Testers, go to 'https://calendar.oregonstate.edu/about/?query=graduation'. This is why we expect true for no port. 
		  *  
		  *  	4. Initial test, Remove Path: <UrlScheme> <UrlAuthority> <UrlPort> <?=UrlQuery>    | expect: true  (dont need path)
		  *  			i.    Sub-test, remove scheme:      <UrlAuthority> <UrlPort> <?=UrlQuery>  | expect: true  (scheme not mandatory) 
		  *  	   		ii.   Sub-test, remove auth:        <UrlScheme> <UrlPort> <?=UrlQuery>     | expect: false (always need auth)
		  *  	   		iii.  Sub-test, remove port:        <UrlScheme> <UrlAuthority> <?=UrlQuery>| expect: true  (dont need port/path)
		  *  			iv.   Sub-test, remove UrlQuery:    <UrlScheme> <UrlAuthority> <UrlPort>   | expect: true  (dont need query)
		  * 	Note: Testers, go to http://calendar.oregonstate.edu:80/?query=graduation. This is why we expect true for no path. 
		  *  
		  *  	5. Initial test, Remove Query: <UrlScheme> <UrlAuthority> <UrlPort> <Path>         | expect: true  (dont need query)
		  *  	   		i.   Sub-test, remove scheme:       <UrlAuthority> <UrlPort> <Path>        | expect: true  (scheme not mandatory) 
		  *  	   		ii.	 Sub-test, remove auth:         <UrlScheme> <UrlPort> <Path>           | expect: false (always need auth)
		  *  			iii. Sub-test, remove port:         <UrlScheme> <UrlAuthority> <Path>      | expect: true  (dont need port) 
		  *  			iv.  Sub-test, remove path:         <UrlScheme> <UrlAuthority> <UrlPort>   | expect: true  (dont need path) 
		  * 	Note: Testers, go to http://calendar.oregonstate.edu:80/about/. This is why we expect true for no query.
		  *   
		  *  Note: We could break this down further to additional sub-tests if we wanted. For the purpose of this implementation, 
		  *        this should be thorough enough. 
		  */
		 
		 System.out.printf("Partition Test: Removing partition(s) from valid URL and capturing results: \n");			 

		 // Variable declarations	 
		 String initialTestString = "";
		 String subTestString = "";
		 int resultIndex;
		 
		 // 2D Array to store our expected bool results - see the "expect" descriptions in comment above for where these values are coming from
		 boolean[][] expectedResults = new boolean[][]{
			  {true, false, true, true, true},     // here are our five scheme results, initial results in index 0 and then four subs results i - iv in indices 1-4
			  {false, false, false, false, false}, // here are our five authority results (same syntax as scheme - values and descriptions listed in above comments)
			  {true, true, false, true, true},     // so on and so forth...
			  {true, true, false, true, true},
			  {true, true, false, true, true},
			};
		 
		 // Perform all tests that omit partition(s) 
		 for(int i = 0; i < 5; i++) {
			 
			 // perform our initial test, where we only remove a single partition
			 initialTestString = _removePartition(URL, validPartitions[i]);	                       // Remove a partition from our string
			 result = validator.isValid(initialTestString);                                        // Call the isValid function and pass in the URL
			 System.out.printf("test: '%s' | expected result: %s, isValid() returned: %s \n",      // Print logging to user
					           initialTestString, expectedResults[i][0] ,result); 
			 
			 // increment errors as needed
			 if(result != expectedResults[i][0]) {
				 errorCounter++;
			 }
			 
			// Reset our result index for inner loop
			 resultIndex = 1; 
			 
			 // perform all our sub-tests, where we remove more partitions
			 for(int j = 0; j < 5; j++) {
				 
				 // if we aren't on index i (i.e. the current partition we have omitted in outer loop), then...
				 if(j != i) {	
					 subTestString = _removePartition(initialTestString, validPartitions[j]);          // Call our function to obtain sub-string of initialTestString 
					 result = validator.isValid(subTestString);                                        // Call the isValid function and pass in the URL
					 System.out.printf("test: '%s' | expected result: %s, isValid() returned: %s \n",  // Print logging to user
							 		   subTestString, expectedResults[i][resultIndex], result); 
					 
					 // increment errors as needed
					 if(result != expectedResults[i][resultIndex]) {
						 errorCounter++;
					 }
					 
					 //increment resultIndex.
					 resultIndex++; 
					 
			 	 }	 					  
			 }
		 } 	 
		
	  // Display Summary 
	  System.out.printf("\nSummary:\n");
	  System.out.printf("  > Partition tester found %d errors (i.e. actual output was not equal expected output).\n", errorCounter);
	  System.out.printf("  > Note: Copying and pasting every URL above with expected result 'true' will take you to a valid webpage. \n\n");	  
		 
	  // Display testing has ended to console 	 
	  System.out.printf("First Partition test session has ended.\n");	
	  System.out.printf("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
	 
   }
   
   // Helper function for testYourFirstPartition() - This removes a partition from a provided string and returns it
   public String _removePartition(String URL, String Partition)
   { 
	   // Remove the partition from the provided URL
	   URL = URL.replace(Partition,"");
	   
	   //return our result 
	   return URL;
   }

	public void testYourSecondPartition() {

       // Display testing has started to console
	   System.out.printf("\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
	   System.out.printf("Second Partition test session has started.\n");





	    // You can use this function to implement your Second Partition testing
		//...code here. 





	  // Display testing has ended to console 	 
	  System.out.printf("Second Partition test session has ended.\n");	
	  System.out.printf("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");

	}
	// You need to create more test cases for your Partitions if you need to

	public void testIsValid() {

       // Display testing has started to console
	   System.out.printf("\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
	   System.out.printf("Programming based test session has started.\n");





		// You can use this function for programming based testing
		//...code here. 




	  // Display testing has ended to console 	 
	  System.out.printf("Programming based test session has ended.\n");	
	  System.out.printf("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
	}

}
