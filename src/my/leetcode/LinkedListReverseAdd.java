package my.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 */
public class LinkedListReverseAdd {

    public static void main(String[] args) {
        List<Integer> list1 = new LinkedList<Integer>();
        List<Integer> list2 = new LinkedList<Integer>();

        list1.add(6);
        list1.add(4);

        list2.add(6);
        list2.add(7);
        list2.add(4);
        list2.add(9);

        addArray(list1, list2);
    }

    public static void addArray(List list1, List list2) {
        if (list1 != null && list1.size() > 0 && list2 != null && list2.size() > 0) {

            String num1="", num2 = "";
            if (list1.size() == list2.size()) {
                for (int i = list1.size()-1; i > -1; i--) {
                    num1 = num1.concat(list1.get(i).toString());
                    num2 = num2.concat(list2.get(i).toString());
                }
            } else {
                for (int i = list1.size()-1; i > -1; i--) {
                    num1 = num1.concat(list1.get(i).toString());
                }

                for (int i = list2.size()-1; i > -1; i--) {
                    num2 = num2.concat(list2.get(i).toString());
                }
            }

            Long sum = Long.parseLong(num1)+Long.parseLong(num2);
            List<Character> newLst = new LinkedList<>();
            char[] finalStringSum = String.valueOf(sum).toCharArray();
            for (int i = finalStringSum.length-1; i>-1; i--){
                newLst.add(finalStringSum[i]);
            }
            System.out.println("Reverse Num1 = "+num1);
            System.out.println("Reverse Num2 = "+num2);
            System.out.println("Sum = "+sum);
            System.out.println(newLst);
        }
    }
}
