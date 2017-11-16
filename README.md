# Improve the Tree
## Task 1 Pretty print tree
Add the following code to your ```BSTNode``` class.

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

Update your ```BinSrchTree``` class with the following method.
```
public void prettyPrintTree(){
  root.print();
}

```

Call the ```prettyPrintTree``` in the tester ```BSTLearn``` as follows:
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

## Task 2 Move tree methods to BSTNode class

