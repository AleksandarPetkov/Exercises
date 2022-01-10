package smallest.largest.element;

public class FindElements {
    private static int smallestNumber = Integer.MAX_VALUE;
    private static int largestNumber = Integer.MIN_VALUE;

    protected static void findSmallestAndLargestElement(int[] numbers){
        if(isInputCorrect(numbers)){
            for (int input = 0; input < numbers.length; input++) {
                checkElement(numbers[input]);
            }
            System.out.printf("Smallest number is %d%n",smallestNumber);
            System.out.printf("Largest number is %d",largestNumber);
        }
    }

    private static void checkElement(int currentNumber){
        if(currentNumber > largestNumber){
            largestNumber = currentNumber;
        }
        if(currentNumber < smallestNumber){
            smallestNumber = currentNumber;
        }
    }

    private static boolean isInputCorrect(int[] numbers) {
        if (numbers.length > 1){
            return true;
        }
        System.out.println("Invalid Input! At least two elements are required!");
        return false;
    }
}
