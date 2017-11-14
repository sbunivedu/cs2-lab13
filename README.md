# Pretty Print Trees
Add the following code to your ```BSTNode``` class.

```
public void print(OutputStreamWriter out) throws IOException{
  if (right != null) {
    right.recPrint(out, true, "");
  }
  out.write(key+"\n");
  if (left != null) {
    left.recPrint(out, false, "");
  }
}

private void recPrint(OutputStreamWriter out,
  boolean isRight, String indent) throws IOException {
  if (right != null) {
    right.recPrint(out, true,
      indent + (isRight ? "        " : " |      "));
  }
  out.write(indent);
  if (isRight) {
    out.write(" /");
  } else {
    out.write(" \\");
  }
  out.write("----- ");
  out.write(key+"\n");
  if (left != null) {
    left.recPrint(out, false,
      indent + (isRight ? " |      " : "        "));
  }
}
```

Update your ```BinSrchTree``` class with the following method.
```
public void prettyPrintTree(OutputStreamWriter out) throws IOException{
  root.print(out);
  out.flush();
}
```

Call the ```prettyPrintTree``` in the tester ```BSTLearn``` as follows:
```
tree.prettyPrintTree(new OutputStreamWriter(System.out));
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