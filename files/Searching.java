public class Searching {
    public static int findInsurer(String[] array, double searchKey) {
    // Declaring Variables
    int low = 0;
    int high = array.length - 1;
    int middle;

    while (low <= high) {
      middle = (high+low) / 2;
      if(array[middle].contains(Double.toString(searchKey))) {
        return middle;
      }
      else if (Double.parseDouble(array[middle].replaceAll("[^0-9.]", "")) > searchKey) {
        high = middle - 1;
      }
      else {
        low = middle + 1;
      }
    }
    return -1;
  }

  public static int[] search(double[] total, int sk1, int sk2) {
    // Declaring variables
    int low = 0;
    int high = total.length - 1;
    int middle;
    int ctr = 0;

    if (sk2 == -1) {
      while (low <= high) {
        middle = (high+low) / 2;
        if(total[middle] > sk1) {
          ctr = ctr + 1;
          low = middle + 1;
        }
        else if (total[middle] < sk1) {
          high = middle - 1;
        }
        else {
          low = middle + 1;
        }
      }
      // Check Counters Value
      if (ctr == 0) {
        int[] notFound = {-1};
        return notFound;
      }
      // Resetting Variable Values
      high = total.length -1;
      low = 0;
      int[] multiple = new int [ctr];
      multiple[0] = -1;
      ctr = -1;
      // Binary Search
      while (low <= high) {
        middle = (high+low) / 2;
        if (total[middle] > sk1) {
          ctr = ctr + 1;
          multiple[ctr] = middle;
          low = middle + 1;
        }
        else if (total[middle] < sk1) {
          high = middle - 1;
        }
        else {
          low = middle + 1;
        }
      }
      return multiple;
    }
    else {
      // First Binary Search to find how big to make the array
      while (low <= high) {
        middle = (high+low) / 2;
        if (total[middle] > sk1 && total[middle] <= sk2) {
          ctr = ctr + 1;
          low = middle + 1;
        }
        else if (total[middle] > sk1) {
          high = middle - 1;
        }
        else {
          low = middle + 1;
        }
      }
      // Check Counters Value
      if (ctr == 0) {
        int[] notFound = {-1};
        return notFound;
      }
      // Resetting Variable Values
      high = total.length -1;
      low = 0;
      int[] multiple = new int [ctr];
      multiple[0] = -1;
      ctr = -1;
      // Binary Search
      while (low <= high) {
        middle = (high+low) / 2;
        if (total[middle] >= sk1 && total[middle] <= sk2) {
          ctr = ctr + 1;
          multiple[ctr] = middle;
          low = middle + 1;
        }
        else if (total[middle] > sk1) {
          high = middle - 1;
        }
        else {
          low = middle + 1;
        }
      }
      return multiple;
    }
  }
}