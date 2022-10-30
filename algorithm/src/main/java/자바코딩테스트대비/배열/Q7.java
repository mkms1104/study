package 자바코딩테스트대비.배열;

import java.util.Scanner;

public class Q7 {
    public static String solution(int[] num) {
        int count = 0;
        int sum = 0;
        for(int i=0; i<num.length; i++) {
            if(num[i] == 1) {
                count++;
                sum += count;
            }
            else {
                count = 0;
            }
        }
        return String.valueOf(sum);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[] arr = new int[num];
        for(int i=0; i< arr.length; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(solution(arr));
    }
}
