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
}
