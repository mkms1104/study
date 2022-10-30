package 자바코딩테스트대비.문자열;

import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String word = in.next().toUpperCase();
        char targetChar = in.next().toUpperCase().charAt(0);

        int answer = 0;
        for(char wordChar : word.toCharArray()) {
            if(wordChar == targetChar) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
