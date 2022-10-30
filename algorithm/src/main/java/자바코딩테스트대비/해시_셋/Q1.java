package 자바코딩테스트대비.해시_셋;

import java.util.*;

public class Q1 {
    public static String solution(int m, String vote) {
        Map<String, Integer> hashMap = new HashMap<>();
        for(char v : vote.toCharArray()) {
            hashMap.putIfAbsent(String.valueOf(v), 0);
            hashMap.computeIfPresent(String.valueOf(v), (a, b) -> ++b);
        }

        String answer = null;
        int max = Integer.MIN_VALUE;
        for(Map.Entry<String, Integer> entry: hashMap.entrySet()) {
            if(entry.getValue() > max) {
                answer = entry.getKey();
                max = entry.getValue();
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int num1 = in.nextInt();
        String vote = in.next();
        System.out.println(solution(num1, vote));
    }
}
