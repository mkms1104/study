package 자바코딩테스트대비.투포인터_슬라이딩윈도우;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Q2 {

    public static String solution(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        List<Integer> answer = new ArrayList<>();
        int i = 0, j = 0;
        while(i < arr1.length && j < arr2.length) {
            if(arr1[i] == arr2[j]) {
                answer.add(arr1[i]);
                i++;
                j++;
            }

            else if(arr1[i] < arr2[j]) {
                i++;
            }

            else {
                j++;
            }
        }

        return answer.stream().map(o -> o.toString()).collect(Collectors.joining(" "));
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
