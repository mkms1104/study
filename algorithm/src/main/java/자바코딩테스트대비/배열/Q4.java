package 자바코딩테스트대비.배열;

import java.util.Scanner;

public class Q4 {
    public static int[] solution(int num) {
        int[] answer = new int[num];
        for(int i=0; i<num; i++) {
            if(i==0 || i==1) answer[i] = 1;
            else answer[i] = answer[i - 2] + answer[i - 1];
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        for(int n : solution(num)) System.out.print(n + " ");
    }
}
