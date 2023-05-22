package com.example.linked_list;

public class Main {
    public static void main(String[] args) {
        LinkedListInt list = new LinkedListImpl();
        list.addLastElement(1);
        list.addLastElement(2);
        list.addLastElement(3);
        list.addLastElement(4);
        list.removeLastElement();
        list.removeLastElement();

        System.out.println(list);
    }
}
