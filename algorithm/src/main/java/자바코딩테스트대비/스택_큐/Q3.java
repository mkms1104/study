package 자바코딩테스트대비.스택_큐;

import java.util.*;

public class Q3 {
    public static String solution(int[][] board, int[] moves) {
        int answer = 0;

        // 열 단위로 스택 만들기
        List<Stack> stackBoard = new ArrayList<>();
        for(int i=0; i<board.length; i++) {
            Stack<Integer> stack  = new Stack<>();
            for(int j=board.length-1; j>=0; j--) { // 뒤에서 부터 꺼내므로 역순으로 초기화해야 한다.
                if(board[j][i] != 0) stack.push(board[j][i]); // 0은 빈칸을 나타내므로 넣지 않는다.
            }
            stackBoard.add(stack);
        }

        Stack<Integer> basket = new Stack<>();
        basket.push(Integer.MIN_VALUE); // EmptyStackException 방지

        // 크레인 n번 동작
        for(int i=0; i<moves.length; i++) {
            int position = moves[i] - 1;
            if(stackBoard.get(position).isEmpty()) continue;
            int targetNumber = (int) stackBoard.get(position).pop(); // 뽑은 인형 번호

            int peekNumberInBasket = basket.peek(); // 현재 바구니 맨 위에 있는 인형 번호
            if(peekNumberInBasket == targetNumber) {
                answer += 2;
                basket.pop();
            } else {
                basket.push(targetNumber); // 바구니에 추가
            }
        }
        return String.valueOf(answer);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] board = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                board[i][j] = in.nextInt();
            }
        }
        int m = in.nextInt();
        int[] moves = new int[m];
        for(int i=0; i<m; i++) {
            moves[i] = in.nextInt();
        }
        System.out.println(solution(board, moves));
    }
}
