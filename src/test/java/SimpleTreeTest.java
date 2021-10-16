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
}
