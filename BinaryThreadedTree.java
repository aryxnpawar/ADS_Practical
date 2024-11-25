public class BinaryThreadedTree {
    private Node root;

    class Node {
        int value;
        Node left, right;
        boolean leftThread, rightThread;

        public Node(int value) {
            this.value = value;
            this.left = this.right = null;
            this.leftThread = this.rightThread = true;
        }
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    private Node insert(Node currentNode, int value) {
        if (currentNode == null)
            return new Node(value);

        if (value < currentNode.value) {
            if (!currentNode.leftThread) {
                currentNode.left = insert(currentNode.left, value);
            } else {
                Node newNode = new Node(value);
                newNode.left = currentNode.left;
                newNode.right = currentNode;
                currentNode.left = newNode;
                currentNode.leftThread = false;
            }
        } else if (value > currentNode.value) {
            if (!currentNode.rightThread) {
                currentNode.right = insert(currentNode.right, value);
            } else {
                Node newNode = new Node(value);
                newNode.right = currentNode.right;
                newNode.left = currentNode;
                currentNode.right = newNode;
                currentNode.rightThread = false;
            }
        }
        return currentNode;
    }

    private Node leftMostNode(Node currentNode) {
        while (currentNode.left != null && !currentNode.leftThread) {
            currentNode = currentNode.left;
        }
        return currentNode;
    }

    public void inOrderTraversal() {
        if (root == null) {
            System.out.println("Empty tree!");
            return;
        }
        Node currentNode = leftMostNode(root);
        while (currentNode != null) {
            System.out.println(currentNode.value);
            if (!currentNode.rightThread) {
                currentNode = leftMostNode(currentNode.right);
            } else {
                currentNode = currentNode.right;
            }
        }
    }

    public void delete(int value) {
        root = delete(root, value, null);
    }

    private Node delete(Node currentNode, int value, Node parent) {
        if (currentNode == null) return null;

        if (value < currentNode.value) {
            currentNode.left = delete(currentNode.left, value, currentNode);
        } else if (value > currentNode.value) {
            currentNode.right = delete(currentNode.right, value, currentNode);
        } else {
            //case1:leaf Node
            if (currentNode.leftThread && currentNode.rightThread) {

                //removal of root Node
                if(parent==null)
                    return null;

                //left child
                if (currentNode.value < parent.value) {
                    parent.leftThread = true;
                    return currentNode.left;
                }

                //right child
                else {
                    parent.rightThread = true;
                    return currentNode.right;
                }
            }

            //left sub-tree present
            else if (!currentNode.leftThread && currentNode.rightThread) {

                if(parent==null)
                    return currentNode.left;  //adjust root

                //left child
                if (currentNode.value < parent.value) {
                    currentNode.left.right = currentNode.right;
                    return currentNode.left;

                    //right child
                } else {
                    currentNode.left.right = currentNode.right;
                    return currentNode.left;
                }
            }

            //right sub-tree present
            else if (currentNode.leftThread && !currentNode.rightThread) {

                if(parent==null)
                    return currentNode.right;  //adjust root

                //left child
                if (currentNode.value > parent.value) {
                    currentNode.right.left = currentNode.left;
                    return currentNode.right;

                    //right child
                } else {
                    currentNode.right.left = currentNode.left;
                    return currentNode.right;
                }
            }

            else{
                int minValue = leftMostNode(currentNode.right).value;
                currentNode.value=minValue;
                currentNode.right=delete(currentNode.right,minValue,currentNode);
            }
        }
        return currentNode;
    }

    public static void main(String[] args) {
        BinaryThreadedTree tree = new BinaryThreadedTree();
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Inorder Traversal:");
        tree.inOrderTraversal();

        tree.delete(40);
        tree.delete(70);

        System.out.println("Inorder Traversal:");
        tree.inOrderTraversal();
    }


}
