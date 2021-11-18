import java.util.*;

public class AlgorithmsDataStructures2 {
  public static int[] GenerateBBSTArray(int[] a) {
    Arrays.sort(a);

    int length = a.length;
    int rootIndex = (int) Math.floor((length - 1) / 2);

    int root = a[rootIndex];
    // int[] leftChild = GenerateBBSTArray(Arrays.copyOfRange(a, 0, rootIndex));
    // int[] rightChild = GenerateBBSTArray(Arrays.copyOfRange(a, 0, rootIndex));;

    int[] result = new int[]{ 0 };

    return result;
  }
}