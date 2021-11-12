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

  private Integer FindByKey(int key, int index) {
    if (index > Tree.length - 1) {
      return null;
    }

    if (this.Tree[index] == null) {
      return null;
    }

    if (key == this.Tree[index]) {
      return index;
    }

    int leftChildIndex = 2 * index + 1;
    int rightChildIndex = 2 * index + 2;

    Integer leftChildResult = FindByKey(key, leftChildIndex);
    Integer rightChildResult = FindByKey(key, rightChildIndex);

    if (leftChildResult != null) {
      return leftChildResult;
    }

    if (rightChildResult != null) {
      return rightChildResult;
    }

    return null;
  }

  public Integer FindKeyIndex(int key) {
    // ищем в массиве индекс ключа
    return FindByKey(key, 0);
  }

  public int AddKey(int key) {
    // добавляем ключ в массив
    return -1;
    // индекс добавленного/существующего ключа или -1 если не удалось
  }
}