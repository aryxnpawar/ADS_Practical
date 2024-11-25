public class CircularQueue {
    private final int[] array;
    private final int maxSize;
    private int front,rear;

    public CircularQueue(int maxSize){
        this.maxSize=maxSize;
        this.array=new int[maxSize];
        this.front=this.rear=-1;
    }

    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }

    public boolean isEmpty(){
        return (rear==-1 && front==-1);
    }

    public boolean enqueue(int value){
        if(isFull()){
            System.out.println("Queue is Full!");
            return false;
        }
        if (rear==-1 && front==-1){
            rear=front=0;
            array[rear]=value;
        }
        else{
            rear=(rear+1)%maxSize;
            array[rear]=value;
        }
        return true;
    }

    public int dequeue(){
        if (isEmpty()){
            System.out.println("Queue is Empty");
            return Integer.MIN_VALUE;
        }

        int temp = array[front];

        if(front==rear){
            front=rear=-1;
        }else{
            front=(front+1)%maxSize;
        }
        return temp;
    }

    public void printQueue(){
        if(isEmpty()){
            System.out.println("Empty Queue");
            return;
        }
        int temp = front;
        do{
            System.out.println(array[temp]);
            temp=(temp+1)%maxSize;

        }while ( (rear+1)%maxSize!=temp );
    }

    public static void main(String[] args) {
        CircularQueue cq = new CircularQueue(5);

        cq.enqueue(10);
        cq.enqueue(20);
        cq.enqueue(30);
        cq.enqueue(40);
        cq.enqueue(50);

        System.out.println("Before Dequeuing 10,20 :");
        cq.printQueue();

        cq.dequeue();
        cq.dequeue();

        System.out.println("After Dequeuing 10,20 :");
        cq.printQueue();

        cq.enqueue(60);
        cq.enqueue(70);

        System.out.println("After queuing 60,70 :");
        cq.printQueue();

    }

}
