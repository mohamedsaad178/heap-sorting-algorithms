package eg.edu.alexu.csd.filestructure.sort;

public class Node<T extends Comparable<T>> implements INode {
    private int index;
    private Heap heap;
    private Comparable value;


    public Node(int index, Heap heap, Comparable value) {
        this.index = index;
        this.heap = heap;
        this.value=value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public INode<T> getLeftChild(){
        if ((2*index+1) >= heap.size()) return null;
        return heap.array[2*index+1];
    }
    @Override
    public INode<T> getRightChild(){
        if ((2*index+2) >= heap.size()) return null;
        return heap.array[2*index+2];
    }
    @Override
    public INode<T> getParent(){
        if (index==((Node)heap.getRoot()).getIndex() ) return null;
        return heap.array[(index-1)/2];
    }
    @Override
    public Comparable getValue(){
        return value;
    }

    @Override
    public void setValue(Comparable var1) {
        this.value=  var1;
    }

}
