package 자바코딩테스트대비.스택_큐;

import java.util.Scanner;
import java.util.Stack;

public class Q1 {
    public static String solution(String str) {
        Stack<String> stack = new Stack<>();
        for(char s : str.toCharArray()) {
            if(s == '(') stack.push("(");
            else stack.pop();
        }
        return stack.isEmpty() ? "YES" : "NO";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        System.out.println(solution(str));
    }
}
