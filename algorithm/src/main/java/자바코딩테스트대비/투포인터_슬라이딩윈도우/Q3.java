package 자바코딩테스트대비.투포인터_슬라이딩윈도우;

import java.util.Scanner;

public class Q3 {
    public static String solution(int day, int[] arr) {
        int sum = 0; // 현재 범위의 합을 기억한다.

        // 첫 범위의 합을 구한다.
        for(int i=0; i<day; i++) {
            sum += arr[i];
        }

        int max = 0;
        for(int i=0; i<arr.length - day; i++) {
            max = Math.max(sum, max);
            sum = sum - arr[i] + arr[day + i];;
        }

        return String.valueOf(max);
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int num1 = in.nextInt();
        int num2 = in.nextInt();

        int[] arr = new int[num1];
        for(int i=0; i<num1; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(solution(num2, arr));
    }
}
