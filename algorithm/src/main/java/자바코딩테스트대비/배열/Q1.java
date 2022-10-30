package 자바코딩테스트대비.배열;

import java.util.Scanner;

public class Q1 {
    public static String solution(int[] arr) {
        // 첫 번째 수는 무조건 출력한다.
        String answer = String.valueOf(arr[0]);
        for(int i=1; i<arr.length; i++) {
            // 두 번째 수부터 검사한다.
            if(arr[i] > arr[i-1]) answer+= " " + arr[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[] arr = new int[num];
        for(int i=0; i<num; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(solution(arr));
    }
}
