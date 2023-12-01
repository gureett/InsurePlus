public class Checks {
  public static Boolean isAlphabet(String letter) {
    // Checks if letter matches anything between a-z or A-Z
    if (letter.matches("[^a-zA-Z]")) {
      return true;
    }
    else {
      return false;
    }
  }
  public static Boolean isSpecial(String letter) {
    // checks if the character entered is a special character or a letter.
    if (letter.matches("[^a-zA-Z0-9]")) {
      return false;
    }
    else {
      return true;
    }
  }
  public static Boolean isUppercase(String letter) {
    // Checks if letter matches A-Z
    if (letter.matches("[^a-z]")) {
      return true;
    }
    else {
      return false;
    }
  }
  public static Boolean isPrime(int n) {
    // Check if the number is less than or 1
    if (n <= 1) {
      return false;
    }
    //Checks if the number is 2
    if (n == 2) {
      return true;
    }
    // Check if it is a multiple of 2
    else if (n % 2 == 0) {
      return false;
    }
    // If not, then just check the odds
    for (int i = 3; i <= Math.sqrt(n); i += 2) {
      if (n % i == 0)
        return false;
    }
    return true;
  }
}