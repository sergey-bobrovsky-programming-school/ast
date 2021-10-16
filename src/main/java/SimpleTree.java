import java.util.*;

public class SimpleTreeNode<T> {
  public T NodeValue; // значение в узле
  public SimpleTreeNode<T> Parent; // родитель или null для корня
  public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

  public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
    NodeValue = val;
    Parent = parent;
    Children = null;
  }
}

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
    NodeToDelete.Parent.Children.remove(NodeToDelete);

    if (NodeToDelete.Parent.Children.size() == 0) {
      NodeToDelete.Parent.Children = null;
    }

    NodeToDelete.Parent = null;
  }

  private List<SimpleTreeNode<T>> GetNodes(SimpleTreeNode<T> node, List<SimpleTreeNode<T>> nodes) {
    nodes.add(node);

    if (node.Children == null) {
      return nodes;
    }

    for (SimpleTreeNode<T> child : node.Children) {
      GetNodes(child, nodes);
    }

    return nodes;
  }

  public List<SimpleTreeNode<T>> GetAllNodes() {
    // ваш код выдачи всех узлов дерева в определённом порядке
    List<SimpleTreeNode<T>> nodes = new ArrayList<SimpleTreeNode<T>>();

    return GetNodes(Root, nodes);
  }

  private List<SimpleTreeNode<T>> FindNodes(T val, SimpleTreeNode<T> node, List<SimpleTreeNode<T>> nodes) {
    if (node.NodeValue == val) {
      nodes.add(node);
    }

    if (node.Children == null) {
      return nodes;
    }

    for (SimpleTreeNode<T> child : node.Children) {
      FindNodes(val, child, nodes);
    }

    return nodes;
  }

  public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
    // ваш код поиска узлов по значению
    List<SimpleTreeNode<T>> nodes = new ArrayList<SimpleTreeNode<T>>();

    return FindNodes(val, Root, nodes);
  }

  public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
    // ваш код перемещения узла вместе с его поддеревом --
    // в качестве дочернего для узла NewParent

    this.DeleteNode(OriginalNode);
    this.AddChild(NewParent, OriginalNode);
  }

  public int Count() {
    // количество всех узлов в дереве
    return this.GetAllNodes().size();
  }

  public int LeafCount() {
    // количество листьев в дереве
    List<SimpleTreeNode<T>> nodes = this.GetAllNodes();

    for (Iterator<SimpleTreeNode<T>> it = nodes.iterator(); it.hasNext();) {
      if (it.next().Children != null) {
        it.remove();
      }
    }

    return nodes.size();
  }
}