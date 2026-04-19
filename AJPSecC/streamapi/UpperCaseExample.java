package streamapi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UpperCaseExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("sachin", "amit");

        List<String> upperNames = names.stream()
                                       .map(String::toUpperCase)
                                       .collect(Collectors.toList());

        System.out.println(upperNames);
    }
}
