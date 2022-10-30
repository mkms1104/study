package 자바코딩테스트대비.배열;

import java.util.Scanner;

public class Q8 {

    // O(n^2) 풀이
    public static String solution(int[] num) {
        String answer = "";
        for(int i=0; i<num.length; i++) {
            int rank = 1;
            for(int j=0; j<num.length; j++) {
                if(i != j) {
                    if(num[i] < num[j]) rank++;
                }
            }
            answer += rank + " ";
            rank = 1;
        }
        return answer;
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
