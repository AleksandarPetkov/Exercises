package find.duplicate.characters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindDuplicatesMain {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        /**
         *  Upper and Lower case letters are used as different ASCII symbols in the program!
         *  Just write your input in the console and you will receive the duplicate characters.
         */
        String input = reader.readLine();
        FindDuplicates.findDuplicatesCharacters(input);
    }
}
