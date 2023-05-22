package com.example.linked_list;

public class LinkedListNode {
    LinkedListNode next;
    LinkedListNode prev;
    Integer value;

    public LinkedListNode(Integer value){
        this.value = value;
    }
    
    void setNextLink(LinkedListNode next){
        this.next = next;
    }

    void setPrevLink(LinkedListNode prev){
        this.prev = prev;
    }
    
}
