package com.dmz.service.utils;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author dmz
 * @date 2017/3/27
 */
public class ConcurrentStack<E> {

    private AtomicReference<Node<E>> head = new AtomicReference<>();

    public void push(E e) {
        Node<E> newNode = new Node<>(e);
        Node<E> oldNode;
        do {
            oldNode = head.get();
            newNode.next = oldNode;
        } while (!head.compareAndSet(oldNode, newNode));
    }

    public Node<E> pop() {
        Node<E> oldNode;
        Node<E> newNode;
        do {
            oldNode = head.get();
            if (oldNode == null) {
                return null;
            }
            newNode = oldNode.next;
        } while (!head.compareAndSet(oldNode, newNode));

        return newNode;
    }

    static class Node<E> {
        final E element;
        Node<E> next;

        Node(E element) {
            this.element = element;
        }
    }

}
