package 자바코딩테스트대비.문자열;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Q10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        String s = in.next();

        Set<Integer> indexes = new HashSet<>();
        char[] chars = str.toCharArray();
        for(int i=0; i<chars.length; i++) {
            if(s.equals(String.valueOf(chars[i]))) indexes.add(i);
        }


        for(int i=0; i<chars.length; i++) {
            int min = Integer.MAX_VALUE;
            for(int index : indexes) {
                int abs = Math.abs(i - index);
                min = Math.min(min, abs);
            }

            System.out.print(min + " ");
        }
    }
}

