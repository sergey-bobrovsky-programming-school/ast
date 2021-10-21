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

    if (key > node.NodeKey && node.RightChild == null) {
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

  public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
    // ищем максимальный/минимальный ключ в поддереве
    return null;
  }

  public boolean DeleteNodeByKey(int key) {
    // удаляем узел по ключу
    return false; // если узел не найден
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
    return CountNodes(0, this.Root); // количество узлов в дереве
  }
}