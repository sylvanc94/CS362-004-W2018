

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
	 //You can use this function to implement your manual testing	 	 
	 String[] schemes = {"http","https", "ftp"};
     UrlValidator urlValidator = new UrlValidator(schemes, UrlValidator.NO_FRAGMENTS);
     String urls[] = {
    		 // All these URLs are passing if using the code from UrlValidatorCorrect
    		 "https://google.com",
    		 "ftp://www.google.com",
    		 "http://www.google.com",
    		 "http://192.168.0.1",
    		 "https://goo.gl/",
    		 "sftp://www.google.com/",
    		 "https://www.invest.google.com/",
    		 "https://100.220.30.140",
    		 "sftp://www.www.net/",
    		 "http://info.info.info.info.info"
     };
     
     for (int i = 0; i < urls.length; i++) {
	    	 if ( urlValidator.isValid(urls[i]) ) {
	    		 System.out.printf("pass: %s", urls[i]);
	    		 System.out.println();
	    	 } else {
	    		 System.out.printf("fail: %s", urls[i]);
	    		 System.out.println();
	    	 }   	 
     }
	   
   }
   
   
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing	

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
