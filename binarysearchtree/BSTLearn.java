package binarysearchtree;

public class BSTLearn
{
  public static void main(String[] args)
  {
    //Create the empty tree
    BinSrchTree tree = new BinSrchTree();

    String keys = "ABCDEFGHIJ";
    for (char key: keys.toCharArray()) {
      System.out.println("Inserting: "+key);
      tree.insert(""+key, ""+key);
      System.out.println("The height is: "+tree.depth());
    }
    //Display the tree
    tree.prettyPrintTree();

    tree = new BinSrchTree();
    keys = "KAODHJ";
    for (char key: keys.toCharArray()) {
      System.out.println("Inserting: "+key);
      tree.insert(""+key, ""+key);
      System.out.println("The height is: "+tree.depth());
    }
    //Display the tree
    tree.prettyPrintTree();

/*
    // test element removal
    tree.insert("P", "P");
    tree.prettyPrintTree();
    tree.delete("J");
    tree.prettyPrintTree();
    tree.delete("A");
    tree.prettyPrintTree();
    tree.delete("D");
    tree.prettyPrintTree();
    tree.delete("H");
    tree.prettyPrintTree();
*/
  }
}
