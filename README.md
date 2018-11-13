# Improve the Tree
## Task 1 Pretty print tree
Add the following code to your `BSTNode` class.

```
public void print() {
  if (right != null) {
    right.recPrint(true, "");
  }
  System.out.println(key);
  if (left != null) {
    left.recPrint(false, "");
  }
}

private void recPrint(boolean isRight, String indent) {
  if (right != null) {
    right.recPrint(true, indent + (isRight ? "        " : " |      "));
  }
  System.out.print(indent);
  if (isRight) {
    System.out.print(" /");
  } else {
    System.out.print(" \\");
  }
  System.out.println("----- "+key);
  if (left != null) {
    left.recPrint(false, indent + (isRight ? " |      " : "        "));
  }
}
```

Update your `BinSrchTree` class with the following method.
```
public void prettyPrintTree(){
  root.print();
}

```

Call the `prettyPrintTree` in the tester `BSTLearn` as follows:
```
tree.prettyPrintTree();
```

Study the code to understand how it works. When you pretty print a tree from
you should see a similar tree as follows:
```
         /----- O
 /----- K
 |       \----- J
H
 \----- D
         \----- A
```

## Task 2 Rebalance tree after element removal
We need to extract the tree balancing code into its own method so that it can be
reused to balance the tree after element removal.

Add the following methods to your `BinSrchTree`:
```
public int balanceFactor( BSTNode node ){
  return height(node.getRight()) - height(node.getLeft());
}

private BSTNode balance( BSTNode node ){
  if(balanceFactor(node) == -2){
    // case 1: left subtree too tall
    // decide what sort of rotation will fix the problem
    BSTNode left = node.getLeft();
    BSTNode right = node.getRight();
    if(balanceFactor(left) < 0 ){
      // left subtree of left child is too tall
      // fix with a single right rotation
      node = singleRightRotation( node );
    }else{
      // right subtree of left child is too tall
      // so double rotation is necessary
      node = doubleLeftRightRotation( node );
    }
  }else if (balanceFactor(node) == 2){
    // case 2: right subtree too tall
    // decide what sort of rotation will fix the problem
    BSTNode left = node.getLeft();
    BSTNode right = node.getRight();
    if(balanceFactor(right) > 0 ){
      // right subtree of right child is too tall
      // fix with a single right rotation
      node = singleLeftRotation( node );
    }else{
      // left subtree of right child is too tall
      // so double rotation is necessary
      node = doubleRightLeftRotation( node );
    }
  }
  return node;
}
```

Refactor your `recAVLInsert`, as follows, to use the `balance` method:
```
private BSTNode recAVLInsert (BSTNode node, String key, Object element){
  if (node == null){
    // empty spot? insert here.
    System.out.println("insert: -"+key+"-");
    node = new BSTNode (key, element, null, null);
  }else if (key.compareTo (node.getKey ()) < 0){
    // insert in left subtree
    node.setLeft (recAVLInsert (node.getLeft (), key, element));
    // AFTER inserting, it is possible the tree is out of balance
    // rebalance the tree as necessary
    node = balance(node);
  }else{
    // insert in right subtree
    // shown below is simply the mirror image of what we did above
    node.setRight (recAVLInsert (node.getRight (), key, element));
    node = balance(node);
  }//else

  // We are done, but now we need to reset the height of this node after the
  // insertion
  node.setHeight(Math.max( height(node.getLeft()), height(node.getRight())) + 1);
  return node;
}
```

Update the `delete` method to use the `balance` method:
```
public void delete (String key){
  root = recDelete (root, key);
}

private BSTNode recDelete (BSTNode node, String key)
//PRE:
//POS:
//TAS: Currently, this method is stubbed; it is returning
// null because it has to return something.
{
  if (node == null){
    return null;
  }else if (key.compareTo(node.getKey()) == 0){
    // remove this node
    return remove(node);
  }else if (key.compareTo(node.getKey()) < 0){
    // try to delete from left subtree
    node.setLeft(recDelete(node.getLeft(), key));
  }else{
    // try to delete from right subtree
    node.setRight(recDelete(node.getRight(), key));
  }
  node = balance(node);
  return node;
}

private BSTNode remove (BSTNode node)
//PRE:
//POS:
//TAS:
{
  if (node.getLeft() == null){
    // is left subtree empty?
    return node.getRight();
  }else if (node.getRight() == null){
    // is right subtree empty?
    return node.getLeft();
  }else{
    // promote left child (L) to root
    // and right child (R) become right child of new root
    // graft L's right child to the leftmost node in R
    BSTNode tree = node.getLeft().getRight();
    BSTNode right =  node.getRight();
    if (tree != null){
      // find a place for "tree"
      BSTNode temp = right;
      while (temp.getLeft() != null){
        temp = temp.getLeft();
      }
      temp.setLeft(tree);
    }
    node.getLeft().setRight(right);
    return node.getLeft();
  }
}
```

Test your implementation thoroughly to make sure it works.
