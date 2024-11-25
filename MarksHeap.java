import java.util.ArrayList;
import java.util.List;

public class MarksHeap {
        private List<Integer> heap;
        public MarksHeap(){
            this.heap=new ArrayList<>();
        }
        private List<Integer> getHeap(){
            return new ArrayList<>(heap);
        }

        private int leftChild(int index){
            return 2*index+1;
        }
        private int rightChild(int index){
            return 2*index+2;
        }
        private int parent(int index){
            return (index-1)/2;
        }

        private void swap(int index1,int index2){
            int temp = heap.get(index1);
            heap.set(index1,heap.get(index2));
            heap.set(index2,temp);;
        }

        public void insert(int value){
            heap.add(value);
            int currentIndex = heap.size()-1;

            while (currentIndex>0 && heap.get(parent(currentIndex))>heap.get(currentIndex)){
                swap(parent(currentIndex),currentIndex);
                currentIndex=parent(currentIndex);
            }
        }

        private void sinkDown(int index){

            int minIndex = index;

            while (true){

                int leftIndex = leftChild(index);
                int rightIndex = rightChild(index);

                if(leftIndex<heap.size() && heap.get(leftIndex)<heap.get(minIndex))
                    minIndex = leftIndex;
                if(rightIndex<heap.size() && heap.get(rightIndex)<heap.get(minIndex))
                    minIndex = rightIndex;

                if(minIndex!=index){
                    swap(index,minIndex);
                    index=minIndex;
                }else{
                    return;
                }
            }

        }

        public Integer remove(){
            if(heap.isEmpty())
                return null;
            if(heap.size()==1) return heap.remove(0);

            int temp = heap.get(0);
            heap.set(0,heap.remove(heap.size()-1));

            sinkDown(0);
            return temp;
        }

        public void printHeap(){
            for (int i :
                    getHeap()) {
                System.out.println(i);
            }
        }


    public static void main(String[] args) {
        MarksHeap minHeap = new MarksHeap();
        minHeap.insert(30);
        minHeap.insert(40);
        minHeap.insert(50);
        minHeap.insert(60);
        minHeap.insert(70);

        minHeap.printHeap();

    }
}
