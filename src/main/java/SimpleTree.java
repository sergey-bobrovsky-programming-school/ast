import java.util.*;

class SimpleTree<T> {
  public SimpleTreeNode<T> Root; // корень, может быть null

  public SimpleTree(SimpleTreeNode<T> root) {
    Root = root;
  }

  public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
    // ваш код добавления нового дочернего узла существующему ParentNode
    if (ParentNode.Children == null) {
      ParentNode.Children = new ArrayList<SimpleTreeNode<T>>();
    }

    ParentNode.Children.add(NewChild);
    NewChild.Parent = ParentNode;
  }

  public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
    // ваш код удаления существующего узла NodeToDelete
  }

  public List<SimpleTreeNode<T>> GetAllNodes() {
    // ваш код выдачи всех узлов дерева в определённом порядке
    return null;
  }

  public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
    // ваш код поиска узлов по значению
    return null;
  }

  public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
    // ваш код перемещения узла вместе с его поддеревом --
  // в качестве дочернего для узла NewParent
  }

  public int Count() {
    // количество всех узлов в дереве
    return 0;
  }

  public int LeafCount() {
    // количество листьев в дереве
    return 0;
  }
}