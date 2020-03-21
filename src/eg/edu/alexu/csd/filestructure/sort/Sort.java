package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

public class Sort implements ISort{

    @Override
    public IHeap heapSort(ArrayList unordered) {
        Heap heap =new Heap();
        heap.build(unordered);
        int s=heap.size();
        for (int i=heap.size()-1; i>0; i--){
            heap.extract();
        }
        heap.setSize(s);
        return heap;
    }


    // Bubble sort
    @Override
    public void sortSlow(ArrayList unordered) {
        if (unordered==null){
            return;
        }
            int s = unordered.size();
            while (s > 1) {
                boolean sorted = true;
                for (int i = 0; i < s - 1; i++) {
                    if ( ((Comparable)unordered.get(i)).compareTo(unordered.get(i + 1)) > 0 ) {
                        Comparable temp = (Comparable) unordered.get(i);
                        unordered.set(i, unordered.get(i + 1));
                        unordered.set(i + 1, temp);
                        sorted = false;
                    }
                }
                if (sorted) {
                    break;
                }
                s--;
            }
    }
    // merge sort
    @Override
    public void sortFast(ArrayList unordered) {
        if (unordered==null){
            return;
        }
        int first=0;
        int last=unordered.size()-1;
        mergeSort(unordered,first,last);
    }

    private void mergeSort(ArrayList unordered, int first, int last){
        int mid=(first+last)/2;
        if (first<last){
            mergeSort(unordered,first,mid);
            mergeSort(unordered,mid+1,last);
            merge(unordered,first,mid,last);
        }
    }
    public void merge(ArrayList unordered, int first, int mid, int last){
        int len1 = mid - first + 1;
        int len2 = last - mid;
        ArrayList<Comparable> temp1=new ArrayList(len1);
        ArrayList<Comparable> temp2=new ArrayList(len2);
        for (int i=0; i<len1; i++) {
            Comparable var = (Comparable) unordered.get(first+i);
            temp1.add(i,var);
        }
        for (int j=0; j<len2; j++) {
            Comparable var = (Comparable) unordered.get(mid+1+j);
            temp2.add(j,var);
        }

        int i = 0, j = 0;
        int index = first;
        while (i < len1 && j < len2)
        {
            if (temp1.get(i).compareTo(temp2.get(j)) <= 0)
            {
                unordered.set(index,temp1.get(i));
                i++;
            }
            else
            {
                unordered.set(index,temp2.get(j));
                j++;
            }
            index++;
        }
        while (i < len1)
        {
            unordered.set(index,temp1.get(i));
            i++;
            index++;
        }

        while (j < len2)
        {
            unordered.set(index,temp2.get(j));
            j++;
            index++;
        }
    }
   /* public static void main(String args[]){
        ArrayList<Integer> cars = new ArrayList<Integer>();
        cars.add(20);
        cars.add(15);
        cars.add(30);
        cars.add(22);
        cars.add(5);
        Sort<Integer> t = new Sort<Integer>();
        t.heapSort(cars);
        System.out.println("heap");
        System.out.println(cars);
        t.sortSlow(cars);
        System.out.println("slow");
        System.out.println(cars);
        t.sortFast(cars);
        System.out.println("fast");
        System.out.println(cars);
    }*/
}
