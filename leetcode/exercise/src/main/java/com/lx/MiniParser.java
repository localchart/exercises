package com.lx;

import java.util.List;
import java.util.Stack;

public class MiniParser {
    int offset = 0;

    //Given s = "[123,[456,[789]]]",
    public NestedInteger deserialize(String s) {
        if (offset >= s.length()) {
            return null;
        }
        NestedInteger token = token(s);
        if (token == null) {
            return null;
        } else if (token.isInteger()) {
            return token;
        } else {
            NestedInteger next;
            while ((next = deserialize(s)) != null) {
                token.add(next);
            }
            return token;
        }
    }

    public NestedInteger token(String s) {
        char c = s.charAt(offset);
        if (c == '[') {
            offset++;
            return new NestedInteger();
        } else if (c == ']') {
            offset++;
            return null;
        } else if (Character.isDigit(c)) {
            return getInteger(s);
        } else if (c == '-') {
            offset++;
            NestedInteger integer = getInteger(s);
            return new NestedInteger(Integer.parseInt("-" + integer.getInteger()));
        } else {
            offset++;
            return token(s);
        }
    }

    private NestedInteger getInteger(String s) {
        StringBuilder sb = new StringBuilder();
        for (; offset < s.length(); offset++) {
            char c1 = s.charAt(offset);
            if (Character.isDigit(c1)) {
                sb.append(c1);
            } else if (c1 == ',') {
                offset++;
                break;
            } else {
                break;
            }
        }
        return new NestedInteger(Integer.parseInt(sb.toString()));
    }

    public static void main(String[] args) {
        MiniParser solution = new MiniParser();
        NestedInteger deserialize = solution.deserialize("[[72,0,74,94,17,38,98,75,2,77,29,37,54,36,89,42,32,97,87,71,16,24,19,76,7]]");
//        NestedInteger deserialize = solution.deserialize("[-1,-2]");
//        NestedInteger deserialize = solution.deserialize("[123,[-45-6,[789]]]");
        System.out.println(deserialize);
    }
}

class NestedInteger {
    Integer value;
    Stack<NestedInteger> lst;

    // Constructor initializes an empty nested list.
    NestedInteger() {
        lst = new Stack<>();
    }

    // Constructor initializes a single integer.
    NestedInteger(int value) {
        setInteger(value);
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return value != null;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    Integer getInteger() {
        return value;
    }

    // Set this NestedInteger to hold a single integer.
    void setInteger(int value) {
        this.value = value;
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    void add(NestedInteger ni) {
        lst.add(ni);
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    List<NestedInteger> getList() {
        return lst;
    }

    @Override
    public String toString() {
        if (value == null) {
            return "lst : " + lst;
        } else {
            return "value : " + value;
        }
    }
}