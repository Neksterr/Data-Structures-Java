package implementations;

import interfaces.List;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_SIZE = 4; // начална
    private Object[] elements;
    private int size;
    private int capacity;// запазване колко елемента имам възможност да съхраня в момента.

    public ArrayList() {
        this.elements = new Object[INITIAL_SIZE];
        this.size = 0;
    }

    @Override
    public boolean add(E element) {
        if (this.size == this.elements.length) {
           this.elements =  grow();
        }


        this.elements[this.size++] = element;
        return true;
    }


    @Override
    public boolean add(int index, E element) {
        if (!validIndex(index)) {
            return false;

        }
        shiftRight(index);
        this.elements[index] = element;//присвояване на елемента на дадения индекс
        this.size++;
        return true;
    }


    @Override
    public E get(int index) {
        //проверка на идекс
        ensureIndex(index);
        return (E) this.elements[index];
    }


    @Override
    public E set(int index, E element) {
        ensureIndex(index); //проверка на индекса
        //запазване на елемента който стои там, за да можем да го върнем
        Object existing = this.elements[index];
        this.elements[index] = element;
        return (E) existing;
    }

    @Override
    public E remove(int index) {
        ensureIndex(index); //walidaciq
        //запазване на елемента който стои там, за да можем да го върнем
        Object existing = this.elements[index];
        shiftLeft(index);
        this.size--;
        shrinkIfNeeded();
        return (E) existing;
    }

    private void shrinkIfNeeded() {
        if (this.size > this.capacity / 3){
            return;
        }
        this.capacity /= 2;
        this.elements = Arrays.copyOf(this.elements, this.capacity / 2);

    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
       for(int i = 0; i < this.size; i++){
           if (this.elements[i].equals(element)){
               return i;
           }
       }
       return -1;
    }

    @Override
    public boolean contains(E element) {
        return this.indexOf(element) != -1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < size() ;
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }
    private Object[] grow(){
        return Arrays.copyOf(this.elements,this.elements.length * 2);
    }

    /*private void grow() {
        this.capacity *= 2; // удвояваме пространството и трябва да намравим нов масив
        Object[] tmp = new Object[this.capacity];
        //трябва да се прехвърлят всички елементи от стария масив в новия
        for (int i = 0; i < this.elements.length; i++) {
            tmp[i] = this.elements[i];
        }
        this.elements = tmp;
    }*/

    private void shiftRight(int index) {
        //[1,2,3,4......]
        //[1,2,3,4,4....]
        //[1,2,3,3,4....]
        //добавяне на определен индех и преместване в дясно всички след този индех. Добавя се на втория (3) индех и копираме останалите
        for (int i = this.size - 1; i >= index; i--) {
            this.elements[i + 1] = this.elements[i];
        }
    }

    private void shiftLeft(int index) {
        for (int i = index; i < this.size -1; i++) {
            this.elements[i] = this.elements[i + 1];
        }
    }

    private boolean validIndex(int index) {
        return index >= 0 && index < this.size;
    }

    private void ensureIndex(int index) {
        if (!validIndex(index)) {
            throw new IndexOutOfBoundsException("Cannot use index " + index + " on ArrayList with " + this.size + " elements");
        }
    }

}
