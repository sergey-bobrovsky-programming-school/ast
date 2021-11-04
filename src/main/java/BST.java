import java.io.*;
import java.util.*;

class BSTNode<T> {
  public int NodeKey; // ключ узла
  public T NodeValue; // значение в узле
  public BSTNode<T> Parent; // родитель или null для корня
  public BSTNode<T> LeftChild; // левый потомок
  public BSTNode<T> RightChild; // правый потомок

  public BSTNode(int key, T val, BSTNode<T> parent) {
    NodeKey = key;
    NodeValue = val;
    Parent = parent;
    LeftChild = null;
    RightChild = null;
  }
}

// промежуточный результат поиска
class BSTFind<T> {
  // null если в дереве вообще нету узлов
  public BSTNode<T> Node;

  // true если узел найден
  public boolean NodeHasKey;

  // true, если родительскому узлу надо добавить новый левым
  public boolean ToLeft;

  public BSTFind() { Node = null; }
}

class BST<T> {
  BSTNode<T> Root; // корень дерева, или null

  public BST(BSTNode<T> node) {
    Root = node;
  }

  private BSTFind<T> FindByKey(BSTNode<T> node, int key) {
    if (node.NodeKey == key) {
      BSTFind<T> result = new BSTFind<T>();

      result.Node = node;
      result.NodeHasKey = true;
      result.ToLeft = false;

      return result;
    }

    if (key < node.NodeKey && node.LeftChild == null) {
      BSTFind<T> result = new BSTFind<T>();

      result.Node = node;
      result.NodeHasKey = false;
      result.ToLeft = true;

      return result;
    }

    if (key >= node.NodeKey && node.RightChild == null) {
      BSTFind<T> result = new BSTFind<T>();

      result.Node = node;
      result.NodeHasKey = false;
      result.ToLeft = false;

      return result;
    }

    return FindByKey(key < node.NodeKey ? node.LeftChild : node.RightChild, key);
  }

  public BSTFind<T> FindNodeByKey(int key) {
    // ищем в дереве узел и сопутствующую информацию по ключу
    return FindByKey(this.Root, key);
  }

  public boolean AddKeyValue(int key, T val) {
    // добавляем ключ-значение в дерево
    if (this.Root == null) {
      this.Root = new BSTNode<T>(key, val, null);

      return true;
    }

    BSTFind<T> searchResult = FindNodeByKey(key);

    if (searchResult.NodeHasKey == true) {
      return false; // если ключ уже есть
    }

    if (searchResult.ToLeft == true) {
      searchResult.Node.LeftChild = new BSTNode<T>(key, val, searchResult.Node);
    }

    if (searchResult.ToLeft == false) {
      searchResult.Node.RightChild = new BSTNode<T>(key, val, searchResult.Node);
    }

    return true;
  }

  private BSTNode<T> FindMin(BSTNode<T> node) {
    if (node.LeftChild == null) {
      return node;
    }

    return FindMin(node.LeftChild);
  }

  private BSTNode<T> FindMax(BSTNode<T> node) {
    if (node.RightChild == null) {
      return node;
    }

    return FindMax(node.RightChild);
  }

  public BSTNode<T> FinMinMax(BSTNode<T> initialNode, boolean isMax) {
    // ищем максимальный/минимальный ключ в поддереве
    if (isMax == true) {
      return FindMax(initialNode);
    }

    return FindMin(initialNode);
  }

  private boolean isLeftChild(BSTNode<T> node) {
    if (node.Parent == null) {
      return false;
    }

    if (node.Parent.LeftChild == node) {
      return true;
    }

    return false;
  }

  private boolean isRightChild(BSTNode<T> node) {
    if (node.Parent == null) {
      return false;
    }

    if (node.Parent.RightChild == node) {
      return true;
    }

    return false;
  }

  private boolean isLeaf(BSTNode<T> node) {
    if (node.LeftChild == null && node.RightChild == null) {
      return true;
    }

    return false;
  }

  private void DeleteLeaf(BSTNode<T> Node) {
    if (isLeftChild(Node)) {
      Node.Parent.LeftChild = null;
    }

    if (isRightChild(Node)) {
      Node.Parent.RightChild = null;
    }

    Node.Parent = null;
  }

  private void DeleteNodeWithChildren(BSTNode<T> Node) {
    BSTNode<T> rightChild = Node.RightChild;
    BSTNode<T> smallestChild = this.FindMin(rightChild);
    BSTNode<T> rightChildOfSmalestChild = smallestChild.RightChild;

    smallestChild.Parent.LeftChild = rightChildOfSmalestChild;

    if (rightChildOfSmalestChild != null) {
      rightChildOfSmalestChild.Parent = smallestChild.Parent;
    }

    if (isLeftChild(Node)) {
      Node.Parent.LeftChild = smallestChild;
    }

    if (isRightChild(Node)) {
      Node.Parent.RightChild = smallestChild;
    }

    smallestChild.Parent = Node.Parent;
    smallestChild.RightChild = Node.RightChild;
    smallestChild.LeftChild = Node.LeftChild;
  }

  private void DeleteNodeWithRightChild(BSTNode<T> Node) {
    BSTNode<T> rightChild = Node.RightChild;
    BSTNode<T> smallestChild = this.FindMin(rightChild);
    BSTNode<T> rightChildOfSmalestChild = smallestChild.RightChild;

    smallestChild.Parent.LeftChild = rightChildOfSmalestChild;

    if (rightChildOfSmalestChild != null) {
      rightChildOfSmalestChild.Parent = smallestChild.Parent;
    }

    if (isLeftChild(Node)) {
      Node.Parent.LeftChild = smallestChild;
    }

    if (isRightChild(Node)) {
      Node.Parent.RightChild = smallestChild;
    }

    smallestChild.Parent = Node.Parent;
    smallestChild.RightChild = Node.RightChild;
  }

  private void DeleteNodeWithLeftChild(BSTNode<T> Node) {
    if (isLeftChild(Node)) {
      Node.Parent.LeftChild = Node.LeftChild;
    }

    if (isRightChild(Node)) {
      Node.Parent.RightChild = Node.LeftChild;
    }

    Node.LeftChild.Parent = Node.Parent;
    Node.Parent = null;
  }

  public boolean DeleteNodeByKey(int key) {
    // // удаляем узел по ключу
    BSTFind<T> searchResult = FindNodeByKey(key);

    if (searchResult.NodeHasKey == false) {
      return false; // если узел не найден
    }

    if (searchResult.Node == this.Root) {
      this.Root = null;

      return true;
    }

    if (isLeaf(searchResult.Node)) {
      DeleteLeaf(searchResult.Node);

      return true;
    }

    if (searchResult.Node.LeftChild != null && searchResult.Node.RightChild == null) {
      DeleteNodeWithLeftChild(searchResult.Node);

      return true;
    }

    if (searchResult.Node.LeftChild == null && searchResult.Node.RightChild != null) {
      DeleteNodeWithRightChild(searchResult.Node);

      return true;
    }

    DeleteNodeWithChildren(searchResult.Node);

    return true;
  }

  private int CountNodes(int accumulator, BSTNode<T> node) {
    if (node.LeftChild == null && node.RightChild == null) {
      return accumulator + 1;
    }

    int leftAccumulator = node.LeftChild != null ? CountNodes(accumulator, node.LeftChild) : 0;
    int rightAccumulator = node.RightChild != null ? CountNodes(accumulator, node.RightChild) : 0;

    return accumulator + leftAccumulator + rightAccumulator + 1;
  }

  public int Count() {
    int initialValue = 0;

    if (this.Root == null) {
      return initialValue;
    }

    return CountNodes(initialValue, this.Root); // количество узлов в дереве
  }

  public ArrayList<BSTNode<T>> GetNodesByDepth(int depth) {
    ArrayList<BSTNode<T>> currentNodes = new ArrayList<BSTNode<T>>();

    if (depth == 0) {
      return currentNodes;
    }

    currentNodes.add(this.Root);

    for (int i = 1; i < depth; i++) {
      ArrayList<BSTNode<T>> nextNodes = new ArrayList<BSTNode<T>>();

      for (BSTNode<T> node : currentNodes) {
        if (node.LeftChild != null) {
          nextNodes.add(node.LeftChild);
        }

        if (node.RightChild != null) {
          nextNodes.add(node.RightChild);
        }
      }

      currentNodes = nextNodes;
    }

    return currentNodes;
  }

  private ArrayList<BSTNode<T>> WideAll(int maxDepth, int currentDepth, ArrayList<BSTNode<T>> accumulator) {
    if (currentDepth > maxDepth) {
      return accumulator;
    }

    return accumulator;
  }

  public ArrayList<BSTNode<T>> WideAllNodes() {
    ArrayList<BSTNode<T>> nodes = this.WideAll(0, 0, new ArrayList<BSTNode<T>>());

    return nodes;
  }
}