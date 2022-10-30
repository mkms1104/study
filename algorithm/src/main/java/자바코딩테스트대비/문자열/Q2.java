package 자바코딩테스트대비.문자열;

import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] wordChars = in.next().toCharArray();
        StringBuilder answer = new StringBuilder();

        for(char wordChar : wordChars) {
            if(isUpperCase(wordChar)) answer.append(Character.toLowerCase(wordChar));
            else answer.append(Character.toUpperCase(wordChar));
        }

        System.out.println(answer);
    }
    public static boolean isUpperCase(char word) {
       return word >= 65 && word <= 90;
    }
}
