package implementations;

import interfaces.AbstractStack;

import java.util.Arrays;
import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {

    private Node<E> top;
    private int size;

    private static class Node<E> {
        private E value;
        private Node<E> next;

        Node(E element) {
            this.value = element;
        }
    }

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push(E element) {
        //съсздаване на елемент
        Node<E> toInsert = new Node<>(element);
        toInsert.next = this.top;
        this.top = toInsert;
        //добавяне
        this.size++;
        //връщане


    }

    @Override
    public E pop() {
        ensureNotEmpty();
        Node<E> tmp = this.top; //head of node
        this.top = tmp.next;// pointer
        this.size--;
        return tmp.value;// returns last to pop

    }

    @Override
    public E peek() {
        ensureNotEmpty();
        return this.top.value;
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
            private Node<E> current = top;

            @Override
            public boolean hasNext() {
                return this.current != null;
            }

            @Override
            public E next() {
                E value = this.current.value;
                this.current = this.current.next;
                return value;
            }

        };
    }


    private void ensureNotEmpty() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
    }
}
