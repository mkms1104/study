package 자바코딩테스트대비.문자열;

import java.util.Scanner;

public class Q3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String sentence = in.nextLine();

        String[] sentenceBySplit = sentence.split(" ");
        int maxLen = Integer.MIN_VALUE;
        String answer = null;

        for(String s : sentenceBySplit) {
            if(s.length() > maxLen) {
                maxLen = s.length();
                answer = s;
            }
        }
        System.out.println(answer);
    }
}
