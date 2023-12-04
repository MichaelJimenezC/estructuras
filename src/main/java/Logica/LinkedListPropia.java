package Logica;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedListPropia<E> implements List<E>, Serializable {

    private Nodo<E> primero;
    private Nodo<E> ultimo;  // Nuevo atributo para mantener el último nodo

    private class Nodo<E> implements Serializable {

        E contenido;
        Nodo<E> sig;

        Nodo(E e) {
            contenido = e;
            sig = null;
        }

    }

    public LinkedListPropia() {
        primero = null;
        ultimo = null;
    }

    @Override
    public int size() {
        int tamaño = 0;
        Nodo<E> nodo = primero;
        while (nodo != null) {
            tamaño++;
            nodo = nodo.sig;
        }
        return tamaño;
    }

    public void recorrerLista() {
        if (!isEmpty()) {
            Nodo<E> nodo = primero;
            while (nodo != null) {
                System.out.println(nodo.contenido);
                nodo = nodo.sig;
            }
        } else {
            throw new NullPointerException("No hay elementos");
        }
    }

    @Override
    public boolean isEmpty() {
        return primero == null;
    }

    @Override
    public boolean contains(Object o) {
        // Implementación de contains
        Nodo<E> nodo = primero;
        while (nodo != null) {
            if (nodo.contenido.equals(o)) {
                return true;
            }
            nodo = nodo.sig;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        // Implementación del iterator
        return new Iterator<E>() {
            private Nodo<E> current = primero;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E contenido = current.contenido;
                current = current.sig;
                return contenido;
            }
        };
    }

    @Override
    public Object[] toArray() {
        // Implementación de toArray
        Object[] array = new Object[size()];
        Nodo<E> nodo = primero;
        int index = 0;
        while (nodo != null) {
            array[index++] = nodo.contenido;
            nodo = nodo.sig;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // Implementación de toArray con arreglo específico
        if (a.length < size()) {
            a = (T[]) new Object[size()];
        }
        Nodo<E> nodo = primero;
        int index = 0;
        while (nodo != null) {
            a[index++] = (T) nodo.contenido;
            nodo = nodo.sig;
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        // Implementación de add
        Nodo<E> nuevoNodo = new Nodo<>(e);
        if (isEmpty()) {
            primero = nuevoNodo;
            ultimo = nuevoNodo;
        } else {
            ultimo.sig = nuevoNodo;
            ultimo = nuevoNodo;
        }
        return true;
    }

    public LinkedListPropia invertirLista() {
        // Implementación de invertirLista
        LinkedListPropia nuevaLista = new LinkedListPropia();

        for (int i = this.size(); i > 0; i--) {
            E contenido = this.get(i - 1);
            nuevaLista.add(contenido);
        }

        return nuevaLista;
    }

    @Override
    public boolean remove(Object o) {
        // Implementación de remove
        if (isEmpty()) {
            return false;
        }

        if (primero.contenido.equals(o)) {
            primero = primero.sig;
            if (primero == null) {
                ultimo = null;  // La lista está vacía
            }
            return true;
        }

        Nodo<E> actual = primero;
        while (actual.sig != null && !actual.sig.contenido.equals(o)) {
            actual = actual.sig;
        }

        if (actual.sig != null) {
            actual.sig = actual.sig.sig;
            if (actual.sig == null) {
                ultimo = actual;  // Se eliminó el último elemento
            }
            return true;
        }

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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E get(int index) {
        int cont = 0;
        Nodo<E> actual = primero;
        if (!isEmpty()) {
            while (actual != null) {
                if (cont == index) {
                    return actual.contenido;
                }
                actual = actual.sig;
                cont++;
            }
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Nodo<E> actual = primero;
        for (int i = 0; i < index; i++) {
            actual = actual.sig;
        }

        E antiguoContenido = actual.contenido;
        actual.contenido = element;

        return antiguoContenido;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        if (index == 0) {
            Nodo<E> nuevoNodo = new Nodo<>(element);
            nuevoNodo.sig = primero;
            primero = nuevoNodo;
            if (nuevoNodo.sig == null) {
                ultimo = nuevoNodo;  // Se añadió al inicio de la lista vacía
            }
        } else {
            Nodo<E> actual = primero;
            for (int i = 0; i < index - 1; i++) {
                actual = actual.sig;
            }
            Nodo<E> nuevoNodo = new Nodo<>(element);
            nuevoNodo.sig = actual.sig;
            actual.sig = nuevoNodo;
            if (nuevoNodo.sig == null) {
                ultimo = nuevoNodo;  // Se añadió al final de la lista
            }
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        E removido;

        if (index == 0) {
            removido = primero.contenido;
            primero = primero.sig;
            if (primero == null) {
                ultimo = null;  // Se eliminó el único elemento
            }
        } else {
            Nodo<E> actual = primero;
            for (int i = 0; i < index - 1; i++) {
                actual = actual.sig;
            }
            removido = actual.sig.contenido;
            actual.sig = actual.sig.sig;
            if (actual.sig == null) {
                ultimo = actual;  // Se eliminó el último elemento
            }
        }

        return removido;
    }

    @Override
    public int indexOf(Object o) {
        Nodo<E> actual = primero;
        int index = 0;

        while (actual != null) {
            if (actual.contenido.equals(o)) {
                return index;
            }
            actual = actual.sig;
            index++;
        }

        return -1; // Si no se encuentra el elemento, devuelve -1
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {
            private Nodo<E> current = primero;
            private Nodo<E> lastReturned = null;
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastReturned = current;
                current = current.sig;
                currentIndex++;
                return lastReturned.contenido;
            }

            @Override
            public boolean hasPrevious() {
                return currentIndex > 0;
            }

            @Override
            public E previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                if (current == null) {
                    current = ultimo;
                } else {
                    current = current.sig;
                }
                lastReturned = current;
                currentIndex--;
                return lastReturned.contenido;
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
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(E e) {
                if (lastReturned == null) {
                    throw new IllegalStateException();
                }
                lastReturned.contenido = e;
            }

            @Override
            public void add(E e) {
                throw new UnsupportedOperationException();
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

    // Métodos adicionales para obtener y remover el último elemento
    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("La lista está vacía");
        }
        return ultimo.contenido;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("La lista está vacía");
        }

        E removido = ultimo.contenido;

        if (size() == 1) {
            primero = null;
            ultimo = null;
        } else {
            Nodo<E> actual = primero;
            while (actual.sig != ultimo) {
                actual = actual.sig;
            }
            actual.sig = null;
            ultimo = actual;
        }

        return removido;
    }

    public E find(Comparator<E> comparator, E encontrar) {
        Nodo<E> actual = primero;
        while (actual != null) {
            if (comparator.compare(actual.contenido, encontrar) == 0) {
                return actual.contenido;
            }
            actual = actual.sig;
        }
        return null;
    }

    public LinkedListPropia<E> findAll(Comparator<E> comparator, E encontrar) {
        LinkedListPropia<E> encontrados = new LinkedListPropia<>();
        Nodo<E> actual = primero;
        while (actual != null) {
            if (comparator.compare(actual.contenido, encontrar) == 0) {
                encontrados.add(actual.contenido);
            }
            actual = actual.sig;
        }
        return encontrados;
    }

}
