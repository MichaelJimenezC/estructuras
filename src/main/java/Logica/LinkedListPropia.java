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
import java.util.NoSuchElementException;

/**
 *
 * @author Michael
 */
public class LinkedListPropia<E> implements List<E>,Serializable {
    
    Nodo<E> primero;

    private class Nodo<E> implements Serializable{

        E contenido;
        Nodo<E> sig;

        Nodo(E e) {
            contenido = e;
            sig = null;
        }

    }

    public LinkedListPropia() {
        primero = null;
    }

    @Override
    public int size() {
        if (!isEmpty()) {
            int tamaño = 0;
            Nodo nodo = primero;
            while (nodo != null) {
                nodo = nodo.sig;
                tamaño++;

            }
            return tamaño;
        } else {
            return 0;
        }

    }

    public void recorrerLista() {
        if (!isEmpty()) {
            Nodo nodo = primero;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Iterator<E> iterator() {
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(E e) {
        if (isEmpty()) {
            primero = new Nodo(e);
            return true;
        } else if (this.size() == 1) {
            primero.sig = new Nodo(e);
            return true;
        } else {
            Nodo<E> actual = primero;
            while (actual != null) {
                actual = actual.sig;
                if (actual.sig == null) {
                    actual.sig = new Nodo(e);
                    return true;
                }
            }

        }
        return true;
    }

    public LinkedListPropia invertirLista() {
        LinkedListPropia nuevaLista = new LinkedListPropia();

        for (int i = this.size(); i > 0; i--) {
            E contenido = this.get(i - 1);
            nuevaLista.add(contenido);

        }

        return nuevaLista;

    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        } else {
            Nodo<E> actual = primero;
            for (int i = 0; i < index - 1; i++) {
                actual = actual.sig;
            }
            Nodo<E> nuevoNodo = new Nodo<>(element);
            nuevoNodo.sig = actual.sig;
            actual.sig = nuevoNodo;
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
        } else {
            Nodo<E> actual = primero;
            for (int i = 0; i < index - 1; i++) {
                actual = actual.sig;
            }
            removido = actual.sig.contenido;
            actual.sig = actual.sig.sig;
        }

        return removido;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("La lista está vacía");
        }

        Nodo<E> actual = primero;
        while (actual.sig != null) {
            actual = actual.sig;
        }

        return actual.contenido;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("La lista está vacía");
        }

        if (size() == 1) {
            E removido = primero.contenido;
            primero = null;
            return removido;
        } else {
            Nodo<E> actual = primero;
            while (actual.sig.sig != null) {
                actual = actual.sig;
            }
            E removido = actual.sig.contenido;
            actual.sig = null;
            return removido;
        }

    }
    
}
