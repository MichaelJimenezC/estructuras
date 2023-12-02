/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<E> implements List<E>, Serializable {

    Nodo<E> primero;
    Nodo<E> ultimo;
    int n;
    private static final long serialVersionUID = -4434340018870603190L;

    private static class Nodo<E> implements Serializable {

        E contenido;
        Nodo<E> sig;
        Nodo<E> anterior;

        Nodo(E e, Nodo<E> sig, Nodo<E> anterior) {
            this.contenido = e;
            this.sig = sig;
            this.anterior = anterior;
        }

        @Override
        public String toString() {
            return contenido.toString();
        }

    }

    public DoubleLinkedList() {
        primero = null;
        ultimo = null;
        n = 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public boolean contains(Object o) {
        Nodo<E> actual = primero;

        if (o == null) {
            do {
                if (actual == null || actual.contenido == null) {
                    return true;
                }
                actual = actual.sig;
            } while (actual != primero);
        } else {
            do {
                if (o.equals(actual.contenido)) {
                    return true;
                }
                actual = actual.sig;
            } while (actual != primero);
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new java.util.Iterator<E>() {
            private Nodo<E> trav = primero;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public E next() {
                E data = trav.contenido;
                trav = trav.sig;
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean add(E e) {
        if (isEmpty()) {
            primero = ultimo = new Nodo<E>(e, null, null);
            primero.sig = primero;
            primero.anterior = primero;
        } else {
            Nodo<E> nuevo = new Nodo<E>(e, primero, ultimo);
            ultimo.sig = nuevo;
            primero.anterior = nuevo;
            ultimo = nuevo;
        }
        n++;
        return true;
    }

    public void addFirst(E e) {
        if (isEmpty()) {
            primero = ultimo = new Nodo<E>(e, null, null);
            primero.sig = primero;
            primero.anterior = primero;
        } else {
            Nodo<E> nuevo = new Nodo<E>(e, primero, ultimo);
            ultimo.sig = nuevo;
            primero.anterior = nuevo;
            primero = nuevo;
        }
        n++;
    }

    public DoubleLinkedList invertirLista() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean remove(Object o) {
        Nodo<E> actual = primero;
        do {
            if (actual.contenido.equals(o)) {
                if (n == 1) {
                    primero = ultimo = null;
                } else {
                    actual.anterior.sig = actual.sig;
                    actual.sig.anterior = actual.anterior;
                    if (actual == primero) {
                        primero = actual.sig;
                    }
                    if (actual == ultimo) {
                        ultimo = actual.anterior;
                    }
                }
                n--;
                return true;
            }
            actual = actual.sig;
        } while (actual != primero);
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E actual : c) {
            this.add(actual);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        primero = null;
        ultimo = null;
        n = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Nodo<E> actual = primero;
        for (int i = 0; i < index; i++) {
            actual = actual.sig;
        }
        return actual.contenido;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Nodo<E> actual = primero;
        for (int i = 0; i < index; i++) {
            actual = actual.sig;
        }
        E data = actual.contenido;
        actual.contenido = element;
        return data;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > n) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            addFirst(element);
        } else if (index == n) {
            add(element);
        } else {
            Nodo<E> actual = primero;
            for (int i = 0; i < index; i++) {
                actual = actual.sig;
            }
            Nodo<E> newNode = new Nodo<E>(element, actual, actual.anterior);
            actual.anterior.sig = newNode;
            actual.anterior = newNode;
            n++;
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (index == 0) {
            return removeFirst(); // Llama a un nuevo método para eliminar el primer elemento
        } else {
            Nodo<E> actual = primero;
            for (int i = 0; i < index; i++) {
                actual = actual.sig;
            }
            actual.anterior.sig = actual.sig;
            actual.sig.anterior = actual.anterior;

            if (actual == ultimo) {
                ultimo = actual.anterior;
            }
            n--;

            return actual.contenido;
        }
    }

    public E removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Empty list");
        }

        Nodo<E> actual = primero;
        if (n == 1) {
            primero = ultimo = null;
        } else {
            primero = actual.sig;
            primero.anterior = ultimo;
            ultimo.sig = primero;
        }
        n--;

        return actual.contenido;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        Nodo<E> actual = primero;
        do {
            if (o == null ? actual.contenido == null : o.equals(actual.contenido)) {
                return index;
            }
            actual = actual.sig;
            index++;
        } while (actual != primero);
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {
            private Nodo<E> current = primero;
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                E data = current.contenido;
                current = current.sig;
                currentIndex++;
                return data;
            }

            @Override
            public boolean hasPrevious() {
                return current != primero;
            }

            @Override
            public E previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }

                if (current == null) {
                    // Estamos al final de la lista, retrocedemos al último elemento
                    current = ultimo;
                } else {
                    current = current.anterior;
                }

                currentIndex--;
                return current.contenido;
            }

            @Override
            public int nextIndex() {
                return currentIndex;
            }

            @Override
            public int previousIndex() {
                return currentIndex - 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void set(E e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void add(E e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public E getLast() {
        return ultimo.contenido;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("Empty list");
        }
        E data = ultimo.contenido;
        ultimo = ultimo.anterior;
        if (ultimo != null) {
            ultimo.sig = primero; // Actualiza el enlace del último al primer nodo
            primero.anterior = ultimo; // Actualiza el enlace del primer nodo al último
        } else {
            primero = null;
        }
        n--;
        return data;
    }

    public <E> boolean esCircular() {
        if (this.isEmpty()) {
            return true;
        }

        DoubleLinkedList.Nodo<E> actual = (DoubleLinkedList.Nodo<E>) this.primero;
        System.out.println(actual.contenido);

        // Avanzar a través de la lista y comprobar si se regresa al nodo inicial
        do {
            actual = actual.sig;
            System.out.println(actual.contenido);
        } while (actual != this.primero && actual != null);

        return actual == this.primero;
    }

    public void printCircularList() {
        if (this.isEmpty()) {
            return; // La lista está vacía
        }

        Nodo<E> actual = this.primero;
        do {
            System.out.print(actual.contenido + " ");
            actual = actual.sig;
        } while (actual != this.primero);
    }
    public E find(Comparator<E> comparator, E encontrar) {
        if (isEmpty()) {
            return null;
        }

        Nodo<E> actual = primero;
        do {
            if (comparator.compare(actual.contenido, encontrar) == 0) {
                return actual.contenido;
            }
            actual = actual.sig;
        } while (actual != primero);

        return null;
    }

    public DoubleLinkedList<E> findAll(Comparator<E> comparator, E encontrar) {
        DoubleLinkedList<E> encontrados = new DoubleLinkedList<>();
        if (isEmpty()) {
            return encontrados;
        }

        Nodo<E> actual = primero;
        do {
            if (comparator.compare(actual.contenido, encontrar) == 0) {
                encontrados.add(actual.contenido);
            }
            actual = actual.sig;
        } while (actual != primero);

        return encontrados;
    }

    @Override
    public String toString() {
        return "DoubleLinkedList{" + "n=" + n + '}';
    }

}
