import java.util.*;

public class EnDe {
  public static String encrypt(String input) {
    // Declaring Variables
    int ascii, e1 = 0, l, num2;
    String encrypted = "", encrypting = "";
    char c;

    // Getting inputs length
    l = input.length();
    // For Loop to start encrypting
    for (int i = 0; i < l; i++) {
      // Gets the character at position i
      c = input.charAt(i);
      // Check if it is A-Z
      if (Checks.isAlphabet(Character.toString(c)) == false) {
        // If not then it will start the encryption method
        // Getting the ascii value for the character
        ascii = (int)c;
        //num1 = i + 1;
        // Checking if it is uppercase or lowercase
        if (Checks.isUppercase(Character.toString(c)) == true) {
          // Num 2 is the character position multiplied by 3
            num2 = i * 3;
          // Do While Loop
          do {
            // Its checked whether or not it is a prime number
            if (Checks.isPrime(num2) == false) {
              num2 = num2 + 1;
            }
          }
          while (Checks.isPrime(num2) == false);
          e1 = ascii + num2;
          encrypting = "à" + Integer.toHexString(e1);
        }
        else {
          // Num 2 is the character position multiplied by 3
          num2 = i * 2;
          do {
            if (Checks.isPrime(num2) == false) {
              num2 = num2 + 1;
            }
          }
          while (Checks.isPrime(num2) == false);
          e1 = ascii - num2;
          encrypting = "é" + Integer.toHexString(e1);
        }
        encrypted = encrypted + encrypting;
      }
      else {
        // Otherwise it will add it back as its original value
        encrypted = encrypted + "î" + c;
      }
    }
    return encrypted;
  }

  public static String decrypt(String input) {
    // Declaring Variables
    int ascii, d1 = 0, l, num1 = 0, num2, listLength = 0;
    List<Integer> pos = new ArrayList<>();
    String decrypted = "", ds;
    char c;

    // Getting inputs length
    l = input.length();
    // Adding positions to an array
    for (int i = 0; i < l; i++) {
      // If Statement to add to the arrays depending on the characters
      if (input.charAt(i) == 'à' || input.charAt(i) == 'é' || input.charAt(i) == 'î') {
        pos.add(i);
      }
    }
    pos.add(l);
    listLength = pos.size();
    for (int i = 0; i < listLength - 1; i++) {
      // Check if it is a special character or not
      ds = input.substring(pos.get(num1), pos.get(num1 + 1));
      //if the string first character is the french a it will know it is a capital letter
      if (ds.charAt(0) == 'à') {
        ds = ds.replaceAll("à", "");
        ascii = Integer.parseInt(ds, 16);
        num2 = i * 3;
        do {
          // Its checked whether or not it is a prime number
          if (Checks.isPrime(num2) == false) {
            num2 = num2 + 1;
          }
        }
        while (Checks.isPrime(num2) == false);
        // Alters the ascii and change the character
        d1 = ascii - num2;
        c = (char)d1;
        decrypted = decrypted + c;
      }
      else if (ds.charAt(0) == 'é') {
        ds = ds.replaceAll("é", "");
        ascii = Integer.parseInt(ds, 16);
        // Num 2 is the character position multiplied by 3
        num2 = i * 2;
        do {
          if (Checks.isPrime(num2) == false) {
            num2 = num2 + 1;
          }
        }
        while (Checks.isPrime(num2) == false);
        // Making ascii alterations
        d1 = ascii + num2;
        c = (char)d1;
        decrypted = decrypted + c;
      }
      else {
        // Knows it is a special character and will not change it
        ds = ds.replaceAll("î", "");
        c = ds.charAt(0);
        decrypted = decrypted + c;
      }
      num1 = num1 + 1;
    }
    
    return decrypted;
  }
}