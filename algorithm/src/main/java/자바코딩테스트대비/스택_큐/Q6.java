package 자바코딩테스트대비.스택_큐;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Q6 {
    public static String solution(int n, int k) {
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[n] = i+1;
        }

        int i = 1;
//        while() {
//            int targetIndex = i % k;
//            if(arr[targetIndex] == 0) continue;
//
//            if(targetIndex == 0) {
//                arr[targetIndex] = 0; // 마킹
//            }
//
//
//            i++;
//        }

        return "";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        System.out.println(solution(n, k));
    }
}
