package 자바코딩테스트대비.투포인터_슬라이딩윈도우;

import java.util.*;

public class Q6 {
    public static String solution(int k, int[] arr) {
        Queue<Integer> queue = new LinkedList<>();

        int max = Integer.MIN_VALUE, count = 0;
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == 0) {
                queue.add(i);
            }

            if(queue.size() > k) {
                max = Math.max(max, count);
                count = i - 1 - queue.poll();
            }
            count++;
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
