package 자바코딩테스트대비.문자열;

import java.util.Scanner;

public class Q11 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();

        StringBuilder answerBuilder = new StringBuilder();
        char[] chars = str.toCharArray();
        char prev = chars[0];
        int count = 1;

        for(int i=1; i<chars.length; i++) {
            if(prev == chars[i]) count++;
            else {
                answerBuilder.append(prev);
                if(count > 1) answerBuilder.append(count);
                count = 1;
            }
            prev = chars[i];
        }

        answerBuilder.append(prev);
        if(count > 1) answerBuilder.append(count);
        System.out.println(answerBuilder);
    }
}
