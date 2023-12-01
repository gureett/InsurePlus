public class Sorting {
  public static void quickSort(double array[], int aMin, int aMax) {
    int rightPos, leftPos;
    double pivot;
    if (aMin > aMax) {
      return;
    }
    pivot = array[aMin];
    leftPos = aMin;
    rightPos = aMax;
    do
    {
      while (pivot > array[leftPos])
      {
        leftPos++;
      }
      while (pivot < array[rightPos])
      {
        rightPos--;
      }
      if (leftPos <= rightPos)
      {
        if (array[leftPos] != array[rightPos])
        {
          swap(array, leftPos, rightPos); 
        }
        leftPos++;
        rightPos--;
      }
    }
      while (leftPos < rightPos);
    quickSort(array, aMin, rightPos);
    quickSort(array, leftPos, aMax);
  }
  public static void swap (double[] array, int first, int second) {
    double hold;
    hold = array[first];
    array[first] = array[second];
    array[second] = hold;
  }
  public static void swapString (String[] array, int first, int second) {
    String hold;
    hold = array[first];
    array[first] = array[second];
    array[second] = hold;
  }
  public static void quickSort2(double[] price,String[] name, int aMin, int aMax) {
    int rightPos, leftPos;
    double pivot;
    if (aMin > aMax) {
      return;
    }
    pivot = price[aMin];
    leftPos = aMin;
    rightPos = aMax;
    do
    {
      while (pivot > price[leftPos])
      {
        leftPos++;
      }
      while (pivot < price[rightPos])
      {
        rightPos--;
      }
      if (leftPos <= rightPos)
      {
        if (price[leftPos] != price[rightPos])
        {
          swap(price, leftPos, rightPos); 
          swapString(name, leftPos, rightPos);
        }
        leftPos++;
        rightPos--;
      }
    }
      while (leftPos < rightPos);
    quickSort2(price, name, aMin, rightPos);
    quickSort2(price, name, leftPos, aMax);
  }
}