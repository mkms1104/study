package 자바코딩테스트대비.해시_셋;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q3 {
    public static String solution(int k, int[] arr) {
        String answer = "";
        Map<Integer, Integer> hashMap = new HashMap<>();

        for(int i=0; i<k; i++) {
            int v = hashMap.getOrDefault(arr[i], 0);
            hashMap.put(arr[i], ++v);
        }
        answer += hashMap.size() + " ";

        for(int rt=4; rt<arr.length; rt++) {
            int rtValue = hashMap.getOrDefault(arr[rt], 0);
            hashMap.put(arr[rt], ++rtValue);

            int lt = rt - k;
            int ltValue = hashMap.get(arr[lt]) - 1;
            hashMap.put(arr[lt], ltValue);
            if(ltValue == 0) hashMap.remove(arr[lt]);
            answer += hashMap.size() + " ";
        }
        return answer;
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
