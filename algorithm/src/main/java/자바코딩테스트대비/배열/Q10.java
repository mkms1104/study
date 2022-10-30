package 자바코딩테스트대비.배열;

import java.util.Scanner;

public class Q10 {
    public static String solution(int[][] num) {
        for(int i=0; i<num.length; i++) {
            for(int j=0; j<num.length; i++) {
                int target = num[i][j];

            }
        }
        return "";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[][] arr = new int[num][num];
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[0].length; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        System.out.println(solution(arr));
    }
}
