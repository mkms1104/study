package com.example.demo.dataStructure;

import org.junit.jupiter.api.Test;

import java.util.*;

public class ListTest {
    @Test
    public void array() {
        int[] arr = {10, 20, 30, 40, 50};
        arr[2] = 3;
        System.out.println(Arrays.toString(arr));

        Integer[] arr2 = new Integer[5];
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = new int[5];
        System.out.println(Arrays.toString(arr3));

        int[] arr4 = {};
        System.out.println(Arrays.toString(arr4));
    }

    @Test
    public void arrayList() {
        List<Integer> list = new ArrayList<>(1);
        list.add(10);
        list.add(10);
        System.out.println(list);
    }

    @Test
    public void linkedList() {
        List<Integer> list = new LinkedList<>();
        list.add(10);
    }

    @Test
    public void stack() {
        Stack<String> stack = new Stack<>();

        stack.push("a");
        stack.push("b");
        System.out.println(stack.size());

        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
