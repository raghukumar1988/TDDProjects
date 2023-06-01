package org.sapient.udemy2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ISBNValidatorTest {

    @Test
    //void checkValidISBN() {
    void check10DigitValidISBN() {
        //fail(); // always add fail() in new test methods to make sure this method is executed
        ISBNValidator isbnValidator = new ISBNValidator();
        boolean result = isbnValidator.validateISBN("1913330001");
        assertTrue(result,"First value");

        // Instead of adding 3rd test case we 2nd assertion in same test method because
        //       we just want to test the same functionality but with different value.

        //We need to take a design decision to change the data type of ISBN because
        //      some of valid ISBN starts with 0 and this cant be adopted with int.

        //result = isbnValidator.validateISBN(0545010225);  // old Int was replaced in all places
        result = isbnValidator.validateISBN("0545010225");
        assertTrue(result,"Second value.."); // we add message bcos we have 2 assertions.

    }

    @Test
    //void checkInValidISBN() {
    void check10DigitInValidISBN() {
        //fail();
        ISBNValidator isbnValidator = new ISBNValidator();
        boolean result = isbnValidator.validateISBN("1913330000");
        assertFalse(result);
    }

    // A design decision was taken to throw an exception if the ISBN is 9 digits
    @Test
    @DisplayName("Nine digits ISBNs are not allowed and should throw NumberFormatException..")
    void nineDigitISBNsAreNotAllowed() {
        //fail();
        ISBNValidator isbnValidator = new ISBNValidator();
        assertThrows(NumberFormatException.class,()->isbnValidator.validateISBN("123456789"));
    }

    @Test
    @DisplayName("Alphabets in ISBNs are not allowed and should throw NumberFormatException..")
    void isbnWithAlphabetsAreNotAllowed() {
        //fail();
        ISBNValidator isbnValidator = new ISBNValidator();
        assertThrows(NumberFormatException.class,()->isbnValidator.validateISBN("HelloWorld"));
    }

    @Test
    @DisplayName(" ISBNs ending with character X are valid..")
    void isbnEndingWithXAreValid() {
        //fail();
        ISBNValidator isbnValidator = new ISBNValidator();
        boolean result = isbnValidator.validateISBN("012000030X");
        assertTrue(result);
    }


    // During this step just refactor the test names by adding 10 and 13 to differentiate
    @Test
    void validate13DigitISBNNumber() {
        //fail();
        ISBNValidator isbnValidator = new ISBNValidator();
        boolean result = isbnValidator.validateISBN("9780525572091");
        assertTrue(result,"Value 1");

         result = isbnValidator.validateISBN("9781492645962");
        assertTrue(result,"Value 2");
    }

    @Test
    void check13DigitInvalidISBN() {
        //fail();
        ISBNValidator isbnValidator = new ISBNValidator();
        boolean result = isbnValidator.validateISBN("9780679805273");
        assertFalse(result);
    }
}