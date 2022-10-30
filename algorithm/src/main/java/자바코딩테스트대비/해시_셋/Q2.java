package 자바코딩테스트대비.해시_셋;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q2 {
    public static String solution(String word1, String word2) {
        Map<Character, Integer> word1Frequency = new HashMap<>();
        Map<Character, Integer> word2Frequency = new HashMap<>();

        for(char word : word1.toCharArray()) {
            word1Frequency.putIfAbsent(word, 0);
            word1Frequency.computeIfPresent(word, (k, v) -> ++v);
        }

        for(char word : word2.toCharArray()) {
            word2Frequency.putIfAbsent(word, 0);
            word2Frequency.computeIfPresent(word, (k, v) -> ++v);
        }

        String answer = "YES";
        for(Map.Entry<Character, Integer> entry : word1Frequency.entrySet()) {
            Integer word2Cnt = word2Frequency.get(entry.getKey());
            if(word2Cnt == null) answer = "NO";
            if(entry.getValue() != word2Cnt) answer = "NO";
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String word1 = in.next();
        String word2 = in.next();
        System.out.println(solution(word1, word2));
    }
}
