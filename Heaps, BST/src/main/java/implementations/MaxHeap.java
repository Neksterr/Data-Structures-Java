package implementations;

import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {
//parent (i-1)/2
    // left 2*i+1 right 2*i+2

    private List<E> elements;
    public MaxHeap(){
        this.elements = new ArrayList<>();
    }
    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.heapifyUp(this.size() - 1);
    }

    private void heapifyUp(int index) {

        while(index > 0 && isLess(getParentIndex(index), index)){
            Collections.swap(this.elements,getParentIndex(index),index);
            index = getParentIndex(index);

        }

    }

    private boolean isLess(int parentIndex, int index) {
        return  getAt(parentIndex).compareTo(getAt(index)) < 0;
    }
    private E getAt(int index){
        return this.elements.get(index);
    }

    private int getParentIndex(int index) {
        return  (index - 1) / 2;
    }

    @Override
    public E peek() {
        ensureNoEmpty();
        return this.elements.get(0);
    }

    private void ensureNoEmpty() {
        if(this.size() == 0){
            throw new IllegalStateException("It is empty");
        }
    }
}
