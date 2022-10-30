package 자바코딩테스트대비.배열;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q3 {
    public static List<String> solution(int[][] arr) {
        int[] arr1 = arr[0]; // n번의 경기 동안 A가 낸 정보
        int[] arr2 = arr[1]; // n번의 경기 동안 B가 낸 정보

        List<String> answer = new ArrayList<>();
        for(int i=0; i<arr1.length; i++) {
            if(arr1[i] == arr2[i]) {
                answer.add("D");
            }
            else if(
                    (arr1[i] == 1 && arr2[i] == 3) ||
                    (arr1[i] == 2 && arr2[i] == 1) ||
                    (arr1[i] == 3 && arr2[i] == 2)
            ) {
                answer.add("A");
            }
            else {
                answer.add("B");
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[][] arr = new int[2][num];
        for(int i=0; i<2; i++) {
            for(int j=0; j<num; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        solution(arr).forEach(System.out::println);
    }
}
