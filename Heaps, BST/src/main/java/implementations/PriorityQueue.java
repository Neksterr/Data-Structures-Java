package implementations;

import interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {
    private List<E> elements;

    public PriorityQueue() {
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

        while (index > 0 && isLess(getParentIndex(index), index)) {
            Collections.swap(this.elements, getParentIndex(index), index);
            index = getParentIndex(index);

        }

    }

    public E getAt(int index) {
        return this.elements.get(index);
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    @Override
    public E peek() {
        ensureNoEmpty();
        return getAt(0);
    }

    private void ensureNoEmpty() {
        if (this.size() == 0) {
            throw new IllegalStateException("Error");
        }
    }

    @Override
    public E poll() {
        ensureNoEmpty();
        E returnedValue = getAt(0);

        Collections.swap(this.elements, 0, elements.size() - 1);
        this.elements.remove(elements.size() - 1);
        this.heapifyDown(0);

        return returnedValue;
    }

    private E getLeftChild(int index) {
        return this.elements.get(this.getLeftChildIndex(index));

    }

    private E getRightChild(int index) {
        return this.elements.get(this.getRightChildIndex(index));
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }


    private void heapifyDown(int index) {
        while (getLeftChildIndex(index) < this.size() && isLess(index, getLeftChildIndex(index))) {
            int child = getLeftChildIndex(index);
            int rightChildIndex = getRightChildIndex(index);
            if (rightChildIndex < this.size() && isLess(child, rightChildIndex)) {
                child = rightChildIndex;
            }
            Collections.swap(this.elements, child, index);
            index = child;
        }
    }

    private boolean isLess(int first, int second) {
        return getAt(first).compareTo(getAt(second)) < 0;
    }
}
