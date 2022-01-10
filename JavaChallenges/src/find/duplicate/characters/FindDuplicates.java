package find.duplicate.characters;

import java.util.HashSet;

public class FindDuplicates {
    private static char[] characters;

    protected static void findDuplicatesCharacters(String input) {
        HashSet<Character> duplicates = new HashSet<>();
        if(inputCheck(input)){
            for (int index = 0; index < characters.length - 1; index++) {
                char currentChar = characters[index];
                for (int position = index + 1; position < characters.length; position++) {
                    char nextChar = characters[position];
                    if ((currentChar == nextChar)){
                        duplicates.add(currentChar);
                        break;
                    }
                }
            }
            duplicates.forEach(System.out::println);
        }
    }

    private static boolean inputCheck(String input) {
        if (input != null && input.length() > 0){
            characters = input.toCharArray();
            return true;
        }
        System.out.println("Invalid Input!");
        return false;
    }
}
