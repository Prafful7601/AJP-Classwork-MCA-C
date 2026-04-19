package streamapi;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntegerToString {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 20, 30);

        List<String> stringList = numbers.stream()
                                         .map(String::valueOf)
                                         .collect(Collectors.toList());

        System.out.println(stringList);
    }
}