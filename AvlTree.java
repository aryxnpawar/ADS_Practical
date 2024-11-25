public class AvlTree {
    private Node root;

    class Node {
        Node left, right;
        int height, balanceFactor;
        int value;

        public Node(int value) {
            this.left = this.right = null;
            this.height = this.balanceFactor = 0;
            this.value = value;
        }
    }

    public AvlTree() {
        root = null;
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    private Node insert(Node currentNode, int value) {
        if (currentNode == null) return new Node(value);

        if (value < currentNode.value)
            currentNode.left = insert(currentNode.left, value);

        else if (value > currentNode.value)
            currentNode.right = insert(currentNode.right, value);

        update(currentNode);
        return balance(currentNode);
    }

    public void delete(int value) {
        root = delete(root, value);
    }

    private Node delete(Node currentNode, int value) {
        if (currentNode == null) return null;

        if (value < currentNode.value) {
            currentNode.left = delete(currentNode.left, value);
        } else if (value > currentNode.value) {
            currentNode.right = delete(currentNode.right, value);
        }

        //found
        else {
            //leaf node
            if (currentNode.left == null && currentNode.right == null) {
                return null;
            }
            //left subtree is present
            else if (currentNode.left != null && currentNode.right == null) {
                return currentNode.left;
            }
            //right subtree is present
            else if (currentNode.left == null && currentNode.right != null) {
                return currentNode.right;
            }
            //both subtrees are present
            else {
                int minValue = leftMost(currentNode.right).value;
                currentNode.value = minValue;
                currentNode.right = delete(currentNode.right, minValue);
            }
        }
        update(currentNode);
        return balance(currentNode);
    }

    private Node leftMost(Node currentNode) {
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode;
    }

    private void update(Node currentNode) {
        int l = -1;
        int r = -1;

        if (currentNode.left != null)
            l = currentNode.left.height;

        if (currentNode.right != null)
            r = currentNode.right.height;

        currentNode.balanceFactor = l - r;
        currentNode.height = 1 + Math.max(l, r);
    }

    private Node balance(Node currentNode) {
        if (currentNode.balanceFactor < -1 || currentNode.balanceFactor > 1) {
            if (currentNode.balanceFactor == 2) {
                //case1:LL
                if (currentNode.left.balanceFactor >= 0) {
                    currentNode = rotateRight(currentNode);
                }
                //case2:LR
                else {
                    currentNode.left = rotateLeft(currentNode.left);
                    currentNode = rotateRight(currentNode);
                }
            } else if (currentNode.balanceFactor == -2) {
                //case3:RR
                if (currentNode.right.balanceFactor <= 0) {
                    currentNode = rotateLeft(currentNode);
                }
                //case4:RL
                else {
                    currentNode.right = rotateRight(currentNode.right);
                    currentNode = rotateLeft(currentNode);
                }
            }
        }
        return currentNode;
    }

    private Node rotateRight(Node currNode) {
        Node newRoot = currNode.left;
        Node temp = newRoot.right;

        newRoot.right = currNode;
        currNode.left = temp;

        update(newRoot);
        update(currNode);

        return newRoot;
    }

    private Node rotateLeft(Node currNode) {
        Node newRoot = currNode.right;
        Node temp = newRoot.left;

        newRoot.left = currNode;
        currNode.right = temp;

        update(newRoot);
        update(currNode);

        return newRoot;
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private Node inOrderTraversal(Node root) {
        if (root == null) {
            System.out.println("Empty Tree!");
            return null;
        }

        if (root.left != null) inOrderTraversal(root.left);
        System.out.println(root.value);
        if (root.right != null) inOrderTraversal(root.right);

        return root;
    }

    public boolean searchIterative(Node root, int value) {
        Node temp = root;
        while (temp != null) {
            if (value == temp.value) {
                return true;
            } else if (value < temp.value) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        return false;
    }

    private boolean searchRecursive(Node currentNode, int value) {
        if (currentNode == null) return false;
        if (currentNode.value == value) return true;
        return value < currentNode.value
                ? searchRecursive(currentNode.left, value)
                : searchRecursive(currentNode.right, value);
    }

    public boolean searchRecursive(int value){
        return searchRecursive(root,value);
    }


    public static void main(String[] args) {


        AvlTree tree = new AvlTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        System.out.println(tree.searchRecursive(5));  // Output: true
        System.out.println(tree.searchRecursive(20)); // Output: false


    }
}
