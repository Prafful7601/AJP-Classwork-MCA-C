package streamapi;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FirstCharacter {
    public static void main(String[] args) {
        List<String> languages = Arrays.asList("java", "pyhton", "c");

        List<Character> firstChars = languages.stream()
                                              .map(str -> str.charAt(0))
                                              .collect(Collectors.toList());

        System.out.println(firstChars);
    }
}