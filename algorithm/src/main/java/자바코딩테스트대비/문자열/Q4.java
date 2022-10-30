package 자바코딩테스트대비.문자열;

import java.util.Scanner;

public class Q4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        String[] words = new String[num];
        for(int i=0; i<num; i++) {
            words[i] = in.next();
        }

        for(String word : words) {
            StringBuilder wordByReverse = new StringBuilder(word).reverse();
            System.out.println(wordByReverse);
        }
    }
}
