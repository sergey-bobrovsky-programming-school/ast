import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

public class AlgorithmsDataStructures2Test {
  @Test
  @DisplayName("Generates balanced tree.")
  void createsTreeWithSize1() {
    int[] values = new int[]{ 3, 2, 4, 1, 0 };
    int[] expectedValues = new int[]{ 2, 1, 3, 0, null, null, 1 };

    Assertions.assertEquals(expectedValues, values);
  }
}
