package devo;

public class Oyster2 {
    public static class Arrays {

    }

    public static class Strings {
        
    }

    public static class LinkedLists {
        public static class SNode<T> implements Comparable<SNode<T>> {
            public T item;
            public SNode<T> next;

            @Override
            public int compareTo(SNode<T> o) {
                return 0;
            }
        }

        public static class DNode<T> {
            public T item;
            public DNode<T> next;
            public DNode<T> prev;
        }
    }

    public static class Stacks {
        
    }

    public static class Recursions {

    }

    public static class Sorting {

    }

    
    public static class BinaryTree {
        private Node root;

        public static class Node {
            Node left;
            Node right;
            int data;

            public Node(int newData) {
                left = null;
                right = null;
                data = newData;
            }
        }
        
        //   Creates an empty binary tree -- a null root pointer. 
        public BinaryTree() {
            root = null;
        }
        
        /** Searching
         We begin by examining the root node. If the tree is null, the value we are searching for does not exist in the tree. 
         Otherwise, if the value equals the root, the search is successful. 
         If the value is less than the root, search the left subtree. 
         Similarly, if it is greater than the root, search the right subtree. 
         This process is repeated until the value is found or the indicated subtree is null. 
         If the searched value is not found before a null subtree is reached, then the item must not be present in the tree.  */
        //  Returns true if the given target is in the binary tree. 
        //  Recursive lookup  -- given a node, recur down searching for the given data. 
        public boolean lookup(int data) {
            return lookup(root, data);
        }
        
        private boolean lookup(Node node, int data) {
            if (node == null) {
              return(false);
            }

            if (data == node.data) {
              return(true);
            }
            else if (data < node.data) {
              return(lookup(node.left, data));
            }
            else {
              return(lookup(node.right, data));
            }
          } 
        
        /** Insertion
         Insertion begins as a search would begin; 
         if the root is not equal to the value, we search the left or right subtrees as before. 
         Eventually, we will reach an external node and add the value as its right or left child, depending on the node's value. 
         In other words, we examine the root and recursively insert the new node 
         to the left subtree if the new value is less than the root, 
         or the right subtree if the new value is greater than or equal to the root. */

        // Inserts the given data into the binary tree 
       public void insert(int data) {
         root = insert(root, data);
       }

       // Recursive insert -- given a node pointer, recur down and insert the given data into the tree. Returns the new node pointer.
       private Node insert(Node node, int data) {
         if (node==null) {
           node = new Node(data);
         }
         else {
           if (data <= node.data) {
             node.left = insert(node.left, data);
           }
           else {
             node.right = insert(node.right, data);
           }
         }

         return(node); // in any case, return the new pointer to the caller
       } 
       
       //1. Build123() Solution (Java)
       // Build 123 using three pointer variables. 
       // Build 123 using only one pointer variable. 
       // Build 123 by calling insert() three times. 
       
       //2. size() Solution (Java)
       //Returns the number of nodes in the tree. Uses a recursive helper that recurs down the tree and counts the nodes. 
       
       //3. maxDepth() Solution (Java)
       //Returns the max root-to-leaf depth of the tree. Uses a recursive helper that recurs down to find the max depth. 
       
       //4. minValue() Solution 
       //Returns the min value in a non-empty binary search tree. Uses a helper method that iterates to the left to find the min value.
       
       //5. printTree() Solution (Java)
       //Prints the node values in the "inorder" order. Uses a recursive helper to do the traversal. 
       
       //6. printPostorder() Solution (Java)
       //Prints the node values in the "postorder" order.Uses a recursive helper to do the traversal. 
       
       //7. hasPathSum() Solution (Java)
       //Given a tree and a sum, returns true if there is a path from the root 
       //down to a leaf, such that adding up all the values along the path equals the given sum.
       // Strategy: subtract the node value from the sum when recurring down,and check to see if the sum is 0 when you run out of tree. 
       
       //8. printPaths() Solution (Java)
       //Given a binary tree, prints out all of its root-to-leaf paths, one per line. Uses a recursive helper to do the work. 
       
       //more problems at http://cslibrary.stanford.edu/110/BinaryTrees.html
      /** Deletion
       There are three possible cases to consider:
     // Deleting a leaf (node with no children): Deleting a leaf is easy, as we can simply remove it from the tree.
     // Deleting a node with one child: Remove the node and replace it with its child.
     // Deleting a node with two children: Call the node to be deleted N. Do not delete N. 
     //         Instead, choose either its in-order successor node or its in-order predecessor node, 
     //         R. Replace the value of N with the value of R, then delete R. */

     /** Traversal
     // Once the binary search tree has been created, 
     // its elements can be retrieved in-order by recursively traversing the left subtree of the root node, 
     // accessing the node itself, then recursively traversing the right subtree of the node, 
     // continuing this pattern with each node in the tree as it's recursively accessed. 
     // As with all binary trees, one may conduct a pre-order traversal or a post-order traversal, 
     // but neither are likely to be useful for binary search trees.  */
     
     /** Sort
     // A binary search tree can be used to implement a simple but efficient sorting algorithm. 
     // Similar to heapsort, we insert all the values we wish to sort into a new ordered data structureÑ
     // in this case a binary search treeÑand then traverse it in order, building our result:
        // Returns true if the given target is in the binary tree. Uses a recursive helper.  */

    }

    public static class BNode<T extends Comparable<T>> implements Comparable<BNode<T>> {
        public T item;
        public BNode<T> left;
        public BNode<T> right;
        public BNode<T> parent;

        public static <T extends Comparable<T>> BNode<T> of(BNode<T> parent) {
            BNode<T> node = new BNode<T>();
            node.parent = parent;
            return node;
        }

        public static <T extends Comparable<T>> BNode<T> of(T item, BNode<T> left, BNode<T> right) {
            BNode<T> node = new BNode<T>();
            node.item = item;
            node.left = left;
            node.right = right;
            return node;
        }

        @Override
        public int compareTo(BNode<T> o) {
            return this.item.compareTo(o.item);
        }

        @Override
        public String toString() {
            return item.toString();
        }
        
    }
}