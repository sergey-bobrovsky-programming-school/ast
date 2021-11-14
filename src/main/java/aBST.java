import java.util.*;

class aBST {
  public Integer Tree []; // массив ключей

  public aBST(int depth) {
    // правильно рассчитайте размер массива для дерева глубины depth:
    int tree_size = this.getSize(depth, 0, 0);

    Tree = new Integer[ tree_size ];
    for(int i=0; i<tree_size; i++) Tree[i] = null;
  }

  private int getSize(int maxDepth, int depth, int size) {
    if (depth > maxDepth) {
      return size;
    }

    int newSize = size * 2 + 1;

    int newDepth = depth + 1;

    return getSize(maxDepth, newDepth, newSize);
  }

  private boolean isMaxDepth(int index) {
    int leftChildIndex = 2 * index + 1;
    int rightChildIndex = 2 * index + 2;
    int maxIndex = this.Tree.length - 1;

    if (leftChildIndex > maxIndex && rightChildIndex > maxIndex) {
      return true;
    }

    return false;
  }

  private Integer FindByKey(int key, int index) {
    if (this.Tree[index] == null) {
      return index * (-1);
    }

    if (key == this.Tree[index]) {
      return index;
    }

    boolean maxDepth = isMaxDepth(index);

    if (maxDepth) {
      return null;
    }

    int nodeKey = this.Tree[index];
    int leftChildIndex = 2 * index + 1;
    int rightChildIndex = 2 * index + 2;

    return FindByKey(key, key < nodeKey ? leftChildIndex : rightChildIndex);
  }

  public Integer FindKeyIndex(int key) {
    // ищем в массиве индекс ключа
    return FindByKey(key, 0);
  }

  public int AddKey(int key) {
    // добавляем ключ в массив
    Integer index = FindKeyIndex(key);

    if (index == null) {
      return -1;
    }

    if (index > 0) {
      return index;
    }

    int newIndex = index * (-1);

    this.Tree[newIndex] = key;

    return newIndex;
    // индекс добавленного/существующего ключа или -1 если не удалось
  }
}