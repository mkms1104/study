package 자바코딩테스트대비.문자열;

import java.util.Scanner;

public class Q6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();

        // 문자열의 길이가 매우 길 경우 좀 더 효율적이다.
        // indexOf는 선형 검색으로 속도가 느리다.
        // Set 컬렉션은 O(1) 검색 속도이다.
//        Set<Character> set = new HashSet<>();
//        String answer = "";
//        for(char c : str.toCharArray()) {
//            if(!set.contains(c)) answer += c;
//            set.add(c);
//        }
//        System.out.println(answer);

        String answer = "";
        for(int i=0; i<str.length(); i++) {
            if(str.indexOf(str.charAt(i)) == i) answer += str.charAt(i);
        }
        System.out.println(answer);
    }
}
