import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

class FakeaBST {
  public static aBST buildTree() {
    aBST tree = new aBST(4);

    tree.Tree[0] = 50;
    tree.Tree[1] = 25;
    tree.Tree[2] = 75;
    tree.Tree[3] = null;
    tree.Tree[4] = 37;
    tree.Tree[5] = 62;
    tree.Tree[6] = 84;
    tree.Tree[7] = null;
    tree.Tree[8] = null;
    tree.Tree[9] = 31;
    tree.Tree[10] = 43;
    tree.Tree[11] = 55;
    tree.Tree[12] = null;
    tree.Tree[13] = null;
    tree.Tree[14] = 92;

    return tree;
  }
}

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
    @DisplayName("Creates tree with size 3")
    void createsTreeWithSize3() {
      aBST tree = new aBST(1);

      Assertions.assertEquals(3, tree.Tree.length);
    }

    @Test
    @DisplayName("Creates tree with size 7")
    void createsTreeWithSize7() {
      aBST tree = new aBST(2);

      Assertions.assertEquals(7, tree.Tree.length);
    }

    @Test
    @DisplayName("Creates tree with size 15")
    void createsTreeWithSize15() {
      aBST tree = new aBST(3);

      Assertions.assertEquals(15, tree.Tree.length);
    }

    @Test
    @DisplayName("Creates tree with size 31")
    void createsTreeWithSize31() {
      aBST tree = new aBST(4);

      Assertions.assertEquals(31, tree.Tree.length);
    }

    @Test
    @DisplayName("Creates tree with size 63")
    void createsTreeWithSize63() {
      aBST tree = new aBST(5);

      Assertions.assertEquals(63, tree.Tree.length);
    }
  }

  // @Nested
  // @DisplayName("FindKeyIndex")
  // class FindKeyIndex {
  //   @Test
  //   @DisplayName("Creates tree with size 16")
  //   void createsTreeWithSize16() {

  //   }
  // }
}
