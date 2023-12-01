// Importing Libraries
import java.io.*;

public class FindBestDeal {
  public static String[] findBest(String file, String selectedPrice, String[] options) {
    // Try Catch for IO Exception
    try {
      // Imports the File into File Reader
      BufferedReader input = new BufferedReader(new FileReader(file));
      // Finding the length of how long the array should be
      int arrayLength = Integer.parseInt(input.readLine());

      // Declaring Arrays
      String[] name = new String[arrayLength];
      String[] nameAndTotal = new String[arrayLength];
      double[] total = new double[arrayLength];
      double[] biP = new double[arrayLength];
      double[] pdP = new double[arrayLength];
      double[] dcP = new double[arrayLength];
      double[] abP = new double[arrayLength];
      double[] umP = new double[arrayLength];
      double[] lwdP = new double[arrayLength];
      double[] colP = new double[arrayLength];
      double[] comP = new double[arrayLength];
      double[] gtsP = new double[arrayLength];
      double[] rgcP = new double[arrayLength];
      double[] afP = new double[arrayLength];
      
      // Importing Data from File into Arrays
      for (int i = 0; i < arrayLength; i++) {
        String[] split = input.readLine().split(",");
        name[i] = split[0];
        biP[i] = Double.parseDouble(split[2]);
        pdP[i] = Double.parseDouble(split[4]);
        dcP[i] = Double.parseDouble(split[6]);
        abP[i] = Double.parseDouble(split[8]);
        umP[i] = Double.parseDouble(split[10]);
        lwdP[i] = Double.parseDouble(split[12]);
        colP[i] = Double.parseDouble(split[14]);
        comP[i] = Double.parseDouble(split[16]);
        gtsP[i] = Double.parseDouble(split[18]);
        rgcP[i] = Double.parseDouble(split[20]);
        afP[i] = Double.parseDouble(split[22]);
      }

      // Finding Total Price for all Insurance Options with the Options selected
      for (int i = 0; i < arrayLength; i++) {
        // Adding all of the mandatory coverages
        total[i] = biP[i] + pdP[i] + dcP[i] + abP[i] + umP[i];
        // Checking which options were selected and adding them to the total
        for (int j = 0; j < options.length; j++) {
          if (options[j].equals("LWD")) {
            total[i] = total[i] + lwdP[i];
          }
          else if (options[j].equals("COL")) {
            total[i] = total[i] + colP[i];
          }
          else if (options[j].equals("COM")) {
            total[i] = total[i] + comP[i];
          }
          else if (options[j].equals("GTS")) {
            total[i] = total[i] + gtsP[i];
          }
          else if (options[j].equals("RGC")) {
            total[i] = total[i] + rgcP[i];
          }
          else if (options[j].equals("ACC")) {
            total[i] = total[i] + afP[i];
          }
        }
        // Adding total to the Insurance to be used to find later
        nameAndTotal[i] = name[i] + total[i];
      }
      

      // Splitting the Selected Price to send over to the Search Method
      int searchKey1, searchKey2;
      // Gets Rid of all Special Characters except dash
      String text = selectedPrice.replaceAll("[^a-zA-Z0-9_-]","");
      try {
        String[] split = text.split("-");
        searchKey1 = Integer.parseInt(split[0]);
        searchKey2 = Integer.parseInt(split[1]);
      }
      catch(Exception error) {
        searchKey1 = Integer.parseInt(text);
        searchKey2 = -1;
      }

      // Searches for the insurance in the user given range
      Sorting.quickSort(total, 0, total.length - 1);
      double[] sortedTotal = total;
      int[] found = Searching.search(sortedTotal, searchKey1, searchKey2);

      //Checking the results of the search
      if (found[0] == -1) {
        String[] returnArray = {"invalid"};
        return returnArray;
      }
      else if (found.length > 1) {
        // Create New Array with the amount of Found
        double[] newTotal = new double[found.length - 1];
        // Giving the new total its values
        int ctr = 0;
        for (int i = (int)found[0]; i < found.length; i++) {
          newTotal[ctr] = sortedTotal[i];
          ctr = ctr + 1;
        }
        // Sorts the new array from lowest to highest
        Sorting.quickSort(newTotal, 0, newTotal.length - 1);
        // Finds the index for all of the other arrays
        int index = Searching.findInsurer(nameAndTotal, newTotal[0]);
        // Resets Variable and Puts all the prices and information into one array to send back
        ctr = 0;
        int length = 7 + options.length;
        String[] returnArray = new String[length];
        // For Loop for giving the values in the return array of the multiple parallel arrays
        for (int i = 0; i < length; i++) {
          if (i == 0) {
            returnArray[i] = name[index];
          }
          else if (i == 1) {
            returnArray[i] = Double.toString(biP[index]);
          }
          else if (i == 2) {
            returnArray[i] = Double.toString(pdP[index]);
          }
          else if (i == 3) {
            returnArray[i] = Double.toString(dcP[index]);
          }
          else if (i == 4) {
            returnArray[i] = Double.toString(abP[index]);
          }
          else if (i == 5) {
            returnArray[i] = Double.toString(umP[index]);
          }
          else {
            // Checks for the customs options
            if (options[ctr].equals("LWD")) {
              returnArray[i] = Double.toString(lwdP[index]);
            }
            else if (options[ctr].equals("COL")) {
              returnArray[i] = Double.toString(colP[index]);
            }
            else if (options[ctr].equals("COM")) {
              returnArray[i] = Double.toString(comP[index]);
            }
            else if (options[ctr].equals("GTS")) {
              returnArray[i] = Double.toString(gtsP[index]);
            }
            else if (options[ctr].equals("RGC")) {
              returnArray[i] = Double.toString(rgcP[index]);
            }
            else if (options[ctr].equals("ACC")) {
              returnArray[i] = Double.toString(afP[index]);
              ctr = -1;
            }
            ctr = ctr + 1;
          }
          // Giving the total to the final return array
          returnArray[returnArray.length - 1] = Double.toString(newTotal[0]);
        }
        return returnArray;
      }
      else {
        // Finds the index for all of the other arrays
        int index = found[0];
        // Resets Variable and Puts all the prices and information into one array to send back
        int ctr = 0;
        int length = 7 + options.length;
        String[] returnArray = new String[length];
        // For Loop for giving the values in the return array of the multiple parallel arrays
        for (int i = 0; i < length - 1; i++) {
          if (i == 0) {
            returnArray[i] = name[index];
          }
          else if (i == 1) {
            returnArray[i] = Double.toString(biP[index]);
          }
          else if (i == 2) {
            returnArray[i] = Double.toString(pdP[index]);
          }
          else if (i == 3) {
            returnArray[i] = Double.toString(dcP[index]);
          }
          else if (i == 4) {
            returnArray[i] = Double.toString(abP[index]);
          }
          else if (i == 5) {
            returnArray[i] = Double.toString(umP[index]);
          }
          else {
            if (options[ctr].equals("LWD")) {
              returnArray[i] = Double.toString(lwdP[index]);
            }
            else if (options[ctr].equals("COL")) {
              returnArray[i] = Double.toString(colP[index]);
            }
            else if (options[ctr].equals("COM")) {
              returnArray[i] = Double.toString(comP[index]);
            }
            else if (options[ctr].equals("GTS")) {
              returnArray[i] = Double.toString(gtsP[index]);
            }
            else if (options[ctr].equals("RGC")) {
              returnArray[i] = Double.toString(rgcP[index]);
            }
            else if (options[ctr].equals("ACC")) {
              returnArray[i] = Double.toString(afP[index]);
              ctr = -1;
            }
            ctr = ctr + 1;
          }
          returnArray[returnArray.length - 1] = Double.toString(sortedTotal[found[0]]);
        }
        return returnArray;
      }
    }
    catch(IOException e) {
      
    }
    String[] returnArray = {"invalid"};
    return returnArray;
  }
}