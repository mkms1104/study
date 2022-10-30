package 자바코딩테스트대비.문자열;

import java.util.Scanner;

public class Q5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        char[] chars = str.toCharArray();

        int p1 = 0; // left pointer
        int p2 = str.length() - 1; // right pointer
        while(p1 < p2) {
            if (!Character.isAlphabetic(chars[p1])) p1++;
            else if (!Character.isAlphabetic(chars[p2])) p2--;
            else {
                char temp = chars[p1];
                chars[p1] = chars[p2];
                chars[p2] = temp;
                p1++;
                p2--;
            }
        }
        System.out.println(String.valueOf(chars));
    }
}
