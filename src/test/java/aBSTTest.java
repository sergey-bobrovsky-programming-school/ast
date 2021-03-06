import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

class FakeaBST {
  public static aBST buildTree() {
    aBST tree = new aBST(3);

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
    @DisplayName("Creates tree with size 1.")
    void createsTreeWithSize1() {
      aBST tree = new aBST(0);

      Assertions.assertEquals(1, tree.Tree.length);
    }

    @Test
    @DisplayName("Creates tree with size 3.")
    void createsTreeWithSize3() {
      aBST tree = new aBST(1);

      Assertions.assertEquals(3, tree.Tree.length);
    }

    @Test
    @DisplayName("Creates tree with size 7.")
    void createsTreeWithSize7() {
      aBST tree = new aBST(2);

      Assertions.assertEquals(7, tree.Tree.length);
    }

    @Test
    @DisplayName("Creates tree with size 15.")
    void createsTreeWithSize15() {
      aBST tree = new aBST(3);

      Assertions.assertEquals(15, tree.Tree.length);
    }

    @Test
    @DisplayName("Creates tree with size 31.")
    void createsTreeWithSize31() {
      aBST tree = new aBST(4);

      Assertions.assertEquals(31, tree.Tree.length);
    }

    @Test
    @DisplayName("Creates tree with size 63.")
    void createsTreeWithSize63() {
      aBST tree = new aBST(5);

      Assertions.assertEquals(63, tree.Tree.length);
    }
  }

  @Nested
  @DisplayName("FindKeyIndex")
  class FindKeyIndex {
    @Test
    @DisplayName("Desired element is root.")
    void desiredElementIsRoot() {
      aBST tree = FakeaBST.buildTree();

      Integer index = tree.FindKeyIndex(50);

      Assertions.assertEquals(0, index);
    }

    @Test
    @DisplayName("Desired element is leaf.")
    void desiredElementIsLeaf() {
      aBST tree = FakeaBST.buildTree();

      Integer index = tree.FindKeyIndex(31);

      Assertions.assertEquals(9, index);
    }

    @Test
    @DisplayName("Desired element is node.")
    void desiredElementIsNode() {
      aBST tree = FakeaBST.buildTree();

      Integer index = tree.FindKeyIndex(62);

      Assertions.assertEquals(5, index);
    }

    @Test
    @DisplayName("Desired element does not exist.")
    void desiredElementDoesNotExist() {
      aBST tree = FakeaBST.buildTree();

      Integer index = tree.FindKeyIndex(201);

      Assertions.assertNull(index);
    }

    @Test
    @DisplayName("Desired element does not exist but can be inserted.")
    void desiredElementDoesNotExistButCanBeInserted() {
      aBST tree = FakeaBST.buildTree();

      Integer index = tree.FindKeyIndex(3);

      Assertions.assertEquals(-3, index);
    }

    @Test
    @DisplayName("Desired element does not exist but can be inserted1.")
    void desiredElementDoesNotExistButCanBeInserted1() {
      aBST tree = FakeaBST.buildTree();

      Integer index = tree.FindKeyIndex(7);

      Assertions.assertEquals(-3, index);
    }

    @Test
    @DisplayName("Desired element does not exist but can be inserted2.")
    void desiredElementDoesNotExistButCanBeInserted2() {
      aBST tree = FakeaBST.buildTree();

      Integer index = tree.FindKeyIndex(8);

      Assertions.assertEquals(-3, index);
    }

    @Test
    @DisplayName("Desired element does not exist but can be inserted3.")
    void desiredElementDoesNotExistButCanBeInserted3() {
      aBST tree = FakeaBST.buildTree();

      Integer index = tree.FindKeyIndex(64);

      Assertions.assertEquals(-12, index);
    }

    @Test
    @DisplayName("Desired element does not exist but can be inserted4.")
    void desiredElementDoesNotExistButCanBeInserted4() {
      aBST tree = FakeaBST.buildTree();

      Integer index = tree.FindKeyIndex(82);

      Assertions.assertEquals(-13, index);
    }
  }

  @Nested
  @DisplayName("AddKey")
  class AddKey {
    @Test
    @DisplayName("Adds key.")
    void addsKey() {
      aBST tree = FakeaBST.buildTree();

      int index = tree.AddKey(20);

      Assertions.assertEquals(3, index);
    }

    @Test
    @DisplayName("Adds key.")
    void addsKey1() {
      aBST tree = FakeaBST.buildTree();

      int index = tree.AddKey(64);

      Assertions.assertEquals(12, index);
    }

    @Test
    @DisplayName("Adds key.")
    void addsKey2() {
      aBST tree = FakeaBST.buildTree();

      int index = tree.AddKey(80);

      Assertions.assertEquals(13, index);
    }

    @Test
    @DisplayName("Does not add existing element.")
    void doesNotAddExistingElement() {
      aBST tree = FakeaBST.buildTree();

      int index = tree.AddKey(50);

      Assertions.assertEquals(0, index);
    }

    @Test
    @DisplayName("Does not add existing element.")
    void doesNotAddExistingElement1() {
      aBST tree = FakeaBST.buildTree();

      int index = tree.AddKey(25);

      Assertions.assertEquals(1, index);
    }

    @Test
    @DisplayName("Does not add existing element.")
    void doesNotAddExistingElement2() {
      aBST tree = FakeaBST.buildTree();

      int index = tree.AddKey(31);

      Assertions.assertEquals(9, index);
    }

    @Test
    @DisplayName("Does not add existing element.")
    void doesNotAddExistingElement3() {
      aBST tree = FakeaBST.buildTree();

      int index = tree.AddKey(62);

      Assertions.assertEquals(5, index);
    }

    @Test
    @DisplayName("Does not add existing element.")
    void doesNotAddExistingElement4() {
      aBST tree = FakeaBST.buildTree();

      int index = tree.AddKey(92);

      Assertions.assertEquals(14, index);
    }

    @Test
    @DisplayName("Does not add element bigger then tree.")
    void doesNotAddElementBiggerThenTree() {
      aBST tree = FakeaBST.buildTree();

      int index = tree.AddKey(200);

      Assertions.assertEquals(-1, index);
    }
  }
}
