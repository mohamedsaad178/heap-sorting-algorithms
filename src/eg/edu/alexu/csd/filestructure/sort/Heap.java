package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


public class Heap implements IHeap {

    Node[] array;
    private int size;
    private int maxsize=10000005;

    public Heap() {
        array = new Node[this.maxsize];
        this.size = 0;
    }

    public void setSize (int size){
        this.size=size;
    }

    @Override
    public INode getRoot() {
        if (size()==0){
            return null;
        }
        return array[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void heapify(INode var1) {
        if (var1==null) return;
        Node node = (Node) var1;
        if (node.getIndex() >= ((size) / 2) && node.getIndex() <= size-1) {
            return;
        }
        if (2*node.getIndex()+2>=size){
            if (node.getValue().compareTo(node.getLeftChild().getValue()) < 0){
                Comparable temp= array[node.getIndex()].getValue();
                array[node.getIndex()].setValue(array[((Node)node.getLeftChild()).getIndex()].getValue());
                array[((Node)node.getLeftChild()).getIndex()].setValue(temp);
                heapify(array[((Node)node.getLeftChild()).getIndex()]);
            }
        }else {
            if ( node.getValue().compareTo(node.getRightChild().getValue()) < 0 || node.getValue().compareTo(node.getLeftChild().getValue()) < 0 ) {
                if ( node.getLeftChild().getValue().compareTo(node.getRightChild().getValue()) > 0 ) {
                    Comparable temp = array[node.getIndex()].getValue();
                    array[node.getIndex()].setValue(array[((Node)node.getLeftChild()).getIndex()].getValue());
                    array[((Node)node.getLeftChild()).getIndex()].setValue(temp);
                    heapify(array[((Node)node.getLeftChild()).getIndex()]);
                } else {
                    Comparable temp = array[node.getIndex()].getValue();
                    array[node.getIndex()].setValue(array[((Node)node.getRightChild()).getIndex()].getValue());
                    array[((Node)node.getRightChild()).getIndex()].setValue(temp);
                    heapify(array[((Node)node.getRightChild()).getIndex()]);
                }
            }
        }
    }

    @Override
    public Comparable extract() {
        if (this.size()==0){
            return null;
        }
        if (this.size()==1){
            size--;
            return array[0].getValue();
        }
        Comparable popped = array[0].getValue();
        array[0].setValue(array[size-1].getValue());
        array[size-1].setValue(popped);
        size--;
        heapify(array[0]);
        return  popped;
    }


    @Override
    public void build(Collection var1) {
        if (var1==null){
            return;
        }
        int i=0;
        ArrayList<Comparable> var= (ArrayList<Comparable>) var1;
        for (Comparable e: var){
            Node temp =new Node(i,this,e);
            array[i]=temp;
            i++;
        }
        size=i;
        for (int j=((size)/2)-1; j>=0; j--){
            heapify(array[j]);
        }
    }

    @Override
    public void insert(Comparable var1) {
        if (var1==null) return;
        size++;
        Node node=new Node(size-1, this,var1);
        array[size-1] =node;
        int current = size-1;
        while (current>0 && array[current].getValue().compareTo(array[current].getParent().getValue()) > 0 ){
            Comparable temp =  array[current].getValue();
            array[current].setValue(array[current].getParent().getValue());
            array[(current-1)/2].setValue(temp);
            current=(current-1)/2;
        }
    }

}
