package 자바코딩테스트대비.배열;

import java.util.Scanner;

public class Q9 {
    public static String solution(int[][] num) {
        int rowMax = Integer.MIN_VALUE;
        int colMax = Integer.MIN_VALUE;
        int leftDiagonalSum = 0;
        int rightDiagonalSum = 0;

        for(int i=0; i<num.length; i++) {
            int rowSum = 0;
            int colSum = 0;

            for(int j=0; j<num[0].length; j++) {
                rowSum += num[i][j]; // 1 row
                colSum += num[j][i]; // 1 column

                if(i == j) leftDiagonalSum += num[i][j];
                if(i+j == num.length-1) rightDiagonalSum += num[i][j];
            }

            rowMax = Math.max(rowMax, rowSum);
            colMax = Math.max(colMax, colSum);
        }

        int rowColMax = Math.max(rowMax, colMax);
        int diagonalMax = Math.max(leftDiagonalSum, rightDiagonalSum);
        return String.valueOf(Math.max(rowColMax, diagonalMax));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine();
        int[][] arr = new int[num][num];
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[0].length; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        System.out.println(solution(arr));
    }
}
