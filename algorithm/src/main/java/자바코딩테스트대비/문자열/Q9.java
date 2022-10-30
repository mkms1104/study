package 자바코딩테스트대비.문자열;

import java.util.Scanner;

public class Q9 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();

        str = str.replaceAll("[^0-9]", "");
        System.out.println(Math.abs(Integer.valueOf(str)));
    }
}
