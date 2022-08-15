package elevenSt;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

// 11번가 코딩 테스트
public class ElevenStTest {

    @Test
    public void solution01() {
        int[] arr = {1, 3 ,6, 4, 1, 2};
        int max = Arrays.stream(arr).max().getAsInt();
        if(max < 0) System.out.println(1);

        else {
            Arrays.sort(arr);
            for(int i=0; i<arr.length; i++) {
                int current = arr[i];
                try {
                    int next = arr[i + 1];
                    if (next - current > 1) {
                        System.out.println(current + 1);
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(max + 1);
                }
            }
        }
    }

    @Test
    public void solution02() {
        int[] arr = {1, 2, 3, 4};
        int max = Arrays.stream(arr).max().getAsInt();
        if(max < 0) System.out.println(1);

        int result = -1;
        int[] parsedArr = Arrays.stream(arr).distinct().sorted().toArray();
        for (int i = 0; i < parsedArr.length; i++) {
            if(parsedArr[i] != i+1) {
                result = parsedArr[i] -1;
                break;
            }
        }

        if(result == -1) result = max + 1;
        System.out.println(result);

    }

    @Test
    public void so() {
        int num = 1000;
        int input = sumOfDigits(num);

        while(num <= 50000) {
            int output = sumOfDigits(++num);
            if(input == output) {
                System.out.println(num);
                break;
            }
        }
    }

    private int sumOfDigits(int num) {
        int sumOfDigits = 0;
        while(num != 0) {
            sumOfDigits += num % 10;
            num /= 10;
        }
        return sumOfDigits;
    }
}
