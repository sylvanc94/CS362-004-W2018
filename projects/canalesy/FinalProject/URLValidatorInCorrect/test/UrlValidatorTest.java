import junit.framework.TestCase;
import java.util.Scanner; 

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!


public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   // This function is completely done, but feel free to adjust as desired -Tim
   public void testManualTest()
   {

	   /* Description: 
	    *  > Manual testing allows humans to provide input into a program to test it. This 
	    * 	 function allows a user to enter an input, sends the input to the validator, 
	    *    then alerts the user if the validator deemed the input correct or not. The user 
	    *    can enter input they know to be valid, and input they know to be false to 
	    *    gauge the accuracy of the validator's isValid() function. 
	    * 
	    * Citations: 
	    *  > [1] - https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html
	    */

	   // Print instructions to user. 
	   System.out.println("Manual test session has started");	   
	   System.out.println("Enter a URL into the command line to test isValid() function [Type 'exit' to end session]:");
	   
	   // Set up our URL validator
	   UrlValidator validator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);	   
	   
	   // Declarations
	   String input = ""; // Variable to hold user input
	   boolean result;    // Variable to hold the results of manual test
	   
	   // While loop will repeat until user types the command "exit" 
	   while(!(input.equals("exit"))) {
		
		  Scanner s = new Scanner(System.in);    // See ref [1], read input from System.in
		  input = s.next();                      // See ref [1], set our "input" variable to console input
		  
		  //if the user is not exiting the program...
		  if(!(input.equals("exit"))) {
			  result = validator.isValid(input); // Call the isValid function and pass in the user input
			  if(result == true) {System.out.println("isValid() function considered provided URL valid");} // Print valid warning 
			  else {System.out.println("isValid() function considered provided URL *NOT* valid");}         // Print invalid warning
		  }
		  
	   }
	   
	   // Alert user Manual Testing has concluded
	   System.out.println("Manual test session has ended");
	
   }
   
   // I started on this one but will come back to it -Tim 
   public void testYourFirstPartition()
   {

	 /* Description: 
	  *  > Partition testing involves breaking down data input into segments, then testing 
	  *    both valid and invalid inputs for each respective segment. The goal with this black 
	  *    box testing approach is to simplify the targeting of errors by controlling individual 
	  *    aspects of the input to such an extent that if the software under test returns an error,
	  *    we have a good understanding of which data component may be mis-handled in the underlying 
	  *    program. See reference [1] for additional information. The partitions to test are below: 
	  *     
	  *     <testUrlScheme> :// <testUrlAuthority> : <testUrlPort> / <testPath> / <?=testUrlQuery>
	  *    
	  * Citations: 
	  *  > [1] - https://www.tutorialspoint.com/software_testing_dictionary/equivalence_partitioning_testing.htm
	  *
	  * */  
	   
	   // Print information to user. 
	   System.out.println("First Partition test session has started");	   
	   
	   // Set up our URL validator
	   UrlValidator validator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);	
	   
	   // Provide a URL we know to be true, ensure the isValid function also recognizes this URL to be true
	   assertTrue(validator.isValid("http://www.google.com")); 
	   System.out.println("isValid() recognizes http://www.google.com as valid ");
	   
	   // ... test each valid and invalid component here, manipulating 1 and testing after each run. 
	   
	   
   }
   
   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing	   

   }
   //You need to create more test cases for your Partitions if you need to 
   
   public void testIsValid()
   {
		//You can use this function for programming based testing   
   }
   

}
