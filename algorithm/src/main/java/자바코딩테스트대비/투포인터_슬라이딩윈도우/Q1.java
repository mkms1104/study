package 자바코딩테스트대비.투포인터_슬라이딩윈도우;

import java.util.Scanner;

public class Q1 {
    public static String solution(int[] arr1, int[] arr2) {
        int i = 0;
        int j = 0;

        String answer = "";
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                answer += arr1[i] + " ";
                i++;
            } else {
                answer += arr2[j] + " ";
                j++;
            }
        }

        while (i < arr1.length) {
            answer += arr1[i] + " ";
            i++;
        }

        while (j < arr2.length) {
            answer += arr2[j] + " ";
            j++;
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int num1 = in.nextInt();
        int[] arr1 = new int[num1];
        for(int i=0; i<num1; i++) {
            arr1[i] = in.nextInt();
        }

        int num2 = in.nextInt();
        int[] arr2 = new int[num2];
        for(int i=0; i<num2; i++) {
            arr2[i] = in.nextInt();
        }

        System.out.println(solution(arr1, arr2));
    }
}
