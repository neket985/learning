package com.example.linked_list;

public class LinkedListImpl implements LinkedListInt {
    LinkedListNode head;
    LinkedListNode tail;

    public LinkedListImpl() {
    }

    @Override
    public Integer getSize() {
        return null;
    }

    @Override
    public void addLastElement(Integer element) {
        LinkedListNode node = new LinkedListNode(element);

        if (head == null) {
            head = node;
            tail = node;
        } else {
//            [1, 2] - set 3
            tail.setNextLink(node); //2 -> 3
            node.setPrevLink(tail); // 3 <- 2
            tail = node; // list tail = 3
        }
    }

    @Override
    public void addFirstElement(Integer element) {

    }

    @Override
    public void addElementAtIndex(Integer element, Integer index) {

    }

    @Override
    public void removeLastElement() {
        if (tail == head) {
            head = null;
            tail = null;
        } else {
            tail.prev.setNextLink(null);
            tail = tail.prev;
        }
    }

    @Override
    public void removeFirstElement() {

    }

    @Override
    public void removeElementAtIndex(Integer index) {

    }

    @Override
    public Integer getElement(Integer index) {
        return null;
    }

    //  "[1, 2, 3]"
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        LinkedListNode current = head;
        while (current != null) {
            if (current.next == null) {
                sb.append(current.value.toString());
            } else {
                sb.append(String.format("%s, ", current.value)); //"{current.value}, "
            }
            current = current.next;
        }

        sb.append("]");
        return sb.toString();
    }
}
