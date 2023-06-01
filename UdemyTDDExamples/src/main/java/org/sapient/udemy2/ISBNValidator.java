package org.sapient.udemy2;

/* Always write minimum code to make the test pass
 Don't think about design architecture while developing using TDD */
public class ISBNValidator {

    // Red
/*    public boolean validateISBN(int i) {
        return false;
    }*/

    // Green
    /*public boolean validateISBN(int i) {
        return true;
    }*/

    // refactor and error for next test
    /*public boolean validateISBN(int isbn) { // changed variable name
        return true;
    }*/

    //Green
    /*public boolean validateISBN(int isbn) {
        if (isbn == 1913330001) {
            return true;
        }
        return false;
    }*/

    //Refactor
/*    public boolean validateISBN(int isbn) {
        return isbn == 1913330001;
    }*/

    // isbn type changed re-run the tests
 /*   public boolean validateISBN(String  isbn) {
        return isbn.equals("1913330001");
    }*/

    /*****    1  9  1 3  3  3  0 0 0 1  - digits of ISBN
     10 9  8 7  6  5  4 3 2 1  - multiplers
     10 81 8 21 18 15 0 0 0 1  - result
     154 - sum of results
     154%11 ==0 This is valid ISBN  */

    // Green for 2nd assertion of test 1
    /*public boolean validateISBN(String isbn) {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += isbn.charAt(i) * (10 - i);
        }
        if (sum % 11 == 0) {
            return true;
        }
        return false;
    }*/

    // refactor
    /*public boolean validateISBN(String isbn) {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += isbn.charAt(i) * (10 - i);
        }
        return sum % 11 == 0;
    }*/

    //Green and red
  /*public boolean validateISBN(String isbn) {
      // Added Apache commons 3 for StringUtils
      if(isbn.length()!=10 ){
          throw new NumberFormatException("ISBN should not be less than 10 digits..");
      }
        // Alternate solution- may be used for refactor
      //if(!StringUtils.isNumeric(isbn)){
          //throw new NumberFormatException("ISBN should not be contain alphabets..");}

      int sum = 0;
      for (int i = 0; i < 10; i++) {
          if(!Character.isDigit(isbn.charAt(i))){
              throw new NumberFormatException("ISBN should not be contain alphabets..");
          }
          sum += isbn.charAt(i) * (10 - i);
      }
      return sum % 11 == 0;
  }*/

    //Green and Red
    /*public boolean validateISBN(String isbn) {
        // Added Apache commons 3 for StringUtils
        if (isbn.length() != 10) {
            throw new NumberFormatException("ISBN should not be less than 10 digits..");
        }
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                if (i == 9 && isbn.charAt(i) == 'X') {
                    //its ok
                    sum += 10; // adding for X
                } else {
                    throw new NumberFormatException("ISBN should not be contain alphabets..");
                }
            }
            sum += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
        }
        return sum % 11 == 0;
    }*/

    //fails for check13DigitInvalidISBN
    /*public boolean validateISBN(String isbn) {
        if(isbn.length()==13){
            return true;
        }
        if (isbn.length() != 10) {
            throw new NumberFormatException("ISBN should not be less than 10 digits..");
        }
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                if (i == 9 && isbn.charAt(i) == 'X') {
                    //its ok
                    sum += 10; // adding for X
                } else {
                    throw new NumberFormatException("ISBN should not be contain alphabets..");
                }
            }
            sum += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
        }
        return sum % 11 == 0;
    }*/

    /* Logic to validate 13 digit ISBN
            1.Multiply each numbers with 1 and 3 alternatively
            2. Sum the results
            3. if the sum is divisible by 10 then it is valid one.   */
    // passed test case  - next is re-factor
   /* public boolean validateISBN(String isbn) {
        if (isbn.length() == 13) {
            int sum = 0;
            for (int i = 0; i < 13; i++) {
                if (i % 2 == 0) {
                    sum += Character.getNumericValue(isbn.charAt(i));
                } else {
                    sum += Character.getNumericValue(isbn.charAt(i)) * 3;
                }
            }
            if (sum % 10 == 0) {  // candidate for refactor
                return true;
            } else {
                return false;
            }
        }
        if (isbn.length() != 10) {
            throw new NumberFormatException("ISBN should not be less than 10 digits..");
        }
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                if (i == 9 && isbn.charAt(i) == 'X') {
                    //it's ok
                    sum += 10; // adding for X
                } else {
                    throw new NumberFormatException("ISBN should not be contain alphabets..");
                }
            }
            sum += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
        }
        return sum % 11 == 0;
    }*/

    // refactored code
    // Refactor the length  with constants
    // re-run the tests and make sure that it is working
    public boolean validateISBN(String isbn) {
        if (isbn.length() == 13) { // 13 can be extracted to constant with Ctrl+Alt+C
            return validateLongISBN(isbn); // extracted to method
        } else if (isbn.length() == 10) {
            return validateShortISBN(isbn); // extracted to method
        }
        throw new NumberFormatException("ISBN should not be less than 10 digits..");
    }

    private static boolean validateShortISBN(String isbn) {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                if (i == 9 && isbn.charAt(i) == 'X') {
                    //it's ok
                    sum += 10; // adding for X
                } else {
                    throw new NumberFormatException("ISBN should not be contain alphabets..");
                }
            }
            sum += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
        }
        return sum % 11 == 0;
    }

    private static boolean validateLongISBN(String isbn) {
        int sum = 0;
        for (int i = 0; i < 13; i++) {
            if (i % 2 == 0) {
                sum += Character.getNumericValue(isbn.charAt(i));
            } else {
                sum += Character.getNumericValue(isbn.charAt(i)) * 3;
            }
        }
        return sum % 10 == 0;  //refactored
    }
}
