package 자바코딩테스트대비.해시_셋;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q4 {

    public static String solution(String s, String t) {
        int answer = 0;
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        Map<Character, Integer> targetMap = new HashMap<>(); // 비교할 아나그램 빈도수 카운터
        for(Character n : tArr) {
            targetMap.put(n, targetMap.getOrDefault(n, 0) + 1);
        }

        Map<Character, Integer> slidingMap = new HashMap<>();
        for(int i=0; i<tArr.length - 1; i++) {
            slidingMap.put(sArr[i], slidingMap.getOrDefault(sArr[i], 0) + 1);
        }

        int lt = 0;
        for(int rt=tArr.length - 1; rt<sArr.length; rt++) {
            slidingMap.put(sArr[rt], slidingMap.getOrDefault(sArr[rt], 0) + 1);
            if(targetMap.equals(slidingMap)) answer++;

            slidingMap.put(sArr[lt], slidingMap.getOrDefault(sArr[lt], 1) - 1);
            if(slidingMap.get(sArr[lt]) == 0) slidingMap.remove(sArr[lt]);
            lt++;
        }

        return String.valueOf(answer);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String str1 = in.next();
        String str2 = in.next();
        System.out.println(solution(str1, str2));
    }
}
