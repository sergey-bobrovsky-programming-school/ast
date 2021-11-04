import java.util.ArrayList;

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
    @DisplayName("Adds node to the empty tree.")
    void addsNodeToTheEmptyTree() {
      BST<Integer> tree = new BST<Integer>(null);

      boolean result = tree.AddKeyValue(80, 80);

      Assertions.assertTrue(result);
      Assertions.assertEquals(1, tree.Count());

      BSTFind<Integer> searchResult = tree.FindNodeByKey(80);

      Assertions.assertTrue(searchResult.NodeHasKey);
      Assertions.assertEquals(80, searchResult.Node.NodeKey);
    }

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
    @DisplayName("Finds min key from root.")
    void findsMinKeyFromRoot() {
      BST<Integer> tree = FakeTree.buildTree();

      BSTNode<Integer> node = tree.FinMinMax(tree.Root, false);

      Assertions.assertEquals(85, node.NodeKey);
    }

    @Test
    @DisplayName("Finds min key from node.")
    void findsMinKeyFromNode() {
      BST<Integer> tree = FakeTree.buildTree();

      BSTFind<Integer> searchResult = tree.FindNodeByKey(105);

      BSTNode<Integer> node = tree.FinMinMax(searchResult.Node, false);

      Assertions.assertEquals(103, node.NodeKey);
    }

    @Test
    @DisplayName("Finds max key from root.")
    void findsMaxKeyFromRoot() {
      BST<Integer> tree = FakeTree.buildTree();

      BSTNode<Integer> node = tree.FinMinMax(tree.Root, true);

      Assertions.assertEquals(108, node.NodeKey);
    }

    @Test
    @DisplayName("Finds max key from node.")
    void findsMaxKeyFromNode() {
      BST<Integer> tree = FakeTree.buildTree();

      BSTFind<Integer> searchResult = tree.FindNodeByKey(95);

      BSTNode<Integer> node = tree.FinMinMax(searchResult.Node, true);

      Assertions.assertEquals(98, node.NodeKey);
    }
  }

  @Nested
  @DisplayName("DeleteNodeByKey")
  class DeleteNodeByKey {
    @Test
    @DisplayName("Does not delete unexisting node.")
    void doesNotDeleteUnexistingNode() {
      BST<Integer> tree = FakeTree.buildTree();

      boolean result = tree.DeleteNodeByKey(200);

      Assertions.assertFalse(result);
      Assertions.assertEquals(7, tree.Count());
    }

    @Test
    @DisplayName("Deletes root node.")
    void deletesRootNode() {
      BSTNode<Integer> root = new BSTNode<Integer>(100, 100, null);
      BST<Integer> tree = new BST<Integer>(root);

      Assertions.assertEquals(1, tree.Count());

      boolean result = tree.DeleteNodeByKey(100);

      Assertions.assertTrue(result);
      Assertions.assertEquals(0, tree.Count());
    }

    @Test
    @DisplayName("Deletes node without children.")
    void deletesLeafNode() {
      BST<Integer> tree = FakeTree.buildTree();

      boolean result = tree.DeleteNodeByKey(108);

      Assertions.assertTrue(result);
      Assertions.assertEquals(6, tree.Count());
      Assertions.assertEquals(false, tree.FindNodeByKey(108).NodeHasKey);
    }

    @Test
    @DisplayName("Deletes node with only left child.")
    void deletesNodeWithOnlyLeftChild() {
      BSTNode<Integer> root = new BSTNode<Integer>(100, 100, null);
      BST<Integer> tree = new BST<Integer>(root);

      tree.AddKeyValue(90, 90);
      tree.AddKeyValue(110, 110);
      tree.AddKeyValue(105, 105);
      tree.AddKeyValue(103, 103);
      tree.AddKeyValue(107, 107);

      Assertions.assertEquals(6, tree.Count());

      tree.DeleteNodeByKey(110);

      BSTFind<Integer> searchResult = tree.FindNodeByKey(110);

      Assertions.assertEquals(5, tree.Count());
      Assertions.assertFalse(searchResult.NodeHasKey);

      BSTFind<Integer> searchResult105 = tree.FindNodeByKey(105);

      Assertions.assertEquals(100, searchResult105.Node.Parent.NodeKey);
      Assertions.assertEquals(105, searchResult105.Node.Parent.RightChild.NodeKey);
      Assertions.assertEquals(103, searchResult105.Node.LeftChild.NodeKey);
      Assertions.assertEquals(107, searchResult105.Node.RightChild.NodeKey);
    }

    @Test
    @DisplayName("Deletes node with only right child.")
    void deletesNodeWithOnlyRightChild() {
      BSTNode<Integer> root = new BSTNode<Integer>(100, 100, null);
      BST<Integer> tree = new BST<Integer>(root);

      tree.AddKeyValue(90, 90);
      tree.AddKeyValue(110, 110);
      tree.AddKeyValue(120, 120);
      tree.AddKeyValue(115, 115);
      tree.AddKeyValue(125, 125);

      Assertions.assertEquals(6, tree.Count());

      boolean result = tree.DeleteNodeByKey(110);

      BSTFind<Integer> searchResult = tree.FindNodeByKey(110);

      Assertions.assertTrue(result);
      Assertions.assertEquals(5, tree.Count());
      Assertions.assertFalse(searchResult.NodeHasKey);

      BSTFind<Integer> searchResult115 = tree.FindNodeByKey(115);

      Assertions.assertEquals(100, searchResult115.Node.Parent.NodeKey);
      Assertions.assertEquals(120, searchResult115.Node.RightChild.NodeKey);
    }

    @Test
    @DisplayName("Deletes node with only right child. Smallest child - node.")
    void deletesNodeWithOnlyRightChildSmallestChildNode() {
      BSTNode<Integer> root = new BSTNode<Integer>(100, 100, null);
      BST<Integer> tree = new BST<Integer>(root);

      tree.AddKeyValue(90, 90);
      tree.AddKeyValue(110, 110);
      tree.AddKeyValue(120, 120);
      tree.AddKeyValue(115, 115);
      tree.AddKeyValue(125, 125);
      tree.AddKeyValue(117, 117);

      Assertions.assertEquals(7, tree.Count());

      boolean result = tree.DeleteNodeByKey(110);

      BSTFind<Integer> searchResult = tree.FindNodeByKey(110);

      Assertions.assertTrue(result);
      Assertions.assertEquals(6, tree.Count());
      Assertions.assertFalse(searchResult.NodeHasKey);

      BSTFind<Integer> searchResult115 = tree.FindNodeByKey(115);

      Assertions.assertEquals(100, searchResult115.Node.Parent.NodeKey);
      Assertions.assertEquals(120, searchResult115.Node.RightChild.NodeKey);
      Assertions.assertEquals(117, searchResult115.Node.RightChild.LeftChild.NodeKey);

      BSTFind<Integer> searchResult117 = tree.FindNodeByKey(117);

      Assertions.assertEquals(120, searchResult117.Node.Parent.NodeKey);
    }

    @Test
    @DisplayName("Deletes node with right and left child.")
    void deletesNodeWithRightAndLeftChild() {
      BSTNode<Integer> root = new BSTNode<Integer>(100, 100, null);
      BST<Integer> tree = new BST<Integer>(root);

      tree.AddKeyValue(90, 90);
      tree.AddKeyValue(110, 110);
      tree.AddKeyValue(105, 105);
      tree.AddKeyValue(120, 120);
      tree.AddKeyValue(125, 125);
      tree.AddKeyValue(115, 115);

      Assertions.assertEquals(7, tree.Count());

      boolean result = tree.DeleteNodeByKey(110);

      BSTFind<Integer> searchResult = tree.FindNodeByKey(110);

      Assertions.assertTrue(result);
      Assertions.assertEquals(6, tree.Count());
      Assertions.assertFalse(searchResult.NodeHasKey);

      BSTFind<Integer> searchResult115 = tree.FindNodeByKey(115);

      Assertions.assertEquals(100, searchResult115.Node.Parent.NodeKey);
      Assertions.assertEquals(105, searchResult115.Node.LeftChild.NodeKey);
      Assertions.assertEquals(120, searchResult115.Node.RightChild.NodeKey);
    }

    @Test
    @DisplayName("Deletes node with right and left child. Smallest child - node.")
    void deletesNodeWithRightAndLeftChildSmallestChildNode() {
      BSTNode<Integer> root = new BSTNode<Integer>(100, 100, null);
      BST<Integer> tree = new BST<Integer>(root);

      tree.AddKeyValue(90, 90);
      tree.AddKeyValue(110, 110);
      tree.AddKeyValue(105, 105);
      tree.AddKeyValue(120, 120);
      tree.AddKeyValue(115, 115);
      tree.AddKeyValue(125, 125);
      tree.AddKeyValue(117, 117);

      Assertions.assertEquals(8, tree.Count());

      boolean result = tree.DeleteNodeByKey(110);

      BSTFind<Integer> searchResult = tree.FindNodeByKey(110);

      Assertions.assertTrue(result);
      Assertions.assertFalse(searchResult.NodeHasKey);
      Assertions.assertEquals(7, tree.Count());

      BSTFind<Integer> searchResult115 = tree.FindNodeByKey(115);

      Assertions.assertEquals(100, searchResult115.Node.Parent.NodeKey);
      Assertions.assertEquals(105, searchResult115.Node.LeftChild.NodeKey);
      Assertions.assertEquals(120, searchResult115.Node.RightChild.NodeKey);
      Assertions.assertEquals(117, searchResult115.Node.RightChild.LeftChild.NodeKey);
    }

    @Test
    @DisplayName("Deletes all nodes.")
    void deletesAllNodes() {
      BST<Integer> tree = FakeTree.buildTree();

      tree.DeleteNodeByKey(85);
      tree.DeleteNodeByKey(98);
      tree.DeleteNodeByKey(95);

      tree.DeleteNodeByKey(103);
      tree.DeleteNodeByKey(108);
      tree.DeleteNodeByKey(105);

      tree.DeleteNodeByKey(100);

      Assertions.assertEquals(0, tree.Count());
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

  @Nested
  @DisplayName("GetNodesByDepth")
  class GetNodesByDepth {
    @Test
    @DisplayName("Returns empty list")
    void returnsEmpty() {
      BSTNode<Integer> root = new BSTNode<Integer>(100, 100, null);
      BST<Integer> tree = new BST<Integer>(root);

      ArrayList<BSTNode<Integer>> result = tree.GetNodesByDepth(0);

      Assertions.assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Returns root node")
    void returnsRootNode() {
      BSTNode<Integer> root = new BSTNode<Integer>(100, 100, null);
      BST<Integer> tree = new BST<Integer>(root);

      ArrayList<BSTNode<Integer>> result = tree.GetNodesByDepth(1);

      Assertions.assertEquals(1, result.size());
      Assertions.assertEquals(root, result.iterator().next());
    }

    @Test
    @DisplayName("Returns 2 level nodes")
    void returnsSecondLevelNodes() {
      BSTNode<Integer> root = new BSTNode<Integer>(100, 100, null);
      BST<Integer> tree = new BST<Integer>(root);

      tree.AddKeyValue(80, 80);
      tree.AddKeyValue(120, 120);

      ArrayList<BSTNode<Integer>> result = tree.GetNodesByDepth(2);

      BSTFind<Integer> searchResult80 = tree.FindNodeByKey(80);
      BSTFind<Integer> searchResult120 = tree.FindNodeByKey(120);

      Assertions.assertEquals(2, result.size());
      Assertions.assertEquals(searchResult80.Node.NodeKey, result.get(0).NodeKey);
      Assertions.assertEquals(searchResult120.Node.NodeKey, result.get(1).NodeKey);
    }

    @Test
    @DisplayName("Returns 3 level nodes")
    void returnsThirdLevelNodes() {
      BSTNode<Integer> root = new BSTNode<Integer>(100, 100, null);
      BST<Integer> tree = new BST<Integer>(root);

      tree.AddKeyValue(80, 80);
      tree.AddKeyValue(120, 120);

      tree.AddKeyValue(90, 90);

      tree.AddKeyValue(110, 110);
      tree.AddKeyValue(130, 130);

      ArrayList<BSTNode<Integer>> result = tree.GetNodesByDepth(3);

      BSTFind<Integer> searchResult90 = tree.FindNodeByKey(90);
      BSTFind<Integer> searchResult110 = tree.FindNodeByKey(110);
      BSTFind<Integer> searchResult130 = tree.FindNodeByKey(130);

      Assertions.assertEquals(3, result.size());
      Assertions.assertEquals(searchResult90.Node.NodeKey, result.get(0).NodeKey);
      Assertions.assertEquals(searchResult110.Node.NodeKey, result.get(1).NodeKey);
      Assertions.assertEquals(searchResult130.Node.NodeKey, result.get(2).NodeKey);
    }
  }

  @Nested
  @DisplayName("WideAllNodes")
  class WideAllNodes {
    @Test
    @DisplayName("Returns root node")
    void returnsRootNode() {
      BSTNode<Integer> root = new BSTNode<Integer>(100, 100, null);
      BST<Integer> tree = new BST<Integer>(root);

      ArrayList<BSTNode<Integer>> result = tree.WideAllNodes();

      Assertions.assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Returns 3 nodes")
    void returnsThreeNodes() {
      BSTNode<Integer> root = new BSTNode<Integer>(100, 100, null);
      BST<Integer> tree = new BST<Integer>(root);

      tree.AddKeyValue(80, 80);
      tree.AddKeyValue(120, 120);

      ArrayList<BSTNode<Integer>> result = tree.WideAllNodes();

      BSTFind<Integer> searchResult100 = tree.FindNodeByKey(100);
      BSTFind<Integer> searchResult80 = tree.FindNodeByKey(80);
      BSTFind<Integer> searchResult120 = tree.FindNodeByKey(120);

      Assertions.assertEquals(3, result.size());
      Assertions.assertEquals(searchResult100.Node.NodeKey, result.get(0).NodeKey);
      Assertions.assertEquals(searchResult80.Node.NodeKey, result.get(1).NodeKey);
      Assertions.assertEquals(searchResult120.Node.NodeKey, result.get(2).NodeKey);
    }

    @Test
    @DisplayName("Returns 7 nodes")
    void returnsSevenNodes() {
      BSTNode<Integer> root = new BSTNode<Integer>(100, 100, null);
      BST<Integer> tree = new BST<Integer>(root);

      tree.AddKeyValue(80, 80);
      tree.AddKeyValue(120, 120);

      tree.AddKeyValue(70, 70);
      tree.AddKeyValue(90, 90);

      tree.AddKeyValue(110, 110);
      tree.AddKeyValue(130, 130);

      ArrayList<BSTNode<Integer>> result = tree.WideAllNodes();

      BSTFind<Integer> searchResult100 = tree.FindNodeByKey(100);

      BSTFind<Integer> searchResult80 = tree.FindNodeByKey(80);
      BSTFind<Integer> searchResult120 = tree.FindNodeByKey(120);

      BSTFind<Integer> searchResult70 = tree.FindNodeByKey(70);
      BSTFind<Integer> searchResult90 = tree.FindNodeByKey(90);

      BSTFind<Integer> searchResult110 = tree.FindNodeByKey(110);
      BSTFind<Integer> searchResult130 = tree.FindNodeByKey(130);

      Assertions.assertEquals(7, result.size());
      Assertions.assertEquals(searchResult100.Node.NodeKey, result.get(0).NodeKey);
      Assertions.assertEquals(searchResult80.Node.NodeKey, result.get(1).NodeKey);
      Assertions.assertEquals(searchResult120.Node.NodeKey, result.get(2).NodeKey);
      Assertions.assertEquals(searchResult70.Node.NodeKey, result.get(3).NodeKey);
      Assertions.assertEquals(searchResult90.Node.NodeKey, result.get(4).NodeKey);
      Assertions.assertEquals(searchResult110.Node.NodeKey, result.get(5).NodeKey);
      Assertions.assertEquals(searchResult130.Node.NodeKey, result.get(6).NodeKey);
    }
  }
}
