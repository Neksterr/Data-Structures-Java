package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private int size;

    private static class Node<E> {
        private E value;
        private Node<E> next;

        Node(E element) {
            this.value = element;
        }
    }

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void addFirst(E element) {
        Node<E> toInsert = new Node<>(element);
        toInsert.next = this.head;
        this.head = toInsert;
        this.size++;

    }

    @Override
    public void addLast(E element) {
        Node<E> toInsert = new Node<>(element);
        if (this.isEmpty()) {
            this.head = toInsert;
            this.size++;
            return;
        }
        Node<E> current = this.head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = toInsert;
        this.size++;
    }


    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        E value = this.head.value;
        this.head = this.head.next;
        this.size--;
        return value;
    }

    @Override
    public E removeLast() {
        if(this.isEmpty()) {
            throw new IllegalStateException();
        }
        if (this.size == 1){
            E value = this.head.value;
            this.head = null;
            return  value;
        }
        Node<E> preLast = this.head;
        Node<E> toRemove = this.head.next;
        while (toRemove.next != null){
            preLast = toRemove;
            toRemove = toRemove.next;
        }
        preLast.next = null;
        return toRemove.value;
    }

    @Override
    public E getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return this.head.value;
    }

    @Override
    public E getLast() {
        if(this.isEmpty()){
            throw new IllegalStateException();
        }
        Node<E> curr = this.head;
        while (curr.next != null){
            curr = curr.next;
        }
        return curr.value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E value = current.value;
                current = current.next;
                return value;
            }
        };
    }
}
