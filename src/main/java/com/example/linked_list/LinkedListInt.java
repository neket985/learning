package com.example.linked_list;

public interface LinkedListInt {

    Integer getSize();

    void addLastElement(Integer element);

    void addFirstElement(Integer element);

    void addElementAtIndex(Integer element, Integer index);

    void removeLastElement();

    void removeFirstElement();

    void removeElementAtIndex(Integer index);

    Integer getElement(Integer index);

}
