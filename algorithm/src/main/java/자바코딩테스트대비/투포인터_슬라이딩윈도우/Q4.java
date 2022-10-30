package 자바코딩테스트대비.투포인터_슬라이딩윈도우;

import java.util.Scanner;

public class Q4 {
    public static String solution(int m, int[] arr) {
        int lt = 0;
        int rt = 0;
        int sum = arr[0];

        int count = 0;
        for(int i=0; i<arr.length; i++) {
            if(sum == m) {
                count++;
                lt++;
            }
            else if(sum < m) {
                rt++;
                sum += arr[rt];
            }
            else {
                sum -= arr[lt];
                lt++;
            }
        }

        return String.valueOf(count);

//        1 2 1 3 1 1 1 2

//        m : 6
//        sum : 1
//        lt : 0
//        rt : 0
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
