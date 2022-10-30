package 자바코딩테스트대비.문자열;

import java.util.Scanner;

public class Q12 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        String str = in.next();

        String answer = "";
        for(int i=0; i<num; i++) {

            // 암호 문자열을 7자리로 쪼갠다.
            String substring = str.substring(i * 7, (i + 1) * 7);

            // 10진수 변환 (parseInt 사용)
            String a = substring.replace('#', '1').replace('*', '0');
            int decimal = Integer.parseInt(a, 2);
            System.out.println(decimal);

            // 10진수로 변환
//            int decimal = 0;
//            for(int j=substring.length()-1; j>=0; j--) {
//                char c = substring.charAt(j);
//                if(c == '#') decimal += (int) Math.pow(2, substring.length() -1 - j);
//            }

            // 알파벳으로 변경한다.
            answer += (char) decimal;

            // 알파벳으로 변경한다. (java 11)
//            answer += Character.toString(decimal);
        }

        System.out.println(answer);
    }
}
