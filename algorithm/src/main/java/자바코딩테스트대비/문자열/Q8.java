package 자바코딩테스트대비.문자열;

import java.util.Scanner;

public class Q8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        str = str.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder strByReverse = new StringBuilder(str).reverse();
        System.out.println(str.equals(strByReverse.toString()) ? "YES" : "NO");
    }
}
