import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

public class aBSTTest {
  @Nested
  @DisplayName("CreateTree")
  class CreateTree {
    @Test
    @DisplayName("Creates tree with size 1")
    void createsTreeWithSize1() {
      aBST tree = new aBST(0);

      Assertions.assertEquals(1, tree.Tree.length);
    }

    @Test
    @DisplayName("Creates tree with size 2")
    void createsTreeWithSize2() {
      aBST tree = new aBST(1);

      Assertions.assertEquals(2, tree.Tree.length);
    }

    @Test
    @DisplayName("Creates tree with size 4")
    void createsTreeWithSize4() {
      aBST tree = new aBST(2);

      Assertions.assertEquals(4, tree.Tree.length);
    }

    @Test
    @DisplayName("Creates tree with size 8")
    void createsTreeWithSize8() {
      aBST tree = new aBST(3);

      Assertions.assertEquals(8, tree.Tree.length);
    }

    @Test
    @DisplayName("Creates tree with size 16")
    void createsTreeWithSize16() {
      aBST tree = new aBST(4);

      Assertions.assertEquals(16, tree.Tree.length);
    }
  }
}
