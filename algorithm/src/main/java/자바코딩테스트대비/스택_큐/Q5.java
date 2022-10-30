package 자바코딩테스트대비.스택_큐;

import java.util.Scanner;
import java.util.Stack;

public class Q5 {
    public static String solution(String str) {
        int answer = 0;
        Stack<Character> stack = new Stack<>();

        for(char s : str.toCharArray()) {
            if(s == ')') {
                // 레이저
                if('(' == stack.peek()) {
                    stack.pop();
                    answer += stack.size();
                }

                // 막대 끝
                else {
                    stack.pop();
                    answer += 1;
                }

            }
            else {
                stack.push(s);
            }
        }

        return String.valueOf(answer);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        System.out.println(solution(str));
    }
}
