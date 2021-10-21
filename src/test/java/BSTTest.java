import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

class FakeTree {
  public static BST<Integer> buildTree() {
    BSTNode<Integer> root = new BSTNode<Integer>(100, 100, null);
    BST<Integer> tree = new BST<Integer>(root);

    BSTNode<Integer> node95 = new BSTNode<Integer>(95, 95, null);
    BSTNode<Integer> node105 = new BSTNode<Integer>(105, 105, null);

    BSTNode<Integer> node85 = new BSTNode<Integer>(85, 85, null);
    BSTNode<Integer> node98 = new BSTNode<Integer>(98, 98, null);

    BSTNode<Integer> node103 = new BSTNode<Integer>(103, 103, null);
    BSTNode<Integer> node108 = new BSTNode<Integer>(108, 108, null);

    root.LeftChild = node95;
    root.RightChild = node105;
    node95.Parent = root;
    node105.Parent = root;

    node95.LeftChild = node85;
    node95.RightChild = node98;
    node85.Parent = node95;
    node98.Parent = node95;

    node105.LeftChild = node103;
    node105.RightChild = node108;
    node103.Parent = node105;
    node108.Parent = node105;

    return tree;
  }
}

public class BSTTest {
  @Nested
  @DisplayName("FindNodeByKey")
  class FindNodeByKey {
    @Test
    @DisplayName("Does not find node by key. Node should be added to left child.")
    void doesNotFindNodeLeftChild() {
      BST<Integer> tree = FakeTree.buildTree();

      BSTFind<Integer> result = tree.FindNodeByKey(80);

      Assertions.assertEquals(85, result.Node.NodeKey);
      Assertions.assertEquals(false, result.NodeHasKey);
      Assertions.assertEquals(true, result.ToLeft);
    }

    @Test
    @DisplayName("Does not find node by key. Node should be added to right child.")
    void doesNotFindNodeRightChild() {
      BST<Integer> tree = FakeTree.buildTree();

      BSTFind<Integer> result = tree.FindNodeByKey(110);

      Assertions.assertEquals(108, result.Node.NodeKey);
      Assertions.assertEquals(false, result.NodeHasKey);
      Assertions.assertEquals(false, result.ToLeft);
    }

    @Test
    @DisplayName("Finds node by key.")
    void findsNodeByKey() {
      BST<Integer> tree = FakeTree.buildTree();

      BSTFind<Integer> result = tree.FindNodeByKey(105);

      Assertions.assertEquals(105, result.Node.NodeKey);
      Assertions.assertEquals(true, result.NodeHasKey);
      Assertions.assertEquals(false, result.ToLeft);
    }
  }

  @Nested
  @DisplayName("AddKeyValue")
  class AddKeyValue {
    @Test
    @DisplayName("Adds left node.")
    void addsLeftNode() {
      BST<Integer> tree = FakeTree.buildTree();

      boolean result = tree.AddKeyValue(80, 80);

      Assertions.assertTrue(result);
      Assertions.assertEquals(8, tree.Count());

      BSTFind<Integer> searchResult = tree.FindNodeByKey(80);

      Assertions.assertEquals(80, searchResult.Node.NodeKey);
      Assertions.assertEquals(searchResult.Node, searchResult.Node.Parent.LeftChild);
    }

    @Test
    @DisplayName("Adds right node.")
    void addsRightNode() {
      BST<Integer> tree = FakeTree.buildTree();

      boolean result = tree.AddKeyValue(110, 110);

      Assertions.assertTrue(result);
      Assertions.assertEquals(8, tree.Count());

      BSTFind<Integer> searchResult = tree.FindNodeByKey(110);

      Assertions.assertEquals(110, searchResult.Node.NodeKey);
      Assertions.assertEquals(searchResult.Node, searchResult.Node.Parent.RightChild);
    }

    @Test
    @DisplayName("Does not add existing node.")
    void doesNotAddExistingNode() {
      BST<Integer> tree = FakeTree.buildTree();

      boolean result = tree.AddKeyValue(105, 105);

      Assertions.assertFalse(result);
      Assertions.assertEquals(7, tree.Count());
    }
  }

  @Nested
  @DisplayName("FinMinMax")
  class FinMinMax {
    @Test
    @DisplayName("Finds min key from root")
    void findsMinKeyFromRoot() {
      BST<Integer> tree = FakeTree.buildTree();

      BSTNode<Integer> node = tree.FindMinMax(tree.Root, false);

      Assertions.assertEquals(85, node.NodeKey);
    }

    @Test
    @DisplayName("Finds min key from node")
    void findsMinKeyFromNode() {
      BST<Integer> tree = FakeTree.buildTree();

      BSTFind<Integer> searchResult = tree.FindNodeByKey(105);

      BSTNode<Integer> node = tree.FindMinMax(searchResult.Node, false);

      Assertions.assertEquals(103, node.NodeKey);
    }

    @Test
    @DisplayName("Finds max key from root")
    void findsMaxKeyFromRoot() {
      BST<Integer> tree = FakeTree.buildTree();

      BSTNode<Integer> node = tree.FindMinMax(tree.Root, true);

      Assertions.assertEquals(108, node.NodeKey);
    }

    @Test
    @DisplayName("Finds max key from node")
    void findsMaxKeyFromNode() {
      BST<Integer> tree = FakeTree.buildTree();

      BSTFind<Integer> searchResult = tree.FindNodeByKey(95);

      BSTNode<Integer> node = tree.FindMinMax(searchResult.Node, true);

      Assertions.assertEquals(98, node.NodeKey);
    }
  }

  @Nested
  @DisplayName("Count")
  class Count {
    @Test
    @DisplayName("Counts nodes.")
    void countsNodes() {
      BST<Integer> tree = FakeTree.buildTree();

      Assertions.assertEquals(7, tree.Count());
    }
  }
}
