import junit.framework.TestCase;
import java.util.*;

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

	// This is done, please let me know if there are questions/concerns so we can discuss any potential changes as a group -Tim
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
	  *     <UrlScheme> :// <UrlAuthority> : <UrlPort> / <Path> / <?=UrlQuery>
	  *    
	  * Citations: 
	  *  > [1] - https://www.tutorialspoint.com/software_testing_dictionary/equivalence_partitioning_testing.htm
	  *
	  * */  
	   
	   // Print information to user. 
	   System.out.printf("\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
	   System.out.printf("Partition test session has started.\n");
	   System.out.printf("Omitting partition sets from http://calendar.oregonstate.edu:80/search/?query=graduation.\n\n");	   
	   
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
		  *  	1. Initial test, Remove Schema: <UrlAuthority> <UrlPort> <Path> <?=UrlQuery>       | expect: false  (scheme mandatory)                  
		  *  	   		i.   Sub-test, remove auth:         <UrlPort> <Path> <?=UrlQuery>		   | expect: false  (scheme mandatory) 
		  *  	   		ii.	 Sub-test, remove port:         <UrlAuthority> <Path> <?=UrlQuery>     | expect: false  (scheme mandatory) 
		  *  			iii. Sub-test, remove Path:		    <UrlAuthority> <UrlPort> <?=UrlQuery>  | expect: false  (scheme mandatory)
		  *  			iv.  Sub-test, remove UrlQuery:     <UrlAuthority> <UrlPort> <Path>        | expect: false  (scheme mandatory)
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
		  *  	   		i.   Sub-test, remove scheme:       <UrlAuthority> <Path> <?=UrlQuery>     | expect: false (scheme mandatory)
		  *  	   		ii.	 Sub-test, remove authority:    <UrlScheme> <Path> <?=UrlQuery>        | expect: false (always need auth)   
		  *  			iii. Sub-test, remove Path:         <UrlScheme> <UrlAuthority> <?=UrlQuery>| expect: true  (don't need path if query)   
		  *  			iv.  Sub-test, remove UrlQuery:     <UrlScheme> <UrlAuthority> <Path>      | expect: true  (dont need query)
		  *     Note: Testers, go to 'https://calendar.oregonstate.edu/about/?query=graduation'. This is why we expect true for no port. 
		  *  
		  *  	4. Initial test, Remove Path: <UrlScheme> <UrlAuthority> <UrlPort> <?=UrlQuery>    | expect: true  (dont need path)
		  *  			i.    Sub-test, remove scheme:      <UrlAuthority> <UrlPort> <?=UrlQuery>  | expect: false (scheme mandatory)
		  *  	   		ii.   Sub-test, remove auth:        <UrlScheme> <UrlPort> <?=UrlQuery>     | expect: false (always need auth)
		  *  	   		iii.  Sub-test, remove port:        <UrlScheme> <UrlAuthority> <?=UrlQuery>| expect: true  (dont need port/path)
		  *  			iv.   Sub-test, remove UrlQuery:    <UrlScheme> <UrlAuthority> <UrlPort>   | expect: true  (dont need query)
		  * 	Note: Testers, go to http://calendar.oregonstate.edu:80/?query=graduation. This is why we expect true for no path. 
		  *  
		  *  	5. Initial test, Remove Query: <UrlScheme> <UrlAuthority> <UrlPort> <Path>         | expect: true  (dont need query)
		  *  	   		i.   Sub-test, remove scheme:       <UrlAuthority> <UrlPort> <Path>        | expect: false (scheme mandatory)
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
			  {false, false, false, false, false},     // here are our five scheme results, initial results in index 0 and then four subs results i - iv in indices 1-4
			  {false, false, false, false, false},     // here are our five authority results (same syntax as scheme - values and descriptions listed in above comments)
			  {true, false, false, true, true},        // so on and so forth...
			  {true, false, false, true, true},
			  {true, false, false, true, true},
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
	  System.out.printf("Partition test session has ended.\n");	
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

	// This is done, please let me know if there are questions/concerns so we can discuss any potential changes as a group -Tim
	public void testYourSecondPartition() {

	 /* Description: 
	  *  > In 'testYourFirstPartition()', we focused on what happens when certain code partitions were *omitted*. In this 
	  *    partition testing, we will focus on: 
	  *    
      *    - What happens when bad partitions are introduced into the URL
	  *    
	  *    The partitions are below: 
	  *    <UrlScheme> :// <UrlAuthority> : <UrlPort> / <Path> / <?=UrlQuery>
	  */  	   
	   
	   // Print information to user. 
	   System.out.printf("\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
	   System.out.printf("Partition test session has started.\n");
	   System.out.printf("Inserting bad partitions into http://calendar.oregonstate.edu:80/search/?query=graduation\n\n");	   
	   
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
	   
	   // Create a URL array strand of invalid partitions to insert into valid URL 'http://calendar.oregonstate.edu:80/search/?query=graduation'
	   String invalidPartitions[] = {
		   		"bad:/",
		   		"calendar.orgstate.gov",
		   		":122",
		   		"/foo",
		   		"/?query=bar" 		
	   };
	    
	    /*  
	    * PARITION TEST - Incorrect Partitions:  
	    * We will now take this valid URL, and insert bad components one by one.                                
	    * 1. Test full w/ bad schema: bad:/calendar.oregonstate.edu:80/search/?query=graduation 
	    * 2. Test full w/ bad authority: http://calendar.orgstate.gov:80/search/?query=graduation 
	    * 3. Test full w/ bad port: http://calendar.oregonstate.edu:122/search/?query=graduation 
	    * 4. Test full w/ bad path: http://calendar.oregonstate.edu:80/foo/?query=graduation 
	    * 5. Test full w/ bad query: http://calendar.oregonstate.edu:80/search/?query=bar                 
	    * Notes: We expect *ALL* tests to come back as InValid URL, if any are successful then we know there is a problem!     
	    */ 
	  
	     // Variable declarations
	     String URL = "";   	// Declare URL string 
		 boolean result;    	// Variable to hold the results of manual test
		 int errorCounter = 0;  // Variable to track errors 
		   
		 // 1. Test full w/ bad schema: bad:/calendar.oregonstate.edu:80/search/?query=graduation 
		 URL = invalidPartitions[0] + validPartitions[1] + validPartitions[2] + validPartitions[3] + validPartitions[4]; // Build full URL
		 result = validator.isValid(URL);                                                                                // Call the isValid function and pass in the URL
		 System.out.printf("test: '%s' | expected result: %s, isValid() returned: %s \n", URL, false ,result); 	         // Print logging to user   
		 if(result != false) {errorCounter++;}																			 // increment errors	 
		 
		 // 2. Test full w/ bad authority: http://calendar.orgstate.gov:80/search/?query=graduation  
		 URL = validPartitions[0] + invalidPartitions[1] + validPartitions[2] + validPartitions[3] + validPartitions[4]; // Build full URL
		 result = validator.isValid(URL);                                                                                // Call the isValid function and pass in the URL
	     System.out.printf("test: '%s' | expected result: %s, isValid() returned: %s \n", URL, false ,result); 	         // Print logging to user   
         if(result != false) {errorCounter++;}																			 // increment errors
		 
		 // 3. Test full w/ bad port: http://calendar.oregonstate.edu:122/search/?query=graduation 
		 URL = validPartitions[0] + validPartitions[1] + invalidPartitions[2] + validPartitions[3] + validPartitions[4]; // Build full URL
	     result = validator.isValid(URL);                                                                                // Call the isValid function and pass in the URL
	     System.out.printf("test: '%s' | expected result: %s, isValid() returned: %s \n", URL, false ,result); 	         // Print logging to user   
	     if(result != false) {errorCounter++;}																			 // increment errors
		 
		 // 4. Test full w/ bad path: http://calendar.oregonstate.edu:80/foo/?query=graduation 
		 URL = validPartitions[0] + validPartitions[1] + validPartitions[2] + invalidPartitions[3] + validPartitions[4]; // Build full URL
		 result = validator.isValid(URL);                                                                                // Call the isValid function and pass in the URL
		 System.out.printf("test: '%s' | expected result: %s, isValid() returned: %s \n", URL, false ,result); 	         // Print logging to user   
		 if(result != false) {errorCounter++;}																			 // increment errors
		 
		 // 5. Test full w/ bad query: http://calendar.oregonstate.edu:80/search/?query=bar   
		 URL = validPartitions[0] + validPartitions[1] + validPartitions[2] + validPartitions[3] + invalidPartitions[4]; // Build full URL
	     result = validator.isValid(URL);                                                                                // Call the isValid function and pass in the URL
	     System.out.printf("test: '%s' | expected result: %s, isValid() returned: %s \n", URL, false ,result); 	         // Print logging to user   
	     if(result != false) {errorCounter++;}																			 // increment errors		 
		 
		  // Display Summary 
		  System.out.printf("\nSummary:\n");
	      System.out.printf("  > Partition tester found %d errors (i.e. actual output was not equal expected output).\n", errorCounter);
		  System.out.printf("  > Note: Copying and pasting every URL above with expected result 'false' will fail to take you to a valid webpage. \n\n");	  
			 
		  // Display testing has ended to console 	 
		  System.out.printf("Partition test session has ended.\n");	
		  System.out.printf("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");		 

	}
	// You need to create more test cases for your Partitions if you need to

   // This is done, please let me know if there are questions/concerns so we can discuss any potential changes as a group -Tim
   public void testYourThirdPartition()
   { 

		 /* Description: 
		  *  > In 'testYourSecondPartition()', we focused on what happens when certain code partitions were *bad*. In this 
		  *    partition testing, we will focus on: 
		  *    
	      *    - What happens in the boundary cases, that is input that is very large and input that is nothing at all
	      *    
	      *  Citations: 
	      *  > [1] - http://archive.oreilly.com/pub/post/the_worlds_longest_domain_name.html  
	      *    
		  */  	   
	
		 // Print information to user. 
		 System.out.printf("\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
		 System.out.printf("Partition test session has started.\n");
		 System.out.printf("Testing boundary cases for isValid() by using empty and inordinately long partitions \n\n");	

		 // Set up our URL validator
		 UrlValidator validator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);	
		   		 
	     // Variable declarations
	     String URL = "";   	// Declare URL string 
		 boolean result;    	// Variable to hold the results of manual test
		 int errorCounter = 0;  // Variable to track errors 
	   
		 // Boundary case 1: No input at all
		 URL = "";  																									 // Pass empty string 
	     result = validator.isValid(URL);                                                                                // Call the isValid function and pass in the URL
	     System.out.printf("test: '%s' (emtpy) | expected result: %s, isValid() returned: %s \n", URL, false ,result); 	 // Print logging to user   
	     if(result != false) {errorCounter++;}		
	   
		 // Boundary case 2: Inordinately long, valid URL
		 URL = "http://thelongestlistofthelongeststuffatthelongestdomainnameatlonglast.com/wearejustdoingthistobestupidnows"
		 		+ "incethiscangoonforeverandeverandeverbutitstilllookskindaneatinthebrowsereventhoughitsabigwasteoftimeande"
		 		+ "nergyandhasnorealpointbutwehadtodoitanyways.html";  													 // Pass long string
	     result = validator.isValid(URL);                                                                                // Call the isValid function and pass in the URL
	     System.out.printf("test: '%s' | expected result: %s, isValid() returned: %s \n", URL, true ,result); 			 // Print logging to user   
	     if(result != true) {errorCounter++;}		     
	     
		  // Display Summary 
		  System.out.printf("\nSummary:\n");
	      System.out.printf("  > Partition tester found %d errors (i.e. actual output was not equal expected output).\n", errorCounter);
		  System.out.printf("  > Note: Copying and pasting every URL above with expected result 'true' will take you to a valid webpage. \n\n");	  
			 
		  // Display testing has ended to console 	 
		  System.out.printf("Partition test session has ended.\n");	
		  System.out.printf("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");		   
   }   

	public void testIsValid() {

       // Display testing has started to console
	   System.out.printf("\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
	   System.out.printf("Programming based test session has started.\n");





		// You can use this function for programming based testing
		//...code here. 
	   boolean result;
	   UrlValidator validator;
	   int runs = 0;
	   int failures = 0;

	   // Unit Test #1 - valid components, DEFAULT_SCHEMES

	   /* Description:
		* First unit test matches every combination of valid URL component into a URL string and
		* passes it to isValid(), checking the result against 'true' since these are all valid components.
		* This test case uses the DEFAULT_SCHEMES option of the UrlValidator object by passing (null, null, 0L)
		* as parameters.
		*/

		// As it turns out, using DEFAULT_SCHEMES causes every test case to fail, whether it's using http, https, or ftp.

	   validator = new UrlValidator(null, null, 0L);
	   System.out.println();
	   System.out.println("Testing all valid components with new UrlValidator(null, null, 0L) DEFAULT_SCHEMES");
	   
	   // Test all valid URL components. Each of these iterations should return TRUE from isValid().
	   // If there are bugs, then some (or all) of these will return FALSE.
	   for (int schemeIdx = 0; schemeIdx < validScheme.length; schemeIdx++) {
	   		String scheme = validScheme[schemeIdx];

	   		for (int separatorIdx = 0; separatorIdx < validAuthoritySeparator.length; separatorIdx++) {
	   			String separator = validAuthoritySeparator[separatorIdx];

	   			for (int authorityIdx = 0; authorityIdx < validAuthority.length; authorityIdx++) {
	   				String authority = validAuthority[authorityIdx];

	   				for (int portIdx = 0; portIdx < validPort.length; portIdx++) {
	   					String port = validPort[portIdx];

	   					for (int pathIdx = 0; pathIdx < validPath.length; pathIdx++) {
	   						String path = validPath[pathIdx];

	   						for (int queryIdx = 0; queryIdx < validQuery.length; queryIdx++) {
	   							String query = validQuery[queryIdx];

	   							StringBuilder urlBuilder = new StringBuilder();
	   							urlBuilder.append(scheme);
	   							urlBuilder.append(separator);
	   							urlBuilder.append(authority);
	   							urlBuilder.append(port);
	   							urlBuilder.append(path);
	   							urlBuilder.append(query);
	   							
	   							String url = urlBuilder.toString();
	   							result = validator.isValid(url);
	   							runs++;
	   							if (result == false) {
	   								//System.out.println("Test FAILED with URL: " + url);
	   								failures++;
	   							} else {
	   								//System.out.println("Test PASS with URL:   " + url);
	   							}
	   						}
	   					}
	   				}
	   			}
	   		}
	   }

	   System.out.printf("Runs: %d, failures: %d\n", runs, failures);	


	   runs = 0;
	   failures = 0;


		// Unit Test #2 - valid components, ALLOW_ALL_SCHEMES

	   /* Description:
		* Second unit test matches every combination of valid URL component into a URL string and
		* passes it to isValid(), checking the result against 'true' since these are all valid components.
		* This test case uses the ALLOW_ALL_SCHEMES option of the UrlValidator object by passing
		* UrlValidator.ALLOW_ALL_SCHEME as the only parameter to the constructor.
		*/

	   System.out.println();
	   System.out.println("Testing valid components with new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES)");
	   validator = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);

	   // Test all valid URL components. Each of these iterations should return TRUE from isValid().
	   // If there are bugs, then some (or all) of these will return FALSE.
	   for (int schemeIdx = 0; schemeIdx < validScheme.length; schemeIdx++) {
	   		String scheme = validScheme[schemeIdx];
	   		
	   		for (int separatorIdx = 0; separatorIdx < validAuthoritySeparator.length; separatorIdx++) {
	   			String separator = validAuthoritySeparator[separatorIdx];
	   			
	   			for (int authorityIdx = 0; authorityIdx < validAuthority.length; authorityIdx++) {
	   				String authority = validAuthority[authorityIdx];
	   				
	   				for (int portIdx = 0; portIdx < validPort.length; portIdx++) {
	   					String port = validPort[portIdx];
	   					
	   					for (int pathIdx = 0; pathIdx < validPath.length; pathIdx++) {
	   						String path = validPath[pathIdx];
	   						
	   						for (int queryIdx = 0; queryIdx < validQuery.length; queryIdx++) {
	   							String query = validQuery[queryIdx];

	   							StringBuilder urlBuilder = new StringBuilder();
	   							urlBuilder.append(scheme);
	   							urlBuilder.append(separator);
	   							urlBuilder.append(authority);
	   							urlBuilder.append(port);
	   							urlBuilder.append(path);
	   							urlBuilder.append(query);
	   							
	   							// With UrlValidator.ALLOW_ALL_SCHEMES, only http works. The other schemes cause the program
	   							// to crash therefore preventing the tests from continuing. There appears to be a bug in the
	   							// regex validator when using UrlValidator.ALLOW_ALL_SCHEMES. Each crashing case is skipped
	   							// over so the program can continue running, but it is still counted as a failure in the final count.
	   							runs++;
	   							failures++;
	   							if (scheme.equals("http")) {
	   								String url = urlBuilder.toString();
		   							result = validator.isValid(url);
		   							
		   							if (result == false) {
		   								//System.out.println("Test FAILED with URL: " + url);
		   								
		   							} else {
		   								//System.out.println("Test PASS with URL:   " + url);
		   								failures--;
		   							}
	   							}
	   						}
	   					}
	   				}
	   			}
	   		}
	   }

	   System.out.printf("Runs: %d, failures: %d\n", runs, failures);	


	   runs = 0;
	   failures = 0;


	   // Unit Test #3 - all components, ALLOW_ALL_SCHEMES and DEFAULT_SCHEMES

	   /* Description:
		* Third unit test matches every combination of valid and invalid URL component into a URL string and
		* passes it to isValid(), checking the result against the expected 'true'/'false' value.
		* If the URL contains just one invalid component, the entire URL is expected to evaluate to 'false'.
		* This test uses a RNG as a way to generate all the possible combinations of valid and invalid URL parts.
		* It's not really a 'random test' because it just generates an exhaustive list of all possible URLs from
		* the component parts, it was just easier to do it using a RNG rather than iterating through each of the 
		* array indeces.
		*/

		/* Outputs:
		 * This test outputs to the console each URL component that was involved in the failure, and the number of
		 * times that part was in a failing test case. It might be useful to shed light on where the problem areas
		 * within the code might be found.
		 */

	   System.out.println();
	   System.out.println("Random test - valid and invalid - all possible combinations");

	   UrlValidator validators[] = {new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES), new UrlValidator(null, null, 0L)};
		
		Random rand = new Random();

		int valIdx;
		int schemeIdx;
		int separatorIdx;
		int authorityIdx;
		int portIdx;
		int pathIdx;
		int queryIdx;

		UrlValidator urlVal;
		String scheme;
		String separator;
		String authority;
		String port;
		String path;
		String query;

		int counter = 700000;	// Aproximately the minimum number of iterations required to hit all the combinations (44,010)
		boolean expected;

		// Store each unique URL so we don't count the same URL in the failure counts
		Set<String> urlSet = new HashSet<String>();

		// Hash maps will store the occurrence of each item in a test failure
		Map<String,Integer> httpMap = new HashMap<String,Integer>();
		Map<String,Integer> valMap = new HashMap<String,Integer>();
		Map<String,Integer> schemeMap = new HashMap<String,Integer>();
		Map<String,Integer> sepMap = new HashMap<String,Integer>();
		Map<String,Integer> authorityMap = new HashMap<String,Integer>();
		Map<String,Integer> portMap = new HashMap<String,Integer>();
		Map<String,Integer> pathMap = new HashMap<String,Integer>();
		Map<String,Integer> queryMap = new HashMap<String,Integer>();

		while (counter-- > 0) {
			// Generate all the new strings for the next iteration
			expected = true;
			valIdx = rand.nextInt(2);
			urlVal = validators[valIdx];

			// Pick the 'scheme' string
			schemeIdx = rand.nextInt(validScheme.length + invalidScheme.length);
			if (schemeIdx < validScheme.length) {
				scheme = validScheme[schemeIdx];
			} else {
				scheme = invalidScheme[schemeIdx - validScheme.length];
				expected = false;
			}
			
			// Pick the 'separator' string
			separatorIdx = rand.nextInt(validAuthoritySeparator.length + invalidAuthoritySeparator.length);
			if (separatorIdx < validAuthoritySeparator.length) {
				separator = validAuthoritySeparator[separatorIdx];
			} else {
				separator = invalidAuthoritySeparator[separatorIdx - validAuthoritySeparator.length];
				expected = false;
			}

			// Pick the 'authority' string
			authorityIdx = rand.nextInt(validAuthority.length + invalidAuthority.length);
			if (authorityIdx < validAuthority.length) {
				authority = validAuthority[authorityIdx];
			} else {
				authority = invalidAuthority[authorityIdx - validAuthority.length];
				expected = false;
			}

			// Pick the 'port' string
			portIdx = rand.nextInt(validPort.length + invalidPort.length);
			if (portIdx < validPort.length) {
				port = validPort[portIdx];
			} else {
				port = invalidPort[portIdx - validPort.length];
				expected = false;
			}

			// Pick the 'pathIdx' string
			pathIdx = rand.nextInt(validPath.length + invalidPath.length);
			if (pathIdx < validPath.length) {
				path = validPath[pathIdx];
			} else {
				path = invalidPath[pathIdx - validPath.length];
				expected = false;
			}

			// Pick the 'query' string
			queryIdx = rand.nextInt(validQuery.length);
			query = validQuery[queryIdx];

			// build the URL string
			StringBuilder urlBuilder = new StringBuilder();
			urlBuilder.append(scheme);
			urlBuilder.append(separator);
			urlBuilder.append(authority);
			urlBuilder.append(port);
			urlBuilder.append(path);
			urlBuilder.append(query);
			String url = urlBuilder.toString();

			// If this URL hasn't been evaluated before, add it to the set of evaluated URLs and check for failures
			if (!urlSet.contains(url)) {
				urlSet.add(url);
				boolean failed = false;
				
				// valIdx == 0 (ALLOW_ALL_SCHEMES) and scheme either 'https' or 'ftp' cause the program to halt execution.
				// So these are hard-coded as failures each time they come up without having to call isValid()
				if (valIdx == 0 && (scheme.equals("https") || scheme.equals("ftp"))) {
					failures++;
					if (httpMap.containsKey("ALLOW_ALL_SCHEMES + https/ftp")) {
						int curVal = httpMap.get("ALLOW_ALL_SCHEMES + https/ftp");
						httpMap.put("ALLOW_ALL_SCHEMES + https/ftp", curVal + 1);
					} else {
						httpMap.put("ALLOW_ALL_SCHEMES + https/ftp", 1);
					}
				}
				else {
					result = urlVal.isValid(url);
					if (result != expected) {
						failed = true;
					}
				}

				if (failed) {
					failures++;
					if (valIdx == 1) {
						if (valMap.containsKey("DEFAULT_SCHEMES")) {
							int curVal = valMap.get("DEFAULT_SCHEMES");
							valMap.put("DEFAULT_SCHEMES", curVal + 1);
						} else {
							valMap.put("DEFAULT_SCHEMES", 1);
						}
					} else {
						if (valMap.containsKey("ALLOW_ALL_SCHEMES")) {
							int curVal = valMap.get("ALLOW_ALL_SCHEMES");
							valMap.put("ALLOW_ALL_SCHEMES", curVal + 1);
						} else {
							valMap.put("ALLOW_ALL_SCHEMES", 1);
						}
					}

					if (schemeMap.containsKey(scheme)) {
						int curVal = schemeMap.get(scheme);
						schemeMap.put(scheme, curVal + 1);
					} else {
						schemeMap.put(scheme, 1);
					}

					if (sepMap.containsKey(separator)) {
						int curVal = sepMap.get(separator);
						sepMap.put(separator, curVal + 1);
					} else {
						sepMap.put(separator, 1);
					}

					if (authorityMap.containsKey(authority)) {
						int curVal = authorityMap.get(authority);
						authorityMap.put(authority, curVal + 1);
					} else {
						authorityMap.put(authority, 1);
					}

					if (portMap.containsKey(port)) {
						int curVal = portMap.get(port);
						portMap.put(port, curVal + 1);
					} else {
						portMap.put(port, 1);
					}

					if (pathMap.containsKey(path)) {
						int curVal = pathMap.get(path);
						pathMap.put(path, curVal + 1);
					} else {
						pathMap.put(path, 1);
					}

					if (queryMap.containsKey(query)) {
						int curVal = queryMap.get(query);
						queryMap.put(query, curVal + 1);
					} else {
						queryMap.put(query, 1);
					}
				}
				runs++;
			}
			
		}
		
		// Output the failure results to the console

		System.out.printf("Runs: %d, failures: %d\n", runs, failures);
		System.out.println();
		System.out.println("Failure counts:");

		System.out.println();
		System.out.println("Special case:");
		for (Map.Entry m:httpMap.entrySet()) {
			System.out.println("\"" + m.getKey() + "\" " + m.getValue());
		}
		System.out.println();
		System.out.println("Validator type:");
		for (Map.Entry m:valMap.entrySet()) {
			System.out.println("\"" + m.getKey() + "\" " + m.getValue());
		}
		System.out.println();
		System.out.println("Scheme:");
		for (Map.Entry m:schemeMap.entrySet()) {
			System.out.println("\"" + m.getKey() + "\" " + m.getValue());
		}
		System.out.println();
		System.out.println("Separator:");
		for (Map.Entry m:sepMap.entrySet()) {
			System.out.println("\"" + m.getKey() + "\" " + m.getValue());
		}
		System.out.println();
		System.out.println("Authority:");
		for (Map.Entry m:authorityMap.entrySet()) {
			System.out.println("\"" + m.getKey() + "\" " + m.getValue());
		}
		System.out.println();
		System.out.println("Port:");
		for (Map.Entry m:portMap.entrySet()) {
			System.out.println("\"" + m.getKey() + "\" " + m.getValue());
		}
		System.out.println();
		System.out.println("Path:");
		for (Map.Entry m:pathMap.entrySet()) {
			System.out.println("\"" + m.getKey() + "\" " + m.getValue());
		}
		System.out.println();
		System.out.println("Query:");
		for (Map.Entry m:queryMap.entrySet()) {
			System.out.println("\"" + m.getKey() + "\" " + m.getValue());
		}



	  // Display testing has ended to console 	 
	  System.out.printf("Programming based test session has ended.\n");	
	  System.out.printf("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
	}
	
	// Some test components
		String validScheme[] = { "http", "ftp", "https" };
		String invalidScheme[] = { "3", "" };
		
		String validAuthoritySeparator[] = { "://" };
		String invalidAuthoritySeparator[] = { ":/", ":", "/", "//"};
		
		String validAuthority[] = { "www.google.com", "google.com", "google.co", "0.0.0.0", "255.255.255.255" };
		String invalidAuthority[] = { "google.comm", "google.c", "google.", "google", "-1.-1.-1.-1", "256.256.256.256", "google.g0c", ".", "" };
		
		String validPort[] = { "", ":80", ":65535" };
		String invalidPort[] = { ":", ":b", ":-1", ":65535" };

		String validPath[] = { "", "/", "/slash", "/2slashes/" };
		String invalidPath[] = { "//", "noslash", "slash/" };

		String validQuery[] = { "", "?test=true", "?test=true&param2=true" };

}
