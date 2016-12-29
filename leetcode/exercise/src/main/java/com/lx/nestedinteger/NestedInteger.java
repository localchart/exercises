package com.lx.nestedinteger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
public interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

class NestedIterator implements Iterator<Integer> {
    List<Integer> nestedList = new ArrayList<>();
    int index;

    void buildList(List<NestedInteger> lst) {
        for (NestedInteger i : lst) {
            if (i.isInteger()) {
                nestedList.add(i.getInteger());
            } else {
                buildList(i.getList());
            }
        }
    }

    public NestedIterator(List<NestedInteger> lst) {
        buildList(lst);
    }

    @Override
    public Integer next() {
        return nestedList.get(index++);
    }

    @Override
    public boolean hasNext() {
        return nestedList.size() != index;
    }
}
