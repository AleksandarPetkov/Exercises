package smallest.largest.element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FindElementsMain {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        /**
         * Please use the console and write a sequence of integers like [ 0 10 -500 2000 77 ]
         * and you will receive smallest and largest number
         */
        int[] numbers = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        FindElements.findSmallestAndLargestElement(numbers);
    }
}
