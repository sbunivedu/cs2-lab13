// This is a BST Tree implementing a recursive algorithm for
// search, insertion, deletion, printing, and determining maximum
// depth.
package binarysearchtree;

import java.io.OutputStreamWriter;
import java.io.IOException;

public class BinSrchTree{
  private BSTNode root; // The root of the Binary Search Tree.

  public BinSrchTree(){
    //PRE: None
    //POS: tree == null
    //TAS: Initialize the Binary Search tree to null
    root = null;
  }

  boolean isEmpty(){
    //PRE: None
    //POS: None
    //TAS: return root being equal to null
    return root == null;
  }

  public Object search(String key){
    //PRE: init<key>
    //POS: none
    //TAS: Search the tree for object with key
    // if (object with this key is in the tree)
    // return a reference to the object
    // else
    // return a null reference
    BSTNode node = recSearch(root, key);
    if(node == null){
      return null;
    }else{
      return node.getElement ();
    }
  }

  private BSTNode recSearch(BSTNode node, String key){
    //PRE:
    //POS:
    //TAS: Currently, this method is stubbed; it is returning
    // null because it has to return something.
    return null;
  }

  public void insert(String key, Object element){
    //PRE: init <key>, init<element>
    //POS: exit <tree> <-- entry <tree> + element
    //TAS: insert the object with key into the tree
    //root = recInsert (root, key, element);
    root = recAVLInsert(root, key, element);
  }

  private BSTNode recInsert(BSTNode node, String key, Object element){
    //PRE:
    //POS:
    //TAS:
    if(node == null){
      node = new BSTNode(key, element, null, null);
    }else if(key.compareTo(node.getKey()) == 0){
      node.setElement (element);
    }else if(key.compareTo(node.getKey()) < 0){
      node.setLeft(recInsert(node.getLeft(), key, element));
    }else {
      node.setRight(recInsert(node.getRight(), key, element));
    }
    return node;
  }

  public void delete(String key){
    //PRE:
    //POS:
    //TAS:
    System.out.println("delete "+key);
    root = recDelete (root, key);
  }

  private BSTNode recDelete(BSTNode node, String key){
    //PRE:
    //POS:
    //TAS: Currently, this method is stubbed; it is returning
    // null because it has to return something.
    if(node == null){
      return null;
    }else if(key.compareTo(node.getKey()) == 0){
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

  private BSTNode remove(BSTNode node){
    if(node.getLeft() == null){
      // is left subtree empty?
      return node.getRight();
    }else if(node.getRight() == null){
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

  public void printTree(){
    // print the in-order traversal of the tree
    System.out.println(this.toString());
  }

  public String toString(){
    return recToString(root);
  }

  private String recToString(BSTNode node){
    if (node == null){
      return "";
    }else{
      return recToString(node.getLeft()) +
        node.getKey() +
        recToString(node.getRight());
    }
  }

  public int depth(){
    return recDepth(root);
  }

  protected int recDepth(BSTNode node){
    if(node == null){
      return 0;
    }else{
      return 1 + Math.max(recDepth(node.getLeft()), recDepth(node.getRight()));
    }
  }

  private int height(BSTNode node){
    //PRE: none
    //POS: none
    //TAS: if the node is null return -1, else return the height in the node
    return node == null ? -1 : node.getHeight();
  }

  public int height(){
    return height(root);
  }

  public int balanceFactor(BSTNode node){
    return height(node.getRight()) - height(node.getLeft());
  }

  private BSTNode balance(BSTNode node){
    if(balanceFactor(node) == -2){
      // case 1: left subtree too tall
      // decide what sort of rotation will fix the problem
      BSTNode left = node.getLeft();
      BSTNode right = node.getRight();
      if(balanceFactor(left) < 0 ){
        // left subtree of left child is too tall
        // fix with a single right rotation
        node = singleRightRotation(node);
      }else{
        // right subtree of left child is too tall
        // so double rotation is necessary
        node = doubleLeftRightRotation(node);
      }
    }else if(balanceFactor(node) == 2){
      // case 2: right subtree too tall
      // decide what sort of rotation will fix the problem
      BSTNode left = node.getLeft();
      BSTNode right = node.getRight();
      if(balanceFactor(right) > 0){
        // right subtree of right child is too tall
        // fix with a single right rotation
        node = singleLeftRotation( node );
      }else{
        // left subtree of right child is too tall
        // so double rotation is necessary
        node = doubleRightLeftRotation(node);
      }
    }
    return node;
  }

  private BSTNode singleRightRotation(BSTNode k2){
    //TASK: Rotate the left child up and to the right to
    //      become the new root of this subtree
    System.out.println("right rotation");
    BSTNode k1 = k2.left;
    k2.left = k1.right;
    k1.right = k2;
    k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
    k1.height = Math.max(height(k1.left), k2.height) + 1;
    return k1;
  }

  private BSTNode singleLeftRotation(BSTNode k1){
    //TASK: Rotate the right child up and to the left to
    //      become the new root of this subtree
    System.out.println("left rotation");
    BSTNode k2 = k1.right;
    k1.right = k2.left;
    k2.left = k1;
    k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
    k2.height = Math.max(height(k2.right), k1.height) + 1;
    return k2;
  }

  private BSTNode doubleLeftRightRotation(BSTNode k3){
    //TASK: Rotate the left subtree to the left, then up
    //      and to the right to become the new root of this subtree
    System.out.println("left right rotation");
    k3.left = singleLeftRotation(k3.left);
    return singleRightRotation(k3);
  }

  private BSTNode doubleRightLeftRotation(BSTNode k4){
    //TASK: Rotate the right subtree to the right, then up and to
    //      the left to become the new root of this subtree
    System.out.println("right left rotation");
    k4.right = singleRightRotation(k4.right);
    return singleLeftRotation(k4);
  }

  private BSTNode recAVLInsert(BSTNode node, String key, Object element){
    if(node == null){
      // empty spot? insert here.
      System.out.println("insert: -"+key+"-");
      node = new BSTNode(key, element, null, null);
    }else if(key.compareTo(node.getKey()) < 0){
      // insert in left subtree
      node.setLeft(recAVLInsert(node.getLeft(), key, element));
      // AFTER inserting, it is possible the tree is out of balance
      // rebalance the tree as necessary
      node = balance(node);
    }else{
      // insert in right subtree
      // shown below is simply the mirror image of what we did above
      node.setRight(recAVLInsert(node.getRight(), key, element));
      node = balance(node);
    }//else

    // We are done, but now we need to reset the height of this node after the
    // insertion
    node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
    return node;
  }
}
