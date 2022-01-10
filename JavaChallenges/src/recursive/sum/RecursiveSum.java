package recursive.sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RecursiveSum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        /**
         * Please write a number in console and you will receive
         * the sum of all the numbers from zero to that number recursively.
         */
        int number = Integer.parseInt(reader.readLine());
        System.out.println(getRecursiveSum(number));
    }

    private static int getRecursiveSum(int number) {
        if (number != 0) {
            return number + getRecursiveSum(number - 1);
        } else {
            return number;
        }
    }
}
