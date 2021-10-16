import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

public class SimpleTreeTest {
  @Nested
  @DisplayName("AddChild method")
  class AddChild {
    @Test
    @DisplayName("Adds child node to the current node")
    void addsChildNodeToTheCurrentNode() {
      SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
      SimpleTree<Integer> tree = new SimpleTree<Integer>(root);
      SimpleTreeNode<Integer> child = new SimpleTreeNode<Integer>(1, null);

      tree.AddChild(root, child);

      Assertions.assertEquals(1, root.Children.size());
      Assertions.assertEquals(child, root.Children.iterator().next());
      Assertions.assertEquals(root, child.Parent);
    }
  }

  @Nested
  @DisplayName("DeleteNode method")
  class DeleteNode {
    @Test
    @DisplayName("Deletes node")
    void deletesNode() {
      SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
      SimpleTree<Integer> tree = new SimpleTree<Integer>(root);
      SimpleTreeNode<Integer> child = new SimpleTreeNode<Integer>(1, null);
      SimpleTreeNode<Integer> subchild = new SimpleTreeNode<Integer>(1, null);

      tree.AddChild(root, child);
      tree.AddChild(child, subchild);

      tree.DeleteNode(child);

      Assertions.assertNull(root.Children);
    }
  }

  @Nested
  @DisplayName("GetAllNodes method")
  class GetAllNodes {
    @Test
    @DisplayName("Gets all nodes")
    void getsAllNodes() {
      SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
      SimpleTree<Integer> tree = new SimpleTree<Integer>(root);

      SimpleTreeNode<Integer> child1 = new SimpleTreeNode<Integer>(1, null);
      SimpleTreeNode<Integer> child2 = new SimpleTreeNode<Integer>(1, null);
      SimpleTreeNode<Integer> child3 = new SimpleTreeNode<Integer>(1, null);

      SimpleTreeNode<Integer> subchild1 = new SimpleTreeNode<Integer>(1, null);
      SimpleTreeNode<Integer> subchild2 = new SimpleTreeNode<Integer>(1, null);

      tree.AddChild(root, child1);
      tree.AddChild(root, child2);
      tree.AddChild(root, child3);

      tree.AddChild(child1, subchild1);
      tree.AddChild(child2, subchild2);

      Assertions.assertEquals(6, tree.GetAllNodes().size());
    }
  }

  @Nested
  @DisplayName("FindNodesByValue method")
  class FindNodesByValue {
    @Test
    @DisplayName("Finds nodes")
    void findsNodes() {
      SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
      SimpleTree<Integer> tree = new SimpleTree<Integer>(root);

      SimpleTreeNode<Integer> child1 = new SimpleTreeNode<Integer>(1, null);
      SimpleTreeNode<Integer> child2 = new SimpleTreeNode<Integer>(0, null);
      SimpleTreeNode<Integer> child3 = new SimpleTreeNode<Integer>(1, null);

      SimpleTreeNode<Integer> subchild1 = new SimpleTreeNode<Integer>(0, null);
      SimpleTreeNode<Integer> subchild2 = new SimpleTreeNode<Integer>(5, null);

      tree.AddChild(root, child1);
      tree.AddChild(root, child2);
      tree.AddChild(root, child3);

      tree.AddChild(child1, subchild1);
      tree.AddChild(child2, subchild2);

      Assertions.assertEquals(3, tree.FindNodesByValue(0).size());
    }
  }

  @Nested
  @DisplayName("MoveNode method")
  class MoveNode {
    @Test
    @DisplayName("Move node")
    void moveNode() {
      SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
      SimpleTree<Integer> tree = new SimpleTree<Integer>(root);

      SimpleTreeNode<Integer> child1 = new SimpleTreeNode<Integer>(1, null);
      SimpleTreeNode<Integer> child2 = new SimpleTreeNode<Integer>(0, null);
      SimpleTreeNode<Integer> child3 = new SimpleTreeNode<Integer>(1, null);

      SimpleTreeNode<Integer> subchild1 = new SimpleTreeNode<Integer>(0, null);
      SimpleTreeNode<Integer> subchild2 = new SimpleTreeNode<Integer>(5, null);

      tree.AddChild(root, child1);
      tree.AddChild(root, child2);
      tree.AddChild(root, child3);

      tree.AddChild(child1, subchild1);
      tree.AddChild(child2, subchild2);

      tree.MoveNode(child1, subchild2);

      Assertions.assertEquals(2, root.Children.size());
      Assertions.assertEquals(subchild2, child1.Parent);
      Assertions.assertEquals(1, subchild2.Children.size());
      Assertions.assertEquals(child1, subchild2.Children.iterator().next());
    }
  }

  @Nested
  @DisplayName("Count method")
  class Count {
    @Test
    @DisplayName("Counts nodes")
    void countsNodes() {
      SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
      SimpleTree<Integer> tree = new SimpleTree<Integer>(root);

      SimpleTreeNode<Integer> child1 = new SimpleTreeNode<Integer>(1, null);
      SimpleTreeNode<Integer> child2 = new SimpleTreeNode<Integer>(0, null);
      SimpleTreeNode<Integer> child3 = new SimpleTreeNode<Integer>(1, null);

      SimpleTreeNode<Integer> subchild1 = new SimpleTreeNode<Integer>(0, null);
      SimpleTreeNode<Integer> subchild2 = new SimpleTreeNode<Integer>(5, null);

      tree.AddChild(root, child1);
      tree.AddChild(root, child2);
      tree.AddChild(root, child3);

      tree.AddChild(child1, subchild1);
      tree.AddChild(child2, subchild2);

      Assertions.assertEquals(6, tree.Count());
    }
  }

  @Nested
  @DisplayName("LeafCount")
  class LeafCount {
    @Test
    @DisplayName("Counts leafs")
    void countsLeafs() {
      SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
      SimpleTree<Integer> tree = new SimpleTree<Integer>(root);

      SimpleTreeNode<Integer> child1 = new SimpleTreeNode<Integer>(1, null);
      SimpleTreeNode<Integer> child2 = new SimpleTreeNode<Integer>(0, null);
      SimpleTreeNode<Integer> child3 = new SimpleTreeNode<Integer>(1, null);

      SimpleTreeNode<Integer> subchild1 = new SimpleTreeNode<Integer>(0, null);
      SimpleTreeNode<Integer> subchild2 = new SimpleTreeNode<Integer>(5, null);

      tree.AddChild(root, child1);
      tree.AddChild(root, child2);
      tree.AddChild(root, child3);

      tree.AddChild(child1, subchild1);
      tree.AddChild(child2, subchild2);

      Assertions.assertEquals(3, tree.LeafCount());
    }
  }
}
