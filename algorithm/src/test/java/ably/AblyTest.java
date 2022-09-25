package ably;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 에이블리 코딩테스트
public class AblyTest {

    /**
     * 배열 내에 아나그램을 찾는다.
     * sentence 문장 내에 아나그램이 몇개 존재하는지 검사한다.
     */
    @Test
    public void question01_solution01() {
    }

    @Test
    public void question01_solution02() {
        List<String> wordSet = List.of("listen", "silent", "it", "is");
        String sentence = "listen it is silent";

        Map<Integer, Integer> frequencyCounter = new HashMap<>();
        wordSet.forEach((w) -> {
            int charsSum = w.chars().sum(); // 문자열에서 각 문자의 아스키 값 합계
            Integer prev = frequencyCounter.getOrDefault(charsSum, 0);
            frequencyCounter.put(charsSum,  prev + 1);
        });

        // workSet 배열에서 아나그램 찾기
//        List<String> anagramList = new ArrayList<>();
//        wordSet.forEach((w) -> {
//            Integer count = frequencyCounter.get(w.chars().sum());
//
//            // 아나그램
//            if(count == 2) anagramList.add(w);
//        });

        int number = 0;
        String[] letter = sentence.split(" ");
        for(String s : letter) {
            Integer count = frequencyCounter.getOrDefault(s.chars().sum(), 0); // prevent null point
            if(count >= 2) number++;
        }
        System.out.println(number);
    }

    @Test
    public void question02() {
        // 페이지네이션
    }

    @Test
    public void question03() {
        // ?
    }

}
