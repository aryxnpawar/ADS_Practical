public class Stack {
    private Node top;
    private int height;

    class Node{
        Node next;
        int value;

        public Node(int value){
            this.next=null;
            this.value=value;
        }
    }

    public Stack(){
        top=null;
        height=0;
    }

    public void push(int value){
        if (top==null && height==0)
            top = new Node(value);
        else{
            Node newNode = new Node(value);
            newNode.next=top;
            top=newNode;
        }
        height++;
    }

    public Node pop(){
        if (top==null && height==0)
            return null;

        Node temp = top;
        top=top.next;
        height--;

        return temp;
    }

    public void printStack(){
        if (top==null && height==0) {
            System.out.println("Empty Stack!");
            return;
        }
        Node temp = top;
        while (temp!=null){
            System.out.println(temp.value);
            temp=temp.next;
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println("Current Stack :");
        stack.printStack();
        stack.pop();
        System.out.println("Stack After popping '4' :");
        stack.printStack();
    }
}
